package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class test extends JDialog {

    private Color borderColor;
    private Color innerColor;
    private Boolean confirmed;

    private JPanel pnlCenter;
    private JPanel pnlBottom;
    private JLabel lblWidth;
    private JLabel lblHeight;
    private JLabel lblBorderColor;
    private JLabel lblInnerColor;
    private JTextField txtWidth;
    private JTextField txtHeight;
    private JButton btnBorderColor;
    private JButton btnInnerColor;
    private JButton btnConfirm;
    private JButton btnCancel;

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

    public test() {

	pnlCenter = new JPanel();
	getContentPane().add(pnlCenter, BorderLayout.CENTER);
	pnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

	lblWidth = new JLabel("Width:");
	pnlCenter.add(lblWidth, "cell 0 0,alignx trailing");

	txtWidth = new JTextField();
	txtWidth.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
	    }
	});
	pnlCenter.add(txtWidth, "cell 1 0,growx");
	txtWidth.setColumns(10);

	lblHeight = new JLabel("Height:");
	pnlCenter.add(lblHeight, "cell 0 1,alignx trailing");

	txtHeight = new JTextField();
	txtHeight.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
	    }
	});
	pnlCenter.add(txtHeight, "cell 1 1,growx");
	txtHeight.setColumns(10);

	lblBorderColor = new JLabel("Border color");
	pnlCenter.add(lblBorderColor, "cell 0 2");

	btnBorderColor = new JButton("Color");
	btnBorderColor.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    }
	});
	pnlCenter.add(btnBorderColor, "cell 1 2,alignx center");

	lblInnerColor = new JLabel("Inner color");
	pnlCenter.add(lblInnerColor, "cell 0 3");

	btnInnerColor = new JButton("Color");
	btnInnerColor.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    }
	});
	pnlCenter.add(btnInnerColor, "cell 1 3,alignx center");

	pnlBottom = new JPanel();
	getContentPane().add(pnlBottom, BorderLayout.SOUTH);

	btnConfirm = new JButton("Confirm");
	btnConfirm.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
//				confirmed = true;
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
