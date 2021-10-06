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

public class RectangleDialog extends JDialog {

	private Color color = null;
	private Color innerColor = null;
	private Boolean confirmed;

	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JLabel lblBorderColor;
	private JLabel lblInnerColor;
	private JLabel lblStartPointX;
	private JLabel lblStartPointY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JButton btnBorderColor;
	private JButton btnInnerColor;
	private JButton btnConfirm;
	private JButton btnCancel;

	public Color getColor() {
		return color;
	}

	public void setColor(Color borderColor) {
		this.color = borderColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public Boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtStartPointY() {
		return txtStartPointY;
	}

	public void setTxtStartPointY(JTextField txtStartPointY) {
		this.txtStartPointY = txtStartPointY;
	}

	public JLabel getLblStartPointY() {
		return lblStartPointY;
	}

	public void setLblStartPointY(JLabel lblStartPointY) {
		this.lblStartPointY = lblStartPointY;
	}

	public JTextField getTxtStartPointX() {
		return txtStartPointX;
	}

	public void setTxtStartPointX(JTextField txtStartPointX) {
		this.txtStartPointX = txtStartPointX;
	}

	public JLabel getLblStartPointX() {
		return lblStartPointX;
	}

	public void setLblStartPointX(JLabel lblStartPointX) {
		this.lblStartPointX = lblStartPointX;
	}

	public RectangleDialog(String type) {

		setTitle(type + " rectangle");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));

		lblStartPointX = new JLabel("X:");
		pnlCenter.add(lblStartPointX, "cell 0 0,alignx trailing");

		txtStartPointX = new JTextField();
		pnlCenter.add(txtStartPointX, "cell 1 0,growx");
		txtStartPointX.setColumns(10);

		lblStartPointY = new JLabel("Y:");
		pnlCenter.add(lblStartPointY, "cell 0 1,alignx trailing");

		txtStartPointY = new JTextField();
		pnlCenter.add(txtStartPointY, "cell 1 1,growx");
		txtStartPointY.setColumns(10);

		lblWidth = new JLabel("Width:");
		pnlCenter.add(lblWidth, "cell 0 2,alignx trailing");

		txtWidth = new JTextField();
		txtWidth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtWidth, "cell 1 2,growx");
		txtWidth.setColumns(10);

		lblHeight = new JLabel("Height:");
		pnlCenter.add(lblHeight, "cell 0 3,alignx trailing");

		txtHeight = new JTextField();
		txtHeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		pnlCenter.add(txtHeight, "cell 1 3,growx");
		txtHeight.setColumns(10);

		lblBorderColor = new JLabel("Color:");
		pnlCenter.add(lblBorderColor, "cell 0 4");

		btnBorderColor = new JButton("Color");
		btnBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color = JColorChooser.showDialog(null, "Border Color:", color.GREEN);
			}
		});
		pnlCenter.add(btnBorderColor, "cell 1 4");

		lblInnerColor = new JLabel("Color:");
		pnlCenter.add(lblInnerColor, "cell 0 5");

		btnInnerColor = new JButton("Color");
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				innerColor = JColorChooser.showDialog(null, "Inner Color:", color.WHITE);
			}
		});
		pnlCenter.add(btnInnerColor, "cell 1 5");

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
					} else if (txtWidth.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter radius value", "ERROR",
								JOptionPane.ERROR_MESSAGE, null);
						confirmed = false;
						return;
					} else if (txtHeight.getText().equals("")) {
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
