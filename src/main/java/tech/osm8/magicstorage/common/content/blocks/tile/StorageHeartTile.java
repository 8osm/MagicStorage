package tech.osm8.magicstorage.common.content.blocks.tile;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.zeith.terraria.common.container.ContainerChest;
import tech.osm8.magicstorage.client.gui.ContainerTerminal;
import tech.osm8.magicstorage.client.gui.GuiTerminal;
import tech.osm8.magicstorage.common.StorageSystem;
import tech.osm8.magicstorage.common.content.IStorageConductor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorageHeartTile extends TileSyncable implements IStorageConductor {

    public final StorageSystem storageSystem;

    public StorageHeartTile() {
        this.storageSystem = new StorageSystem(this);
    }

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
        return storageSystem;
    }

    @Override
    public Object getServerGuiElement(EntityPlayer player) {
        return new ContainerTerminal(player);
    }

    @Override
    public Object getClientGuiElement(EntityPlayer player) {
        return new GuiTerminal((ContainerTerminal) this.getServerGuiElement(player));
    }

    public void onHeartDestroy() {
        storageSystem.invalidate(world);
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    public void defineSystem() {
        List<BlockPos> system = new ArrayList<>();
        system.add(getPos());
        storageSystem.remoteAccesses.removeIf(x -> {
            TileEntity remote = world.getTileEntity(x);
            return !(remote instanceof IStorageConductor && ((IStorageConductor) remote).getSystem() != null && ((IStorageConductor) remote).getSystem().getHeartPos().equals(getPos()));
        });
        system.addAll(storageSystem.remoteAccesses);
        storageSystem.invalidate(world);
        for (int i = 0; i < system.size(); i++) {
            BlockPos current = system.get(i);
            for (EnumFacing facing : EnumFacing.values()) {
                BlockPos adjacent = current.offset(facing);
                if (system.contains(adjacent)) {
                    continue;
                }
                TileEntity tile = world.getTileEntity(adjacent);
                if (tile instanceof IStorageConductor && !(tile instanceof StorageRemoteAccessTile)) {
                    IStorageConductor conductor = (IStorageConductor) tile;
                    system.add(tile.getPos());
                    conductor.setSystem(storageSystem);
                }
            }
            TileEntity remote = world.getTileEntity(current);
            if (remote instanceof StorageRemoteAccessTile)
                ((StorageRemoteAccessTile) remote).updateSystem(storageSystem);
        }
        storageSystem.nodes = Collections.unmodifiableList(system);
    }

    @Override
    public void setSystem(StorageSystem system) {
    }
}
