package me.hades.render.gui;

import me.hades.render.render.Window;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public abstract class GuiScreen {
    private final List<GuiElement> elements;

    public GuiScreen() {
        elements = new ArrayList<>();
        init();
    }

    public void init() {}

    public void drawScreen() {
        GL11.glClearColor(1, 1, 1, 1);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        for (GuiElement element : elements) {
            element.render();
        }
    }

    public void update() {
        for (GuiElement element : elements) {
            element.update();
        }
    }

    public void mouseClicked(double x, double y, int button) {
        for (GuiElement element : elements) {
            element.mousePressed(x, y, button);
        }
    }

    public void mouseReleased(double x, double y, int button) {
        for (GuiElement element : elements) {
            element.mouseReleased(x, y, button);
        }
    }

    public void keyTyped(char key, int keyCode) {
        if (key == Keyboard.KEY_ESCAPE) {
            close();
        }
        for (GuiElement element : elements) {
            element.keyPressed(key, keyCode);
        }
    }

    public void addElement(GuiElement element) {
        elements.add(element);
    }

    public void handleMouseInput() {
        int width = Window.getWidth();
        int height = Window.getHeight();
        int i = Mouse.getEventX() *  width / width;
        int j = height - Mouse.getEventY() * height / height - 1;
        int button = Mouse.getEventButton();

        if(Mouse.getEventButtonState()) {
            mouseClicked(i, j, button);
        }
        else if(button != -1) {
            mouseReleased(i, j, button);
        }
    }

    public void handleKeyboardInput() {
        if (Keyboard.getEventKeyState()) {
            keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());
        }
    }

    public void close() {}

    public boolean isInside(double mouseX, double mouseY, float x, float y, float width, float height) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
    }
}
