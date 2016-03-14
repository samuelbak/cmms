package source;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TableControl extends JTable{
	
	private static final long serialVersionUID = -7883355993547782410L;
	private DefaultTableModel model = new DefaultTableModel();
	public String id_riferimento="";
	public JPopupMenu popupMenu = new JPopupMenu();
	private String[] columnsName;
	
	public TableControl(Integer numberOfColumn, String[] columns){
		super();
		
		columnsName = columns;
		model = new DefaultTableModel();
		for(int i=0;i<numberOfColumn;i++)
			model.addColumn(columns[i]);
		this.setModel(model);
		
		RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
	    this.setRowSorter(sorter);
		
		this.setRowSelectionAllowed(false);
		this.setBackground(new Color(255, 235, 205));
		this.setAutoCreateColumnsFromModel(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setEnabled(false);

		hideColumn(numberOfColumn-1);
	}
	
	public void createPopUpMenu(){
		JCheckBoxMenuItem[] chckbxPopUp = new JCheckBoxMenuItem[this.columnsName.length]; 
		for (int i=0;i<this.columnsName.length-1;i++){
			chckbxPopUp[i] = new JCheckBoxMenuItem(this.columnsName[i]);
			chckbxPopUp[i].setSelected(true);
			chckbxPopUp[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!((JCheckBoxMenuItem)arg0.getSource()).isSelected())
						hideColumn(((JCheckBoxMenuItem)arg0.getSource()).getText());
					if (((JCheckBoxMenuItem)arg0.getSource()).isSelected())
						showColumn(((JCheckBoxMenuItem)arg0.getSource()).getText());
				}
			});
			this.popupMenu.add(chckbxPopUp[i]);
		}
	}
	
	public void uncheckColumn(String text){
		for (int i=0;i<popupMenu.getComponentCount();i++){
			if (((JCheckBoxMenuItem)this.popupMenu.getComponent(i)).getText().equals(text))
				((JCheckBoxMenuItem)this.popupMenu.getComponent(i)).doClick();
		}
	}
	
	public void hideColumn(String columnName){
		this.getColumn(columnName).setMaxWidth(0);
		this.getColumn(columnName).setMinWidth(0);
		this.getColumn(columnName).setWidth(0);
		this.getColumn(columnName).setPreferredWidth(0);		
	}
	
	public void hideColumn(Integer columnNumber){
		this.getColumnModel().getColumn(columnNumber).setMaxWidth(0);
		this.getColumnModel().getColumn(columnNumber).setMinWidth(0);
		this.getColumnModel().getColumn(columnNumber).setWidth(0);
		this.getColumnModel().getColumn(columnNumber).setPreferredWidth(0);		
	}
	
	public void showColumn(String columnName){
		this.getColumn(columnName).setMaxWidth(10000);
		this.getColumn(columnName).setMinWidth(10);
		this.getColumn(columnName).setWidth(10);
		this.getColumn(columnName).setPreferredWidth(100);
	}
	
	public void showColumn(Integer columnNumber){
		this.getColumnModel().getColumn(columnNumber).setMaxWidth(10000);
		this.getColumnModel().getColumn(columnNumber).setMinWidth(10);
		this.getColumnModel().getColumn(columnNumber).setWidth(10);
		this.getColumnModel().getColumn(columnNumber).setPreferredWidth(100);
	}

	
	public boolean addRow(String[] row){
		boolean ret = false;
		Object[] newRow = new Object[this.getColumnCount()];
		if(row.length==this.getColumnCount()){
			for(int i=0;i<this.getColumnCount();i++)
				newRow[i] = row[i];
			model.addRow(newRow);
			super.setModel(model);
			ret = true;
		}
		return ret;
	}
	
	public void clearTable(){
		for (int i=model.getRowCount()-1 ; i>=0 ; i--)
			model.removeRow(i);
	}
}
