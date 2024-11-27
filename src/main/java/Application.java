public class Application {
    public static void main(String[] args) {
        QuoteView quoteView = new QuoteView();
        QuoteRepository quoteRepository = new QuoteRepository();
        QuoteService quoteService = new QuoteService(quoteRepository);
        QuoteController controller = new QuoteController(quoteView, quoteService);
        
        controller.run();
    }
}