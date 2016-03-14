package gui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import source.DatabaseOperation;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class InterventoForm extends JFrame {

	private static final long serialVersionUID = -6570661073822410633L;
	private JPanel contentPane;
	private JTextField txtZona;
	private JTextField txtAddetto;
	private JTextArea txtDescrizione;
	private JTextField txtMotivoIntervento;
	private JXDatePicker datePickerData;
	private JButton btnAggiungi;
	private JLabel lblMotivoInterveno;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public InterventoForm(final String id_riferimento) {	//nuovo intervento
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
			setResizable(false);
			setTitle("Aggiungi intervento");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 414, 349);
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
					RowSpec.decode("107dlu"),
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,}));
				
			txtZona = new JTextField();
			contentPane.add(txtZona, "2, 2, 3, 1, fill, default");
			txtZona.setColumns(10);
			
			JLabel lblZonaIntervento = new JLabel("Zona intervento");
			contentPane.add(lblZonaIntervento, "6, 2");
			
			txtMotivoIntervento = new JTextField();
			contentPane.add(txtMotivoIntervento, "2, 4, 3, 1, fill, default");
			txtMotivoIntervento.setColumns(10);
			
			lblMotivoInterveno = new JLabel("Motivo intervento");
			contentPane.add(lblMotivoInterveno, "6, 4");
			
			
			JButton btnAnnulla = new JButton("Annulla");
			btnAnnulla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						DatabaseOperation.writeNewInterventionToDbWithoutDate(getStringArray(), id_riferimento);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MainForm.tableInterventionRefresh();
					dispose();
				}
			});
			
			txtDescrizione = new JTextArea();
			txtDescrizione.setWrapStyleWord(true);
			txtDescrizione.setLineWrap(true);
			txtDescrizione.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtDescrizione.setBorder(UIManager.getBorder("TextField.border"));
			contentPane.add(txtDescrizione, "2, 6, 3, 1, fill, fill");
			
			JLabel lblDescrizioneIntervento = new JLabel("Descrizione intervento");
			contentPane.add(lblDescrizioneIntervento, "6, 6");
			
			datePickerData = new JXDatePicker();
			datePickerData.setFormats(new String[]{"yyyy-MM-dd"});
			contentPane.add(datePickerData, "2, 8, 3, 1, fill, default");
			datePickerData.setDate(new Date());
			
			JLabel lblData = new JLabel("Data");
			contentPane.add(lblData, "6, 8");
			
			txtAddetto = new JTextField();
			contentPane.add(txtAddetto, "2, 10, 3, 1, fill, default");
			txtAddetto.setColumns(10);
			
			JLabel lblAddetto = new JLabel("Addetto");
			contentPane.add(lblAddetto, "6, 10");				
			contentPane.add(btnAggiungi, "2, 12");
			contentPane.add(btnAnnulla, "4, 12");
	}

	
	
	/**
	 * @wbp.parser.constructor
	 */
	
	public InterventoForm(String[] values, final String id) {	//modifica Item
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
		setResizable(false);
		setTitle("Modifica intervento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 348);
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
				RowSpec.decode("109dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		txtZona = new JTextField();
		contentPane.add(txtZona, "2, 2, 3, 1, fill, default");
		txtZona.setColumns(10);
		
		JLabel lblZonaIntervento = new JLabel("Zona intervento");
		contentPane.add(lblZonaIntervento, "6, 2");
		
		txtMotivoIntervento = new JTextField();
		contentPane.add(txtMotivoIntervento, "2, 4, 3, 1, fill, default");
		txtMotivoIntervento.setColumns(10);
		
		JLabel lblMotivoIntervento = new JLabel("Motivo intervento");
		contentPane.add(lblMotivoIntervento, "6, 4");
		
		JButton btnAggiungiModifica = new JButton("Modifica");
		btnAggiungiModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseOperation.updateInterventionWithId(getStringArray(), id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainForm.tableInterventionRefresh();
				dispose();
			}
		});
		
		txtDescrizione = new JTextArea();
		txtDescrizione.setWrapStyleWord(true);
		txtDescrizione.setLineWrap(true);
		txtDescrizione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDescrizione.setBorder(UIManager.getBorder("TextField.border"));
		contentPane.add(txtDescrizione, "2, 6, 3, 1, fill, fill");
		
		JLabel lblDescrizioneIntervento = new JLabel("Descrizione intervento");
		contentPane.add(lblDescrizioneIntervento, "6, 6");
		
		datePickerData = new JXDatePicker();
		datePickerData.setFormats(new String[]{"yyyy-MM-dd"});
		contentPane.add(datePickerData, "2, 8, 3, 1, fill, default");
		
		JLabel lblData = new JLabel("Data");
		contentPane.add(lblData, "6, 8");
		
		txtAddetto = new JTextField();
		contentPane.add(txtAddetto, "2, 10, 3, 1, fill, default");
		txtAddetto.setColumns(10);
		
		JLabel lblAddetto = new JLabel("Addetto");
		contentPane.add(lblAddetto, "6, 10");
		contentPane.add(btnAggiungiModifica, "2, 12");
		
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainForm.interventoForm.setAlwaysOnTop(false);
				try {
					if (JOptionPane.showConfirmDialog(null, "Confermi eliminazione?") == 0)
						DatabaseOperation.deleteInterventionFromDbWithId(id);
					MainForm.tableRefresh();	
					dispose();
				} catch (SQLException e) {
					MainForm.showMessage("Impossibile eliminare l'item", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				dispose();
				MainForm.tableInterventionRefresh();
			}
		});
		contentPane.add(btnElimina, "4, 12");
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnAnnulla, "6, 12");
		
		if(values.length==5){
			txtZona.setText(values[0]);
			txtMotivoIntervento.setText(values[1]);
			txtDescrizione.setText(values[2]);
			if (values[3]!=null)
				datePickerData.setDate(setDateToPicker(values[3]));
			txtAddetto.setText(values[4]);
		}
	}
	
	private String[] getStringArray(){
		String[] array = 	{txtZona.getText(), txtMotivoIntervento.getText(),
							 txtDescrizione.getText(), getDateFromPicker(datePickerData.getDate()), 
							 txtAddetto.getText()};
		if(array[3].isEmpty())
			array[3]=DatabaseOperation.writeDate("");	//write null
		else if(DatabaseOperation.checkDate(array[3]))
			array[3]=DatabaseOperation.writeDate(array[3]);
		
		return array;
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
