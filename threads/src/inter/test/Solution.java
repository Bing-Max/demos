package inter.test;

import java.util.Arrays;
import java.util.LinkedList;

public class Solution {
    public static int maximumGap(int[] nums) {
        if(null == nums || nums.length < 2){
            return 0;
        }

        // 排序
        int start,  index, cur, temp;
        int max = -1;
        cur = 1;
        start = 0;
        index = 0;
        temp = 0;
        LinkedList<Integer>[] containers = new LinkedList[10];
        for(int i = 0; i < 10; i++){
            containers[i] = new LinkedList<Integer>();
        }

        while(start < nums.length){
            index = start;

            while(index < nums.length){
                temp = nums[index] / cur;
                temp = temp % 10;

                containers[temp].offerLast(nums[index++]);
            }

            index--;

            for(int k = 9; k > -1; k--){
                while(!containers[k].isEmpty()){
                    nums[index--] = containers[k].pollLast();
                }
            }

            cur *= 10;



            while(start < nums.length && nums[start] < cur)
                start++;
        }

        for(int i = 1; i < nums.length; i++){
            max = Math.max(max, nums[i] - nums[i - 1]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {3,6,9,1};

//        System.arraycopy();
        int re = maximumGap(arr);
        System.out.println(re);
        System.out.println(Arrays.toString(arr));
    }
}
