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

public class PointDialog extends JDialog {

	private Color color = null;
	private Boolean confirmed;

	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private JLabel lblPointX;
	private JLabel lblPointY;
	private JLabel lblColor;
	private JTextField txtPointX;
	private JTextField txtPointY;
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

	public JTextField getTxtPointX() {
		return txtPointX;
	}

	public void setTxtPointX(JTextField txtPointX) {
		this.txtPointX = txtPointX;
	}

	public JTextField getTxtPointY() {
		return txtPointY;
	}

	public void setTxtPointY(JTextField txtPointY) {
		this.txtPointY = txtPointY;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}

	public PointDialog(String type) {

		setTitle(type + " point");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][]"));

		lblPointX = new JLabel("X:");
		pnlCenter.add(lblPointX, "cell 0 0,alignx left");

		txtPointX = new JTextField();
		txtPointX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtPointX, "cell 1 0,growx");
		txtPointX.setColumns(10);

		lblPointY = new JLabel("Y:");
		pnlCenter.add(lblPointY, "cell 0 1,alignx left");

		txtPointY = new JTextField();
		txtPointY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtPointY, "cell 1 1,growx");
		txtPointY.setColumns(10);

		lblColor = new JLabel("Color:");
		pnlCenter.add(lblColor, "cell 0 2");

		btnColor = new JButton("Color");
		btnColor.setBackground(Color.BLACK);
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color = JColorChooser.showDialog(null, "Outer circle border color:", Color.RED);
			}
		});
		pnlCenter.add(btnColor, "cell 1 2,alignx center");

		pnlBottom = new JPanel();
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);
		btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (txtPointX.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter radius value", "ERROR",
								JOptionPane.ERROR_MESSAGE, null);
						confirmed = false;
						return;
					} else if (txtPointY.getText().equals("")) {
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
