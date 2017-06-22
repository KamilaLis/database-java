package db;
import java.sql.*;
import java.util.List;

interface ControlerInterface {
	
	String[] getTypeOfVehicleTable();
	
	List<String> getTypeOfDrivingLicenceTable();
	List<String> getTypeOfConsumableTable();
	List<String> getTypeOfCargoeTable();
	
	List<String[]> getTrailerTable();
	List<String[]> getServicingTable();
	List<String[]> getCargoTable();
	List<String[]> getVehicleTable();
	List<String[]> getHistoryTable();
	List<String[]> getFineTable();
	List<String[]> getDriverTable();
	
	List<String> getPartsOfCarTable();
	List<String> getActionTable();

	String[] getModelTable();//List<String>
	String[] getMarkTable();
	String[] getMostExpensiveMaintenance(Date from, Date to, String type);
	String[] getVehicleWithMaxFuelConsumption();
	String[] getDriverWithMaxKm(Date from, Date to);
	String[] getDriverWithMinKm(Date from, Date to);
	String[] getDriverWithCheapestCarForServicing(Date from, Date to);
	String[] getDriverWithTheMostExpensiveMaterials();
	String[] getDriverWithMostPenaltyPoints(Date from, Date to);
	String[] getDriverWithLeastPenaltyPoints(Date from, Date to);
	String[] getDriverWithWrongDrivingLicense();
	String[] getCargoCarriedByTheLargestNumberOfKm();
    String[] getTypeOfCargoWithSmallestTransportCost();
    String[] getCargoWithMostPenaltyPoints(Date from, Date to);
    String[] getCargoWithLeastPenaltyPoints(Date from, Date to);
    String[] getMaterialsIssuedTheMost(Date from, Date to);
    String[] getMaterialsIssuedAtLeast(Date from, Date to);
    String[] getDriverWithMostExpensiveCarForServicing(Date from, Date to);
    
    void addCargo(int id_rodzaju, float waga, int id_historii);
}
