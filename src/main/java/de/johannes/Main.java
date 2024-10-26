package de.johannes;

import de.johannes.json.JsonComponent;
import de.johannes.jsonparser.FileUtil;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        OptionParser parser = new OptionParser() {
            {
                acceptsAll(Arrays.asList("help", "h", "?"), "Shows the help menu").forHelp();
                accepts("file", "The input file").requiredUnless("help").withRequiredArg().ofType(File.class).describedAs("f");
                accepts("pretty", "If the json should be formatted");
                accepts("indent", "The Indentation of the formatted Json").withRequiredArg().ofType(Integer.class);
                accepts("color", "If the json should be color coded");
            }
        };
        OptionSet options = parser.parse(args);
        if(options.has("help")) {
            parser.printHelpOn(System.out);
        }else {
            JsonParser jsonParser = new JsonParser(options.has("indent") ? (Integer)options.valueOf("indent") : 4, options.has("pretty"), options.has("color"));
            if(options.has("file")) {
                jsonParser.printFile(((File)options.valueOf("file")).getAbsolutePath());
            }else{
                System.out.println("No file Specified!");
            }
        }

    }
}