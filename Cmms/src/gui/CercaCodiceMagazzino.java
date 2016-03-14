package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import source.DatabaseOperation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CercaCodiceMagazzino extends JFrame {

	private static final long serialVersionUID = -8389981407557054614L;
	private JPanel contentPane;
	private JTextField textCodiceMagazzino;
	
	//test

	
	public CercaCodiceMagazzino() {
		setTitle("Cerca");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 185, 119);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblCodiceMagazzino = new JLabel("Codice Magazzino");
		lblCodiceMagazzino.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCodiceMagazzino, "2, 2, 3, 1");
		
		textCodiceMagazzino = new JTextField();
		contentPane.add(textCodiceMagazzino, "2, 4, 3, 1, fill, default");
		textCodiceMagazzino.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainForm.tableItem.clearTable();
				try {
					ResultSet result = DatabaseOperation.cercaCodiceMagazzino(textCodiceMagazzino.getText());
					while(result.next())
						MainForm.tableItem.addRow(DatabaseOperation.parseColumnDataForTable(result));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		contentPane.add(btnOk, "2, 6, fill, center");
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnAnnulla, "4, 6");
	}
}
