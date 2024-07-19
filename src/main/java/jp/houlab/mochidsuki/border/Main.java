package jp.houlab.mochidsuki.border;

import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("debug").setExecutor(new CommandListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

