package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class PositionDao implements CrudDao <Position, Integer> {

    QuoteHttpHelper qhh;

    private static final String INSERT = "INSERT INTO position (symbol, number_of_shares, value_paid) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE position SET number_of_shares = ?,  value_paid=? WHERE id=?";

    private static final String GET_ONE = "SELECT id, symbol, number_of_shares, value_paid FROM position WHERE id=?";

    private static final String GET_BY_SYMBOL= "SELECT id, symbol, number_of_shares, value_paid FROM position WHERE symbol=?";

    private static final String GET_ALL = "SELECT id, symbol, number_of_shares, value_paid FROM position";
    private static final String DELETE_ONE = "DELETE FROM position WHERE id=?";

    private static final String DELETE_ALL = "TRUNCATE TABLE position";

    Connection connection;

    PositionDao (Connection connection){
        this.connection = connection;
    }

    public Position update(Position entity) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setInt(1, entity.getNumOfShares());
            statement.setDouble(2, entity.getValuePaid());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Position save(Position entity) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT);){

            statement.setString(1, entity.getSymbol());
            statement.setInt(2, entity.getNumOfShares());
            statement.setDouble(3, entity.getValuePaid());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }

    public Optional<Position> findById(Integer integer) throws IllegalArgumentException {
        Position pos = new Position();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setInt(1, integer);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                pos.setId(rs.getInt("id"));
                pos.setSymbol(rs.getString("symbol"));
                pos.setNumOfShares(rs.getInt("number_of_shares"));
                pos.setValuePaid(rs.getDouble("value_paid"));

                System.out.println(pos + " found");
                return Optional.of(pos);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Position> findBySymbol(String symbol) throws IllegalArgumentException {
        Position pos = new Position();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_SYMBOL);){
            statement.setString(1, symbol);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                pos.setId(rs.getInt("id"));
                pos.setSymbol(rs.getString("symbol"));
                pos.setNumOfShares(rs.getInt("number_of_shares"));
                pos.setValuePaid(rs.getDouble("value_paid"));

                //System.out.println(pos + " found");
                return Optional.of(pos);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Iterable<Position> findAll() {
        ArrayList<Position> list = new ArrayList<Position>();

        try (PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Position pos = new Position();
                pos.setId(rs.getInt("id"));
                pos.setSymbol(rs.getString("symbol"));
                pos.setNumOfShares(rs.getInt("number_of_shares"));
                pos.setValuePaid(rs.getDouble("value_paid"));

                list.add(pos);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        //System.out.println(list + " found");
        return list;
    }


    public void deleteById(Integer integer) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_ONE);){
            statement.setLong(1, integer);
            statement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAll() {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_ALL);){
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
