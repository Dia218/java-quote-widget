import java.util.Scanner;

public class QuoteView {
    private final String TITLE = "== 명언 앱 ==\n";
    private final String COMMAND = "명령) ";
    private final String COMPLETE = "%d번 명언이 %s되었습니다.\n";
    private final String ERROR_COMMAND = "잘못된 명령어 입니다: ";
    private final String ERROR_QUOTENUM = "%d번 명언은 존재하지 않습니다.";

    private final Scanner scanner;

    public QuoteView() {
        scanner = new Scanner(System.in);
    }

    public void showTitle() {
        printMessage(TITLE);
    }

    public String requestCommand() throws InvalidCommandException {
        String command = "";
        
        printMessage(COMMAND);
        command = scanner.nextLine().trim();
        
        if (!isValidCommand(command)) {
            throw new InvalidCommandException(ERROR_COMMAND + command);
        }
        
        return command;
    }
    
    public void closeScanner() {
        scanner.close();
    }
    
    private boolean isValidCommand(String command) {
        return command.equals(Command.REGISTER.getValue()) ||
                command.equals(Command.DELETE.getValue()) ||
                command.equals(Command.UPDATE.getValue()) ||
                command.equals(Command.SELECT.getValue()) ||
                command.equals(Command.EXIT.getValue());
    }
    
    private void printMessage(String message) {
        System.out.print(message);
    }
}
