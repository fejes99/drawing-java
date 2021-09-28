package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {

	private Color innerColor;

	public Color getInnerColor() {
		return this.innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public abstract void fill(Graphics g);

	public abstract double area();
}
