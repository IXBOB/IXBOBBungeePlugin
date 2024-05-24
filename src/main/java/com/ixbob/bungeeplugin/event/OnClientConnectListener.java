package com.ixbob.bungeeplugin.event;

import com.ixbob.bungeeplugin.Main;
import com.ixbob.bungeeplugin.auth.Auth;
import com.ixbob.bungeeplugin.util.Utils;
import net.md_5.bungee.api.event.ClientConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;
import java.util.logging.Level;

public class OnClientConnectListener implements Listener {
    @EventHandler
    public void onClientConnect(ClientConnectEvent event) {
        String ip = Utils.removeAfterColon(event.getSocketAddress().toString());
        System.out.println(ip);

        if (Main.getInstance().getConfig().getStringList("banned_ips").contains(ip)) {
            event.setCancelled(true);
            return;
        }

        boolean ipCheckResult = Auth.authIp(ip);
        if (!ipCheckResult) {
            event.setCancelled(true);
            Utils.saveIPToConfig(ip);
            try {
                Main.getInstance().saveConfig();
            } catch (IOException e) {
                Main.getInstance().getLogger().log(Level.SEVERE, "unable to save config with added ip", e);
            }
        }
    }
}
