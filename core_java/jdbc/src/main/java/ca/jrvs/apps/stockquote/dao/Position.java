package ca.jrvs.apps.stockquote.dao;

public class Position {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNumOfShares() {
        return numOfShares;
    }

    public void setNumOfShares(int numOfShares) {
        this.numOfShares = numOfShares;
    }

    public double getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(double valuePaid) {
        this.valuePaid = valuePaid;
    }

    private int id;
    private String symbol;
    private int numOfShares;
    private double valuePaid; //total amount paid for shares

    public Position (){

    }

    public Position (int id, String symbol, int numOfShares, double valuePaid){
        this.id = id;
        this.symbol = symbol;
        this.numOfShares = numOfShares;
        this.valuePaid = valuePaid;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", numOfShares=" + numOfShares +
                ", valuePaid=" + valuePaid +
                '}';
    }
}