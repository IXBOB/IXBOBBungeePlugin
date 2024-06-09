package com.ixbob.bungeeplugin.enums;

public enum ServerNameEnum {
    LOBBY("lobby");

    private final String serverName;

    ServerNameEnum(String serverName) {
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }
}
