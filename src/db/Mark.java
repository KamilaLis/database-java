package db;

import java.sql.Connection;
import java.util.List;

public class Mark extends Table{
	List<String> types;
	
	Mark(Connection con){
		getTable(con);
	}
	
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con, "SELECT * FROM marka;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO marka "+
				"(opis_marki)" +
		        "VALUES ("+opis_typ+"); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from marka WHERE id_marki = "+typeID+";";
		update(con,sql);
	}
}
