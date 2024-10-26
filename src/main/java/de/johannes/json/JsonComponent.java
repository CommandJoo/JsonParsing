package de.johannes.json;

import de.johannes.JsonParser;

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

    public JsonArray asArray() {
        if(this.content() == null) return null;
        if(this instanceof JsonArray) {
            return ((JsonArray) this);
        } else throw new IllegalArgumentException("This JsonComponent is not of type JsonArray");
    }

    public JsonObject asObject() {
        if(this.content() == null) return null;
        if(this instanceof JsonObject) {
            return ((JsonObject) this);
        } else throw new IllegalArgumentException("This JsonComponent is not of type JsonObject");
    }

    public Float asNumber() {
        if(this.content() == null) return null;
        if(this.content() instanceof Float) {
            return ((Float) this.content());
        }else throw new IllegalArgumentException("This JsonComponent is not of type Number");
    }

    public String asString() {
        if(this.content() == null) return null;
        if(this.content() instanceof String) {
            return ((String) this.content());
        }else throw new IllegalArgumentException("This JsonComponent is not of type String");
    }

    public Boolean asBoolean() {
        if(this.content() == null) throw new IllegalArgumentException("This JsonComponent has a value of null");
        if(this.content() instanceof Boolean) {
            return ((Boolean) this.content());
        }else throw new IllegalArgumentException("This JsonComponent is not of type Boolean");
    }

    @Override
    public String toString() {
        return stringify(null, 0, JsonParser.instance().isPretty(), JsonParser.instance().indent());
    }

    public String stringify(String key, int level, boolean pretty, int indent) {
        StringBuilder str = new StringBuilder();
        //added the necessary amount of spaces
        String indentation = " ".repeat(indent);
        str.append(pretty ? indentation.repeat(level) : "");
        //add the key if not null
        str.append(key != null ? "\""+key+"\":" : "");
        str.append(pretty ? " " : "");//space after colon
        //added the content
        str.append((content instanceof String ? "\""+content+"\"" : content.toString()));

        return str.toString();
    }
}
