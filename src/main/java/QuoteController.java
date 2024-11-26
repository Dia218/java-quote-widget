public class QuoteController {
    private QuoteView quoteView;
    private QuoteService quoteService;
    
    public QuoteController(QuoteView quoteView, QuoteService quoteService) {
        this.quoteView = quoteView;
        this.quoteService = quoteService;
    }
    
    public void run() {
        quoteView.showTitle();
        
        while (true) {
            String command = getCommandFromUser();
            if (command != null && !processCommand(command)) {
                break;
            }
        }
        
        quoteView.closeScanner();
    }
    
    private String getCommandFromUser() {
        try {
            return quoteView.requestCommand();
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private boolean processCommand(String command) {
        if (command.equals(Command.EXIT.getValue())) {
            return false;
        }
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
        return true;
    }
    
    private void handleRegister() {
        // 등록 처리
    }
    
    private void handleDelete() {
        // 삭제 처리
    }
    
    private void handleUpdate() {
        // 수정 처리
    }
    
    private void handleSelect() {
        // 목록 처리
    }
}