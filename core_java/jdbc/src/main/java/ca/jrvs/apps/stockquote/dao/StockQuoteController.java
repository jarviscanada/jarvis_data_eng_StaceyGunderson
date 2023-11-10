package ca.jrvs.apps.stockquote.dao;

import ca.jrvs.apps.stockquote.dao.Quote;
import ca.jrvs.apps.stockquote.dao.QuoteService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;

public class StockQuoteController {

    private QuoteService quoteService;
    private Quote.PositionService positionService;
    private Scanner sc;

    /**
     * User interface for our application
     */
    public void initClient(QuoteService quoteService, Quote.PositionService posService) {
        String line = "";
        this.quoteService = quoteService;
        this.positionService = posService;

        System.out.println("1. To check all balances.");
        System.out.println("2. To check a current stock.");
        System.out.println("3. To quit.");
        try {
            sc = new Scanner(System.in);
            while(sc.hasNext()){
                line = sc.next();

                switch (line) {
                    case "1":
                        listStock();

                        System.out.println("1. To check all balances.");
                        System.out.println("2. To check a current stock.");
                        System.out.println("3. To quit.");
                        break;
                    case "2":
                        checkStock();

                        System.out.println("1. To check all balances.");
                        System.out.println("2. To check a current stock.");
                        System.out.println("3. To quit.");
                        break;
                    case "3":
                        System.out.println("Program quit.");
                        return;
                    default:
                        System.out.println("Invalid input.");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void getCurrentPositions(){
        Iterable<Position> iter = positionService.listPositions();

        for (Position pos: iter) {
            System.out.println(pos);
        }
    }

    public void listStock() {
        getCurrentPositions();

        System.out.println("1. Sell a given stock.");
        System.out.println("2. To return to menu");
        String line ="";
        while (sc.hasNext()) {
            line = sc.next();
            switch (line) {
                case "1":

                    System.out.println("\nInput stock id to sell:");
                    int id = sc.nextInt();
                    positionService.sell(id);

                    System.out.println("Current positions:");
                    getCurrentPositions();

                    System.out.println("1. Sell a given stock.");
                    System.out.println("2. To return to menu");
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    public void checkStock() {
        String line ="";

        Quote quote  = getStock();
        if (quote == null){
            return;
        }

        System.out.println("1. Buy the given stock.");
        System.out.println("Enter anything else to return to menu");
        line = sc.next();
        switch (line) {
            case "1":
                System.out.println("Enter integer amount to buy: ");
                int amount = sc.nextInt();

                quoteService.storeQuoteData(quote);
                //quote.
                positionService.buy(quote, amount, quote.getPrice());

                System.out.println("Purchase finalized: ");
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }



    public Quote getStock(){
        String line = "";
        System.out.println("Input symbol to check. 1 to quit:");
        while (sc.hasNext()) {
            line = sc.next();

            switch (line) {
                case "1":
                    System.out.println("Exiting.");
                    return null;
                default:
                    Optional<Quote> quote = quoteService.fetchQuoteDataFromAPI(line.trim());
                    if (quote.isPresent())
                        System.out.println(quote.get());
                    return quote.get();
            }
        }
        return null;
    }

}

