package jp.houlab.mochidsuki.border;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import jp.houlab.mochidsuki.border.BorderInfo.*;
import org.bukkit.util.Transformation;

import java.lang.reflect.InvocationTargetException;

import static jp.houlab.mochidsuki.border.Main.*;

/**
 * ボーダーの収縮を行うクラス
 * @author Mochidsuki
 */
public class BorderShrinkSystem extends BukkitRunnable {
    private int time;
    private double nextX;// 次の中心x m/t
    private double nextZ;// 次の中心z m/t
    private double nextRadius;// 次半径
    private double speedX;// 中心のx移動速度 m/t
    private double speedZ;// 中心のz移動速度 m/t
    private double speedRadius;// 半径縮小速度 m/t

    /**
     * BorderShrinkSystemのコンストラクタ
     * @param time 収縮にかかる時間
     * @param nextX 次のボーダーの中心座標のx座標
     * @param nextZ 次のボーダーの中心座標のz座標
     * @param nextRadius 次のボーダーの半径
     */
    public BorderShrinkSystem(int time,double nextX,double nextZ,double nextRadius) {
        this.time = time;
        this.nextX = nextX;
        this.nextZ = nextZ;
        this.nextRadius = nextRadius;

        this.speedX = (this.nextX - BorderInfo.getNowCenterX())/(this.time);
        this.speedZ = (this.nextZ - BorderInfo.getNowCenterZ())/(this.time);
        this.speedRadius = (BorderInfo.getNowRadius() - this.nextRadius)/(this.time);
    }
    public void run(){
        //中心の更新
        BorderInfo.setNowCenterX(BorderInfo.getNowCenterX()+speedX);
        BorderInfo.setNowCenterZ(BorderInfo.getNowCenterZ()+speedZ);
        //各辺の更新
        BorderInfo.setNowPX(BorderInfo.getNowCenterX()+BorderInfo.getNowRadius());
        BorderInfo.setNowMX(BorderInfo.getNowCenterX()-BorderInfo.getNowRadius());
        BorderInfo.setNowPZ(BorderInfo.getNowCenterZ()+BorderInfo.getNowRadius());
        BorderInfo.setNowMZ(BorderInfo.getNowCenterZ()-BorderInfo.getNowRadius());

        BorderInfo.setNowRadius(BorderInfo.getNowRadius()-speedRadius);
        //ブロックディスプレイの更新


        time--;
        if(time == 0){
            cancel();
        }
    }

    /**
     * ボーダーを初期化
     * ボーダーの大きさと場所を強制的に指定
     * @param centerX 中心のx座標
     * @param centerZ 中心のz座標
     * @param radius 半径
     */
    static public void Initializer(double centerX,double centerZ,double radius){
        BorderInfo.setNowCenterX(centerX);
        BorderInfo.setNowCenterZ(centerZ);
        BorderInfo.setNowRadius(radius);
        BorderInfo.setNowPX(centerX+radius);
        BorderInfo.setNowMX(centerX-radius);
        BorderInfo.setNowPZ(centerZ+radius);
        BorderInfo.setNowMZ(centerZ-radius);
    }
}