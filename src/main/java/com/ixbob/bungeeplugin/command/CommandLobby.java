package com.ixbob.bungeeplugin.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Objects;

public class CommandLobby extends Command {

    public CommandLobby() {
        super("lobby", "permission.lobby", "l", "hub");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (player.getServer().getInfo() == ProxyServer.getInstance().getServerInfo("lobby")) {
                player.sendMessage(new ComponentBuilder("您已经连接在此服务器了!").color(ChatColor.RED).create());
            } else {
                player.sendMessage(new ComponentBuilder("正在将您转移至大厅服务器...").color(ChatColor.YELLOW).create());
                ServerInfo target = ProxyServer.getInstance().getServerInfo("lobby");
                player.connect(target);
            }
        } else {
            commandSender.sendMessage(new ComponentBuilder("Only player can execute this command!").color(ChatColor.RED).create());
        }
    }
}
