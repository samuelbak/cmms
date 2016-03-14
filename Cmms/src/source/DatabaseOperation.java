package source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import gui.MainForm;

public class DatabaseOperation {
	public static boolean createNewDatabase(String name) throws SQLException{
		boolean ret; 
		ret = MainForm.db.queryStatement(QueryConstructor.getQueryForCreateItemDatabase(DatabaseSetup.dataBaseName, name));
		ret = MainForm.db.queryStatement(QueryConstructor.getQueryForCreatePlantDatabase(DatabaseSetup.dataBaseName, name));
		ret = MainForm.db.queryStatement(QueryConstructor.getQueryForCreateInterventionDatabase(DatabaseSetup.dataBaseName, name));
		DatabaseSetup.radix = name;
		DatabaseSetup.writeConfiguration();
		return ret;
	}

	public static boolean addNewPath(String[] path) throws SQLException{
		String defValue[] = new String[10];
		for(int i=0;i<10;i++)
			defValue[i]="";
		for(int i=0;i<path.length;i++)
			defValue[i]=path[i];
		String query = "INSERT INTO `"+DatabaseSetup.dataBaseName+"`.`impianti_"+DatabaseSetup.radix+"` (";
		for(Integer i=0; i<10; i++)
			query = query.concat("`zona_"+i.toString()+"`, ");
		query = query.substring(0, query.length()-2);
		query = query.concat(") VALUES (");
		for(Integer i=0; i<10; i++)
			query = query.concat("'"+defValue[i]+"', ");
		query = query.substring(0, query.length()-2);
		query = query.concat(");");
		return MainForm.db.queryStatement(query);
	}

	public static String[] parseColumnDataForTable(ResultSet result) throws SQLException{
		String[] data = new String[5];
		data[0]=result.getString("tipo_strumento");
		data[1]=result.getString("modello");
		data[2]=result.getString("data_montaggio");
		data[3]=result.getString("data_sostituzione");
		data[4]=result.getString("id");
		return data;	
	}
	
	public static String[] parseColumnDataForTableIntervention(ResultSet result) throws SQLException{
		String[] data = new String[4];
		data[0]=result.getString("zona_intervento");
		data[1]=result.getString("motivo");
		data[2]=result.getString("data");
		data[3]=result.getString("id");
		return data;
	}

	public static String[] getValuesForMod(String id) throws SQLException{
		String[] values = new String[9];
		ResultSet resultFromDb = MainForm.db.query("SELECT * FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE id='"+id+"';");
		resultFromDb.next();
		values[0]=resultFromDb.getString("marca");
		values[1]=resultFromDb.getString("tipo_strumento");
		values[2]=resultFromDb.getString("modello");
		values[3]=resultFromDb.getString("codice_magazzino");
		values[4]=resultFromDb.getString("data_montaggio");
		values[5]=resultFromDb.getString("data_sostituzione");
		values[6]=resultFromDb.getString("motivo_sostituzione");
		values[7]=resultFromDb.getString("numero_serie");
		values[8]=resultFromDb.getString("addetto");
		return values;
	}
	
	public static String[] getValuesForInterventionMod(String id) throws SQLException{
		String[] values = new String[5];
		ResultSet resultFromDb = MainForm.db.query("SELECT * FROM "+DatabaseSetup.dataBaseName+".interventi_"+DatabaseSetup.radix+" WHERE id='"+id+"';");
		resultFromDb.next();
		values[0]=resultFromDb.getString("zona_intervento");
		values[1]=resultFromDb.getString("motivo");
		values[2]=resultFromDb.getString("descrizione");
		values[3]=resultFromDb.getString("data");
		values[4]=resultFromDb.getString("addetto");
		return values;
		
	}
	
	public static boolean writeNewItemToDb(String[] path, String[] values) throws SQLException{
		String defPathValue[] = new String[10];
		for(int i=0;i<10;i++)
			defPathValue[i]="";
		for(int i=0;i<path.length;i++)
			defPathValue[i]=path[i];
		String query = ("INSERT INTO `"+DatabaseSetup.dataBaseName+"`.`guasti_"+DatabaseSetup.radix+"` "
		+ "(`zona_0`, `zona_1`, `zona_2`, `zona_3`, `zona_4`, `zona_5`, `zona_6`, `zona_7`, `zona_8`, `zona_9`, "
		+ "`marca`, `tipo_strumento`, `modello`, `data_montaggio`, `data_sostituzione`, "
		+ "`motivo_sostituzione`, `numero_serie`, `addetto`, `codice_magazzino`) VALUES ("
		+ "'"+defPathValue[0]+"', "	//zona 0
		+ "'"+defPathValue[1]+"', "	//zona 1
		+ "'"+defPathValue[2]+"', "	//zona 2
		+ "'"+defPathValue[3]+"', "	//zona 3
		+ "'"+defPathValue[4]+"', "	//zona 4
		+ "'"+defPathValue[5]+"', "	//zona 5
		+ "'"+defPathValue[6]+"', "	//zona 6
		+ "'"+defPathValue[7]+"', "	//zona 7
		+ "'"+defPathValue[8]+"', "	//zona 8
		+ "'"+defPathValue[9]+"', "	//zona 9
		+ "'"+values[0]+"', "						//marca
		+ "'"+nascondiApostrofo(values[1])+"', "	//tipo_strumento
		+ "'"+nascondiApostrofo(values[2])+"', "	//modello
		+ "'"+values[3]+"', "	//data_montaggio
		+ "'"+values[4]+"', "	//data_sostituzione
		+ "'"+nascondiApostrofo(values[5])+"', "	//motivo_sostituzione
		+ "'"+nascondiApostrofo(values[6])+"', "	//numero_serie
		+ "'"+nascondiApostrofo(values[7])+"', "	//Addetto
		+ "'"+values[8]+"')");	//Codice magazzino
		return MainForm.db.queryStatement(query);
	}
	
	public static boolean writeNewItemToDbWithoutDate(String[] path, String[] values) throws SQLException{
		String defPathValue[] = new String[10];
		for(int i=0;i<10;i++)
			defPathValue[i]="";
		for(int i=0;i<path.length;i++)
			defPathValue[i]=path[i];
		String query = ("INSERT INTO `"+DatabaseSetup.dataBaseName+"`.`guasti_"+DatabaseSetup.radix+"` "
		+ "(`zona_0`, `zona_1`, `zona_2`, `zona_3`, `zona_4`, `zona_5`, `zona_6`, `zona_7`, `zona_8`, `zona_9`, "
		+ "`marca`, `tipo_strumento`, `modello`, `data_montaggio`, `data_sostituzione`, "
		+ "`motivo_sostituzione`, `numero_serie`, `addetto`, `codice_magazzino`) VALUES ("
		+ "'"+defPathValue[0]+"', "	//zona 0
		+ "'"+defPathValue[1]+"', "	//zona 1
		+ "'"+defPathValue[2]+"', "	//zona 2
		+ "'"+defPathValue[3]+"', "	//zona 3
		+ "'"+defPathValue[4]+"', "	//zona 4
		+ "'"+defPathValue[5]+"', "	//zona 5
		+ "'"+defPathValue[6]+"', "	//zona 6
		+ "'"+defPathValue[7]+"', "	//zona 7
		+ "'"+defPathValue[8]+"', "	//zona 8
		+ "'"+defPathValue[9]+"', "	//zona 9
		+ "'"+values[0]+"', "						//marca
		+ "'"+nascondiApostrofo(values[1])+"', "	//tipo_strumento
		+ "'"+nascondiApostrofo(values[2])+"', "	//modello
		+ ""+values[3]+", "	//data_montaggio
		+ ""+values[4]+", "	//data_sostituzione
		+ "'"+nascondiApostrofo(values[5])+"', "	//motivo_sostituzione
		+ "'"+nascondiApostrofo(values[6])+"', "	//numero_serie
		+ "'"+nascondiApostrofo(values[7])+"', "	//Addetto
		+ "'"+values[8]+"')");	//Codice magazzino
		return MainForm.db.queryStatement(query);
	}
	
	public static boolean writeNewInterventionToDbWithoutDate(String[] values, String id_riferimento) throws SQLException{
		String query = "INSERT INTO `"+DatabaseSetup.dataBaseName+"`.`interventi_"+DatabaseSetup.radix+"` "
				+ "(`id_riferimento`, `zona_intervento`, `motivo`, "
				+ "`descrizione`, `data`, `addetto`) VALUES ("
				+ "'"+id_riferimento+"', "	//id_riferimento
				+ "'"+values[0]+"', "	//zona
				+ "'"+nascondiApostrofo(values[1])+"', "	//motivo
				+ "'"+nascondiApostrofo(values[2])+"', "	//descrizione
				+ values[3]+", "	//data
				+ "'"+values[4]+"'); ";	//addetto
		return MainForm.db.queryStatement(query);
	}
	
	public static boolean deleteItemFromDbWithId(String Id) throws SQLException{
		String query = "DELETE FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE id='"+Id+"';";
		return MainForm.db.queryStatement(query);	
	}
	
	public static boolean deleteInterventionFromDbWithId(String id) throws SQLException{
		String query = "DELETE FROM "+DatabaseSetup.dataBaseName+".interventi_"+DatabaseSetup.radix+" WHERE id='"+id+"';";
		return MainForm.db.queryStatement(query);	
	}
	
	public static boolean deleteFromDb(String[] zone) throws SQLException{
		DatabaseConnector dbSearch = new DatabaseConnector();
		DatabaseConnector dbDelete = new DatabaseConnector();
		String querySelect = "SELECT id FROM "+DatabaseSetup.dataBaseName+".impianti_"+DatabaseSetup.radix+" WHERE ";
		String selection = "";
		boolean ret = false; 
		ResultSet ids;
		for (Integer i=0; i<zone.length;i++)
			selection = selection.concat("zona_"+i.toString()+"='"+zone[i]+"' AND ");
		selection = selection.substring(0, selection.length()-5);
		querySelect = querySelect.concat(selection+";");
		ids = dbSearch.query(querySelect);
		while(ids.next())
			ret = dbDelete.queryStatement("DELETE FROM "+DatabaseSetup.dataBaseName+".impianti_"+DatabaseSetup.radix+" WHERE id='"+ids.getString(1)+"';");
		querySelect = "SELECT id FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE "+selection;
		ids = dbSearch.query(querySelect);
		while(ids.next())
			ret = dbDelete.queryStatement("DELETE FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE id='"+ids.getString(1)+"';");
		return ret;
	}
	
	public static boolean updateItemWithId(String[] values, String id) throws SQLException{
		String query = "UPDATE `"+DatabaseSetup.dataBaseName+"`.`guasti_"+DatabaseSetup.radix+"` "
				+ "SET `marca`='"+DatabaseOperation.nascondiApostrofo(values[0])+"', "
				+ "`tipo_strumento`='"+DatabaseOperation.nascondiApostrofo(values[1])+"', "
				+ "`modello`='"+DatabaseOperation.nascondiApostrofo(values[2])+"', "
				+ "`data_montaggio`="+values[3]+", "
				+ "`data_sostituzione`="+values[4]+", "
				+ "`motivo_sostituzione`='"+DatabaseOperation.nascondiApostrofo(values[5])+"', "
				+ "`numero_serie`='"+DatabaseOperation.nascondiApostrofo(values[6])+"', "
				+ "`addetto`='"+DatabaseOperation.nascondiApostrofo(values[7])+"', "
				+ "`codice_magazzino`='"+values[8]+"' "
				+ "WHERE `id`='"+id+"';";		
		return MainForm.db.queryStatement(query);
	}
	
	public static boolean updateInterventionWithId(String[] values, String id) throws SQLException{
		String query = "UPDATE `"+DatabaseSetup.dataBaseName+"`.`interventi_"+DatabaseSetup.radix+"` "
				+ "SET `zona_intervento`='"+DatabaseOperation.nascondiApostrofo(values[0])+"', "
				+ "`motivo`='"+DatabaseOperation.nascondiApostrofo(values[1])+"', "
				+ "`descrizione`='"+DatabaseOperation.nascondiApostrofo(values[2])+"', "
				+ "`data`="+values[3]+", "
				+ "`addetto`='"+DatabaseOperation.nascondiApostrofo(values[4])+"' "
				+ "WHERE `id`='"+id+"';";
				
		return MainForm.db.queryStatement(query);
	}
	
	public static String[] getStringForDuplicated(String id) throws SQLException{
		String[] returnString = new String[5];
		String query = 	"SELECT marca, tipo_strumento, modello, "
				+ "codice_magazzino, data_sostituzione "
				+ "FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" "
				+ "WHERE id='"+id+"';";
		ResultSet rs = MainForm.db.query(query);
		rs.next();
		returnString[0] = rs.getString(1);
		returnString[1] = rs.getString(2);
		returnString[2] = rs.getString(3);
		returnString[3] = rs.getString(4);
		returnString[4] = rs.getString(5);
		
		return returnString;
	}
	
	public static boolean editZones(String[] previousPath, String newPath) throws SQLException{
		DatabaseConnector dbSearch = new DatabaseConnector();
		DatabaseConnector dbEdit = new DatabaseConnector();
		boolean ret = false;
		String querySelect = "SELECT id FROM "+DatabaseSetup.dataBaseName+".impianti_"+DatabaseSetup.radix+" WHERE ";
		String selection = "";
		ResultSet ids;
		for (Integer i=0; i<previousPath.length;i++)
			selection = selection.concat("zona_"+i.toString()+"='"+previousPath[i]+"' AND ");
		selection = selection.substring(0, selection.length()-5);
		querySelect = querySelect.concat(selection+";");
		ids = dbSearch.query(querySelect);
		while(ids.next())
			ret = dbEdit.queryStatement("UPDATE "+DatabaseSetup.dataBaseName+".impianti_"+DatabaseSetup.radix+" SET zona_"+(previousPath.length-1)+"='"+newPath+"' WHERE id='"+ids.getString(1)+"';");
		querySelect = "SELECT id FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE ";
		querySelect = querySelect.concat(selection+";");
		ids = dbSearch.query(querySelect);
		while(ids.next())
			ret = dbEdit.queryStatement("UPDATE "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" SET zona_"+(previousPath.length-1)+"='"+newPath+"' WHERE id='"+ids.getString(1)+"';");
		return ret;
	}
	
	public static ResultSet query(String query) throws SQLException{
		return MainForm.db.query(query);
	}
	
	public static ResultSet cercaCodiceMagazzino(String codiceMagazzino) throws SQLException{
		return MainForm.db.query("SELECT * FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE codice_magazzino='"+codiceMagazzino+"';");
	}

	public static boolean checkDate(String date){
		StringTokenizer st = new StringTokenizer(date, "-");
		if(st.countTokens() == 3){
			String anno = st.nextToken();
			if (anno.length()==4){
				String mese = st.nextToken();
				if(mese.length() == 2 && Integer.valueOf(mese)<=12){
					String giorno = st.nextToken();
					if (giorno.length() == 2 && Integer.valueOf(giorno)<=31)
						return true;
				}
			}
		}
		return false;
	}
	
	public static String writeDate(String date){
		if (date == "")
			return "NULL";
		else
			return "'"+date+"'";
	}
	
	public static String nascondiApostrofo(String text){
		String[] array = text.split("'");
		String retString = "";
		if (array.length==1)
			return text;
		
		if (array.length==2)
			return array[0]+'\\'+'\''+array[1];
		
		retString = array[0]+'\\'+'\'';
		for (int i=1;i<(array.length-1);i++)
			retString = retString+array[i]+'\\'+'\'';
		retString = retString+array[array.length-1];
		return retString;
	}

	public static boolean controllaApostrofo(String text){
		for(int i=0;i<text.length();i++)
			if(text.charAt(i)=='\'')
				return true;
		return false;
	}
	
}
