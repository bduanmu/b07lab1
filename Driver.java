import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        ArrayList<Double> c1 = new ArrayList<Double>();
        c1.add(6.0);
        c1.add(5.0);
        c1.add(-1.0);
        ArrayList<Integer> e1 = new ArrayList<Integer>();
        e1.add(0);
        e1.add(3);
        e1.add(5);
        Polynomial p1 = new Polynomial(c1, e1);
        ArrayList<Double> c2 = new ArrayList<Double>();
        c2.add(-2.0);
        c2.add(-9.0);
        c2.add(1.0);
        ArrayList<Integer> e2 = new ArrayList<Integer>();
        e2.add(1);
        e2.add(4);
        e2.add(5);
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        Polynomial prod = p1.multiply(p2);
        for (int i = 0; i < prod.coefficients.size(); i++) {
            System.out.print(prod.coefficients.get(i) + "x^" + prod.exponents.get(i) + " + ");
        }

        System.out.println("");

        ArrayList<Double> c3 = new ArrayList<Double>();
        c3.add(1.0);
        c3.add(1.0);
        ArrayList<Integer> e3 = new ArrayList<Integer>();
        e3.add(0);
        e3.add(1);
        ArrayList<Double> c4 = new ArrayList<Double>();
        c4.add(-1.0);
        c4.add(1.0);
        ArrayList<Integer> e4 = new ArrayList<Integer>();
        e4.add(0);
        e4.add(1);
        Polynomial p3 = new Polynomial(c3, e3);
        Polynomial p4 = new Polynomial(c4, e4);
        Polynomial prod2 = p3.multiply(p4);
        for (int i = 0; i < prod2.coefficients.size(); i++) {
            System.out.print(prod2.coefficients.get(i) + "x^" + prod2.exponents.get(i) + " + ");
        }

        System.out.println("");
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}