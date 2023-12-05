package ru.zwanter.clientinteract.listener.output;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import ru.zwanter.clientinteract.ClientInteractAPI;
import ru.zwanter.clientinteract.data.PacketUtils;
import ru.zwanter.clientinteract.data.packet.InputPacketType;
import ru.zwanter.clientinteract.data.screen.Screen;
import ru.zwanter.clientinteract.listener.event.PressKeyboardEvent;

public class PacketListenerKeyboard implements PluginMessageListener {

    public static void init(ClientInteractAPI clientInteractAPI) {
        clientInteractAPI.getServer().getMessenger().registerIncomingPluginChannel(clientInteractAPI, channelId, new PacketListenerKeyboard());
    }

    private static final String channelId = ClientInteractAPI.getMOD_ID() + ":" + InputPacketType.KEYBOARD_INPUT_PACKET.getPacketName();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!(channel.equals(channelId))) return;
        int keyCode = PacketUtils.getIntFromByteArray(message, 1);
        int modeCode = PacketUtils.getIntFromByteArray(message, 5);
        int scrennCode = PacketUtils.getIntFromByteArray(message, 9);

        Bukkit.getPluginManager().callEvent(new PressKeyboardEvent(player, keyCode, modeCode, Screen.fromString(scrennCode)));
    }
}
