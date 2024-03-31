package tech.osm8.magicstorage.common.content.blocks;

import com.zeitheron.hammercore.api.ITileBlock;
import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.zeith.terraria.api.items.ItemRarity;
import org.zeith.terraria.api.npc.Economy;
import org.zeith.terraria.common.content.blocks.base.BlockBaseTC;
import tech.osm8.magicstorage.common.content.blocks.tile.StorageHeartTile;
import tech.osm8.magicstorage.common.content.blocks.tile.StorageRemoteAccessTile;
import tech.osm8.magicstorage.init.ItemTabsMS;

public class StorageRemoteAccess extends BlockBaseTC implements ITileBlock<StorageRemoteAccessTile> {
    public StorageRemoteAccess() {
        super(Material.ROCK);
        this.withValue(Economy.GOLD_VALUE + Economy.SILVER_VALUE * 72 + Economy.COPPER_VALUE * 50);
        this.withRarity(ItemRarity.BLUE);
        minToolPower = 1;
        toolMineModifier = 0.01f;
        inTab(ItemTabsMS.MOD_TAB);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public Class<StorageRemoteAccessTile> getTileClass() {
        return StorageRemoteAccessTile.class;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        TileSyncable tile = (TileSyncable) worldIn.getTileEntity(pos);
        if (tile instanceof StorageRemoteAccessTile) {
            StorageRemoteAccessTile storageTile = (StorageRemoteAccessTile) tile;
            if (storageTile.storageSystem != null) {
                TileSyncable heart = (TileSyncable) worldIn.getTileEntity(storageTile.storageSystem.getHeartPos());
                if (heart instanceof StorageHeartTile) ((StorageHeartTile) heart).defineSystem();
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileSyncable tile = (TileSyncable) worldIn.getTileEntity(pos);
        if (tile instanceof StorageRemoteAccessTile) {
            ((StorageRemoteAccessTile) tile).onDestroy();
        }
        super.breakBlock(worldIn, pos, state);
    }
}
