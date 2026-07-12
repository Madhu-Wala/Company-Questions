# Problem 1

## Wildlife Photography Optimization *(Greedy Algorithm)*

### Problem Description
Bob is a wildlife photographer who is going on an $N$-hour trekking trip. He decides to use one of his old favorite cameras for the trip. The camera's battery can last for $X$ hours, but it switches off automatically one hour after being turned on, and it cannot be turned off manually in between. 

The camera has a limited storage capacity $L$, meaning Bob can click only $(L - 1)$ number of pictures total during the trip. He gets information from the tour guide about the number of different animals he will be able to see in every hour of the trip. 

Write a program to help him maximize the number of animals $P$ he will be able to click during the entire trip.

---

### Input Format
* **First Line:** Contains three space-separated integers:
  * $N$: Total number of hours of the trekking trip.
  * $X$: The camera's battery life in hours.
  * $L$: The camera's storage capacity boundary.
* **Second Line:** Contains $N$ space-separated integers denoting the array $C$, where $C[i]$ represents the number of animals that can be seen during the $i$-th hour of the trip.

### Output Format
* Print a single line containing an integer $P$, which represents the maximum number of animals Bob will be able to photograph during the trip.


### Constraints
* $1 \le N \le 50$
* $X \ge 1$
* $L > 0$
* $C[i] \ge 0$


### Examples

#### Sample Input 1
```text
5 3 45
10 42 1 8 2
```

#### Sample Output 1

```text
44

```

### Explanation 1

* **Trip Duration ($N$):** 5 hours
* **Battery ($X$):** 3 hours (Bob can choose at most 3 hours to turn on his camera)
* **Max Storage Limit ($L - 1$):** $45 - 1 = 44$ pictures max.
* **Animals per hour ($C$):** `[10, 42, 1, 8, 2]`

To maximize the photo count, Bob greedily chooses the 3 hours with the most animals:

1. Hour with **42** animals
2. Hour with **10** animals
3. Hour with **8** animals

Total potential animals seen = $42 + 10 + 8 = 60$.

However, since his storage is strictly capped at $L - 1 = 44$, the maximum pictures he can physically store is **44**.

---

Solution: [Problem_1.java](./Problem_1.java)

### Solution Strategy (Greedy Approach)

1. **Sort the Array:** Sort the animal counts in ascending order to easily access the hours with the highest density of wildlife.
2. **Cap the Selection Limits:** The maximum number of hours Bob can realistically harvest is bounded by three constraints: the trip length ($N$), the battery constraint ($X$), and the total picture limit ($L-1$).
3. **Handle Overflow:** Use `long` integers during accumulation to prevent value wrapped overflows if array inputs scale significantly in hidden test suites.
