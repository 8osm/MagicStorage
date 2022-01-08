package tech.osm8.magicstorage.init;

import tech.osm8.magicstorage.common.content.items.DebugCuboid;
import tech.osm8.magicstorage.common.content.items.ShadowDiamond;
import tech.osm8.magicstorage.common.content.items.StorageLocator;

public class ItemsMS {
    public static final ShadowDiamond SHADOW_DIAMOND = new ShadowDiamond("shadow_diamond");
    public static final StorageLocator STORAGE_LOCATOR = new StorageLocator("storage_locator", false);
    public static final StorageLocator STORAGE_LOCATOR_DRIVE = new StorageLocator("storage_locator_drive", true);
    public static final DebugCuboid DEBUG_CUBOID = new DebugCuboid("debug_cuboid");
}