package ru.zwanter.clientinteractapi.listener.output;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import ru.zwanter.clientinteractapi.ClientInteractAPI;
import ru.zwanter.clientinteractapi.data.PacketUtils;
import ru.zwanter.clientinteractapi.data.packet.InputPacketType;
import ru.zwanter.clientinteractapi.data.screen.Screen;
import ru.zwanter.clientinteractapi.listener.event.KeyboardEvent;

public class PacketListenerKeyboard implements PluginMessageListener {

    public static void init(ClientInteractAPI plugin) {
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, channelId, new PacketListenerKeyboard());
    }

    private static final String channelId = ClientInteractAPI.getMOD_ID() + ":" + InputPacketType.KEYBOARD_INPUT_PACKET.getPacketName();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!(channel.equals(channelId))) return;

        int keyCode = PacketUtils.getIntFromByteArray(message, 1);
        int modeCode = PacketUtils.getIntFromByteArray(message, 5);
        int screenCode = PacketUtils.getIntFromByteArray(message, 9);

        Bukkit.getPluginManager().callEvent(new KeyboardEvent(player, keyCode, modeCode, Screen.fromInt(screenCode)));
    }
}
