package me.hades.render.render;

import me.hades.render.gui.GuiScreen;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Window {

    private int width, height;

    private GuiScreen currentScreen;

    public void createWindow(String title, int width, int height, boolean resizable) throws LWJGLException {
        this.width = width;
        this.height = height;

        Display.setResizable(resizable);
        Display.setTitle(title);
        Display.setDisplayMode(new DisplayMode(width, height));

        try {
            Display.create(new PixelFormat().withDepthBits(24));
        }
        catch (LWJGLException lwjglexception) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ignored) {}
            Display.create();
        }

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void loop() {

        if (Display.isCreated() && Display.isCloseRequested()) {
            close();
        }

        Display.update();

        currentScreen.drawScreen(MouseHelper.getX(this), MouseHelper.getY(this));

        while (Mouse.next()) {
            currentScreen.handleMouseInput(this);
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                currentScreen.handleKeyboardInput();
            }
        }
    }

    public void close() {
        Display.destroy();
        System.exit(0);
    }

    public void setCurrentScreen(GuiScreen screen) {
        this.currentScreen = screen;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
