import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuoteService {
    private final QuoteRepository quoteRepository;
    private Map<Integer, Quote> quotes;
    
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
        this.quotes = new LinkedHashMap<>();
        //this.quotes = quoteRepository.readQuotesFile();
    }
    
    public int addQuote(String content, String author) {
        int id = getNextId();
        quotes.put(id, new Quote(id, author, content));
        return id;
    }
    
    public void removeQuote(Quote quote) {
        quotes.remove(quote.getId());
    }
    
    public void updateQuote(Quote quote, String author, String content) {
        quote.update(author, content);
    }
    
    public List<String> selectQuote() {
        return quotes.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getKey() - entry1.getKey()) //역순 정렬
                .map(entry -> entry.getValue().getInfo())
                .collect(Collectors.toList());
    }
    
    public void saveQuotes() {
        //json 파일 저장
    }
    
    public Quote getQuoteById(int targetId) {
        return findQuoteById(targetId);
    }
    
    private int getNextId() {
        return getLastId() + 1;
    }
    
    private int getLastId() {
        return quotes.keySet()
                .stream()
                .max(Integer::compare)
                .orElse(0);
    }
    
    public Quote findQuoteById(int targetId) {
        if(!quotes.containsKey(targetId)) {
            throw new QuoteNotFoundException(targetId);
        }
        return quotes.get(targetId);
    }
}