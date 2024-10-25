package de.johannes.jsonparser.token;


public class Token {

    private TokenType type;
    private String content;


    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public TokenType type() {
        return type;
    }

    public String content() {
        return content;
    }

    @Override
    public String toString() {
        return "Type: "+type.toString()+", Content: "+content;
    }
}
