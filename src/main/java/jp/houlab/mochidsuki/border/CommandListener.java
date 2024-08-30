package jp.houlab.mochidsuki.border;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("debug")){
            BorderShrinkSystem.Initializer(0,0,500);
            new BorderShrinkSystem(20,((Player)commandSender).getX(),((Player)commandSender).getZ(),20).runTaskTimer(Main.plugin,0,1);
            ((Player) commandSender).sendMessage(((Player)commandSender).getLocation().getBlockX()+"|"+((Player)commandSender).getLocation().getBlockZ());
        }
        if(s.equalsIgnoreCase("ddamage")){
            BorderDamager.setDamage(Float.parseFloat(strings[0]));
        }

        return false;
    }
}
