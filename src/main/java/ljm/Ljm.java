package ljm;

import java.util.List;

public class Ljm {
    public static void main(String[] args) {
        String path = args.length != 0 ? args[0] : "ljm-sources.txt";

        FileReader reader = new FileReader(path);
        String contents = reader.read();

        Parser parser = new Parser(contents);
        parser.parse();
        List<LjmJar> jars = parser.getJars();

        for (LjmJar jar : jars) {
            String file = jar.file();
            String address = jar.address();

            JarDownloader downloader = new JarDownloader(address, file);
            downloader.download();
        }
    }
}
