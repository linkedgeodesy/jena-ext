package org.linkedgeodesy.jenaext.run;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import org.linkedgeodesy.jenaext.log.Logging;

public class FileOutput {

    private static final String fileName = "main.txt";

    public static void writeFile(List<String> o) {
        try {
            File file = new File(fileName);
            String path = file.getCanonicalPath();
            File filePath = new File(path);
            filePath.delete();
            try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"))) {
                for (Object item : o) {
                    String tmp = (String) item;
                    out.append(tmp).append("\r\n");
                }
                out.flush();
            }
        } catch (IOException e) {
            System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.jenaext.run.FileOutput"));
        }
    }

}
