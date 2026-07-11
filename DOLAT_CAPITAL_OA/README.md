### In this section, we had 3 questions to be solved within the stipulated time.

Total time: 01 hr 15 mins
- Problem 1
- Problem 2
- Problem 3

Both the questions have 2 public and 10 private test cases.

### ⚠️ Platform & IDE Rules
This Online Assessment (OA) was conducted on the **iMocha Platform**, and the IDE provided native support for **C++** and **Java** languages. 

* **Boilerplate Code:** Provided. The environment includes the `Main` class along with all necessary input/output handling logic. 
* **Implementation:** You only need to write the logic for the core solution inside the provided baseline method declaration.
* **IDE Features:** Auto code-completion is fully supported.
* **Constraints:** A maximum limit of **50 compilations** is allowed per question.

---

## 1. Tree: Maximum Binary Tree

There are N cities and (N-1) roads connecting them. The Cities and roads together form a tree like structure.
Each city has a binary value of either 0 or 1. 
Ben starts the tour from starting city and he can visit any other city that he has not visited before and if there exists a direct node from current city.
The tour ends and he reaches the ending city. Ben notes down the value of cities (either 0 or 1) he visits.
At the end of the tour he defines the value of tour as the decimal representation of binary string he noted down in the tour.

Find the binary representation of the value of which is maximum value of the tour that Ben can take.

**Note:** There is a possibility that the multiple tours have the same value, print the one that is longest.

### Input format:
* First line of the input contains a single integer N denoting no,of nodes in the tree.
* The second line of input contains a binary string s of length N containing 0 and 1, where s[i] denotes value of ith node.
* The next N-1 lines of the input contain 2 space seperated integers u & v denoting there is undirected edge between u and v.

### Output format:
* String containing binary representation of value which is maximum value of tour that Ben can take.

### Constraints:
* 1 <= N <= 100

### Example:
* **Input:**
  ```text
  3
  100
  1 2
  1 3
  ```

* **Output:**
```text
010

```
* **Sample Tree Respresentation**

<img width="347" height="241" alt="image" src="https://github.com/user-attachments/assets/338e1dd2-0b39-47ab-97bd-a6f57b66a256" />


**Solution:** *To be uploaded.*

---

## 2. Encapsulation: Summation of series

You are given a class Summation. You will be provided with a positive integer N.
Print summation of all numbers up to N (inclusive).

**Note:** Even though the test cases are passed, the evaluation is based on the implementation of encapsulation.

### Example:

* **Input:**
```text
4

```

* **Output:**
```text
10

```

*(Explanation: 1+2+3+4)*

**Solution:** *To be uploaded.*

---

## 3. Stack: Balanced Brackets

A sequence of brackets is said to the balanced if

1. It starts with an opening bracket.
2. It ends with a closing bracket.
3. In any instance the number of closing brackets does not exceed the number of opening brackets.
4. The number of opening and closing brackets is equal.

Count the minimum number of "(" or ")" that must be inserted to make the String s balanced again.

### Input format:

* First line contains string s

### Output format:

* Output contains an integer, denoting the minimum number of "(" or ")" that must be inserted tp make the string balanced again.

### Example:

* **Sample Input:**
```text
()))(

```

* **Sample Output:**
```text
2
```

**Solution:** *To be uploaded.*
