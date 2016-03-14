package source;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public class DynamicTree extends JTree{
	private static final long serialVersionUID = 8813840093714101946L;
	private static DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root Node");
	private static DefaultTreeModel rootTree = new DefaultTreeModel(rootNode);
	
	
	public DynamicTree(){	
		super(rootTree);
		setRootVisible(false);
	}
	
	//private void getNamesFromDb(){}
	
	public void createTreeFromScratch() throws SQLException{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Impianti");
		DefaultTreeModel model = new DefaultTreeModel(root);
		
		ResultSet zoneResult[] = new ResultSet[10];
		DefaultMutableTreeNode zoneNode[] = new DefaultMutableTreeNode[10];
		DatabaseConnector zoneDb[] = new DatabaseConnector[10];
		
		for(int i=0;i<10;i++)
			zoneDb[i] = new DatabaseConnector();
		
		zoneResult[0] = zoneDb[0].query("SELECT DISTINCT(zona_0) AS String FROM impianti_"+DatabaseSetup.radix+" ORDER BY zona_0");
		
		while(zoneResult[0].next()){
			if (!zoneResult[0].getString(1).equals("")){	
				zoneNode[0] = new DefaultMutableTreeNode(zoneResult[0].getString(1));
				root.add(zoneNode[0]);
			}
			zoneResult[1] = zoneDb[1].query("SELECT DISTINCT(zona_1) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' ORDER BY zona_1");
			while(zoneResult[1].next()){
				if (!zoneResult[1].getString(1).equals("")){
					zoneNode[1] = new DefaultMutableTreeNode(zoneResult[1].getString(1));
					zoneNode[0].add(zoneNode[1]);
				}
				zoneResult[2] = zoneDb[2].query("SELECT DISTINCT(zona_2) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' ORDER BY zona_2");
				while(zoneResult[2].next()){
					if (!zoneResult[2].getString(1).equals("")){
						zoneNode[2] = new DefaultMutableTreeNode(zoneResult[2].getString(1));
						zoneNode[1].add(zoneNode[2]);
					}
					zoneResult[3] = zoneDb[3].query("SELECT DISTINCT(zona_3) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' AND zona_2='"+zoneResult[2].getString(1)+"' ORDER BY zona_3");
					while(zoneResult[3].next()){
						if (!zoneResult[3].getString(1).equals("")){
							zoneNode[3] = new DefaultMutableTreeNode(zoneResult[3].getString(1));
							zoneNode[2].add(zoneNode[3]);
						}
						zoneResult[4] = zoneDb[4].query("SELECT DISTINCT(zona_4) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' AND zona_2='"+zoneResult[2].getString(1)+"' AND zona_3='"+zoneResult[3].getString(1)+"' ORDER BY zona_4");
						while(zoneResult[4].next()){
							if (!zoneResult[4].getString(1).equals("")){
								zoneNode[4] = new DefaultMutableTreeNode(zoneResult[4].getString(1));
								zoneNode[3].add(zoneNode[4]);
							}
							zoneResult[5] = zoneDb[5].query("SELECT DISTINCT(zona_5) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' AND zona_2='"+zoneResult[2].getString(1)+"' AND zona_3='"+zoneResult[3].getString(1)+"' AND zona_4='"+zoneResult[4].getString(1)+"' ORDER BY zona_5");
							while(zoneResult[5].next()){
								if (!zoneResult[5].getString(1).equals("")){
									zoneNode[5] = new DefaultMutableTreeNode(zoneResult[5].getString(1));
									zoneNode[4].add(zoneNode[5]);
								}
								zoneResult[6] = zoneDb[6].query("SELECT DISTINCT(zona_6) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' AND zona_2='"+zoneResult[2].getString(1)+"' AND zona_3='"+zoneResult[3].getString(1)+"' AND zona_4='"+zoneResult[4].getString(1)+"' AND zona_5='"+zoneResult[5].getString(1)+"' ORDER BY zona_6");
								while(zoneResult[6].next()){
									if (!zoneResult[6].getString(1).equals("")){
										zoneNode[6] = new DefaultMutableTreeNode(zoneResult[6].getString(1));
										zoneNode[5].add(zoneNode[6]);
									}
									zoneResult[7] = zoneDb[7].query("SELECT DISTINCT(zona_7) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' AND zona_2='"+zoneResult[2].getString(1)+"' AND zona_3='"+zoneResult[3].getString(1)+"' AND zona_4='"+zoneResult[4].getString(1)+"' AND zona_5='"+zoneResult[5].getString(1)+"' AND zona_6='"+zoneResult[6].getString(1)+"' ORDER BY zona_7");
									while(zoneResult[7].next()){
										if (!zoneResult[7].getString(1).equals("")){
											zoneNode[7] = new DefaultMutableTreeNode(zoneResult[7].getString(1));
											zoneNode[6].add(zoneNode[7]);
										}
										zoneResult[8] = zoneDb[8].query("SELECT DISTINCT(zona_8) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' AND zona_2='"+zoneResult[2].getString(1)+"' AND zona_3='"+zoneResult[3].getString(1)+"' AND zona_4='"+zoneResult[4].getString(1)+"' AND zona_5='"+zoneResult[5].getString(1)+"' AND zona_6='"+zoneResult[6].getString(1)+"' AND zona_7='"+zoneResult[7].getString(1)+"' ORDER BY zona_8");
										while(zoneResult[8].next()){
											if (!zoneResult[8].getString(1).equals("")){
												zoneNode[8] = new DefaultMutableTreeNode(zoneResult[8].getString(1));
												zoneNode[7].add(zoneNode[8]);
											}
											zoneResult[9] = zoneDb[9].query("SELECT DISTINCT(zona_9) AS String FROM impianti_"+DatabaseSetup.radix+" WHERE zona_0='"+zoneResult[0].getString(1)+"' AND zona_1='"+zoneResult[1].getString(1)+"' AND zona_2='"+zoneResult[2].getString(1)+"' AND zona_3='"+zoneResult[3].getString(1)+"' AND zona_4='"+zoneResult[4].getString(1)+"' AND zona_5='"+zoneResult[5].getString(1)+"' AND zona_6='"+zoneResult[6].getString(1)+"' AND zona_7='"+zoneResult[7].getString(1)+"' AND zona_8='"+zoneResult[8].getString(1)+"'  ORDER BY zona_9");
											while(zoneResult[9].next()){
												if (!zoneResult[9].getString(1).equals("")){
													zoneNode[9] = new DefaultMutableTreeNode(zoneResult[9].getString(1));
													zoneNode[8].add(zoneNode[9]);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for(int i=0;i<10;i++)
			zoneDb[i].close();
		
		this.setNewModel(model, root);
	}
	
	public void setNewModel(DefaultTreeModel newModel, DefaultMutableTreeNode newNode){
		rootNode = newNode;
		rootTree = newModel;
		this.setModel(rootTree);
		rootTree.reload();
	}
	
	public void clear(){
		rootNode.removeAllChildren();
		rootTree.reload();
		
	}
	
	public String[] getSelectionPathAsString(){
		String selPath = this.getSelectionPath().toString();
		selPath = selPath.substring(11, selPath.length()-1);
		String[] retPath = selPath.split(", ");
		return retPath;
	}
	
	public String[] addNodeUnderCurrent(String text) throws SQLException{
		TreePath path = this.getSelectionPath();
		String[] dbArray;
		if (path!=null){
			DefaultMutableTreeNode insertNode = (DefaultMutableTreeNode) path.getLastPathComponent();
			rootTree.insertNodeInto(new DefaultMutableTreeNode(text), insertNode, insertNode.getChildCount());
			String pathString = path.toString();
			pathString = pathString.substring(1, pathString.length()-1);
			String[] pathArray = pathString.split(", ");
			dbArray = new String[pathArray.length];
			for(int i=1;i<pathArray.length;i++)
				dbArray[i-1]=pathArray[i];
			dbArray[dbArray.length-1]=text;
		}
		else{
			rootTree.insertNodeInto(new DefaultMutableTreeNode(text), (MutableTreeNode) rootTree.getRoot(), rootNode.getChildCount());
			dbArray = new String[1];
			dbArray[0]=text;
		}
		return dbArray;
	}

	public void deleteNode(TreePath node){
		rootTree.removeNodeFromParent((MutableTreeNode) node.getLastPathComponent());	
	}
}
