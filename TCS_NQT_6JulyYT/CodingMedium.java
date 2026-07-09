package TCS_NQT_6JulyYT;

import java.util.ArrayList;
import java.util.Scanner;

class Stack{
    private ArrayList<Integer> st=new ArrayList<>();
    public void push(String n){
        st.add(Integer.parseInt(n));
    }
    public void push(int n){
        st.add(n);
    }
    public int pop(){
        if(st.isEmpty())return 0;
        return st.remove(st.size()-1);
    }
    public int peek(){
        if(st.isEmpty())return 0;
        return st.get(st.size()-1);
    }
}
public class CodingMedium {
    public static int evaluatePostfix(String[] tokens){
        Stack st=new Stack();
        for(String token:tokens){
            if(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/")){
                int val2=st.pop();
                int val1=st.pop();
                int ans=0;
                switch (token){
                    case "+":ans=val1+val2;
                    break;
                    case "-":ans=val1-val2;
                        break;
                    case "*":ans=val1*val2;
                        break;
                    case "/":ans=val1/val2;
                        break;
                }
                st.push(ans);
            }else{
                st.push(token);
            }
        }
        return st.pop();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String[] tokens=new String[n];
        for (int i = 0; i < n; i++) {
            tokens[i]=sc.next();
        }
        System.out.println(evaluatePostfix(tokens));
    }
}
