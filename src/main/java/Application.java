import controller.QuoteController;
import repository.MapQuoteRepository;
import repository.QuoteRepository;
import service.QuoteService;
import view.QuoteView;

public class Application {
    public static void main(String[] args) {
        QuoteView quoteView = new QuoteView();
        QuoteRepository quoteRepository = new MapQuoteRepository();
        QuoteService quoteService = new QuoteService(quoteRepository);
        QuoteController controller = new QuoteController(quoteView, quoteService);
        
        controller.run();
    }
}