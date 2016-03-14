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
import java.sql.SQLException;

public class NewZoneForm extends JFrame {

	private static final long serialVersionUID = -8389981407557054614L;
	private JPanel contentPane;
	private JTextField textZoneName;

	
	public NewZoneForm() {
		setTitle("Nuova zona");
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
		
		JLabel lblNomeDelNuovo = new JLabel("Nome della nuova zona");
		lblNomeDelNuovo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNomeDelNuovo, "2, 2, 3, 1");
		
		textZoneName = new JTextField();
		contentPane.add(textZoneName, "2, 4, 3, 1, fill, default");
		textZoneName.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textZoneName.getText().equals("")){
					try {
						String[] path = MainForm.tree.addNodeUnderCurrent(textZoneName.getText());
						DatabaseOperation.addNewPath(path);
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
