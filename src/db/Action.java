package db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Action extends Table{
	List<String> types;
	Map<Integer,String> map;
	
	Action(Connection con){
		types = new Vector<String>();
		map = new HashMap<Integer,String>();
	}
	
	Map<Integer,String> getMap(Connection con){
		List<String[]> response = getTableAsList(con, "SELECT * FROM czynnosc;");
        for( String[] row: response ){
			map.put(Integer.parseInt(row[0]), row[1]);
        }
        return map;
	}
	//static 
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con, "SELECT * FROM czynnosc;");
		for( String[] row: response ){
			types.add(row[1]);
		}
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO czynnosc "+
				"(opis_czynnosci)" +
		        "VALUES ('"+opis_typ+"'); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from czynnosc WHERE id_czynnosci = "+typeID+";";
		update(con,sql);
	}
}
