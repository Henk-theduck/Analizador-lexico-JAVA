package parser;
import scanner.DuckScanner;
import scanner.Token;

public class DuckParser {
    private DuckScanner scanner;
    private Token token;
    
    public DuckParser(DuckScanner scanner){
        this.scanner = scanner;
    }

    public void E(){
        T();
        El();
    }

    public void El(){
        token = scanner.nextToken();
		if (token != null) {
			OP();
			T();
			El();
		}
    }
    public void T(){
        token = scanner.nextToken();
		if (token.getType() != Token.IDENTIFIER && token.getType() != Token.NUMBER) {
			throw new SyntaxException("ID or NUMBER Expected!, found "+Token.TEXT[token.getType()]+" ("+token.getText()+") at LINE "+token.getLine()+" and COLUMN "+token.getColumn());
		}
    }
    public void OP() {
		if (token.getType() != Token.OPERATOR) {
			throw new SyntaxException("Operator Expected, found "+Token.TEXT[token.getType()]+" ("+token.getText()+")  at LINE "+token.getLine()+" and COLUMN "+token.getColumn());
		}
	}
}
