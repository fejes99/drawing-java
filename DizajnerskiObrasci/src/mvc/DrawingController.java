package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dialogs.CreateCircleDialog;
import dialogs.CreateDonutDialog;
import dialogs.CreatePointLineDialog;
import dialogs.CreateRectangleDialog;
import dialogs.test;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	private int counter = 1;
	
	private int height;
	private int width;
	private int radius;
	private int innerRadius;
	
	private Point startPoint;
	private Point endPoint;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void drawPoint(MouseEvent e) {
		CreatePointLineDialog dlgCreatePoint = new CreatePointLineDialog("point");
		dlgCreatePoint.pack();
		dlgCreatePoint.setVisible(true);
		if (dlgCreatePoint.isConfirmed()) {
			Point p = new Point(e.getX(), e.getY(), false);
			p.setColor(dlgCreatePoint.getColor());
			model.add(p);
			frame.repaint();
		} // if canceled null pointer exception occur
	}
	
	public void drawLine(MouseEvent e) {
		CreatePointLineDialog dlgCreateLine = new CreatePointLineDialog("line");
		if(counter == 1) {
			startPoint=	new Point(e.getX(), e.getY());
			counter++;
		} else {
			endPoint = new Point(e.getX(), e.getY());
			dlgCreateLine.pack();
			dlgCreateLine.setVisible(true);
			if(dlgCreateLine.isConfirmed()) {
				Line l = new Line(startPoint, endPoint, false);
				l.setColor(dlgCreateLine.getColor());
				model.add(l);
				frame.repaint();
			}
			counter = 1;
		}
	}
	
	public void drawRectangle(MouseEvent e) {
		CreateRectangleDialog dlgCreateRectangle = new CreateRectangleDialog();
		startPoint=	new Point(e.getX(), e.getY());
		dlgCreateRectangle.pack();
		dlgCreateRectangle.setVisible(true);
		if(dlgCreateRectangle .isConfirmed()) {
			height = Integer.parseInt(dlgCreateRectangle .getTxtHeight().getText());
			width = Integer.parseInt(dlgCreateRectangle .getTxtWidth().getText());
			Rectangle r = new Rectangle(startPoint, height, width);
			
			// not showing default color
			r.setLineColor(dlgCreateRectangle .getBorderColor());
			r.setInternalColor(dlgCreateRectangle .getInnerColor());
			model.add(r);
		}
		
		frame.repaint();
	}
	
	public void drawCircle(MouseEvent e) {
		CreateCircleDialog dlgCreateCircle = new CreateCircleDialog();
		startPoint = new Point(e.getX(), e.getY());
		dlgCreateCircle.pack();
		dlgCreateCircle.setVisible(true);
		if(dlgCreateCircle.isConfirmed()) {
			radius = Integer.parseInt(dlgCreateCircle.getTxtRadius().getText());
		}
		
		Circle c = new Circle(startPoint, radius);
		c.setColor(dlgCreateCircle.getBorderColor());
		c.setInternalColor(dlgCreateCircle.getInnerColor());
		model.add(c);
		frame.repaint();
	}
	
	// color scheme is not correct
	public void drawDonut(MouseEvent e) {
		CreateDonutDialog dlgCreateDonut = new CreateDonutDialog();
		startPoint = new Point(e.getX(), e.getY());
		dlgCreateDonut.pack();
		dlgCreateDonut.setVisible(true);
		if(dlgCreateDonut.isConfirmed()) {
			radius = Integer.parseInt(dlgCreateDonut.getTxtOuterCircleRadius().getText());
			innerRadius = Integer.parseInt(dlgCreateDonut.getTxtOuterCircleRadius().getText());
		}
		Donut d = new Donut(startPoint, radius, innerRadius);
		d.setOuterCircleBorderColor(dlgCreateDonut.getOuterCircleBorderColor());
		d.setInnerCircleBorderColor(dlgCreateDonut.getInnerCircleBorderColor());
		d.setOuterCircleFillColor(dlgCreateDonut.getOuterCircleFillColor());
		d.setInnerCircleFillColor(dlgCreateDonut.getInnerCircleFillColor());
		model.add(d);
		frame.repaint();
	}
	
	public void deleteShape() {
	}
	
	public void modifyShape() {
		
	}

}
