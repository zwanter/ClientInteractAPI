package ru.zwanter.clientinteractapi.data.keys;

import org.bukkit.ChatColor;

public class MouseKeys {

    public static final int BUTTON5 = 4;
    public static final int BUTTON4 = 3;

    public static String getButtonText(int i) {
        if (i == BUTTON4) return "BUTTON4";
        if (i == BUTTON5) return "BUTTON5";
        return ChatColor.RED + "The button is not \"BUTTON4\" or \"BUTTON5\"!";
    }

}
