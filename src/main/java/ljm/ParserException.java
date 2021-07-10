package ljm;

/**
 * An exception generated while an instance of the {@link Parser} class is
 * attempting to parse some text. This exception will most frequently be
 * thrown if there's an invalid syntax - if there is... well, I hate to
 * break it to you, but that... well, that sounds like a you problem, now
 * doesn't it? On a more serious note, you should review the syntax ljm
 * uses and get things sorted out.
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
public class ParserException extends RuntimeException {
    public ParserException(String message) {
        super(message);
    }
}
