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


    public void addMonomial(Monomial monomial) {
        int power = monomial.getPower();
        if (polynomial.containsKey(power)) {
            Monomial existingMonomial = polynomial.get(power);
            double newCoefficient = existingMonomial.getCoefficient() + monomial.getCoefficient();
            existingMonomial.setCoefficient(newCoefficient);
        } else {
            polynomial.put(power, monomial);
        }
    }


    public static Polynomial formPolynomial(String polynomialInput) throws IllegalArgumentException {
        Polynomial polynomial = new Polynomial();
        Pattern termPattern = Pattern.compile("([-+]?[0-9]*\\.?[0-9]*)\\*?x\\^([0-9]+)"); // ala bun
        Matcher matcher = termPattern.matcher(polynomialInput);
        if (!matcher.find()) {
            JOptionPane.showMessageDialog(null, "Invalid polynomial format!");
        }
        do {
            String coefficientString = matcher.group(1);
            double coefficient = coefficientString.isEmpty() ? 1.0 : Double.parseDouble(coefficientString);
            String powerString = matcher.group(2);
            int power = powerString.isEmpty() ? 0 : Integer.parseInt(powerString);
            Monomial monomial = new Monomial(power, coefficient);
            polynomial.addMonomial(monomial);
        } while (matcher.find());

        return polynomial;
    }

    public HashMap<Integer, Monomial> getPolynomial() {
        return polynomial;
    }





    public int getDegree() {
        int degree = 0;
        for (Map.Entry<Integer, Monomial> entry : polynomial.entrySet()) {
            int currentDegree = entry.getKey();
            if (currentDegree > degree) {
                degree = currentDegree;
            }
        }
        return degree;
    }

    public double needCoefficient(int power) {
        if (polynomial.containsKey(power)) {
            return polynomial.get(power).getCoefficient();
        }
        return 0.0;
    }

    public String toString() {
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
