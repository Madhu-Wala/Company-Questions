# Fraudulent Transaction Detection - Coding Challenge

(Solution should strictly be in Python)

## Problem Description

A bank wants to identify successful transactions that may be fraudulent. 

The bank stores account information, transaction details, merchant profiles, device activity history, and location risk data. Your task is to analyse the records and print transactions whose fraud risk level is *HIGH* or *MEDIUM*.

The fraud risk must be calculated using account transaction behaviour, same-day transaction velocity, merchant risk, device usage, transaction location, and location risk score.

Read input from **STDIN** and print output to **STDOUT**. Do not print arbitrary strings anywhere in the program, as these contribute to the standard output and test cases will fail.

---

## Data Structures

### Accounts Table

| Field | Description |
| :--- | :--- |
| accountId | Unique identifier of the account |
| accountHolder | Name of the account holder |
| accountType | Type of account such as *SAVINGS* or *CURRENT* |
| homeLocation | Registered home location of the account |

### Transactions Table

| Field | Description |
| :--- | :--- |
| transactionId | Unique identifier of the transaction |
| accountId | Account from which the transaction was made |
| merchantId | Merchant where the transaction was made |
| deviceId | Device used for the transaction |
| location | Location where the transaction was made |
| transactionDay | Day number of the transaction |
| amount | Transaction amount |
| status | *SUCCESS* or *FAILED* |

### Merchant Profiles Table

| Field | Description |
| :--- | :--- |
| merchantId | Unique identifier of the merchant |
| category | Merchant category |
| riskTier | Merchant risk tier: *LOW*, *MEDIUM*, or *HIGH* |

### Device Activity Table

| Field | Description |
| :--- | :--- |
| activityId | Unique identifier of the device activity record |
| accountId | Account that used the device |
| deviceId | Device used by the account |
| firstUsedDay | First day on which the device was used by the account |

### Location Risk Table

| Field | Description |
| :--- | :--- |
| location | Location code |
| riskScore | Location risk score |


## Valid Record Rules

A transaction is valid only when all conditions are satisfied:
1. `accountId` exists in the Accounts table.
2. `merchantId` exists in the Merchant Profiles table.
3. `location` exists in the Location Risk table.
4. `transactionDay` is between 1 and `referenceDay`, inclusive.
5. `amount` is greater than 0.
6. `status` is *SUCCESS* or *FAILED*.

Only valid *SUCCESS* transactions are scored and considered for output.

A device activity record is valid only when:
1. `accountId` exists in the Accounts table.
2. `firstUsedDay` is greater than or equal to 1.

If multiple valid device activity records exist for the same `accountId` and `deviceId`, use the earliest `firstUsedDay`.

Invalid records must be ignored completely. Each transaction record must be processed independently. If identical transaction records occur more than once, each occurrence must be included separately in the account statistics, same-day transaction count, and output evaluation.

For multiplt caalid Device Activity records having the same `accountId` and `deviceId`, use the eraliest `firstUsedDay`. The identifiers in the Accounts, Merchant Profiles, and Location Risk tables are guaranteed to be unique.


## Features to Calculate

For every valid *SUCCESS* transaction, calculate the following values:

1. **`accountAverageAmount`**
   $$accountAverageAmount = \frac{\text{sum of successful transaction amounts of the account}}{\text{count of successful transactions of the account}}$$

2. **`sameDayTransactionCount`**
   Count valid *SUCCESS* transactions of the same account on the same `transactionDay`.

3. **`isUnknownDevice`**
   The transaction device is unknown if there is no valid Device Activity record for the same `accountId` and `deviceId`.

4. **`isFutureDevice`**
   The device is a future device if it exists in Device Activity but the earliest `firstUsedDay` is greater than `transactionDay`.

5. **`merchantRiskTier`**
   The `riskTier` of the merchant used in the transaction.

6. **`locationRiskScore`**
   The risk score of the transaction location.

7. **`isOutsideHomeLocation`**
   True when the transaction location is different from the account's `homeLocation`.


## Fraud Risk Score

Calculate the fraud risk score for every valid *SUCCESS* transaction by summing the score additions for all matching conditions below:

| Condition | Score Added |
| :--- | :---: |
| If $amount > 2 \times accountAverageAmount$ | 4 |
| If $sameDayTransactionCount \ge 3$ | 3 |
| If `isUnknownDevice` is true | 4 |
| If `isFutureDevice` is true | 3 |
| If `merchantRiskTier` is *HIGH* | 3 |
| If `merchantRiskTier` is *MEDIUM* | 1 |
| If $locationRiskScore \ge 4$ | 3 |
| If `isOutsideHomeLocation` is true | 2 |


## Fraud Risk Level

| Risk Score | Risk Level |
| :--- | :--- |
| 10 or more | *HIGH* |
| 6 to 9 | *MEDIUM* |
| Less than 6 | *LOW* |

Only *HIGH* and *MEDIUM* risk transactions must be printed.

## Constraints

* $2 \le \text{numberOfAccounts} \le 100000$
* $0 \le \text{numberOfTransactions} \le 200000$
* $1 \le \text{numberOfMerchants} \le 100000$
* $0 \le \text{numberOfDeviceActivities} \le 200000$
* $1 \le \text{numberOfLocations} \le 100000$
* $1 \le \text{referenceDay} \le 1000000$
* $-1000000 \le \text{transactionDay}, \text{firstUsedDay} \le 1000000$
* $-1000000000 \le \text{amount} \le 1000000000$

All IDs, names, account types, locations, statuses, categories, and risk tiers contain no spaces.

---

> ### Note: Boilerplate code handled the input so we had to write only the function.

## Input Format

The first line contains the integer `referenceDay`.
The second line contains the number of records in the Accounts table.
The third line contains the number of records in the Transactions table.
The fourth line contains the number of records in the Merchant Profiles table.
The fifth line contains the number of records in the Device Activity table.
The sixth line contains the number of records in the Location Risk table.

The next records are provided in this space-separated line order:

1. **Accounts table:**
   `accountId accountHolder accountType homeLocation`
2. **Transactions table:**
   `transactionId accountId merchantId deviceId location transactionDay amount status`
3. **Merchant Profiles table:**
   `merchantId category riskTier`
4. **Device Activity table:**
   `activityId accountId deviceId firstUsedDay`
5. **Location Risk table:**
   `location riskScore`

## Output Format

Print all eligible transactions in the following format:
`TransactionId-AccountHolder-RiskLevel-RiskScore`

If multiple transactions must be displayed, separate them using a hash symbol (`#`).

Sort the output in the following sequence:
1. *HIGH* risk transactions before *MEDIUM* risk transactions.
2. Higher `riskScore` first.
3. Higher `amount` first.
4. Original input sequence of the Transactions table when all previous values are equal.

Apply the next sorting condition only when the previous condition results in a tie.

Print `NA` if no transaction has *HIGH* or *MEDIUM* risk.

---

## Examples

### Sample Input 1
```text
200
3
7
3
3
4
A1 Rahul SAVINGS BLR
A2 Priya CURRENT MUM
A3 Kiran SAVINGS DEL
T1 A1 M1 D1 BLR 100 1000 SUCCESS
T2 A1 M2 D9 DEL 110 7000 SUCCESS
T3 A1 M1 D1 BLR 120 1000 SUCCESS
T4 A2 M3 D2 MUM 130 2000 SUCCESS
T5 A2 M3 D8 HYD 130 2500 SUCCESS
T6 A2 M3 D2 MUM 130 3000 SUCCESS
T7 A3 M1 D3 DEL 140 500 FAILED
M1 GROCERY LOW
M2 ELECTRONICS HIGH
M3 TRAVEL MEDIUM
DLOG1 A1 D1 1
DLOG2 A2 D2 20
DLOG3 A3 D3 30
BLR 1
DEL 4
MUM 1
HYD 4
```

### Sample Output 1

```text
T2-Rahul-HIGH-16#T5-Priya-HIGH-13
```

#### Explanation 1

* For **T2**, the transaction uses an unknown device, a *HIGH*-risk merchant, a high-risk location, and a location outside Rahul's home location. The amount is also greater than twice Rahul's `accountAverageAmount`. Therefore, its risk score is 16.
* For Priya's transactions on day 130, `sameDayTransactionCount` is 3. **T5** uses an unknown device, has a high-risk location, is outside Priya's home location, has a *MEDIUM*-risk merchant, and is part of three successful transactions on the same day. Therefore, its risk score is 13, and it is classified as *HIGH*. **T4** and **T6** get only same-day velocity and *MEDIUM* merchant-risk points, so their score is 4. Hence, they are *LOW* risk and are not printed.
* **T2** is printed before **T5** because both transactions are *HIGH* risk, and **T2** has a higher fraud risk score (16) than **T5** (13).

---

### Sample Input 2

```text
100
3
9
3
3
3
A1 Anita SAVINGS BLR
A2 Bala SAVINGS DEL
A3 Charu CURRENT MUM
T1 A1 M1 D1 BLR 90 1000 SUCCESS
T2 A1 M2 D9 DEL 91 6000 SUCCESS
T3 A2 M3 D2 DEL 101 5000 SUCCESS
T4 A2 M3 D2 DEL 80 -100 SUCCESS
T5 A3 M9 D3 MUM 80 1000 SUCCESS
T6 X9 M1 D1 BLR 80 1000 SUCCESS
T7 A3 M3 D3 XXX 80 1000 SUCCESS
T8 A2 M3 D2 DEL 80 1000 PENDING
T9 A2 M3 D2 DEL 80 1000 FAILED
M1 GROCERY LOW
M2 JEWELLERY HIGH
M3 TRAVEL MEDIUM
D1 A1 D1 1
D2 A2 D2 1
D3 A3 D3 1
BLR 1
DEL 4
MUM 1
```

### Sample Output 2

```text
T2-Anita-HIGH-12
```

#### Explanation 2

* First, we filter out invalid records: **T4** is invalid because its amount is less than 0 ($-100$). **T6** is invalid because `accountId` `X9` does not exist in the Accounts table. **T7** is invalid because location `XXX` does not exist in the Location Risk table. **T8** and **T9** are not scored because their status is not *SUCCESS*.
* For Anita's transactions, there are two valid successful transactions (**T1** and **T2**). Anita's successful transactions total $1000 + 6000 = 7000$, making her `accountAverageAmount` equal to $7000 / 2 = 3500$.
* For **T2**, the transaction amount ($6000$) is not greater than $2 \times 3500$ ($7000$), so it adds 0 points here. However, **T2** uses an unknown device `D9` (+4 points), a *HIGH*-risk merchant `M2` (+3 points), occurs at a high-risk location `DEL` with a risk score of 4 $\ge 4$ (+3 points), and happens outside Anita's home location `BLR` (+2 points). This sums up to a fraud risk score of $4 + 3 + 3 + 2 = 12$, placing it in the *HIGH* risk level.
* For **T1**, the transaction uses a known device, a *LOW*-risk merchant, happens inside Anita's home location, and has a low location risk score (1), resulting in a score less than 6 (*LOW* risk).
* For **T3**, Bala's transaction is invalid because the transaction day (101) exceeds the `referenceDay` constraint threshold limit of 100.
* Therefore, **T2** is the only eligible transaction with a *HIGH* or *MEDIUM* risk level and is printed as the final output.
