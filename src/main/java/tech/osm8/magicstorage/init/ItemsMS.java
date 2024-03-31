package tech.osm8.magicstorage.init;

import com.zeitheron.hammercore.annotations.RegistryName;
import com.zeitheron.hammercore.annotations.SimplyRegister;
import tech.osm8.magicstorage.common.content.items.DebugCuboid;
import tech.osm8.magicstorage.common.content.items.ShadowDiamond;
import tech.osm8.magicstorage.common.content.items.StorageLocator;

@SimplyRegister
public interface ItemsMS {

    @RegistryName("shadow_diamond")
    ShadowDiamond SHADOW_DIAMOND = new ShadowDiamond();

    @RegistryName("storage_locator")
    StorageLocator STORAGE_LOCATOR = new StorageLocator(false);

    @RegistryName("storage_locator_drive")
    StorageLocator STORAGE_LOCATOR_DRIVE = new StorageLocator(true);

    @RegistryName("debug_cuboid")
    DebugCuboid DEBUG_CUBOID = new DebugCuboid();
}