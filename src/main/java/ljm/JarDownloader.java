package ljm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Class responsible for downloading JAR files from the internet and storing
 * them locally.
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class JarDownloader {
    private final String url;

    private final String output;

    /**
     * Create a new {@code JarDownloader}.
     *
     * @param url    the URL to download the JAR from.
     * @param output the output file - this is where the JAR file will get
     *               downloaded to.
     */
    public JarDownloader(String url,
                         String output) {
        this.url = url;
        this.output = "ljm" + File.separator + output;
    }

    /**
     * Download the JAR file. If there's an IO exception - say, for example,
     * the file can't be downloaded or the connection is interrupted - do
     * absolutely nothing. Useful, right?
     */
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
