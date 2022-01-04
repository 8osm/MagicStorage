package tech.osm8.magicstorage.init;

import tech.osm8.magicstorage.common.content.blocks.BaseStorageUnit;
import tech.osm8.magicstorage.common.content.blocks.StorageComponent;
import tech.osm8.magicstorage.common.content.blocks.StorageHeart;
import tech.osm8.magicstorage.common.content.blocks.tile.BaseStorageTile;

public class BlocksMS {
    public static final StorageComponent STORAGE_COMPONENT = new StorageComponent("storage_component");
    public static final StorageHeart STORAGE_HEART = new StorageHeart("storage_heart");
    public static final BaseStorageUnit<BaseStorageTile.StorageUnitTile> STORAGE_UNIT_BASE = new BaseStorageUnit<>(BaseStorageTile.StorageUnitTile.class, "units/base");
    public static final BaseStorageUnit<BaseStorageTile.CursedStorageUnitTile> STORAGE_UNIT_CRIMTANE = new BaseStorageUnit<>(BaseStorageTile.CursedStorageUnitTile.class, "units/crimtane");
    public static final BaseStorageUnit<BaseStorageTile.CursedStorageUnitTile> STORAGE_UNIT_DEMONITE = new BaseStorageUnit<>(BaseStorageTile.CursedStorageUnitTile.class, "units/demonite");
    public static final BaseStorageUnit<BaseStorageTile.HellstoneStorageUnitTile> STORAGE_UNIT_HELLSTONE = new BaseStorageUnit<>(BaseStorageTile.HellstoneStorageUnitTile.class, "units/hellstone");
    public static final BaseStorageUnit<BaseStorageTile.HallowedStorageUnitTile> STORAGE_UNIT_HALLOWED = new BaseStorageUnit<>(BaseStorageTile.HallowedStorageUnitTile.class, "units/hallowed");
    public static final BaseStorageUnit<BaseStorageTile.BlueChlorophyteStorageUnitTile> STORAGE_UNIT_BLUE_CHLOROPHYTE = new BaseStorageUnit<>(BaseStorageTile.BlueChlorophyteStorageUnitTile.class, "units/blue_chlorophyte");
    public static final BaseStorageUnit<BaseStorageTile.LuminiteStorageUnitTile> STORAGE_UNIT_LUMINITE = new BaseStorageUnit<>(BaseStorageTile.LuminiteStorageUnitTile.class, "units/luminite");
    public static final BaseStorageUnit<BaseStorageTile.TerraStorageUnitTile> STORAGE_UNIT_TERRA = new BaseStorageUnit<>(BaseStorageTile.TerraStorageUnitTile.class, "units/terra");

}