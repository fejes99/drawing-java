package mvc;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdRemoveShape;
import command.CmdSelectShape;
import command.Command;
import dialogs.CircleDialog;
import dialogs.DonutDialog;
import dialogs.HexagonDialog;
import dialogs.PointLineDialog;
import dialogs.RectangleDialog;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;

	Command command;

	private int counter = 1;

	private int height;
	private int width;
	private int radius;
	private int innerRadius;

	private Point startPoint;
	private Point endPoint;

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void selectShapes(MouseEvent e) {
		Shape selectedShape = null;
		Shape shape = null;

		Iterator<Shape> it = this.model.getShapes().iterator();

		Command command = null;

		while (it.hasNext()) {
			shape = it.next();

			if (shape.contains(e.getX(), e.getY())) {
				selectedShape = shape;
			}

		}

		if (selectedShape != null) {
			if (selectedShape.isSelected()) {
				command = new CmdSelectShape(this, selectedShape);
				command.execute();

			} else {
				command = new CmdSelectShape(this, selectedShape);
				command.execute();
			}
		}
		frame.repaint();
	}

	public void drawPoint(MouseEvent e) {
		PointLineDialog dlgPoint = new PointLineDialog("point");
		dlgPoint.pack();
		dlgPoint.setVisible(true);
		if (dlgPoint.isConfirmed()) {
			Point p = new Point(e.getX(), e.getY(), false);
			p.setColor(dlgPoint.getColor());
			command = new CmdAddShape(model, p);
			command.execute();
			frame.repaint();
		} // if canceled null pointer exception occur
	}

	public void drawLine(MouseEvent e) {
		PointLineDialog dlgLine = new PointLineDialog("line");
		if (startPoint == null) {
			startPoint = new Point(e.getX(), e.getY());
			return;
		}
		endPoint = new Point(e.getX(), e.getY());
		dlgLine.setVisible(true);
		if (dlgLine.isConfirmed()) {
			Line l = new Line(startPoint, endPoint, false);
			l.setColor(dlgLine.getColor());
			command = new CmdAddShape(model, l);
			command.execute();
			frame.repaint();
		}
		startPoint = null;
	}

	public void drawRectangle(MouseEvent e) {
		RectangleDialog dlgRectangle = new RectangleDialog();
		startPoint = new Point(e.getX(), e.getY());
		dlgRectangle.pack();
		dlgRectangle.setVisible(true);
		if (dlgRectangle.isConfirmed()) {
			height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
			width = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
			Rectangle r = new Rectangle(startPoint, height, width);

			// not showing default color
			r.setColor(dlgRectangle.getColor());
			r.setInnerColor(dlgRectangle.getInnerColor());
			command = new CmdAddShape(model, r);
			command.execute();
		}

		frame.repaint();
	}

	public void drawCircle(MouseEvent e) {
		CircleDialog dlgCircle = new CircleDialog();
		startPoint = new Point(e.getX(), e.getY());
		dlgCircle.pack();
		dlgCircle.setVisible(true);
		if (dlgCircle.isConfirmed()) {
			radius = Integer.parseInt(dlgCircle.getTxtRadius().getText());
		}

		Circle c = new Circle(startPoint, radius);
		c.setColor(dlgCircle.getColor());
		c.setInnerColor(dlgCircle.getInnerColor());
		command = new CmdAddShape(model, c);
		command.execute();
		frame.repaint();
	}

	public void drawDonut(MouseEvent e) {
		DonutDialog dlgDonut = new DonutDialog();
		startPoint = new Point(e.getX(), e.getY());
		dlgDonut.pack();
		dlgDonut.setVisible(true);
		if (dlgDonut.isConfirmed()) {
			radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
			innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
		}
		Donut d = new Donut(startPoint, radius, innerRadius);
		d.setColor(dlgDonut.getColor());
		d.setInnerColor(dlgDonut.getInnerColor());
		command = new CmdAddShape(model, d);
		command.execute();
		frame.repaint();
	}

	public void drawHexagon(MouseEvent e) {
		HexagonDialog dlgHexagon = new HexagonDialog();
		dlgHexagon.pack();
		dlgHexagon.setVisible(true);
		if (dlgHexagon.isConfirmed()) {
			radius = Integer.parseInt(dlgHexagon.getTxtRadius().getText());
		}
		HexagonAdapter hexagonAdapter = new HexagonAdapter(new Point(e.getX(), e.getY()), radius);
		hexagonAdapter.setHexagonBorderColor(dlgHexagon.getColor());
		hexagonAdapter.setHexagonInnerColor(dlgHexagon.getInnerColor());
		command = new CmdAddShape(model, hexagonAdapter);
		command.execute();
		frame.repaint();
	}

	public void modifyShape() {

	}

	public void deleteShape() {
		Shape shape;

		for (int i = 0; i < selectedShapes.size(); i++) {
			shape = selectedShapes.get(0);
			command = new CmdRemoveShape(model, shape, model.getShapes().indexOf(shape));
			command.execute();
			selectedShapes.remove(shape);
		}

		frame.repaint();
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}
}
