package tech.osm8.magicstorage.common.content.blocks.tile;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.nbt.NBTTagCompound;
import org.zeith.terraria.client.gui.api.inventory.BigDummyInventory;

public class BaseStorageTile extends TileSyncable {
    public final BigDummyInventory items;

    public BaseStorageTile(int slotCount) {
        this.items = new BigDummyInventory(slotCount);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        items.writeToNBT(nbt);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        items.readFromNBT(nbt);
    }

    public static class StorageUnitTile extends BaseStorageTile {
        public StorageUnitTile() {
            super(40);
        }
    }

    public static class CursedStorageUnitTile extends BaseStorageTile {
        public CursedStorageUnitTile() {
            super(80);
        }
    }

    public static class HellstoneStorageUnitTile extends BaseStorageTile {
        public HellstoneStorageUnitTile() {
            super(120);
        }
    }

    public static class HallowedStorageUnitTile extends BaseStorageTile {
        public HallowedStorageUnitTile() {
            super(160);
        }
    }

    public static class BlueChlorophyteStorageUnitTile extends BaseStorageTile {
        public BlueChlorophyteStorageUnitTile() {
            super(240);
        }
    }

    public static class LuminiteStorageUnitTile extends BaseStorageTile {
        public LuminiteStorageUnitTile() {
            super(320);
        }
    }

    public static class TerraStorageUnitTile extends BaseStorageTile {
        public TerraStorageUnitTile() {
            super(640);
        }
    }

}
