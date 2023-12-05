package ru.zwanter.clientinteract.listener.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.zwanter.clientinteract.data.screen.Screen;

@Getter
public class PressMouseEvent extends SendKeyEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public PressMouseEvent(@NotNull Player who, int key, Screen screen) {
        super(who, key, screen);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
