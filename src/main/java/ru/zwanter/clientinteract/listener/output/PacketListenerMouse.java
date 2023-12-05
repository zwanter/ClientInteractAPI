package ru.zwanter.clientinteract.listener.output;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import ru.zwanter.clientinteract.ClientInteractAPI;
import ru.zwanter.clientinteract.data.PacketUtils;
import ru.zwanter.clientinteract.data.packet.InputPacketType;
import ru.zwanter.clientinteract.data.screen.Screen;
import ru.zwanter.clientinteract.listener.event.PressMouseEvent;

public class PacketListenerMouse implements PluginMessageListener {
    public static void init(ClientInteractAPI clientInteractAPI) {
        clientInteractAPI.getServer().getMessenger().registerIncomingPluginChannel(clientInteractAPI, channelId, new PacketListenerMouse());
    }

    private static final String channelId = ClientInteractAPI.getMOD_ID() + ":" + InputPacketType.MODE_MOUSE_INPUT_PACKET.getPacketName();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!(channel.equals(channelId))) return;
        int keyCode = PacketUtils.getIntFromByteArray(message, 1);
        int scrennCode = PacketUtils.getIntFromByteArray(message, 5);

        Bukkit.getPluginManager().callEvent(new PressMouseEvent(player, keyCode, Screen.fromString(scrennCode)));
    }
}
