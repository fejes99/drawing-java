package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdAddShape implements Command {

	private DrawingModel model;
	private Shape shape;

	public CmdAddShape(DrawingModel model, Shape shape) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(shape);

	}

	@Override
	public void unexecute() {
		model.remove(shape);
	}

	public String toString() {
		return "Added - " + shape + "\n";
	}

}
