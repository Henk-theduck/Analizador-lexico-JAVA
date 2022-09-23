import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScannerA {
    private char[] content;
    private int state;
    private int position;

    public ScannerA(String filename){
        try{
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

            switch (state) {
                case 0:
                    if (isChar(currentChar)) {
                        term += currentChar;
                        state = 1;

                    } else if (isDigit(currentChar)) {
                        state = 3;
                        term += currentChar;

                    } else if (isSpace(currentChar)) {
                        state = 0;

                    } else if (isOperator(currentChar)) {
                        state = 5;
                    }else{
                        throw new LexicalException("Símbolo não reconhecido");
                    }
                    break;
                case 1:
                    if (isChar(currentChar) || isDigit(currentChar)) {
                        state = 1;
                        term += currentChar;
                    } else if(isSpace(currentChar) || isOperator(currentChar)){
                       state  = 2;
                    }else{
                        throw new LexicalException("Identificador mal formado");
                    }
                    break;
                case 2:
                    back();
                    token  = new Token();
                    token.setType(Token.IDENTIFIER);
                    token.setText(term);
                    return token;
                case 3:
                    if(isDigit(currentChar)){
                        state = 3;
                        term += currentChar;
                    } else if (!isChar(currentChar)) {
                        state = 4;
                    } else{
                        throw new LexicalException("Número não reconhecido");
                    }
                case 4:
                    token = new Token();
                    token.setType(Token.NUMBER);
                    token.setText(term);
                    back();
                    return token;
                case 5:
                    term += currentChar;
                    token = new Token();
                    token.setType(Token.OPERATOR);
                    token.setText(term);
                    back();
                    return token;
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
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private char nextChar(){
        return content[position++];
    }
    private boolean isEOF(){
        return position >= content.length;
    }

    private void back() {
        position--;
    }
}