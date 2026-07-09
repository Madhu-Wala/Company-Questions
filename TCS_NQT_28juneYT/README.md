### In this section, we had 2 questions to be solved within the stipulated time.

Advanced Coding Easy (35 mins)  
Advanced Coding Medium (55 mins)  

Both the questions have 2 public and 5 private test cases, and the evaluation will be done on the basis of the number of test cases passed.

### ⚠️ Important
No boilerplate code is provided; you have to write the entire code from scratch, and auto code-completion is not provided.

---

## 1. Advanced Coding Easy

A number `n` is given. Generate a series where the first two terms are 5 and 6, and every subsequent term is the sum of the previous two terms. Print the series up to `n` terms.

### Input Format:
* An integer `n` representing the number of terms.

### Output Format:
* Print the series up to `n` terms separated by spaces.

### Example:
* **Input:** `5`
* **Output:** `5 6 11 17 28`

**Solution:** [CodingEasy.java](./CodingEasy.java)

---

## 2. Advanced Coding Medium

There are `N` passengers who need to travel. 
- A car can carry 4 passengers and produces `x` units of pollution.
- A van can carry 100 passengers and produces `y` units of pollution.
You may use any number of cars and vans. Your task is to transport at least `N` passengers while producing the minimum total pollution.

### Input Format:
* The first line contains the number of test cases `T`.
* Each test case contains three integers on a new line: `N` (number of passengers), `x` (car pollution units), and `y` (van pollution units).

### Output Format:
* Print the minimum total pollution generated for each test case.

### Examples:

**Input:** 

```text
3
120 3 50  
400 20 40
205 5 15
```

**Output:** 

```text
65  *(Using 1 van for 100 passengers and 5 cars for the remaining 20 passengers: 50 + 5*3 = 65)*
160
40
```

**Solution:** [CodingMedium.java](./CodingMedium.java)
