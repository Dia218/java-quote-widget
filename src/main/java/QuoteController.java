import java.util.Arrays;

public class QuoteController {
    private final QuoteView quoteView;
    private final QuoteService quoteService;
    
    public QuoteController(QuoteView quoteView, QuoteService quoteService) {
        this.quoteView = quoteView;
        this.quoteService = quoteService;
    }
    
    public void run() {
        quoteView.showTitle();
        
        while (true) {
            String command = quoteView.requestCommand().trim();
            
            try {
                validateCommand(command);
            } catch (InvalidCommandException e) {
                quoteView.displayErrorMessage(e.getMessage());
                continue;
            }
            
            if(isExit(command)) { break; }
            processCommand(command);
        }
        
        quoteView.closeScanner();
    }
    
    private void validateCommand(String targetCommand) throws InvalidCommandException {
        if (Arrays.stream(Command.values())
                .map(Command::getValue)
                .noneMatch(value -> value.equals(targetCommand))) {
            throw new InvalidCommandException(targetCommand);
        }
    }
    
    private boolean isExit(String command) {
        return command.equals(Command.EXIT.getValue());
    }
    
    private void processCommand(String command) {
        if (command.equals(Command.REGISTER.getValue())) {
            handleRegister();
        }
        if (command.equals(Command.DELETE.getValue())) {
            handleDelete();
        }
        if (command.equals(Command.UPDATE.getValue())) {
            handleUpdate();
        }
        if (command.equals(Command.SELECT.getValue())) {
            handleSelect();
        }
        if (command.equals(Command.BUILD.getValue())) {
            handleBuild();
        }
    }
    
    private void handleRegister() {
        String[] ContentAndAuthor = quoteView.requestRegister();
        int newId = quoteService.addQuote(ContentAndAuthor[1], ContentAndAuthor[0]);
        quoteView.alertSuccess(newId, Command.REGISTER);
    }
    
    private void handleDelete() {
        try {
            int targetId = Integer.parseInt(quoteView.requestTargetId(Command.DELETE));
            quoteService.removeQuote(quoteService.getQuoteById(targetId));
            quoteView.alertSuccess(targetId, Command.DELETE);
        } catch (NumberFormatException e) {
            quoteView.displayErrorMessage("숫자를 정확히 입력해주세요");
        } catch (QuoteNotFoundException e) {
            quoteView.displayErrorMessage(e.getMessage());
        }
    }
    
    private void handleUpdate() {
        try {
            int targetId = Integer.parseInt(quoteView.requestTargetId(Command.UPDATE));
            Quote targetQuote = quoteService.getQuoteById(targetId);
            String[] newContentAndAuthor = quoteView.requestUpdate(targetQuote.getContentAndAuthor());
            quoteService.updateQuote(targetQuote, newContentAndAuthor[1], newContentAndAuthor[0]);
        } catch (NumberFormatException e) {
            quoteView.displayErrorMessage("숫자를 정확히 입력해주세요");
        } catch (QuoteNotFoundException e) {
            quoteView.displayErrorMessage(e.getMessage());
        }
    }
    
    private void handleSelect() {
        quoteView.displayQuotes(quoteService.selectQuote().reversed());
    }
    
    private void handleBuild() {
        // 빌드 처리
    }
}