import sys


def solve(reference_day, accounts, transactions, merchants, device_activities, location_risks):
    """
    reference_day:
        Fixed analysis day.
        
    accounts: List of tuples:(account_id, account_holder, account_type, home_location, input_index)
    
    transactions: List of tuples:(transaction_id, account_id, merchant_id, device_id, location,
        transaction_day, amount, status, input_index)
        
    merchants: List of tuples:(merchant_id, category, risk_tier)
    
    device_activities: List of tuples:(activity_id, account_id, device_id, first_used_day)
    
    location_risks: List of tuples:(location, risk_score)
    
    Return:
        Final output string in the required format.
        Return "NA" if no transaction qualifies.
    """
    
    final_output = ""
    
    # Sets/Dictionaries for O(1) validations and attribute lookups
    account_set = {a[0] for a in accounts}
    account_holders = {a[0]: a[1] for a in accounts}
    account_home = {a[0]: a[3] for a in accounts}
    
    merchant_set = {m[0] for m in merchants}
    merchant_tiers = {m[0]: m[2] for m in merchants}
    
    location_set = {l[0] for l in location_risks}
    location_risk_map = {l[0]: int(l[1]) for l in location_risks}

    # Process Device Activity: group by (accountId, deviceId) and keep the earliest firstUsedDay
    device_earliest = {}
    for activity_id, account_id, device_id, first_used_day in device_activities:
        if account_id in account_set and int(first_used_day) >= 1:
            key = (account_id, device_id)
            day_val = int(first_used_day)
            if key not in device_earliest or day_val < device_earliest[key]:
                device_earliest[key] = day_val

    # Step 1: Filter valid transactions and count successful ones per account
    valid_txs = []
    success_counts_per_day = {} # (accountId, transactionDay) -> count
    success_amounts_per_account = {} # accountId -> list of successful amounts

    for tx in transactions:
        tx_id, acc_id, merch_id, dev_id, loc, tx_day, amt, status, input_idx = tx
        tx_day_int = int(tx_day)
        amt_float = float(amt)
        
        # 1-6. Valid record rules check
        if (acc_id in account_set and 
            merch_id in merchant_set and 
            loc in location_set and 
            1 <= tx_day_int <= int(reference_day) and 
            amt_float > 0 and 
            status in ("SUCCESS", "FAILED")):
            
            # Keep trace of it
            valid_txs.append(tx)
            
            # Only SUCCESS transactions are scored and counted toward features
            if status == "SUCCESS":
                # For sameDayTransactionCount feature
                day_key = (acc_id, tx_day_int)
                success_counts_per_day[day_key] = success_counts_per_day.get(day_key, 0) + 1
                
                # For accountAverageAmount feature
                if acc_id not in success_amounts_per_account:
                    success_amounts_per_account[acc_id] = []
                success_amounts_per_account[acc_id].append(amt_float)

    # Step 2: Score each valid SUCCESS transaction
    scored_transactions = []
    
    for tx in valid_txs:
        tx_id, acc_id, merch_id, dev_id, loc, tx_day, amt, status, input_idx = tx
        if status != "SUCCESS":
            continue
            
        tx_day_int = int(tx_day)
        amt_float = float(amt)
        
        # Feature calculations
        # 1. accountAverageAmount
        amounts = success_amounts_per_account.get(acc_id, [])
        avg_amt = sum(amounts) / len(amounts) if amounts else 0.0
        
        # 2. sameDayTransactionCount
        same_day_cnt = success_counts_per_day.get((acc_id, tx_day_int), 0)
        
        # 3. isUnknownDevice & 4. isFutureDevice
        dev_key = (acc_id, dev_id)
        is_unknown = dev_key not in device_earliest
        is_future = (not is_unknown) and (device_earliest[dev_key] > tx_day_int)
        
        # 5. merchantRiskTier
        m_tier = merchant_tiers.get(merch_id, "LOW")
        
        # 6. locationRiskScore
        loc_score = location_risk_map.get(loc, 0)
        
        # 7. isOutsideHomeLocation
        is_outside = loc != account_home.get(acc_id, "")

        # Calculate Fraud Risk Score
        score = 0
        if amt_float > 2 * avg_amt: score += 4
        if same_day_cnt >= 3: score += 3
        if is_unknown: score += 4
        if is_future: score += 3
        if m_tier == "HIGH": score += 3
        elif m_tier == "MEDIUM": score += 1
        if loc_score >= 4: score += 3
        if is_outside: score += 2

        # Map Score to Risk Level
        if score >= 10:
            level = "HIGH"
        elif 6 <= score <= 9:
            level = "MEDIUM"
        else:
            level = "LOW"

        if level in ("HIGH", "MEDIUM"):
            holder_name = account_holders.get(acc_id, "")
            out_str = f"{tx_id}-{holder_name}-{level}-{score}"
            # Store values for multi-criteria sorting
            scored_transactions.append({
                "str": out_str,
                "level_rank": 0 if level == "HIGH" else 1, # HIGH before MEDIUM
                "score": score,
                "amount": amt_float,
                "idx": input_idx
            })

    # Step 3: Sort outputs by criteria
    if not scored_transactions:
        return "NA"

    scored_transactions.sort(key=lambda x: (x["level_rank"], -x["score"], -x["amount"], x["idx"]))
    
    # Join results using hash symbol '#'
    final_output = "#".join([item["str"] for item in scored_transactions])
    
    return final_output

