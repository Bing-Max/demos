package inter;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] arr = {2,-1,3,4,4,8,6,8};

        int[] temp = new int[arr.length];

//        quickSort(arr,0, arr.length - 1);
        mergeSort(arr,0,arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int mid = getMidAndSwap(arr, low, high);

            quickSort(arr, low, mid);
            quickSort(arr, mid + 1, high);
        }
    }

    public static int getMidAndSwap(int[] arr, int low, int high){
        int start, end, temp;

        temp = arr[low];
        start = low;
        end = high;

        while (start < end){
            while (start < end && arr[end] >= temp){
                end--;
            }

            if(start < end){
                arr[start] = arr[end];
                start++;
            }

            while (start < end && arr[start] < temp){
                start++;
            }

            if(start < end){
                arr[end] = arr[start];
                end--;
            }
        }

        arr[start] = temp;
        return start;
    }

    public static void mergeSort(int[] arr, int low, int high, int[] temp){
        if(low < high){
            int mid = (low + high) / 2;

            mergeSort(arr, low, mid, temp);
            mergeSort(arr, mid + 1, high, temp);

            merge(arr, low, mid, high, temp);
        }
    }

    public static void merge(int[] arr, int low,int mid, int high,int[] temp){
        for(int i = mid + 1;i <= high; i++){
            temp[i] = arr[i];
        }

        int index1, index2, cur;
        index1 = mid;//arr
        index2 = high;//temp
        cur = high; //copy after

        while (index1 >= low && index2 > mid){
            while (index1 >= low && index2 > mid && temp[index2] >= arr[index1]){
                arr[cur--] = temp[index2--];
            }

            while (index1 >= low && index2 > mid && arr[index1] >= temp[index2]){
                arr[cur--] = arr[index1--];
            }
        }

        while (index2 > mid){
            arr[cur--] = temp[index2--];
        }

        while (index1 >= low){
            arr[cur--] = arr[index1--];
        }
    }

}
