package repository;

import model.Quote;

import java.util.List;

public interface QuoteRepository {
    abstract void insertQuote(Quote quote);
    
    public void deleteQuote(int quoteId);
    
    public void updateQuote(Quote quote, String author, String content);
    
    public List<Quote> selectAllQuotes();
    
    public Quote selectQuoteById(int quoteId);
    
    public void commitQuotes();
    
    public int getLastId();
}
