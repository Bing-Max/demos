package inter;

import java.math.BigDecimal;

public class Sum {

    public static void main(String[] args) {

        double a = 0.2;
        double b = -0.3;
        double res = sum(a, b);
        System.out.println(res);
    }

    public static double sum(double a, double b){

        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(a));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(b));

        return bigDecimal1.add(bigDecimal2).doubleValue();
    }
}
