package ru.zwanter.clientinteractapi.listener.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import ru.zwanter.clientinteractapi.ClientInteractAPI;
import ru.zwanter.clientinteractapi.data.version.MinecraftVersion;

@Getter
public class ModPacketEvent extends PlayerEvent implements Cancellable, Listener {

    public static void init(ClientInteractAPI plugin){
        plugin.getServer().getPluginManager().registerEvents(new ModPacketEvent(), plugin);
    }

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled = true;
    private MinecraftVersion version;

    public ModPacketEvent(@NotNull Player who, MinecraftVersion version) {
        super(who);
        this.version = version;
    }

    public ModPacketEvent() {
        super(null);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @EventHandler
    public void onJoin(ModPacketEvent event) {
        MinecraftVersion.addPlayer(event.getPlayer(), event.getVersion());
    }
}
