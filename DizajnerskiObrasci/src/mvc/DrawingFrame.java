package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DrawingFrame extends JFrame {

	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JPanel pnlFunctions;
	private JMenuBar menuBar;
	private ButtonGroup shapeBtns;
	private JToggleButton btnPoint;
	private JToggleButton btnLine;
	private JToggleButton btnRectangle;
	private JToggleButton btnCircle;
	private JToggleButton btnDonut;
	private JToggleButton btnSelect;
	private JToggleButton btnHexagon;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnUndo;
	private JButton btnRedo;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JPanel panel;

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public DrawingFrame() {

		setBounds(100, 100, 1000, 800);

		view.setBackground(Color.WHITE);
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
					} else if (btnHexagon.isSelected()) {
						controller.drawHexagon(e);
					} else if (btnSelect.isSelected()) {
						controller.selectShapes(e);
					}
				} catch (Exception exception) {
					System.out.println(exception);
				}

			}
		});

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

		btnHexagon = new JToggleButton("Hexagon");
		btnHexagon.setToolTipText("Draw hexagon");
		menuBar.add(btnHexagon);
		shapeBtns.add(btnHexagon);

		pnlFunctions = new JPanel();

		btnSelect = new JToggleButton("Select");
		btnSelect.setToolTipText("Select shape");
		pnlFunctions.add(btnSelect);
		shapeBtns.add(btnSelect);

		btnModify = new JButton("Modify");
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.modifyShape();
			}
		});
		pnlFunctions.add(btnModify);

		btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deleteShape();
			}
		});
		pnlFunctions.add(btnDelete);

		JPanel pnlLog = new JPanel();

		JPanel pnlLocation = new JPanel();

		panel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(pnlLog,
								GroupLayout.DEFAULT_SIZE, 1181, Short.MAX_VALUE))
						.addComponent(view, GroupLayout.DEFAULT_SIZE, 1187, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(pnlFunctions, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(pnlLocation,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(0)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlFunctions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlLocation, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(view, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnlLog, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)));

		btnUndo = new JButton("Undo");
		panel.add(btnUndo);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnUndo.setEnabled(false);

		btnRedo = new JButton("Redo");
		panel.add(btnRedo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		btnRedo.setEnabled(false);

		btnToBack = new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		pnlLocation.add(btnToBack);

		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		pnlLocation.add(btnToFront);

		btnBringToBack = new JButton("Bring to Back");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		pnlLocation.add(btnBringToBack);

		btnBringToFront = new JButton("Bring to Front");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		pnlLocation.add(btnBringToFront);

		scrollPane = new JScrollPane();
		GroupLayout gl_pnlLog = new GroupLayout(pnlLog);
		gl_pnlLog.setHorizontalGroup(
				gl_pnlLog.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlLog.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE).addContainerGap()));
		gl_pnlLog.setVerticalGroup(
				gl_pnlLog.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlLog.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE).addContainerGap()));

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		pnlLog.setLayout(gl_pnlLog);
		getContentPane().setLayout(groupLayout);
	}
}
