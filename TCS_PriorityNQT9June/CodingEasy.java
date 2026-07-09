package TCS_PriorityNQT9June;
/*
* Given a sorted array and target element, print the index of first occurrence of the target element. If element not in array, then return -1.
* Input:
* size of array (int)
* space separated sorted array elements (int)
* target element (int)
*
* Output:
* Print the index of first occurrence of target element.
* */
import java.util.*;
public class CodingEasy {
    public static int firstOcc(int[] arr,int target){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==target){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int target=sc.nextInt();
        int firstocc=firstOcc(arr,target);
        System.out.println(firstocc);
    }
}
