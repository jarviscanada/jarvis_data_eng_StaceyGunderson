package ca.jrvs.apps.stockquote.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class StockQuoteApp {
    private static final String url = "jdbc:postgresql://localhost:5432/stockquote";
    static HashMap<String, String> properties = new HashMap<String, String>();
    static String propertiesFile = "C:\\Users\\shado\\Documents\\GitHub\\jarvis_data_eng_StaceyGunderson\\core_java\\jdbc\\src\\resources\\properties.txt";
    public static Connection conn;
    public static PositionDao positionDao;
    public static QuoteDao quoteDao;
    public static StockQuoteController controller;
    public static QuoteHttpHelper quoteHttpHelper;
    public static QuoteService quoteService;
    public static Quote.PositionService positionService;



    public static void main(String[] args) {

        try {
        File filename = new File(propertiesFile);
        String line;
        BufferedReader bufrd = new BufferedReader( new FileReader(filename) );

         while ( (line = bufrd.readLine()) != null ){
            String[] values = line.split(":");
            String key = values[0].trim();
            String value = values[1].trim();
            if( !key.equals("") && !value.equals("") )
                properties.put(key, value);
        }

        Class.forName(properties.get("db-class"));

        conn = DriverManager.getConnection(url, properties.get("username"),properties.get("password"));
        positionDao = new PositionDao(conn);
        quoteDao = new QuoteDao(conn);
        quoteHttpHelper = new QuoteHttpHelper();
        positionService = new Quote.PositionService(quoteHttpHelper, positionDao);
        quoteService = new QuoteService(quoteHttpHelper, quoteDao);

        controller = new StockQuoteController();
        controller.initClient(quoteService, positionService);

        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }


    }
}
