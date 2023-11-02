package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


public class QuoteDao implements CrudDao <Quote, Integer>{

    QuoteHttpHelper qhh;

    private static final String INSERT = "INSERT INTO quote (id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ONE = "SELECT id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote WHERE id=?";

    private static final String GET_ALL = "SELECT id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote";
    
    private static final String DELETE_ONE = "DELETE FROM quote WHERE id=?";
    
    private static final String DELETE_ALL = "TRUNCATE TABLE quote CASCADE";

    Connection connection;

    QuoteDao (Connection connection) { this.connection = connection;}

    @Override
    public Quote save(Quote entity) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getSymbol());
            statement.setDouble(3, entity.getOpen());
            statement.setDouble(4, entity.getHigh());
            statement.setDouble(5, entity.getLow());
            statement.setDouble(6, entity.getPrice());
            statement.setInt(7, entity.getVolume());
            statement.setDate(8, entity.getLatestTradingDay());
            statement.setDouble(9, entity.getPreviousClose());
            statement.setDouble(10, entity.getChange());
            statement.setString(11, entity.getChangePercent());
            statement.setTimestamp(12, entity.getTimestamp());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(entity + " added");
        return entity;
    }

    @Override
    public Optional<Quote> findById(Integer integer) throws IllegalArgumentException {
        Quote quote;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setInt(1, integer);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                quote = new Quote(rs.getInt("id"),
                        rs.getString("symbol"),
                        rs.getDouble("open"),
                        rs.getDouble("high"),
                        rs.getDouble("low"),
                        rs.getDouble("price"),
                        rs.getInt("volume"),
                        rs.getDate("latest_trading_day"),
                        rs.getDouble("previous_close"),
                        rs.getDouble("change"),
                        rs.getString("change_percent"),
                        rs.getTimestamp("timestamp"));

                System.out.println(quote + " found");
                return Optional.of(quote);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Iterable findAll() {
        ArrayList<Quote> list = new ArrayList<Quote>();

        try (PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Quote quote = new Quote(rs.getInt("id"),
                        rs.getString("symbol"),
                        rs.getDouble("open"),
                        rs.getDouble("high"),
                        rs.getDouble("low"),
                        rs.getDouble("price"),
                        rs.getInt("volume"),
                        rs.getDate("latest_trading_day"),
                        rs.getDouble("previous_close"),
                        rs.getDouble("change"),
                        rs.getString("change_percent"),
                        rs.getTimestamp("timestamp"));

                list.add(quote);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(list + " found");
        return list;
    }

    @Override
    public void deleteById(Integer integer) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_ONE);){
            statement.setLong(1, integer);
            statement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(integer + " deleted");
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_ALL);){
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("All deleted");
    }
}
