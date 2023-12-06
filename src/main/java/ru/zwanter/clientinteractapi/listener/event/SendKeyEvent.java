package ru.zwanter.clientinteractapi.listener.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import ru.zwanter.clientinteractapi.data.screen.Screen;

@Getter
public class SendKeyEvent extends PlayerEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final int key;
    private final int modifiers;
    private final Screen screen;

    public SendKeyEvent(@NotNull Player who, int key, int mode, Screen screen) {
        super(who);
        this.key = key;
        this.modifiers = mode;
        this.screen = screen;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
