package me.hades.render.utils;

import me.hades.render.render.Window;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    private static void enableStuff() {
        glEnable(GL_BLEND);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
    }

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

    public static void drawSquarePixel(float x, float y, float width, float height, Color color) {
        float xPercent = (float) (x / Window.getWidth());
        float yPercent = (float) (y / Window.getHeight());
        float widthPercent = (float) (width / Window.getWidth());
        float heightPercent = (float) (height / Window.getHeight());
        drawSquare(xPercent, yPercent, widthPercent, heightPercent, color);
    }

    public static void drawCircle(float x, float y, float radius) {
        // Draw a circle with the given x, y, and radius
        int numSegments = 32;
        glBegin(GL_LINE_LOOP);
        for (int i = 0; i < numSegments; i++) {
            float theta = 2.0f * (float) Math.PI * (float) i / (float) numSegments;
            float x_ = (float) (x + radius * Math.cos(theta));
            float y_ = (float) (y + radius * Math.sin(theta));
            glVertex3f(x_, y_, 0);
        }
        glEnd();
    }

    public static void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        // Draw a triangle with the given x and y coordinates
        glBegin(GL_TRIANGLES);
        glVertex3f(x1, y1, 0);
        glVertex3f(x2, y2, 0);
        glVertex3f(x3, y3, 0);
        glEnd();
    }

    public static void drawRoundedSquare(float x, float y, float width, float height, float radius, Color color) {
        float halfSizeX = width / 2.0f;
        float halfSizeY = halfSizeX / 2.0f;
        float x1 = x + radius;
        float x2 = x + halfSizeX;
        float x3 = x + width - radius;
        float y1 = y + radius;
        float y2 = y + halfSizeY;
        float y3 = y + height - radius;

        glBegin(GL_POLYGON); // begin drawing a rounded square
        glColor3f(color.getRed(), color.getGreen(), color.getBlue()); // set the color of the rounded square

        // draw the rounded corners
        for (int i = 0; i < 90; i++) {
            float angle = i * (float)Math.PI / 180.0f;
            float dx = radius * (float)Math.cos(angle);
            float dy = radius * (float)Math.sin(angle);
            glVertex3f(x1 + dx, y1 + dy, 0.0f);
            glVertex3f(x2 + dy, y1 + dx, 0.0f);
            glVertex3f(x3 - dx, y1 + dy, 0.0f);
            glVertex3f(x3 - dy, y2 + dx, 0.0f);
            glVertex3f(x3 - dx, y3 - dy, 0.0f);
            glVertex3f(x2 + dy, y3 - dx, 0.0f);
            glVertex3f(x1 + dx, y3 - dy, 0.0f);
            glVertex3f(x1 + dy, y2 - dx, 0.0f);
        }

        // draw the straight sides
        glVertex3f(x1, y1 - radius, 0.0f);
        glVertex3f(x3, y1 - radius, 0.0f);
        glVertex3f(x3, y3 + radius, 0.0f);
        glVertex3f(x1, y3 + radius, 0.0f);
        glEnd(); // end drawing the rounded square
    }

    public static void drawSquare(float x, float y, float size, Color color) {

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        //GLU.gluOrtho2D(0, Window.getWidth(), Window.getHeight(), 0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f); // Set the color
        glBegin(GL_QUADS);
        glVertex2f(x, y); // Top-left vertex
        glVertex2f(x + size, y); // Top-right vertex
        glVertex2f(x + size, y + size); // Bottom-right vertex
        glVertex2f(x, y + size); // Bottom-left vertex
        glEnd();
    }

    public static void drawTexturedRect(float x, float y, float width, float height, Texture texture) {
        // Bind the texture
        glEnable(GL_TEXTURE_2D); // Enable 2D texturing
        texture.bind();

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
        texture.unbind();
        glDisable(GL_TEXTURE_2D); // Disable 2D texturing
    }

    public static void drawTexturedRectPixel(float x, float y, float width, float height, Texture texture) {
        // Bind the texture
        texture.bind();

        // Draw the textured rectangle
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y);
        glTexCoord2f(1, 0);
        glVertex2f(x + width, y);
        glTexCoord2f(1, 1);
        glVertex2f(x + width, y + height);
        glTexCoord2f(0, 1);
        glVertex2f(x, y + height);
        glEnd();

        // Unbind the texture
        texture.unbind();
    }


}
