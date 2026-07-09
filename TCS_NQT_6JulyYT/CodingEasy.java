package TCS_NQT_6JulyYT;

import java.util.Scanner;

public class CodingEasy {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        if(a<0 || b<0){
            System.out.println("Invalid Input");
        }else if(a==b){
            System.out.println("Prices equal");
        } else {
            System.out.println((a>b?a:b)+" is more expensive");
        }
    }
}
