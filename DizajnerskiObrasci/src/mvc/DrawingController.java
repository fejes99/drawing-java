package mvc;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeselectShape;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdRemoveShape;
import command.CmdSelectShape;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import dialogs.CircleDialog;
import dialogs.DonutDialog;
import dialogs.HexagonDialog;
import dialogs.LineDialog;
import dialogs.PointDialog;
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

	private Command command;

	private int height;
	private int width;
	private int radius;
	private int innerRadius;

	private Point startPoint;
	private Point endPoint;

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();

	private ArrayList<Shape> undoShapes = new ArrayList<Shape>();
	private ArrayList<Shape> redoShapes = new ArrayList<Shape>();

	private int undoCounter = 0;
	private int redoCounter = 0;

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
				command = new CmdDeselectShape(this, selectedShape);
				command.execute();
				undoStack.push(command);
				frame.getTextArea().append(command.toString());
			} else {
				command = new CmdSelectShape(this, selectedShape);
				command.execute();
				undoStack.push(command);
				frame.getTextArea().append(command.toString());
			}
			undoCounter++;
			redoStack.clear();
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void drawPoint(MouseEvent e) {
		PointDialog dlgPoint = new PointDialog("Create");
		dlgPoint.pack();
		dlgPoint.getTxtPointX().setText(Integer.toString(e.getX()));
		dlgPoint.getTxtPointY().setText(Integer.toString(e.getY()));
		dlgPoint.getTxtPointX().setEnabled(false);
		dlgPoint.getTxtPointY().setEnabled(false);
		dlgPoint.setVisible(true);
		if (dlgPoint.isConfirmed()) {
			Point p = new Point(e.getX(), e.getY(), false);
			p.setColor(dlgPoint.getColor());
			command = new CmdAddShape(model, p);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void drawLine(MouseEvent e) {
		LineDialog dlgLine = new LineDialog("Create");
		if (startPoint == null) {
			startPoint = new Point(e.getX(), e.getY());
			return;
		}
		endPoint = new Point(e.getX(), e.getY());
		dlgLine.pack();
		dlgLine.getTxtStartPointX().setText(Integer.toString(startPoint.getX()));
		dlgLine.getTxtStartPointY().setText(Integer.toString(startPoint.getY()));
		dlgLine.getTxtEndPointX().setText(Integer.toString(endPoint.getX()));
		dlgLine.getTxtEndPointY().setText(Integer.toString(endPoint.getY()));
		dlgLine.getTxtStartPointX().setEnabled(false);
		dlgLine.getTxtStartPointY().setEnabled(false);
		dlgLine.getTxtEndPointX().setEnabled(false);
		dlgLine.getTxtEndPointY().setEnabled(false);
		dlgLine.setVisible(true);
		if (dlgLine.isConfirmed()) {
			Line l = new Line(startPoint, endPoint, false);
			l.setColor(dlgLine.getColor());
			command = new CmdAddShape(model, l);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		startPoint = null;
		undoRedoButtons();
		frame.repaint();
	}

	public void drawRectangle(MouseEvent e) {
		RectangleDialog dlgRectangle = new RectangleDialog("Create");
		startPoint = new Point(e.getX(), e.getY());
		dlgRectangle.pack();
		dlgRectangle.getTxtStartPointX().setText(Integer.toString(startPoint.getX()));
		dlgRectangle.getTxtStartPointY().setText(Integer.toString(startPoint.getY()));
		dlgRectangle.getTxtStartPointX().setEnabled(false);
		dlgRectangle.getTxtStartPointY().setEnabled(false);
		dlgRectangle.setVisible(true);
		if (dlgRectangle.isConfirmed()) {
			height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
			width = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
			Rectangle r = new Rectangle(startPoint, height, width);
			r.setColor(dlgRectangle.getColor());
			r.setInnerColor(dlgRectangle.getInnerColor());
			command = new CmdAddShape(model, r);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void drawCircle(MouseEvent e) {
		CircleDialog dlgCircle = new CircleDialog("Create");
		startPoint = new Point(e.getX(), e.getY());
		dlgCircle.pack();
		dlgCircle.getTxtCenterX().setText(Integer.toString(startPoint.getX()));
		dlgCircle.getTxtCenterY().setText(Integer.toString(startPoint.getY()));
		dlgCircle.getTxtCenterX().setEnabled(false);
		dlgCircle.getTxtCenterY().setEnabled(false);
		dlgCircle.setVisible(true);
		if (dlgCircle.isConfirmed()) {
			radius = Integer.parseInt(dlgCircle.getTxtRadius().getText());
			Circle c = new Circle(startPoint, radius);
			c.setColor(dlgCircle.getColor());
			c.setInnerColor(dlgCircle.getInnerColor());
			command = new CmdAddShape(model, c);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void drawDonut(MouseEvent e) {
		DonutDialog dlgDonut = new DonutDialog("Create");
		startPoint = new Point(e.getX(), e.getY());
		dlgDonut.pack();
		dlgDonut.getTxtCenterX().setText(Integer.toString(startPoint.getX()));
		dlgDonut.getTxtCenterY().setText(Integer.toString(startPoint.getY()));
		dlgDonut.getTxtCenterX().setEnabled(false);
		dlgDonut.getTxtCenterY().setEnabled(false);
		dlgDonut.setVisible(true);
		if (dlgDonut.isConfirmed()) {
			radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
			innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
			Donut d = new Donut(startPoint, radius, innerRadius);
			d.setColor(dlgDonut.getColor());
			d.setInnerColor(dlgDonut.getInnerColor());
			command = new CmdAddShape(model, d);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void drawHexagon(MouseEvent e) {
		HexagonDialog dlgHexagon = new HexagonDialog("Create");
		startPoint = new Point(e.getX(), e.getY());
		dlgHexagon.pack();
		dlgHexagon.getTxtCenterX().setText(Integer.toString(startPoint.getX()));
		dlgHexagon.getTxtCenterY().setText(Integer.toString(startPoint.getY()));
		dlgHexagon.getTxtCenterX().setEnabled(false);
		dlgHexagon.getTxtCenterY().setEnabled(false);
		dlgHexagon.setVisible(true);
		if (dlgHexagon.isConfirmed()) {
			radius = Integer.parseInt(dlgHexagon.getTxtRadius().getText());
			HexagonAdapter hexagonAdapter = new HexagonAdapter(new Point(e.getX(), e.getY()), radius);
			hexagonAdapter.setHexagonBorderColor(dlgHexagon.getColor());
			hexagonAdapter.setHexagonInnerColor(dlgHexagon.getInnerColor());
			command = new CmdAddShape(model, hexagonAdapter);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void modifyShape() {

		if (selectedShapes.get(0) instanceof Point) {
			Point oldPoint = (Point) selectedShapes.get(0);
			PointDialog dlgPoint = new PointDialog("Modify");
			dlgPoint.getTxtPointX().setText(Integer.toString(oldPoint.getX()));
			dlgPoint.getTxtPointY().setText(Integer.toString(oldPoint.getY()));
			dlgPoint.pack();
			dlgPoint.setVisible(true);
			if (dlgPoint.isConfirmed()) {
				Point newPoint = new Point(Integer.parseInt(dlgPoint.getTxtPointX().getText()),
						Integer.parseInt(dlgPoint.getTxtPointY().getText()), true, dlgPoint.getColor());
				command = new CmdModifyPoint(oldPoint, newPoint);
				command.execute();
				undoStack.push(command);
				undoCounter++;
				redoStack.clear();
				frame.getTextArea().append(command.toString());
			}

		} else if (selectedShapes.get(0) instanceof Line) {
			Line oldLine = (Line) selectedShapes.get(0);
			LineDialog dlgLine = new LineDialog("Modify");
			dlgLine.getTxtStartPointX().setText(Integer.toString(oldLine.getStartPoint().getX()));
			dlgLine.getTxtStartPointY().setText(Integer.toString(oldLine.getStartPoint().getY()));
			dlgLine.getTxtEndPointX().setText(Integer.toString(oldLine.getEndPoint().getX()));
			dlgLine.getTxtEndPointY().setText(Integer.toString(oldLine.getEndPoint().getY()));
			dlgLine.pack();
			dlgLine.setVisible(true);
			if (dlgLine.isConfirmed()) {
				Point newStartPoint = new Point(Integer.parseInt(dlgLine.getTxtStartPointX().getText()),
						Integer.parseInt(dlgLine.getTxtStartPointY().getText()));
				Point newEndPoint = new Point(Integer.parseInt(dlgLine.getTxtEndPointX().getText()),
						Integer.parseInt(dlgLine.getTxtEndPointY().getText()));
				Line newLine = new Line(newStartPoint, newEndPoint, dlgLine.getColor());
				command = new CmdModifyLine(oldLine, newLine);
				command.execute();
				undoStack.push(command);
				undoCounter++;
				redoStack.clear();
				frame.getTextArea().append(command.toString());
			}

		} else if (selectedShapes.get(0) instanceof Rectangle) {
			Rectangle oldRectangle = (Rectangle) selectedShapes.get(0);
			RectangleDialog dlgRectangle = new RectangleDialog("Modify");
			dlgRectangle.getTxtStartPointX().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getX()));
			dlgRectangle.getTxtStartPointY().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getY()));
			dlgRectangle.getTxtHeight().setText(Integer.toString(oldRectangle.getHeight()));
			dlgRectangle.getTxtWidth().setText(Integer.toString(oldRectangle.getWidth()));
			dlgRectangle.pack();
			dlgRectangle.setVisible(true);
			if (dlgRectangle.isConfirmed()) {
				Point newStartPoint = new Point(Integer.parseInt(dlgRectangle.getTxtStartPointX().getText()),
						Integer.parseInt(dlgRectangle.getTxtStartPointY().getText()));
				Rectangle newRectangle = new Rectangle(newStartPoint,
						Integer.parseInt(dlgRectangle.getTxtHeight().getText()),
						Integer.parseInt(dlgRectangle.getTxtWidth().getText()), true, dlgRectangle.getColor(),
						dlgRectangle.getInnerColor());
				command = new CmdModifyRectangle(oldRectangle, newRectangle);
				command.execute();
				undoStack.push(command);
				undoCounter++;
				redoStack.clear();
				frame.getTextArea().append(command.toString());
			}

		} else if (selectedShapes.get(0) instanceof Donut) {
			Donut oldDonut = (Donut) selectedShapes.get(0);
			DonutDialog dlgDonut = new DonutDialog("Modify");
			dlgDonut.getTxtCenterX().setText(Integer.toString(oldDonut.getCenter().getX()));
			dlgDonut.getTxtCenterY().setText(Integer.toString(oldDonut.getCenter().getY()));
			dlgDonut.getTxtRadius().setText(Integer.toString(oldDonut.getRadius()));
			dlgDonut.getTxtInnerRadius().setText(Integer.toString(oldDonut.getInnerRadius()));
			dlgDonut.pack();
			dlgDonut.setVisible(true);
			if (dlgDonut.isConfirmed()) {
				Point newStartPoint = new Point(Integer.parseInt(dlgDonut.getTxtCenterX().getText()),
						Integer.parseInt(dlgDonut.getTxtCenterY().getText()));
				Donut newDonut = new Donut(newStartPoint, Integer.parseInt(dlgDonut.getTxtRadius().getText()),
						Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()), true, dlgDonut.getColor(),
						dlgDonut.getInnerColor());
				command = new CmdModifyDonut(oldDonut, newDonut);
				command.execute();
				undoStack.push(command);
				undoCounter++;
				redoStack.clear();
				frame.getTextArea().append(command.toString());
			}

		} else if (selectedShapes.get(0) instanceof Circle) {
			Circle oldCircle = (Circle) selectedShapes.get(0);
			CircleDialog dlgCircle = new CircleDialog("Modify");
			dlgCircle.getTxtCenterX().setText(Integer.toString(oldCircle.getCenter().getX()));
			dlgCircle.getTxtCenterY().setText(Integer.toString(oldCircle.getCenter().getY()));
			dlgCircle.getTxtRadius().setText(Integer.toString(oldCircle.getRadius()));
			dlgCircle.pack();
			dlgCircle.setVisible(true);
			if (dlgCircle.isConfirmed()) {
				Point newStartPoint = new Point(Integer.parseInt(dlgCircle.getTxtCenterX().getText()),
						Integer.parseInt(dlgCircle.getTxtCenterY().getText()));
				Circle newCircle = new Circle(newStartPoint, Integer.parseInt(dlgCircle.getTxtRadius().getText()), true,
						dlgCircle.getColor(), dlgCircle.getInnerColor());
				command = new CmdModifyCircle(oldCircle, newCircle);
				command.execute();
				undoStack.push(command);
				undoCounter++;
				redoStack.clear();
				frame.getTextArea().append(command.toString());
			}

		} else if (selectedShapes.get(0) instanceof HexagonAdapter) {
			HexagonAdapter oldHexagon = (HexagonAdapter) selectedShapes.get(0);
			HexagonDialog dlgHexagon = new HexagonDialog("Modify");
			dlgHexagon.getTxtCenterX().setText(Integer.toString(oldHexagon.getHexagonCenter().getX()));
			dlgHexagon.getTxtCenterY().setText(Integer.toString(oldHexagon.getHexagonCenter().getY()));
			dlgHexagon.getTxtRadius().setText(Integer.toString(oldHexagon.getHexagonRadius()));
			dlgHexagon.pack();
			dlgHexagon.setVisible(true);
			if (dlgHexagon.isConfirmed()) {
				Point newStartPoint = new Point(Integer.parseInt(dlgHexagon.getTxtCenterX().getText()),
						Integer.parseInt(dlgHexagon.getTxtCenterY().getText()));
				HexagonAdapter newHexagon = new HexagonAdapter(newStartPoint,
						Integer.parseInt(dlgHexagon.getTxtRadius().getText()), true, dlgHexagon.getColor(),
						dlgHexagon.getInnerColor());
				command = new CmdModifyHexagon(oldHexagon, newHexagon);
				command.execute();
				undoStack.push(command);
				undoCounter++;
				redoStack.clear();
				frame.getTextArea().append(command.toString());
			}
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void deleteShape() {
		Shape shape;

		for (int i = selectedShapes.size() - 1; i >= 0; i--) {
			shape = selectedShapes.get(0);
			command = new CmdRemoveShape(model, shape, model.getShapes().indexOf(shape));
			command.execute();
			undoShapes.add(shape);
			undoStack.push(command);
			undoCounter++;
			frame.getTextArea().append(command.toString());
			selectedShapes.remove(shape);
		}
		redoStack.clear();
		undoRedoButtons();
		frame.repaint();
	}

	public void undo() {
		command = undoStack.peek();

		if (command instanceof CmdRemoveShape) {
			while (command instanceof CmdRemoveShape) {
				command.unexecute();
				this.redoShapes.add(this.undoShapes.get(this.undoShapes.size() - 1));
				this.selectedShapes.add(this.undoShapes.get(this.undoShapes.size() - 1));
				this.undoShapes.remove(this.undoShapes.size() - 1);
				this.frame.getTextArea().append("Undo " + undoStack.peek().toString());
				undoCounter--;
				redoCounter++;
				undoStack.pop();
				redoStack.push(command);
				command = undoStack.peek();
			}
		} else {
			command.unexecute();
			this.frame.getTextArea().append("Undo " + undoStack.peek().toString());
			undoCounter--;
			redoCounter++;
			undoStack.pop();
			redoStack.push(command);
		}
		undoRedoButtons();
		frame.repaint();

	}

	public void redo() {
		command = redoStack.peek();

		if (command instanceof CmdRemoveShape) {
			while (command instanceof CmdRemoveShape) {
				command.execute();
				this.undoShapes.add(this.redoShapes.get(this.redoShapes.size() - 1));
				this.selectedShapes.remove(this.redoShapes.get(this.redoShapes.size() - 1));
				this.redoShapes.remove(this.redoShapes.size() - 1);
				this.frame.getTextArea().append("Redo " + redoStack.peek().toString());
				undoCounter++;
				redoCounter--;
				redoStack.pop();
				undoStack.push(command);
				if (!redoStack.isEmpty()) {
					command = redoStack.peek();
				} else {
					command = null;
				}

			}
		} else {
			command.execute();
			this.frame.getTextArea().append("Redo " + redoStack.peek().toString());
			undoCounter++;
			redoCounter--;
			redoStack.pop();
			undoStack.push(command);
		}
		undoRedoButtons();
		frame.repaint();
	}

	public void undoRedoButtons() {
		if (undoCounter < 1) {
			frame.getBtnUndo().setEnabled(false);
		} else {
			frame.getBtnUndo().setEnabled(true);
		}

		if (redoCounter < 1 || redoStack.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
		} else {
			frame.getBtnRedo().setEnabled(true);
		}
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void toBack() {
		Shape shape = selectedShapes.get(0);
		CmdToBack command = new CmdToBack(model, shape);
		command.execute();
		frame.getTextArea().append(command.toString());
		undoStack.push(command);
		undoCounter++;
		redoStack.clear();
		frame.repaint();
	}

	public void toFront() {
		Shape shape = selectedShapes.get(0);
		CmdToFront command = new CmdToFront(model, shape);
		command.execute();
		frame.getTextArea().append(command.toString());
		undoStack.push(command);
		undoCounter++;
		redoStack.clear();
		frame.repaint();
	}

	public void bringToBack() {
		Shape shape = selectedShapes.get(0);
		CmdBringToBack command = new CmdBringToBack(model, shape);
		command.execute();
		frame.getTextArea().append(command.toString());
		undoStack.push(command);
		undoCounter++;
		redoStack.clear();
		undoRedoButtons();
		frame.repaint();
	}

	public void bringToFront() {
		Shape shape = selectedShapes.get(0);
		CmdBringToFront command = new CmdBringToFront(model, shape);
		command.execute();
		frame.getTextArea().append(command.toString());
		undoStack.push(command);
		undoCounter++;
		redoStack.clear();
		undoRedoButtons();
		frame.repaint();
	}
}
