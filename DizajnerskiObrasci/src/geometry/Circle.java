package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {

	private Point center;
	protected int radius;
	private Color borderColor;
	private Color innerColor;
	
	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be greater then 0!");
		}
	}

	public Color getLineColor() {
		return borderColor;
	}

	public void setLineColor(Color lineColor) {
		this.borderColor = lineColor;
	}

	public Color getInternalColor() {
		return innerColor;
	}

	public void setInternalColor(Color internalColor) {
		this.innerColor = internalColor;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		
		g.setColor(getInternalColor());
		g.fillOval(this.getCenter().getX() + 1 - this.radius, getCenter().getY() + 1 - getRadius(), (this.getRadius()-1) * 2, (this.getRadius() - 1) * 2);
		
		if (isSelected()) {
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
		}
	}

	
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	
	public boolean contains(Point p) {
		return p.distance(p.getX(), p.getY()) <= radius;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public String toString() {
		return "Center = " + center + ", radius = " + radius; 
	}

	
}
