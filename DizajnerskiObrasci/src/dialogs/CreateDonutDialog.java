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

public class CreateDonutDialog extends JDialog {
	
	private Boolean confirmed;
	private Color outerCircleBorderColor;
	private Color outerCircleFillColor;
	private Color innerCircleBorderColor;
	private Color innerCircleFillColor;
	
	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblOuterCircleRadius;
	private JLabel lblInnerCircleRadius;
	private JTextField txtOuterCircleRadius;
	private JTextField txtInnerCircleRadius;
	private JLabel lblOuterCircleBorderColor;
	private JLabel lblOuterCircleFillColor;
	private JLabel lblInnerCircleBorderColor;
	private JLabel lblInnerCircleFillColor;
	private JButton btnOuterCircleBorderColor;
	private JButton btnOuterCircleFillColor;
	private JButton btnInnerCircleBorderColor;
	private JButton btnInnerCircleFillColor;
	
	
	public Boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public Color getOuterCircleBorderColor() {
		return outerCircleBorderColor;
	}

	public void setOuterCircleBorderColor(Color outerCircleBorderColor) {
		this.outerCircleBorderColor = outerCircleBorderColor;
	}

	public Color getOuterCircleFillColor() {
		return outerCircleFillColor;
	}

	public void setOuterCircleFillColor(Color outerCircleFillColor) {
		this.outerCircleFillColor = outerCircleFillColor;
	}

	public Color getInnerCircleBorderColor() {
		return innerCircleBorderColor;
	}

	public void setInnerCircleBorderColor(Color innerCircleBorderColor) {
		this.innerCircleBorderColor = innerCircleBorderColor;
	}

	public Color getInnerCircleFillColor() {
		return innerCircleFillColor;
	}

	public void setInnerCircleFillColor(Color innerCircleFillColor) {
		this.innerCircleFillColor = innerCircleFillColor;
	}

	public JTextField getTxtOuterCircleRadius() {
		return txtOuterCircleRadius;
	}

	public void setTxtOuterCircleRadius(JTextField txtExternalCircleRadius) {
		this.txtOuterCircleRadius = txtExternalCircleRadius;
	}

	public JTextField getTxtInnerCircleRadius() {
		return txtInnerCircleRadius;
	}

	public void setTxtInnerCircleRadius(JTextField txtInnerCircleRadius) {
		this.txtInnerCircleRadius = txtInnerCircleRadius;
	}

	
	public CreateDonutDialog() {
		
		setTitle("Create donut");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		
		pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		lblOuterCircleRadius = new JLabel("Circle radius:");
		pnlCenter.add(lblOuterCircleRadius, "cell 0 0,alignx left");
		
		txtOuterCircleRadius = new JTextField();
		txtOuterCircleRadius.addKeyListener(new KeyAdapter() {
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
		pnlCenter.add(txtOuterCircleRadius, "cell 1 0,growx");
		txtOuterCircleRadius.setColumns(10);
		
		
		lblInnerCircleRadius = new JLabel("Inner circle radius:");
		pnlCenter.add(lblInnerCircleRadius, "cell 0 1,alignx left");
		
		txtInnerCircleRadius = new JTextField();
		txtInnerCircleRadius.addKeyListener(new KeyAdapter() {
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
		pnlCenter.add(txtInnerCircleRadius, "cell 1 1,growx");
		txtInnerCircleRadius.setColumns(10);
		
		
		lblOuterCircleBorderColor = new JLabel("Border color:");
		pnlCenter.add(lblOuterCircleBorderColor, "cell 0 2");
		
		btnOuterCircleBorderColor = new JButton("Color");
		btnOuterCircleBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				outerCircleBorderColor = JColorChooser.showDialog(null, "Outer circle border color:", Color.RED); 
			}
		});
		pnlCenter.add(btnOuterCircleBorderColor, "cell 1 2,alignx center");
		
		
		lblOuterCircleFillColor = new JLabel("Fulness color:");
		pnlCenter.add(lblOuterCircleFillColor, "cell 0 3");
		
		btnOuterCircleFillColor = new JButton("Color");
		btnOuterCircleFillColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				outerCircleFillColor = JColorChooser.showDialog(null, "Outer circle fulness color:", Color.WHITE); 
			}
		});
		pnlCenter.add(btnOuterCircleFillColor, "cell 1 3,alignx center");
		
		
		lblInnerCircleBorderColor = new JLabel("Inner circle border color:");
		pnlCenter.add(lblInnerCircleBorderColor, "cell 0 4");
		
		btnInnerCircleBorderColor = new JButton("Color");
		btnInnerCircleBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				innerCircleBorderColor = JColorChooser.showDialog(null, "Inner circle border color:", Color.BLACK);
				
			}
		});
		pnlCenter.add(btnInnerCircleBorderColor, "cell 1 4,alignx center");
		
		
		lblInnerCircleFillColor = new JLabel("Internal circle fulness color:");
		pnlCenter.add(lblInnerCircleFillColor, "cell 0 5");
		
		btnInnerCircleFillColor = new JButton("Color");
		btnInnerCircleFillColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				innerCircleFillColor = JColorChooser.showDialog(null, "Inner circle fulness color:", Color.YELLOW);
			}
		});
		pnlCenter.add(btnInnerCircleFillColor, "cell 1 5,alignx center");
		
		
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
