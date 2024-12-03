package service;

import exception.QuoteNotFoundException;
import model.Quote;
import repository.QuoteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class QuoteService {
    private final QuoteRepository quoteRepository;
    
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }
    
    public int addQuote(String author, String content) {
        int id = getNextId();
        quoteRepository.insertQuote(new Quote(id, author, content));
        return id;
    }
    
    public void deleteQuote(Quote quote) {
        quoteRepository.deleteQuote(quote.getId());
    }
    
    public void updateQuote(Quote quote, String author, String content) {
        quoteRepository.updateQuote(quote, author, content);
    }
    
    public List<String> listQuotes() {
        return quoteRepository.selectAllQuotes().stream()
                .map(Quote::getInfo)
                .collect(Collectors.toList());
    }
    
    public void buildQuotes() {
        quoteRepository.commitQuotes();
    }
    
    public Quote getQuoteById(int targetId) {
        Quote quote = quoteRepository.selectQuoteById(targetId);
        if (quote == null) {
            throw new QuoteNotFoundException(targetId);
        }
        return quote;
    }
    
    private int getNextId() {
        return quoteRepository.getLastId() + 1;
    }
    
}