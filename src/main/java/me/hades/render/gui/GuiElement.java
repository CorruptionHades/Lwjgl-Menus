package me.hades.render.gui;

public abstract class GuiElement {
    protected double x, y;
    protected int width, height;

    public GuiElement(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render();

    public abstract void update();

    public abstract void mousePressed(double x, double y, int button);

    public abstract void mouseReleased(double x, double y, int button);

    public abstract void keyPressed(char key, int keyCode);

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isInside(double mouseX, double mouseY, float x, float y, float width, float height) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
    }
}
