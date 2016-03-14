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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import source.DatabaseOperation;
import source.DatabaseSetup;

public class NewDatabaseForm extends JFrame {

	private static final long serialVersionUID = -4857628918351377230L;
	private JPanel contentPane;
	private JTextField textDatabaseName;

	
	public NewDatabaseForm() {
		setTitle("Nuovo database");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 219, 119);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(61dlu;pref)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(61dlu;pref)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNomeDelNuovo = new JLabel("Nome del nuovo database");
		lblNomeDelNuovo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNomeDelNuovo, "2, 2, 3, 1");
		
		textDatabaseName = new JTextField();
		contentPane.add(textDatabaseName, "2, 4, 3, 1, fill, default");
		textDatabaseName.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textDatabaseName.getText().equals("")){
					try {
						DatabaseOperation.createNewDatabase(textDatabaseName.getText());
						DatabaseSetup.radix = textDatabaseName.getText();
						DatabaseSetup.writeConfiguration();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}
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
