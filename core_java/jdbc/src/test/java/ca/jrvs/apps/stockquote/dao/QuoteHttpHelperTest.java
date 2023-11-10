package ca.jrvs.apps.stockquote.dao;

import ca.jrvs.apps.twitter.example.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class QuoteHttpHelperTest {

    QuoteHttpHelper quoteHttpHelper;
    JsonParser jsonParser;
    String expected;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        quoteHttpHelper = new QuoteHttpHelper();
        jsonParser = new JsonParser();
    }

    @org.junit.jupiter.api.Test
    void fetchQuoteInfo() throws IOException {
        expected = Files.readString(Path.of("C:\\Users\\shado\\Documents\\GitHub\\jarvis_data_eng_StaceyGunderson\\core_java\\jdbc\\src\\test\\java\\ca\\jrvs\\apps\\stockquote\\dao\\quote.json"));
        Quote quote = quoteHttpHelper.fetchQuoteInfo("MSFT");
        String actual = jsonParser.toJson(quote,true,false);

        System.out.println(expected+'\n');
        System.out.println(actual);
        assertEquals(expected,actual);
    }
}