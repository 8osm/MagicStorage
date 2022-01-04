package tech.osm8.magicstorage.common.content.blocks;

import com.zeitheron.hammercore.api.blocks.IBlockItemRegisterListener;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.zeith.terraria.api.items.IRareItem;
import org.zeith.terraria.api.npc.Economy;
import org.zeith.terraria.common.content.blocks.base.BlockBaseTC;
import org.zeith.terraria.init.items.ItemsTC;
import tech.osm8.magicstorage.init.BlocksMS;
import tech.osm8.magicstorage.init.ItemTabsMS;
import tech.osm8.magicstorage.init.ItemsMS;

public class StorageHeart extends BlockBaseTC implements IBlockItemRegisterListener, IRareItem {
    public StorageHeart(String name) {
        super(Material.ROCK);
        setTranslationKey(name);
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
    public void onItemBlockRegistered(Item item) {
        item.setMaxStackSize(99);
        Economy.bindValue(item, Economy.SILVER_VALUE);
    }

    @Override
    public ItemRarity getRarity() {
        return ItemRarity.BLUE;
    }
}
