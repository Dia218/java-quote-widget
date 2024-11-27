public class QuoteNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "%d번 명언은 존재하지 않습니다.";
    private final int quoteId;
    
    public QuoteNotFoundException(int quoteId) {
        super(String.format(ERROR_MESSAGE, quoteId));
        this.quoteId = quoteId;
    }
    
    public int getQuoteId() {
        return quoteId;
    }
}
