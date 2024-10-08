package jp.houlab.mochidsuki.border;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;

import static jp.houlab.mochidsuki.border.Main.plugin;

/**
 * ボーダーの範囲外にいるプレイヤーに対して警告表示とダメージを与えるクラス
 * @author Mochidsuki
 */
public class BorderDamager extends BukkitRunnable {

    static private float damage;
    static private boolean power;

    @Override
    public void run() {
        if(power) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                Location loc = player.getLocation();

                Duration fadeIn;
                Duration stay;
                Duration fadeOut;

                if (loc.getBlockX() >= BorderInfo.getNowPX() || loc.getBlockX() <= BorderInfo.getNowMX() || loc.getBlockZ() >= BorderInfo.getNowPZ() || loc.getBlockZ() <= BorderInfo.getNowMZ()) {
                    TextComponent textComponent = Component.text("ボーダー外!!").color(NamedTextColor.RED);
                    fadeIn = Duration.ofMillis(300);
                    stay = Duration.ofMillis(400);
                    fadeOut = Duration.ofMillis(300);
                    if (player.getGameMode().equals(GameMode.ADVENTURE) || player.getGameMode().equals(GameMode.SURVIVAL)) {
                        if (!player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
                            if (player.getHealth() - damage > 0) {
                                player.setHealth(player.getHealth() - damage);
                            } else {
                                player.damage(damage);
                            }
                        } else {
                            int duration = player.getPotionEffect(PotionEffectType.FIRE_RESISTANCE).getDuration();
                            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                            if (duration - damage * 20 > 0) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, (int) (duration - damage * 400), 0, false, true, true));
                                textComponent = Component.text("耐火の効果によりダメージ無効化中 - " + (duration / 36) + "%").color(NamedTextColor.RED);
                                fadeIn = Duration.ofMillis(0);
                                stay = Duration.ofSeconds(2);
                                fadeOut = Duration.ofMillis(0);
                            }
                        }
                        Title title = Title.title(Component.text(""), textComponent, Title.Times.times(fadeIn, stay, fadeOut));
                        player.showTitle(title);
                    }
                }
            }
        }
    }

    /**
     * ボーダーダメージを設定する
     * @param damage ダメージ値
     */
    static public void setDamage(float damage){
        BorderDamager.damage = damage;
    }

    /**
     * ボーダーダメージを取得する
     * @return ダメージ値
     */
    static public float getDamage(){ return damage; }

    /**
     * ボーダーのオンオフを設定する
     * @param power オンオフ。オンの場合はtrue
     */
    static public void setPower(boolean power){BorderDamager.power = power;}

    /**
     * ボーダーのオンオフを取得
     * @return オンオフ。オンの場合はtrue
     */
    static public boolean isPower(){ return power; }
}
