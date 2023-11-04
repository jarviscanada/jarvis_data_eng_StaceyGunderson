package ca.jrvs.apps.stockquote.dao;

import java.util.Optional;

public class QuoteService {

    private QuoteDao dao;
    private QuoteHttpHelper httpHelper;

    /**
     * Fetches latest quote data from endpoint
     * @param symbol
     * @return Latest quote information or empty optional if symbol not found
     */
    public Optional<Quote> fetchQuoteDataFromAPI(String symbol) {
        //TO DO
        symbol = symbol.toUpperCase();
        if (!symbol.matches("^[A-Z]{1,4}$"))
            return null;

        //CHECK IF SYMBOL RETURNS ACTUAL VALUE


        return null;
    }

}