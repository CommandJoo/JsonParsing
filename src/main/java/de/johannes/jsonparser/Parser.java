package de.johannes.jsonparser;

import de.johannes.json.JsonArray;
import de.johannes.json.JsonComponent;
import de.johannes.json.JsonObject;
import de.johannes.jsonparser.token.Token;
import de.johannes.jsonparser.token.TokenType;

import java.util.*;

public class Parser {

    private static JsonArray parseArray(LinkedList<Token> tokens) throws Exception {
        tokens.removeFirst();//remove [
        ArrayList<JsonComponent> content = new ArrayList<>();
        int count = 0;
        while(!tokens.isEmpty() && tokens.getFirst().type() != TokenType.BRACKET_CLOSE) {
            if(count > 0) expect(tokens, TokenType.COMMA);
            content.add(parse(tokens));
            count++;
        }
        tokens.removeFirst();//remove ]
        return new JsonArray(content);
    }

    private static JsonObject parseObject(LinkedList<Token> tokens) throws Exception{
        tokens.removeFirst();//remove {
        HashMap<String, JsonComponent> fields = new HashMap<>();
        int count = 0;
        while(!tokens.isEmpty() && tokens.getFirst().type() != TokenType.BRACE_CLOSE) {
            if(count > 0) expect(tokens, TokenType.COMMA);
            String identifier = expect(tokens, TokenType.TEXT, ", for JsonObject Entry").content();
            expect(tokens, TokenType.COLON);
            JsonComponent value = parse(tokens);
            fields.put(identifier, value);
            count++;
        }
        tokens.removeFirst();//remove }
        return new JsonObject(fields);
    }

    public static JsonComponent parse(LinkedList<Token> tokens) throws Exception {
        if (tokens.getFirst().type() == TokenType.BRACKET_OPEN) {
            return parseArray(tokens);
        } else if (tokens.getFirst().type() == TokenType.BRACE_OPEN) {
            return parseObject(tokens);
        } else if (tokens.getFirst().type() == TokenType.COMMA) {
            tokens.removeFirst();
            parse(tokens);
        } else if(tokens.getFirst().type() == TokenType.BOOLEAN) {
            return new JsonComponent(Boolean.valueOf(tokens.removeFirst().content()));
        } else if(tokens.getFirst().type() == TokenType.NUMBER) {
            return new JsonComponent(Float.valueOf(tokens.removeFirst().content()));
        }  else if(tokens.getFirst().type() == TokenType.TEXT) {
            return new JsonComponent(tokens.removeFirst().content());
        } else if(tokens.getFirst().type() == TokenType.NULL) {
            return new JsonComponent(null);
        }else {
            throw new Exception("Couldn't find Type: "+tokens.getFirst().type());
        }
        return null;
    }

    private static Token expect(LinkedList<Token> tokens, TokenType type, String message) {
        if(tokens.getFirst().type() == type) {
            return tokens.removeFirst();
        }else{
            throw new IllegalArgumentException("Expected Token of type: "+type.name()+" but got: "+tokens.getFirst().type().name()+message);
        }
    }

    private static Token expect(LinkedList<Token> tokens, TokenType type) {
        if(tokens.getFirst().type() == type) {
            return tokens.removeFirst();
        }else{
            throw new IllegalArgumentException("Expected Token of type: "+type.name()+" but got: "+tokens.getFirst().type().name()+", content: "+tokens.getFirst().content());
        }
    }

}
