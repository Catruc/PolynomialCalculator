package org.example;

import GUI.Calculator;
import Polynomial.Monomial;
import Polynomial.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.calculatorUserInterface();
    }
}
