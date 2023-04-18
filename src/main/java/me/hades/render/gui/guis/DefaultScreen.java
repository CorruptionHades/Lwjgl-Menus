package me.hades.render.gui.guis;

import me.hades.render.gui.GuiScreen;
import me.hades.render.utils.RenderUtils;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class DefaultScreen extends GuiScreen {

    private boolean dragging;
    private float x, y, dragX, dragY;

    @Override
    public void drawScreen() {
        super.drawScreen();

        if(dragging) {
            x = dragX - Mouse.getX();
            y = dragY - Mouse.getY();
        }

        RenderUtils.drawSquare(x, y, 50, Color.cyan);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {

        if(isInside(mouseX, mouseY, 10, 10, 50, 50) && button == 0) {
            System.out.println("Clicked!");
            dragging = true;
            dragX = (float) (mouseX - x);
            dragY = (float) (mouseY - y);
        }

        super.mouseClicked(mouseX,mouseY, button);
    }

    @Override
    public void mouseReleased(double x, double y, int button) {
        dragging = false;
        super.mouseReleased(x, y, button);
    }

    @Override
    public void keyTyped(char key, int keyCode) {
        System.out.println(key);
        super.keyTyped(key, keyCode);
    }
}
