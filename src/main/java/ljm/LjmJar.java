package ljm;

/**
 * A record-like class storing information about a single jar file.
 *
 * <p>
 * Each {@link LjmJar} represents one actual jar file that ljm is responsible
 * for processing. Every jar has three components:
 * <ul>
 *     <li>
 *         <em>NAME</em>,
 *         or the jar's... well, it's the jar's name. This is different
 *         from the "file" attribute - this is a name used exclusively for
 *         processing purposes, whereas the "file" attribute is used for
 *         actual filesystem purposes.
 *     </li>
 *     <li>
 *         <em>FILE</em>,
 *         or the actual file that the jar corresponds with. Once again,
 *         this is different from the jar's name. The jar's file is quite
 *         literally the file where the jar gets stored in the file system.
 *     </li>
 *     <li>
 *         <em>ADDRESS</em>,
 *         or where the jar can/should be downloaded from. This should be
 *         a regular HTTP address. ljm will download the jar from this location.
 *     </li>
 * </ul>
 * </p>
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
public class LjmJar {
    private final String name;

    private final String file;

    private final String address;

    /**
     * Create a new {@code LjmJar}.
     *
     * @param name    the jar's name.
     * @param file    the jar's file.
     * @param address the jar's address.
     */
    public LjmJar(String name,
                  String file,
                  String address) {
        this.name = name;
        this.file = file;
        this.address = address;
    }

    /**
     * Get the jar's name.
     *
     * @return the jar's name.
     */
    public String name() {
        return this.name;
    }

    /**
     * Get the jar's file.
     *
     * @return the jar's file.
     */
    public String file() {
        return this.file;
    }

    /**
     * Get the jar's address.
     *
     * @return the jar's address.
     */
    public String address() {
        return this.address;
    }
}
