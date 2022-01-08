package tech.osm8.magicstorage.common.content.blocks.tile;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.nbt.NBTTagCompound;
import tech.osm8.magicstorage.common.StorageSystem;
import tech.osm8.magicstorage.common.content.IStorageConductor;

public class StorageRemoteAccessTile extends TileSyncable implements IStorageConductor {
    public StorageSystem storageSystem;

    public StorageRemoteAccessTile() {
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {

    }

    @Override
    public void readNBT(NBTTagCompound nbt) {

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
        }
    }

    public void updateSystem(StorageSystem system) {
        storageSystem = system;
    }

    @Override
    public StorageSystem getSystem() {
        return storageSystem;
    }
}
