package db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TypeOfCargo extends Table{
	List<String> types;
	Map<Integer,String> map;
	
	TypeOfCargo(Connection con){
		types = new Vector<String>();
		map = new HashMap<Integer,String>();
	}
	
	Map<Integer,String> getMap(Connection con){
		List<String[]> response = getTableAsList(con, "SELECT * FROM rodzaj_ladunku;");
        for( String[] row: response ){
			map.put(Integer.parseInt(row[0]), row[1]);
        }
        return map;
	}
	
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con, "SELECT * FROM rodzaj_ladunku;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO rodzaj_ladunku "+
				"(opis_rodzaju)" +
		        "VALUES ('"+opis_typ+"'); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from rodzaj_ladunku WHERE id_rodzaju = "+typeID+";";
		update(con,sql);
	}
}
