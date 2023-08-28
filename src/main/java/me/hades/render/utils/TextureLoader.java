package me.hades.render.utils;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader {

    private static final int BYTES_PER_PIXEL = 4;//3 for RGB, 4 for RGBA
    private static TextureLoader instance;

    private TextureLoader() {
    }

    public static TextureLoader getInstance() {
        if (instance == null) {
            instance = new TextureLoader();
        }
        return instance;
    }

    /**
     * Load a texture from file.
     *
     * @param loc the location of the file
     * @return the id of the texture
     */
    public int loadTexture(String loc) {
        BufferedImage image = loadImage(loc);
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
        Color c;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                c = new Color(image.getRGB(x, y));
                buffer.put((byte) c.getRed());     // Red component
                buffer.put((byte) c.getGreen());      // Green component
                buffer.put((byte) c.getBlue());               // Blue component
                buffer.put((byte) c.getAlpha());    // Alpha component. Only for RGBA
            }
        }

        buffer.flip();

        int textureID = glGenTextures(); //Generate texture ID
        glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID

        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        //Return the texture ID so we can bind it later again
        return textureID;
    }

    /**
     * Load an image from disc.
     *
     * @param loc the location of the image
     * @return the image
     */
    private BufferedImage loadImage(String loc) {
        try {
            return ImageIO.read(new File(loc));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
