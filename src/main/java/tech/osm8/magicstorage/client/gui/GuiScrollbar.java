package tech.osm8.magicstorage.client.gui;

import com.zeitheron.hammercore.client.utils.RenderUtil;
import com.zeitheron.hammercore.client.utils.UtilsFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiScrollbar {
    private static final ResourceLocation TEX_SHEET = new ResourceLocation("magicstorage", "textures/gui/panel.png");
    private static final int bodyTexX = 200;
    private static final int bodyTexHeight = 256;
    private static final int bodyTexPaddingXY = 2;
    private static final int scrollerTexX = 210;
    private static final int scrollerTexHeight = 254;


    public int height;
    private int scrollerPos;
    public float scrollPercent = 0.0F;
    public int scrollerHeight = 2;
    private int scrollerX = 0;
    private int scrollerY = 0;
    private int scrollerMinY = 0;
    private int scrollerMaxY = 0;
    private int x;
    private int y;

    public GuiScrollbar() {
    }

    public void renderScrollbar(int x, int y, int height, float scrollerPercent) {
        this.height = height;
        this.scrollPercent = scrollerPercent;
        this.x = x;
        this.y = y;
        this.scrollerHeight = 2;
        this.scrollerMinY = y + bodyTexPaddingXY + (scrollerHeight >> 1);
        this.scrollerMaxY = y + height - bodyTexPaddingXY - (scrollerHeight >> 1);
        GlStateManager.pushMatrix();
        UtilsFX.bindTexture(TEX_SHEET);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        RenderUtil.drawTexturedModalRect(this.x, this.y, bodyTexX, 0, 10, height - 3);
        RenderUtil.drawTexturedModalRect(this.x, this.y + height - 3, bodyTexX, bodyTexHeight - 3, 10, 3);

//        scrollerY = y + bodyTexPaddingXY + scrollerOffset;
        scrollerY = y + bodyTexPaddingXY;
        scrollerX = x + bodyTexPaddingXY;
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

    public void setScrollbarPercentage(float percent) {
        this.scrollPercent = percent;
        int scrollbarUsableHeight = this.scrollerMaxY - this.scrollerMinY;
        this.scrollerPos = Math.max(this.scrollerMinY, Math.min(this.scrollerMaxY, (int) Math.floor(scrollbarUsableHeight * percent)));
    }

    // Method that returns the percentage of the scrollbar that corresponds to the mouse's Y position on the scrollbar
    private float getPercentageFromMouseY(int mouseY) {
        int scrollbarUsableHeight = this.scrollerMaxY - this.scrollerMinY;
        int relativeMouseY = mouseY - this.scrollerMinY;
        relativeMouseY = Math.max(0, Math.min(relativeMouseY, scrollbarUsableHeight));
        return (float) relativeMouseY / (float) scrollbarUsableHeight;
    }

    public void setScrollerPosFromY(int y) {
        setScrollbarPercentage(getPercentageFromMouseY(y));
    }
}
