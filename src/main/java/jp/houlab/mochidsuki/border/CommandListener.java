package jp.houlab.mochidsuki.border;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("debug")){
            if(strings[0].equalsIgnoreCase("now")){
                BorderInfo.setCenterX(Double.parseDouble(strings[1]));
                BorderInfo.setCenterZ(Double.parseDouble(strings[2]));
                BorderInfo.setNowPX(BorderInfo.getCenterX() + Double.parseDouble(strings[3]));
                BorderInfo.setNowMX(BorderInfo.getCenterX() - Double.parseDouble(strings[3]));
                BorderInfo.setNowPZ(BorderInfo.getCenterZ() + Double.parseDouble(strings[3]));
                BorderInfo.setNowMZ(BorderInfo.getCenterZ() - Double.parseDouble(strings[3]));

            }
            if(strings[0].equalsIgnoreCase("getnow")){
                commandSender.sendMessage(BorderInfo.getNowPX() + "," + BorderInfo.getNowMX() + "," + BorderInfo.getNowPZ() + "," + BorderInfo.getNowMZ());
            }
        }

        return false;
    }
}
