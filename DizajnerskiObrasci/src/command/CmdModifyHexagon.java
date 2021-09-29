package command;

import adapter.HexagonAdapter;
import geometry.Point;

public class CmdModifyHexagon implements Command {

	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter original = new HexagonAdapter(new Point(0, 0), 0);

	public CmdModifyHexagon(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		original = oldHexagon.clone(original);
		oldHexagon = newHexagon.clone(oldHexagon);
	}

	@Override
	public void unexecute() {
		oldHexagon = original.clone(oldHexagon);
	}

	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newHexagon + "\n";
	}

}
