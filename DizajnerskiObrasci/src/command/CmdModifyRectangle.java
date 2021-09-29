package command;

import geometry.Rectangle;

public class CmdModifyRectangle implements Command {

	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle original = new Rectangle();

	public CmdModifyRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		original = oldRectangle.clone(original);
		oldRectangle = newRectangle.clone(oldRectangle);
	}

	@Override
	public void unexecute() {
		oldRectangle = original.clone(oldRectangle);
	}

	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newRectangle + "\n";
	}

}
