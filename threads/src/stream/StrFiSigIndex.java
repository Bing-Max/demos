package stream;

import java.util.HashMap;

public class StrFiSigIndex {

    public static void main(String[] args) {
        System.out.println(getFirstSigIndex("hh"));
    }

    public static int getFirstSigIndex(String str){

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < str.length(); i++){
            int cur = str.charAt(i);

            if(map.containsKey(cur)){
                map.put(cur, -1);
            }else{
                map.put(cur, i);
            }
        }

        Integer integer = map.entrySet().stream().filter(entry -> !entry.getValue().equals(-1)).map(entry -> entry.getValue())
                .min(Integer::compareTo).orElse(-1);

        return integer;
    }
}
