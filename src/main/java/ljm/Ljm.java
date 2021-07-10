package ljm;

import java.util.List;

/**
 * LJM's main class. Also known as where the magic happens. Also known as
 * the second coolest place in the universe. I'll let you figure out what
 * the first one is.
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
public class Ljm {
    /**
     * The main entry point for the application. If you execute the LJM by
     * double-clicking on the JAR file, it'll call this method with no
     * arguments. If you execute the LJM via a terminal of some sort, you
     * can optionally provide an argument. If no arguments are provided,
     * LJM will attempt to find a file named {@code ljm-sources.txt}. If one
     * argument is provided, LJM will attempt to find a file named whatever
     * you inputted.
     *
     * @param args an array of arguments to pass to LJM. This array should
     *             contain a single entry - that, of course, being the file
     *             at which LJM's sources can be located. If no arguments are
     *             provided, LJM will use {@code ljm-sources.txt}.
     */
    public static void main(String[] args) {
        // Use either ljm-sources.txt or whatever argument is passed.
        String path = args.length != 0 ? args[0] : "ljm-sources.txt";

        // Create a new FileReader to read the file. Read the file into
        // a string - we'll parse it in just a moment.
        FileReader reader = new FileReader(path);
        String contents = reader.read();

        // Create a new Parser with the FileReader's output. Call the parse
        // method to generate a list of LjmJar instances. Get this list
        // using the getJars method.
        Parser parser = new Parser(contents);
        parser.parse();
        List<LjmJar> jars = parser.getJars();

        // For each of the LjmJar elements stored in the above list, we need
        // to create a JarDownloader to download said jar.
        for (LjmJar jar : jars) {
            String file = jar.file();
            String address = jar.address();

            // Create a new JarDownloader and download the JAR file.
            JarDownloader downloader = new JarDownloader(address, file);
            downloader.download();
        }
    }
}
