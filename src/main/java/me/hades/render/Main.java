package me.hades.render;

import me.hades.render.gui.guis.DefaultScreen;
import me.hades.render.render.Window;
import org.lwjgl.LWJGLException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();
        try {
            window.createWindow("Test Window", 1000, 500, true);
            window.setCurrentScreen(new DefaultScreen());
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        while (!Thread.interrupted()) {
            window.loop();
        }

        window.close();
    }
}
