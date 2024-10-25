package de.johannes;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        OptionParser parser = new OptionParser();
        OptionSpec<File> file2 = parser.accepts("f").withRequiredArg().ofType(File.class);

        OptionSet options = parser.parse(args);
        if(options.has(file2)) {
            System.out.println(JsonParser.parseFile(options.valueOf(file2).getAbsolutePath()));
        }
    }
}