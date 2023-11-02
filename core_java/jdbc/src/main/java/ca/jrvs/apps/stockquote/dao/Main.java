package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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

            /*
            Optional<Position> opt = position.findById(0);
            if (opt.isEmpty()){
                System.out.println("Pos is empty");
            }
            else {
                System.out.println(opt.get());
            }
            */

            Position pos = new Position(1,"MSFT", 24, 4.4);
            Position opt = position.save(pos);

        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }



    }
}
