package source;

public class QueryConstructor {

	public static String getQueryForCreateItemDatabase(String nomeDb, String radice){
		String query = "CREATE TABLE `"+nomeDb+"`.`guasti_"+radice+"` ("
				+ "`id` INT NOT NULL AUTO_INCREMENT,"
				+ "`zona_0` VARCHAR(45) NULL,"
				+ "`zona_1` VARCHAR(45) NULL,"
				+ "`zona_2` VARCHAR(45) NULL,"
				+ "`zona_3` VARCHAR(45) NULL,"
				+ "`zona_4` VARCHAR(45) NULL,"
				+ "`zona_5` VARCHAR(45) NULL,"
				+ "`zona_6` VARCHAR(45) NULL,"
				+ "`zona_7` VARCHAR(45) NULL,"
				+ "`zona_8` VARCHAR(45) NULL,"
				+ "`zona_9` VARCHAR(45) NULL,"
				+ "`marca` VARCHAR(45) NULL,"
				+ "`tipo_strumento` VARCHAR(60) NULL,"
				+ "`modello` VARCHAR(60) NULL,"
				+ "`data_montaggio` DATE NULL,"
				+ "`data_sostituzione` DATE NULL,"
				+ "`motivo_sostituzione` VARCHAR(600) NULL,"
				+ "`numero_serie` VARCHAR(45) NULL,"
				+ "`addetto` VARCHAR(45) NULL,"
				+ "`codice_magazzino` VARCHAR(45) NULL,"
				+ "PRIMARY KEY (`id`))";
		return query;
	}

	public static String getQueryForCreatePlantDatabase(String nomeDb, String radice){
		String query = "CREATE TABLE `"+nomeDb+"`.`impianti_"+radice+"` ("
				+ "`id` INT NOT NULL AUTO_INCREMENT,"
				+ "`zona_0` VARCHAR(45) NULL,"
				+ "`zona_1` VARCHAR(45) NULL,"
				+ "`zona_2` VARCHAR(45) NULL,"
				+ "`zona_3` VARCHAR(45) NULL,"
				+ "`zona_4` VARCHAR(45) NULL,"
				+ "`zona_5` VARCHAR(45) NULL,"
				+ "`zona_6` VARCHAR(45) NULL,"
				+ "`zona_7` VARCHAR(45) NULL,"
				+ "`zona_8` VARCHAR(45) NULL,"
				+ "`zona_9` VARCHAR(45) NULL,"
				+ "PRIMARY KEY (`id`))";
		return query;
	}
	
	public static String getQueryForCreateInterventionDatabase(String nomeDb, String radice){
		String query = 	"CREATE TABLE `"+nomeDb+"`.`interventi_"+radice+"` ("+
						"`id` INT NOT NULL AUTO_INCREMENT,"+
						"`id_riferimento` INT NULL,"+
						"`zona_intervento` VARCHAR(100) NULL,"+
						"`motivo` VARCHAR(50) NULL,"+
						"`descrizione` VARCHAR(500) NULL,"+
						"`data` DATE NOT NULL,"+
						"`addetto` VARCHAR(100) NULL,"+
						"PRIMARY KEY (`id`));";
		return query;
	}
	
	public static String getQueryForCreateSpareDatabase(String nomeDb, String radice){
		String query = "";
		return query;
	}
 
	public static String getQueryForInterventionSearch(String id_riferimento){
		String query;
		query = "SELECT * FROM "+DatabaseSetup.dataBaseName+".interventi_"+DatabaseSetup.radix+" WHERE id_riferimento="+"'"+id_riferimento+"';";
		return query;
	}
	
	public static String getQueryForIdsSearch(String[] path){
		String query;
		query = "SELECT id FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE ";
		for(Integer i=0; i<10; i++){
			if(i<path.length)
				query = query + "zona_"+i.toString()+"='"+path[i]+"' AND ";
			else
				query = query + "zona_"+i.toString()+"='' AND ";
		}
		query = query.substring(0, query.length()-5);
		query = query +";";
		return query;
	}
	
	public static String getQueryForSearch(String[] path){
		String query;
		query = "SELECT * FROM "+DatabaseSetup.dataBaseName+".guasti_"+DatabaseSetup.radix+" WHERE ";
		for(Integer i=0; i<10; i++){
			if(i<path.length)
				query = query + "zona_"+i.toString()+"='"+path[i]+"' AND ";
			else
				query = query + "zona_"+i.toString()+"='' AND ";
		}
		query = query.substring(0, query.length()-5);
		query = query +";";
		return query;
	}
}
