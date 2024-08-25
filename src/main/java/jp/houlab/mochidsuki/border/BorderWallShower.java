package jp.houlab.mochidsuki.border;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

import java.util.HashMap;

import static jp.houlab.mochidsuki.border.Main.plugin;
import static jp.houlab.mochidsuki.border.Main.world;

public class BorderWallShower extends BukkitRunnable {

    private HashMap<Player, BlockDisplay> px = new HashMap<>();
    private HashMap<Player, BlockDisplay> mx = new HashMap<>();
    private HashMap<Player, BlockDisplay> pz = new HashMap<>();
    private HashMap<Player, BlockDisplay> mz = new HashMap<>();


    @Override
    public void run() {
        for (Player player: plugin.getServer().getOnlinePlayers()) {
            Location loc = player.getLocation();
            /*
            if(Math.abs(loc.getBlockX() - BorderInfo.getNowPX()) < 80&&loc.getBlockZ() - BorderInfo.getNowPZ() < 160) {
                if(!px.containsKey(player)){//スポーン
                    BlockDisplay display = world.spawn(new Location(world,0,0,0), BlockDisplay.class);
                    px.put(player,display);
                    display.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));
                }

                int lengthK = (int) Math.sqrt(24964 - Math.pow(loc.getBlockX() - BorderInfo.getNowPX(),2));
                int z =  (loc.getBlockZ() - lengthK);//起点z
                if(z<BorderInfo.getNowMZ()){
                    z = (int) BorderInfo.getNowMZ();
                }
                px.get(player).teleport(new Location(world,BorderInfo.getNowPX(),-70,z));

                int sz =  (lengthK*2); //Size Z
                if(loc.getBlockZ() + lengthK > BorderInfo.getNowPZ()){
                    sz = (int) (sz - (loc.getBlockZ() + lengthK - BorderInfo.getNowPZ()));
                }

                Transformation transformation = px.get(player).getTransformation();
                transformation.getScale().set(1,400,sz);
                px.get(player).setTransformation(transformation);

            }else{
                if(px.containsKey(player)) {
                    px.get(player).remove();
                    px.remove(player);
                }
            }

             */

            controlWall(player,BorderInfo.getNowPX(),loc.getX(),BorderInfo.getNowMZ(), loc.getZ(), BorderInfo.getNowPZ(),px,"x");
            controlWall(player,BorderInfo.getNowMX(),loc.getX(),BorderInfo.getNowMZ(), loc.getZ(), BorderInfo.getNowPZ(),mx,"x");
            controlWall(player,BorderInfo.getNowPZ(),loc.getZ(),BorderInfo.getNowMX(), loc.getX(), BorderInfo.getNowPX(),pz,"z");
            controlWall(player,BorderInfo.getNowMZ(),loc.getZ(),BorderInfo.getNowMX(), loc.getX(), BorderInfo.getNowPX(),mz,"z");

        }
    }


    private void controlWall(Player player,double shaft,double shaftPLLoc,double width,double widthPLLoc,double widthTop,HashMap<Player, BlockDisplay> hashMap,String shaftCode){
        //コメントはshaft = PX で想定
        if(Math.abs(shaftPLLoc - shaft) < 160&&widthPLLoc - widthTop < 160) {
            if(!hashMap.containsKey(player)){//スポーン
                BlockDisplay display = world.spawn(new Location(world,0,0,0), BlockDisplay.class);
                hashMap.put(player,display);
                display.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));
                display.setBrightness(new Display.Brightness(15,15));
            }

            double lengthK = Math.sqrt(24964 - Math.pow(shaftPLLoc - shaft,2));//三平方の定理より、距離から必要な横幅を算出。半径158。
            double z = widthPLLoc - lengthK;//起点z
            if(z<width){
                z = width;
            }
            if(shaftCode.equals("x")){
                try {
                    hashMap.get(player).teleport(new Location(world,shaft,-70,z));
                }catch (Exception e){}
            }else if(shaftCode.equals("z")){
                try {
                    hashMap.get(player).teleport(new Location(world,z,-70,shaft));
                }catch (Exception e){}
            }

            double sz =  (lengthK*2); //Size Z
            if(widthPLLoc + lengthK > widthTop){
                sz =  (sz - (widthPLLoc + lengthK - widthTop));
            }
            //player.sendMessage(hashMap.get(player).getLocation().toString());
            //サイズ変更
            Transformation transformation = hashMap.get(player).getTransformation();
            if(shaftCode.equals("x")){
                transformation.getScale().set(1,400,sz);
            }else if(shaftCode.equals("z")){
                transformation.getScale().set(sz,400,1);
            }
            hashMap.get(player).setTransformation(transformation);

        }else{
            if(hashMap.containsKey(player)) {
                hashMap.get(player).remove();
                hashMap.remove(player);
            }
        }
    }
}
