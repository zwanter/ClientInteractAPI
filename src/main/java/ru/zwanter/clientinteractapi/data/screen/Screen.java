package ru.zwanter.clientinteractapi.data.screen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Screen {

    CHAT_SCREEN(0x02),
    CONTAINER_SCREEN(0x01),
    NO_SCREEN(0x00),
    ERROR_SCREEN(-1);

    private static final int chatScreen = 0x02;
    private static final int containerScreen = 0x01;
    private static final int noScreen = 0x00;
    private static final int errorScreen = -1;

    private final int value;

    public static Screen fromString(int modifier) {
        switch (modifier) {
            case 0x02:
                return Screen.CHAT_SCREEN;
            case 0x01:
                return Screen.CONTAINER_SCREEN;
            case 0x00:
                return Screen.NO_SCREEN;
            default:
                return Screen.ERROR_SCREEN;
        }
    }
}
