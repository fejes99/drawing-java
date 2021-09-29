package command;

import geometry.Donut;

public class CmdModifyDonut implements Command {

	private Donut oldDonut;
	private Donut newDonut;
	private Donut original = new Donut();

	public CmdModifyDonut(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		original = oldDonut.clone(original);
		oldDonut = newDonut.clone(oldDonut);
	}

	@Override
	public void unexecute() {
		oldDonut = original.clone(oldDonut);
	}

	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newDonut + "\n";
	}

}
