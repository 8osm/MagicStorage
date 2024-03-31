package tech.osm8.magicstorage.common.content.blocks.tile;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import tech.osm8.magicstorage.common.StorageSystem;
import tech.osm8.magicstorage.common.content.IStorageConductor;

public class StorageRemoteAccessTile extends TileSyncable implements IStorageConductor {
    public StorageSystem storageSystem;
    private BlockPos heartPos;


    public StorageRemoteAccessTile() {
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        if (storageSystem != null) {
            nbt.setLong("Heart", storageSystem.getHeartPos().toLong());
        }
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("Heart", Constants.NBT.TAG_LONG)) {
            this.heartPos = BlockPos.fromLong(nbt.getLong("Heart"));
        }
    }

    public void onDestroy() {
        if (storageSystem != null) {
            storageSystem.removeRemoteNode(this);
            storageSystem.invalidate(world);
        }
    }

    @Override
    public void setSystem(StorageSystem system) {
        storageSystem = system;
        if (system != null) {
            storageSystem.addRemoteNode(this);
            TileEntity tile = world.getTileEntity(storageSystem.getHeartPos());
            if (tile instanceof StorageHeartTile) {
                ((StorageHeartTile) tile).defineSystem();
            }
        }
    }

    public void updateSystem(StorageSystem system) {
        storageSystem = system;
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
}
