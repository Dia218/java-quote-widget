package repository;

import model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuoteRepositoryTest {
    private static final String AUTHOR_1 = "작가명1";
    private static final String QUOTE_CONTENT_1 = "명언내용1";
    private static final String AUTHOR_2 = "작가명2";
    private static final String QUOTE_CONTENT_2 = "명언내용2";
    private static final int QUOTE_ID_1 = 1;
    private static final int QUOTE_ID_2 = 2;
    
    QuoteRepository quoteRepository;
    
    @BeforeEach
    public void setUp() {
        quoteRepository = new MapQuoteRepository();
    }
    
    @Test
    void testInsertQuote() {
        Quote expectedQuote1 = new Quote(QUOTE_ID_1, AUTHOR_1, QUOTE_CONTENT_1);
        Quote expectedQuote2 = new Quote(QUOTE_ID_2, AUTHOR_2, QUOTE_CONTENT_2);
        
        quoteRepository.insertQuote(expectedQuote1);
        quoteRepository.insertQuote(expectedQuote2);
        Quote actualQuote1 = quoteRepository.selectQuoteById(QUOTE_ID_1);
        Quote actualQuote2 = quoteRepository.selectQuoteById(QUOTE_ID_2);
        
        assertNotNull(actualQuote1, "1번 저장 안됨");
        assertEquals(expectedQuote1.getInfo(), actualQuote1.getInfo(), "1번 명언 객체가 다름");
        assertNotNull(actualQuote2, "2번 저장 안됨");
        assertEquals(expectedQuote2.getInfo(), actualQuote2.getInfo(), "2번 명언 객체가 다름");
    }
    
    @Test
    void testDeleteQuote() {
        Quote quote = new Quote(QUOTE_ID_1, AUTHOR_1, QUOTE_CONTENT_1);
        quoteRepository.insertQuote(quote);
        quoteRepository.deleteQuote(QUOTE_ID_1);
        
        Quote deletedQuote = quoteRepository.selectQuoteById(QUOTE_ID_1);
        
        assertNull(deletedQuote, "삭제되지 않음");
    }
    
    @Test
    void testUpdateQuote() {
        Quote quote = new Quote(QUOTE_ID_1, AUTHOR_1, QUOTE_CONTENT_1);
        quoteRepository.insertQuote(quote);
        
        String newAuthor = "Updated Author";
        String newContent = "Updated content";
        quoteRepository.updateQuote(quote, newAuthor, newContent);
        Quote updatedQuote = quoteRepository.selectQuoteById(QUOTE_ID_1);
        String expectedQuoteInfo = QUOTE_ID_1 + " / " + newAuthor + " / " + newContent;
        
        assertNotNull(updatedQuote, "객체를 찾을 수 없음");
        assertEquals(expectedQuoteInfo, updatedQuote.getInfo(), "업데이트 정보가 잘못됨");
    }
    
    @Test
    void testSelectAllQuotes() {
        quoteRepository.insertQuote(new Quote(QUOTE_ID_1, AUTHOR_1, QUOTE_CONTENT_1));
        quoteRepository.insertQuote(new Quote(QUOTE_ID_2, AUTHOR_2, QUOTE_CONTENT_2));
        
        List<Quote> allQuotes = quoteRepository.selectAllQuotes();
        
        assertNotNull(allQuotes, "목록이 비어있음");
        assertEquals(2, allQuotes.size(), "저장된 개수가 맞지 않음");
        assertEquals(QUOTE_ID_1, allQuotes.get(0).getId(), "1번 객체가 잘못됨");
        assertEquals(QUOTE_ID_2, allQuotes.get(1).getId(), "2번 객체가 잘못됨");
    }
    
    @Test
    void testSelectQuoteById() {
        quoteRepository.insertQuote(new Quote(QUOTE_ID_1, AUTHOR_1, QUOTE_CONTENT_1));
        
        Quote quote = quoteRepository.selectQuoteById(QUOTE_ID_1);
        
        assertNotNull(quote, "찾지 못함");
        assertEquals(QUOTE_ID_1, quote.getId(), "Id가 일치하지 않음");
        Quote nonExistentQuote = quoteRepository.selectQuoteById(999);
        assertNull(nonExistentQuote, "존재하지 않은 객체 리턴");
    }
    
    @Test
    void testCommitQuotes() {
    }
    
    @Test
    void testGetLastId() {
    }
}