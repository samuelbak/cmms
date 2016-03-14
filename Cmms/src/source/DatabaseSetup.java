package source;

import java.lang.reflect.InvocationTargetException;

public class DatabaseSetup {
	
	public static String versione = "1.0";
	public static String hostName = "DBMANELE-REGISTROGUASTI";
	public static Integer port = 3306;
	public static String dataBaseName = "bacchetta";
	public static String userName = "viewer_db";
	public static String userPassword = "viewer_db";
	public static String radix = "strumenti";
	
	public static boolean checkRegistry(){
		String versione = null;
		try {
			versione = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "versione");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(versione);
		if(versione==null)
			return false;
		else
			return true;
	}
	
	public static void writeConfiguration(){
		try {
			WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms");
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "versione", versione);
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "database_host", hostName);
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "porta", port.toString());
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "nome_database", dataBaseName);
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "username", userName);
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "password", userPassword);
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "radice_database", radix);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void readConfiguration(){
		try {
			versione = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "versione");
			hostName = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "database_host");
			port = Integer.valueOf(WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "porta"));
			dataBaseName = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "nome_database");
			userName = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "username");
			userPassword = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "password");
			radix = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Arvedi\\Cmms", "radice_database");
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}


