# Telecom Service System - SQL Coding Challenge

## Problem Description

A telecom service system maintains comprehensive information on mobile users, service packages, mobile connections, monthly service bills, bill transactions, daily service consumption, and network zones.

*   The `mobile_users` table stores user details such as user name, email, registered city, and signup time.
*   The `service_packages` table stores package details such as monthly charge, free data limit, free call minutes, free SMS count, and extra usage rates.
*   The `mobile_connections` table stores the connection details of each user and links every connection to a subscribed service package.
*   The `daily_service_consumption` table records the daily internet usage, call minutes, and SMS usage for each mobile connection. Each usage record is linked to the `network_zones` table, which stores details about the network zone, city, network generation, and signal location.
*   For every billing cycle, the `monthly_service_bills` table stores the bill details generated for each mobile connection, including package amount, additional usage amount, tax value, payable amount, due date, and bill state. 
*   The `bill_transactions` table stores payment details for each bill, including transaction date, transaction mode, transaction amount, and transaction state.

---

## Task

Write an SQL query to display the `user_ref` and the total internet usage in MB for all users who have consumed the highest total internet data across all their mobile connections. If more than one `user_ref` has the same highest total internet usage, display those `user_ref` values in ascending order.

---

## Schema Information

### 1. `mobile_users`
*   `user_ref` INT (PK)
*   `user_name` VARCHAR(120)
*   `user_email` VARCHAR(120)
*   `registered_city` VARCHAR(60)
*   `signup_time` DATETIME

### 2. `mobile_connections`
*   `connection_ref` INT (PK)
*   `user_ref` INT (FK)
*   `package_ref` INT (FK)
*   `activated_on` DATE
*   `deactivated_on` DATE
*   `connection_state` ENUM('ACTIVE', 'BLOCKED', 'CLOSED')

### 3. `service_packages`
*   `package_ref` INT (PK)
*   `package_label` VARCHAR(80)
*   `fixed_monthly_charge` DECIMAL(10,2)
*   `free_data_gb` DECIMAL(6,2)
*   `free_call_minutes` INT
*   `free_sms_count` INT
*   `extra_data_rate` DECIMAL(10,2)
*   `extra_call_rate` DECIMAL(10,2)
*   `extra_sms_rate` DECIMAL(10,2)
*   `package_enabled` TINYINT(1)

### 4. `daily_service_consumption`
*   `consumption_ref` BIGINT (PK)
*   `connection_ref` INT (FK)
*   `consumption_date` DATE
*   `zone_ref` INT (FK)
*   `internet_mb` INT
*   `call_minutes` INT
*   `sms_used` INT

### 5. `monthly_service_bills`
*   `bill_ref` INT (PK)
*   `connection_ref` INT (FK)
*   `cycle_month` DATE
*   `bill_date` DATE
*   `due_date` DATE
*   `package_amount` DECIMAL(10,2)
*   `additional_usage_amount` DECIMAL(10,2)
*   `tax_value` DECIMAL(10,2)
*   `payable_amount` DECIMAL(10,2)
*   `bill_state` ENUM('OPEN', 'PAID', 'OVERDUE', 'CANCELLED')

### 6. `bill_transactions`
*   `transaction_ref` INT (PK)
*   `bill_ref` INT (FK)
*   `transaction_date` DATE
*   `transaction_mode` ENUM('UPI', 'CARD', 'NETBANKING', 'WALLET', 'CASH')
*   `transaction_amount` DECIMAL(10,2)
*   `transaction_state` ENUM('SUCCESS', 'FAILED', 'REFUNDED')

### 7. `network_zones`
*   `zone_ref` INT (PK)
*   `zone_code` VARCHAR(30)
*   `zone_city` VARCHAR(60)
*   `network_generation` ENUM('2G', '3G', '4G', '5G')
*   `signal_latitude` DECIMAL(9,6)
*   `signal_longitude` DECIMAL(9,6)

---

## Sample Input

### `mobile_users`

| user_ref | user_name | user_email | registered_city | signup_time |
| :--- | :--- | :--- | :--- | :--- |
| 1 | Arun Kumar | arun@email.com | Bengaluru | 2024-01-01 10:00:00 |
| 2 | Meera Shah | meera@email.com | Mumbai | 2024-01-02 11:00:00 |
| 3 | Ravi Menon | ravi@email.com | Chennai | 2024-01-03 12:00:00 |
| 4 | Nisha Rao | nisha@email.com | Hyderabad | 2024-01-04 13:00:00 |

### `service_packages`

| package_ref | package_label | fixed_monthly_charge | free_data_gb | free_call_minutes | free_sms_count | extra_data_rate | extra_call_rate | extra_sms_rate | package_enabled |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 201 | Basic 2GB | 299.00 | 2.00 | 100 | 50 | 50.00 | 1.00 | 0.50 | 1 |
| 202 | Silver 5GB | 499.00 | 5.00 | 300 | 100 | 40.00 | 0.80 | 0.40 | 1 |
| 203 | Gold 10GB | 799.00 | 10.00 | 500 | 200 | 30.00 | 0.60 | 0.30 | 1 |
| 204 | Platinum 20GB | 999.00 | 20.00 | 1000 | 500 | 20.00 | 0.50 | 0.20 | 1 |

### `mobile_connections`

| connection_ref | user_ref | package_ref | activated_on | deactivated_on | connection_state |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 101 | 1 | 201 | 2024-01-01 | NULL | ACTIVE |
| 102 | 2 | 202 | 2024-01-02 | NULL | ACTIVE |
| 103 | 3 | 203 | 2024-01-03 | NULL | ACTIVE |
| 104 | 4 | 204 | 2024-01-04 | NULL | ACTIVE |

### `daily_service_consumption`

| consumption_ref | connection_ref | consumption_date | zone_ref | internet_mb | call_minutes | sms_used |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 101 | 2024-02-01 | 301 | 500 | 20 | 5 |
| 2 | 102 | 2024-02-01 | 302 | 700 | 15 | 3 |
| 3 | 103 | 2024-02-01 | 303 | 700 | 25 | 4 |
| 4 | 104 | 2024-02-01 | 304 | 300 | 10 | 2 |

### `monthly_service_bills`

| bill_ref | connection_ref | cycle_month | bill_date | due_date | package_amount | additional_usage_amount | tax_value | payable_amount | bill_state |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 401 | 101 | 2024-02-01 | 2024-02-05 | 2024-02-20 | 299.00 | 0.00 | 53.82 | 352.82 | PAID |
| 402 | 102 | 2024-02-01 | 2024-02-05 | 2024-02-20 | 499.00 | 0.00 | 89.82 | 588.82 | PAID |
| 403 | 103 | 2024-02-01 | 2024-02-05 | 2024-02-20 | 799.00 | 0.00 | 143.82 | 942.82 | PAID |
| 404 | 104 | 2024-02-01 | 2024-02-05 | 2024-02-20 | 999.00 | 0.00 | 179.82 | 1178.82 | PAID |

### `bill_transactions`

| transaction_ref | bill_ref | transaction_date | transaction_mode | transaction_amount | transaction_state |
| --- | --- | --- | --- | --- | --- |
| 501 | 401 | 2024-02-10 | UPI | 352.82 | SUCCESS |
| 502 | 402 | 2024-02-11 | CARD | 588.82 | SUCCESS |
| 503 | 403 | 2024-02-12 | WALLET | 942.82 | SUCCESS |
| 504 | 404 | 2024-02-13 | CASH | 1178.82 | SUCCESS |

### `network_zones`

| zone_ref | zone_code | zone_city | network_generation | signal_latitude | signal_longitude |
| --- | --- | --- | --- | --- | --- |
| 301 | BLR-4G-01 | Bengaluru | 4G | 12.971600 | 77.594600 |
| 302 | MUM-5G-01 | Mumbai | 5G | 19.076000 | 72.877700 |
| 303 | CHN-4G-01 | Chennai | 4G | 13.082700 | 80.270700 |
| 304 | HYD-5G-01 | Hyderabad | 5G | 17.385000 | 78.486700 |

---

## Sample Output

```text
2,700
3,700
```

## Explanation

User 2 and user 3 each consumed 700 MB, which is the highest total internet usage in the sample data. Since multiple users have the same highest total, they are displayed in ascending order of `user_ref`.
