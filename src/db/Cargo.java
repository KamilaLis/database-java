package db;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Cargo extends Table{

	static void addMember(Connection con, 
			int id_rodzaju,
			float waga,int id_historii){
		String sql = "INSERT INTO ladunek "+
			"(id_rodzaju,waga,id_historii)" +
	         "VALUES ("+id_rodzaju+","+waga+", "+id_historii+"); ";
		update(con,sql);
	}
	
	static void removeMember(Connection con, int cargoID){
		String sql = "DELETE from ladunek WHERE id_ladunku = "+cargoID+";";
		update(con,sql);
	}
	
	DefaultTableModel getTableCargo(Connection con){
		String sql = "SELECT * FROM ladunek;";
		DefaultTableModel table = getTable(con, sql); 
		return table;
	}
	
	static List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM ladunek;";
		return getTableAsList(con, sql); 
	}
	
	static String[] getCargoCarriedByTheLargestNumberOfKm(Connection con){
        //ładunek przewozony przez największą liczbę km
        String sql = "SELECT rl.opis_rodzaju" +
                ",sum(hp.liczba_km) AS max_liczba_km " +
                " FROM ladunek ld " +
                " LEFT JOIN historia_przejazdu hp ON hp.id_historii = ld.id_historii " +
                " LEFT JOIN rodzaj_ladunku rl ON ld.id_rodzaju = rl.id_rodzaju " +
                " GROUP BY rl.id_rodzaju " +
                " HAVING sum(hp.liczba_km)>=0 " +
                " ORDER BY sum(hp.liczba_km) DESC " +
                " LIMIT 1 ;";
        String[] elems = {"opis_rodzaju","max_liczba_km"};
        return ask(con, sql, elems);
    }
    
	static String[] getTypeOfCargoWithSmallestTransportCost(Connection con){
        //Jaki rodzaj ładunku ma najmniejszy koszt przewozu na 1km
        String sql = "SELECT rl.opis_rodzaju" +
                ",sum(cena_zuzytego_paliwa)/sum(liczba_km) AS max_koszt_przewozu " +
                " FROM ladunek ld " +
                " LEFT JOIN historia_przejazdu hp ON hp.id_historii = ld.id_historii " +
                " LEFT JOIN rodzaj_ladunku rl on ld.id_rodzaju = rl.id_rodzaju " +
                " GROUP BY rl.opis_rodzaju " +
                " HAVING sum(cena_zuzytego_paliwa)/sum(liczba_km)>=0 " +
                " ORDER BY 2 ASC " +
                " LIMIT 1 ;";
        String[] elems = {"opis_rodzaju","max_koszt_przewozu"};
        return ask(con, sql, elems);
    }

	static String[] getCargoWithMostPenaltyPoints(Connection con, Date from, Date to){
        //Przy przewozie jakiego łądunku w okresie od... do ... przyznano najwieksza liczbe punktow karnych
        String sql = "SELECT rl.opis_rodzaju" +
                ",sum(m.pkt_karne) AS max_punkty_karne " +
                " FROM ladunek ld " +
                " LEFT JOIN rodzaj_ladunku rl on ld.id_rodzaju = rl.id_rodzaju " +
                " LEFT JOIN historia_przejazdu hp ON hp.id_historii = ld.id_historii " +
                " LEFT JOIN mandat m ON hp.id_historii = m.id_historii  " +
                " and m.data > '" + from + "' and m.data < '" + to +
                "' GROUP BY rl.opis_rodzaju " +
                " HAVING sum(m.pkt_karne)>=0 " +
                " ORDER BY sum(m.pkt_karne) DESC " +
                " LIMIT 1 ;";
        String[] elems = {"opis_rodzaju","max_punkty_karne"};
        return ask(con, sql, elems);
    }
    
	static String[] getCargoWithLeastPenaltyPoints(Connection con, Date from, Date to){
        //Przy przewozie jakiego łądunku w okresie od... do ... przyznano najmniejszą liczbe punktow karnych
        String sql = "SELECT rl.opis_rodzaju" +
                ",sum(m.pkt_karne) AS min_punkty_karne " +
                " FROM ladunek ld " +
                " LEFT JOIN rodzaj_ladunku rl on ld.id_rodzaju = rl.id_rodzaju " +
                " LEFT JOIN historia_przejazdu hp ON hp.id_historii = ld.id_historii " +
                " LEFT JOIN mandat m ON hp.id_historii = m.id_historii  " +
                " and m.data > '" + from + "' and m.data < '" + to +
                "' GROUP BY rl.id_rodzaju " +
                " HAVING sum(m.pkt_karne)>=0 " +
                " ORDER BY sum(m.pkt_karne) ASC " +
                " LIMIT 1 ;";
        String[] elems = {"opis_rodzaju","min_punkty_karne"};
        return ask(con, sql, elems);
    }
}
