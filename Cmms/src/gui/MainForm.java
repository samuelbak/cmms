package gui;


import java.awt.Point;
//import java.awt.SystemTray;
//import java.awt.TrayIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JButton;

import source.DatabaseOperation;
import source.DatabaseConnector;
import source.DynamicTree;
import source.QueryConstructor;
import source.TableControl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;

public class MainForm extends JFrame {

	private static final long serialVersionUID = -9210129773153199942L;
	private JPanel contentPane;
	private static JLabel lblDatabaseStatus;
	private static JButton btnAggiungi;
	private static JButton btnModifica;
	private static JMenuItem mntmConnetti;
	private static JMenuItem mntmDisconnetti;
	private static JButton btnAggiungiItem;
	private static JButton btnDuplicaItem;
	private static JButton btnRimuovi;
	private static JButton btnAggiungiIntervento;
	public static DynamicTree tree;
	static TableControl tableItem;
	static TableControl tableInterventi;
	
	public static DatabaseConnector db;
	
	public static DatabaseSetupForm databaseSetupForm;
	public static NewDatabaseForm newDatabaseForm;
	public static NewZoneForm newZoneForm;
	public static ItemForm itemForm;
	public static InterventoForm interventoForm;
	public static EditZoneForm editZoneForm;
	public static CercaCodiceMagazzino cercaCodiceMagazzino;
	public static CercaPersonalizzato cercaPersonalizzato;
	
	private JMenu mnCerca;
	private static JMenuItem mntmCodiceMagazzino;
	private JMenu mnImpostazioni;
	private static JMenuItem mntmPersonalizzata;

	private JPanel panelInterventi;
	private JScrollPane scrollPaneInterventi;
	
	private JMenuBar menuBarInterventi;
	private JPanel panelItem;
	private JMenuBar menuBarItem;
	private JPanel panelTree;
	private JScrollPane scrollPaneTree;
	private JMenu mnStrumenti;
	private JMenuItem mntmReportItem;

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainForm() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			showMessage("Classe look and feel non trovata", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (InstantiationException e) {
			showMessage("Errore istanza look and feel", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			showMessage("Errore accesso look and feel", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			showMessage("Look and feel non trovata", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tool.png")));
	    setTitle("Cmms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 733);
		setMinimumSize(getSize());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDatabase = new JMenu("Database");
		menuBar.add(mnDatabase);
		
		mntmConnetti = new JMenuItem("Connetti");
		mntmConnetti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					db = new DatabaseConnector();
				} catch (SQLException e1) {
					showMessage("Connessione fallita", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				if(db.isConnected())
					databaseIsConnected();
			}
		});
		mnDatabase.add(mntmConnetti);
		
		
		mntmDisconnetti = new JMenuItem("Disconnetti");
		mntmDisconnetti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					db.close();
				} catch (SQLException e1) {
					showMessage("Errore disconnessione", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				if(!db.isConnected()){
					databaseIsNotConnected();
					tableItem.clearTable();
				}
			}
		});
		mntmDisconnetti.setEnabled(false);
		mnDatabase.add(mntmDisconnetti);
		
		JMenuItem mntmCreaNuovoDatabase = new JMenuItem("Crea nuovo database");
		mntmCreaNuovoDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newDatabaseForm = new NewDatabaseForm();
				newDatabaseForm.setLocationRelativeTo(null);
				newDatabaseForm.setVisible(true);
			}
		});
		mnDatabase.add(mntmCreaNuovoDatabase);
		
		mnImpostazioni = new JMenu("Impostazioni");
		menuBar.add(mnImpostazioni);
		
		JMenuItem mntmImpostazioni = new JMenuItem("Impostazioni");
		mnImpostazioni.add(mntmImpostazioni);
		mntmImpostazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseSetupForm = new DatabaseSetupForm();
				databaseSetupForm.setLocationRelativeTo(null);
				databaseSetupForm.setVisible(true);
			}
		});
		
		mnStrumenti = new JMenu("Strumenti");
		menuBar.add(mnStrumenti);
		
		mntmReportItem = new JMenuItem("Report item");
		mntmReportItem.setEnabled(false);
		mnStrumenti.add(mntmReportItem);
		
		mnCerca = new JMenu("Cerca");
		menuBar.add(mnCerca);
		
		mntmCodiceMagazzino = new JMenuItem("Codice magazzino");
		mntmCodiceMagazzino.setEnabled(false);
		mntmCodiceMagazzino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cercaCodiceMagazzino = new CercaCodiceMagazzino();
				cercaCodiceMagazzino.setLocationRelativeTo(null);
				cercaCodiceMagazzino.setVisible(true);				
			}
		});
		mnCerca.add(mntmCodiceMagazzino);
		
		mntmPersonalizzata = new JMenuItem("Personalizzata");
		mntmPersonalizzata.setEnabled(false);
		mntmPersonalizzata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cercaPersonalizzato = new CercaPersonalizzato();
				cercaPersonalizzato.setLocationRelativeTo(null);
				cercaPersonalizzato.setVisible(true);	
			}
		});
		mnCerca.add(mntmPersonalizzata);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("268dlu:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		panelTree = new JPanel();
		contentPane.add(panelTree, "2, 2, 5, 3, fill, fill");
		panelTree.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTree = new JScrollPane();
		panelTree.add(scrollPaneTree);
		
		tree = new DynamicTree();
		scrollPaneTree.setViewportView(tree);
		tree.setShowsRootHandles(true);
		tree.setEnabled(false);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)	{
				if(db.isConnected()){
					btnAggiungiItem.setEnabled(true);
					btnDuplicaItem.setEnabled(true);
					tableItem.clearTable();
					tableInterventi.clearTable();
					btnAggiungiIntervento.setEnabled(false);
					try {
						ResultSet result = db.query(QueryConstructor.getQueryForSearch(tree.getSelectionPathAsString()));
						while(result.next())
							tableItem.addRow(DatabaseOperation.parseColumnDataForTable(result));
					} catch (SQLException e) {
						showMessage("Errore selezione dati database o tabella", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
				else{
					btnAggiungiItem.setEnabled(true);
					btnDuplicaItem.setEnabled(true);
				}
			}
		});
		
		panelItem = new JPanel();
		panelItem.setToolTipText("Tabella item");
		contentPane.add(panelItem, "8, 2, 5, 1, fill, fill");
		panelItem.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTableItem = new JScrollPane();
		panelItem.add(scrollPaneTableItem, BorderLayout.CENTER);
		
		tableItem = new TableControl(5, new String[]{"Tipo item", "Modello", "Data di montaggio", "Data di sostituzione", "id"});
		tableItem.setBackground(new Color(204, 255, 204));
		tableItem.setRowSelectionAllowed(true);
		tableItem.setEnabled(true);
		scrollPaneTableItem.setViewportView(tableItem);
		tableItem.createPopUpMenu();
		addPopup(scrollPaneTableItem, tableItem.popupMenu);
		
		menuBarItem = new JMenuBar();
		menuBarItem.setBorderPainted(false);
		panelItem.add(menuBarItem, BorderLayout.NORTH);
		
		btnAggiungiItem = new JButton("Aggiungi Item");
		menuBarItem.add(btnAggiungiItem);
		btnAggiungiItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemForm = new ItemForm(tree.getSelectionPathAsString());
				itemForm.setLocationRelativeTo(null);
				itemForm.setAlwaysOnTop(true);
	            itemForm.setVisible(true);
			}
		});
		btnAggiungiItem.setEnabled(false);
		
		btnDuplicaItem = new JButton("Duplica Item");
		menuBarItem.add(btnDuplicaItem);
		btnDuplicaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					duplicateItem();
				} catch (SQLException e) {
					showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				tableRefresh();
			}
		});
		btnDuplicaItem.setEnabled(false);
		
		tableItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
		        Point p = mouseEvent.getPoint();
		        int row = table.rowAtPoint(p);
		        if(mouseEvent.getClickCount() == 1){
		        	btnAggiungiIntervento.setEnabled(true);
		        	tableInterventi.clearTable();
		        	tableInterventi.id_riferimento=(String)table.getValueAt(row, 4);
		        	try {
						ResultSet result = db.query(QueryConstructor.getQueryForInterventionSearch(tableInterventi.id_riferimento));
						while(result.next())
							tableInterventi.addRow(DatabaseOperation.parseColumnDataForTableIntervention(result));
					} catch (SQLException e) {
						showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
		        }
		        if (mouseEvent.getClickCount() == 2) {
		            try {
						itemForm = new ItemForm(DatabaseOperation.getValuesForMod((String)table.getValueAt(row, table.getColumnCount()-1)), (String)table.getValueAt(row, table.getColumnCount()-1));
						itemForm.setLocationRelativeTo(null);
						itemForm.setVisible(true);
						itemForm.setAlwaysOnTop(true);
					} catch (SQLException e) {
						showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
		        }
		        
			}
		});
		
		btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editZoneForm = new EditZoneForm(tree.getSelectionPathAsString());
				editZoneForm.setAlwaysOnTop(true);
				editZoneForm.setVisible(true);
			}
		});
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					newZoneForm = new NewZoneForm();
					newZoneForm.setLocationRelativeTo(null);
					newZoneForm.setVisible(true);
			}
		});
		
		panelInterventi = new JPanel();
		contentPane.add(panelInterventi, "8, 4, 5, 1, fill, fill");
		panelInterventi.setLayout(new BorderLayout(0, 0));
		
		scrollPaneInterventi = new JScrollPane();
		panelInterventi.add(scrollPaneInterventi, BorderLayout.CENTER);
		
		tableInterventi = new TableControl(4, new String[]{"Zona intervento","Motivo intervento", "Data", "id"});
		tableInterventi.setBackground(new Color(255, 255, 153));
		tableInterventi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
		        Point p = mouseEvent.getPoint();
		        int row = table.rowAtPoint(p);  
		        if (mouseEvent.getClickCount() == 2) {
		            try {
		            	try {
							interventoForm = new InterventoForm(DatabaseOperation.getValuesForInterventionMod((String)tableInterventi.getValueAt(row, table.getColumnCount()-1)), (String)tableInterventi.getValueAt(row, table.getColumnCount()-1));
							interventoForm.setLocationRelativeTo(null);
							interventoForm.setVisible(true);
							interventoForm.setAlwaysOnTop(true);
						} catch (SQLException e) {
							showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					} catch (Exception e) {
						showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
		        }
				
			}
		});
		scrollPaneInterventi.setViewportView(tableInterventi);
		tableInterventi.createPopUpMenu();
		addPopup(scrollPaneInterventi, tableInterventi.popupMenu);
		
		
		menuBarInterventi = new JMenuBar();
		menuBarInterventi.setBorderPainted(false);
		panelInterventi.add(menuBarInterventi, BorderLayout.NORTH);
		
		btnAggiungiIntervento = new JButton("Aggiungi");
		btnAggiungiIntervento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				interventoForm = new InterventoForm(tableInterventi.id_riferimento);
				interventoForm.setLocationRelativeTo(null);
				interventoForm.setAlwaysOnTop(true);
				interventoForm.setVisible(true);
			}
		});
		menuBarInterventi.add(btnAggiungiIntervento);
		btnAggiungiIntervento.setEnabled(false);
		btnAggiungi.setEnabled(false);
		contentPane.add(btnAggiungi, "2, 6");
		btnModifica.setEnabled(false);
		contentPane.add(btnModifica, "4, 6");
		
		btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Confermi eliminazione zona?") == 0){
					try {
						DatabaseOperation.deleteFromDb(tree.getSelectionPathAsString());
						tree.deleteNode(tree.getSelectionPath());
					} catch (SQLException e) {
						showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		});
		btnRimuovi.setEnabled(false);
		contentPane.add(btnRimuovi, "6, 6");
		
		lblDatabaseStatus = new JLabel("Non connesso");
		contentPane.add(lblDatabaseStatus, "12, 6, right, center");
		/*
		//Tray setup
		//final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("images\\tool.gif"));
        trayIcon.setImageAutoSize(true);
        final SystemTray tray = SystemTray.getSystemTray();
        
     // Create a pop-up menu components
        /*
        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
       
        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(errorItem);
        displayMenu.add(warningItem);
        displayMenu.add(infoItem);
        displayMenu.add(noneItem);
        popup.add(exitItem);
       
        trayIcon.setPopupMenu(popup);
       
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
        */
		
	}
	
	@SuppressWarnings("unused")
	private static void autoConnection() throws SQLException{
			db = new DatabaseConnector();
			if(db.isConnected())
				databaseIsConnected();
	}
	
	public static void databaseIsConnected(){
		lblDatabaseStatus.setText("Connesso");
		try {
			tree.createTreeFromScratch();
		} catch (SQLException e) {
			showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		btnAggiungi.setEnabled(true);
		tree.setEnabled(true);
		btnModifica.setEnabled(true);
		btnRimuovi.setEnabled(true);
		//btnAggiungiItem.setEnabled(true);
		mntmConnetti.setEnabled(false);
		mntmDisconnetti.setEnabled(true);
		mntmCodiceMagazzino.setEnabled(true);
		mntmPersonalizzata.setEnabled(true);
	}
	
	public static void databaseIsNotConnected(){
		tree.clear();
		tree.setEnabled(false);
		lblDatabaseStatus.setText("Non connesso");
		btnAggiungi.setEnabled(false);
		btnModifica.setEnabled(false);
		btnRimuovi.setEnabled(false);
		btnDuplicaItem.setEnabled(false);
		btnAggiungiIntervento.setEnabled(false);
		mntmConnetti.setEnabled(true);
		mntmDisconnetti.setEnabled(false);
		mntmCodiceMagazzino.setEnabled(false);
		mntmPersonalizzata.setEnabled(false);
	}
	
	public static void showMessage(String message, int typeOfMessage){
		JOptionPane.showMessageDialog(null, message, message, typeOfMessage);
	}
	
	public static void tableRefresh(){
		tableItem.clearTable();
		try {
			ResultSet result = db.query(QueryConstructor.getQueryForSearch(tree.getSelectionPathAsString()));
			while(result.next())
				tableItem.addRow(DatabaseOperation.parseColumnDataForTable(result));
		} catch (SQLException e) {
			showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void tableInterventionRefresh(){
		tableInterventi.clearTable();
		try {
			ResultSet result = db.query(QueryConstructor.getQueryForInterventionSearch(tableInterventi.id_riferimento));
			while(result.next())
				tableInterventi.addRow(DatabaseOperation.parseColumnDataForTableIntervention(result));
		} catch (SQLException e) {
			showMessage("Errore selezione dati database", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void duplicateItem() throws SQLException{
		String lastId = (String)tableItem.getValueAt(tableItem.getRowCount()-1, 4);
		itemForm = new ItemForm(tree.getSelectionPathAsString(),DatabaseOperation.getStringForDuplicated(lastId));
		itemForm.setLocationRelativeTo(null);
		itemForm.setVisible(true);
		itemForm.setAlwaysOnTop(true);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
