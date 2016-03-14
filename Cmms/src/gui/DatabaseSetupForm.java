package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import source.DatabaseSetup;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatabaseSetupForm extends JFrame {


	private static final long serialVersionUID = -4652986039046333769L;
	private JPanel contentPane;
	private JTextField txtHostname;
	private JTextField txtPorta;
	private JTextField txtDatabasename;
	private JTextField txtReparto;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;

	/**
	 * Create the frame.
	 */
	public DatabaseSetupForm() {
		setResizable(false);
		setTitle("Impostazioni database");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 259, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		txtHostname = new JTextField();
		contentPane.add(txtHostname, "2, 2, 3, 1, fill, default");
		txtHostname.setColumns(10);
		txtHostname.setText(DatabaseSetup.hostName);
		
		JLabel lblHostname = new JLabel("Hostname");
		contentPane.add(lblHostname, "6, 2");
		
		txtPorta = new JTextField();
		contentPane.add(txtPorta, "2, 4, 3, 1, fill, default");
		txtPorta.setColumns(10);
		txtPorta.setText(DatabaseSetup.port.toString());
		
		
		JLabel lblPorta = new JLabel("Porta");
		contentPane.add(lblPorta, "6, 4");
		
		txtDatabasename = new JTextField();
		contentPane.add(txtDatabasename, "2, 6, 3, 1, fill, default");
		txtDatabasename.setColumns(10);
		txtDatabasename.setText(DatabaseSetup.dataBaseName);
		
		JLabel lblNomeDatabase = new JLabel("Nome database");
		contentPane.add(lblNomeDatabase, "6, 6");
		
		txtReparto = new JTextField();
		contentPane.add(txtReparto, "2, 8, 3, 1, fill, default");
		txtReparto.setColumns(10);
		txtReparto.setText(DatabaseSetup.radix);
		
		JLabel lblReparto = new JLabel("Reparto");
		contentPane.add(lblReparto, "6, 8");
		
		txtUsername = new JTextField();
		contentPane.add(txtUsername, "2, 10, 3, 1, fill, default");
		txtUsername.setColumns(10);
		txtUsername.setText(DatabaseSetup.userName);
		
		JLabel lblUsername = new JLabel("Username");
		contentPane.add(lblUsername, "6, 10");
		
		pwdPassword = new JPasswordField();
		contentPane.add(pwdPassword, "2, 12, 3, 1, fill, default");
		pwdPassword.setText(DatabaseSetup.userPassword);
		
		JLabel lblPassword = new JLabel("Password");
		contentPane.add(lblPassword, "6, 12");
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseSetup.hostName = txtHostname.getText();
				DatabaseSetup.port = Integer.valueOf(txtPorta.getText());
				DatabaseSetup.dataBaseName = txtDatabasename.getText();
				DatabaseSetup.radix = txtReparto.getText();
				DatabaseSetup.userName = txtUsername.getText();
				DatabaseSetup.userPassword = String.valueOf(pwdPassword.getPassword());
				DatabaseSetup.writeConfiguration();
				dispose();
			}
		});
		contentPane.add(btnOk, "2, 14");
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnAnnulla, "4, 14");
	}

}
