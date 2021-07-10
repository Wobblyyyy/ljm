package ljm;

import org.junit.jupiter.api.Test;

public class TestJarDownloader {
    String testPlace = "file.zip";
    String testUrl1 = "https://www.learningcontainer.com/wp-content/uploads/2020/04/sample-text-file.txt";
    String testUrl2 = "http://www.java2s.com/Code/JarDownload/sample/sample.jar.zip";

    @Test
    public void testDownload1() {
        JarDownloader downloader = new JarDownloader(testUrl1, testPlace);
        downloader.download();
    }

    @Test
    public void testDownload2() {
        JarDownloader downloader = new JarDownloader(testUrl2, testPlace);
        downloader.download();
    }
}
