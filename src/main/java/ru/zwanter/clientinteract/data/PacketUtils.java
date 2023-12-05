package ru.zwanter.clientinteract.data;

public class PacketUtils {

    public static int getIntFromByteArray(byte[] byteArray, int startIndex) {
        return (byteArray[startIndex] & 0xFF) << 24 |
                (byteArray[startIndex + 1] & 0xFF) << 16 |
                (byteArray[startIndex + 2] & 0xFF) << 8 |
                (byteArray[startIndex + 3] & 0xFF);
    }
}
