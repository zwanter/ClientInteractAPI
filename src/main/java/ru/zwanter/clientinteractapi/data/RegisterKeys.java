package ru.zwanter.clientinteractapi.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class RegisterKeys {
    @Getter
    private static final List<Integer> registerKeysKeyboard = new ArrayList<>();
    @Getter
    private static final List<Integer> registerKeysMouse = new ArrayList<>();

    public static void registerKeyKeyboard(int... keys) {
        for (int k : keys) {
            registerKeysKeyboard.add(k);
        }
    }

    public static void registerKeyMouse(int... keys) {
        for (int k : keys) {
            registerKeysMouse.add(k);
        }
    }

    public static void unRegisterKeyMouse(int key) {
        registerKeysMouse.remove(key);
    }
    public static void unRegisterKeyKeyboard(int key) {
        registerKeysKeyboard.remove(key);
    }
}
