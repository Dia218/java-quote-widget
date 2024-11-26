public class Application {
    public static void main(String[] args) {
        QuoteView quoteView = new QuoteView();
        QuoteService quoteService = new QuoteService();
        QuoteController controller = new QuoteController(quoteView, quoteService);
        
        controller.run();
    }
}