package jp.houlab.mochidsuki.border;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BukkitConverters;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import jp.houlab.mochidsuki.border.packetwrapper.WrapperPlayServerEntityMetadata;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrowMetadataListener extends PacketAdapter {
    static Set< Integer > editMetadata = new HashSet<>( ) ; // 編集するエンティティ ID エンティティを指定するために使用されます

    public ArrowMetadataListener ( JavaPlugin plugin ) {
        super ( plugin, PacketType. Play . Server . ENTITY_METADATA ) ;
    }
    @Override
    public void onPacketSending ( PacketEvent event ) {
        WrapperPlayServerEntityMetadata metadata = new WrapperPlayServerEntityMetadata ( event. getPacket ( ) ) ;

        if ( editMetadata. contains ( metadata. getEntityID ( ) ) ) {

            WrappedDataWatcher. Serializer itemStackSerializer = WrappedDataWatcher. Registry . getItemStackSerializer ( false ) ;

            WrappedDataValue itemStackObject = new WrappedDataValue ( 22 , itemStackSerializer , BukkitConverters.getItemStackConverter ( ) . getGeneric ( new ItemStack( Material.BRICK_STAIRS ) ) ) ; event.getPacket ( ) . getDataValueCollectionModifier ( ) . write ( 0 , List.of(itemStackObject) ) ;
        }
    }

}





