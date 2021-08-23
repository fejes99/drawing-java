package dialogs;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateCircleDialog extends JDialog {
	
	private Color borderColor;
	private Color innerColor;
	private Boolean confirmed;
	
	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblRadius;
	private JTextField txtRadius;
	private JLabel lblBorderColor;
	private JButton btnBorderColor;
	private JLabel lblInnerColor;
	private JButton btnInnerColor;
	
	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
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

	public CreateCircleDialog() {
		
		setTitle("Create circle");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		
		pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		lblRadius = new JLabel("Radius:");
		pnlCenter.add(lblRadius, "cell 0 0,alignx left");
		
		txtRadius = new JTextField();
		txtRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		pnlCenter.add(txtRadius, "cell 1 0,growx");
		txtRadius.setColumns(10);
		
		lblBorderColor = new JLabel("Border color:");
		pnlCenter.add(lblBorderColor, "cell 0 1,alignx left");
		
		btnBorderColor = new JButton("Color");
		btnBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				borderColor = JColorChooser.showDialog(null, "Border Color:", Color.RED);
			}
		});
		pnlCenter.add(btnBorderColor, "cell 1 1,alignx center");
		
		lblInnerColor = new JLabel("Inner color:");
		pnlCenter.add(lblInnerColor, "cell 0 2,alignx left");
		
		btnInnerColor = new JButton("Color");
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				innerColor = JColorChooser.showDialog(null, "Border Color:", Color.RED);
			}
		});
		pnlCenter.add(btnInnerColor, "cell 1 2,alignx center");
		
		
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
