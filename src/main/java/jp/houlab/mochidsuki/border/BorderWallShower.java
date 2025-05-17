package jp.houlab.mochidsuki.border;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;


import org.bukkit.util.Transformation;

import java.util.*;

import static jp.houlab.mochidsuki.border.Main.plugin;
import static jp.houlab.mochidsuki.border.Main.world;

/**
 * ボーダーの壁を表示するクラス
 * @author Mochidsuki
 */
public class BorderWallShower extends BukkitRunnable {

    //p - プラス m - マイナス
    //x,z - 軸
    //b - 戻しディスプレイ。blockDisplay1の格納先
    public List<List<BlockDisplay>> px = new ArrayList<>();
    public List<List<BlockDisplay>> pxb = new ArrayList<>();
    public List<List<BlockDisplay>> mx = new ArrayList<>();
    public List<List<BlockDisplay>> mxb = new ArrayList<>();
    public List<List<BlockDisplay>> pz = new ArrayList<>();
    public List<List<BlockDisplay>> pzb = new ArrayList<>();
    public List<List<BlockDisplay>> mz = new ArrayList<>();
    public List<List<BlockDisplay>> mzb = new ArrayList<>();

    public void setHeight(int height) {
        this.height = height;
    }

    private int height;

    public BorderWallShower(int height) {
        this.height = height;
    }


    /**
     * 壁をコントロールするメソッドを呼び出す
     */
    @Override
    public void run() {
        controlWall(BorderInfo.getNowPX(),BorderInfo.getNowMZ(),BorderInfo.getNowPZ(),"x",px,pxb);
        controlWall(BorderInfo.getNowMX(),BorderInfo.getNowMZ(),BorderInfo.getNowPZ(),"x",mx,mxb);
        controlWall(BorderInfo.getNowPZ(),BorderInfo.getNowMX(),BorderInfo.getNowPX(),"z",pz,pzb);
        controlWall(BorderInfo.getNowMZ(),BorderInfo.getNowMX(),BorderInfo.getNowPX(),"z",mz,mzb);

        /*
        Issueメモ
        折り返しが生成されていない。
         */
    }





    /**
     * 壁のサイズと座標を計算し、生成、テレポート、サイズ設定、削除を行う
     * @param shaft 軸の座標
     * @param width 横幅のマイナス方向の座標
     * @param widthTop 横幅のプラス方向の座標
     * @param shaftCode 軸の方向を指定
     * @param blockDisplaysList ブロックティスプレイを保存し、次の処理に渡すリスト
     */
    private void controlWall(final double shaft,double width,double widthTop,String shaftCode,List<List<BlockDisplay>> blockDisplaysList,List<List<BlockDisplay>> blockDisplaysList1) {
        final int longK = 80; //1ブロックディスプレイの長さ
        final int heightK = 40;
        double widthNow = width;
        int i = 0;

        for(;widthNow < widthTop;i++) {
            if(i < blockDisplaysList.size()) {
                List<BlockDisplay> blockDisplays = blockDisplaysList.get(i);
                List<BlockDisplay> blockDisplays1 = blockDisplaysList1.get(i);
                for(int j = 0; j < blockDisplays.size(); j++) {
                    BlockDisplay blockDisplay = blockDisplays.get(j);
                    BlockDisplay blockDisplay1 = blockDisplays1.get(j);

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
                    if (shaftCode.equals("x")) {
                        sx = 1;
                        sz = longK;
                    } else if (shaftCode.equals("z")) {
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
                            sz1 = (widthTop - widthNow + longK)*-1;
                        }else if(shaftCode.equals("z")) {
                            sx1 = (widthTop - widthNow + longK)*-1;
                            sz1 = 1;
                        }

                        if(shaftCode.equals("x")) {
                            sz = (widthTop - widthNow + longK);
                        }else if(shaftCode.equals("z")) {
                            sx = (widthTop - widthNow + longK);
                        }
                    }

                    widthNow = widthNow-longK;

                    Transformation transformation1 = blockDisplay1.getTransformation();

                    blockDisplay.getChunk().addPluginChunkTicket(plugin);
                    blockDisplay1.getChunk().addPluginChunkTicket(plugin);
                    new Location(world, x, -70+j*heightK, z).getChunk().addPluginChunkTicket(plugin);
                    new Location(world, x1, -70+j*heightK, z1).getChunk().addPluginChunkTicket(plugin);


                    blockDisplay.teleport(new Location(world, x, -70+j*heightK, z));
                    blockDisplay1.teleport(new Location(world, x1, -70+j*heightK, z1));

                    transformation.getScale().set(sx,-1*heightK,sz);
                    blockDisplay.setTransformation(transformation);

                    transformation1.getScale().set(sx1,-1*heightK,sz1);
                    blockDisplay1.setTransformation(transformation1);

                }
                widthNow = widthNow+longK;
            }else {
                List<BlockDisplay> blockDisplays = new ArrayList<>();
                List<BlockDisplay> blockDisplays1 = new ArrayList<>();
                for (int j = 0; j < 10; j++){
                    new Location(world,0,0,0).getChunk().addPluginChunkTicket(plugin);
                    BlockDisplay blockDisplay = world.spawn(new Location(world,0,0,0),BlockDisplay.class);
                    blockDisplay.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));

                    blockDisplays.add(j, blockDisplay);

                    widthNow = widthNow+longK;

                    BlockDisplay blockDisplay1 = world.spawn(new Location(world,0,0,0),BlockDisplay.class);
                    blockDisplay1.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));

                    blockDisplays1.add(j, blockDisplay1);

                }
                blockDisplaysList.add(blockDisplays);
                blockDisplaysList1.add(blockDisplays1);
            }
        }

        if(blockDisplaysList.size() > i) {

        }





    }

}
