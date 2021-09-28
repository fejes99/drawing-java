package mvc;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);

		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);

		frame.setTitle("Drawing | David Fejes IT70-2017");
		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
