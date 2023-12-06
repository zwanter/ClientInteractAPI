package ru.zwanter.clientinteractapi;

import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zwanter.clientinteractapi.data.RegisterKeys;
import ru.zwanter.clientinteractapi.data.keys.MouseKeys;
import ru.zwanter.clientinteractapi.listener.event.KeyboardEvent;
import ru.zwanter.clientinteractapi.listener.event.MouseButtonEvent;
import ru.zwanter.clientinteractapi.listener.event.SendKeyEvent;
import ru.zwanter.clientinteractapi.listener.input.KeyboardKeysPacket;
import ru.zwanter.clientinteractapi.listener.input.MouseKeysPacket;
import ru.zwanter.clientinteractapi.listener.output.ModInstalledPacketListener;
import ru.zwanter.clientinteractapi.listener.output.PacketListenerKeyboard;
import ru.zwanter.clientinteractapi.listener.output.PacketListenerMouse;

import java.awt.event.KeyEvent;

public final class ClientInteractAPI extends JavaPlugin implements Listener {

    @Getter
    private static final String MOD_ID = "clientconnectormod";
    @Getter
    private static JavaPlugin instance;

    @Override
    public void onEnable() {

        instance = this;

        PacketListenerKeyboard.init(this);

        ModInstalledPacketListener.init(this);

        PacketListenerMouse.init(this);

        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onKeyboard(KeyboardEvent event) {
        if (event.getKey() == KeyEvent.VK_J) {
            //We display a message about the pressed key, receiving the contents of the key using KeyEvent.getKeyText(key)
            event.getPlayer().sendMessage("You pressed key: " + KeyEvent.getKeyText(event.getKey()));
        }
        //We get it using "event.getMods()" an additional pressed key and check that the pressed key is "CTRL"
        if (event.getModifiers() == KeyEvent.CTRL_MASK && event.getKey() == KeyEvent.VK_J) {
            event.getPlayer().sendMessage("You have pressed a keyboard shortcut: " +
                    KeyEvent.getKeyText(event.getKey()) +
                    " + " +
                    KeyEvent.getKeyText(event.getModifiers()));
        }
    }

    @EventHandler
    public void onMouse(MouseButtonEvent event) {
        if (event.getKey() == MouseKeys.BUTTON4 || event.getKey() == MouseKeys.BUTTON5) {
            event.getPlayer().sendMessage("You pressed mouse key: " + MouseKeys.getButtonText(event.getKey()));
        }
    }

}