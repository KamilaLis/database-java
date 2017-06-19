package db;

import java.sql.Connection;
import java.util.List;

public class PartsOfCar extends Table{
	List<String> types;
	
	PartsOfCar(Connection con){
		getTable(con);
	}
	
	List<String> getTable(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = getTableAsList(con, "SELECT * FROM czesc_samochodowa;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(Connection con, String opis_typ){
		types.add(opis_typ);
		String sql = "INSERT INTO czesc_samochodowa "+
				"(opis_czesci)" +
		        "VALUES ('"+opis_typ+"'); ";
		update(con,sql);
		
	}
	
	void removeMember(Connection con, int typeID){
		String sql = "DELETE from czesc_samochodowa WHERE id_czesci = "+typeID+";";
		update(con,sql);
	}
}
