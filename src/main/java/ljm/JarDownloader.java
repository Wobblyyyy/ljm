package ljm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class JarDownloader {
    private final String url;
    private final String output;

    public JarDownloader(String url,
                         String output) {
        this.url = url;
        this.output = "ljm" + File.separator + output;
    }

    public void download() {
        try {
            File folder = new File(System.getProperty("user.dir") + File.separator + "ljm");
            folder.mkdir();
            InputStream input = new URL(url).openStream();
            Files.copy(input, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
