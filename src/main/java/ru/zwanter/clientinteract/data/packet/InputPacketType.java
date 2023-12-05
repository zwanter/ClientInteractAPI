package ru.zwanter.clientinteract.data.packet;

import lombok.Getter;


public enum InputPacketType {

    KEYBOARD_INPUT_PACKET("KeyboardInputPacket"),
    MODE_MOUSE_INPUT_PACKET("ModeMouseInputPacket"),
    INSTALLED_PLUGIN_PACKET("InstalledPluginPacket"),
    MOD_INSTALLED_PACKET("ModInstalledPacket");

    @Getter
    private final String packetName;

    InputPacketType(String s) {
        this.packetName = s.toLowerCase();
    }

}
