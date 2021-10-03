package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import observer.ButtonObserver;
import observer.ButtonObserverUpdate;
import strategy.SaveLog;
import strategy.SaveManager;
import strategy.SavePainting;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;

	private Command command;

	private ButtonObserver btnObserver = new ButtonObserver();
	private ButtonObserverUpdate btnObserverUpdate;

	private int height;
	private int width;
	private int radius;
	private int innerRadius;

	public Color color = new Color(0, 0, 0);
	public Color innerColor = new Color(255, 255, 255);

	private Point startPoint;
	private Point endPoint;

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();

	private ArrayList<Shape> undoShapes = new ArrayList<Shape>();
	private ArrayList<Shape> redoShapes = new ArrayList<Shape>();

	private int undoCounter = 0;
	private int redoCounter = 0;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		btnObserverUpdate = new ButtonObserverUpdate(frame);
		btnObserver.addPropertyChangeListener(btnObserverUpdate);
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
		frame.repaint();
		undoRedoButtons();
		buttonsState();
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
			if (dlgPoint.getColor() == null) {
				p.setColor(color);
			} else {
				p.setColor(dlgPoint.getColor());
			}
			command = new CmdAddShape(model, p);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		buttonsState();
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
			if (dlgLine.getColor() == null) {
				l.setColor(color);
			} else {
				l.setColor(dlgLine.getColor());
			}
			command = new CmdAddShape(model, l);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		startPoint = null;
		undoRedoButtons();
		buttonsState();
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
			if (dlgRectangle.getColor() != null && dlgRectangle.getInnerColor() == null) {
				r.setColor(dlgRectangle.getColor());
				r.setInnerColor(innerColor);
			} else if (dlgRectangle.getColor() == null && dlgRectangle.getInnerColor() != null) {
				r.setColor(color);
				r.setInnerColor(dlgRectangle.getInnerColor());
			} else if (dlgRectangle.getColor() == null && dlgRectangle.getInnerColor() == null) {
				r.setColor(color);
				r.setInnerColor(innerColor);
			} else {
				r.setColor(dlgRectangle.getColor());
				r.setInnerColor(dlgRectangle.getInnerColor());
			}
			command = new CmdAddShape(model, r);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		buttonsState();
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
			if (dlgCircle.getColor() != null && dlgCircle.getInnerColor() == null) {
				c.setColor(dlgCircle.getColor());
				c.setInnerColor(innerColor);
			} else if (dlgCircle.getColor() == null && dlgCircle.getInnerColor() != null) {
				c.setColor(color);
				c.setInnerColor(dlgCircle.getInnerColor());
			} else if (dlgCircle.getColor() == null && dlgCircle.getInnerColor() == null) {
				c.setColor(color);
				c.setInnerColor(innerColor);
			} else {
				c.setColor(dlgCircle.getColor());
				c.setInnerColor(dlgCircle.getInnerColor());
			}
			command = new CmdAddShape(model, c);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		buttonsState();
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
			if (dlgDonut.getColor() != null && dlgDonut.getInnerColor() == null) {
				d.setColor(dlgDonut.getColor());
				d.setInnerColor(innerColor);
			} else if (dlgDonut.getColor() == null && dlgDonut.getInnerColor() != null) {
				d.setColor(color);
				d.setInnerColor(dlgDonut.getInnerColor());
			} else if (dlgDonut.getColor() == null && dlgDonut.getInnerColor() == null) {
				d.setColor(color);
				d.setInnerColor(innerColor);
			} else {
				d.setColor(dlgDonut.getColor());
				d.setInnerColor(dlgDonut.getInnerColor());
			}
			command = new CmdAddShape(model, d);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		buttonsState();
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
			HexagonAdapter h = new HexagonAdapter(new Point(e.getX(), e.getY()), radius);
			if (dlgHexagon.getColor() != null && dlgHexagon.getInnerColor() == null) {
				h.setHexagonBorderColor(dlgHexagon.getColor());
				h.setHexagonInnerColor(innerColor);
			} else if (dlgHexagon.getColor() == null && dlgHexagon.getInnerColor() != null) {
				h.setHexagonBorderColor(color);
				h.setHexagonInnerColor(dlgHexagon.getInnerColor());
			} else if (dlgHexagon.getColor() == null && dlgHexagon.getInnerColor() == null) {
				h.setHexagonBorderColor(color);
				h.setHexagonInnerColor(innerColor);
			} else {
				h.setHexagonBorderColor(dlgHexagon.getColor());
				h.setHexagonInnerColor(dlgHexagon.getInnerColor());
			}
			command = new CmdAddShape(model, h);
			command.execute();
			undoStack.push(command);
			undoCounter++;
			redoStack.clear();
			frame.getTextArea().append(command.toString());
		}
		undoRedoButtons();
		buttonsState();
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
		buttonsState();
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
		buttonsState();
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
		buttonsState();
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
		buttonsState();
		frame.repaint();
	}

	public void toBack() {
		Shape shape = selectedShapes.get(0);
		CmdToBack command = new CmdToBack(model, shape);
		command.execute();
		frame.getTextArea().append(command.toString());
		undoStack.push(command);
		undoCounter++;
		redoStack.clear();
		undoRedoButtons();
		buttonsState();
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
		undoRedoButtons();
		buttonsState();
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
		buttonsState();
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
		buttonsState();
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

	public void buttonsState() {
		if (model.getShapes().size() != 0) {
			btnObserver.setSelectBtnActivated(true);
			if (selectedShapes.size() != 0) {
				if (selectedShapes.size() == 1) {
					btnObserver.setModifyBtnActivated(true);
					buttonUpdate();
				} else {
					btnObserver.setModifyBtnActivated(false);
					btnObserver.setToBackBtnActivated(false);
					btnObserver.setToFrontBtnActivated(false);
					btnObserver.setBringToBackBtnActivated(false);
					btnObserver.setBringToFrontBtnActivated(false);
				}
				btnObserver.setDeleteBtnActivated(true);
			} else {
				btnObserver.setModifyBtnActivated(false);
				btnObserver.setDeleteBtnActivated(false);
				btnObserver.setToBackBtnActivated(false);
				btnObserver.setToFrontBtnActivated(false);
				btnObserver.setBringToBackBtnActivated(false);
				btnObserver.setBringToFrontBtnActivated(false);
			}
		} else {
			btnObserver.setSelectBtnActivated(false);
			btnObserver.setModifyBtnActivated(false);
			btnObserver.setDeleteBtnActivated(false);
			btnObserver.setToBackBtnActivated(false);
			btnObserver.setToFrontBtnActivated(false);
			btnObserver.setBringToBackBtnActivated(false);
			btnObserver.setBringToFrontBtnActivated(false);
		}
	}

	public void buttonUpdate() {
		Iterator<Shape> it = this.model.getShapes().iterator();
		Shape shape;

		while (it.hasNext()) {
			shape = it.next();

			if (shape.isSelected()) {
				if (model.getShapes().size() == 1) {
					btnObserver.setToFrontBtnActivated(false);
					btnObserver.setToBackBtnActivated(false);
					btnObserver.setBringToFrontBtnActivated(false);
					btnObserver.setBringToBackBtnActivated(false);
				} else {
					if (shape.equals(model.getShape(model.getShapes().size() - 1))) {
						btnObserver.setToFrontBtnActivated(false);
						btnObserver.setToBackBtnActivated(true);
						btnObserver.setBringToFrontBtnActivated(false);
						btnObserver.setBringToBackBtnActivated(true);
					} else if (shape.equals(model.getShape(0))) {
						btnObserver.setToFrontBtnActivated(true);
						btnObserver.setToBackBtnActivated(false);
						btnObserver.setBringToFrontBtnActivated(true);
						btnObserver.setBringToBackBtnActivated(false);
					} else {
						btnObserver.setToFrontBtnActivated(true);
						btnObserver.setToBackBtnActivated(true);
						btnObserver.setBringToFrontBtnActivated(true);
						btnObserver.setBringToBackBtnActivated(true);
					}
				}
			}
		}
	}

	public void savePainting() throws IOException, NotSerializableException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save painting");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".bin", "bin");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File paintingToSave = fileChooser.getSelectedFile();
			File logToSave;
			String filePath = paintingToSave.getAbsolutePath();
			if (!filePath.endsWith(".bin") && !filePath.contains(".")) {
				paintingToSave = new File(filePath + ".bin");
				logToSave = new File(filePath + ".txt");
			}

			String filename = paintingToSave.getPath();
			System.out.println("Painting saved at: " + paintingToSave.getAbsolutePath());
			System.out.println(filename.substring(filename.lastIndexOf("."), filename.length()));
			if (filename.substring(filename.lastIndexOf("."), filename.length()).contains(".bin")) {
				filename = paintingToSave.getAbsolutePath().substring(0, filename.lastIndexOf(".")) + ".txt";
				logToSave = new File(filename);
				SaveManager savePainting = new SaveManager(new SavePainting());
				SaveManager saveLog = new SaveManager(new SaveLog());
				savePainting.save(model, paintingToSave);
				saveLog.save(frame, logToSave);
			} else {
				JOptionPane.showMessageDialog(null, "Wrong file extension!");
			}
		}
	}

	public void openPainting() throws IOException, ClassNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(".bin", "bin");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(fileNameExtensionFilter);

		fileChooser.setDialogTitle("Open painting");
		int userSelection = fileChooser.showOpenDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File paintingToLoad = fileChooser.getSelectedFile();
			loadPainting(paintingToLoad);

		}
	}

	@SuppressWarnings("unchecked")
	public void loadPainting(File paintingToLoad) throws FileNotFoundException, IOException, ClassNotFoundException {
		frame.getTextArea().setText("");

		File file = new File(paintingToLoad.getAbsolutePath().replace("bin", "txt"));

		if (file.length() == 0) {
			System.out.println("\"" + paintingToLoad.getName() + "\" file is empty!");
			return;
		}

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String logLine;

		while ((logLine = bufferedReader.readLine()) != null) {
			frame.getTextArea().append(logLine + "\n");
		}
		bufferedReader.close();

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(paintingToLoad));
		try {
			model.getShapes().addAll((ArrayList<Shape>) objectInputStream.readObject());
			objectInputStream.close();
		} catch (InvalidClassException ice) {
			ice.printStackTrace();
		} catch (SocketTimeoutException ste) {
			ste.printStackTrace();
		} catch (EOFException eofe) {
			eofe.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		frame.repaint();
	}

	public void saveLog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save log");
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(".txt", "txt");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(fileNameExtensionFilter);

		if (fileChooser.showSaveDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Successfully saved " + fileChooser.getSelectedFile().getName() + " file!");
			File file = fileChooser.getSelectedFile();
			String filePath = file.getAbsolutePath();
			File logToSave = new File(filePath + ".txt");

			SaveManager manager = new SaveManager(new SaveLog());
			manager.save(frame, logToSave);
		}
		frame.getView().repaint();
	}
}
