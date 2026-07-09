package TCS_NQT_28juneYT;

import java.util.Scanner;

public class CodingEasy {
    public static void newFibonacci(int n){
        if(n<=0){
            return;
        }
        long first=5;
        long second=6;
        if(n>=1){
            System.out.print(first+" ");
        }
        if(n>=2){
            System.out.print(second+" ");
        }
        for (int i = 3; i <=n ; i++) {
            long next=first+second;
            System.out.print(next+" ");
            first=second;
            second=next;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        newFibonacci(n);
    }
}
