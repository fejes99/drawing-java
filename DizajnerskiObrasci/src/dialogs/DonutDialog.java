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
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class DonutDialog extends JDialog {

	private Boolean confirmed;
	private Color color = new Color(0, 0, 0);
	private Color innerColor = new Color(255, 255, 255);

	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblRadius;
	private JLabel lblInnerRadius;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private JLabel lblColor;
	private JLabel lblInnerColor;
	private JButton btnColor;
	private JButton btnInnerColor;

	public Boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

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

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtExternalCircleRadius) {
		this.txtRadius = txtExternalCircleRadius;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerCircleRadius) {
		this.txtInnerRadius = txtInnerCircleRadius;
	}

	public DonutDialog() {

		setTitle("Create donut");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));

		lblRadius = new JLabel("Radius:");
		pnlCenter.add(lblRadius, "cell 0 0,alignx left");

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
		pnlCenter.add(txtRadius, "cell 1 0,growx");
		txtRadius.setColumns(10);

		lblInnerRadius = new JLabel("Inner radius:");
		pnlCenter.add(lblInnerRadius, "cell 0 1,alignx left");

		txtInnerRadius = new JTextField();
		txtInnerRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtInnerRadius, "cell 1 1,growx");
		txtInnerRadius.setColumns(10);

		lblColor = new JLabel("Color:");
		pnlCenter.add(lblColor, "cell 0 2");

		btnColor = new JButton("Color");
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color = JColorChooser.showDialog(null, "Outer circle border color:", Color.RED);
			}
		});
		pnlCenter.add(btnColor, "cell 1 2,alignx center");

		lblInnerColor = new JLabel("Inner color:");
		pnlCenter.add(lblInnerColor, "cell 0 3");

		btnInnerColor = new JButton("Color");
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				innerColor = JColorChooser.showDialog(null, "Outer circle fulness color:", Color.WHITE);
			}
		});
		pnlCenter.add(btnInnerColor, "cell 1 3,alignx center");

		pnlBottom = new JPanel();
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmed = true;
				dispose();
			}
		});
		pnlBottom.add(btnConfirm);
		getRootPane().setDefaultButton(btnConfirm);

//		null pointer exception
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		pnlBottom.add(btnCancel);
	}

}
