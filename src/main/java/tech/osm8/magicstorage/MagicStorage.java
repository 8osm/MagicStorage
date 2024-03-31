package tech.osm8.magicstorage;

import com.zeitheron.hammercore.HammerCore;
import com.zeitheron.hammercore.internal.SimpleRegistration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zeith.terraria.api.mod.ITerrariaMod;
import org.zeith.terraria.common.data.world.world.BaseWorldDataTC;
import org.zeith.terraria.utils.forge.DeferredRegistries;
import tech.osm8.magicstorage.common.world.data.ShadowDiamondDropData;
import tech.osm8.magicstorage.init.BlocksMS;
import tech.osm8.magicstorage.init.ItemsMS;

@Mod(
        modid = MagicStorage.MODID,
        name = MagicStorage.NAME,
        version = MagicStorage.VERSION,
        dependencies = "required-after:terraria"
)
public class MagicStorage
        implements ITerrariaMod {
    public static final String MODID = "magicstorage";
    public static final String NAME = "MagicStorage";
    public static final String VERSION = "@VERSION@";
    private static Logger logger = LogManager.getLogger(MODID);

    public MagicStorage() {
        // Used to add custom recipes
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void construction(FMLConstructionEvent event) {
        // This is very recommended for common addon setup.
        ITerrariaMod.super.constructionEvent(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent initEvent) {
        BaseWorldDataTC.register("ms_slainbosses", ShadowDiamondDropData.class, ShadowDiamondDropData::new);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        // Registers all items
//        SimpleRegistration.registerFieldItemsFrom(ItemsMS.class, MODID, null);

        HammerCore.registerKernelsForMod(MODID);

        // Registers all blocks
//        SimpleRegistration.registerFieldBlocksFrom(BlocksMS.class, MODID, null);
    }
}
