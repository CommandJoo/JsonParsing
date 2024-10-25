package de.johannes;

import de.johannes.json.JsonComponent;
import de.johannes.jsonparser.FileUtil;
import de.johannes.jsonparser.Lexer;
import de.johannes.jsonparser.Parser;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;

public class JsonParser {

    public static LinkedList<Character> toTokenList(String input) {
        LinkedList<Character> result = new LinkedList<>();
        for (char c : input.toCharArray()) {
            result.add(c);
        }
        return result;
    }

    public static void prettyPrint(String json) throws Exception {
        System.out.println(Parser.parse(Lexer.lexer(toTokenList(json))));
    }
    public static JsonComponent parse(String json) throws Exception {
        return Parser.parse(Lexer.lexer(toTokenList(json)));
    }
    public static JsonComponent parseFile(String file) throws Exception {
        return Parser.parse(Lexer.lexer(toTokenList(FileUtil.readFile(file))));
    }
}
