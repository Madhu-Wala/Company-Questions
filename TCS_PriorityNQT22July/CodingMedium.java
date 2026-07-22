package TCS_PriorityNQT22July;

import java.util.Scanner;

public class CodingMedium {
    public static int[] selectionSortFromTo(int[] arr,int start,int end){
        for (int i = start; i <=end ; i++) {
            int minIndex=i;
            for (int j = i+1; j <arr.length ; j++) {
                if(arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }
            int temp=arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
        return arr;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        String sn=sc.next();
        String[] snarr=sn.split(",");
        int n=Integer.parseInt(snarr[0]);

        String sp=sc.next();
        String[] sparr=sp.split(",");
        int p=Integer.parseInt(sparr[0]);

        String sq=sc.next();
        String[] sqarr=sq.split(",");
        int q=Integer.parseInt(sqarr[0]);
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }
        arr=selectionSortFromTo(arr,p+1,q);
        for(int a:arr){
            System.out.print(a+" ");
        }
    }
}
