package me.hades.render.utils;

import me.hades.render.render.Window;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.FontUtils;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    private static void enableStuff() {
        glEnable(GL_BLEND);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
    }

    /**
     * Draw a square on the screen.
     * @param x X position of the square
     * @param y Y position of the square
     * @param width Width of the square
     * @param height Height of the square
     * @param color Color of the square
     */
    public static void drawSquare(float x, float y, float width, float height, Color color) {
        enableStuff();

        glBegin(GL_QUADS); // begin drawing a square
        glColor3f(color.getRed(), color.getGreen(), color.getBlue()); // set the color of the square
        glVertex3f(x, y, 0.0f); // set the position of the first vertex
        glVertex3f(x + width, y, 0.0f); // set the position of the second vertex
        glVertex3f(x + width, y + height, 0.0f); // set the position of the third vertex
        glVertex3f(x, y + height, 0.0f); // set the position of the fourth vertex
        glEnd(); // end drawing the square
    }

    /**
     * Draw a string on the screen.
     * @param text The text to draw
     * @param x X position of the text
     * @param y Y position of the text
     * @param color Color of the text
     */
     public static void drawString(String text, int x, int y, Color color) {
        glEnable(3042);
        glBlendFunc(770, 771);
        glPushMatrix();
        Font awtFont = new Font("default", Font.PLAIN, 20);
        TrueTypeFont font = new TrueTypeFont(awtFont, true);
        FontUtils.drawString(font, text, 1, x, y, 10, new org.newdawn.slick.Color(color.getRed(), color.getGreen(), color.getBlue()));
        glDisable(3553);
        glPopMatrix();
    }

    /**
     * Draws a circle outline on the screen.
     * @param x X position of the circle
     * @param y Y position of the circle
     * @param radius Radius of the circle
     */
    public static void drawCircleOutline(float x, float y, float radius) {
        // Draw a circle with the given x, y, and radius
        int numSegments = 32;
        glBegin(GL_LINE_LOOP);
        for (int i = 0; i < numSegments; i++) {
            float theta1 = 2.0f * (float) Math.PI * (float) i / (float) numSegments;
            float theta2 = 2.0f * (float) Math.PI * (float) (i + 1) / (float) numSegments;

            float x1 = (float) (x + radius * Math.cos(theta1));
            float y1 = (float) (y + radius * Math.sin(theta1));

            float x2 = (float) (x + radius * Math.cos(theta2));
            float y2 = (float) (y + radius * Math.sin(theta2));

            glVertex3f(x1, y1, 0);
            glVertex3f(x2, y2, 0);
        }
        glEnd();
    }

    /**
     * Draw a triangle on the screen.
     * @param x1 X position of the first point
     * @param y1 Y position of the first point
     * @param x2 X position of the second point
     * @param y2 Y position of the second point
     * @param x3 X position of the third point
     * @param y3 Y position of the third point
     */
    public static void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        // Draw a triangle with the given x and y coordinates
        glBegin(GL_TRIANGLES);
        glVertex3f(x1, y1, 0);
        glVertex3f(x2, y2, 0);
        glVertex3f(x3, y3, 0);
        glEnd();
    }

    /**
     * Draw a square on the screen.
     * @param x X position of the square
     * @param y Y position of the square
     * @param size Size of the square
     * @param color Color of the square
     */
    public static void drawSquare(float x, float y, float size, Color color) {
        glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f); // Set the color
        glBegin(GL_QUADS);
        glVertex2f(x, y); // Top-left vertex
        glVertex2f(x + size, y); // Top-right vertex
        glVertex2f(x + size, y + size); // Bottom-right vertex
        glVertex2f(x, y + size); // Bottom-left vertex
        glEnd();
    }

    /**
     * Draw a textured rectangle on the screen.
     * @param x X position of the rectangle
     * @param y Y position of the rectangle
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     * @param texture Texture of the rectangle (see {@link me.hades.render.utils.TextureLoader})
     */
    public static void drawTexturedRect(float x, float y, float width, float height, int texture) {
        // Bind the texture
        glEnable(GL_TEXTURE_2D); // Enable 2D texturing
        glBindTexture(GL_TEXTURE_2D, texture);

        // Set the color to white
        glColor3f(1, 1, 1);

        // Draw the rectangle with texture coordinates
        glBegin(GL_QUADS);
        glTexCoord3f(0, 1, 0);  // Flip the y-coordinate here
        glVertex3f(x, y, 0);
        glTexCoord3f(1, 1, 0);  // Flip the y-coordinate here
        glVertex3f(x + width, y, 0);
        glTexCoord3f(1, 0, 0);  // Flip the y-coordinate here
        glVertex3f(x + width, y + height, 0);
        glTexCoord3f(0, 0, 0);  // Flip the y-coordinate here
        glVertex3f(x, y + height, 0);
        glEnd();

        // Unbind the texture
        glBindTexture(GL_TEXTURE_2D, 0);
        glDisable(GL_TEXTURE_2D); // Disable 2D texturing
    }

    /**
     * Draw a rectangle with rounded corners on the screen.
     * @param x X position of the rectangle
     * @param y Y position of the rectangle
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     * @param color Color of the rectangle
     * @param cornerRadius Radius of the corners
     */
    public static void drawFilledRoundedRect(float x, float y, float width, float height, Color color, float cornerRadius) {
        // Set the color
        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);

        // Draw the central rectangle
        glBegin(GL_QUADS);
        glVertex2f(x + cornerRadius, y);
        glVertex2f(x + width - cornerRadius, y);
        glVertex2f(x + width - cornerRadius, y + height);
        glVertex2f(x + cornerRadius, y + height);
        glEnd();

        // Draw the horizontal and vertical lines
        glBegin(GL_QUADS);
        glVertex2f(x, y + cornerRadius);
        glVertex2f(x + cornerRadius, y + cornerRadius);
        glVertex2f(x + cornerRadius, y + height - cornerRadius);
        glVertex2f(x, y + height - cornerRadius);

        glVertex2f(x + width - cornerRadius, y + cornerRadius);
        glVertex2f(x + width, y + cornerRadius);
        glVertex2f(x + width, y + height - cornerRadius);
        glVertex2f(x + width - cornerRadius, y + height - cornerRadius);
        glEnd();

        // Draw the rounded corners using circles
        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        glBegin(GL_TRIANGLE_FAN);
        for (int i = 0; i <= 360; i++) {
            float angle = (float) Math.toRadians(i);
            float xVertex = x + cornerRadius + (float) Math.cos(angle) * cornerRadius;
            float yVertex = y + cornerRadius + (float) Math.sin(angle) * cornerRadius;
            glVertex2f(xVertex, yVertex);
        }
        glEnd();

        glBegin(GL_TRIANGLE_FAN);
        for (int i = 0; i <= 360; i++) {
            float angle = (float) Math.toRadians(i);
            float xVertex = x + width - cornerRadius + (float) Math.cos(angle) * cornerRadius;
            float yVertex = y + cornerRadius + (float) Math.sin(angle) * cornerRadius;
            glVertex2f(xVertex, yVertex);
        }
        glEnd();

        glBegin(GL_TRIANGLE_FAN);
        for (int i = 0; i <= 360; i++) {
            float angle = (float) Math.toRadians(i);
            float xVertex = x + width - cornerRadius + (float) Math.cos(angle) * cornerRadius;
            float yVertex = y + height - cornerRadius + (float) Math.sin(angle) * cornerRadius;
            glVertex2f(xVertex, yVertex);
        }
        glEnd();

        glBegin(GL_TRIANGLE_FAN);
        for (int i = 0; i <= 360; i++) {
            float angle = (float) Math.toRadians(i);
            float xVertex = x + cornerRadius + (float) Math.cos(angle) * cornerRadius;
            float yVertex = y + height - cornerRadius + (float) Math.sin(angle) * cornerRadius;
            glVertex2f(xVertex, yVertex);
        }
        glEnd();
    }



}
