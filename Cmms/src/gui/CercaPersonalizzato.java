package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import source.DatabaseOperation;
import source.DatabaseSetup;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CercaPersonalizzato extends JFrame {

	private static final long serialVersionUID = 5133845108612867156L;
	private JPanel contentPane;
	private JTextField textMarca;
	private JTextField textTipoStrumento;
	private JTextField textModello;
	private JTextField textCodiceMagazzino;
	private JTextField textDataMontaggio;
	private JTextField textDataSostituzione;
	private JTextField textMotivoSostituzione;
	private JTextField textNumeroDiSerie;
	private JTextField textAddetto;
	private JCheckBox chckbxNumeroDiSerie;
	private JComboBox<String> comboBoxModello;
	private JCheckBox chckbxModello;
	private JCheckBox chckbxCodiceMagazzino;
	private JComboBox<String> comboBoxMotivoSostituzione;
	private JCheckBox chckbxMotivoSostituzione;
	private JCheckBox chckbxTipoStrumento;
	private JCheckBox chckbxMarca;
	private JComboBox<String> comboBoxDataSostituzione;
	private JCheckBox chckbxAddetto;
	private JCheckBox chckbxDataSostituzione;
	private JComboBox<String> comboBoxDataMontaggio;
	private JComboBox<String> comboBoxTipoStrumento;
	private JComboBox<String> comboBoxAddetto;
	private JComboBox<String> comboBoxMarca;
	private JComboBox<String> comboBoxCodiceMagazzino;
	private JComboBox<String> comboBoxNumeroDiSerie;
	private JCheckBox chckbxDataMontaggio;

	public CercaPersonalizzato() {
		setMinimumSize(new Dimension(580, 340));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setTitle("Ricerca personalizzata");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(145dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		chckbxMarca = new JCheckBox("Marca");
		chckbxMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxMarca.isSelected()){
					textMarca.setEnabled(true);
					comboBoxMarca.setEnabled(true);
				}
				else{
					textMarca.setEnabled(false);
					comboBoxMarca.setEnabled(false);
				}		
			}
		});
		contentPane.add(chckbxMarca, "2, 2");
		
		textMarca = new JTextField();
		textMarca.setEnabled(false);
		contentPane.add(textMarca, "4, 2, fill, default");
		textMarca.setColumns(10);
		
		comboBoxMarca = new JComboBox<String>();
		comboBoxMarca.setEnabled(false);
		contentPane.add(comboBoxMarca, "6, 2, fill, default");
		comboBoxMarca.addItem("Contiene");
		comboBoxMarca.addItem("Uguale");
		
		chckbxTipoStrumento = new JCheckBox("Tipo strumento");
		chckbxTipoStrumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxTipoStrumento.isSelected()){
					textTipoStrumento.setEnabled(true);
					comboBoxTipoStrumento.setEnabled(true);
				}
				else{
					textTipoStrumento.setEnabled(false);
					comboBoxTipoStrumento.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxTipoStrumento, "2, 4");
		
		textTipoStrumento = new JTextField();
		textTipoStrumento.setEnabled(false);
		contentPane.add(textTipoStrumento, "4, 4, fill, default");
		textTipoStrumento.setColumns(10);
		
		comboBoxTipoStrumento = new JComboBox<String>();
		comboBoxTipoStrumento.setEnabled(false);
		contentPane.add(comboBoxTipoStrumento, "6, 4, fill, default");
		comboBoxTipoStrumento.addItem("Contiene");
		comboBoxTipoStrumento.addItem("Uguale");
		
		chckbxModello = new JCheckBox("Modello");
		chckbxModello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxModello.isSelected()){
					textModello.setEnabled(true);
					comboBoxModello.setEnabled(true);
				}
				else{
					textModello.setEnabled(false);
					comboBoxModello.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxModello, "2, 6");
		
		textModello = new JTextField();
		textModello.setEnabled(false);
		contentPane.add(textModello, "4, 6, fill, default");
		textModello.setColumns(10);
		
		comboBoxModello = new JComboBox<String>();
		comboBoxModello.setEnabled(false);
		contentPane.add(comboBoxModello, "6, 6, fill, default");
		comboBoxModello.addItem("Contiene");
		comboBoxModello.addItem("Uguale");
		
		chckbxCodiceMagazzino = new JCheckBox("Codice magazzino");
		chckbxCodiceMagazzino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCodiceMagazzino.isSelected()){
					textCodiceMagazzino.setEnabled(true);
					comboBoxCodiceMagazzino.setEnabled(true);
				}
				else{
					textCodiceMagazzino.setEnabled(false);
					comboBoxCodiceMagazzino.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxCodiceMagazzino, "2, 8");
		
		textCodiceMagazzino = new JTextField();
		textCodiceMagazzino.setEnabled(false);
		contentPane.add(textCodiceMagazzino, "4, 8, fill, default");
		textCodiceMagazzino.setColumns(10);
		
		comboBoxCodiceMagazzino = new JComboBox<String>();
		comboBoxCodiceMagazzino.setEnabled(false);
		contentPane.add(comboBoxCodiceMagazzino, "6, 8, fill, default");
		comboBoxCodiceMagazzino.addItem("Contiene");
		comboBoxCodiceMagazzino.addItem("Uguale");
		
		chckbxDataMontaggio = new JCheckBox("Data montaggio");
		chckbxDataMontaggio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxDataMontaggio.isSelected()){
					textDataMontaggio.setEnabled(true);
					comboBoxDataMontaggio.setEnabled(true);
				}
				else{
					textDataMontaggio.setEnabled(false);
					comboBoxDataMontaggio.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxDataMontaggio, "2, 10");
		
		textDataMontaggio = new JTextField();
		textDataMontaggio.setEnabled(false);
		contentPane.add(textDataMontaggio, "4, 10, fill, default");
		textDataMontaggio.setColumns(10);
		
		comboBoxDataMontaggio = new JComboBox<String>();
		comboBoxDataMontaggio.setEnabled(false);
		contentPane.add(comboBoxDataMontaggio, "6, 10, fill, default");
		comboBoxDataMontaggio.addItem("Contiene");
		comboBoxDataMontaggio.addItem("Uguale");
		
		chckbxDataSostituzione = new JCheckBox("Data sostituzione");
		chckbxDataSostituzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxDataSostituzione.isSelected()){
					textDataSostituzione.setEnabled(true);
					comboBoxDataSostituzione.setEnabled(true);
				}
				else{
					textDataSostituzione.setEnabled(false);
					comboBoxDataSostituzione.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxDataSostituzione, "2, 12");
		
		textDataSostituzione = new JTextField();
		textDataSostituzione.setEnabled(false);
		contentPane.add(textDataSostituzione, "4, 12, fill, default");
		textDataSostituzione.setColumns(10);
		
		comboBoxDataSostituzione = new JComboBox<String>();
		comboBoxDataSostituzione.setEnabled(false);
		contentPane.add(comboBoxDataSostituzione, "6, 12, fill, default");
		comboBoxDataSostituzione.addItem("Contiene");
		comboBoxDataSostituzione.addItem("Uguale");
		
		chckbxMotivoSostituzione = new JCheckBox("Motivo sostituzione");
		chckbxMotivoSostituzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxMotivoSostituzione.isSelected()){
					textMotivoSostituzione.setEnabled(true);
					comboBoxMotivoSostituzione.setEnabled(true);
				}
				else{
					textMotivoSostituzione.setEnabled(false);
					comboBoxMotivoSostituzione.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxMotivoSostituzione, "2, 14");
		
		textMotivoSostituzione = new JTextField();
		textMotivoSostituzione.setEnabled(false);
		contentPane.add(textMotivoSostituzione, "4, 14, fill, default");
		textMotivoSostituzione.setColumns(10);
		
		comboBoxMotivoSostituzione = new JComboBox<String>();
		comboBoxMotivoSostituzione.setEnabled(false);
		contentPane.add(comboBoxMotivoSostituzione, "6, 14, fill, default");
		comboBoxMotivoSostituzione.addItem("Contiene");
		comboBoxMotivoSostituzione.addItem("Uguale");
		
		chckbxNumeroDiSerie = new JCheckBox("Numero di serie");
		chckbxNumeroDiSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNumeroDiSerie.isSelected()){
					textNumeroDiSerie.setEnabled(true);
					comboBoxNumeroDiSerie.setEnabled(true);
				}
				else{
					textNumeroDiSerie.setEnabled(false);
					comboBoxNumeroDiSerie.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxNumeroDiSerie, "2, 16");
		
		textNumeroDiSerie = new JTextField();
		textNumeroDiSerie.setEnabled(false);
		contentPane.add(textNumeroDiSerie, "4, 16, fill, default");
		textNumeroDiSerie.setColumns(10);
		
		comboBoxNumeroDiSerie = new JComboBox<String>();
		comboBoxNumeroDiSerie.setEnabled(false);
		contentPane.add(comboBoxNumeroDiSerie, "6, 16, fill, default");
		comboBoxNumeroDiSerie.addItem("Contiene");
		comboBoxNumeroDiSerie.addItem("Uguale");
		
		chckbxAddetto = new JCheckBox("Addetto");
		chckbxAddetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAddetto.isSelected()){
					textAddetto.setEnabled(true);
					comboBoxAddetto.setEnabled(true);
				}
				else{
					textAddetto.setEnabled(false);
					comboBoxAddetto.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxAddetto, "2, 18");
		
		textAddetto = new JTextField();
		textAddetto.setEnabled(false);
		contentPane.add(textAddetto, "4, 18, fill, default");
		textAddetto.setColumns(10);
		
		comboBoxAddetto = new JComboBox<String>();
		comboBoxAddetto.setEnabled(false);
		contentPane.add(comboBoxAddetto, "6, 18, fill, default");
		comboBoxAddetto.addItem("Contiene");
		comboBoxAddetto.addItem("Uguale");
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm.tableItem.clearTable();
				try {
					ResultSet datas = getDatas();
					if(datas!=null){
						while(datas.next())
							MainForm.tableItem.addRow(DatabaseOperation.parseColumnDataForTable(datas));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainForm.cercaPersonalizzato.setVisible(false);
			}
		});
		contentPane.add(btnCerca, "2, 20");
	}
	
	private ResultSet getDatas() throws SQLException{
		String selection = "";
		if(chckbxMarca.isSelected()){
			if (comboBoxMarca.getSelectedItem().equals("Contiene"))
				selection = selection.concat("marca like '%"+textMarca.getText()+"%' AND ");
			if (comboBoxMarca.getSelectedItem().equals("Uguale"))
				selection = selection.concat("marca='"+textMarca.getText()+"' AND ");
		}
		if(chckbxTipoStrumento.isSelected()){
			if (comboBoxTipoStrumento.getSelectedItem().equals("Contiene"))
				selection = selection.concat("tipo_strumento like '%"+textTipoStrumento.getText()+"%' AND ");
			if (comboBoxTipoStrumento.getSelectedItem().equals("Uguale"))
				selection = selection.concat("tipo_strumento='"+textTipoStrumento.getText()+"' AND ");
		}
		if(chckbxModello.isSelected()){
			if (comboBoxModello.getSelectedItem().equals("Contiene"))
				selection = selection.concat("modello like '%"+textModello.getText()+"%' AND ");
			if (comboBoxModello.getSelectedItem().equals("Uguale"))
				selection = selection.concat("modello='"+textModello.getText()+"' AND ");
		}
		if(chckbxCodiceMagazzino.isSelected()){
			if (comboBoxCodiceMagazzino.getSelectedItem().equals("Contiene"))
				selection = selection.concat("codice_magazzino like '%"+textCodiceMagazzino.getText()+"%' AND ");
			if (comboBoxCodiceMagazzino.getSelectedItem().equals("Uguale"))
				selection = selection.concat("codice_magazzino='"+textCodiceMagazzino.getText()+"' AND ");
		}
		if(chckbxDataMontaggio.isSelected()){
			if (comboBoxDataMontaggio.getSelectedItem().equals("Contiene"))
				selection = selection.concat("data_montaggio like '%"+textDataMontaggio.getText()+"%' AND ");
			if (comboBoxDataMontaggio.getSelectedItem().equals("Uguale"))
				selection = selection.concat("data_montaggio='"+textDataMontaggio.getText()+"' AND ");
		}
		if(chckbxDataSostituzione.isSelected()){
			if (comboBoxDataSostituzione.getSelectedItem().equals("Contiene"))
				selection = selection.concat("data_sostituzione like '%"+textDataSostituzione.getText()+"%' AND ");
			if (comboBoxDataSostituzione.getSelectedItem().equals("Uguale"))
				selection = selection.concat("data_sostituzione='"+textDataSostituzione.getText()+"' AND ");
		}
		if(chckbxMotivoSostituzione.isSelected()){
			if (comboBoxMotivoSostituzione.getSelectedItem().equals("Contiene"))
				selection = selection.concat("motivo_sostituzione like '%"+textMotivoSostituzione.getText()+"%' AND ");
			if (comboBoxMotivoSostituzione.getSelectedItem().equals("Uguale"))
				selection = selection.concat("motivo_sostituzione='"+textMotivoSostituzione.getText()+"' AND ");
		}
		if(chckbxNumeroDiSerie.isSelected()){
			if (comboBoxNumeroDiSerie.getSelectedItem().equals("Contiene"))
				selection = selection.concat("numero_serie like '%"+textNumeroDiSerie.getText()+"%' AND ");
			if (comboBoxNumeroDiSerie.getSelectedItem().equals("Uguale"))
				selection = selection.concat("numero_serie='"+textNumeroDiSerie.getText()+"' AND ");
		}
		if(chckbxAddetto.isSelected()){
			if (comboBoxAddetto.getSelectedItem().equals("Contiene"))
				selection = selection.concat("addetto like '%"+textAddetto.getText()+"%' AND ");
			if (comboBoxAddetto.getSelectedItem().equals("Uguale"))
				selection = selection.concat("addetto='"+textAddetto.getText()+"' AND ");
		}
		if(!selection.equals("")){
			selection = selection.substring(0, selection.length()-5);	
			return DatabaseOperation.query("SELECT * FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE "+selection);
		}
		else{
			return null;
			
		}
	}
}
