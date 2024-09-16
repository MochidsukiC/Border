package jp.houlab.mochidsuki.border;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * コマンドリスナー
 * @author Mochidsuki
 */
public class CommandListener implements CommandExecutor {
    /**
     * コマンドが実行された時に呼び出される
     * @param commandSender 実行者
     * @param command 実行されたコマンド
     * @param s 送信されたコマンド
     * @param strings 引数
     * @return 実行結果
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("debug")){//ボーダーサイズを手動で設定する
            BorderShrinkSystem.Initializer(0,0,500);
            new BorderShrinkSystem(20,((Player)commandSender).getX(),((Player)commandSender).getZ(),20).runTaskTimer(Main.plugin,0,1);
            ((Player) commandSender).sendMessage(((Player)commandSender).getLocation().getBlockX()+"|"+((Player)commandSender).getLocation().getBlockZ());
        }
        if(s.equalsIgnoreCase("ddamage")){//ボーダーダメージを手動で指定する
                BorderDamager.setDamage(Float.parseFloat(strings[0]));
        }

        return false;
    }
}
