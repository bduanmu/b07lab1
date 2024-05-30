import java.util.ArrayList;


public class Polynomial {
    ArrayList<Double> coefficients = new ArrayList<Double>();
    ArrayList<Integer> exponents = new ArrayList<Integer>();

    public Polynomial() {}

    public Polynomial(ArrayList<Double> coefficients, ArrayList<Integer> exponents) {
        this.coefficients.addAll(coefficients);
        this.exponents.addAll(exponents);
    }

    public Polynomial add(Polynomial polynomial) {
        Polynomial sum = new Polynomial(polynomial.coefficients, polynomial.exponents);

        for (int i = 0; i < exponents.size(); i++) {
            if (sum.exponents.contains(exponents.get(i))) {
                sum.coefficients.set(i, sum.coefficients.get(i) + coefficients.get(i));
                if (sum.coefficients.get(i) == 0) {
                    sum.coefficients.remove(i);
                    sum.exponents.remove(i);
                }
            } else {
                sum.coefficients.add(coefficients.get(i));
                sum.exponents.add(exponents.get(i));
            }
        }

        return sum;
    }

    public Polynomial multiply(Polynomial polynomial) {
        Polynomial product = new Polynomial();
        for (int i = 0; i < coefficients.size(); i++) {
            for (int j = 0; j < polynomial.coefficients.size(); j++) {
                int exp = exponents.get(i) + polynomial.exponents.get(j);
                double coeff = coefficients.get(i) * polynomial.coefficients.get(j);

                if (product.exponents.contains(exp)) {
                    product.coefficients.set(product.exponents.indexOf(exp), product.coefficients.get(product.exponents.indexOf(exp)) + coeff);
                    if (product.coefficients.get(product.exponents.indexOf(exp)) == 0) {
                        product.coefficients.remove(product.exponents.indexOf(exp));
                        product.exponents.remove(product.exponents.indexOf(exp));
                    }
                } else {
                    product.exponents.add(exp);
                    product.coefficients.add(coeff);
                }
            }
        }

        return product;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(x, exponents.get(i));
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }


}