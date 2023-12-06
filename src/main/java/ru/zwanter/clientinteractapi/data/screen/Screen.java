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

    private final int value;

    public static Screen fromInt(int modifier) {
        for (Screen screen : values())
            if (screen.getValue() == modifier) return screen;
        return Screen.ERROR_SCREEN;
    }
}
