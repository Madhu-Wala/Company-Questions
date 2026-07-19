import java.util.*;

public class CodingQuestion2{
  public static void solution(int N, int[] arr) {
        long totalNum = 0;
        
        for (int i = 0; i < N; i++) {
            int P = arr[i];
            
            // Z is the last digit
            int Z = P % 10;
            int remaining = P / 10;
            
            // Extract Y (power) and X (base) based on Z
            int divisor = (int) Math.pow(10, Z);
            int Y = remaining % divisor;
            int X = remaining / divisor;
            
            // Add X^Y to the total sum
            totalNum += (long) Math.pow(X, Y);
        }
        
        // Print the final result
        System.out.println(totalNum);
    }
  public static void main(String [] args){
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int[] arr=new int[n];
    for(int i=0;i<n;i++){
      arr[i]=sc.nextInt();
    }
    solution(n,arr);
  }
}
