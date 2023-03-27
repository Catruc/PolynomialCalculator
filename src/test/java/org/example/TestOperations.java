package org.example;

import static org.junit.Assert.assertEquals;

import Operations.ProceedOperations;
import Polynomial.Monomial;
import Polynomial.Polynomial;
import org.junit.Test;

import java.util.ArrayList;

public class TestOperations {

    public Polynomial createPolynomial1() {
        Polynomial polynomial1 = new Polynomial();
        polynomial1.addMonomial(new Monomial(2, 3));
        polynomial1.addMonomial(new Monomial(1, 2));
        polynomial1.addMonomial(new Monomial(0, 1));

        return polynomial1;
    }

    public Polynomial createPolynomial2() {
        Polynomial polynomial2 = new Polynomial();
        polynomial2.addMonomial(new Monomial(3, 3));
        polynomial2.addMonomial(new Monomial(1, 2));
        polynomial2.addMonomial(new Monomial(0, 1));

        return polynomial2;
    }


    @Test
    public void testAddition() {
        Polynomial polynomialAddition = new Polynomial();
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial2 = createPolynomial2();
        ProceedOperations proceedOperations = new ProceedOperations();
        try {
            polynomialAddition = proceedOperations.add(polynomial1, polynomial2);
            assertEquals("3.0x^3+3.0x^2+4.0x+2.0", polynomialAddition.toString());
            System.out.println("Test for addition passed");
        } catch (Exception e) {
            System.out.println("Test for addition failed");
        }

    }

    @Test
    public void testSubstraction() {
        Polynomial polynomialSubstraction = new Polynomial();
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial2 = createPolynomial2();
        ProceedOperations proceedOperations = new ProceedOperations();
        try {
            polynomialSubstraction = proceedOperations.substract(polynomial1, polynomial2);
            assertEquals("-3.0x^3+3.0x^2", polynomialSubstraction.toString());
            System.out.println("Test for substraction passed");
        } catch (Exception e) {
            System.out.println("Test for substraction failed");
        }
    }

    @Test
    public void testMultiplication() {
        Polynomial polynomialMultiplication = new Polynomial();
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial2 = createPolynomial2();
        ProceedOperations proceedOperations = new ProceedOperations();
        try {
            polynomialMultiplication = proceedOperations.multiply(polynomial1, polynomial2);
            assertEquals("9.0x^5+6.0x^4+12.0x^3+11.0x^2+6.0x+1.0", polynomialMultiplication.toString());
            System.out.println("Test for multiplication passed");
        } catch (Exception e) {
            System.out.println("Test for multiplication failed");
        }
    }

    @Test
    public void testDerivation() {
        Polynomial polynomialDerivation = new Polynomial();
        Polynomial polynomial1 = createPolynomial1();
        ProceedOperations proceedOperations = new ProceedOperations();
        try {
            polynomialDerivation = proceedOperations.derivative(polynomial1);
            assertEquals("6.0x+2.0", polynomialDerivation.toString());
            System.out.println("Test for derivation passed");
        } catch (Exception e) {
            System.out.println("Test for derivation failed");
        }
    }

    @Test
    public void testIntegration() {
        Polynomial polynomialIntegration = new Polynomial();
        Polynomial polynomial1 = createPolynomial1();
        ProceedOperations proceedOperations = new ProceedOperations();
        try
        {
            polynomialIntegration = proceedOperations.integration(polynomial1);
            assertEquals("x^3+x^2+x", polynomialIntegration.toString());
            System.out.println("Test for integration passed");
        }catch (Exception e)
        {
            System.out.println("Test for integration failed");
        }
    }

    @Test
    public void testDivision() {
        ArrayList<Polynomial> polynomialDivision = new ArrayList<>();
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial2 = createPolynomial2();
        ProceedOperations proceedOperations = new ProceedOperations();
        try
        {
            polynomialDivision = proceedOperations.dividePolynomials(polynomial2, polynomial1);
            assertEquals("x-0.6666666666666666", polynomialDivision.get(0).toString());
            System.out.println("Test for division passed");
        }catch (Exception e)
        {
            System.out.println("Test for division failed");
        }
    }

}
