package Operations;

import Polynomial.Polynomial;
import Polynomial.Monomial;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.System.exit;

public class ProceedOperations {
    public Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();   /// the result of the addition
        for (Map.Entry<Integer, Monomial> entry : p1.getPolynomial().entrySet()) {   /// iterate through the first polynomial
            int power = entry.getKey();   /// get the power of the current monomial
            double coefficient = entry.getValue().getCoefficient();    /// get the coefficient of the current monomial
            if (p2.getPolynomial().containsKey(power)) {    /// if the second polynomial contains the current power
                coefficient += p2.getPolynomial().get(power).getCoefficient();   /// add the coefficients
            }
            if (coefficient != 0) {    /// if the coefficient is not 0
                Monomial monomial = new Monomial(power, coefficient);   /// create a new monomial
                result.addMonomial(monomial);    /// add the monomial to the result
            }
        }
        for (Map.Entry<Integer, Monomial> entry : p2.getPolynomial().entrySet()) {   /// iterate through the second polynomial
            int power = entry.getKey();   /// get the power of the current monomial
            double coefficient = entry.getValue().getCoefficient();     /// get the coefficient of the current monomial
            if (!p1.getPolynomial().containsKey(power)) {   /// if the first polynomial does not contain the current power
                Monomial monomial = new Monomial(power, coefficient);    /// create a new monomial
                result.addMonomial(monomial);   /// add the monomial to the result
            }
        }
        return result;   /// return the result
    }

    public Polynomial substract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();   /// the result of the substraction
        for (Map.Entry<Integer, Monomial> entry : p1.getPolynomial().entrySet()) {    /// iterate through the first polynomial
            int power = entry.getKey();   /// get the power of the current monomial
            double coefficient = entry.getValue().getCoefficient();   /// get the coefficient of the current monomial
            if (p2.getPolynomial().containsKey(power)) {   /// if the second polynomial contains the current power
                coefficient -= p2.getPolynomial().get(power).getCoefficient();   /// substract the coefficients
            }
            if (coefficient != 0) {   /// if the coefficient is not 0
                Monomial monomial = new Monomial(power, coefficient);    /// create a new monomial
                result.addMonomial(monomial);   /// add the monomial to the result
            }
        }
        for (Map.Entry<Integer, Monomial> entry : p2.getPolynomial().entrySet()) {   /// iterate through the second polynomial
            int power = entry.getKey();   /// get the power of the current monomial
            double coefficient = entry.getValue().getCoefficient();   /// get the coefficient of the current monomial
            if (!p1.getPolynomial().containsKey(power)) {   /// if the first polynomial does not contain the current power
                coefficient = -coefficient; // negate the coefficient
                Monomial monomial = new Monomial(power, coefficient);  /// create a new monomial
                result.addMonomial(monomial);  /// add the monomial to the result
            }
        }
        return result;   /// return the result
    }

    public Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial resultMultiply = new Polynomial();   /// the result of the multiplication
        for (Map.Entry<Integer, Monomial> entry : p1.getPolynomial().entrySet()) {   /// iterate through the first polynomial
            int power1 = entry.getKey();   /// get the power of the current monomial
            double coefficient1 = entry.getValue().getCoefficient();   /// get the coefficient of the current monomial
            for (Map.Entry<Integer, Monomial> entry2 : p2.getPolynomial().entrySet()) {   /// iterate through the second polynomial
                int power2 = entry2.getKey();   /// get the power of the current monomial
                double coefficient2 = entry2.getValue().getCoefficient();    /// get the coefficient of the current monomial
                int power = power1 + power2;   /// calculate the power of the new monomial
                double coefficient = coefficient1 * coefficient2;   /// calculate the coefficient of the new monomial
                if (resultMultiply.getPolynomial().containsKey(power)) {  /// if the result polynomial contains the current power
                    coefficient += resultMultiply.needCoefficient(power);   /// add the coefficients
                }
                if (coefficient != 0) {  /// if the coefficient is not 0
                    Monomial monomial = new Monomial(power, coefficient);  /// create a new monomial
                    resultMultiply.addMonomial(monomial);   /// add the monomial to the result
                }
            }
        }
        return resultMultiply;  /// return the result
    }

    public Polynomial derivative(Polynomial polynomial) {
        Polynomial resultDerivative = new Polynomial();   /// the result of the derivative
        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomial().entrySet()) {  /// start from power 1
            int maxPower = entry.getKey();  /// get the power of the current monomial
            double coefficient = entry.getValue().getCoefficient();  /// get the coefficient of the current monomial
            if (coefficient != 0) {  /// if the coefficient is not 0
                Monomial monomial = new Monomial(maxPower - 1, coefficient * maxPower);   /// create a new monomial
                resultDerivative.addMonomial(monomial);   /// add the monomial to the result
            }
        }
        return resultDerivative;
    }

    public Polynomial integration(Polynomial polynomial) {
        Polynomial resultIntegration = new Polynomial();   /// the result of the integration
        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomial().entrySet()) { /// start from power 1
            int maxPower = entry.getKey();      /// get the power of the current monomial
            double coefficient = entry.getValue().getCoefficient();  /// get the coefficient of the current monomial
            if (coefficient != 0) {  /// if the coefficient is not 0
                Monomial monomial = new Monomial(maxPower + 1, coefficient / (maxPower + 1));  /// create a new monomial
                resultIntegration.addMonomial(monomial);   /// add the monomial to the result
            }
        }
        return resultIntegration;   /// return the result
    }

    public ArrayList<Polynomial> dividePolynomials(Polynomial divident, Polynomial divisor) throws ArithmeticException, IllegalArgumentException {
        if (divisor.getPolynomial().isEmpty() || divisor.getPolynomial().values().stream().allMatch(c -> c.getCoefficient() == 0)) {  /// if the divisor is 0
            JOptionPane.showMessageDialog(null, "Division by zero");  /// show a message
        }
        Polynomial quotient = new Polynomial();   /// the quotient
        Polynomial remainder = new Polynomial();  /// the remainder
        int dividendDegree = divident.getDegree();  /// get the degree of the dividend
        int divisorDegree = divisor.getDegree();   /// get the degree of the divisor
        if (dividendDegree < divisorDegree) {  /// if the dividend degree is smaller than the divisor degree
            JOptionPane.showMessageDialog(null, "Dividend degree is smaller than divisor degree");  /// show a message
        }
        if (divisor.getDegree() == 0) {   /// if the divisor degree is 0
            for (Map.Entry<Integer, Monomial> entry : divident.getPolynomial().entrySet()) {  /// iterate through the dividend
                int power = entry.getKey();   /// get the power of the current monomial
                double coefficient = entry.getValue().getCoefficient() / divisor.needCoefficient(0);   /// get the coefficient of the current monomial
                quotient.addMonomial(new Monomial(power, coefficient));  /// add the monomial to the quotient
            }
            return new ArrayList<Polynomial>() {{  /// return the quotient and the remainder
                add(quotient);
                add(remainder);
            }};
        }
        while (dividendDegree >= divisorDegree) {  /// while the dividend degree is bigger than the divisor degree
            if (divisor.needCoefficient(divisorDegree) == 0) {   /// if the divisor coefficient is 0
                JOptionPane.showMessageDialog(null, "Divisor coefficient is zero");  /// show a message
                break;  /// break the loop
            }
            double quotientCoefficient = divident.needCoefficient(dividendDegree) / divisor.needCoefficient(divisorDegree);  /// calculate the quotient coefficient
            Monomial quotientMonomial = new Monomial(dividendDegree - divisorDegree, quotientCoefficient);  /// create a new monomial
            quotient.addMonomial(quotientMonomial);  /// add the quotientMonomial to the quotient
            Polynomial product = new Polynomial();   /// the product
            product.addMonomial(quotientMonomial);  /// add the quotientMonomial to the product
            product = multiply(product, divisor);   /// multiply the product with the divisor
            Polynomial difference = substract(divident, product);  /// substract the product from the dividend
            divident.getPolynomial().clear();  /// clear the dividend
            divident.getPolynomial().putAll(difference.getPolynomial());  /// add the difference to the dividend
            dividendDegree = divident.getDegree();  /// get the degree of the dividend
        }
        for (Map.Entry<Integer, Monomial> entry : divident.getPolynomial().entrySet()) {  /// iterate through the dividend
            int power = entry.getKey();  /// get the power of the current monomial
            double coefficient = entry.getValue().getCoefficient();  /// get the coefficient of the current monomial
            remainder.addMonomial(new Monomial(power, coefficient));   /// add the monomial to the remainder
        }
        return new ArrayList<Polynomial>() {{  /// return the quotient and the remainder
            add(quotient);
            add(remainder);
        }};
    }
}


