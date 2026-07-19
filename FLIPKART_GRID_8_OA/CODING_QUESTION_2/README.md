# Math Equation Solver - Coding Challenge

## Problem Description

Mr. Thomas is a very well-known mathematician. He went to an education fair where he noticed a lot of brilliant students. So, by seeing their talent, he has given them a problem to solve.

The problem was to compute the value of an expression in the form:
$$num = P_1 + P_2 + \dots + P_N$$

Where each operand $P_i$ is an integer provided in a structural format represented as **XYZ**:
*   **Z** represents the number of digits at the end of the number that indicate how many positions to move left to read the exponent. The final $Z$ digits themselves are ignored during calculation.
*   **Y** is the integer parsed using the $Z$ digits right before it, which is considered the **power**.
*   **X** consists of the remaining leftmost digits, which are considered the **base**.

For example, given an equation in the form of $num = 3011 + 1122$:
*   For `3011`: $Z = 1$ (ignore last $1$ digit). Moving $1$ position left gives power $Y = 1$. The remaining part is base $X = 30$. Thus, $30^1 = 30$.
*   For `1122`: $Z = 2$ (ignore last $2$ digits). Moving $2$ positions left gives power $Y = 12$. The remaining part is base $X = 1$. Thus, $1^{12} = 1$.
*   The final calculation becomes: $num = 30^1 + 1^{12} = 30 + 1 = 31$.

Can you help students with a program to solve the given problem?

---

## Input Format

*   The first line of input contains the integer $N$, the total number of operands in the equation. Each of the following N lines contains the integer $P_i$ from the equation.

## Output Format
* The first and the only line of output must contain the value of $num$ from the given equation.

## Constraints

1. $N(1 \le  N \le 100)$
2. $P_i (10 \le P_i \le 9999, i=1 ... N)$
3. $num (num \le 10^9)$

## Sample Input 1:
```text
1
3001
```

## Sample Output 1:

```text
30
```

## Explanation 1:

The number of operands in the equation N: 1.

$P_1$ = 3011

<img width="290" height="250" alt="image" src="https://github.com/user-attachments/assets/045b4840-9e86-4732-a858-79895090f05a" />

Here, following the rule of the problem in the form of XYZ, the equation will be formed as $num = 30^1 1$, where 1 is considered as Z, so it will be ignored, and from right to left one digit will be considered as the power i.e, Y and the rest number i.e., 30 will be the base.

So, $num=X^Y = 30^1 = 30$ as an output it will print.

---
## Sample Input 2:
```text
2
4122
2021
```

## Sample Output 2:
```text
16777616
```

## Explanation 2:

The number of operands in the equation N: 1.

$P_1$ and $P_2$ : 4122 and 2021

<img width="682" height="277" alt="image" src="https://github.com/user-attachments/assets/50849dbb-d026-410b-8e73-7a6016ea9270" />

Here, following the rule of the problem in the form of XYZ: 

$num=X^Y = 4^{12} + 20^2 = 16777216 + 400 = 16777616$

So it will print 16777616 as an output.
