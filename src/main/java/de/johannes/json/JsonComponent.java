package de.johannes.json;

import de.johannes.JsonParser;

import java.awt.*;
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
        return stringify(null, 0, JsonParser.instance().isPretty(), JsonParser.instance().indent(), JsonParser.instance().isColored());
    }

    public String stringify(String key, int level, boolean pretty, int indent, boolean colored) {
        StringBuilder str = new StringBuilder();
        //added the necessary amount of spaces
        String indentation = " ".repeat(indent);
        str.append(pretty ? indentation.repeat(level) : "");
        //add the key if not null
        str.append(consoleColor(new Color(0,255, 50),colored));
        str.append(key != null ? "\""+key+"\":" : "");
        str.append(pretty ? " " : "");//space after colon
        //added the content
        if(content() instanceof Float) {
            str.append(consoleColor(new Color(255,0, 150),colored));
        }else if(content() instanceof String) {
            str.append(consoleColor(new Color(255,120, 100),colored));
        }else if(content() instanceof Boolean) {
            str.append(consoleColor(new Color(255,200, 50),colored));
        }else {
            str.append(consoleColor(new Color(0,255, 200),colored));
        }
        str.append((content instanceof String ? "\""+content+"\"" : content.toString()));

        return str.toString();
    }

    protected String consoleColor(Color color, boolean colored) {
        if(colored) {
            return "\u001b[38;2;"+color.getRed()+";"+color.getGreen()+";"+color.getBlue()+"m";
        }else {
            return "";
        }
    }

}
