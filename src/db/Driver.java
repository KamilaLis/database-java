package db;
import java.sql.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Driver extends Table{
	
	DefaultTableModel getTableDriver(Connection con){
		String sql = "SELECT * FROM kierowca;";
		DefaultTableModel table = getTable(con, sql); 
		//nie jestem pewna czy sie dobrze tworzy
		//do wykorzystania przez JTable
		//table.setModel(model);
		return table;
	}
	
	static List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM kierowca;";
		return getTableAsList(con, sql); 
	}
	
	static void addMember(Connection con, String name, String lastname,
			String pesel,int  id_zastepcy, int id_kat_prawa_jazdy, int id_pojazdu){
		String sql = "INSERT INTO kierowca (imie,nazwisko,PESEL,id_zastepcy,id_kat_prawa_jazdy,id_pojazdu)"
	            + "VALUES ('"+name+"', '"+lastname+"', '"+pesel+"', "+id_zastepcy+","+id_kat_prawa_jazdy+","+
	            id_pojazdu+" );";
		update(con,sql);
	}
	
	static void removeMember(Connection con, int driverID){
		String sql = "DELETE from kierowca where id_kierowcy = "+driverID+";";
		update(con,sql);
	}
	
	static String[] getDriverWithMaxKm(Connection con,java.sql.Date from, java.sql.Date to){
		//kierowca, ktory przejechal najwiecej
		String sql ="SELECT kr.id_kierowcy"+
					",kr.imie"+
					",kr.nazwisko"+
					",sum(hp.liczba_km) AS max_liczba_km"+
					" FROM kierowca kr"+
					" LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy"+
					" and hp.data > '" + from + "' and hp.data < '" + to +
					"' GROUP BY kr.id_kierowcy "+
					" HAVING sum(hp.liczba_km)>=0 " +
					" ORDER BY sum(hp.liczba_km) DESC "+
					" LIMIT 1 ;";
		String[] elems = {"id_kierowcy","imie","nazwisko",
				"max_liczba_km"};
		String[] top = ask(con, sql, elems);
		//System.out.print(top[0]+" "+top[1]);
		return top;
	}
	
	static String[] getDriverWithMinKm(Connection con, Date from, Date to){
		//kierowca, ktory przejechał najmniej
		String sql = "SELECT kr.id_kierowcy"+
				   ",kr.imie"+
				   ",kr.nazwisko"+
				   ",sum(hp.liczba_km) AS min_liczba_km"+
				" FROM kierowca kr"+
				" LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy"+
				" and hp.data > '" + from + "' and hp.data < '" + to + 
				"' GROUP BY kr.id_kierowcy "+
				" HAVING sum(hp.liczba_km)>=0 " +
				" ORDER BY sum(hp.liczba_km) ASC "+
				" LIMIT 1 ;";
		String[] elems = {"id_kierowcy","imie","nazwisko",
		"min_liczba_km"};
		return ask(con, sql, elems);
	}
	
	static String[] getDriverWithCheapestCarForServicing(Connection con, Date from, Date to){
		//Kierowca z najtanszym samochodem do serwisowania w danym czasie
		//TODO: w jakim formacie przedstawiamy date?
		String sql = "SELECT kr.imie" +
				",kr.nazwisko" +
				",sum(sw.cena) AS min_cena " +
				" FROM kierowca kr  " +
				" LEFT JOIN pojazd pj ON kr.id_pojazdu = pj.id_pojazdu " +
				" LEFT JOIN serwis sw ON pj.id_pojazdu = sw.id_pojazdu " +
				" WHERE sw.data> '" + from + "' and sw.data< '" + to + 
				"' GROUP BY kr.id_kierowcy " +
				" HAVING sum(sw.cena)>=0 " +
				" ORDER BY sum(sw.cena) ASC " +
				" LIMIT 1 ;";
		String[] elems = {"imie","nazwisko","min_cena"};
		return ask(con, sql, elems);
	}
	
	static String[] getDriverWithMostExpensiveCarForServicing(Connection con, Date from, Date to){
        //Kierowca z najtanszym samochodem do serwisowania w danym czasie
        String sql = "SELECT kr.imie" +
                ",kr.nazwisko" +
                ",sum(sw.cena) AS max_cena " +
                " FROM kierowca kr  " +
                " LEFT JOIN pojazd pj ON kr.id_pojazdu = pj.id_pojazdu " +
                " LEFT JOIN serwis sw ON pj.id_pojazdu = sw.id_pojazdu " +
                " WHERE sw.data> '" + from + "' and sw.data< '" + to +
                "' GROUP BY kr.id_kierowcy " +
                " HAVING sum(sw.cena)>=0 " +
                " ORDER BY sum(sw.cena) DESC " +
                " LIMIT 1 ;";
        String[] elems = {"imie","nazwisko","max_cena"};
        return ask(con, sql, elems);
    }
	
	static String[] getDriverWithTheMostExpensiveMaterials(Connection con){
		//kierowca z najdroższym zużyciem materiałów
		String sql = "SELECT kr.imie " +
				",kr.nazwisko " +
				",sum(hp.cena_zuzytego_paliwa) AS max_cena_paliwa " +
				"FROM kierowca kr  " +
				"LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy " +
				"GROUP BY kr.id_kierowcy " +
				"HAVING sum(hp.cena_zuzytego_paliwa)>=0 " +
				"ORDER BY sum(hp.cena_zuzytego_paliwa) DESC " +
				"LIMIT 1 ;";
		String[] elems = {"imie","nazwisko","max_cena_paliwa"};
		return ask(con, sql, elems);
	}
	
	static String[] getDriverWithMostPenaltyPoints(Connection con, Date from, Date to){
		//Kto ma najwięcej punktów karnych przyznanych w okresie od ... do...
		String sql = "SELECT kr.id_kierowcy " +
				",kr.imie " +
				",kr.nazwisko " +
				",sum(m.pkt_karne) AS max_punkty_karne " +
				" FROM kierowca kr " +
				" LEFT JOIN mandat m ON kr.id_kierowcy = m.id_kierowcy " +
				" and m.data > '" + from + "' and m.data < '" + to + 
				"' GROUP BY kr.id_kierowcy " +
				" HAVING sum(m.pkt_karne)>=0 " +
				" ORDER BY sum(m.pkt_karne) DESC " +
				" LIMIT 1 ;";
		String[] elems = {"id_kierowcy","imie","nazwisko","max_punkty_karne"};
		return ask(con, sql, elems);
	}
	
	static String[] getDriverWithLeastPenaltyPoints(Connection con, Date from, Date to){
		//Kto ma najmniej punktów karnych przyznanych w okresie od ... do...
		String sql = "SELECT kr.id_kierowcy " +
				",kr.imie " +
				",kr.nazwisko " +
				",sum(m.pkt_karne) AS max_punkty_karne " +
				" FROM kierowca kr " +
				" LEFT JOIN mandat m ON kr.id_kierowcy = m.id_kierowcy " +
				" and m.data > '" + from + "' and m.data < '" + to + 
				"' GROUP BY kr.id_kierowcy " +
				" HAVING sum(m.pkt_karne)>=0 " +
				" ORDER BY sum(m.pkt_karne) ASC " +
				" LIMIT 1 ;";
		String[] elems = {"id_kierowcy","imie","nazwisko","max_punkty_karne"};
		return ask(con, sql, elems);
	}
	
	static String[] getDriverWithWrongDrivingLicense(Connection con){
		//Samochody ciezarowe bez uprawnien
		//(na razie wszystkie, w razie czego można ograniczyć tylko do ciezarowych)
		String sql = "SELECT kr.imie" +
				",kr.nazwisko" +
				" FROM kierowca kr " +
				" LEFT JOIN pojazd pj ON kr.id_pojazdu = pj.id_pojazdu" +
				" WHERE (pj.id_kat_prawa_jazdy = 12 or pj.id_kat_prawa_jazdy = 13) AND kr.id_kat_prawa_jazdy <> 12  AND kr.id_kat_prawa_jazdy <> 13;";
		String[] elems = {"imie","nazwisko"};
		return ask(con, sql, elems);
	}
	
	
   /* public static void main(String[] args) {
    	//Podane argumenty to: host, uzytkownik, nazwa bazy, haslo
     //   Manager m = new Manager();
        Connection con = m.connect("localhost","bd2","postgres","bd2");
        //Driver d = new Driver();
        //d.addMember(con, "jan", "kowalski", "00000000000", 1, 1, 1);
        //d.removeMember(con, 1);
        //d.getTable(con);
        //String[] elems = {"login","password"};
        //DefaultTableModel users = m.getTable(con, "SELECT * FROM users_info;");
       // Table t = new Table();
        /*List<String[]> response = (new Vehicle()).getTable(con);
        for( String[] row: response ){
            for( String s: row ){
                System.out.print( " " + s );
            }
            System.out.println();
        }
        List<String> dict = (new Action(con)).getTable(con);
        for(String row : dict){
        	System.out.print( " " + row );
        }
        System.out.println();
        //System.out.println(response.get(0)[1]);
        //users.setColumnIdentifiers(new String[] {"userID", "Value"});
        //System.out.println(users.getColumnName(0));
        //m.ask(con, "SELECT login,password FROM users_info WHERE user_id=3;",elems);
        m.closeConnection(con);
    } */
	
}
