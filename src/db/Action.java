package db;

import java.sql.Connection;
import java.util.List;

public class Action extends Table{
	List<String> types;
	
	Action(Connection con){
		getTable(con);
	}
	
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
		        "VALUES ("+opis_typ+"); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from czynnosc WHERE id_czynnosci = "+typeID+";";
		update(con,sql);
	}
}
