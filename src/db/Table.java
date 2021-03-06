package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Table {
	static void update(Connection con,String sql){
		// Realizacja zapytan typu: INSERT, CREATE...
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			try {
				con.close();
			} catch (SQLException r) {
				e.printStackTrace();
				System.err.println(r.getClass().getName()
						+": "+r.getMessage());
			}
			System.out.println("Connection with database closed successfully");
			System.exit(0);
		}
	}
	
	static String[] ask(Connection con, String query, String[] names){
        // Realizacja zapytan typu: SELECT
        Statement stmt = null;
        String[] o = new String[names.length];
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                for (int i=0; i<names.length;++i){
                    String some_string = rs.getString(names[i]);
                    o[i] = some_string.replaceAll("\\s+"," ");
                    System.out.println(o[i]);
                }
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            try {
				con.close();
			} catch (SQLException r) {
				e.printStackTrace();
				System.err.println(r.getClass().getName()
						+": "+r.getMessage());
			}
			System.out.println("Connection with database closed successfully");
            System.exit(0);
        }
        return o;
    }
	
	static DefaultTableModel getTable(Connection con, String sql){
		Statement stmt = null;
		DefaultTableModel model = new DefaultTableModel();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNumber=rsmd.getColumnCount();
			while ( rs.next() ) {
				Object[] row = new Object[columnNumber];
				for (int col=1; col<columnNumber+1; ++col){
					row[col - 1] = rs.getString(col); // Or even rs.getObject()
				}
				model.addRow(row);
			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			try {
				con.close();
			} catch (SQLException r) {
				e.printStackTrace();
				System.err.println(r.getClass().getName()
						+": "+r.getMessage());
			}
			System.out.println("Connection with database closed successfully");
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return model;
	}
	
	static List<String[]> getTableAsList(Connection con, String sql){
		Statement stmt = null;
		List<String[]> table = new ArrayList<>();
	      try {
	         stmt = con.createStatement();
	         ResultSet result = stmt.executeQuery(sql);
	         int nCol = result.getMetaData().getColumnCount();
	         
	         while( result.next()) {
	             String[] row = new String[nCol];
	             for( int iCol = 1; iCol <= nCol; iCol++ ){
	                     Object obj = result.getObject( iCol );
	                     row[iCol-1] = (obj == null) ?null:obj.toString();
	             }
	             table.add( row );
	         }
	         result.close();
	         stmt.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         try {
					con.close();
				} catch (SQLException r) {
					e.printStackTrace();
					System.err.println(r.getClass().getName()
							+": "+r.getMessage());
				}
				System.out.println("Connection with database closed successfully");
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	      return table;
	}
	
}
