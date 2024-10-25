package de.johannes;

import de.johannes.jsonparser.FileUtil;
import de.johannes.jsonparser.Lexer;
import de.johannes.jsonparser.token.Token;

import java.util.LinkedList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        LinkedList<Character> input = FileUtil.readFile("input.json");
        List<Token> tokens = Lexer.lexer(input);
        Lexer.listTokens(tokens);
    }
}