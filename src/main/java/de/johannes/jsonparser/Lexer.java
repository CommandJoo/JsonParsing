package de.johannes.jsonparser;

import de.johannes.jsonparser.token.Token;
import de.johannes.jsonparser.token.TokenType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lexer {

    public static List<Token> lexer(LinkedList<Character> input) {
        List<Token> result = new ArrayList<>();

        while(!input.isEmpty()) {
            Character first = input.getFirst();
            if(Character.isWhitespace(first)) {
                input.removeFirst();
            } else if(first == '{') {
                result.add(new Token(TokenType.BRACE_OPEN, null));
                input.removeFirst();
            }else if(first == '}') {
                result.add(new Token(TokenType.BRACE_CLOSE, null));
                input.removeFirst();
            }else if(first == '[') {
                result.add(new Token(TokenType.BRACKET_OPEN, null));
                input.removeFirst();
            }else if(first == ']') {
                result.add(new Token(TokenType.BRACKET_CLOSE, null));
                input.removeFirst();
            }
            else if(first == ',') {
                result.add(new Token(TokenType.COMMA, null));
                input.removeFirst();
            }else if(first == ':') {
                result.add(new Token(TokenType.COLON, null));
                input.removeFirst();
            }
            else if(first == '"') {
                input.removeFirst();
                StringBuilder content = new StringBuilder();
                while(!input.isEmpty() && input.getFirst() != '"') {
                    content.append((char)input.removeFirst());
                }
                input.removeFirst();
                result.add(new Token(TokenType.TEXT, content.toString()));
            }
            else if(Character.isDigit(first)) {
                int dotCount = 0;
                StringBuilder content = new StringBuilder().append((char)input.removeFirst());
                while(!input.isEmpty() && (Character.isDigit(input.getFirst()) || (dotCount < 1 && input.getFirst()=='.'))) {
                    if(input.getFirst()=='.') {
                        content.append((char)input.removeFirst());
                        dotCount++;
                    }else {
                        content.append((char)input.removeFirst());
                    }
                }
                result.add(new Token(TokenType.NUMBER, content.toString()));
            }
            else{
                if(Character.isAlphabetic(first)) {
                    StringBuilder wordBuilder = new StringBuilder().append((char)input.removeFirst());
                    while(!input.isEmpty() && Character.isAlphabetic(input.getFirst())) {
                        wordBuilder.append((char)input.removeFirst());
                    }
                    String word = wordBuilder.toString();
                    //handle keywords
                    if(word.equals("true")) {
                        result.add(new Token(TokenType.BOOLEAN, "true"));
                    }else if(word.equals("false")) {
                        result.add(new Token(TokenType.BOOLEAN, "false"));
                    }else{
                        continue;
                    }
                }
                input.removeFirst();
            }
        }
        result.add(new Token(TokenType.END_OF_FILE, null));

        return result;
    }

    public static void listTokens(List<Token> tokens) {
        tokens.forEach(System.out::println);
    }

}
