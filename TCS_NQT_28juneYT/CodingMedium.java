package TCS_NQT_28juneYT;

import java.util.Scanner;

public class CodingMedium {
    public static long minimumCarbon(int n,int x,int y){
        long maxVans=(n+99)/100;
        long minPollution=Long.MAX_VALUE;
        for(long v=0;v<=maxVans;v++){//v=>no.of vans
            long coveredByVans=v*100;
            long remaining=n-coveredByVans;
            if(remaining<0){
                remaining=0;
            }
            long cars=(remaining+3)/4;
            long currentPollution=(v*y)+(cars*x);
            minPollution=Math.min(currentPollution, minPollution);
        }
        return minPollution;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t>0){
            int n=sc.nextInt();
            int x=sc.nextInt();
            int y= sc.nextInt();
            System.out.println(minimumCarbon(n,x,y));
            t--;
        }
    }
}
