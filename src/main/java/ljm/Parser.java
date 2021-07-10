package ljm;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for generating a {@link List} of {@link LjmJar}s from a given
 * input string. This string should almost always come directly from a file.
 * This parser is rather simple - there's no debugging features, etc. The
 * syntax the parser uses is detailed in this project's readme.
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
public class Parser {
    private final String contents;

    private final List<LjmJar> jars = new ArrayList<>();

    public Parser(String contents) {
        this.contents = contents;
    }

    /**
     * Create a {@link LjmJar} from some provided strings.
     *
     * @param name    the jar's name.
     * @param file    the jar's file.
     * @param address the jar's address.
     * @return new {@link LjmJar}.
     */
    private LjmJar jarFrom(String name,
                           String file,
                           String address) {
        return new LjmJar(name, file, address);
    }

    /**
     * Based on an inputted mode, return the "next" mode.
     *
     * @param current the mode the parser is currently operating in.
     * @return the next mode the parser should operate in.
     */
    private ParserMode cycleMode(ParserMode current) {
        switch (current) {
            case NAME:
                return ParserMode.FILE;
            case FILE:
                return ParserMode.ADDRESS;
            case ADDRESS:
                return ParserMode.NAME;
            default:
                throw new RuntimeException("Invalid parser mode!");
        }
    }

    /**
     * Parse the {@link #contents} of this {@code Parser}.
     *
     * <p>
     * This method will first clear the {@link #jars} list before populating
     * that list with {@link LjmJar} instances it has generated from the
     * provided contents.
     * </p>
     */
    public void parse() {
        /*
         * The parser can operate in three different modes:
         * - NAME
         * - FILE
         * - ADDRESS
         *
         * Each of these modes has a respective StringBuilder associated with
         * it. For example, if the parser is in NAME mode, the nameBuilder
         * defined below would receive whatever character comes next. If the
         * parser is in its FILE mode, likewise, the fileBuilder would
         * be our very lovely recipient.
         *
         * Each parsable section of text looks something like this:
         * Line 1: (<name>, <file>, <address>);
         * Line 2: (<name>, <file>, <address>);
         *
         * If there's any issues with that format, the parser will have a
         * fit and... well, it won't work, quite simply.
         *
         * The parser operates by reading one character at a time, and,
         * based on that character, determining what to do. Several characters
         * (we'll call them "control characters") are restricted. Those
         * characters are:
         * ( - open LjmJar instance
         * ) - close LjmJar instance
         * ; - end line (reset LjmJar instance)
         * , - cycle mode
         *
         * Any other character ("plaintext") is appended to one of the three
         * builders. Basically, as long as there's no syntax errors, the parser
         * will parse things pretty well.
         */

        jars.clear();

        final char[] characters = contents.toCharArray();

        StringBuilder nameBuilder = new StringBuilder();
        StringBuilder fileBuilder = new StringBuilder();
        StringBuilder addressBuilder = new StringBuilder();

        ParserMode mode = ParserMode.NAME;

        boolean isInParentheses = false;

        for (char character : characters) {
            switch (character) {
                case ';':
                    mode = ParserMode.NAME;

                    final LjmJar jar = jarFrom(
                            nameBuilder.toString().trim(),
                            fileBuilder.toString().trim(),
                            addressBuilder.toString().trim()
                    );

                    nameBuilder = new StringBuilder();
                    fileBuilder = new StringBuilder();
                    addressBuilder = new StringBuilder();

                    if (!jars.contains(jar)) {
                        jars.add(jar);
                    }

                    break;

                case '(':
                    if (mode != ParserMode.NAME) {
                        throw new ParserException("Invalid syntax!");
                    }

                    isInParentheses = true;

                    break;

                case ')':
                    if (mode != ParserMode.ADDRESS) {
                        throw new ParserException("Invalid syntax!");
                    }

                    isInParentheses = false;

                    break;

                case ',':
                    mode = cycleMode(mode);

                    break;

                default:
                    if (isInParentheses) {
                        switch (mode) {
                            case NAME:
                                nameBuilder.append(character);
                                break;
                            case FILE:
                                fileBuilder.append(character);
                                break;
                            case ADDRESS:
                                addressBuilder.append(character);
                                break;
                        }
                    }

                    break;
            }
        }
    }

    /**
     * Get the {@link List} of {@link LjmJar}s the parser has parsed. If
     * the {@link #parse()} method has not been called, or if there was
     * an exception while parsing the provided contents, this method will
     * return an empty list.
     *
     * @return the list of {@link LjmJar} instances which the parser has
     * generated from the provided contents.
     */
    public List<LjmJar> getJars() {
        return this.jars;
    }

    /**
     * Different "modes" the parser can be in.
     */
    private enum ParserMode {
        /**
         * The parser should append letters to the NAME field.
         */
        NAME,

        /**
         * The parser should append letters to the FILE field.
         */
        FILE,

        /**
         * The parser should append letters to the ADDRESS field.
         */
        ADDRESS
    }
}
