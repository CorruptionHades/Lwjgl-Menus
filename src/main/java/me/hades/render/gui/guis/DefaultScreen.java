package me.hades.render.gui.guis;

import me.hades.render.gui.GuiScreen;
import me.hades.render.utils.RenderUtils;
import me.hades.render.utils.TextureLoader;

import java.awt.*;

/**
 * @author CorruptionHades
 * @implNote A default screen with some example rendering
 */
public class DefaultScreen extends GuiScreen {

    private int stoneTexture;

    @Override
    public void init() {
        stoneTexture = TextureLoader.getInstance().loadTexture("src/main/resources/textures/stone.png");
    }

    @Override
    public void drawScreen() {
        super.drawScreen();

        RenderUtils.drawSquare(20, 20, 50, Color.cyan);
        RenderUtils.drawSquare(80, 20, 50, 30, Color.cyan);
        RenderUtils.drawString("This is a String", 140, 20, Color.cyan);

        RenderUtils.drawCircleOutline(330, 50, 30);

        RenderUtils.drawTriangle(380, 50, 440, 50, 410, 20);

        RenderUtils.drawFilledRoundedRect(500, 20, 50, 50, Color.CYAN, 5);

        RenderUtils.drawTexturedRect(600, 20, 50, 50, stoneTexture);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {

        if(isInside(mouseX, mouseY, 10, 10, 50, 50) && button == 0) {
            System.out.println("Clicked!");
        }

        super.mouseClicked(mouseX,mouseY, button);
    }

    @Override
    public void mouseReleased(double x, double y, int button) {
        super.mouseReleased(x, y, button);
        System.out.println("Released Mouse!");
    }

    @Override
    public void keyTyped(char key, int keyCode) {
        super.keyTyped(key, keyCode);
        System.out.println("Pressed key: " + key + "(" + keyCode + ")");
    }
}
