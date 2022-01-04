package tech.osm8.magicstorage.common.content.blocks;

import com.zeitheron.hammercore.api.ITileBlock;
import net.minecraft.block.material.Material;
import org.zeith.terraria.common.content.blocks.base.BlockBaseTC;
import tech.osm8.magicstorage.common.content.blocks.tile.BaseStorageTile;
import tech.osm8.magicstorage.init.ItemTabsMS;

public class BaseStorageUnit<T extends BaseStorageTile> extends BlockBaseTC implements ITileBlock<T> {
    private final Class<T> tileType;

    public BaseStorageUnit(Class<T> tileType, String name) {
        super(Material.ROCK);
        setTranslationKey(name);
        minToolPower = 1;
        toolMineModifier = 0.01f;
        inTab(ItemTabsMS.UNITS_TAB);
        this.tileType = tileType;
    }

    @Override
    public Class<T> getTileClass() {
        return tileType;
    }
}
