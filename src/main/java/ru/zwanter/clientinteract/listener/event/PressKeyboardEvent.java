package ru.zwanter.clientinteract.listener.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.zwanter.clientinteract.data.screen.Screen;

@Getter
public class PressKeyboardEvent extends SendKeyEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final int mods;

    public PressKeyboardEvent(@NotNull Player who, int key, int mods, Screen screen) {
        super(who, key, screen);
        this.mods = mods;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
