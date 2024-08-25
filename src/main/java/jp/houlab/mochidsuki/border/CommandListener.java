package jp.houlab.mochidsuki.border;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("debug")){
            BorderShrinkSystem.Initializer(0,0,500);
            new BorderShrinkSystem(1200,100,100,200).runTaskTimer(Main.plugin,0,1);
        }

        return false;
    }
}
