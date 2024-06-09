package com.ixbob.bungeeplugin.event;

import com.ixbob.bungeeplugin.Main;
import com.ixbob.bungeeplugin.enums.ServerNameEnum;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class OnReceiveLobbyMessageListener implements Listener {
    @EventHandler
    public void onReceiveLobbyMessage(PluginMessageEvent event) throws IOException {
        if (event.getTag().equals("ixbob_thepit:bungeecord")) {
            ByteArrayInputStream steam = new ByteArrayInputStream(event.getData());
            DataInputStream in = new DataInputStream(steam);
            String playerName = in.readUTF();
            ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(playerName);
            ServerInfo target = ProxyServer.getInstance().getServerInfo(ServerNameEnum.LOBBY.getServerName());
            player.sendMessage(new ComponentBuilder("正在将您转移至大厅服务器...").color(ChatColor.YELLOW).create());
            player.connect(target);
        }
    }
}
