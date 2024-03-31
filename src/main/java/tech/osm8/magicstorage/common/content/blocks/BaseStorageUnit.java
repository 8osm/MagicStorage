package tech.osm8.magicstorage.common.content.blocks;

import com.zeitheron.hammercore.api.ITileBlock;
import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.zeith.terraria.common.content.blocks.base.BlockBaseTC;
import tech.osm8.magicstorage.common.content.blocks.tile.BaseStorageTile;
import tech.osm8.magicstorage.common.content.blocks.tile.StorageHeartTile;
import tech.osm8.magicstorage.init.ItemTabsMS;

public class BaseStorageUnit<T extends BaseStorageTile> extends BlockBaseTC implements ITileBlock<T> {

    private final Class<T> tileType;
    public static final PropertyDirection FACING = BlockHorizontal.FACING;


    public BaseStorageUnit(Class<T> tileType) {
        super(Material.ROCK);
        minToolPower = 1;
        toolMineModifier = 0.01f;
        inTab(ItemTabsMS.UNITS_TAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.tileType = tileType;
    }

    @Override
    public Class<T> getTileClass() {
        return tileType;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        TileSyncable tile = (TileSyncable) worldIn.getTileEntity(pos);
        if (tile instanceof BaseStorageTile) {
            BaseStorageTile storageTile = (BaseStorageTile) tile;
            if (storageTile.storageSystem != null) {
                TileSyncable heart = (TileSyncable) worldIn.getTileEntity(storageTile.storageSystem.getHeartPos());
                if (heart instanceof StorageHeartTile) ((StorageHeartTile) heart).defineSystem();
            }
        }
    }
}
