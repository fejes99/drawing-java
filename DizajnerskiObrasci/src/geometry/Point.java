package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private int x;
	private int y;

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}

	public Point(int x, int y, Color color) {
		this(x, y);
		setColor(color);
	}

	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setColor(color);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 4, this.y, this.x + 4, y);
		g.drawLine(this.x, this.y - 4, x, this.y + 4);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 4, this.y - 4, 8, 8);
		}
	}

	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (this.x == p.getX() && this.y == p.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}

	public String toString() {
		return "Point: (" + x + ", " + y + "), " + "Color: (" + Integer.toString(getColor().getRGB()) + ")";
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y = this.y + byY;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point p = new Point(0, 0);
			return (int) (this.distance(p.getX(), p.getY()) - ((Point) o).distance(p.getX(), p.getY()));
		}
		return 0;
	}

	public Point clone(Point p) {
		p.setX(this.getX());
		p.setY(this.getY());
		p.setColor(this.getColor());

		return p;
	}
}
