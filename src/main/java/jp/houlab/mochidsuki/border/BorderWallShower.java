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

import jp.houlab.mochidsuki.border.packetwrapper.*;

import static jp.houlab.mochidsuki.border.Main.plugin;
import static jp.houlab.mochidsuki.border.Main.world;

public class BorderWallShower extends BukkitRunnable {

    public List<BlockDisplay> px = new ArrayList<>();
    public List<BlockDisplay> mx = new ArrayList<>();
    public List<BlockDisplay> pz = new ArrayList<>();
    public List<BlockDisplay> mz = new ArrayList<>();
    @Override
    public void run() {


        controlWall(BorderInfo.getNowPX(),BorderInfo.getNowMZ(),BorderInfo.getNowPZ(),"x",px);
        controlWall(BorderInfo.getNowMX(),BorderInfo.getNowMZ(),BorderInfo.getNowPZ(),"x",mx);
        controlWall(BorderInfo.getNowPZ(),BorderInfo.getNowMX(),BorderInfo.getNowPX(),"z",pz);
        controlWall(BorderInfo.getNowMZ(),BorderInfo.getNowMX(),BorderInfo.getNowPX(),"z",mz);


    }


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

class DisplayEntity{

    private Player player;
    private EntityType entityType;
    private int entityId;

    DisplayEntity(Player player, EntityType type, int entityId) {
        this.player = player;
        this.entityType = type;
        this.entityId = entityId;
    }



    public void spawnEntity(Location loc) {
        WrapperPlayServerSpawnEntity wrapper = new WrapperPlayServerSpawnEntity();
        wrapper.setEntityID(this.entityId);
        wrapper.setUniqueId(UUID.randomUUID());
        wrapper.setType(entityType);
        wrapper.setX(loc.getX());
        wrapper.setY(loc.getY());;
        wrapper.setZ(loc.getZ());
        wrapper.sendPacket(player);
    }
    public void setBlockMaterial(Material material) {
        WrapperPlayServerEntityMetadata packet = new WrapperPlayServerEntityMetadata();
        packet.setEntityID(entityId);
        List<WrappedDataValue> dataValues = new ArrayList<>();
        dataValues.add(new WrappedDataValue(23,WrappedDataWatcher.Registry.get(Integer.class) , WrappedBlockData.createData(material).getHandle()));
        packet.setMetadata(dataValues);
        packet.sendPacket(player);
    }
    public void setScale(Vector3f scale) {
        WrapperPlayServerEntityMetadata packet = new WrapperPlayServerEntityMetadata();
        packet.setEntityID(entityId);
        List<WrappedDataValue> dataValues = new ArrayList<>();
        dataValues.add(new WrappedDataValue(12,WrappedDataWatcher.Registry.get(Float.class) ,scale.x));
        dataValues.add(new WrappedDataValue(12,WrappedDataWatcher.Registry.get(Float.class) ,scale.y));
        dataValues.add(new WrappedDataValue(12,WrappedDataWatcher.Registry.get(Float.class) ,scale.z));
        packet.setMetadata(dataValues);
        packet.sendPacket(player);
    }
    /*
    private fun removeEntity() {
        val packet = WrapperPlayServerEntityDestroy()
        packet.entityIds = IntList.of(entityID)
        packet.sendPacket(player)
    }

     */
}

/*
if (Math.abs(shaftPLLoc - shaft) < 160 && widthPLLoc - widthTop < 160) {
            if (!hashMap.containsKey(player)) {//スポーン
                BlockDisplay display = world.spawn(new Location(world, 0, 0, 0), BlockDisplay.class);
                hashMap.put(player, display);
                display.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));
                display.setBrightness(new Display.Brightness(15, 15));
            }

            Location location = new Location(world, 0, 0, 0);
            Vector3f vector = new Vector3f(0, 0, 0);

            double lengthK = Math.sqrt(24964 - Math.pow(shaftPLLoc - shaft, 2));//三平方の定理より、距離から必要な横幅を算出。半径158。
            double z = widthPLLoc - lengthK;//起点z
            if (z < width) {
                z = width;
            }
            if (shaftCode.equals("x")) {
                try {
                    location = new Location(world, shaft, -70, z);
                } catch (Exception e) {
                }
            } else if (shaftCode.equals("z")) {
                try {
                    location = new Location(world, z, -70, shaft);
                } catch (Exception e) {
                }
            }

            double sz = (lengthK * 2); //Size Z
            if (widthPLLoc + lengthK > widthTop) {
                sz = (sz - (widthPLLoc + lengthK - widthTop));
            }
            //player.sendMessage(hashMap.get(player).getLocation().toString());
            //サイズ変更
            Transformation transformation = hashMap.get(player).getTransformation();
            if (shaftCode.equals("x")) {
                vector = new Vector3f(1, 400, (float) sz);
            } else if (shaftCode.equals("z")) {
                vector = new Vector3f((float) sz, 400, 1);
            }
            //hashMap.get(player).setTransformation(transformation);

            //sendWallPacket(player,location,vector,EntityType.BLOCK_DISPLAY,i);

            DisplayEntity displayEntity = new DisplayEntity(player,EntityType.BLOCK_DISPLAY,20000+i);
            displayEntity.spawnEntity(location);
            displayEntity.setScale(vector);
            displayEntity.setBlockMaterial(Material.RED_STAINED_GLASS);

        } else {
            if (hashMap.containsKey(player)) {
                hashMap.get(player).remove();
                hashMap.remove(player);
            }
        }
 */




/*

    public void sendWallPacket(Player player, Location loc, Vector3f size, EntityType entityType, int entityIdPlus) {
        int entityId = 20000 + entityIdPlus;

            entityId++;

            PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);

            packet.getIntegers().write(0, entityId);
            packet.getUUIDs().write(0, UUID.randomUUID());
            packet.getEntityTypeModifier().write(0, entityType);
            packet.getIntegers().write(1, 1);

            packet.getDoubles()
                    .write(0, loc.getX())
                    .write(1, loc.getY())
                    .write(2, loc.getZ());

        PacketContainer packet0 = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet0.getIntegers().write(0, entityId);


        // deleted some irrelevant code here that utilizes the parameter bitmask (100% no errors here)

        WrappedDataWatcher.Serializer integerSerializer = WrappedDataWatcher.Registry.get(Integer.class);
        WrappedDataWatcher.Serializer floatSerializer = WrappedDataWatcher.Registry.get(Float.class);
        WrappedDataWatcher.Serializer vector3fSerializer = WrappedDataWatcher.Registry.get(Vector3f.class);

        List<WrappedDataValue> dataValues = new ArrayList<>();


        dataValues.add(new WrappedDataValue(20, floatSerializer, 1.0F));
        dataValues.add(new WrappedDataValue(21, floatSerializer, 1.0F));
        dataValues.add(new WrappedDataValue(12, vector3fSerializer,size));
        dataValues.add(new WrappedDataValue(23, integerSerializer, 1)); // 1 is the block state id of stone


        packet0.getDataValueCollectionModifier().write(0, dataValues);


/*
        EasyMetadataPacket metadata = new EasyMetadataPacket(null);
        metadata.write(23,1);
        packet0.getWatchableCollectionModifier().write(0, metadata.export());


        try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet0);
            } catch (Exception ignored) {
        }


        }
 */

/*

        if(blockDisplays.size()<(times+1)*2) {//BlockDisplaysに不足している壁を追加
            for(int i = 1;i<=(times+1)*2 - blockDisplays.size();i++) {
                BlockDisplay blockDisplay = world.spawn(new Location(world,0,0,0),BlockDisplay.class);
                blockDisplay.setBrightness(new Display.Brightness(15,15));
                blockDisplay.setBlock(Bukkit.createBlockData(Material.RED_STAINED_GLASS));
                blockDisplays.add(blockDisplay);

            }
        }
        if(blockDisplays.size()>(times+1)*2) {//BlockDisplaysで余った壁の除去
            for(int i = 1;i<=blockDisplays.size() - (times+1)*2;i++) {
                //blockDisplays.get((times+i)*2-1).remove();
                //blockDisplays.get((times+i)*2).remove();
                //blockDisplays.remove((times+i)*2-1);
                //blockDisplays.remove((times+i)*2);
            }
        }

        for(int i = 0;i<times;i++) {
            int di = i*2;
            int dii = i*2+1;
            BlockDisplay blockDisplay = blockDisplays.get(di);
            BlockDisplay blockDisplay1 =blockDisplays.get(dii);
            if(shaftCode.equals("x")) {
                Location dl = new Location(world, shaft, -70, widthNow);
                blockDisplay.teleport(dl);
                Transformation transformation = blockDisplay.getTransformation();
                transformation.getScale().set(1,400,longK);
                blockDisplay.setTransformation(transformation);
                widthNow = widthNow+longK;
                blockDisplay1.teleport(new Location(world, shaft, -70, widthNow));
                Transformation transformation1 = blockDisplay1.getTransformation();
                transformation1.getScale().set(1,400,longK*-1);
                blockDisplay1.setTransformation(transformation1);

                plugin.getServer().getPlayer("Mochidsuki").sendMessage(di+"|"+blockDisplay.getLocation());
                plugin.getServer().getPlayer("Mochidsuki").sendMessage(dii+"|"+blockDisplay1.getLocation());

            }else if(shaftCode.equals("z")) {
                blockDisplay.teleport(new Location(world, widthNow, -70, shaft));
                blockDisplay1.teleport(new Location(world, widthNow+longK, -70, shaft));
            }
        }

        BlockDisplay blockDisplay = blockDisplays.get((times+1)*2-1);
        BlockDisplay blockDisplay1 = blockDisplays.get((times+1)*2-2);
        if(shaftCode.equals("x")) {
            blockDisplay.teleport(new Location(world, shaft, -70, widthNow));
            Transformation transformation = blockDisplay.getTransformation();
            transformation.getScale().set(1,400,(BorderInfo.getNowRadius()*2)%longK);
            blockDisplay.setTransformation(transformation);

            blockDisplay1.teleport(new Location(world, shaft, -70, widthNow+(BorderInfo.getNowRadius()*2)%longK));
            Transformation transformation1 = blockDisplay1.getTransformation();
            transformation1.getScale().set(1,400,((BorderInfo.getNowRadius()*2)%longK)*-1);
            blockDisplay1.setTransformation(transformation1);

        }else if(shaftCode.equals("z")) {
            blockDisplay.teleport(new Location(world, widthNow, -70, shaft));
            blockDisplay1.teleport(new Location(world, widthNow+(BorderInfo.getNowRadius()*2)%longK, -70, shaft));
        }

 */