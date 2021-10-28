package by.slizh.tutorsweb.model.util.security;

/**
 * The type LinkIdEncoder.
 */
public final class LinkIdEncoder {

    /**
     * Encode id.
     *
     * @param id the id
     * @return encoded id
     */
    public static int encodeId(int id) {
        int encodedId = (id * 827 + 53) | 883;
        return encodedId;
    }

    /**
     * Decode id.
     *
     * @param encodedId the encoded id
     * @return decoded id
     */
    public static int decodeId(int encodedId) {
        int decodedId = ((encodedId | 883) - 53) / 827;
        return decodedId;
    }
}
