package com.ixbob.bungeeplugin;

import com.ixbob.bungeeplugin.command.CommandLobby;
import com.ixbob.bungeeplugin.event.OnLoginListener;
import com.ixbob.bungeeplugin.event.OnReceiveLobbyMessageListener;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class Main extends Plugin {

    private static Main instance;
    private Configuration config;

    @Override
    public void onEnable() {
        instance = this;

        createConfig();
        loadConfig();

        getProxy().registerChannel("ixbob_thepit:bungeecord");

        getProxy().getPluginManager().registerCommand(this, new CommandLobby());

        Listener onClientConnectListener = new OnLoginListener();
        getProxy().getPluginManager().registerListener(this, onClientConnectListener);

        Listener onReceiveLobbyMessageListener = new OnReceiveLobbyMessageListener();
        getProxy().getPluginManager().registerListener(this, onReceiveLobbyMessageListener);
    }

    public static Main getInstance() {
        return instance;
    }

    public Configuration getConfig() {
        return config;
    }

    public void saveConfig() throws IOException {
        File file = new File(getDataFolder(), "config.yml");
        file.createNewFile();
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
    }

    private void createConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
                config.set("banned_ips", new ArrayList<String>());
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
            } catch (IOException e) {
                getLogger().log(Level.SEVERE, "Unable to create config file", e);
            }
        }
    }

    private void loadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Unable to load config file", e);
        }
    }
}
