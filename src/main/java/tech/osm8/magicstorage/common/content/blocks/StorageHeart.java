package tech.osm8.magicstorage.common.content.blocks;

import com.zeitheron.hammercore.api.ITileBlock;
import com.zeitheron.hammercore.internal.GuiManager;
import com.zeitheron.hammercore.tile.TileSyncable;
import com.zeitheron.hammercore.utils.base.Cast;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.zeith.terraria.api.items.ItemRarity;
import org.zeith.terraria.api.npc.Economy;
import org.zeith.terraria.common.content.blocks.base.BlockBaseTC;
import org.zeith.terraria.init.ItemsTC;
import tech.osm8.magicstorage.common.content.IStorageConductor;
import tech.osm8.magicstorage.common.content.blocks.tile.StorageHeartTile;
import tech.osm8.magicstorage.init.BlocksMS;
import tech.osm8.magicstorage.init.ItemTabsMS;
import tech.osm8.magicstorage.init.ItemsMS;

public class StorageHeart extends BlockBaseTC implements ITileBlock<StorageHeartTile> {
    public StorageHeart() {
        super(Material.ROCK);
        this.withValue(Economy.SILVER_VALUE);
        this.withRarity(ItemRarity.BLUE);
        minToolPower = 1;
        toolMineModifier = 0.01f;
        inTab(ItemTabsMS.MOD_TAB);
    }

    @Override
    public void addRecipes(Craftery c) {
        super.addRecipes(c);
        c.addCraftingRecipe(this.stack(), BlocksMS.STORAGE_COMPONENT.stack(), ItemsTC.GEMS_DIAMOND.stack(3), ItemsTC.GEMS_EMERALD.stack(7));
        c.addCraftingRecipe(this.stack(), BlocksMS.STORAGE_COMPONENT.stack(), ItemsMS.SHADOW_DIAMOND.stack(3), ItemsTC.GEMS_EMERALD.stack(7));
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
    public Class<StorageHeartTile> getTileClass() {
        return StorageHeartTile.class;
    }


    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        TileSyncable tile = (TileSyncable) worldIn.getTileEntity(pos);
        if (tile instanceof StorageHeartTile) {
            ((StorageHeartTile) tile).defineSystem();
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        TileSyncable tile = (TileSyncable) worldIn.getTileEntity(pos);
        if (tile instanceof StorageHeartTile) {
            ((StorageHeartTile) tile).defineSystem();
        }
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        GuiManager.openGui(playerIn, Cast.cast(worldIn.getTileEntity(pos), TileSyncable.class));
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        for (EnumFacing facing : EnumFacing.values()) {
            BlockPos adjacent = pos.offset(facing);
            TileEntity tile = worldIn.getTileEntity(adjacent);
            if (tile instanceof IStorageConductor) {
                return ((IStorageConductor) tile).getSystem() == null;
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileSyncable tile = (TileSyncable) worldIn.getTileEntity(pos);
        if (tile instanceof StorageHeartTile) {
            ((StorageHeartTile) tile).onHeartDestroy();
        }
        super.breakBlock(worldIn, pos, state);
    }
}
