package db;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class Consumables extends Table{

	void addMember(Connection con, int id_rodzaj_materialu,
			float cena, Date data_zakupu, 
			float wielkosc){
		String sql = "INSERT INTO materialy_eksploatacyjne "+
			"(id_rodzaj_materialu," +
			"cena,data_zakupu," +
			"wielkosc)"
	        + "VALUES ("+id_rodzaj_materialu+", "+ cena+", "+data_zakupu+", "
				+wielkosc+" );";
		update(con,sql);
	}
	
	void removeMember(Connection con, int ID){
		String sql = "DELETE from materialy_eksploatacyjne WHERE id_materialu = "+ID+";";
		update(con,sql);
	}
	
	List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM materialy_eksploatacyjne;";
		return getTableAsList(con, sql); 
	}
}
