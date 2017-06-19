package db;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

public class TypeOfVehicle extends Table{
	List<String> types;
	
	TypeOfVehicle(Connection con){
		types = new Vector<String>();
	}
	
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con, "SELECT * FROM typ_pojazdu;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO typ_pojazdu "+
				"(opis_typ)" +
		        "VALUES ('"+opis_typ+"'); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from typ_pojazdu WHERE id_typ_pojazdu = "+typeID+";";
		update(con,sql);
	}
	
}
