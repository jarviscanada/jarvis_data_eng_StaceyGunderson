package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PositionDao implements CrudDao <Position, Integer> {

    QuoteHttpHelper qhh;

    private static final String INSERT = "INSERT INTO position (id, symbol, number_of_shares, value_paid) VALUES ( ?, ?, ?, ?)";

    private static final String GET_ONE = "SELECT id, symbol, number_of_shares, value_paid FROM position WHERE id=?";

    private static final String GET_ALL = "SELECT id, symbol, number_of_shares, value_paid FROM position";
    private static final String DELETE_ONE = "DELETE FROM position WHERE id=?";

    private static final String DELETE_ALL = "TRUNCATE TABLE position";

    Connection connection;

    PositionDao (Connection connection){
        this.connection = connection;
    }

    @Override
    public Position save(Position entity) throws IllegalArgumentException {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setInt(1, entity.getId());
            statement.setString(1, entity.getSymbol());
            statement.setInt(1, entity.getNumOfShares());
            statement.setDouble(1, entity.getValuePaid());
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

                return Optional.of(pos);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable findAll() {

        return null;
    }


    public void deleteById(Integer integer) throws IllegalArgumentException {

    }


    @Override
    public void deleteAll() {

    }
}
