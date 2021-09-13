package mvc;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class DrawingFrame extends JFrame {

	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JPanel pnlBottom;
	private JMenuBar menuBar;
	private ButtonGroup shapeBtns;
	private JToggleButton btnPoint;
	private JToggleButton btnLine;
	private JToggleButton btnRectangle;
	private JToggleButton btnCircle;
	private JToggleButton btnDonut;
	private JToggleButton btnSelect;
	private JButton btnModify;
	private JButton btnDelete;

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public DrawingFrame() {

		view.addMouseListener(new MouseAdapter() {

			@Override
			// refactor this else if with polymorphism if its possible
			public void mouseClicked(MouseEvent e) {
				btnPoint.addMouseListener(this);

				try {
					if (btnPoint.isSelected()) {
						controller.drawPoint(e);
					} else if (btnLine.isSelected()) {
						controller.drawLine(e);
					} else if (btnRectangle.isSelected()) {
						controller.drawRectangle(e);
					} else if (btnCircle.isSelected()) {
						controller.drawCircle(e);
					} else if (btnDonut.isSelected()) {
						controller.drawDonut(e);
					} else if (btnSelect.isSelected()) {
						controller.selectShapes(e);
					}
				} catch (Exception exception) {
					System.out.println(exception);
				}

			}
		});

		getContentPane().add(view, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		shapeBtns = new ButtonGroup();

		btnPoint = new JToggleButton("Point");
		btnPoint.setToolTipText("Draw point");
		menuBar.add(btnPoint);
		shapeBtns.add(btnPoint);

		btnLine = new JToggleButton("Line");
		btnLine.setToolTipText("Draw line");
		menuBar.add(btnLine);
		shapeBtns.add(btnLine);

		btnRectangle = new JToggleButton("Rectangle");
		btnRectangle.setToolTipText("Draw rectangle");
		menuBar.add(btnRectangle);
		shapeBtns.add(btnRectangle);

		btnCircle = new JToggleButton("Circle");
		btnCircle.setToolTipText("Draw circle");
		menuBar.add(btnCircle);
		shapeBtns.add(btnCircle);

		btnDonut = new JToggleButton("Donut");
		btnDonut.setToolTipText("Draw donut");
		menuBar.add(btnDonut);
		shapeBtns.add(btnDonut);

		pnlBottom = new JPanel();
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);

		btnSelect = new JToggleButton("Select");
		btnSelect.setToolTipText("Select shape");
		pnlBottom.add(btnSelect);
		shapeBtns.add(btnSelect);

		btnModify = new JButton("Modify");
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		pnlBottom.add(btnModify);

		btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deleteShape();
			}
		});
		pnlBottom.add(btnDelete);
	}
}
