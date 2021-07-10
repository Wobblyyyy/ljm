package ljm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    private final String path;

    public FileReader(String path) {
        this.path = System.getProperty("user.dir") + File.separator + path;
    }

    public String read() {
//        try {
//            File file = new File(String.valueOf(Paths.get(URI.create(path))));
//            java.io.FileReader reader = new java.io.FileReader(file);
//
//            StringBuilder builder = new StringBuilder();
//
//            int i;
//            while ((i = reader.read()) != 1) {
//                builder.append((char) i);
//            }
//
//            return builder.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "";
//        }
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(path), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
