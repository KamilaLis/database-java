package db;
import java.sql.*;
import java.util.List;

interface ControlerInterface {
	
	void close();
	
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
    void addDriver(String name, String lastname, String pesel,
    		int id_zastepcy, int id_kat_prawa_jazdy, int id_pojazdu);
    void addFine(int id_kierowcy, java.sql.Date data,
			float oplata, int pkt_karne, int id_historii);
    void addHistory(int id_kierowcy, int id_pojazdu, String miejsce_startu,
			String miejsce_docelowe, float liczba_km,
			float srednie_zuzycie_paliwa,float cena_zużytego_paliwa,
			java.sql.Date data, boolean przyczepa, int id_przyczepy );
    void addMark(String opis_typ);
    void addModel(String opis_typ);
    void addPartsOfCar(String opis_typ);
    void addServicing(int id_serwisu,int id_przejazdu,
    		java.sql.Date data,int id_przedmiotu, int id_czynnosci,
			String miejsce_serwisu, float cena);
    void addTrailer(int id_marki, int id_modelu, String nr_rejestracyjny);
    void addTypeOfCargo(String opis_typ);
    void addTypeOfConsumable(String opis_typ);
    void addTypeOfDrivingLicence(String opis_typ);
    void addTypeOfVehicle(String opis_typ);
    void addVehicle(int id_typ_pojazdu, int id_marki, int id_modelu,
    		int id_kat_prawa_jazdy, float przebieg, String rodz_paliwa,
    		String nr_rejestracyjny);
    
    void removeCargo(int cargoID);
    void removeDriver(int driverID);
    void removeFine(int fineID);
    void removeHistory(int historyID);
    void removeMark(int markID);
    void removeModel(int modelID);
    void removePartsOfCar(int partsOfCarID);
    void removeServicing(int servicingID);
    void removeTrailer(int trailerID);
    void removeTypeOfCargo(int typeOfCargoID);
    void removeTypeOfConsumable(int typeOfConsumableID);
    void removeTypeOfDrivingLicence(int typeOfDrivingLicenceID);
    void removeTypeOfVehicle(int typeOfVehicleID);
    void removeVehicle(int vehicleID);
}
