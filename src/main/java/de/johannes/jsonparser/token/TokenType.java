package de.johannes.jsonparser.token;

public enum TokenType {

    BRACE_OPEN, BRACE_CLOSE,
    BRACKET_OPEN, BRACKET_CLOSE,
    COMMA, COLON,

    TEXT, NUMBER, BOOLEAN, NULL,

    END_OF_FILE

}
