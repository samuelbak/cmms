package gui;

import javafx.beans.value.ChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.UIManager;

import source.DatabaseOperation;
import source.DateLabelFormatter;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
//import java.sql.Date;
import java.util.Date;
import java.util.Calendar;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JLayeredPane;

import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("unused")
public class ItemForm extends JFrame {

	private static final long serialVersionUID = -9209084412885578804L;
	private JLayeredPane contentPane;
	private JTextField txtMarca;
	private JTextField txtTipoStrumento;
	private JTextField txtModello;
	private JTextField txtCodiceMagazzino;
	private JTextField txtDataDiMontaggio;
	private JTextField txtDataDiSostituzione;
	private JXDatePicker datePickerDataDiMontaggio;
	private JXDatePicker datePickerDataDiSostituzione;
	private JTextField txtNumeroDiSerie;
	private JTextField txtAddetto;
	private JTextArea textMotivoDellaSostituzione;
	private final Integer informazioni = 0;
	private final Integer interventi = 1;
	private Point posizione; 
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public ItemForm(String[] path) {	//nuovo Item
		JPanel contentPane;
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setResizable(false);
		setTitle("Aggiungi item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,},
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		txtMarca = new JTextField();
		contentPane.add(txtMarca, "2, 2, 3, 1, fill, default");
		txtMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		contentPane.add(lblMarca, "6, 2");
		
		txtTipoStrumento = new JTextField();
		contentPane.add(txtTipoStrumento, "2, 4, 3, 1, fill, default");
		txtTipoStrumento.setColumns(10);
		
		JLabel lblTipoStrumento = new JLabel("Tipo strumento");
		contentPane.add(lblTipoStrumento, "6, 4");
		
		txtModello = new JTextField();
		contentPane.add(txtModello, "2, 6, 3, 1, fill, default");
		txtModello.setColumns(10);
		
		JLabel lblModello = new JLabel("Modello");
		contentPane.add(lblModello, "6, 6");
		
		txtCodiceMagazzino = new JTextField();
		contentPane.add(txtCodiceMagazzino, "2, 8, 3, 1, fill, default");
		txtCodiceMagazzino.setColumns(10);
		
		JLabel lblCodiceMagazzino = new JLabel("Codice magazzino");
		contentPane.add(lblCodiceMagazzino, "6, 8");
		
		datePickerDataDiMontaggio = new JXDatePicker();
		datePickerDataDiMontaggio.setFormats(new String[]{"yyyy-MM-dd"});
		contentPane.add(datePickerDataDiMontaggio, "2, 10, 3, 1, fill, default");
		datePickerDataDiMontaggio.setDate(setDateToPicker("2000-01-01"));
		
		JLabel lblDataDiMontaggio = new JLabel("Data di montaggio");
		contentPane.add(lblDataDiMontaggio, "6, 10");
		
		datePickerDataDiSostituzione = new JXDatePicker();
		datePickerDataDiSostituzione.setFormats(new String[]{"yyyy-MM-dd"});
		contentPane.add(datePickerDataDiSostituzione, "2, 12, 3, 1, fill, default");
		
		JLabel lblDataDiSostituzione = new JLabel("Data di sostituzione");
		contentPane.add(lblDataDiSostituzione, "6, 12");
		
		textMotivoDellaSostituzione = new JTextArea();
		textMotivoDellaSostituzione.setWrapStyleWord(true);
		textMotivoDellaSostituzione.setLineWrap(true);
		textMotivoDellaSostituzione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textMotivoDellaSostituzione.setBorder(UIManager.getBorder("TextField.border"));
		contentPane.add(textMotivoDellaSostituzione, "2, 14, 3, 1, fill, fill");
		
		JLabel lblMotivoDellaSostituzione = new JLabel("Motivo della sostituzione");
		contentPane.add(lblMotivoDellaSostituzione, "6, 14");
		
		txtNumeroDiSerie = new JTextField();
		contentPane.add(txtNumeroDiSerie, "2, 16, 3, 1, fill, default");
		txtNumeroDiSerie.setColumns(10);
		
		JLabel lblNumeroDiSerie = new JLabel("Numero di serie");
		contentPane.add(lblNumeroDiSerie, "6, 16");
		
		txtAddetto = new JTextField();
		contentPane.add(txtAddetto, "2, 18, 3, 1, fill, default");
		txtAddetto.setColumns(10);
		
		JLabel lblAddetto = new JLabel("Addetto");
		contentPane.add(lblAddetto, "6, 18");
		
		JButton btnAggiungiModifica = new JButton("Aggiungi");
		btnAggiungiModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseOperation.writeNewItemToDbWithoutDate(path, getStringArray());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainForm.tableRefresh();
				dispose();
			}
		});
		contentPane.add(btnAggiungiModifica, "2, 20");

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnAnnulla, "6, 20");
	}

	public ItemForm(String[] path, String[] values) {	//duplica Item
		JPanel contentPane;
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setResizable(false);
		setTitle("Duplica item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,},
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		txtMarca = new JTextField();
		contentPane.add(txtMarca, "2, 2, 3, 1, fill, default");
		txtMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		contentPane.add(lblMarca, "6, 2");
		
		txtTipoStrumento = new JTextField();
		contentPane.add(txtTipoStrumento, "2, 4, 3, 1, fill, default");
		txtTipoStrumento.setColumns(10);
		
		JLabel lblTipoStrumento = new JLabel("Tipo strumento");
		contentPane.add(lblTipoStrumento, "6, 4");
		
		txtModello = new JTextField();
		contentPane.add(txtModello, "2, 6, 3, 1, fill, default");
		txtModello.setColumns(10);
		
		JLabel lblModello = new JLabel("Modello");
		contentPane.add(lblModello, "6, 6");
		
		txtCodiceMagazzino = new JTextField();
		contentPane.add(txtCodiceMagazzino, "2, 8, 3, 1, fill, default");
		txtCodiceMagazzino.setColumns(10);
		
		JLabel lblCodiceMagazzino = new JLabel("Codice magazzino");
		contentPane.add(lblCodiceMagazzino, "6, 8");

		datePickerDataDiMontaggio = new JXDatePicker();
		datePickerDataDiMontaggio.setFormats(new String[]{"yyyy-MM-dd"});
		contentPane.add(datePickerDataDiMontaggio, "2, 10, 3, 1, fill, default");
		
		JLabel lblDataDiMontaggio = new JLabel("Data di montaggio");
		contentPane.add(lblDataDiMontaggio, "6, 10");

		datePickerDataDiSostituzione = new JXDatePicker();
		datePickerDataDiSostituzione.setFormats(new String[]{"yyyy-MM-dd"});
		contentPane.add(datePickerDataDiSostituzione, "2, 12, 3, 1, fill, default");
		
		JLabel lblDataDiSostituzione = new JLabel("Data di sostituzione");
		contentPane.add(lblDataDiSostituzione, "6, 12");
		
		textMotivoDellaSostituzione = new JTextArea();
		textMotivoDellaSostituzione.setWrapStyleWord(true);
		textMotivoDellaSostituzione.setLineWrap(true);
		textMotivoDellaSostituzione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textMotivoDellaSostituzione.setBorder(UIManager.getBorder("TextField.border"));
		contentPane.add(textMotivoDellaSostituzione, "2, 14, 3, 1, fill, fill");
		
		JLabel lblMotivoDellaSostituzione = new JLabel("Motivo della sostituzione");
		contentPane.add(lblMotivoDellaSostituzione, "6, 14");
		
		txtNumeroDiSerie = new JTextField();
		contentPane.add(txtNumeroDiSerie, "2, 16, 3, 1, fill, default");
		txtNumeroDiSerie.setColumns(10);
		
		JLabel lblNumeroDiSerie = new JLabel("Numero di serie");
		contentPane.add(lblNumeroDiSerie, "6, 16");
		
		txtAddetto = new JTextField();
		contentPane.add(txtAddetto, "2, 18, 3, 1, fill, default");
		txtAddetto.setColumns(10);
		
		JLabel lblAddetto = new JLabel("Addetto");
		contentPane.add(lblAddetto, "6, 18");
		
		JButton btnAggiungiModifica = new JButton("Aggiungi");
		btnAggiungiModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseOperation.writeNewItemToDbWithoutDate(path, getStringArray());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainForm.tableRefresh();
				dispose();
			}
		});
		contentPane.add(btnAggiungiModifica, "2, 20");

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnAnnulla, "6, 20");
		
		setTextForDuplicate(values);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	
	public ItemForm(String[] values, String id) {	//modifica Item
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setResizable(false);
		setTitle("Modifica item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 586);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,},
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		txtMarca = new JTextField();
		contentPane.add(txtMarca, "2, 2, 3, 1, fill, default");
		txtMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		contentPane.add(lblMarca, "6, 2");
		
		txtTipoStrumento = new JTextField();
		contentPane.add(txtTipoStrumento, "2, 4, 3, 1, fill, default");
		txtTipoStrumento.setColumns(10);
		
		JLabel lblTipoStrumento = new JLabel("Tipo strumento");
		contentPane.add(lblTipoStrumento, "6, 4");
		
		txtModello = new JTextField();
		contentPane.add(txtModello, "2, 6, 3, 1, fill, default");
		txtModello.setColumns(10);
		
		JLabel lblModello = new JLabel("Modello");
		contentPane.add(lblModello, "6, 6");
		
		txtCodiceMagazzino = new JTextField();
		contentPane.add(txtCodiceMagazzino, "2, 8, 3, 1, fill, default");
		txtCodiceMagazzino.setColumns(10);
		
		JLabel lblCodiceMagazzino = new JLabel("Codice magazzino");
		contentPane.add(lblCodiceMagazzino, "6, 8");

		datePickerDataDiMontaggio = new JXDatePicker();
		datePickerDataDiMontaggio.setFormats(new String[]{"yyyy-MM-dd"});
		contentPane.add(datePickerDataDiMontaggio, "2, 10, 3, 1, fill, default");
		
		JLabel lblDataDiMontaggio = new JLabel("Data di montaggio");
		contentPane.add(lblDataDiMontaggio, "6, 10");

		datePickerDataDiSostituzione = new JXDatePicker();
		datePickerDataDiSostituzione.setFormats(new String[]{"yyyy-MM-dd"});
		contentPane.add(datePickerDataDiSostituzione, "2, 12, 3, 1, fill, default");
		
		JLabel lblDataDiSostituzione = new JLabel("Data di sostituzione");
		contentPane.add(lblDataDiSostituzione, "6, 12");
		
		textMotivoDellaSostituzione = new JTextArea();
		textMotivoDellaSostituzione.setWrapStyleWord(true);
		textMotivoDellaSostituzione.setLineWrap(true);
		textMotivoDellaSostituzione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textMotivoDellaSostituzione.setBorder(UIManager.getBorder("TextField.border"));
		contentPane.add(textMotivoDellaSostituzione, "2, 14, 3, 1, fill, fill");
		
		JLabel lblMotivoDellaSostituzione = new JLabel("Motivo della sostituzione");
		contentPane.add(lblMotivoDellaSostituzione, "6, 14");
		
		txtNumeroDiSerie = new JTextField();
		contentPane.add(txtNumeroDiSerie, "2, 16, 3, 1, fill, default");
		txtNumeroDiSerie.setColumns(10);
		
		JLabel lblNumeroDiSerie = new JLabel("Numero di serie");
		contentPane.add(lblNumeroDiSerie, "6, 16");
		
		txtAddetto = new JTextField();
		contentPane.add(txtAddetto, "2, 18, 3, 1, fill, default");
		txtAddetto.setColumns(10);
		
		JLabel lblAddetto = new JLabel("Addetto");
		contentPane.add(lblAddetto, "6, 18");
		
		JButton btnAggiungiModifica = new JButton("Modifica");
		btnAggiungiModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseOperation.updateItemWithId(getStringArray(), id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainForm.tableRefresh();
				dispose();
				MainForm.tableRefresh();
			}
		});
		contentPane.add(btnAggiungiModifica, "2, 20");
		
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainForm.itemForm.setAlwaysOnTop(false);
				try {
					if (JOptionPane.showConfirmDialog(null, "Confermi eliminazione?") == 0)
						DatabaseOperation.deleteItemFromDbWithId(id);
					MainForm.tableRefresh();	
					dispose();
				} catch (SQLException e) {
					MainForm.showMessage("Impossibile eliminare l'item", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				setAlwaysOnTop(true);
				MainForm.tableRefresh();
			}
		});
		contentPane.add(btnElimina, "4, 20");
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnAnnulla, "6, 20");
		
		if(values.length==9){
			txtMarca.setText(values[0]);
			txtTipoStrumento.setText(values[1]);
			txtModello.setText(values[2]);
			txtCodiceMagazzino.setText(values[3]);
			if(values[4]!=null)
				datePickerDataDiMontaggio.setDate(setDateToPicker(values[4]));
			if(values[5]!=null)
				datePickerDataDiSostituzione.setDate(setDateToPicker(values[5]));
			textMotivoDellaSostituzione.setText(values[6]);
			txtNumeroDiSerie.setText(values[7]);
			txtAddetto.setText(values[8]);
		}
	}
	
	
	private String[] getStringArray(){
		String[] array = 	{txtMarca.getText(), txtTipoStrumento.getText(), txtModello.getText(),
							getDateFromPicker(datePickerDataDiMontaggio.getDate()), getDateFromPicker(datePickerDataDiSostituzione.getDate()),
							textMotivoDellaSostituzione.getText(), txtNumeroDiSerie.getText(),
							txtAddetto.getText(), txtCodiceMagazzino.getText()};
		if(array[3].isEmpty())
			array[3]=DatabaseOperation.writeDate("");	//write null
		else if(DatabaseOperation.checkDate(array[3]))
			array[3]=DatabaseOperation.writeDate(array[3]);
		if(array[4].isEmpty())
			array[4]=DatabaseOperation.writeDate("");
		else if(DatabaseOperation.checkDate(array[4]))
			array[4]=DatabaseOperation.writeDate(array[4]);
		
		return array;
	}
	
	
	private void setTextForDuplicate(String[] val){
		txtMarca.setText(val[0]);
		txtTipoStrumento.setText(val[1]);
		txtModello.setText(val[2]);
		txtCodiceMagazzino.setText(val[3]);
		if (val[4]!=null)
			datePickerDataDiMontaggio.setDate(setDateToPicker(val[4]));
	}
	
	
	private String getDateFromPicker(Date data){
		if(data==null){
			return "";
		}
		return sdf.format(data);
	}
	
	
	private Date setDateToPicker(String date){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.valueOf(date.substring(0, 4)));
		cal.set(Calendar.MONTH,Integer.valueOf(date.substring(5, 7))-1);
		cal.set(Calendar.DAY_OF_MONTH,Integer.valueOf(date.substring(8, 10)));
		return cal.getTime();
	}
	
	

}
