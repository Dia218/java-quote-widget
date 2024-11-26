import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuoteControllerTest {
    
    @Mock
    private QuoteView mockQuoteView;
    
    @Mock
    private QuoteService mockQuoteService;
    
    private QuoteController quoteController;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quoteController = new QuoteController(mockQuoteView, mockQuoteService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @Test
    void testRun_shouldRunCorrectly() throws InvalidCommandException {
        when(mockQuoteView.requestCommand()).thenReturn("등록", "종료");
        
        quoteController.run();
        
        verify(mockQuoteView).showTitle();
        verify(mockQuoteView, times(2)).requestCommand();
        verify(mockQuoteView).closeScanner();
    }
    
    @Test
    void testRun_shouldHandleInvalidCommand() throws InvalidCommandException {
        when(mockQuoteView.requestCommand())
                .thenThrow(new InvalidCommandException("잘못된 명령어 입니다: InvalidCommand"))
                .thenReturn(Command.EXIT.getValue());
        
        quoteController.run();
        
        assertTrue(outputStreamCaptor.toString().contains("잘못된 명령어 입니다: InvalidCommand"));
    }

}