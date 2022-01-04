package tech.osm8.magicstorage.init;

import net.minecraft.util.ResourceLocation;
import org.zeith.terraria.api.items.stack.IStackProvider;
import org.zeith.terraria.api.items.tabs.ItemTab;
import org.zeith.terraria.api.items.tabs.TabInfo;
import tech.osm8.magicstorage.MagicStorage;

public class ItemTabsMS {
    public static final ItemTab MOD_ROOT = new ItemTab(new ResourceLocation(MagicStorage.MODID, "items")).setIcon(IStackProvider.accessor(() -> BlocksMS.STORAGE_HEART));
    public static final TabInfo MOD_TAB = new TabInfo(MOD_ROOT);
    public static final TabInfo UNITS_TAB = MOD_TAB.sub("units");

}