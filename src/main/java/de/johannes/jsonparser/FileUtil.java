package de.johannes.jsonparser;

import java.io.*;
import java.util.LinkedList;

public class FileUtil {

    public static LinkedList<Character> readFile(String location) throws Exception{
        File file = new File(location);
        if(!file.exists()) throw new IOException("File does not exist");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder str = new StringBuilder();
        String line = "";
        while((line = reader.readLine()) != null) {
            str.append(line);
        }
        LinkedList<Character> result = new LinkedList<>();
        for (char c : str.toString().toCharArray()) {
            result.add(c);
        }
        return result;
    }

    public static void writeFile(String content, String location) throws IOException {
        File output = new File(location);
        if(!output.exists()) {
            output.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
        writer.write(content);
        writer.flush();
        writer.close();
    }

}
