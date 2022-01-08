package tech.osm8.magicstorage.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.zeith.terraria.api.items.tabs.ItemTab;
import org.zeith.terraria.client.gui.terra.GuiNewInventory;

public class GuiTerminal extends GuiNewInventory<ContainerTerminal> {
    @Override
    public void initGui() {
        super.initGui();
    }

    public GuiTerminal(ContainerTerminal container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        GuiScreen.drawRect(this.width - 25, -24, this.width - 10, -11, -7631989);
        GuiScreen.drawRect(this.width - 25, -15, this.width - 10, -2, -7631989);
        GlStateManager.pushMatrix();
        font.drawString("<-", this.width - 20 + (15 - font.getStringWidth("<-")) / 2, -19, 1 > 0 ? -5570646 : -21846);
        font.drawString("->", this.width - 20 + (15 - font.getStringWidth("->")) / 2, -10, 1 + 1 < ItemTab.getAllTabs().size() - 10 ? -5570646 : -21846);
        GlStateManager.popMatrix();
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    }
}
