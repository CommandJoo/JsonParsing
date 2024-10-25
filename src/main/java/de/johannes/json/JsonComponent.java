package de.johannes.json;

import java.util.List;

public class JsonComponent {

    private Object content;

    public JsonComponent(Object content) {
        this.content = content;
    }

    public Object content() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return stringify(null, 0);
    }

    public String stringify(String key, int indent) {
        return "  ".repeat(indent)+(key != null ? "\""+key+"\": " : "")+(content instanceof String ? "\""+content+"\"" : content.toString());
    }
}
