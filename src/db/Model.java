package db;

import java.sql.Connection;
import java.util.List;

public class Model extends Table{
	List<String> types;
	
	Model(Connection con){
		getTable(con);
	}
	
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con, "SELECT * FROM model;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO model "+
				"(opis_modelu)" +
		        "VALUES ("+opis_typ+"); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from model WHERE id_modelu = "+typeID+";";
		update(con,sql);
	}
}
