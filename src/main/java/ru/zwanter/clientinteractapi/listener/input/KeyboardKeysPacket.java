package ru.zwanter.clientinteractapi.listener.input;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.protocol.game.PacketPlayOutCustomPayload;
import net.minecraft.resources.MinecraftKey;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import ru.zwanter.clientinteractapi.ClientInteractAPI;
import ru.zwanter.clientinteractapi.data.RegisterKeys;
import ru.zwanter.clientinteractapi.data.packet.InputPacketType;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class KeyboardKeysPacket {

    public static void sendPacket(Player player) {
        sendCustomPayloadPacket(player, RegisterKeys.getRegisterKeysKeyboard());
    }

    public static void sendPacket(Player player, List<Integer> ints) {
        sendCustomPayloadPacket(player, ints);
    }


    private static void sendCustomPayloadPacket(Player player, List<Integer> list) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try {
            for (int number : list) {
                dataOutputStream.writeInt(number);
            }

            sendBytePacket(player, byteArrayOutputStream.toByteArray());
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

    private static void sendBytePacket(Player player, byte[] message) {
        try {
            byte[] newMessage = new byte[message.length + 1];
            System.arraycopy(message, 0, newMessage, 1, message.length);
            ByteBuf byteBuf = Unpooled.wrappedBuffer(newMessage);

            PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload(new MinecraftKey(
                    ClientInteractAPI.getMOD_ID(),
                    InputPacketType.KEYBOARD_KEYS_PACKET.getPacketName()),
                    new PacketDataSerializer(byteBuf));

            CraftPlayer craftPlayer = (CraftPlayer) player;
            craftPlayer.getHandle().c.a(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
