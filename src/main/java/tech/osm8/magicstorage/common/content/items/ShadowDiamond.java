package tech.osm8.magicstorage.common.content.items;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.zeith.terraria.api.entity.IBoss;
import org.zeith.terraria.api.events.CreateAuxiliaryDropsEvent;
import org.zeith.terraria.api.items.ItemRarity;
import org.zeith.terraria.api.npc.Economy;
import org.zeith.terraria.common.content.items.base.ItemBaseTC;
import org.zeith.terraria.common.data.world.world.WorldDataTC;
import tech.osm8.magicstorage.common.world.data.ShadowDiamondDropData;
import tech.osm8.magicstorage.init.ItemTabsMS;
import tech.osm8.magicstorage.init.ItemsMS;

@Mod.EventBusSubscriber
public class ShadowDiamond extends ItemBaseTC {

    public ShadowDiamond() {
        this.withRarity(ItemRarity.BLUE).withValue(Economy.GOLD_VALUE);
        this.hasTooltip = true;
        inTab(ItemTabsMS.MOD_TAB);
    }

    @SubscribeEvent
    public static void bossDrops(CreateAuxiliaryDropsEvent event) {
        if (event.getEntity() instanceof IBoss) {
            IBoss boss = (IBoss) event.getEntity();
            if (WorldDataTC.get(event.getEntity().world).getData(ShadowDiamondDropData.class).doesShadowDiamondDrop(boss)) {
                event.stack.add(ItemsMS.SHADOW_DIAMOND.stack());
            }
        }
    }
}
