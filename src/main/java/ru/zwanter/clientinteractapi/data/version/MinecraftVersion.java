package ru.zwanter.clientinteractapi.data.version;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
@RequiredArgsConstructor
public enum MinecraftVersion {

    MINECRAFT(0),
    FORGE(1),
    FABRIC(2);

    private final int i;

    private static final HashMap<Player, MinecraftVersion> modifiedPlayers = new HashMap<>();

    public static MinecraftVersion getPlayerVersion(Player player) {
        if (modifiedPlayers.containsKey(player)) {
            return modifiedPlayers.get(player);
        }
        return MINECRAFT;
    }

    public static MinecraftVersion getMinecraftVersion(int version) {
        for (MinecraftVersion versions : values())
            if (versions.i == version) return versions;
        return MINECRAFT;
    }

    public static void addPlayer(Player player, MinecraftVersion version) {
        if (version != MINECRAFT) {
            modifiedPlayers.put(player, version);
        }
    }

    public static void addPlayer(Player player, int version) {
        if (version != MINECRAFT.i) {
            modifiedPlayers.put(player, MinecraftVersion.getMinecraftVersion(version));
        }
    }

}
