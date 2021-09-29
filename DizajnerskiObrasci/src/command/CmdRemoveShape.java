package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdRemoveShape implements Command {

	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdRemoveShape(DrawingModel model, Shape shape, int index) {
		this.model = model;
		this.shape = shape;
		this.index = index;
	}

	@Override
	public void execute() {
		this.model.remove(shape);
	}

	@Override
	public void unexecute() {
		this.model.getShapes().add(index, shape);
	}

	public String toString() {
		return "Removed - " + this.shape + "\n";
	}

}
