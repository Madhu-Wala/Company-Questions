package DOLAT_CAPITAL_9July;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem_1 {
    static class Result{
        String binaryStr="";
        public void updateIfBetter(String newStr){
            if(binaryStr.isEmpty()){
                binaryStr=newStr;
                return;
            }

            String cleanCurrent=removeLeadingZeros(binaryStr);
            String cleanNew=removeLeadingZeros(newStr);

            if(cleanNew.length()>cleanCurrent.length()){
                binaryStr=newStr;
            }else if(cleanNew.length()<cleanCurrent.length()){
                //current is already the better one
            }else{
                int cmp=cleanNew.compareTo(cleanCurrent);
                if(cmp>0){
                    binaryStr=newStr;
                } else if (cmp==0) {
                    if(newStr.length()>binaryStr.length()){
                        binaryStr=newStr;
                    }
                }
            }
        }
        public String removeLeadingZeros(String s){
            int firstOne=s.indexOf('1');
            if(firstOne==-1) return "0";
            return s.substring(firstOne);
        }
    }
    public static String maxBinaryPath(int N,String S,int[] U,int[] V){
        List<List<Integer>> adj=new ArrayList<>();
        for (int i = 0; i <=N ; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < U.length; i++) {
            adj.get(U[i]).add(V[i]);
            adj.get(V[i]).add(U[i]);
        }

        Result bestResult=new Result();
        for(int i=1;i<=N;i++){
            boolean[] visited=new boolean[N+1];
            dfs(i,""+S.charAt(i-1),adj,S,visited,bestResult);
        }
        return bestResult.binaryStr;
    }
    public static void dfs(int node,String currentPathStr,List<List<Integer>> adj,String S,boolean[] visited,Result bestResult){
        visited[node]=true;
        bestResult.updateIfBetter(currentPathStr);

        for(int neighbor:adj.get(node)){
            if(!visited[neighbor]){
                dfs(neighbor,currentPathStr+S.charAt(neighbor-1),adj,S,visited,bestResult);
            }
        }
        //backtrack
        visited[node]=false;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        String S=sc.next();
        int[] U=new int[N-1];
        int[] V=new int[N-1];
        for (int i = 0; i < N-1; i++) {
            U[i]=sc.nextInt();
            V[i]=sc.nextInt();
        }

        String result=maxBinaryPath(N,S,U,V);
        System.out.println(result);
        sc.close();
    }
}
