package ca.jrvs.apps.stockquote.dao.Example;

public class SimpleCalculatorImpl implements SimpleCalculator {

    @Override
    public int add(int x, int y) {
        // TODO Auto-generated method stub
        return x+y;
    }

    @Override
    public int subtract(int x, int y) {
        // TODO Auto-generated method stub
        return x-y;
    }

    @Override
    public int multiply(int x, int y) {
        // TODO Auto-generated method stub
        return x*y;
    }

    @Override
    public double divide(int x, int y) {
        // TODO Auto-generated method stub
        //Dies on 0
        return x/y;
    }

}