package DOLAT_CAPITAL_9July;
import java.util.*;

public class Problem_3 {
    public static int minInsertions(String s){
 //       Deque<Character> st=new ArrayDeque<>();  //Deque or stack class, any one can be used.
        Stack<Character> st=new Stack<>();
        int count=0;
        for(char c:s.toCharArray()){
            if(c=='('){
                st.push(c);
            }else if(c==')'){
                if(st.isEmpty()){
                    count++;
                }else{
                    st.pop();
                }
            }
        }
        count=count+st.size();
        return count;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String S=sc.next();
        System.out.println(minInsertions(S));
    }
}
