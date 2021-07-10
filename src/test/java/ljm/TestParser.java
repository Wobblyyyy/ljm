package ljm;

import org.junit.jupiter.api.Test;

public class TestParser {
    private static final String exampleString1 =
            "(ExampleJar1, example.jar, https://google.com);";
    private static final String exampleString2 =
            "(ExampleJar1, example.jar, https://google.com);" +
            "(ExampleJar2, another.jar, https://bing.com);";

    @Test
    public void testExampleString1() {
        Parser parser = new Parser(exampleString1);
        parser.parse();
    }

    @Test
    public void testExampleString2() {
        Parser parser = new Parser(exampleString2);
        parser.parse();
    }
}
