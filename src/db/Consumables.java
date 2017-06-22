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
			"litry)"
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
	
	static String[] getMaterialsIssuedTheMost(Connection con, Date from, Date to){
		//Materiały na ktore wydano najwiecej
		String sql = "SELECT rm.opis_rodzaju" +
				", sum(me.cena) AS max_cena_mat " +
				" FROM materialy_eksploatacyjne me " +
				" LEFT JOIN rodzaj_materialu rm " +
				" ON me.id_rodzaj_materialu = rm.id_rodzaj_materialu" +
				" WHERE me.data_zakupu> '"+ from + "' and me.data_zakupu< '" + to +
				"' GROUP BY rm.id_rodzaj_materialu" +
				" HAVING sum(me.cena)>=0 " +
				" ORDER BY sum(me.cena) DESC" +
				" LIMIT 1 ;";
		String[] elems = {"opis_rodzaj","max_cena_mat"};
		return ask(con, sql, elems);
	}


	static String[] getMaterialsIssuedAtLeast(Connection con, Date from, Date to){
		//Materiały na ktore wydano najmniej
		String sql = "SELECT rm.opis_rodzaju" +
				", sum(me.cena) AS min_cena_mat " +
				" FROM materialy_eksploatacyjne me" +
				" LEFT JOIN rodzaj_materialu rm " +
				" ON me.id_rodzaj_materialu = rm.id_rodzaj_materialu" +
				" WHERE me.data_zakupu> '"+ from + "' and me.data_zakupu< '" + to +
				"' GROUP BY rm.id_rodzaj_materialu" +
				" HAVING sum(me.cena)>=0 " +
				" ORDER BY sum(me.cena) ASC" +
				" LIMIT 1 ;";
		String[] elems = {"opis_rodzaj","min_cena_mat"};
		return ask(con, sql, elems);
	}
}
