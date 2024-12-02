import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class QuoteServiceTest {
    
    private QuoteRepository mockRepository;
    private QuoteService quoteService;
    
    String content1 = "테스트 명언1";
    String author1 = "테스트 작가명1";
    String content2 = "테스트 명언2";
    String author2 = "테스트 작가명2";
    
    @BeforeEach
    void setUp() {
        mockRepository = mock(QuoteRepository.class);
        quoteService = new QuoteService(mockRepository);
    }
    
    @Test
    void testAddQuote() {
        int actualId1 = quoteService.addQuote(author1, content1);
        int actualId2 = quoteService.addQuote(author2, content2);
        
        assertEquals(1, actualId1);
        assertEquals(2, actualId2);
    }
    
    @Test
    void testRemoveQuote() {
        int firstId = 1;
        
        quoteService.addQuote(author1, content1);
        quoteService.removeQuote(quoteService.getQuoteById(firstId));
        
        assertThrows(QuoteNotFoundException.class, () -> quoteService.getQuoteById(firstId));
    }
    
    @Test
    void testUpdateQuote() {
        String expectedInfo = 1 + " / " + author2 + " / " + content2;
        
        Quote actualQuote = quoteService.getQuoteById(quoteService.addQuote(author1, content1));
        quoteService.updateQuote(actualQuote, author2, content2);
        
        assertEquals(expectedInfo, actualQuote.getInfo());
    }
    
    @Test
    void testSelectQuote() {
        quoteService.addQuote(author1, content1);
        quoteService.addQuote(author2, content2);
        
        List<String> quotesInfo = quoteService.selectQuote();
        
        assertEquals(2, quotesInfo.size());
        assertEquals(quotesInfo.get(0), (1 + " / " + author1 + " / " + content1));
        assertEquals(quotesInfo.get(1), (2 + " / " + author2 + " / " + content2));
    }
    
    @Test
    void testSaveQuotes() {
    }
    
    @Test
    void testGetQuoteById() {
        String expectedInfo = 2 + " / " + author2 + " / " + content2;
        
        quoteService.addQuote(author1, content1);
        quoteService.addQuote(author2, content2);
        
        assertEquals(quoteService.getQuoteById(2).getInfo(), expectedInfo);
    }

}