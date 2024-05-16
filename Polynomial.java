public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0;
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients.clone();
    }

    public Polynomial add(Polynomial polynomial) {
        double[] poly1 = coefficients.clone();
        double[] poly2 = polynomial.coefficients.clone();
        if (poly1.length > poly2.length) {
            for (int i = 0; i < poly1.length; i++) {
                if (i < poly2.length) poly1[i] += poly2[i];
            }
            return new Polynomial(poly1);
        } else {
            for (int i = 0; i < poly2.length; i++) {
                if (i < poly1.length) poly2[i] += poly1[i];
            }
            return new Polynomial(poly2);
        }
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}