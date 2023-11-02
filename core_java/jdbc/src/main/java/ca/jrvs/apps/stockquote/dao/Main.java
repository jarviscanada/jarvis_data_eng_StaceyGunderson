package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/stockquote";
    private static final String user = "postgres";
    private static final String password = "password";

    public static void main(String[]args){
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            PositionDao position = new PositionDao(conn);
            QuoteDao quotedao = new QuoteDao(conn);

            /*
            Optional<Position> opt = position.findById(0);
            if (opt.isEmpty()){
                System.out.println("Pos is empty");
            }
            else {
                System.out.println(opt.get());
            }
            */

            Position pos = new Position(3,"MSFT", 23, 4.2);
            //Position opt = position.save(pos);
            //position.deleteById(1);
            //position.deleteAll();
            //position.findAll();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            Quote quote = new Quote(2, "AMZN", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);


            //quotedao.deleteById(2);
            //quotedao.findById(2);
            Quote rs = quotedao.save(quote);
            quotedao.findAll();
            quotedao.deleteAll();
            quotedao.findAll();
            //quotedao.findById(2);

        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }



    }
}
