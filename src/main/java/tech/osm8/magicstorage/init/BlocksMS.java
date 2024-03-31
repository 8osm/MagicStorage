package tech.osm8.magicstorage.init;

import com.zeitheron.hammercore.annotations.RegistryName;
import com.zeitheron.hammercore.annotations.SimplyRegister;
import tech.osm8.magicstorage.common.content.blocks.BaseStorageUnit;
import tech.osm8.magicstorage.common.content.blocks.StorageComponent;
import tech.osm8.magicstorage.common.content.blocks.StorageHeart;
import tech.osm8.magicstorage.common.content.blocks.StorageRemoteAccess;
import tech.osm8.magicstorage.common.content.blocks.tile.BaseStorageTile;

@SimplyRegister
public interface BlocksMS {

    @RegistryName("storage_component")
    StorageComponent STORAGE_COMPONENT = new StorageComponent();

    @RegistryName("storage_heart")
    StorageHeart STORAGE_HEART = new StorageHeart();

    @RegistryName("storage_remote_access")
    StorageRemoteAccess STORAGE_REMOTE_ACCESS = new StorageRemoteAccess();
}