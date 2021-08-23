package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {

	private int innerRadius;
	private Color outerCircleBorderColor;
	private Color outerCircleFillColor;
	private Color innerCircleBorderColor;
	private Color innerCircleFillColor;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public Color getOuterCircleBorderColor() {
		return outerCircleBorderColor;
	}

	public void setOuterCircleBorderColor(Color externalCircleBorderColor) {
		this.outerCircleBorderColor = externalCircleBorderColor;
	}

	public Color getOuterCircleFillColor() {
		return outerCircleFillColor;
	}

	public void setOuterCircleFillColor(Color externalCircleInnerColor) {
		this.outerCircleFillColor = externalCircleInnerColor;
	}

	public Color getInnerCircleBorderColor() {
		return innerCircleBorderColor;
	}

	public void setInnerCircleBorderColor(Color internalCircleBorderColor) {
		this.innerCircleBorderColor = internalCircleBorderColor;
	}

	public Color getInnerCircleFillColor() {
		return innerCircleFillColor;
	}

	public void setInnerCircleFillColor(Color internalCircleInnerColor) {
		this.innerCircleFillColor = internalCircleInnerColor;
	}

	public void draw(Graphics g) {
		g.setColor(getOuterCircleBorderColor());
		g.drawOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		
		g.setColor(getOuterCircleFillColor());
		g.fillOval(this.getCenter().getX() + 1 - this.radius, getCenter().getY() + 1 - getRadius(), (this.getRadius() - 1) * 2, (this.getRadius() - 1) * 2);
		//super.draw(g);
		
		g.setColor(getInnerCircleBorderColor());
		g.drawOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius()*2, this.innerRadius*2);
		
		g.setColor(getInnerCircleFillColor());
		g.fillOval(this.getCenter().getX()+1 - this.innerRadius, this.getCenter().getY() + 1 - this.getInnerRadius(), (this.getInnerRadius() - 1) * 2, (this.innerRadius - 1) * 2);
		
		if (isSelected()) {
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return dFromCenter > innerRadius && super.contains(x, y);
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius && super.contains(p.getX(), p.getY());
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) && this.getRadius() == d.getRadius() && this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String toString() {
		return super.toString() + ", inner radius = " + innerRadius ;
	}
	
}
