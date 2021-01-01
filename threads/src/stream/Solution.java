package stream;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        if(null == root){
            return new ArrayList<Integer>();
        }

        Map<Integer, Integer> indexs = new HashMap<Integer,Integer>();
        List<List<Integer>> list = new ArrayList<>();
        convertToGrapgh(root, indexs, list);

        Set<Integer> set = new HashSet<Integer>();
        set.add(target.val);

        List<Integer> temp = list.get(indexs.get(target.val));


        for(int i = 1; i < K; i++){
//            List<Integer> finalQue = que;
            List<Integer> que = new ArrayList<>();
            temp.stream().filter(integer -> !set.contains(integer))
                    .peek(integer -> {
                        list.get(indexs.get(integer)).stream().filter(integer1 -> !set.contains(integer1)).forEach(integer1 -> que.add(integer1));
                    }).forEach(integer -> set.add(integer));

            temp = que;

        }

        return temp;

    }

    public void convertToGrapgh(TreeNode root, Map<Integer,Integer> indexs, List<List<Integer>> list){

        List<TreeNode> que = new ArrayList<>();
        List<Integer> temp = null;
        TreeNode node = root;
        que.add(node);

        while(!que.isEmpty()){
            node = que.remove(0);
            if(!indexs.containsKey(node.val)){
                temp = new ArrayList();
                list.add(temp);
                indexs.put(node.val,list.size() - 1);
            }else{
                temp = list.get(indexs.get(node.val));
            }

            if(node.left != null){
                que.add(node.left);
                temp.add(node.left.val);

                if(!indexs.containsKey(node.left.val)){
                    List<Integer> leftL = new ArrayList<>();
                    leftL.add(node.val);
                    list.add(leftL);
                    indexs.put(node.left.val, list.size() - 1);
                }
            }

            if(node.right != null){
                que.add(node.right);
                temp.add(node.right.val);
                if(!indexs.containsKey(node.right.val)){
                    List<Integer> rightL = new ArrayList<>();
                    rightL.add(node.val);
                    list.add(rightL);
                    indexs.put(node.right.val, list.size() - 1);
                }
            }

        }
    }
}

class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
}
