package com.ixbob.bungeeplugin.util;

import com.ixbob.bungeeplugin.Main;
import net.md_5.bungee.config.Configuration;

import java.util.List;

public class Utils {
    public static String removeAfterColon(String input) {
        int colonIndex = input.indexOf(':');
        if (colonIndex != -1) {
            return input.substring(1, colonIndex);
        }
        return input;  // 如果没有冒号，返回原字符串
    }

    public static void saveIPToConfig(String ip) {
        Configuration config = Main.getInstance().getConfig();
        List<String> bannedIps = config.getStringList("banned_ips");
        bannedIps.add(ip);
        config.set("banned_ips", bannedIps);
    }
}
