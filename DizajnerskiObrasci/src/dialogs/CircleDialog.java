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

public class CircleDialog extends JDialog {

    private Color borderColor = new Color(0, 0, 0);
    private Color innerColor = new Color(255, 255, 255);
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

    public CircleDialog() {

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
		if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
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
		borderColor = JColorChooser.showDialog(null, "SELECT CIRCLE COLOR", borderColor);
		if (borderColor != null) {
		    btnBorderColor.setBackground(borderColor);
		}
	    }
	});
	pnlCenter.add(btnBorderColor, "cell 1 1,alignx center");

	lblInnerColor = new JLabel("Inner color:");
	pnlCenter.add(lblInnerColor, "cell 0 2,alignx left");

	btnInnerColor = new JButton("Color");
	btnInnerColor.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		innerColor = JColorChooser.showDialog(null, "SELECT CIRCLE COLOR", innerColor);
		if (innerColor != null) {
		    btnInnerColor.setBackground(innerColor);
		}
	    }
	});
	pnlCenter.add(btnInnerColor, "cell 1 2,alignx center");

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
		    }
		} catch (NumberFormatException exc) {
		    JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "ERROR",
			    JOptionPane.ERROR_MESSAGE, null);
		    return;
		}
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
		confirmed = false;
		dispose();
	    }
	});
	pnlBottom.add(btnCancel);
    }

}
