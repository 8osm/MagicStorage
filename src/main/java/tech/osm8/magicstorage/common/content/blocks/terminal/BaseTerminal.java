package tech.osm8.magicstorage.common.content.blocks.terminal;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import tech.osm8.magicstorage.common.StorageSystem;
import tech.osm8.magicstorage.common.content.IStorageConductor;

public class BaseTerminal extends TileSyncable implements IStorageConductor {

    public StorageSystem storageSystem;
    public BlockPos heart;

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        storageSystem.writeNBT(nbt);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        storageSystem.readNBT(nbt);
    }

    @Override
    public StorageSystem getSystem() {
        return null;
    }

    @Override
    public void setSystem(StorageSystem system) {
        storageSystem = system;
    }
}
