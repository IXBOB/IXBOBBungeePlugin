package com.ixbob.bungeeplugin;

import com.ixbob.bungeeplugin.command.CommandLobby;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    @Override
    public void onEnable() {
        getLogger().info("loaded bungee plugin by IXBOB");

        getProxy().getPluginManager().registerCommand(this, new CommandLobby());
    }
}
