package me.hades.render;

import me.hades.render.gui.guis.DefaultScreen;
import me.hades.render.render.Window;
import org.lwjgl.LWJGLException;

public class Main {

    public static Window window;

    public static void main(String[] args) throws LWJGLException {
        // Instantiate the window
        window = new Window();
        // Create the window
        window.createWindow("Test Window", 1000, 500, true);
        // Set the current screen to the default screen
        window.setCurrentScreen(new DefaultScreen());

        // Loop until the window is closed
        while (!Thread.interrupted()) {
            window.loop();
        }

        // Close the window
        window.close();
    }
}
