package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ReportItem extends JFrame {

	private static final long serialVersionUID = 2038367451038731850L;
	private JPanel contentPane;

	public ReportItem() {
		setTitle("Report item");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
