package me.hades.render.gui;

public class GuiElement {

    protected final GuiScreen parent;
    protected double x, y;
    protected int width, height;

    public GuiElement(GuiScreen parent, double x, double y, int width, int height) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(int mouseX, int mouseY) {}
    public void mousePressed(double x, double y, int button) {}
    public void mouseReleased(double x, double y, int button) {}
    public void keyPressed(char key, int keyCode) {}

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public boolean isInside(double mouseX, double mouseY, float x, float y, float width, float height) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
    }

    public GuiScreen getParent() {
        return parent;
    }
}
