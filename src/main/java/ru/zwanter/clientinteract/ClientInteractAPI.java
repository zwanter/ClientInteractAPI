package ru.zwanter.clientinteract;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import ru.zwanter.clientinteract.data.RegisterKeys;
import ru.zwanter.clientinteract.listener.event.PressKeyboardEvent;
import ru.zwanter.clientinteract.listener.event.PressMouseEvent;
import ru.zwanter.clientinteract.listener.output.PacketListenerKeyboard;
import ru.zwanter.clientinteract.listener.input.ModInstalledPacketListener;
import ru.zwanter.clientinteract.listener.output.PacketListenerMouse;

import java.awt.event.KeyEvent;

public final class ClientInteractAPI extends JavaPlugin implements Listener {

    @Getter
    private static final String MOD_ID = "clientconnectormod";
    @Getter
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        RegisterKeys.registerKey(KeyEvent.VK_G);

        PacketListenerKeyboard.init(this);

        ModInstalledPacketListener.init(this);

        PacketListenerMouse.init(this);

        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    private void on(PressKeyboardEvent event) {
        Player player = event.getPlayer();
        if (KeyEvent.VK_G == event.getKey()) {
            Location location = player.getLocation().add(0, 1, 0);
            LlamaSpit llamaSpit = (LlamaSpit) location.getWorld().spawnEntity(location, EntityType.LLAMA_SPIT);
            llamaSpit.setVelocity(location.getDirection().multiply(2));
            llamaSpit.setShooter(player);
            llamaSpit.addPassenger(player);
        }
    }

    @EventHandler
    public void onMouse(PressMouseEvent event) {
        if (4 == event.getKey()) {
            event.getPlayer().sendMessage("Нажата клавиша 4 на мышке");
        }
    }
}