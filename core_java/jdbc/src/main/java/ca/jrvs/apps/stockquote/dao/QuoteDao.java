package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


public class QuoteDao implements CrudDao <Quote, Integer>{

    QuoteHttpHelper qhh;

    private static final String INSERT = "INSERT INTO quote (symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE quote SET open=?, high=?, low=?, price=?, volume=?, latest_trading_day=?, previous_close=?, change=?, change_percent=?, timestamp=? WHERE symbol=?";

    private static final String GET_ONE = "SELECT id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote WHERE id=?";
    private static final String GET_SYMBOL = "SELECT id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote WHERE symbol=?";

    private static final String GET_ALL = "SELECT id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote";
    
    private static final String DELETE_ONE = "DELETE FROM quote WHERE id=?";
    
    private static final String DELETE_ALL = "TRUNCATE TABLE quote CASCADE";

    Connection connection;

    QuoteDao (Connection connection) { this.connection = connection;}

    public Quote update(Quote entity) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setDouble(1, entity.getOpen());
            statement.setDouble(2, entity.getHigh());
            statement.setDouble(3, entity.getLow());
            statement.setDouble(4, entity.getPrice());
            statement.setInt(5, entity.getVolume());
            statement.setDate(6, entity.getLatestTradingDay());
            statement.setDouble(7, entity.getPreviousClose());
            statement.setDouble(8, entity.getChange());
            statement.setString(9, entity.getChangePercent());
            statement.setTimestamp(10, entity.getTimestamp());
            statement.setString(11, entity.getSymbol());
            statement.executeUpdate();
            //System.out.println(i);
        } catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(entity + " updated.");
        return entity;
    }

    public Quote save(Quote entity) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, entity.getSymbol());
            statement.setDouble(2, entity.getOpen());
            statement.setDouble(3, entity.getHigh());
            statement.setDouble(4, entity.getLow());
            statement.setDouble(5, entity.getPrice());
            statement.setInt(6, entity.getVolume());
            statement.setDate(7, entity.getLatestTradingDay());
            statement.setDouble(8, entity.getPreviousClose());
            statement.setDouble(9, entity.getChange());
            statement.setString(10, entity.getChangePercent());
            statement.setTimestamp(11, entity.getTimestamp());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(entity + " added.");
        return entity;
    }


    public Optional<Quote> findBySymbol(String symbol) throws IllegalArgumentException {
        Quote quote;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_SYMBOL);){
            statement.setString(1, symbol);
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

                System.out.println(quote + " found.");
                return Optional.of(quote);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
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

                //System.out.println(quote + " found.");
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
        //System.out.println(list + " found.");
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
        //System.out.println(integer + " deleted.");
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_ALL);){
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("All deleted.");
    }
}
