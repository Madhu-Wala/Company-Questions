package TCS_PriorityNQT9June;
/*
* Given a binary tree, print the minium height of the tree. In other words print the height of the shortest path to leaf or height of the nearest leaf from root.
* Input:
* No.of nodes n (int)
* Next n lines contain (nodeVal parentVal)
*
* Output:
* min height of tree (int)*/

import java.util.*;
public class CodingMedium {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
            this.left=null;
            this.right=null;
        }
    }
    public static int minHeight(TreeNode root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return 1;
        }
        if(root.left==null){
            return 1+minHeight(root.right);
        }
        if(root.right==null){
            return 1+minHeight(root.left);
        }
        return 1 + Math.min(minHeight(root.left),minHeight(root.right));
    }
    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        //hashmap to store <val,TreeNode> tuple
        HashMap<Integer, TreeNode> map=new HashMap<>();
        int n= sc.nextInt();
        TreeNode root=null;
        for(int i=0;i<n;i++){
            int val=sc.nextInt();
            int valp=sc.nextInt();
            map.putIfAbsent(val,new TreeNode(val) );
            TreeNode curr=map.get(val);
            if(valp==-1){
                root=curr;
            }else{
                map.putIfAbsent(valp, new TreeNode(valp));
                TreeNode parent=map.get(valp);
                 if(parent.left==null){
                    parent.left=curr;
                }else{
                    parent.right=curr;
                }
            }
        }
        System.out.println(minHeight(root));
    }
}
