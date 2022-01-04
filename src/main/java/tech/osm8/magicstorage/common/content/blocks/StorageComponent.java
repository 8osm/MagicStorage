package tech.osm8.magicstorage.common.content.blocks;

import com.zeitheron.hammercore.api.blocks.IBlockItemRegisterListener;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.zeith.terraria.api.blocks.BlockMaterial;
import org.zeith.terraria.api.blocks.SoundTypesTC;
import org.zeith.terraria.api.items.IRareItem;
import org.zeith.terraria.api.items.ItemMaterial;
import org.zeith.terraria.api.npc.Economy;
import org.zeith.terraria.common.content.blocks.base.BlockBaseTC;
import tech.osm8.magicstorage.init.ItemTabsMS;

public class StorageComponent extends BlockBaseTC implements IBlockItemRegisterListener, IRareItem {

    public StorageComponent(String name) {
        super(Material.ROCK);
        setTranslationKey(name);
        setSoundType(SoundTypesTC.COMMON);
        minToolPower = 1;
        toolMineModifier = 0.01f;
        inTab(ItemTabsMS.MOD_TAB);
    }

    @Override
    public void addRecipes(Craftery c) {
        super.addRecipes(c);
        c.addCraftingRecipe(this.stack(), BlockMaterial.WOOD.toOredictString(10), ItemMaterial.ANY_IRON.toOredictString(2));
    }


    @Override
    public void onItemBlockRegistered(Item item) {
        item.setMaxStackSize(99);
        Economy.bindValue(item, Economy.SILVER_VALUE);
    }

    @Override
    public ItemRarity getRarity() {
        return ItemRarity.WHITE;
    }
}
