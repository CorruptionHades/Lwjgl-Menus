package me.hades.render;

import me.hades.render.gui.guis.DefaultScreen;
import me.hades.render.render.Window;
import org.lwjgl.LWJGLException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();
        try {
            window.setCurrentScreen(new DefaultScreen());
            window.createWindow("Test Window", 1000, 500, true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            window.loop();
        }

       // window.close();
    }
}
