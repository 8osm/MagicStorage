package tech.osm8.magicstorage.client.gui;

import com.zeitheron.hammercore.client.utils.RenderUtil;
import com.zeitheron.hammercore.client.utils.UtilsFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiScrollbar {
    private static final ResourceLocation TEX_SHEET = new ResourceLocation("magicstorage", "textures/gui/panel.png");
    private static final int bodyTexX = 200;
    private static final int bodyTexHeight = 256;
    private static final int scrollerTexX = 210;
    private static final int scrollerTexHeight = 254;

    public int height;
    public int scrollerPos;

    private int scrollerHeight = 16;
    private int scrollerX = 0;
    private int scrollerY = 0;
    private int x;
    private int y;

    public GuiScrollbar() {
    }

    public void renderScrollbar(int x, int y, int height, int scrollerPos) {
        this.height = height;
        this.scrollerPos = scrollerPos;
        this.x = x;
        this.y = y;
        scrollerHeight = 16;
        GlStateManager.pushMatrix();
        UtilsFX.bindTexture(TEX_SHEET);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        RenderUtil.drawTexturedModalRect(x, y, bodyTexX, 0, 10, height - 3);
        RenderUtil.drawTexturedModalRect(x, y + height - 3, bodyTexX, bodyTexHeight - 3, 10, 3);
        int scrollerOffset = this.scrollerPos >= 256 ? height - scrollerHeight - 4 : (int) Math.floor(height * (this.scrollerPos / 256f));
        scrollerY = y + 2 + scrollerOffset;
        scrollerX = x + 2;
        RenderUtil.drawTexturedModalRect(scrollerX, scrollerY, scrollerTexX, 0, 6, scrollerHeight - 1);
        RenderUtil.drawTexturedModalRect(scrollerX, scrollerY - 1 + scrollerHeight, scrollerTexX, scrollerTexHeight - 1, 6, 1);
        GlStateManager.popMatrix();
    }

    public boolean isMouseOnScroller(int mouseX, int mouseY) {
        return mouseX >= this.scrollerX && mouseY >= this.scrollerY && mouseX < this.scrollerX + 4 && mouseY < this.scrollerY + this.scrollerHeight;
    }

    public boolean isMouseOnScrollbar(int mouseX, int mouseY) {
        return mouseX >= (this.x + 1) && mouseY >= (this.y + 2) && mouseX < this.x + 8 && mouseY < this.y + (this.height - 2);
    }

    public void setScrollerPosFromY(int y) {
        if (y - this.y > height - 12) scrollerPos = 256;
        else if (y - this.y < 12) scrollerPos = 0;
        else
            scrollerPos = (int) Math.max(0, Math.floor((((y - this.y) / ((float) height)) * 256)));
    }

}
