package de.johannes;

import de.johannes.json.JsonComponent;
import de.johannes.jsonparser.FileUtil;
import de.johannes.jsonparser.Lexer;
import de.johannes.jsonparser.Parser;

import java.util.LinkedList;

public class JsonParser {

    private int indent = 2;
    private boolean pretty = false;

    private static JsonParser instance;

    public JsonParser(int indent, boolean pretty) {
        if(instance() != null) {
            throw new IllegalStateException("Can only initialize one JsonParser per program, please use JsonParser.instance()");
        }
        instance = this;
        this.indent = indent;
        this.pretty = pretty;
    }

    public static LinkedList<Character> toTokenList(String input) {
        LinkedList<Character> result = new LinkedList<>();
        for (char c : input.toCharArray()) {
            result.add(c);
        }
        return result;
    }

    public void print(String json) throws Exception {
        String result = parse(json).stringify(null, 0, isPretty(), indent());
        System.out.println(result);
    }
    public void printFile(String file) throws Exception {
        String result = parseFile(file).stringify(null, 0, isPretty(), indent());
        System.out.println(result);
    }
    public JsonComponent parse(String json) throws Exception {
        return Parser.parse(Lexer.lexer(toTokenList(json)));
    }
    public JsonComponent parseFile(String file) throws Exception {
        return Parser.parse(Lexer.lexer(toTokenList(FileUtil.readFile(file))));
    }

    public int indent() {
        return indent;
    }

    public boolean isPretty() {
        return pretty;
    }

    public static JsonParser instance() {
        return instance;
    }
}
