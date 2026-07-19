# Cookie Box Packing Strategy - Coding Challenge

## Problem Description

A bakery shop sells gift boxes that consist of distinct weighted handmade cookies. To increase their sales, they use a different packing strategy.

Write a program to find the box number of a cookie whose weight is $W_x$, when the number of cookies $N$ and the weight of each cookie $W_i$ is provided.

### Packing Strategy:
1. The box comprises of three portions: a smaller box in the center, a cookie on the left, and a cookie on the right. The weight of cookies present in the box in the center is less than the weight of both the left and right cookie. The cookie on the right side of the box is the heaviest as shown below.

   `[C6 [C4 [C2 [C1] C3] C5] C7]` 
   Where: $C1 < C2 < C3 < C4 < C5 < C6 < C7$

2. The innermost cookie box will be the first box that will be nested inside the second box, which in turn will be nested inside the third box and so on. The first box contains only one cookie.
3. A box is denoted using the square brackets `[]`.

Read the input from STDIN and print the output to STDOUT. Do not print arbitrary strings anywhere in the program, as these contribute to the output and test cases will fail.

---

## Constraints

*   $N \le 25$
*   $0 < W_i < 100$

---

## Input Format

*   The first line of input contains $N$, the number of cookies to be packed together.
*   The second line of input consists of the weight $W_i$ of $N$ cookies with a single white space between them.
*   The third line of input contains the cookie weight $W_x$ for which the box number has to be found out.

---

## Output Format

*   The first line of output contains the display of the cookie box arrangement without spaces.

    [C6 [C4 [C2 [C1] C3] C5] C7] where $C1 < C2 < C3 < C4 < C5 < C6 < C7$
*   The second line of output is the box number of the given cookie weight.

---

## Examples

### Sample Input 1
```text
3
45 12 32
32
```

### Sample Output 1

```text
[32[12]45]
2
```

#### Explanation 1

* Weights given: `45 12 32`

<img width="602" height="192" alt="image" src="https://github.com/user-attachments/assets/67f418df-f5c6-4daf-ab88-45a41eb258b2" />

* We can see that `32` is in the 2nd Box, hence `2` is the output.

---

### Sample Input 2

```text
5
76 62 99 21 59
99
```

### Sample Output 2

```text
[76[59[21]62]99]
3
```

#### Explanation 2

* Weights given: `76 62 99 21 59`

<img width="595" height="202" alt="image" src="https://github.com/user-attachments/assets/5ff17bcf-8ca3-462a-af4e-f3b789c6ee51" />


* We can see that `99` is in the 3rd Box, hence `3` is the output.

