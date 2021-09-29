package command;

import geometry.Shape;
import mvc.DrawingController;

public class CmdSelectShape implements Command {

	DrawingController controller;
	Shape shape;

	public CmdSelectShape(DrawingController controller, Shape shape) {
		this.controller = controller;
		this.shape = shape;
	}

	@Override
	public void execute() {
		this.shape.setSelected(true);
		controller.getSelectedShapes().add(shape);

	}

	@Override
	public void unexecute() {
		this.shape.setSelected(false);
		controller.getSelectedShapes().remove(shape);
	}

	public String toString() {
		return "Selected - " + this.shape + "\n";
	}

}
