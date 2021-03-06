package db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TypeOfDrivingLicence extends Table{
	List<String> types;
	Map<Integer,String> map;
	
	TypeOfDrivingLicence(Connection con){
		types = new Vector<String>();
		map = new HashMap<Integer,String>();
	}
	
	Map<Integer,String> getMap(Connection con){
		List<String[]> response = getTableAsList(con, "SELECT * FROM kategoria_prawa_jazdy;");
        for( String[] row: response ){
			map.put(Integer.parseInt(row[0]), row[1]);
        }
        return map;
	}
	
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con, "SELECT * FROM kategoria_prawa_jazdy;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO kategoria_prawa_jazdy "+
				"(opis_kat)" +
		        "VALUES ('"+opis_typ+"'); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from kategoria_prawa_jazdy WHERE id_kat_prawa_jazdy = "+typeID+";";
		update(con,sql);
	}
}
