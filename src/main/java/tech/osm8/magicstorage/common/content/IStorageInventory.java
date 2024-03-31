package tech.osm8.magicstorage.common.content;

import net.minecraft.item.ItemStack;
import org.zeith.terraria.client.gui.api.inventory.BigDummyInventory;

public interface IStorageInventory extends IStorageConductor {

    BigDummyInventory getInventory();

    ItemStack placeItemStack(ItemStack stack);

    int getCapacity();
}
