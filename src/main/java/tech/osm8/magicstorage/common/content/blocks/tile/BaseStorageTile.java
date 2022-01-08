package tech.osm8.magicstorage.common.content.blocks.tile;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import org.zeith.terraria.client.gui.api.inventory.BigDummyInventory;
import tech.osm8.magicstorage.common.StorageSystem;
import tech.osm8.magicstorage.common.content.IStorageInventory;

public class BaseStorageTile extends TileSyncable implements IStorageInventory {
    public final BigDummyInventory items;
    public StorageSystem storageSystem;
    private BlockPos heartPos;


    public BaseStorageTile(int slotCount) {
        this.items = new BigDummyInventory(slotCount);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        if (storageSystem != null) {
            nbt.setLong("Heart", storageSystem.getHeartPos().toLong());
        }
        items.writeToNBT(nbt);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("Heart", Constants.NBT.TAG_LONG)) {
            this.heartPos = BlockPos.fromLong(nbt.getLong("Heart"));
        }
        items.readFromNBT(nbt);
    }

    @Override
    public BigDummyInventory getInventory() {
        return items;
    }

    @Override
    public ItemStack placeItemStack(ItemStack stack) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int stackSize = stack.getMaxStackSize();
        for (int i = 0; i < items.getSizeInventory(); i++) {
            ItemStack slot = items.getStackInSlot(i);
            if (slot.isEmpty()) {
                items.setInventorySlotContents(i, stack);
                return ItemStack.EMPTY;
            } else if (slot.isItemEqual(stack) && slot.getCount() < stackSize) {
                int toAdd = Math.min(stack.getCount(), stackSize - slot.getCount());
                slot.grow(toAdd);
                stack.shrink(toAdd);
                if (stack.isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }
        }

        return stack;
    }

    @Override
    public int getCapacity() {
        return items.getSizeInventory();
    }

    @Override
    public StorageSystem getSystem() {
        if (storageSystem == null && heartPos != null) {
            TileSyncable tile = (TileSyncable) world.getTileEntity(heartPos);
            if (tile instanceof StorageHeartTile) {
                storageSystem = ((StorageHeartTile) tile).getSystem();
            }
        }
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
