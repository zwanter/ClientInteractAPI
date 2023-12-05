package ru.zwanter.clientinteract.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RegisterKeys {

    @Getter
    private static final List<Integer> registerKeys = new ArrayList<>();

    public static void registerKey(int... keys) {
        for (int k : keys) {
            registerKeys.add(k);
        }
    }

    public static void unRegisterKey(int key) {
        registerKeys.remove(key);
    }
}
