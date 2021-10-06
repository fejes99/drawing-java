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

public class HexagonDialog extends JDialog {

	private Color color = null;
	private Color innerColor = null;
	private Boolean confirmed;

	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private JButton btnColor;
	private JButton btnInnerColor;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblCenterY;
	private JLabel lblCenterX;
	private JLabel lblRadius;
	private JLabel lblColor;
	private JLabel lblInnerColor;
	private JTextField txtRadius;
	private JTextField txtCenterY;
	private JTextField txtCenterX;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public JTextField getTxtCenterY() {
		return txtCenterY;
	}

	public void setTxtCenterY(JTextField txtCenterY) {
		this.txtCenterY = txtCenterY;
	}

	public JTextField getTxtCenterX() {
		return txtCenterX;
	}

	public void setTxtCenterX(JTextField txtCenterX) {
		this.txtCenterX = txtCenterX;
	}

	public HexagonDialog(String type) {
		setTitle(type + " Hexagon");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));

		lblCenterX = new JLabel("X:");
		pnlCenter.add(lblCenterX, "cell 0 0,alignx trailing");

		txtCenterX = new JTextField();
		pnlCenter.add(txtCenterX, "cell 1 0,growx");
		txtCenterX.setColumns(10);

		lblCenterY = new JLabel("Y:");
		pnlCenter.add(lblCenterY, "cell 0 1,alignx trailing");

		txtCenterY = new JTextField();
		pnlCenter.add(txtCenterY, "cell 1 1,growx");
		txtCenterY.setColumns(10);

		lblRadius = new JLabel("Radius:");
		pnlCenter.add(lblRadius, "cell 0 2,alignx trailing");

		txtRadius = new JTextField();
		txtRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtRadius, "cell 1 2,growx");
		txtRadius.setColumns(10);

		btnColor = new JButton("Color");
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color = JColorChooser.showDialog(null, "Border Color:", color.GREEN);
			}
		});

		lblColor = new JLabel("Color:");
		pnlCenter.add(lblColor, "cell 0 3");
		pnlCenter.add(btnColor, "cell 1 3");

		btnInnerColor = new JButton("Color");
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				innerColor = JColorChooser.showDialog(null, "Inner Color:", color.WHITE);
			}
		});

		lblInnerColor = new JLabel("Inner color:");
		pnlCenter.add(lblInnerColor, "cell 0 4");
		pnlCenter.add(btnInnerColor, "cell 1 4");

		pnlBottom = new JPanel();
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (txtRadius.getText().equals("")) {
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
