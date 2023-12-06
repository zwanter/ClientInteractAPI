package ru.zwanter.clientinteractapi.listener.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.zwanter.clientinteractapi.data.screen.Screen;

public class KeyboardEvent extends SendKeyEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public KeyboardEvent(@NotNull Player who, int key, int mods, Screen screen) {
        super(who, key, mods, screen);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
