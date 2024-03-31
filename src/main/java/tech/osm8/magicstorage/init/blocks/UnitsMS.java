package tech.osm8.magicstorage.init.blocks;

import com.zeitheron.hammercore.annotations.RegistryName;
import com.zeitheron.hammercore.annotations.SimplyRegister;
import tech.osm8.magicstorage.common.content.blocks.BaseStorageUnit;
import tech.osm8.magicstorage.common.content.blocks.tile.BaseStorageTile;


@SimplyRegister(prefix = "units/")
public interface UnitsMS {
    @RegistryName("base")
    BaseStorageUnit<BaseStorageTile.StorageUnitTile> STORAGE_UNIT_BASE = new BaseStorageUnit<>(BaseStorageTile.StorageUnitTile.class);

    @RegistryName("crimtane")
    BaseStorageUnit<BaseStorageTile.CursedStorageUnitTile> STORAGE_UNIT_CRIMTANE = new BaseStorageUnit<>(BaseStorageTile.CursedStorageUnitTile.class);

    @RegistryName("demonite")
    BaseStorageUnit<BaseStorageTile.CursedStorageUnitTile> STORAGE_UNIT_DEMONITE = new BaseStorageUnit<>(BaseStorageTile.CursedStorageUnitTile.class);

    @RegistryName("hellstone")
    BaseStorageUnit<BaseStorageTile.HellstoneStorageUnitTile> STORAGE_UNIT_HELLSTONE = new BaseStorageUnit<>(BaseStorageTile.HellstoneStorageUnitTile.class);

    @RegistryName("hallowed")
    BaseStorageUnit<BaseStorageTile.HallowedStorageUnitTile> STORAGE_UNIT_HALLOWED = new BaseStorageUnit<>(BaseStorageTile.HallowedStorageUnitTile.class);

    @RegistryName("blue_chlorophyte")
    BaseStorageUnit<BaseStorageTile.BlueChlorophyteStorageUnitTile> STORAGE_UNIT_BLUE_CHLOROPHYTE = new BaseStorageUnit<>(BaseStorageTile.BlueChlorophyteStorageUnitTile.class);

    @RegistryName("luminite")
    BaseStorageUnit<BaseStorageTile.LuminiteStorageUnitTile> STORAGE_UNIT_LUMINITE = new BaseStorageUnit<>(BaseStorageTile.LuminiteStorageUnitTile.class);

    @RegistryName("terra")
    BaseStorageUnit<BaseStorageTile.TerraStorageUnitTile> STORAGE_UNIT_TERRA = new BaseStorageUnit<>(BaseStorageTile.TerraStorageUnitTile.class);
}
