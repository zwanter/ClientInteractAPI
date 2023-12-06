package ru.zwanter.clientinteractapi;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zwanter.clientinteractapi.listener.output.ModInstalledPacketListener;
import ru.zwanter.clientinteractapi.listener.output.PacketListenerKeyboard;
import ru.zwanter.clientinteractapi.listener.output.PacketListenerMouse;

public final class ClientInteractAPI extends JavaPlugin {

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

    }

}