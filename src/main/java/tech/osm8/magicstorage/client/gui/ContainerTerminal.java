package tech.osm8.magicstorage.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import org.zeith.terraria.client.gui.api.inventory.BigDummyInventory;
import org.zeith.terraria.client.gui.api.inventory.ItemSlot;
import org.zeith.terraria.common.container.ContainerNewInventory;

public class ContainerTerminal extends ContainerNewInventory {
    public BigDummyInventory item;

    public ContainerTerminal(EntityPlayer crafter) {
        super(crafter);
        item = new BigDummyInventory(100);
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                int id = x + y * 10;
                this.addSlot((new ItemSlot(item, id, 12 + x * 18, 156 + y * 18, 11, 11, 16, 16)).setColor(0x414499));
            }
        }
    }


}
