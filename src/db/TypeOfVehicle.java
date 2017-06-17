package db;

import java.sql.Connection;
import java.util.List;

public class TypeOfVehicle {
	Manager m;
	List<String> types;
	
	TypeOfVehicle(Connection con){
		this.m = new Manager();
		getTypes(con);
	}
	
	List<String> getTypes(Connection con){
		//Pobierz typy z bazy danych
		List<String[]> response = m.getTableAsList(con, "SELECT * FROM typ_pojazdu;");
        for( String[] row: response ){
			types.add(row[1]);
        }
        return types;
	}
	
	void addMember(){
		
	}
	
	void removeMember(){
		
	}
	
}
