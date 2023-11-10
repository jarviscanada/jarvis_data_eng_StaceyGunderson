package ca.jrvs.apps.stockquote.dao.Example;

import ca.jrvs.apps.stockquote.dao.Example.NotSoSimpleCalculator;

public class NotSoSimpleCalculatorImpl implements NotSoSimpleCalculator {

    private SimpleCalculator calc;

    public NotSoSimpleCalculatorImpl(SimpleCalculator calc) {
        this.calc = calc;
    }


    public int power(int x, int y) {

        return (int) Math.pow(x,y);
    }


    public int abs(int x) {
        if (x>=0) {
            return x;
        }
        return calc.multiply(x, -1);
    }


    public double sqrt(int x) {

        return Math.sqrt(x);
    }

}