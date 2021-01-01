package inter;

import java.util.concurrent.ConcurrentHashMap;

public class KthMax {

    public static void main(String[] args) {
        int[] arr = {2,-1,3,4,4,8,6,8};


        System.out.println(Kth(arr, 0, arr.length - 1, 2));
    }

    public static int Kth(int[] arr, int low, int high, int k){
        if(low < high){
            int mid = getMidIndex(arr, low, high);
            if(mid == k - 1)
                return arr[mid];
            if(mid >= k){
                return Kth(arr, low, mid, k);
            }else {
                return Kth(arr, mid+1, high, k);
            }
        }

        return -1;
    }

    public static int getMidIndex(int[] arr, int low, int high){
        int temp = arr[low];

        int left, right;
        left = low;
        right = high;

        while (left < right){
            while (left < right && arr[right] >= temp){
                right--;
            }

            if(left < right){
                arr[left] = arr[right];
                left++;
            }

            while(left < right && arr[left] < temp){
                left++;
            }

            if(left < right){
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = temp;

        return left;
    }
}
