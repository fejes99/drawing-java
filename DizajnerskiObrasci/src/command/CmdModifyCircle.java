package command;

import geometry.Circle;

public class CmdModifyCircle implements Command {

	private Circle oldCircle;
	private Circle newCircle;
	private Circle original = new Circle();

	public CmdModifyCircle(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		original = oldCircle.clone(original);
		oldCircle = newCircle.clone(oldCircle);
	}

	@Override
	public void unexecute() {
		oldCircle = original.clone(oldCircle);
	}

	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newCircle + "\n";
	}
}
