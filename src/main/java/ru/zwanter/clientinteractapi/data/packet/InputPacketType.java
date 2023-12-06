package ru.zwanter.clientinteractapi.data.packet;

import lombok.Getter;

@Getter
public enum InputPacketType {

    KEYBOARD_INPUT_PACKET("KeyboardInputPacket"),
    MODE_MOUSE_INPUT_PACKET("ModeMouseInputPacket"),
    INSTALLED_PLUGIN_PACKET("InstalledPluginPacket"),
    MOD_INSTALLED_PACKET("ModInstalledPacket"),
    MOUSE_KEYS_PACKET("MouseKeysPacket"),
    KEYBOARD_KEYS_PACKET("KeyboardKeysPacket");

    private final String packetName;

    InputPacketType(String s) {
        this.packetName = s.toLowerCase();
    }

}
