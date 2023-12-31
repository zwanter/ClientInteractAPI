package ru.zwanter.clientinteractapi.listener.output;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.protocol.game.PacketPlayOutCustomPayload;
import net.minecraft.resources.MinecraftKey;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import ru.zwanter.clientinteractapi.ClientInteractAPI;
import ru.zwanter.clientinteractapi.data.PacketUtils;
import ru.zwanter.clientinteractapi.data.packet.InputPacketType;
import ru.zwanter.clientinteractapi.data.version.MinecraftVersion;
import ru.zwanter.clientinteractapi.listener.event.ModPacketEvent;
import ru.zwanter.clientinteractapi.listener.input.KeyboardKeysPacket;
import ru.zwanter.clientinteractapi.listener.input.MouseKeysPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ModInstalledPacketListener implements PluginMessageListener {

    public static void init(ClientInteractAPI plugin) {
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, channelId, new ModInstalledPacketListener());
    }

    private static final String channelId = ClientInteractAPI.getMOD_ID() + ":" +  InputPacketType.MOD_INSTALLED_PACKET.getPacketName();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!(channel.equals(channelId))) return;

        int versionCode = PacketUtils.getIntFromByteArray(message, 1);

        sendCustomPayloadPacket(player, new ArrayList<>(), versionCode);
    }


    private static void sendCustomPayloadPacket(Player player, List<Integer> list, int version) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try {
            for (int number : list) {
                dataOutputStream.writeInt(number);
            }

            sendBytePacket(player, byteArrayOutputStream.toByteArray(), version);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendBytePacket(Player player, byte[] message, int version) {
        try {
            byte[] newMessage = new byte[message.length + 1];
            System.arraycopy(message, 0, newMessage, 1, message.length);
            ByteBuf byteBuf = Unpooled.wrappedBuffer(newMessage);

            PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload(new MinecraftKey(
                    ClientInteractAPI.getMOD_ID(),
                    InputPacketType.INSTALLED_PLUGIN_PACKET.getPacketName()),
                    new PacketDataSerializer(byteBuf));

            ModPacketEvent joinModifiedPlayerEvent = new ModPacketEvent(player, MinecraftVersion.getMinecraftVersion(version));

            Bukkit.getPluginManager().callEvent(joinModifiedPlayerEvent);

            if (joinModifiedPlayerEvent.isCancelled()) {
                CraftPlayer craftPlayer = (CraftPlayer) player;
                craftPlayer.getHandle().c.a(packet);
                KeyboardKeysPacket.sendPacket(player);
                MouseKeysPacket.sendPacket(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

