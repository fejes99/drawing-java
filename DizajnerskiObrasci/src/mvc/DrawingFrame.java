package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	private JMenu menuMain;
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
	private JButton btnLoadNext;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JPanel pnlUndoRedo;
	private JPanel pnlColors;
	private JButton btnInnerColor;
	private JButton btnColor;
	private JMenuItem menuItemSavePainting;
	private JMenuItem menuItemOpenPainting;
	private JMenuItem menuItemSaveLog;
	private JMenuItem menuItemOpenLog;

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

	public JButton getBtnLoadNext() {
		return btnLoadNext;
	}

	public void setBtnLoadNext(JButton btnLoadNext) {
		this.btnLoadNext = btnLoadNext;
	}

	public JToggleButton getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(JToggleButton btnSelect) {
		this.btnSelect = btnSelect;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JToggleButton getBtnPoint() {
		return btnPoint;
	}

	public JToggleButton getBtnLine() {
		return btnLine;
	}

	public JToggleButton getBtnRectangle() {
		return btnRectangle;
	}

	public JToggleButton getBtnCircle() {
		return btnCircle;
	}

	public JToggleButton getBtnDonut() {
		return btnDonut;
	}

	public JToggleButton getBtnHexagon() {
		return btnHexagon;
	}

	public DrawingFrame() {
		setBounds(100, 100, 1216, 800);
		view.setBackground(Color.WHITE);
		view.addMouseListener(new MouseAdapter() {
			@Override
			// refactor this else if with polymorphism if its possible
			public void mouseClicked(MouseEvent e) {
//				try {
//					if (btnPoint.isSelected()) {
//						controller.drawPoint(e);
//					} else if (btnLine.isSelected()) {
//						controller.drawLine(e);
//					} else if (btnRectangle.isSelected()) {
//						controller.drawRectangle(e);
//					} else if (btnCircle.isSelected()) {
//						controller.drawCircle(e);
//					} else if (btnDonut.isSelected()) {
//						controller.drawDonut(e);
//					} else if (btnHexagon.isSelected()) {
//						controller.drawHexagon(e);
//					} else if (btnSelect.isSelected()) {
//						controller.selectShapes(e);
//					}
//				} catch (Exception exception) {
//					System.out.println(exception);
//				}
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
			}
		});

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuMain = new JMenu("File");
		menuBar.add(menuMain);

		menuItemSavePainting = new JMenuItem("Save painting");
		menuItemSavePainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.savePainting();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		menuMain.add(menuItemSavePainting);

		menuItemOpenPainting = new JMenuItem("Open painting");
		menuItemOpenPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openPainting();
				} catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		menuMain.add(menuItemOpenPainting);

		menuItemOpenLog = new JMenuItem("Open log");
		menuItemOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openLog();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		menuMain.add(menuItemOpenLog);

		menuItemSaveLog = new JMenuItem("Save log");
		menuItemSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		menuMain.add(menuItemSaveLog);

		shapeBtns = new ButtonGroup();

		pnlFunctions = new JPanel();

		btnSelect = new JToggleButton("Select");
		btnSelect.setToolTipText("Select shape");
		btnSelect.setEnabled(false);
		pnlFunctions.add(btnSelect);
		shapeBtns.add(btnSelect);

		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifyShape();
			}
		});
		pnlFunctions.add(btnModify);

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deleteShape();
			}
		});
		pnlFunctions.add(btnDelete);

		JPanel pnlLog = new JPanel();

		JPanel pnlLocation = new JPanel();

		pnlUndoRedo = new JPanel();

		pnlColors = new JPanel();

		JPanel pnlShapes = new JPanel();

		btnLoadNext = new JButton("Load next");
		btnLoadNext.setEnabled(false);
		btnLoadNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadNext();
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(pnlLog, GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE))
										.addComponent(view, GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(pnlFunctions, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(pnlUndoRedo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(pnlLocation, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(pnlColors, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnLoadNext)))
								.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(btnLoadNext)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlColors, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pnlFunctions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(pnlUndoRedo, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
								.addComponent(pnlLocation, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(view, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(pnlLog, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)));

		btnPoint = new JToggleButton("Point");
		pnlShapes.add(btnPoint);
		btnPoint.setToolTipText("Draw point");
		shapeBtns.add(btnPoint);

		btnLine = new JToggleButton("Line");
		pnlShapes.add(btnLine);
		btnLine.setToolTipText("Draw line");
		shapeBtns.add(btnLine);

		btnRectangle = new JToggleButton("Rectangle");
		pnlShapes.add(btnRectangle);
		btnRectangle.setToolTipText("Draw rectangle");
		shapeBtns.add(btnRectangle);

		btnCircle = new JToggleButton("Circle");
		pnlShapes.add(btnCircle);
		btnCircle.setToolTipText("Draw circle");
		shapeBtns.add(btnCircle);

		btnDonut = new JToggleButton("Donut");
		pnlShapes.add(btnDonut);
		btnDonut.setToolTipText("Draw donut");
		shapeBtns.add(btnDonut);

		btnHexagon = new JToggleButton("Hexagon");
		pnlShapes.add(btnHexagon);
		btnHexagon.setToolTipText("Draw hexagon");
		shapeBtns.add(btnHexagon);

		btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose color", Color.RED);
				if (temp != null) {
					controller.setColor(temp);
				}
			}
		});
		pnlColors.add(btnColor);

		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose color", Color.RED);
				if (temp != null) {
					controller.setInnerColor(temp);
				}
			}
		});
		pnlColors.add(btnInnerColor);

		btnUndo = new JButton("Undo");
		pnlUndoRedo.add(btnUndo);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnUndo.setEnabled(false);

		btnRedo = new JButton("Redo");
		pnlUndoRedo.add(btnRedo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		btnRedo.setEnabled(false);

		btnToBack = new JButton("To Back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		pnlLocation.add(btnToBack);

		btnToFront = new JButton("To Front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		pnlLocation.add(btnToFront);

		btnBringToBack = new JButton("Bring to Back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		pnlLocation.add(btnBringToBack);

		btnBringToFront = new JButton("Bring to Front");
		btnBringToFront.setEnabled(false);
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
