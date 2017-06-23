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
	
	public Controller(){
		con = connect("localhost","bd2","bd2","bd2");
		mod = new Model(con);
		mak = new Mark(con);
		tov = new TypeOfVehicle(con);
	}
	
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
	
}
