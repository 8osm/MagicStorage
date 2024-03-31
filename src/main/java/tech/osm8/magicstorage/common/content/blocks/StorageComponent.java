package tech.osm8.magicstorage.common.content.blocks;

import com.zeitheron.hammercore.api.blocks.IBlockItemRegisterListener;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import org.zeith.terraria.api.blocks.SoundTypesTC;
import org.zeith.terraria.api.items.ItemRarity;
import org.zeith.terraria.api.npc.Economy;
import org.zeith.terraria.common.content.blocks.base.BlockBaseTC;
import org.zeith.terraria.init.TagsTC;
import tech.osm8.magicstorage.init.ItemTabsMS;

public class StorageComponent extends BlockBaseTC {

    public StorageComponent() {
        super(Material.ROCK);
        setSoundType(SoundTypesTC.COMMON);
        this.withValue(Economy.SILVER_VALUE);
        this.withRarity(ItemRarity.WHITE);
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
    public void addRecipes(Craftery c) {
        super.addRecipes(c);
        c.addCraftingRecipe(this.stack(),
                TagsTC.Items.WOOD.ingredient(10),
                TagsTC.Items.BARS_IRON_OR_LEAD.ingredient(2)
        );
    }
}
