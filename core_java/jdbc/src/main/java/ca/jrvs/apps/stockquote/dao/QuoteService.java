package ca.jrvs.apps.stockquote.dao;

import java.util.Optional;

public class QuoteService {

    private QuoteDao dao;
    private QuoteHttpHelper httpHelper;

    QuoteService(QuoteHttpHelper httpHelper, QuoteDao dao) {
        this.dao = dao;
        this.httpHelper = httpHelper;
    }

    /**
     * Fetches latest quote data from endpoint
     * @param symbol
     * @return Latest quote information or empty optional if symbol not found
     */
    public Optional<Quote> fetchQuoteDataFromAPI(String symbol) {
        symbol = symbol.toUpperCase();
        if (!symbol.matches("^[A-Z]{3,4}$"))
            return Optional.empty();

        Quote quote = httpHelper.fetchQuoteInfo(symbol);
        storeQuoteData(quote);
        return Optional.ofNullable(quote);
    }

    public Optional<Quote> storeQuoteData(Quote newQuote) {
        Optional<Quote> oldQuote = dao.findBySymbol( newQuote.getSymbol());

        if (oldQuote.isPresent()) {
            dao.update(newQuote);
            return Optional.ofNullable(newQuote);
        }
        dao.save(newQuote);

        return Optional.ofNullable(newQuote);
    }

}