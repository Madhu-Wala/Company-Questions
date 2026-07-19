import java.util.*;
public class CodingQuestion1{

  static void arrangeCookies(int N, int arr[], int W) {
        // Sort the weights in ascending order
        Arrays.sort(arr);
        
        // Map to store the box number for each cookie weight
        Map<Integer, Integer> cookieBoxMap = new HashMap<>();
        
        // Start with the innermost box (Box 1) containing the smallest cookie
        String currentStr = "[" + arr[0] + "]";
        cookieBoxMap.put(arr[0], 1);
        
        int boxIdx = 2;
        int i = 1;
        
        // Build the nested structure from inside out
        while (i < N) {
            int leftCookie = arr[i];
            cookieBoxMap.put(leftCookie, boxIdx);
            
            if (i + 1 < N) {
                int rightCookie = arr[i + 1];
                cookieBoxMap.put(rightCookie, boxIdx);
                currentStr = "[" + leftCookie + currentStr + rightCookie + "]";
                i += 2;
            } else {
                // If N is even, the outermost layer will only have a left cookie
                currentStr = "[" + leftCookie + currentStr + "]";
                i += 1;
            }
            boxIdx++;
        }
        
        // Print the final arrangement string and the target box number
        System.out.println(currentStr);
        System.out.println(cookieBoxMap.getOrDefault(W, -1));
    }

  public static void main(String args{}){
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int mainarray[]=new int[n];
    for(int i=0;i<n;i++){
      mainarray[i]=sc.nextInt();
    }
    int test=sc.nextInt();
    arrangeCookies(n,mainarray,test);
  }
}
