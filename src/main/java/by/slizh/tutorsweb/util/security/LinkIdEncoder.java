package by.slizh.tutorsweb.util.security;

public final class LinkIdEncoder {

    public static int encodeId(int id) {
        int encodedId = (id * 827 + 53) | 883;
        return encodedId;
    }

    public static int decodeId(int encodedId) {
        int decodedId = ((encodedId | 883) - 53) / 827;
        return decodedId;
    }
}
