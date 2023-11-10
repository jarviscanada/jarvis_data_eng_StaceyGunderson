package ca.jrvs.apps.stockquote.dao;

import ca.jrvs.apps.stockquote.dao.Quote;
import ca.jrvs.apps.twitter.example.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;


public class QuoteHttpHelper {

    private String apiKey = "3a233912f7mshfcc4d55b4a532b4p147a46jsn76b8d93ef87e";
    private OkHttpClient client;
    static JsonParser parser;

    /**
     * Fetch latest quote data from Alpha Vantage endpoint
     * @param symbol
     * @return Quote with latest data
     * @throws IllegalArgumentException - if no data was found for the given symbol
     */
    public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {
        parser = new JsonParser();
        symbol = symbol.toUpperCase();
        if (!symbol.matches("^[A-Z]{1,4}$"))
            return null;

        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol="+symbol+"&datatype=json";


        Request request = new Request.Builder()
                .url(url)
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            //objectMapper.readTree(response.body().string()).get("Global Quote");
            Quote quote = objectMapper.readValue(objectMapper.readTree(response.body().string()).get("Global Quote").toString(), Quote.class);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            quote.setTimestamp(timestamp);

            return quote;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
