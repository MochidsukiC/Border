package jp.houlab.mochidsuki.border;

import com.comphenix.protocol.wrappers.WrappedBlockData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;


import com.comphenix.protocol.wrappers.*;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;
import java.util.*;

import java.util.HashMap;
import java.util.UUID;

import static jp.houlab.mochidsuki.border.Main.plugin;
import static jp.houlab.mochidsuki.border.Main.world;

/**
 * ボーダーの壁を表示するクラス
 * @author Mochidsuki
 */
public class BorderWallShower extends BukkitRunnable {

    public List<BlockDisplay> px = new ArrayList<>();
    public List<BlockDisplay> mx = new ArrayList<>();
    public List<BlockDisplay> pz = new ArrayList<>();
    public List<BlockDisplay> mz = new ArrayList<>();

    /**
     * 壁をコントロールするメソッドを呼び出す
     */
    @Override
    public void run() {
        controlWall(BorderInfo.getNowPX(),BorderInfo.getNowMZ(),BorderInfo.getNowPZ(),"x",px);
        controlWall(BorderInfo.getNowMX(),BorderInfo.getNowMZ(),BorderInfo.getNowPZ(),"x",mx);
        controlWall(BorderInfo.getNowPZ(),BorderInfo.getNowMX(),BorderInfo.getNowPX(),"z",pz);
        controlWall(BorderInfo.getNowMZ(),BorderInfo.getNowMX(),BorderInfo.getNowPX(),"z",mz);
    }

    /**
     * 壁のサイズと座標を計算し、生成、テレポート、サイズ設定、削除を行う
     * @param shaft 軸の座標
     * @param width 横幅のマイナス方向の座標
     * @param widthTop 横幅のプラス方向の座標
     * @param shaftCode 軸の方向を指定
     * @param blockDisplays ブロックティスプレイを保存し、次の処理に渡すリスト
     */
    private void controlWall(final double shaft,double width,double widthTop,String shaftCode,List<BlockDisplay> blockDisplays) {
        final int longK = 158; //1ブロックディスプレイの長さ
        double widthNow = width;
        int i = 0;
        for(;widthNow < widthTop;i++) {
            if(i*2 < blockDisplays.size()) {
                BlockDisplay blockDisplay = blockDisplays.get(i*2);
                BlockDisplay blockDisplay1 = blockDisplays.get(i*2+1);
                double x =0;
                double z =0;
                double sx =0;
                double sz =0;

                double x1 =0;
                double z1 =0;
                double sx1 =0;
                double sz1 =0;

                if(shaftCode.equals("x")) {
                    x = shaft;
                    z = widthNow;
                }else if(shaftCode.equals("z")) {
                    x = widthNow;
                    z = shaft;
                }
                Transformation transformation = blockDisplay.getTransformation();

                if(shaftCode.equals("x")) {
                    sx = 1;
                    sz = longK;
                }else if(shaftCode.equals("z")) {
                    sx = longK;
                    sz = 1;
                }

                widthNow = widthNow+longK;

                if(widthNow < widthTop) {

                    if(shaftCode.equals("x")) {
                        x1 = shaft;
                        z1 = widthNow;
                    }else if(shaftCode.equals("z")) {
                        x1 = widthNow;
                        z1 = shaft;
                    }


                    if(shaftCode.equals("x")) {
                        sx1 = 1;
                        sz1 = longK*-1;
                    }else if(shaftCode.equals("z")) {
                        sx1 = longK*-1;
                        sz1 = 1;
                    }

                }else {//末端処理

                    if(shaftCode.equals("x")) {
                        x1 = shaft;
                        z1 = widthTop;
                    }else if(shaftCode.equals("z")) {
                        x1 = widthTop;
                        z1 = shaft;
                    }

                    if(shaftCode.equals("x")) {
                        sx1 = 1;
                        sz1 = (widthTop-widthNow+longK)*-1; //widthTop - (width - longK)
                    }else if(shaftCode.equals("z")) {
                        sx1 = (widthTop-widthNow+longK)*-1; //widthTop - (width - longK)
                        sz1 = 1;
                    }

                    if(shaftCode.equals("x")) {
                        sz = (widthTop-widthNow+longK); //widthTop - (width - longK)
                    }else if(shaftCode.equals("z")) {
                        sx = (widthTop-widthNow+longK); //widthTop - (width - longK)
                    }
                }

                Transformation transformation1 = blockDisplay1.getTransformation();

                world.loadChunk(new Location(world, x, -70, z).getChunk());
                world.loadChunk(new Location(world, x1, -70, z1).getChunk());

                blockDisplay.teleportAsync(new Location(world, x, -70, z));
                blockDisplay1.teleportAsync(new Location(world, x1, -70, z1));

                transformation.getScale().set(sx,400,sz);
                blockDisplay.setTransformation(transformation);

                transformation1.getScale().set(sx1,400,sz1);
                blockDisplay1.setTransformation(transformation1);

            }else {
                BlockDisplay blockDisplay = world.spawn(new Location(world,0,0,0),BlockDisplay.class);
                blockDisplay.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));

                if(blockDisplays.size()-1 > i*2) {
                    blockDisplays.set(i * 2, blockDisplay);
                }else {
                    blockDisplays.add(blockDisplay);
                }

                widthNow = widthNow+longK;

                BlockDisplay blockDisplay1 = world.spawn(new Location(world,0,0,0),BlockDisplay.class);
                blockDisplay1.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));

                if(blockDisplays.size()-1 > i*2+1) {
                    blockDisplays.set(i * 2 + 1, blockDisplay1);
                }else {
                    blockDisplays.add(blockDisplay1);
                }
            }
        }

        if(blockDisplays.size() > i*2) {
            for(int ii = i; ii < blockDisplays.size(); ii++) {
                blockDisplays.get(ii).setBlock(Bukkit.createBlockData(Material.EMERALD_BLOCK));
                blockDisplays.get(ii).remove();
            }
        }





    }

}
