package Polynomial;

public class Monomial {

    private double coefficient;   /// the coefficient of the monomial

    private int power;     /// the power of the monomial


    /// contructor for the monomial
    public Monomial(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    /// getters
    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }


    /// setters

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
