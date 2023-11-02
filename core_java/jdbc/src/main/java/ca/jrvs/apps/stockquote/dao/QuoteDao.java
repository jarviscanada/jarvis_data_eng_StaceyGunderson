package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class QuoteDao implements CrudDao{

    QuoteHttpHelper qhh;

    private static final String INSERT = "INSERT INTO position (id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ONE = "SELECT id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote WHERE id=?";

    private static final String GET_ALL = "SELECT id, symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote";

    Connection connection;

    QuoteDao (Connection connection) { this.connection = connection;}

    @Override
    public Object save(Object entity) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Optional findById(Object o) throws IllegalArgumentException {


        return Optional.empty();
    }

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public void deleteById(Object o) throws IllegalArgumentException {

    }

    @Override
    public void deleteAll() {

    }
}
