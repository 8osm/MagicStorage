package tech.osm8.magicstorage.common.content;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tech.osm8.magicstorage.common.StorageSystem;

import java.util.ArrayList;
import java.util.List;

public interface IStorageConductor extends IStorageNode {
    default List<IStorageConductor> getNeighbors(World world, BlockPos blockPos) {
        List<IStorageConductor> adjacent = new ArrayList<>();

        for (EnumFacing facing : EnumFacing.values()) {
            TileEntity tile = world.getTileEntity(blockPos.offset(facing));
            if (tile instanceof IStorageConductor) {
                adjacent.add((IStorageConductor) tile);
            }
        }
        return adjacent;
    }

    BlockPos getPos();

    default boolean equals(IStorageConductor node) {
        return this.getPos().equals(node.getPos());
    }
}
