package db;

import java.sql.*;
import java.util.List;

class Controller extends Manager implements ControlerInterface{
	
	private Connection con;
	private Action act;
	private Model mod;
	private Mark mak;
	private PartsOfCar poc;
	private TypeOfCargo toc;
	private TypeOfConsumable tocon;
	private TypeOfDrivingLicence todl;
	private TypeOfVehicle tov;
	
	public Controller(String[] args){
		con = connect(args[0],args[1],args[2],args[3]);
		//con = connect("localhost","bd2","bd2","bd2");
		mod = new Model(con);
		mak = new Mark(con);
		tov = new TypeOfVehicle(con);
		toc = new TypeOfCargo(con);
		poc = new PartsOfCar(con);
		tocon = new TypeOfConsumable(con);
		todl = new TypeOfDrivingLicence(con);
	}
	
	@Override
	public void close(){
		this.closeConnection(con);
	}

	@Override
	public List<String[]> getCargoTable() {
		return Cargo.getTable(con);
	}

	@Override
	public String[] getModelTable() {
		List<String> temp = mod.getTable(con);
		return temp.toArray(new String[temp.size()]);
	}
	
	@Override
	public String[] getMarkTable() {
		List<String> temp = mak.getTable(con);
		return temp.toArray(new String[temp.size()]);
	}

	@Override
	public String[] getTypeOfVehicleTable() {
		List<String> temp = tov.getTable(con);
		return temp.toArray(new String[temp.size()]);
	}

	@Override
	public List<String> getTypeOfDrivingLicenceTable() {
		return todl.getTable(con);
	}

	@Override
	public List<String> getTypeOfConsumableTable() {
		return tocon.getTable(con);
	}

	@Override
	public List<String> getTypeOfCargoeTable() {
		return toc.getTable(con);
	}

	@Override
	public List<String[]> getTrailerTable() {
		return Trailer.getTable(con);
	}

	@Override
	public List<String[]> getServicingTable() {
		return Servicing.getTable(con);
	}

	@Override
	public List<String[]> getVehicleTable() {
		return Vehicle.getTable(con);
	}

	@Override
	public List<String> getPartsOfCarTable() {
		return poc.getTable(con);
	}

	@Override
	public List<String[]> getHistoryTable() {
		return History.getTable(con);
	}

	@Override
	public List<String[]> getFineTable() {
		return Fine.getTable(con);
	}

	@Override
	public List<String[]> getDriverTable() {
		return Driver.getTable(con);
	}

	@Override
	public List<String> getActionTable() {
		return act.getTable(con);
	}

	@Override
	public String[] getMostExpensiveMaintenance(Date from, Date to, String type) {
		return Servicing.getMostExpensiveMaintenance(con, from, to, type);
	}

	@Override
	public String[] getVehicleWithMaxFuelConsumption() {
		return Vehicle.getVehicleWithMaxFuelConsumption(con);
	}

	@Override
	public String[] getDriverWithMaxKm(Date from, Date to) {
		return Driver.getDriverWithMaxKm(con, from, to);
	}

	@Override
	public String[] getDriverWithMinKm(Date from, Date to) {
		return Driver.getDriverWithMinKm(con, from, to);
	}

	@Override
	public String[] getDriverWithCheapestCarForServicing(Date from, Date to) {
		return Driver.getDriverWithCheapestCarForServicing(con, from, to);
	}
	@Override
	public String[] getDriverWithMostExpensiveCarForServicing(Date from, Date to) {
		return Driver.getDriverWithMostExpensiveCarForServicing(con, from, to);
	}
	@Override
	public String[] getDriverWithTheMostExpensiveMaterials() {
		return Driver.getDriverWithTheMostExpensiveMaterials(con);
	}

	@Override
	public String[] getDriverWithMostPenaltyPoints(Date from, Date to) {
		return Driver.getDriverWithMostPenaltyPoints(con, from, to);
	}

	@Override
	public String[] getDriverWithLeastPenaltyPoints(Date from, Date to) {
		return Driver.getDriverWithLeastPenaltyPoints(con, from, to);
	}

	@Override
	public String[] getDriverWithWrongDrivingLicense() {	
		return Driver.getDriverWithWrongDrivingLicense(con);
	}

	@Override
	public String[] getCargoCarriedByTheLargestNumberOfKm() {
		return Cargo.getCargoCarriedByTheLargestNumberOfKm(con);
	}

	@Override
	public String[] getTypeOfCargoWithSmallestTransportCost() {
		return Cargo.getTypeOfCargoWithSmallestTransportCost(con);
	}

	@Override
	public String[] getCargoWithMostPenaltyPoints(Date from, Date to) {
		return Cargo.getCargoWithMostPenaltyPoints(con, from, to);
	}

	@Override
	public String[] getCargoWithLeastPenaltyPoints(Date from, Date to) {
		return Cargo.getCargoWithLeastPenaltyPoints(con, from, to);
	}

	@Override
	public String[] getMaterialsIssuedTheMost(Date from, Date to) {
		return Consumables.getMaterialsIssuedTheMost(con, from, to);
	}

	@Override
	public String[] getMaterialsIssuedAtLeast(Date from, Date to) {
		return Consumables.getMaterialsIssuedAtLeast(con, from, to);
	}
	
	@Override
	public void addCargo(int id_rodzaju, float waga, int id_historii){
		Cargo.addMember(con, id_rodzaju, waga, id_historii);
	}
	
	@Override
	public void addDriver(String name, String lastname, String pesel,
    		int id_zastepcy, int id_kat_prawa_jazdy, int id_pojazdu){
		Driver.addMember(con, name, lastname, pesel,
	    		id_zastepcy, id_kat_prawa_jazdy, id_pojazdu);
	}
	
	@Override
	public void addFine(int id_kierowcy, java.sql.Date data,
			float oplata, int pkt_karne, int id_historii){
		Fine.addMember(con, id_kierowcy, data, oplata, pkt_karne, id_historii);
	}
	
	@Override
	public void addHistory(int id_kierowcy, int id_pojazdu, String miejsce_startu,
			String miejsce_docelowe, float liczba_km,
			float srednie_zuzycie_paliwa,float cena_zużytego_paliwa,
			java.sql.Date data, boolean przyczepa, int id_przyczepy ){
		History.addMember(con, id_kierowcy, id_pojazdu, miejsce_startu,
				miejsce_docelowe, liczba_km, srednie_zuzycie_paliwa, 
				cena_zużytego_paliwa, data, przyczepa, id_przyczepy);
	}
	
	@Override
	public void addMark(String opis_typ){
		mak.addMember(con, opis_typ);
	}
	
	@Override
	public void addModel(String opis_typ){
		mod.addMember(con, opis_typ);
	}
	
	@Override
	public void addPartsOfCar(String opis_typ){
		poc.addMember(con, opis_typ);
	}
	
	@Override
	public void addServicing(int id_serwisu,int id_przejazdu,
			Date data,int id_przedmiotu, int id_czynnosci,
			String miejsce_serwisu, float cena){
		Servicing.addMember(con, id_serwisu, id_przejazdu, 
				data, id_przedmiotu, id_czynnosci, miejsce_serwisu, cena);
	}
	
	@Override
	public void addTrailer(int id_marki, int id_modelu, String nr_rejestracyjny){
		Trailer.addMember(con, id_marki, id_modelu, nr_rejestracyjny);
	}
	
	@Override
	public void addTypeOfCargo(String opis_typ){
		toc.addMember(con, opis_typ);
	}
	
	@Override
	public void addTypeOfConsumable(String opis_typ){
		tocon.addMember(con, opis_typ);
	}
	
	@Override
	public void addTypeOfDrivingLicence(String opis_typ){
		todl.addMember(con, opis_typ);
	}
	
	@Override
	public void addTypeOfVehicle(String opis_typ){
		tov.addMember(con, opis_typ);
	}
	
	@Override
	public void removeCargo(int cargoID){
		Cargo.removeMember(con, cargoID);
	}

	@Override
	public void removeDriver(int driverID) {
		Driver.removeMember(con, driverID);
		
	}

	@Override
	public void removeFine(int fineID) {
		Fine.removeMember(con, fineID);
	}

	@Override
	public void removeHistory(int historyID) {
		History.removeMember(con, historyID);
	}

	@Override
	public void removeMark(int markID) {
		mak.removeMember(con, markID);
	}
	
	@Override
	public void removeModel(int modelID) {
		mod.removeMember(con, modelID);
	}

	@Override
	public void removePartsOfCar(int partsOfCarID) {
		poc.removeMember(con, partsOfCarID);
	}
	
	@Override
	public void removeServicing(int servicingID) {
		Servicing.removeMember(con, servicingID);
	}

	@Override
	public void removeTrailer(int trailerID) {
		Trailer.removeMember(con, trailerID);
	}

	@Override
	public void removeTypeOfCargo(int typeOfCargoID) {
		toc.removeMember(con, typeOfCargoID);
	}

	@Override
	public void removeTypeOfConsumable(int typeOfConsumableID) {
		tocon.removeMember(con, typeOfConsumableID);
	}
	
	@Override
	public void removeTypeOfDrivingLicence(int typeOfDrivingLicenceID) {
		todl.removeMember(con, typeOfDrivingLicenceID);
	}

	@Override
	public void removeTypeOfVehicle(int typeOfVehicleID) {
		tov.removeMember(con, typeOfVehicleID);
	}

	@Override
	public void addVehicle(int id_typ_pojazdu, int id_marki, int id_modelu,
			int id_kat_prawa_jazdy, float przebieg,
			String rodz_paliwa, String nr_rejestracyjny) {
		Vehicle.addMember(con, id_typ_pojazdu, id_marki, id_modelu, 
				id_kat_prawa_jazdy, przebieg, rodz_paliwa, nr_rejestracyjny);
	}

	@Override
	public void removeVehicle(int vehicleID) {
		Vehicle.removeMember(con, vehicleID);
	}
}
