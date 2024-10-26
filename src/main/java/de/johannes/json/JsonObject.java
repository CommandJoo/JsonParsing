package de.johannes.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class JsonObject extends JsonComponent{

    public JsonObject(HashMap<String, JsonComponent> content) {
        super(content);
    }

    @Override
    public HashMap<String, JsonComponent> content() {
        HashMap<String, JsonComponent> map = new HashMap<>();
        if(!(super.content() instanceof HashMap<?,?>)) {
            throw new IllegalStateException("Expected HashMap of JsonObject but got: "+super.content().getClass().getSimpleName());
        }
        ((HashMap<?, ?>) super.content()).forEach((key, value) -> {
            if(!(key instanceof String) || !(value instanceof JsonComponent)) {
                throw new IllegalArgumentException("Expected key to be of type "+String.class+" and value to be of type: "+JsonComponent.class+"\nGot key: "+key.getClass()+" and value: "+value.getClass());
            }else{
                map.put((String) key, (JsonComponent) value);
            }
        });
        return map;
    }

    public JsonComponent get(String name) {
        return content().getOrDefault(name, null);
    }

    @Override
    public String stringify(String keyName, int level, boolean pretty, int indent) {
        StringBuilder str = new StringBuilder();
        //added necessary amount of space
        String indentation = " ".repeat(indent);
        String finalIndentation = pretty ? indentation.repeat(level) : "";
        str.append(finalIndentation);
        //added key if not null
        str.append((keyName != null) ? "\""+keyName+"\":" : "");
        str.append(pretty && keyName != null ? " " : "");//space after colon
        str.append("{");
        str.append(pretty ? "\n" : "");

        int count = 0;
        for(String key : content().keySet()) {
            count++;
            str.append(get(key).stringify(key, level+1, pretty, indent));
            str.append(count < content().keySet().size() ? "," : "");
            str.append(pretty ? "\n" : "");
        }
        str.append(finalIndentation);
        str.append("}");
        return str.toString();
    }
}
