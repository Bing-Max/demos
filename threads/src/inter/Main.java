package inter;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String str1 = new String("hello world") ;
        String str2 = new String("hello world");

        System.out.println(str1 == str2);

        System.out.println(str1.equals(str2));
        BufferedInputStream bufferedInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("D://readme.md")));

            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = bufferedInputStream.read(bytes)) > 0 ){
                String str = new String(bytes,0, len);
                stringBuilder.append(str);
            }

            System.out.println(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
