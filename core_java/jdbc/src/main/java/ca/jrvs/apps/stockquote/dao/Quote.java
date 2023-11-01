package ca.jrvs.apps.stockquote.dao;

import java.sql.Timestamp;
import java.sql.Date;


public class Quote {

    private int id; //unique identifier
    private String symbol;
    private double open;
    private double high;
    private double low;
    private double price;
    private int volume;
    private Date latestTradingDay;
    private double previousClose;
    private double change;
    private String changePercent;
    private Timestamp timestamp; //time when the info was pulled

    public static class PositionService {

        private PositionDao dao;

        public Position buy(String symbol, int numberOfShares, double price) {
            //TO DO
            return null;
        }

        public void sell(int id) {
            //TO DO
        }

    }
}