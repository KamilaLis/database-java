package db;

import java.sql.Connection;
import java.util.List;

public class Trailer extends Table{

	void addMember(Connection con, int id_przyczepy, int id_marki,
			int id_modelu, String nr_rejestracyjny ){
		String sql = "INSERT INTO przyczepa "+
			"(id_przyczepy,id_marki," +
			"id_modelu, nr_rejestracyjny)"
	        + "VALUES ("+id_przyczepy+", "+id_marki+", "+id_modelu+", "
				+nr_rejestracyjny+" );";
		update(con,sql);
	}
	
	void removeMember(Connection con, int trailerID){
		String sql = "DELETE from przyczepa WHERE id_pojazdu = "+trailerID+";";
		update(con,sql);
	}
	
	List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM przyczepa;";
		return getTableAsList(con, sql); 
	}
}
