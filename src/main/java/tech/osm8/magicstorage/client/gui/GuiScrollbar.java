package tech.osm8.magicstorage.client.gui;

import com.zeitheron.hammercore.client.utils.RenderUtil;
import com.zeitheron.hammercore.client.utils.UtilsFX;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tech.osm8.magicstorage.MagicStorage;

import java.awt.*;

public class GuiScrollbar {
    private static final ResourceLocation TEX_SHEET = new ResourceLocation("magicstorage", "textures/gui/panel.png");
    private static final int bodyTexX = 200;
    private static final int bodyTexHeight = 256;
    private static final int bodyTexPaddingXY = 2;
    private static final int scrollerTexX = 210;
    private static final int scrollerTexHeight = 254;


    public int height;
    public int width = 10;

    // number of detents on the scrollbar
    public int detentCount = 2;

    @Getter
    private float detentHeight = 0;

    @Getter
    private int value, prevValue = 0;

    // TODO: dynamic scroller height
    public float scrollerHeight;
    public int scrollerWidth = 6;
    private int scrollerX = 0;
    private float scrollerY = 0;
    private int scrollerMinY = 0;
    private int scrollerMaxY = 0;

    private float scrollerUsableHeight = 0;

    @Setter
    @Getter
    private boolean anchoredToDetents = false;
    private int x;
    private int y;
    private final Rectangle rect = new Rectangle();

    public GuiScrollbar(int x, int y, int height, int value, int detentCount) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.value = value;
        this.detentCount = detentCount;
        this.scrollerX = x + bodyTexPaddingXY;
        // scrollerUsableHeight must be divisible by detentCount

        this.scrollerMinY = y + bodyTexPaddingXY;
        this.scrollerMaxY = y + height - bodyTexPaddingXY;
        this.scrollerUsableHeight = this.scrollerMaxY - this.scrollerMinY;
        this.detentHeight = scrollerUsableHeight / (detentCount-1);

        MagicStorage.logger.info("Detent Height: " + detentHeight);
        MagicStorage.logger.info("Usable Height: " + scrollerUsableHeight);

        this.scrollerHeight = Math.max(8, scrollerUsableHeight / (detentCount-1))/2;
        this.scrollerY = scrollerMinY + value * detentHeight;

    }

    public void renderScrollbar() {
//        this.x = x;
//        this.y = y;
//        this.scrollerMinY = y + bodyTexPaddingXY + (scrollerHeight >> 1);
//        this.scrollerMaxY = y + height - bodyTexPaddingXY - (scrollerHeight >> 1);
//        this.scrollerUsableHeight = this.scrollerMaxY - this.scrollerMinY;
//        this.detentCount = detentCount;
//        this.detentHeight = scrollerUsableHeight / detentCount;
        GlStateManager.pushMatrix();
        UtilsFX.bindTexture(TEX_SHEET);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        RenderUtil.drawTexturedModalRect(this.x, this.y, bodyTexX, 0, width, height - 3);
        RenderUtil.drawTexturedModalRect(this.x, this.y + height - 3, bodyTexX, bodyTexHeight - 3, width, 3);

        // make sure scroller is within bounds (scrollerY is the center of the scroller)
        float scrollerRenderY = Math.max(scrollerMinY, Math.min(scrollerMaxY - scrollerHeight, scrollerY));
//        float scrollerRenderY = scrollerY
        RenderUtil.drawTexturedModalRect(scrollerX, scrollerRenderY, scrollerTexX, 0, scrollerWidth, scrollerHeight - 1);

        // draw a dot at each detent
//        for (int i = 0; i < detentCount; i++) {
//            RenderUtil.drawColoredModalRect(scrollerX + 2, scrollerMinY + i * detentHeight, 2, 1, Color.BLACK.getRGB());
//        }
        // draw a rectangle showing scrollerUsableHeight
//        RenderUtil.drawColoredModalRect(scrollerX - bodyTexPaddingXY, scrollerMinY, width, (int) scrollerUsableHeight, Color.GREEN.getRGB());
//         draw a 1 pixel thick line at scrollerY
//        RenderUtil.drawColoredModalRect(scrollerX - bodyTexPaddingXY, scrollerY, width, 1, Color.RED.getRGB());


        RenderUtil.drawTexturedModalRect(scrollerX, scrollerRenderY - 1 + scrollerHeight, scrollerTexX, scrollerTexHeight - 1, scrollerWidth, 1);
        GlStateManager.popMatrix();
    }

    public boolean isMouseOnScroller(int mouseX, int mouseY) {
        rect.setBounds(scrollerX, (int) scrollerY, scrollerWidth, (int) scrollerHeight);
        return rect.contains(mouseX, mouseY);
    }

    public boolean isMouseOnScrollbar(int mouseX, int mouseY) {
        rect.setBounds(x, y, width, height);
        return rect.contains(mouseX, mouseY);
    }

    public int getDetent() {
        return Math.round((scrollerY - scrollerMinY) / detentHeight);
    }

    public void anchorToClosestDetent() {
        scrollerY = getClosestDetentY(scrollerY) + scrollerMinY;
        value = getDetent();
    }

    // Method that returns the closest detent to the provided Y position
    public float getClosestDetentY(float y) {
        int detent = Math.round((y - scrollerMinY) / detentHeight);
        MagicStorage.logger.info("Detent: " + detent);
        // clamp detent
        return Math.max(0, Math.min(detentCount - 1, detent)) * detentHeight;
    }

    public void setScrollerY(float y) {
        if (anchoredToDetents) {
            scrollerY = getClosestDetentY(y) + scrollerMinY;
        } else {
            scrollerY = Math.max(scrollerMinY, Math.min(scrollerMaxY, y));
        }
        prevValue = value;
        value = getDetent();
    }

    public void setValue(int value) {

        int newValue = Math.max(0, Math.min(detentCount - 1, value));

        MagicStorage.logger.info("Value: " + value + " New Value: " + newValue);
        if (newValue != this.value) {
            this.value = newValue;
            setScrollerY(scrollerMinY + this.value * detentHeight);
        }

    }
}
