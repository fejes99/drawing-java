package command;

import geometry.Line;

public class CmdModifyLine implements Command {

	private Line oldLine;
	private Line newLine;
	private Line original = new Line();

	public CmdModifyLine(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		original = oldLine.clone(original);
		oldLine = newLine.clone(oldLine);
	}

	@Override
	public void unexecute() {
		oldLine = original.clone(oldLine);
	}

	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newLine + "\n";
	}

}
