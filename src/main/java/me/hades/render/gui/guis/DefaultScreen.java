package me.hades.render.gui.guis;

import me.hades.render.gui.GuiScreen;
import me.hades.render.render.Window;
import me.hades.render.utils.RenderUtils;
import me.hades.render.utils.TextureLoader;

import java.awt.*;

/**
 * @author CorruptionHades
 * @implNote A default screen with some example rendering
 */
public class DefaultScreen extends GuiScreen {

    private int stoneTexture;

    // The color of the square
    private Color squareColor = Color.cyan;

    // Variables for the square you can move around
    boolean dragging = false;
    int squareX = 0, squareY = 50;
    int dragX = 0, dragY = 0;

    public DefaultScreen(Window window) {
        super(window);
    }

    @Override
    public void init() {
        stoneTexture = TextureLoader.getInstance().loadTexture("src/main/resources/textures/stone.png");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);

        RenderUtils.drawSquare(20, 20, 50, squareColor);
        RenderUtils.drawSquare(80, 20, 50, 30, Color.cyan);
        RenderUtils.drawString("This is a String", 140, 20, Color.cyan);

        RenderUtils.drawCircleOutline(330, 50, 30);

        RenderUtils.drawTriangle(380, 50, 440, 50, 410, 20);

        RenderUtils.drawFilledRoundedRect(500, 20, 50, 50, Color.CYAN, 5);

        RenderUtils.drawTexturedRect(600, 20, 50, 50, stoneTexture);

        // for dragging
        if(dragging) {

            int newX = mouseX - dragX;
            int newY = mouseY - dragY;

            if(newX < 0) {
                newX = 0;
            }
            if(newX > window.getWidth() - 50) {
                newX = window.getWidth() - 50;
            }

            if(newY < 0) {
                newY = 0;
            }
            if(newY > window.getHeight() - 50) {
                newY = window.getHeight() - 50;
            }

            squareX = newX;
            squareY = newY;
        }

        RenderUtils.drawSquare(squareX, squareY, 50, 50, squareColor);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {

        if(isInside(mouseX, mouseY, 10, 10, 50, 50) && button == 0) {
            if(squareColor == Color.cyan) {
                squareColor = Color.red;
            }
            else {
                squareColor = Color.cyan;
            }
        }

        if(isInside(mouseX, mouseY, squareX, squareY, squareX + 50, squareY + 50)) {
            dragging = true;
            dragX = (int) mouseX - squareX;
            dragY = (int) mouseY - squareY;
        }

        super.mouseClicked(mouseX,mouseY, button);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        super.mouseReleased(mouseX, mouseY, button);
        dragging = false;
    }

    @Override
    public void keyTyped(char key, int keyCode) {
        super.keyTyped(key, keyCode);
        System.out.println("Pressed key: " + key + "(" + keyCode + ")");
    }
}
