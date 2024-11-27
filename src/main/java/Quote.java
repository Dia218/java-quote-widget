public class Quote {
    private final int id;
    private String author;
    private String content;
    
    public Quote(int id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }
    
    public int getId() {
        return id;
    }
    
    public void update(String newAuthor, String newContent) {
        this.author = newAuthor;
        this.content = newContent;
    }
    
    public String[] getContentAndAuthor() {
        return new String[]{this.content, this.author};
    }
    
    public String getInfo() {
        return id + " / " + author + " / " + content;
    }
    
}
