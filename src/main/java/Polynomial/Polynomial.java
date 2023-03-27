package Polynomial;

import GUI.Calculator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    private HashMap<Integer, Monomial> polynomial;  /// hashmap created for a polynomial

    public Polynomial() {
        this.polynomial = new HashMap<>();
    }


    public void addMonomial(Monomial monomial) {  /// method for adding a monomial to the polynomial
        int power = monomial.getPower();  /// the power of the monomial
        if (polynomial.containsKey(power)) {  /// if the polynomial already contains the power of the monomial
            Monomial existingMonomial = polynomial.get(power);  /// the monomial with the same power
            double newCoefficient = existingMonomial.getCoefficient() + monomial.getCoefficient();  /// the new coefficient of the monomial
            existingMonomial.setCoefficient(newCoefficient);  /// the new coefficient is set
        } else {
            polynomial.put(power, monomial);  /// if the polynomial does not contain the power of the monomial, the monomial is added to the polynomial
        }
    }


    public static Polynomial formPolynomial(String polynomialInput) throws IllegalArgumentException {  /// method for creating a polynomial from a string
        Polynomial polynomial = new Polynomial();  /// a new polynomial is created
        Pattern termPattern = Pattern.compile("([-+]?[0-9]*\\.?[0-9]*)\\*?x\\^([0-9]+)"); /// the pattern for the monomial
        Matcher matcher = termPattern.matcher(polynomialInput);  /// the matcher for the monomial
        if (!matcher.find()) {  /// if the polynomial is not in the correct format
            JOptionPane.showMessageDialog(null, "Invalid polynomial format!");  /// an error message is displayed
        }
        do {
            String coefficientString = matcher.group(1);  /// the coefficient of the monomial
            double coefficient = coefficientString.isEmpty() ? 1.0 : Double.parseDouble(coefficientString);  /// if the coefficient is empty, the coefficient is 1 else the coefficient is the coefficient of the monomial
            int power = Integer.parseInt(matcher.group(2));  /// the power of the monomial
            Monomial monomial = new Monomial(power, coefficient);  /// a new monomial is created
            polynomial.addMonomial(monomial);  /// the monomial is added to the polynomial
        } while (matcher.find()); /// the monomial is added to the polynomial until there are no more monomials

        return polynomial;  /// the polynomial is returned
    }



    public HashMap<Integer, Monomial> getPolynomial() {
        return polynomial;
    }


    public int getDegree() {  /// method for getting the degree of the polynomial
        int degree = 0;  /// the degree of the polynomial
        for (Map.Entry<Integer, Monomial> entry : polynomial.entrySet()) {  /// for each entry in the polynomial
            int currentDegree = entry.getKey();  /// the current degree of the monomial
            if (currentDegree > degree) {  /// if the current degree is greater than the degree of the polynomial
                degree = currentDegree;  /// the degree of the polynomial is set to the current degree
            }
        }
        return degree;  /// the degree of the polynomial is returned
    }

    public double needCoefficient(int power) {  /// method for getting the coefficient of a monomial with a given power
        if (polynomial.containsKey(power)) {  /// if the polynomial contains the power of the monomial
            return polynomial.get(power).getCoefficient();  /// the coefficient of the monomial is returned
        }
        return 0.0;
    }

    public String toString() {  /// method for converting the polynomial to a string
        StringBuilder sb = new StringBuilder();
        for (int power = this.getDegree(); power >= 0; power--) {
            double coefficient = this.needCoefficient(power);
            if (coefficient == 0) {
                continue;
            }
            if (coefficient > 0 && sb.length() > 0) {
                sb.append("+");
            }
            if (coefficient == -1 && power > 0) {
                sb.append("-");
            } else if (coefficient != 1 || power == 0) {
                sb.append(coefficient);
            }
            if (power > 0) {
                sb.append("x");
                if (power > 1) {
                    sb.append("^").append(power);
                }
            }
        }
        if (sb.length() == 0) {
            sb.append("0");
        }
        return sb.toString();
    }


}
