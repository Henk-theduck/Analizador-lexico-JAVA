public class Token {
    public static final int IDENTIFIER = 0;
    public static final int NUMBER = 1;
    public static final int OPERATOR = 2;
    public static final int PUNCTUATION = 3;
    public static final int ASSIGNMENT = 4;


    private int type;
    private String text;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public Token(){
        super();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



}
