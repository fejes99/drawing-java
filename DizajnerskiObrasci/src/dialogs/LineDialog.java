package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class LineDialog extends JDialog {

	private Color color = null;
	private Boolean confirmed;

	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private JLabel lblStartPointX;
	private JLabel lblStartPointY;
	private JLabel lblEndPointX;
	private JLabel lblEndPointY;
	private JLabel lblColor;
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	private JButton btnColor;
	private JButton btnConfirm;
	private JButton btnCancel;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public JTextField getTxtStartPointX() {
		return txtStartPointX;
	}

	public void setTxtStartPointX(JTextField txtStartPointX) {
		this.txtStartPointX = txtStartPointX;
	}

	public JTextField getTxtStartPointY() {
		return txtStartPointY;
	}

	public void setTxtStartPointY(JTextField txtStartPointY) {
		this.txtStartPointY = txtStartPointY;
	}

	public JTextField getTxtEndPointX() {
		return txtEndPointX;
	}

	public void setTxtEndPointX(JTextField txtEndPointX) {
		this.txtEndPointX = txtEndPointX;
	}

	public JTextField getTxtEndPointY() {
		return txtEndPointY;
	}

	public void setTxtEndPointY(JTextField txtEndPointY) {
		this.txtEndPointY = txtEndPointY;
	}

	public LineDialog(String type) {

		setTitle(type + " line");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][]"));

		lblStartPointX = new JLabel("Start X:");
		pnlCenter.add(lblStartPointX, "cell 0 0,alignx left");

		txtStartPointX = new JTextField();
		txtStartPointX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtStartPointX, "cell 1 0,growx");
		txtStartPointX.setColumns(10);

		lblStartPointY = new JLabel("Start Y:");
		pnlCenter.add(lblStartPointY, "cell 0 1,alignx left");

		txtStartPointY = new JTextField();
		txtStartPointY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtStartPointY, "cell 1 1,growx");
		txtStartPointY.setColumns(10);

		lblEndPointX = new JLabel("End X:");
		pnlCenter.add(lblEndPointX, "cell 0 2,alignx left");

		txtEndPointX = new JTextField();
		txtEndPointX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtEndPointX, "cell 1 2,growx");
		txtEndPointX.setColumns(10);

		lblEndPointY = new JLabel("End Y:");
		pnlCenter.add(lblEndPointY, "cell 0 3,alignx left");

		txtEndPointY = new JTextField();
		txtEndPointY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtEndPointY, "cell 1 3,growx");
		txtEndPointY.setColumns(10);

		lblColor = new JLabel("Color:");
		pnlCenter.add(lblColor, "cell 0 4");

		btnColor = new JButton("Color");
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color = JColorChooser.showDialog(null, "Outer circle border color:", Color.RED);
			}
		});
		pnlCenter.add(btnColor, "cell 1 4,alignx center");

		pnlBottom = new JPanel();
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (txtStartPointX.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter radius value", "ERROR",
								JOptionPane.ERROR_MESSAGE, null);
						confirmed = false;
						return;
					} else if (txtStartPointY.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter radius value", "ERROR",
								JOptionPane.ERROR_MESSAGE, null);
						confirmed = false;
						return;
					} else if (txtEndPointX.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter radius value", "ERROR",
								JOptionPane.ERROR_MESSAGE, null);
						confirmed = false;
						return;
					} else if (txtEndPointY.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter radius value", "ERROR",
								JOptionPane.ERROR_MESSAGE, null);
						confirmed = false;
						return;
					}
				} catch (NumberFormatException exc) {
					JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "ERROR",
							JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				confirmed = true;
				setVisible(false);
			}
		});
		pnlBottom.add(btnConfirm);
		getRootPane().setDefaultButton(btnConfirm);

//		null pointer exception
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmed = false;
				dispose();
			}
		});
		pnlBottom.add(btnCancel);

	}

}
