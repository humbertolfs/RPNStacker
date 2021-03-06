package Task02;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;

public class RPN {
    public static void main(String[] args) throws IOException {
        
        Pattern pattern = Pattern.compile("\\D+");

        Stack<Token> stack = new Stack<Token>();
        ArrayList<Token> list = new ArrayList<Token>();

        String ch;
        int a, b;
        Token tk;
        TokenType signal;

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("Task02/Calc1.stk");
            br = new BufferedReader(fr);
        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
        }

        while ((ch = br.readLine()) != null){

            Matcher matcher = pattern.matcher(ch);
            boolean matchFound = matcher.find();

            if (ch.equals("*")) {
                tk = new Token(TokenType.STAR, ch);
            } else if (ch.equals("+")) {
                tk = new Token(TokenType.PLUS, ch);
            } else if (ch.equals("-")) {
                tk = new Token(TokenType.MINUS, ch);
            } else if (ch.equals("/")) {
                tk = new Token(TokenType.SLASH, ch);
            } else if (matchFound) {
                tk = new Token(TokenType.EOF, ch);
            } else {
                tk = new Token(TokenType.NUM, ch);
            }

            list.add(tk);
        }

        fr.close();

        list.forEach(System.out::println); // Imprimir a lista de tokens reconhecida

        for (Token tokenAtual : list){

            stack.push(tokenAtual);

            if (stack.peek().type != TokenType.NUM && stack.peek().type != TokenType.EOF) {
                Token token = stack.pop();
                signal = token.type;
                ch = token.lexeme;
                
                a = Integer.parseInt(stack.pop().lexeme);
                b = Integer.parseInt(stack.pop().lexeme);

                if (signal == TokenType.STAR) {
                    stack.push(new Token(TokenType.NUM, Integer.toString(a * b)));
                } else if (signal == TokenType.PLUS) {
                    stack.push(new Token(TokenType.NUM, Integer.toString(a + b)));
                } else if (signal == TokenType.MINUS) {
                    stack.push(new Token(TokenType.NUM, Integer.toString(b - a)));
                } else if (signal == TokenType.SLASH) {
                    stack.push(new Token(TokenType.NUM, Integer.toString(b / a)));
                }
            } else if (stack.peek().type == TokenType.EOF) {
                System.out.println("Error: Unexpected character: " + stack.pop().lexeme);
                System.exit(1);
            }
        }
        
        System.out.println("Saida: " + stack.pop().lexeme);
        System.exit(0);
    }
}