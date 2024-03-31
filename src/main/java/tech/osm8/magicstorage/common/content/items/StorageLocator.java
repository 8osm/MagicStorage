package tech.osm8.magicstorage.common.content.items;

import com.zeitheron.hammercore.tile.TileSyncable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
import tech.osm8.magicstorage.common.content.blocks.tile.StorageHeartTile;
import tech.osm8.magicstorage.common.content.blocks.tile.StorageRemoteAccessTile;
import tech.osm8.magicstorage.init.ItemTabsMS;

public class StorageLocator extends ItemBaseTC {
    public final boolean isReusable;

    public StorageLocator(boolean isReusable) {

        this.withValue(isReusable ? Economy.GOLD_VALUE * 5 : Economy.GOLD_VALUE)
                .withRarity(isReusable ? ItemRarity.RED : ItemRarity.BLUE);
        this.maxStackSize = 1;
        this.hasTooltip = false;
        this.isReusable = isReusable;
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
            ItemStack locator = player.getHeldItem(EnumHand.MAIN_HAND);
            if (storageConductor instanceof StorageHeartTile) {
                StorageSystem system = storageConductor.getSystem();
                if (system != null && system.getHeartPos() != null) {
                    player.sendMessage(new TextComponentString("Stored heart location!"));
                    locator.getOrCreateSubCompound("MagicStorage").setLong("Pos", system.getHeartPos().toLong());
                }
            } else if (storageConductor instanceof StorageRemoteAccessTile) {
                NBTTagCompound nbt = locator.getSubCompound("MagicStorage");
                if (nbt == null) {
                    player.sendMessage(new TextComponentString("Right click a Storage Heart first!"));
                    return EnumActionResult.FAIL;
                }
                TileSyncable storedTile = (TileSyncable) worldIn.getTileEntity(BlockPos.fromLong(nbt.getLong("Pos")));
                if (storedTile instanceof IStorageConductor) {
                    player.sendMessage(new TextComponentString("Inserted heart location!"));
                    IStorageConductor storedConductor = (IStorageConductor) storedTile;
                    StorageSystem storedSystem = storedConductor.getSystem();
                    storageConductor.setSystem(storedSystem);
                    if (!isReusable) {
                        locator.shrink(1);
                    }
                }
            }
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }
}
