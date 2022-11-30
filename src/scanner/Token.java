package scanner;
public class Token {
    public static final int IDENTIFIER = 0;
    public static final int NUMBER = 1;
    public static final int OPERATOR = 2;
    public static final int PONCTUATION = 3;
    public static final int ASSIGN = 4;

    public static final String TEXT[] = {
			"IDENTIFIER", "NUMBER", "OPERATOR", "PONCTUACTION", "ASSIGNMENT"
	};

    private int type;
    private String text;
    private int line;
    private int column;

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

    public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

    @Override
	public String toString() {
		return "Token [tipo=" + type + ", texto=" + text + "]";
	}
    

}
