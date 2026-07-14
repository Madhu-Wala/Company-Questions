# Coding Round: Pen & Paper Technical Assessment

This directory contains the problem descriptions and solution approaches for the offline, pen-and-paper technical round. 

### 📝 Assessment Overview
* **Duration:** 1.5 Hours (90 Minutes)
* **Format:** Offline pen-and-paper coding round.
* **Language Requirements:** Core solutions are expected in **C++** (highest priority). However, writing pseudocode in **Java** or **Python** was acceptable to outline logic when relying on specific built-in library functions.

---

## 1. Rotate Array Right by K Places

### Problem Statement
Given an integer array `arr` of size `N` and an integer `k`, rotate the array to the right by `k` positions and return the rotated array.

### Example
* **Input:**
```text
  arr = [1, 2, 3, 4, 5]
  k = 2
```

* **Output:**
```text
[4, 5, 1, 2, 3]
```

### Solution Approach

To rotate the array to the right by `k` positions:

1. Handle edge cases where the array is empty (`n == 0`).
2. Minimize redundant rotations by taking $k = k \pmod n$, where $n$ is the size of the array.
3. Use an auxiliary array `temp` of size `n` to store the rotated elements.
4. For each element at index `i`, place it at its new position `(i + k) % n` in the `temp` array.
5. Return the resulting rotated array.

### Complexity Analysis

* **Time Complexity:** $\mathcal{O}(n)$ because we iterate through the array of size $n$ exactly once.
* **Space Complexity:** $\mathcal{O}(n)$ to store the rotated array in a temporary buffer.

**Solution Code:** [Problem1.cpp](./Problem1.cpp)

---

## 2. Order Book Design

### Problem Statement

Design an Order Book supporting real-time order tracking and modification. Each order contains:

* **Order ID**
* **Price**
* **Quantity**

The order book must maintain and dynamically update the cumulative quantity at every price level.

#### Example

If the current orders are:

| Order ID | Price | Quantity |
| --- | --- | --- |
| 10001 | 100 | 10 |
| 10002 | 101 | 5 |
| 10003 | 100 | 7 |

The cumulative price table should group the quantities by price level:

| Price | Total Quantity |
| --- | --- |
| 101 | 5 |
| 100 | 17 |

Whenever `printOrderBook()` is called, print the cumulative values sorted in descending order of price:

```text
101 5
100 17
```

### Required Methods

The custom class must support the following interface:

```cpp
class OrderBook
{
public:
    void processNewOrder(int orderId, int price, int quantity);
    void processModifyOrder(int orderId, int price, int quantity);
    void processCancelOrder(int orderId);
    void processTrade(int orderId, int tradedQuantity);
    void printOrderBook();
};

```

### Solution Approach

Using a linear container like a vector to find orders would result in an inefficient $\mathcal{O}(n)$ scan for every update. To optimize lookup and modification operations, we employ two complementary maps:

1. **`unordered_map<int, Order> orders`**: Maps each `orderId` to its corresponding `Order` object. This provides $\mathcal{O}(1)$ average-time lookups to instantly access, modify, or cancel orders using their IDs.
2. **`map<int, int, greater<int>> book`**: Maps `price` to its current `cumulative quantity`. By configuring this map with `greater<int>`, the keys (prices) are automatically kept in descending order. This allows us to print the order book instantly without manually sorting.

When modifying, canceling, or trading orders:

* We retrieve the order details using the ID map.
* We subtract the previous quantity from the price tracker map.
* If a price level's cumulative quantity reaches `0`, we remove that key from the price map to save space.
* We apply the new changes and update the price map with the new quantities.

### Complexity Analysis

Where $P$ is the number of unique price levels in the order book:

| Operation | Time Complexity | Description |
| --- | --- | --- |
| **New Order** | $\mathcal{O}(\log P)$ | Insertion into the ordered price map. |
| **Modify Order** | $\mathcal{O}(\log P)$ | Updating values in the ordered price map. |
| **Cancel Order** | $\mathcal{O}(\log P)$ | Removing or modifying keys in the ordered price map. |
| **Trade** | $\mathcal{O}(\log P)$ | Decrementing quantities and clean-up of elements. |
| **Print** | $\mathcal{O}(P)$ | Traversing the pre-sorted price map. |

**Solution Code:** [Problem2.cpp](./Problem2.cpp)
