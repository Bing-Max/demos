package inter.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

//public class Main {
//
//    static int maxScore;
//    static int maxNum;
//    static int n, k;
//    static int[] val;
//    static int[][] a;
//    static boolean[] choose;
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int ss = in.nextInt();
//        Main main = new Main();
//        for(int loop=0; loop<ss; loop++) {
//            n = in.nextInt();
//            k = in.nextInt();
//            val = new int[n];
//            for(int i=0; i<n; i++) {
//                val[i] = in.nextInt();
//            }
//            a = new int[n][n];
//            for(int i=0; i<n; i++) {
//                for(int j=0; j<n; j++) {
//                    a[i][j] = in.nextInt();
//                }
//            }
//
//
//            maxScore = Integer.MIN_VALUE;
//            maxNum = 0;
//            choose = new boolean[n];
//            int score;
//            //select num people
//            int selectNum;
//
//            for(int i= (1 << k) - 1; i < 1<<n; i++) {
//                score = 0;
//                selectNum = 0;
//                for(int j=0; j<n; j++) {
//                    if((i>>j)%2==1) {
//                        choose[j] = true;
//                        selectNum++;
//                        score = score + val[j];
//                        for(int k=0; k<j; k++) {
//                            if(choose[k]) score = score + a[j][k];
//                        }
//                    } else {
//                        choose[j] = false;
//                    }
//                }
//                //System.out.println(i+" "+score+" "+selectNum);
//                if(selectNum<k) continue;
//                if(score>maxScore) {
//                    maxScore = score;
//                    maxNum = 1;
//                } else if(score==maxScore) {
//                    maxNum++;
//                }
//            }
//
//            System.out.println(maxScore + " " + maxNum);
//
//        }
//    }
//}

public class Main{

    static int n;
    static int k;
    static int[][] valCon;
    static int[] val;
    static boolean[] choose;
    static int maxCount;
    static int maxScore;


    // 枚举

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while(num > 0){
            n = sc.nextInt();
            k = sc.nextInt();

            val = new int[n];
            valCon = new int[n][n];
            choose = new boolean[n];
            maxScore = Integer.MIN_VALUE;
            maxCount = 0;

            for(int i = 0; i < n; i++){
                val[i] = sc.nextInt();
            }

            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++)
                    valCon[j][k] = sc.nextInt();
            }

            getMaxSolu();
            num--;
        }
    }

    public static void getMaxSolu(){

        int score = 0;
        int selectNum = 0;

        for(int i = (1 << k) - 1 ; i < (1 << n); i++){
            score = 0;
            selectNum = 0;

            for(int j = 0; j < n; j++){
                if(((i >> j) % 2) == 1){
                    choose[j] = true;

                    score += val[j];
                    selectNum++;

                    for(int k = 0; k < j; k++){
                        if(choose[k]) score += valCon[j][k];
                    }

                }else{
                    choose[j] = false;
                }
            }

            System.out.println(i + "====" + score);

            if(k > selectNum) continue;

            if(score > maxScore){
                maxCount = 1;
                maxScore = score;
            }else if(score == maxScore){
                maxCount++;
            }

        }

        System.out.println(maxScore + " " + maxCount);

    }


}
