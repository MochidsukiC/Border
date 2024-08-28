package jp.houlab.mochidsuki.border;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    static public World world;
    static public Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("debug").setExecutor(new CommandListener());
        world = getServer().getWorld("world");
        plugin = this;

        new BorderWallShower().runTaskTimer(this, 1L, 1L);
        new EveryTicks().runTaskTimer(this, 1L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}

