package tech.osm8.magicstorage.common.content.blocks.tile;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.zeith.terraria.client.gui.api.inventory.BigDummyInventory;
import tech.osm8.magicstorage.common.StorageSystem;
import tech.osm8.magicstorage.common.content.IStorageInventory;

public class BaseStorageTile extends TileSyncable implements IStorageInventory {
    public final BigDummyInventory items;
    public StorageSystem storageSystem;

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

    @Override
    public BigDummyInventory getInventory() {
        return items;
    }

    @Override
    public boolean placeItemStack(ItemStack stack) {
        return items.inventory.add(stack);
    }

    @Override
    public int getCapacity() {
        return items.getSizeInventory();
    }

    @Override
    public StorageSystem getSystem() {
        return storageSystem;
    }

    @Override
    public void setSystem(StorageSystem system) {
        storageSystem = system;
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
