package command;

import geometry.Point;

public class CmdModifyPoint implements Command {

	private Point oldPoint;
	private Point newPoint;
	private Point original = new Point();

	public CmdModifyPoint(Point oldPoint, Point newPoint) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}

	@Override
	public void execute() {
		original = oldPoint.clone(original);
		oldPoint = newPoint.clone(oldPoint);
	}

	@Override
	public void unexecute() {
		oldPoint = original.clone(oldPoint);
	}

	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newPoint + "\n";
	}

}
