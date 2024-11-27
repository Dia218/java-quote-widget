public class InvalidCommandException extends Exception {
    private static final String ERROR_MESSAGE = "잘못된 명령어 입니다: ";
    private final String errorCommand;
    
    public InvalidCommandException(String errorCommand) {
        super(String.format(ERROR_MESSAGE + errorCommand));
        this.errorCommand = errorCommand;
    }
    
    public String getErrorCommand() {
        return errorCommand;
    }
}
