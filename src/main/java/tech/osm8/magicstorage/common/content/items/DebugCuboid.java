package tech.osm8.magicstorage.common.content.items;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.zeith.terraria.api.items.ItemRarity;
import org.zeith.terraria.api.npc.Economy;
import org.zeith.terraria.common.content.items.base.ItemBaseTC;
import tech.osm8.magicstorage.common.StorageSystem;
import tech.osm8.magicstorage.common.content.IStorageConductor;
import tech.osm8.magicstorage.init.ItemTabsMS;

public class DebugCuboid extends ItemBaseTC {
    public DebugCuboid() {
        this.withValue(Economy.PLATINUM_VALUE * 1000);
        this.withRarity(ItemRarity.RAINBOW);
        this.hasTooltip = false;
        inTab(ItemTabsMS.MOD_TAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (hand != EnumHand.MAIN_HAND || worldIn.isRemote) {
            return EnumActionResult.SUCCESS;
        }

        TileSyncable tile = (TileSyncable) worldIn.getTileEntity(pos);
        if (tile instanceof IStorageConductor) {
            IStorageConductor storageConductor = (IStorageConductor) tile;
            StorageSystem system = storageConductor.getSystem();
            if (system == null) {
                player.sendMessage(new TextComponentString(storageConductor.getPos() + " has no heart"));
                return EnumActionResult.SUCCESS;
            }
            player.sendMessage(new TextComponentString("Heart of " + storageConductor.getPos() + " at: " + storageConductor.getSystem().getHeartPos()));
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.SUCCESS;
    }
}
