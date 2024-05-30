import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Polynomial {
    ArrayList<Double> coefficients = new ArrayList<Double>();
    ArrayList<Integer> exponents = new ArrayList<Integer>();

    public Polynomial() {}

    public Polynomial(ArrayList<Double> coefficients, ArrayList<Integer> exponents) {
        this.coefficients.addAll(coefficients);
        this.exponents.addAll(exponents);
    }

    public Polynomial(File polynomial) {
        try {
            Scanner sc = new Scanner(polynomial);
            String polyString = sc.nextLine();
            String coeff = "";
            String exp = "";
            boolean isCoeff = true;
            char[] poly = polyString.toCharArray();

            for(int i = 0; i < poly.length; i++) {
                char ch = poly[i];
                if (ch == '-') {
                    if (this.coefficients.size() != 0) {
                        if (coeff.equals("")) {
                            coeff = "1";
                        } else if (coeff.equals("-")) {
                            coeff = "-1";
                        }

                        if (exp.equals("")) {
                            if (isCoeff) {
                                exp = "0";
                            } else {
                                exp = "1";
                            }
                        }

                        this.coefficients.add(Double.parseDouble(coeff));
                        this.exponents.add(Integer.parseInt(exp));
                    }

                    isCoeff = true;
                    coeff = "-";
                    exp = "";
                } else if (ch == '+') {
                    if (coeff.equals("")) {
                        coeff = "1";
                    } else if (coeff.equals("-")) {
                        coeff = "-1";
                    }

                    if (exp.equals("")) {
                        if (isCoeff) {
                            exp = "0";
                        } else {
                            exp = "1";
                        }
                    }

                    this.coefficients.add(Double.parseDouble(coeff));
                    this.exponents.add(Integer.parseInt(exp));
                    isCoeff = true;
                    coeff = "";
                    exp = "";
                } else if (ch == 'x') {
                    isCoeff = false;
                } else if (isCoeff) {
                    coeff = coeff + ch;
                } else {
                    exp = exp + ch;
                }
            }

            if (coeff.equals("")) {
                coeff = "1";
            } else if (coeff.equals("-")) {
                coeff = "-1";
            }

            if (exp.equals("")) {
                if (isCoeff) {
                    exp = "0";
                } else {
                    exp = "1";
                }
            }

            this.coefficients.add(Double.parseDouble(coeff));
            this.exponents.add(Integer.parseInt(exp));
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error");
        }
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

    public void saveToFile(String fileName) {
        try {
            String polynomial = "";

            for(int i = 0; i < this.coefficients.size(); i++) {
                if ((Double)this.coefficients.get(i) == 1.0D) {
                    polynomial = polynomial + "+";
                } else if ((Double)this.coefficients.get(i) == -1.0D) {
                    polynomial = polynomial + "-";
                } else if ((Double)this.coefficients.get(i) < 0.0D) {
                    polynomial = polynomial + this.coefficients.get(i);
                } else {
                    polynomial = polynomial + "+" + this.coefficients.get(i);
                }

                if ((Integer)this.exponents.get(i) != 0) {
                    polynomial = polynomial + "x";
                    if ((Integer)this.exponents.get(i) != 1) {
                        polynomial = polynomial + this.exponents.get(i);
                    }
                }
            }

            if (polynomial.substring(0, 1).equals("+")) {
                polynomial = polynomial.substring(1);
            }

            FileWriter writer = new FileWriter(fileName);
            writer.write(polynomial);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
}