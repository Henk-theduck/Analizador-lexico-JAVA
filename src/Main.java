public class Main {
    public static void main(String[] args) {
        try {
            ScannerA scannerA = new ScannerA("src/input.txt");
            Token token;
            do {
                token = scannerA.nextToken();
                if (token != null) {
                    System.out.println(token.toString());
                }
            } while (token != null);
        }catch (LexicalException e){
            System.out.println("Lexical error: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Generic Error");
        }
    }
}