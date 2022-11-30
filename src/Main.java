import parser.DuckParser;
import parser.SyntaxException;
import scanner.DuckScanner;
import scanner.LexicalException;
import scanner.Token;

public class Main {
    public static void main(String[] args) {
        try {
            DuckScanner duckScanner = new DuckScanner("input.duck");
            DuckParser duckParser = new DuckParser(duckScanner);
            duckParser.E();
            System.out.println("Compilação bem sucedida!");

        }catch (LexicalException e){
            System.out.println("Lexical Error: " + e.getMessage());
        }catch (SyntaxException e){
            System.out.println("Syntax Error: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Generic Error");
            System.out.println(e.getClass().getName());
        }
    }
}