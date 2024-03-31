package tech.osm8.magicstorage.common;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import tech.osm8.magicstorage.common.content.IStorageConductor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorageSystem {
    public List<BlockPos> nodes;
    public ArrayList<BlockPos> remoteAccesses;
    public boolean isValid = true;

    public StorageSystem(IStorageConductor heart) {
        this.nodes = Collections.emptyList();
        this.remoteAccesses = new ArrayList<>();
    }

    public void writeNBT(NBTTagCompound nbt) {
        NBTTagCompound system = new NBTTagCompound();

        NBTTagList nodeNBT = new NBTTagList();
        NBTTagList remoteAccessNBT = new NBTTagList();

        nodes.forEach((x) -> nodeNBT.appendTag(new NBTTagLong(x.toLong())));
        system.setTag("Nodes", nodeNBT);

        if (!remoteAccesses.isEmpty()) {
            remoteAccesses.forEach((x) -> remoteAccessNBT.appendTag(new NBTTagLong(x.toLong())));
        }
        system.setTag("RemoteAccesses", remoteAccessNBT);

        nbt.setTag("StorageSystem", system);
    }

    public void readNBT(NBTTagCompound nbt) {
        if (nbt != null) {
            NBTTagCompound system = nbt.getCompoundTag("StorageSystem");
            NonNullList<BlockPos> newNodes = NonNullList.create();
            ArrayList<BlockPos> newRemoteAccesses = new ArrayList<>();
            NBTTagList nbtTagList = system.getTagList("Nodes", Constants.NBT.TAG_LONG);
            NBTTagList nbtTagList1 = system.getTagList("RemoteAccesses", Constants.NBT.TAG_LONG);
            for (int i = 0; i < nbtTagList.tagCount(); i++) {
                newNodes.add(BlockPos.fromLong(((NBTPrimitive) nbtTagList.get(i)).getLong()));
            }

            for (int i = 0; i < nbtTagList1.tagCount(); i++) {
                newRemoteAccesses.add(BlockPos.fromLong(((NBTPrimitive) nbtTagList1.get(i)).getLong()));
            }
            nodes = newNodes;
            remoteAccesses = newRemoteAccesses;
        }
    }

    public BlockPos getHeartPos() {
        return nodes.get(0);
    }

    public void invalidate(World world) {
        isValid = false;
        nodes.stream().skip(1).forEach((x) -> {
            TileSyncable tile = (TileSyncable) world.getTileEntity(x);
            if (tile instanceof IStorageConductor) {
                ((IStorageConductor) tile).setSystem(null);
            }
        });
    }

    public void addRemoteNode(IStorageConductor node) {
        remoteAccesses.add(node.getPos());
    }

    public void removeRemoteNode(IStorageConductor node) {
        remoteAccesses.removeIf(x -> x.equals(node.getPos()));
    }
}
