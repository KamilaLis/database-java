package db;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

public class TypeOfConsumable extends Table{
	List<String> types;
	
	TypeOfConsumable(Connection con){
		types = new Vector<String>();
	}
	
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con,
				"SELECT * FROM rodzaj_materialu;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO rodzaj_materialu "+
				"(opis_rodzaju)" +
		        "VALUES ('"+opis_typ+"'); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from rodzaj_materialu WHERE id_rodzaj_materialu = "+typeID+";";
		update(con,sql);
	}
}
