package reflect;

public class Test04 {

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = Class.forName("reflect.Test04").getClassLoader();
        System.out.println(classLoader);

        System.out.println(classLoader.getParent());

        System.out.println(classLoader.getParent().getParent());


        System.out.println(System.getProperty("java.class.path"));
        /**
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\charsets.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\deploy.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\access-bridge-64.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\cldrdata.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\dnsns.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\jaccess.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\jfxrt.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\localedata.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\nashorn.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunec.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunjce_provider.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunmscapi.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunpkcs11.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\zipfs.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\javaws.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jce.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jfr.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jfxswt.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jsse.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\management-agent.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\plugin.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\resources.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\rt.jar;
         * E:\IDEA-workspace\threads\out\production\threads;
         * D:\idea\IntelliJ IDEA 2019.3.3\lib\idea_rt.jar
         */
    }
}
