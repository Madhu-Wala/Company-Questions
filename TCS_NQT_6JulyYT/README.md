### In this section, we had 2 questions to be solved within the stipulated time.

Advanced Coding Easy (35 mins)  
Advanced Coding Medium (55 mins)  

Both the questions have 2 public and 5 private test cases, and the evaluation will be done on the basis of the number of test cases passed.

### ⚠️ Important
No boilerplate code is provided; you have to write the entire code from scratch, and auto code-completion is not provided.

---

## 1. Advanced Coding Easy

There are two items, `A` and `B`, with their respective prices. Write a program to determine which item is more expensive. If either price is less than zero, print `invalid input`. If both prices are equal, print `equal`. Otherwise, print the higher price followed by `is more expensive`.

### Input Format:
* Two integers separated by spaces representing the prices of item `A` and item `B`.

### Output Format:
* Print `invalid input`, `equal`, or `[Price] is more expensive` based on the given conditions.

### Examples:
* **Example 1:**
  * **Input:** `10 15`
  * **Output:** `15 is more expensive`
* **Example 2:**
  * **Input:** `10 10`
  * **Output:** `Prices equal`
* **Example 3:**
  * **Input:** `15 -20`
  * **Output:** `Invalid input`

**Solution:** [CodingEasy.java](./CodingEasy.java)

---

## 2. Advanced Coding Medium

Create a class that implements a **Stack** with the following methods: 
- `push(value:String)`
- `pop`
- `evaluate`
The stack should be **encapsulated**, meaning the stack data should be `private` and can only be accessed through these methods.

Use this class to evaluate a **Postfix Expression**. 

It is guaranteed that the input expression is **always valid**. 

### Input Format:
* The first line contains an integer representing the total number of elements (tokens) in the postfix expression.
* The next line contains space-separated tokens (integers and operators) representing the postfix expression.
* Supported operators are `+`, `-`, `*`, and `/`.

### Output Format:
* Print the integer result after evaluating the complete postfix expression.

### Examples:
* **Example 1:**
  * **Input:**
    ```text
    5
    13 5 + 4 -
    ```
  * **Output:** `14` *(13 + 5 = 18, then 18 - 4 = 14)*
* **Example 2:**
  * **Input:**
    ```text
    7
    5 6 2 + * 4 -
    ```
  * **Output:** `36` *(6 + 2 = 8, then 5 * 8 = 40, then 40 - 4 = 36)*

**Solution:** [CodingMedium.java](./CodingMedium.java)
