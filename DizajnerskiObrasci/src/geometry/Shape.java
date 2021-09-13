package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

    private boolean selected;
    private Color color;

    public abstract void draw(Graphics g);

    public abstract boolean contains(int x, int y);

    public Shape() {

    }

    public Shape(boolean selected) {
	this.selected = selected;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }
}
