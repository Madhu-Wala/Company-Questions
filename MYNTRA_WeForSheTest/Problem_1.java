package MYNTRA_WeForSheTest;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_1 {
    static int printClicks(int N,int X,int L,int[] C){
        Arrays.sort(C);
        long totalAnimals=0;
        long maxStorage=L-1;

        int hoursToClick=(int) Math.min(N,Math.min((long)X,maxStorage));

        for (int i = 0; i < hoursToClick; i++) {
            totalAnimals+=C[N-i-1];
        }

        return (int) Math.min(totalAnimals,maxStorage);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int X=sc.nextInt();
        int L=sc.nextInt();
        int[] C=new int[N];
        for(int i=0;i<N;i++){
            C[i]=sc.nextInt();
        }
        System.out.println(printClicks(N,X,L,C));
    }
}
