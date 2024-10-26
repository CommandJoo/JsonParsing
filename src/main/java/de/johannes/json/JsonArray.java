package de.johannes.json;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonComponent{

    public JsonArray(ArrayList<JsonComponent> content) {
        super(content);
    }

    @Override
    public List<JsonComponent> content() {
        List<JsonComponent> list = new ArrayList<>();
        if(!(super.content() instanceof ArrayList<?>)) {
            throw new IllegalStateException("Expected List of JsonArray but got: "+super.content().getClass().getSimpleName());
        }
        ArrayList<?> contents = ((ArrayList<?>) super.content());
        ((ArrayList<?>) super.content()).forEach(entry -> {
            if(!(entry instanceof JsonComponent) && entry != null) {
                throw new IllegalArgumentException("Expected content of JsonArray to be of type JsonComponent but got: "+entry.getClass().getSimpleName());
            }else {
                list.add(((JsonComponent) entry));
            }
        });
        return list;
    }

    public JsonComponent get(int i) {
        if(i < content().size()) {
            return content().get(i);
        }else {
            throw new IndexOutOfBoundsException("Size of JsonArray is: "+content().size()+", given index: "+i);
        }
    }

    @Override
    public String stringify(String key, int level, boolean pretty, int indent, boolean colored) {
        StringBuilder str = new StringBuilder();
        //added necessary amount of spaces
        String indentation = " ".repeat(indent);
        String finalIndentation = pretty ? indentation.repeat(level) : "";
        str.append(finalIndentation);
        //added key if not null
        str.append(consoleColor(new Color(255, 50, 0),colored));
        str.append(key != null ? "\"" + key + "\":" : "");
        str.append(pretty && key != null ? " " : "");//space after colon
        str.append(consoleColor(new Color(255, 150, 0),colored));
        str.append("[");
        str.append(pretty ? "\n" : "");

        int count = 0;
        for (JsonComponent entry : content()) {
            count++;
            str.append(entry != null ?
                    entry.stringify(null, level + 1, pretty, indent, colored) :
                    "  ".repeat(level + 1) + "Invalid"
            );
            str.append(consoleColor(new Color(255, 150, 0),colored));
            str.append(count < content().size() ? "," : "");
            str.append(pretty ? "\n" : "");
        }
        str.append(finalIndentation);
        str.append(consoleColor(new Color(255, 150, 0),colored));
        str.append("]");
        return str.toString();
    }

}
