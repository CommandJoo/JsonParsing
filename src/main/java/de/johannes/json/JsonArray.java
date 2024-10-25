package de.johannes.json;

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
        System.out.println(contents.getClass());
        ((ArrayList<?>) super.content()).forEach(entry -> {
            if(!(entry instanceof JsonComponent) && entry != null) {
                throw new IllegalArgumentException("Expected content of JsonArray to be of type JsonComponent but got: "+entry.getClass().getSimpleName());
            }else {
                list.add(((JsonComponent) entry));
            }
        });
        return list;
    }

    @Override
    public String stringify(String key, int indent) {
        StringBuilder str = new StringBuilder();
        str.append("  ".repeat(indent)).append(key != null ? "\"" + key + "\": " : "").append("[\n");
        int count = 0;
        for (JsonComponent entry : content()) {
            count++;
            str.append(entry != null ? entry.stringify(null, indent + 1) : "  ".repeat(indent + 1) + "Invalid").append(count < content().size() ? "," : "").append("\n");
        }
        str.append("  ".repeat(indent)).append("]");
        return str.toString();
    }

}
