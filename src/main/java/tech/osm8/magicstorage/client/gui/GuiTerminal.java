package tech.osm8.magicstorage.client.gui;

import com.zeitheron.hammercore.client.utils.RenderUtil;
import com.zeitheron.hammercore.client.utils.UtilsFX;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.zeith.terraria.client.gui.api.inventory.ItemSlot;
import org.zeith.terraria.client.gui.terra.GuiNewInventory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;

public class GuiTerminal extends GuiNewInventory<ContainerTerminal> {

    private static final ResourceLocation PANEL_TEX = new ResourceLocation("magicstorage", "textures/gui/panel.png");


    private final int height = 240;
    private final int width = 200;
    private int startX = 8;
    private int startY = 0;
    private int[] sortButtons = new int[4];
    private int[] filterButtons = new int[7];
    private GuiScrollbar scrollbar;
    private int initialScrollPos = 0;
    private int rowsToRender = 10;
    public static Float forceTabScrollAmount;

    @Override
    public void initGui() {
        this.xSize = this.width;
        this.ySize = this.height;
        super.initGui();
        ContainerTerminal c = this.getContainer();
        startY = Integer.MAX_VALUE;
        startX = Integer.MAX_VALUE;
        c.slots.forEach((s) -> {
            if (s.getInventory() == c.item) {
                startX = Math.min(startX, s.x);
                startY = Math.min(startY, s.y);
            }
        });
        int buttonID = 0;
        for (int x = 0; x < 4; x++) {
            sortButtons[x] = buttonID;
            this.buttonList.add(new GuiButton(buttonID++, startX + x * 14, startY - 32, 12, 12, "" + (buttonID - 1)));
        }
        for (int x = 0; x < 7; x++) {
            filterButtons[x] = buttonID;
            this.buttonList.add(new GuiButton(buttonID++, startX + x * 14, startY - 16, 12, 12, "" + (buttonID - 1)));
        }
        startX -= 4;
        startY -= 36;
        scrollbar = new GuiScrollbar(startX + 186, startY + 25, 200, initialScrollPos, 160);
        this.initWidgets();
    }

    public GuiTerminal(ContainerTerminal container) {
        super(container);
    }

    @Override
    protected void renderSlots(int mouseX, int mouseY) {
        ContainerTerminal c = this.getContainer();
        GlStateManager.enableBlend();
        c.slots.forEach(ItemSlot::drawSlotBackground);
        RenderHelper.enableGUIStandardItemLighting();
        c.slots.forEach((s) -> {
//            this.rect.setBounds(this.guiLeft + s.getX(), this.guiTop + s.getY(), s.getSlotWidth(), s.getSlotHeight());
//            if (this.rect.contains(mouseX, mouseY)) {
//                this.cslot = s;
//            }

            GlStateManager.pushMatrix();
            GlStateManager.translate((float) (this.guiLeft + s.x) + (float) (s.swidth - s.iwidth) / 2.0F, (float) (this.guiTop + s.y) + (float) (s.sheight - s.iheight) / 2.0F, 0.0F);
            GlStateManager.scale((double) s.getItemWidth() / 16.0D, (double) s.getItemHeight() / 16.0D, (double) s.getItemWidth() / 16.0D);
            this.drawStack(s.getStack(), 0, 0, (String) null);
            GlStateManager.popMatrix();
        });
    }

    protected void drawPanel() {
        int x = startX;
        int y = startY;
        GlStateManager.pushMatrix();
        UtilsFX.bindTexture(PANEL_TEX);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        RenderUtil.drawTexturedModalRect(x, y, 0, 0, width - 4, height - 4);
        RenderUtil.drawTexturedModalRect(x + width - 4, y, 200 - 4, 0, 4, height - 4);
        RenderUtil.drawTexturedModalRect(x, y + height - 4, 0, 256 - 4, width - 4, 4);
        RenderUtil.drawTexturedModalRect(x + width - 4, y + height - 4, 200 - 4, 256 - 4, 4, 4);
        GlStateManager.popMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawPanel();
        scrollbar.renderScrollbar();
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (scrollbar.isMouseOnScrollbar(mouseX, mouseY)) {
            scrollbar.setAnchoredToDetents(false);
            scrollbar.setScrollerY(mouseY - scrollbar.scrollerHeight / 2);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }


    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (!scrollbar.isAnchoredToDetents()) {
            scrollbar.setAnchoredToDetents(false);
            scrollbar.anchorToClosestDetent();
        }
        super.mouseReleased(mouseX, mouseY, state);
    }


    @Override
    @ParametersAreNonnullByDefault
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

        if (scrollbar.isAnchoredToDetents()) {
            scrollbar.setScrollerY(mouseY);
        }
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void updateScreen() {
        int wheel = Mouse.getDWheel();
        if (wheel != 0) {
            scrollbar.setValue(scrollbar.getValue() - wheel / 120);
        }
        super.updateScreen();
    }
}
