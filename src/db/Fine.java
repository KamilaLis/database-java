package db;

import java.sql.Connection;

public class Fine extends Manager{

	void addMember(Connection con, int id_kierowcy, String data,
			float oplata, int pkt_karne, int id_historii){
		String sql = "INSERT INTO mandat "+
			"(id_kierowcy,data,oplata,pkt_karne,id_historii)"
	        + "VALUES ("+id_kierowcy+", "+data+", "+oplata+", "
				+pkt_karne+","+id_historii+" );";
		update(con,sql);
	}
	
	void removeMember(Connection con, int fineID){
		String sql = "DELETE from mandat WHERE id_mandatu = "+fineID+";";
		update(con,sql);
	}
	
}
