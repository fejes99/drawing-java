package dialogs;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

public class CreatePointLineDialog extends JDialog {
	
	private Color color;
	private Boolean confirmed;
	
	private JPanel pnlBottom;
	private JLabel lblColor;
	private JButton btnColor;
	private JButton btnConfirm;
	
	
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


	public CreatePointLineDialog(String type) {
		
		setTitle("Create " + type);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		
		lblColor = new JLabel("Color: ");
		getContentPane().add(lblColor, BorderLayout.WEST);
		
		btnColor = new JButton("Color");
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color = JColorChooser.showDialog(null, "Choose color:", Color.RED);
			}
		});
		getContentPane().add(btnColor, BorderLayout.CENTER);
		
		
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
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		pnlBottom.add(btnCancel);
				
	}
}
