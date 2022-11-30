package scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DuckScanner {
    private char[] content;
    private int state;
    private int position;
    private int line;
    private int column;

    public DuckScanner(String filename){
        try{
            line = 1;
            column = 0;
            String textContent;
            textContent = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            content = textContent.toCharArray();
            position = 0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Token nextToken() {
        char currentChar;
        Token token;
        String term = "";
        if (isEOF()) {
            return null;
        }
        state = 0;
        while (true) {
            currentChar = nextChar();
            column++;

            switch (state) {
                case 0:
                    if (isChar(currentChar)) {
                        term += currentChar;
                        state = 1;

                    } else if (isDigit(currentChar)) {
                        state = 2;
                        term += currentChar;

                    } else if (isSpace(currentChar)) {
                        state = 0;
                    } else if (isOperator(currentChar)) {
                        term += currentChar;
                        token = new Token();
                        token.setType(Token.OPERATOR);
                        token.setText(term);
                        token.setLine(line);
                        token.setColumn(column - term.length());
                        return token;
                    }else{
                        throw new LexicalException("Símbolo não reconhecido");
                    }
                    break;
                case 1:
                    if (isChar(currentChar) || isDigit(currentChar)) {
                        state = 1;
                        term += currentChar;
                    } else if(isSpace(currentChar) || isOperator(currentChar) || isEOF(currentChar)){
                       if(!isEOF(currentChar)) back();
                       token = new Token();
                       token.setType(Token.IDENTIFIER);
                       token.setText(term);
                       token.setLine(line);
                       token.setColumn(column - term.length());
                       return token;
                    }else{
                        throw new LexicalException("Identificador mal formado");
                    }
                    break;
                case 2:

                if(isDigit(currentChar) || currentChar == '.'){
                    state = 2;
                    term += currentChar;
                } else if (!isChar(currentChar) || isEOF(currentChar)){
                    if(!isEOF(currentChar)) back();
                    token = new Token();
                    token.setType(Token.NUMBER);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    return token;
                } else{
                    throw new LexicalException("Identificador mal formado");
                }
                break;
            }
        }
    }

    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    private boolean isChar(char c){
        return (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z');
    }

    private boolean isOperator(char c){
        return c == '>' || c == '<' || c == '=' || c == '!' || c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isSpace(char c){
        
        if (c == '\n' || c== '\r') {
			line++;
			column=0;
		}
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private char nextChar(){
        if (isEOF()) {
			return '\0';
		}
        return content[position++];
    }
    private boolean isEOF(){
        return position >= content.length;
    }

    private void back() {
        position--;
        column--;
    }

    private boolean isEOF(char c) {
    	return c == '\0';
    }
}