package me.hades.render.render;

import org.lwjgl.input.Mouse;

public class MouseHelper {

    public int getX(Window window) {
        return Mouse.getEventX() * window.getWidth() / window.getWidth();
    }

    public int getY(Window window) {
        return window.getHeight() - Mouse.getEventY() * window.getHeight() / window.getHeight() - 1;
    }
}
