package Operations;

import Polynomial.Polynomial;
import Polynomial.Monomial;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.System.exit;

public class ProceedOperations {
    public Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : p1.getPolynomial().entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue().getCoefficient();
            if (p2.getPolynomial().containsKey(power)) {
                coefficient += p2.getPolynomial().get(power).getCoefficient();
            }
            if (coefficient != 0) {
                Monomial monomial = new Monomial(power, coefficient);
                result.addMonomial(monomial);
            }
        }
        for (Map.Entry<Integer, Monomial> entry : p2.getPolynomial().entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue().getCoefficient();
            if (!p1.getPolynomial().containsKey(power)) {
                Monomial monomial = new Monomial(power, coefficient);
                result.addMonomial(monomial);
            }
        }
        return result;
    }

    public Polynomial substract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : p1.getPolynomial().entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue().getCoefficient();
            if (p2.getPolynomial().containsKey(power)) {
                coefficient -= p2.getPolynomial().get(power).getCoefficient();
            }
            if (coefficient != 0) {
                Monomial monomial = new Monomial(power, coefficient);
                result.addMonomial(monomial);
            }
        }
        for (Map.Entry<Integer, Monomial> entry : p2.getPolynomial().entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue().getCoefficient();
            if (!p1.getPolynomial().containsKey(power)) {
                coefficient = -coefficient; // negate the coefficient
                Monomial monomial = new Monomial(power, coefficient);
                result.addMonomial(monomial);
            }
        }
        return result;
    }

    public Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial resultMultiply = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : p1.getPolynomial().entrySet()) {
            int power1 = entry.getKey();
            double coefficient1 = entry.getValue().getCoefficient();
            for (Map.Entry<Integer, Monomial> entry2 : p2.getPolynomial().entrySet()) {
                int power2 = entry2.getKey();
                double coefficient2 = entry2.getValue().getCoefficient();
                int power = power1 + power2;
                double coefficient = coefficient1 * coefficient2;
                if (resultMultiply.getPolynomial().containsKey(power)) {
                    coefficient += resultMultiply.needCoefficient(power);
                }
                if (coefficient != 0) {
                    Monomial monomial = new Monomial(power, coefficient);
                    resultMultiply.addMonomial(monomial);
                }
            }
        }
        return resultMultiply;
    }

    public Polynomial derivative(Polynomial polynomial) {
        Polynomial resultDerivative = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomial().entrySet()) { // start from power 1
            int maxPower = entry.getKey();
            double coefficient = entry.getValue().getCoefficient();
            if (coefficient != 0) {
                Monomial monomial = new Monomial(maxPower - 1, coefficient * maxPower);
                resultDerivative.addMonomial(monomial);
            }
        }
        return resultDerivative;
    }

    public Polynomial integration(Polynomial polynomial) {
        Polynomial resultIntegration = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomial().entrySet()) { // start from power 1
            int maxPower = entry.getKey();
            double coefficient = entry.getValue().getCoefficient();
            if (coefficient != 0) {
                Monomial monomial = new Monomial(maxPower + 1, coefficient / (maxPower + 1));
                resultIntegration.addMonomial(monomial);
            }
        }
        return resultIntegration;
    }

    public ArrayList<Polynomial> dividePolynomials(Polynomial divident, Polynomial divisor) throws ArithmeticException, IllegalArgumentException {
        if (divisor.getPolynomial().isEmpty() || divisor.getPolynomial().values().stream().allMatch(c -> c.getCoefficient() == 0)) {
            JOptionPane.showMessageDialog(null, "Division by zero");
        }
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        int dividendDegree = divident.getDegree();
        int divisorDegree = divisor.getDegree();
        if (dividendDegree < divisorDegree) {
            JOptionPane.showMessageDialog(null, "Dividend degree is smaller than divisor degree");
        }
        if (divisor.getDegree() == 0) {
            for (Map.Entry<Integer, Monomial> entry : divident.getPolynomial().entrySet()) {
                int power = entry.getKey();
                double coefficient = entry.getValue().getCoefficient() / divisor.needCoefficient(0);
                quotient.addMonomial(new Monomial(power, coefficient));
            }
            return new ArrayList<Polynomial>() {{
                add(quotient);
                add(remainder);
            }};
        }
        while (dividendDegree >= divisorDegree) {
            if (divisor.needCoefficient(divisorDegree) == 0) {
                JOptionPane.showMessageDialog(null, "Divisor coefficient is zero");
                break;
            }
            double quotientCoefficient = divident.needCoefficient(dividendDegree) / divisor.needCoefficient(divisorDegree);
            Monomial quotientMonomial = new Monomial(dividendDegree - divisorDegree, quotientCoefficient);
            quotient.addMonomial(quotientMonomial);
            Polynomial product = new Polynomial();
            product.addMonomial(quotientMonomial);
            product = multiply(product, divisor);
            Polynomial difference = substract(divident, product);
            divident.getPolynomial().clear();
            divident.getPolynomial().putAll(difference.getPolynomial());
            dividendDegree = divident.getDegree();
        }
        for (Map.Entry<Integer, Monomial> entry : divident.getPolynomial().entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue().getCoefficient();
            remainder.addMonomial(new Monomial(power, coefficient));
        }
        return new ArrayList<Polynomial>() {{
            add(quotient);
            add(remainder);
        }};
    }
}


