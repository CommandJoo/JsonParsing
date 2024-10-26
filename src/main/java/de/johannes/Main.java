package de.johannes;

import de.johannes.json.JsonComponent;
import de.johannes.jsonparser.FileUtil;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        OptionParser parser = new OptionParser();
        OptionSpec<File> file2 = parser.accepts("f").withRequiredArg().ofType(File.class);
        OptionSpec<Void> pretty = parser.accepts("pretty");
        OptionSet options = parser.parse(args);

        JsonParser jsonParser = new JsonParser(4, options.has(pretty));
        if(options.has(file2)) {
            jsonParser.printFile(options.valueOf(file2).getAbsolutePath());
        }else{
            System.out.println("No file Specified!");
        }
    }
}