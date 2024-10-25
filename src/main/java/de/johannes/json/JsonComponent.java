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
        return stringify(null, 0);
    }

    public String stringify(String key, int indent) {
        return "  ".repeat(indent)+(key != null ? "\""+key+"\": " : "")+(content instanceof String ? "\""+content+"\"" : content.toString());
    }
}
