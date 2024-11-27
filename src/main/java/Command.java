public enum Command {
    REGISTER("등록"),
    DELETE("삭제"),
    UPDATE("수정"),
    SELECT("목록"),
    EXIT("종료"),
    BUILD("빌드"),;
    
    private final String value;
    
    Command(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
