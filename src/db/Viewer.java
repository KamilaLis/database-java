package db;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

public class Viewer implements WindowListener{
	
	public enum QueryType{
	DATE_FROM_TO, DATE_YEAR, CAR_TYPE_AND_YEAR, NONE;}
	
	public enum BoxType{
	QUERRY, YEAR, MONTH, DAY, CAR_TYPE;}
	
	private ControlerInterface Controler;
	
	public Viewer(ControlerInterface C){
		this.Controler = C;
	}
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Car Company Data Base");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(this);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(new Dimension(screensize.width/2,screensize.height/2));
		frame.setLocation(screensize.width/4, screensize.height/4);
		JTabbedPane tp=new JTabbedPane();
		tp.setBounds(50,50,200,200);  
		tp.add("zapytania",new GUIQueryPanel(screensize.width/2, screensize.height/2));  
		tp.add("dodawanie",new GUIAddPanel(screensize.width/2, screensize.height/2));  
		tp.add("usuwanie",new GUIRemovePanel(screensize.width/2, screensize.height/2));  
		tp.add("wypisywanie",new GUITablePanel(screensize.width/2, screensize.height/2));  
		//GUIQueryPanel gui = new GUIQueryPanel(screensize.width/2, screensize.height/2);
		//frame.getContentPane().add(gui, BorderLayout.CENTER);
		frame.getContentPane().add(tp);
		frame.setVisible(true);
	}
	
	
	private class GUIRemovePanel extends JPanel implements ActionListener{
		private BorderLayout layout;
		private JPanel[] panels;
		JButton button;
		private final String[] properties = { "Cargo", "Kierowca", "Mandat", 
				"Historia", "Marka", "Model", "Parts of car", "Serwisowanie",
				"Przyczepa", "Typ cargo", "Typ materiału eksploatacyjnego",
				"Typ prawa jazdy", "Typ pojazdu", "Pojazd"
		};

		public GUIRemovePanel(int width, int height){
			layout = new BorderLayout();
	        this.setLayout(layout);
	        this.setSize(width, height);
	        button = new JButton("Submit");
	        panels = new JPanel[3];
	        panels[0]= new JPanel();
	        panels[1]= new JPanel();
	        panels[2]= new JPanel();
	        this.add(panels[0],BorderLayout.NORTH);
	        this.add(panels[1],BorderLayout.CENTER);
	        this.add(panels[2],BorderLayout.SOUTH);
			JComboBox propertyBox = new JComboBox(properties);
			propertyBox.setSize(100,20);
			propertyBox.addActionListener(this);
			JLabel jl = new JLabel("Wybierz jaki obiekt chcesz usunąć:");
			panels[0].add(jl);
			panels[0].add(propertyBox);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(button)){
				JComboBox temp  = (JComboBox)panels[0].getComponent(1);
				String property = (String)temp.getSelectedItem();
				removeMember(property);
				showInputBox(property);
			} else {
				JComboBox eventSource = (JComboBox)e.getSource();
				String property = (String)eventSource.getSelectedItem();
				showInputBox(property);
			}
		}
		
		private void removeMember(String property){
			JTextField temp = (JTextField)panels[1].getComponent(1);
			int id= Integer.parseInt(temp.getText());
			if(property == "Cargo"){
				Viewer.this.Controler.removeCargo(id);
			}else if (property == "Kierowca"){
				Viewer.this.Controler.removeDriver(id);
			}else if (property == "Mandat"){
				Viewer.this.Controler.removeFine(id);
			}else if (property == "Historia"){
				Viewer.this.Controler.removeHistory(id);
			}else if (property == "Marka"){
				Viewer.this.Controler.removeMark(id);
			}else if (property == "Model"){
				Viewer.this.Controler.removeModel(id);
			}else if (property == "Części samochodwe"){
				Viewer.this.Controler.removePartsOfCar(id);
			}else if (property == "Serwisowanie"){
				Viewer.this.Controler.removeServicing(id);
			}else if (property == "Przyczepa"){
				Viewer.this.Controler.removeTrailer(id);
			}else if (property == "Typ cargo"){
				Viewer.this.Controler.removeTypeOfCargo(id);
			}else if (property == "Typ materiału eksploatacyjnego"){
				Viewer.this.Controler.removeTypeOfConsumable(id);
			}else if (property == "Typ prawa jazdy"){
				Viewer.this.Controler.removeTypeOfDrivingLicence(id);
			}else if (property == "Typ pojazdu"){
				Viewer.this.Controler.removeTypeOfVehicle(id);
			}else if (property == "Pojazd"){
				Viewer.this.Controler.removeVehicle(id);
			}
		}
		
		private void showInputBox(String property){
			panels[1].removeAll();
			this.repaint();
			panels[1].add(new JLabel("ID obiektu do usunięcia:"));
			panels[1].add(new JTextField(85));
			if (panels[2].getComponentCount() == 0){
				button.addActionListener(this);
				this.panels[2].add(button);
			}
			this.revalidate();
		}
	}
	
	private class GUITablePanel extends JPanel implements ActionListener{
		
		private BorderLayout layout;
		private JPanel[] panels;
		
		private final String[] properties = { "Cargo", "Kierowca", "Mandat", 
				"Historia", "Marka", "Model", "Części samochodwe", "Serwisowanie",
				"Przyczepa", "Typ cargo", "Typ materiału eksploatacyjnego",
				"Typ prawa jazdy", "Typ pojazdu","Pojazd"
		};
		
		public GUITablePanel(int width, int height){
			layout = new BorderLayout();
	        this.setLayout(layout);
	        this.setSize(width, height);
	        panels = new JPanel[2];
	        panels[0]= new JPanel();
	        panels[1]= new JPanel();
	        this.add(panels[0],BorderLayout.NORTH);
	        this.add(panels[1],BorderLayout.CENTER);
			JComboBox propertyBox = new JComboBox(properties);
			propertyBox.setSize(100,20);
			propertyBox.addActionListener(this);
			JLabel jl = new JLabel("Wybierz jakiego obiektu tabele chcesz obejżeć:");
			panels[0].add(jl);
			panels[0].add(propertyBox);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox eventSource = (JComboBox)e.getSource();
			String property = (String)eventSource.getSelectedItem();
			showTable(property);
		}
		
		private void showTable(String property){
			panels[1].removeAll();
			this.repaint();
			if(property == "Cargo"){
				List<String[]> ls = Viewer.this.Controler.getCargoTable();
				Object[] l = {"ID","ID rodzaju","Waga","ID historii"};
				panels[1].add(new JScrollPane(new JTable(listToObject(ls),l)));
			}else if (property == "Kierowca"){
				List<String[]> ls = Viewer.this.Controler.getDriverTable();
				Object[] l = {"ID","Imię", "Nazwisko", "PESEL",
			    		"ID zastępcy", "ID kat. prawa jazdy", "ID pojazdu"};
				panels[1].add(new JScrollPane(new JTable(listToObject(ls),l)));
			}else if (property == "Mandat"){
				List<String[]> ls = Viewer.this.Controler.getFineTable();
				Object[] l = {"ID","ID kierowcy", "Data", "Opłata",
			    		"Pkt. karne", "ID Historii"};
				panels[1].add(new JScrollPane(new JTable(listToObject(ls),l)));
			}else if (property == "Historia"){
				List<String[]> ls = Viewer.this.Controler.getHistoryTable();
				Object[] l = {"ID","ID kierowcy", "ID pojazdu", "Miejsce startu",
			    		"Cel", "Dystans (km)", "Śr. zużycie paliwa",
			    		"Koszt paliwa", "Data", "Przyczepa","ID przyczepy"};
				panels[1].add(new JScrollPane(new JTable(listToObject(ls),l)));
			}else if (property == "Marka"){
				String[] ls = Viewer.this.Controler.getMarkTable();
				Object[] l = {"ID","Nazwa marki"};
				panels[1].add(new JScrollPane(new JTable(stringTabToObject(ls),l)));
			}else if (property == "Model"){
				String[] ls = Viewer.this.Controler.getModelTable();
				Object[] l = {"ID","Nazwa modelu"};
				panels[1].add(new JScrollPane(new JTable(stringTabToObject(ls),l)));
			}else if (property == "Części samochodwe"){
				List<String> temp = Viewer.this.Controler.getPartsOfCarTable();
				String[] ls = temp.toArray(new String[temp.size()]);
				Object[] l = {"ID","Nazwa części"};
				panels[1].add(new JScrollPane(new JTable(stringTabToObject(ls),l)));
			}else if (property == "Serwisowanie"){
				List<String[]> ls = Viewer.this.Controler.getServicingTable();
				Object[] l = {"ID serwisu", "ID przejazdu", "Data",
						"ID przedmiotu","ID czynności","Miejsce serwisu","Koszt"};
				panels[1].add(new JScrollPane(new JTable(listToObject(ls),l)));
			}else if (property == "Przyczepa"){
				List<String[]> ls = Viewer.this.Controler.getTrailerTable();
				Object[] l = {"ID", "ID marki","ID modelu","Nr rejestracyjny"};
				panels[1].add(new JScrollPane(new JTable(listToObject(ls),l)));
			}else if (property == "Typ cargo"){
				List<String> temp = Viewer.this.Controler.getTypeOfCargoeTable();
				String[] ls = temp.toArray(new String[temp.size()]);
				Object[] l = {"ID","Typ towaru"};
				panels[1].add(new JScrollPane(new JTable(stringTabToObject(ls),l)));
			}else if (property == "Typ materiału eksploatacyjnego"){
				List<String> temp = Viewer.this.Controler.getTypeOfConsumableTable();
				String[] ls = temp.toArray(new String[temp.size()]);
				Object[] l = {"ID","Typ materiału eksploatacyjnego"};
				panels[1].add(new JScrollPane(new JTable(stringTabToObject(ls),l)));
			}else if (property == "Typ prawa jazdy"){
				List<String> temp = Viewer.this.Controler.getTypeOfDrivingLicenceTable();
				String[] ls = temp.toArray(new String[temp.size()]);
				Object[] l = {"ID","Typ prawa jazdy"};
				panels[1].add(new JScrollPane(new JTable(stringTabToObject(ls),l)));
			}else if (property == "Typ pojazdu"){
				String[] ls = Viewer.this.Controler.getTypeOfVehicleTable();
				Object[] l = {"ID","Typ samochodu"};
				panels[1].add(new JScrollPane(new JTable(stringTabToObject(ls),l)));
			}else if (property == "Pojazd"){
				List<String[]> ls = Viewer.this.Controler.getVehicleTable();
				Object[] l = {"ID","ID typu samochodu", "ID marki","ID modelu",
						"ID kat. prawa jazdy","Przebieg","Rodzaj paliwa","Nr rejestracyjny"};
				panels[1].add(new JScrollPane(new JTable(listToObject(ls),l)));
			}
			this.revalidate();
		}
		 
		private Object[][] listToObject(List<String[]> ls){
			int k = ls.get(0).length;
			int w = ls.size();
			Object[][] ot = new Object[w][k];
			for(int i=0;i<w;i++){
				for(int j=0;j<k;j++){
					ot[i][j] = ls.get(i)[j];
				}
			}
			return ot;
		}
		
		private Object[][] stringTabToObject(String[] ls){
			int k = ls.length;
			Object[][] ot = new Object[k][2];
			for(int j=0;j<k;j++){
				ot[j][0] = String.valueOf(j+1);
				ot[j][1] = ls[j];
			}
			return ot;
		}
		
	}
	
	private class GUIAddPanel extends JPanel implements ActionListener{
		private BorderLayout layout;
		private JPanel[] panels;
		JButton button;
		private final String[] properties = { "Cargo", "Kierowca", "Mandat", 
				"Historia", "Marka", "Model", "Parts of car", "Serwisowanie",
				"Przyczepa", "Typ cargo", "Typ materiału eksploatacyjnego",
				"Typ prawa jazdy", "Typ pojazdu","Pojazd"
		};

		public GUIAddPanel(int width, int height){
			layout = new BorderLayout();
	        this.setLayout(layout);
	        this.setSize(width, height);
	        button = new JButton("Submit");
	        panels = new JPanel[3];
	        panels[0]= new JPanel();
	        panels[1]= new JPanel();
	        panels[2]= new JPanel();
	        this.add(panels[0],BorderLayout.NORTH);
	        this.add(panels[1],BorderLayout.CENTER);
	        this.add(panels[2],BorderLayout.SOUTH);
			JComboBox propertyBox = new JComboBox(properties);
			propertyBox.setSize(100,20);
			propertyBox.addActionListener(this);
			JLabel jl = new JLabel("Wybierz jaki obiekt chcesz dodać:");
			panels[0].add(jl);
			panels[0].add(propertyBox);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(button)){
				JComboBox temp  = (JComboBox)panels[0].getComponent(1);
				String property = (String)temp.getSelectedItem();
				sendNewMember(property);
			} else {
				JComboBox eventSource = (JComboBox)e.getSource();
				String property = (String)eventSource.getSelectedItem();
				showInputBoxes(property);
			}
		}
		
		private void sendNewMember(String property){
			if(property == "Cargo"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				int id_rodzaju = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(3);
				float waga = Float.parseFloat(temp.getText());
				temp = (JTextField)panels[1].getComponent(5);
				int id_historii = Integer.parseInt(temp.getText());
				Viewer.this.Controler.addCargo(id_rodzaju,waga,id_historii);
			}else if (property == "Kierowca"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String name = temp.getText();
				temp = (JTextField)panels[1].getComponent(3);
				String lastname = temp.getText();
				temp = (JTextField)panels[1].getComponent(5);
				String pesel = temp.getText();
				temp = (JTextField)panels[1].getComponent(7);
				int id_zastepcy = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(9);
				int id_kat_prawa_jazdy = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(11);
				int id_pojazdu = Integer.parseInt(temp.getText());
				Viewer.this.Controler.addDriver(name,lastname,pesel,id_zastepcy,
						id_kat_prawa_jazdy,id_pojazdu);
			}else if (property == "Mandat"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				int id_kierowcy = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(3);
				java.sql.Date data = getDate(temp.getText());
				temp = (JTextField)panels[1].getComponent(5);
				float oplata = Float.parseFloat(temp.getText());
				temp = (JTextField)panels[1].getComponent(7);
				int pkt_karne = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(9);
				int id_historii = Integer.parseInt(temp.getText());
				Viewer.this.Controler.addFine(id_kierowcy, (java.sql.Date)data,oplata,pkt_karne,id_historii);
			}else if (property == "Historia"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				int id_kierowcy = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(3);
				int id_pojazdu = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(5);
				String miejsce_startu = temp.getText();
				temp = (JTextField)panels[1].getComponent(7);
				String miejsce_docelowe = temp.getText();
				temp = (JTextField)panels[1].getComponent(9);
				float liczba_km = Float.parseFloat(temp.getText());
				temp = (JTextField)panels[1].getComponent(11);
				float srednie_zuzycie_paliwa = Float.parseFloat(temp.getText());
				temp = (JTextField)panels[1].getComponent(13);
				float cena_zużytego_paliwa = Float.parseFloat(temp.getText());
				temp = (JTextField)panels[1].getComponent(15);
				java.sql.Date data = getDate(temp.getText());
				temp = (JTextField)panels[1].getComponent(17);
				boolean przyczepa = Boolean.parseBoolean(temp.getText());
				temp = (JTextField)panels[1].getComponent(19);
				int id_przyczepy = Integer.parseInt(temp.getText());
				Viewer.this.Controler.addHistory(id_kierowcy,id_pojazdu,miejsce_startu,
						miejsce_docelowe,liczba_km,srednie_zuzycie_paliwa,cena_zużytego_paliwa,
						data,przyczepa,id_przyczepy);
			}else if (property == "Marka"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String opis_typ = temp.getText();
				Viewer.this.Controler.addMark(opis_typ);
			}else if (property == "Model"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String opis_typ = temp.getText();
				Viewer.this.Controler.addModel(opis_typ);
			}else if (property == "Części samochodwe"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String opis_typ = temp.getText();
				Viewer.this.Controler.addPartsOfCar(opis_typ);
			}else if (property == "Serwisowanie"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				int id_serwisu = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(3);
				int id_przejazdu = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(5);
				java.sql.Date data = getDate(temp.getText());
				temp = (JTextField)panels[1].getComponent(7);
				int id_przedmiotu = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(9);
				int id_czynnosci = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(11);
				String miejsce_serwisu = temp.getText();
				temp = (JTextField)panels[1].getComponent(13);
				float cena = Float.parseFloat(temp.getText());
				Viewer.this.Controler.addServicing(id_serwisu,id_przejazdu,
						data,id_przedmiotu,id_czynnosci,miejsce_serwisu,cena);
			}else if (property == "Przyczepa"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				int id_marki = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(3);
				int id_modelu = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(5);
				String nr_rejestracyjny = temp.getText();
				Viewer.this.Controler.addTrailer(id_marki,id_modelu,nr_rejestracyjny);
			}else if (property == "Typ cargo"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String opis_typ = temp.getText();
				Viewer.this.Controler.addTypeOfCargo(opis_typ);
			}else if (property == "Typ materiału eksploatacyjnego"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String opis_typ = temp.getText();
				Viewer.this.Controler.addTypeOfConsumable(opis_typ);
			}else if (property == "Typ prawa jazdy"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String opis_typ = temp.getText();
				Viewer.this.Controler.addTypeOfDrivingLicence(opis_typ);
			}else if (property == "Typ pojazdu"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				String opis_typ = temp.getText();
				Viewer.this.Controler.addTypeOfVehicle(opis_typ);
			}else if (property == "Serwisowanie"){
				JTextField temp = (JTextField)panels[1].getComponent(1);
				int id_typ_pojazdu = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(3);
				int id_marki = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(5);
				int id_modelu = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(7);
				int id_kat_prawa_jazdy = Integer.parseInt(temp.getText());
				temp = (JTextField)panels[1].getComponent(9);
				float przebieg = Float.parseFloat(temp.getText());
				temp = (JTextField)panels[1].getComponent(11);
				String rodz_paliwa = temp.getText();
				temp = (JTextField)panels[1].getComponent(13);
				String nr_rejestracyjny = temp.getText();
				Viewer.this.Controler.addVehicle(id_typ_pojazdu,id_marki,
						id_modelu, id_kat_prawa_jazdy, przebieg,
						rodz_paliwa, nr_rejestracyjny);
			}
		}
		
		private void showInputBoxes(String property){
			panels[1].removeAll();
			this.repaint();
			if(property == "Cargo"){
				int n = 3;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("ID rodzaju towaru:");
				lab[1] = new JLabel("Waga towaru:");
				lab[2] = new JLabel("ID historii:");
				for(int i=0; i<n;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if(property == "Kierowca"){
				int n = 6;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Imię:");
				lab[1] = new JLabel("Nazwisko:");
				lab[2] = new JLabel("PESEL:");
				lab[3] = new JLabel("ID zastępcy:");
				lab[4] = new JLabel("ID Kat. prawa jazdy:");
				lab[5] = new JLabel("ID pojazdu:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Mandat"){
				int n = 5;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("ID kierowcy:");
				lab[1] = new JLabel("Data (w formacie yyyy-mm-dd):");
				lab[2] = new JLabel("Opłata:");
				lab[3] = new JLabel("Pkt. karne:");
				lab[4] = new JLabel("ID historii:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Historia"){
				int n = 10;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("ID kierowcy:");
				lab[1] = new JLabel("ID pojazdu:");
				lab[2] = new JLabel("Miejsce startu:");
				lab[3] = new JLabel("Miejsce docelowe:");
				lab[4] = new JLabel("Liczba km:");
				lab[5] = new JLabel("Średnie zużycie paliwa:");
				lab[6] = new JLabel("Cena zużytegoo paliwa:");
				lab[7] = new JLabel("Data (w formacie yyyy-mm-dd):");
				lab[8] = new JLabel("Przyczepa (true/false):");
				lab[9] = new JLabel("ID przyczepy:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Marka"){
				int n = 1;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Nowa marka:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Model"){
				int n = 1;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Nowy model:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Części samochodwe"){
				int n = 1;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Nowa część samochodowa:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Serwisowanie"){
				int n = 7;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("ID serwisu:");
				lab[1] = new JLabel("ID historii:");
				lab[2] = new JLabel("Data (w formacie yyyy-mm-dd):");
				lab[3] = new JLabel("ID części samochodowej:");
				lab[4] = new JLabel("ID czynności:");
				lab[5] = new JLabel("Miejsce serwisu:");
				lab[6] = new JLabel("Cena:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Przyczepa"){
				int n = 3;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("ID marki:");
				lab[1] = new JLabel("ID modelu:");
				lab[2] = new JLabel("Nr rejestracyjny:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Typ cargo"){
				int n = 1;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Nowy typ cargo:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Typ materiału eksploatacyjnego"){
				int n = 1;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Nowy typ materiału eksploatacyjnego:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Typ prawa jazdy"){
				int n = 1;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Nowy typ prawa jazdy:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Typ pojazdu"){
				int n = 1;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("Nowy typ pojazdu:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}else if (property == "Pojazd"){
				int n = 7;
				JLabel[] lab = new JLabel[n];
				lab[0] = new JLabel("ID typu pojazdu:");
				lab[1] = new JLabel("ID marki:");
				lab[2] = new JLabel("ID modelu:");
				lab[3] = new JLabel("ID kat. prawa jazdy:");
				lab[4] = new JLabel("Przebieg:");
				lab[5] = new JLabel("Rodzaj paliwa:");
				lab[6] = new JLabel("Nr rejestracyjny:");
				for(int i=0; i<n ;i++){
					panels[1].add(lab[i]);
					panels[1].add(new JTextField(85));
				}
			}
			if (panels[2].getComponentCount() == 0){
				button.addActionListener(this);
				this.panels[2].add(button);
			}
			this.revalidate();
		}
		
		private java.sql.Date getDate(String d){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date  parsed;
			java.sql.Date sqlD;
			try {
				parsed = (java.util.Date)format.parse(d);
				sqlD = new java.sql.Date(parsed.getTime());
				//System.out.print(sqlD);
				return sqlD;
			} catch (ParseException e) {
				System.out.print("aaaa");
			}
			//long millis=System.currentTimeMillis(); 
			return null;
		}
		
	}
	
	private class GUIQueryPanel extends JPanel implements ActionListener{
		
		private BorderLayout layout;
		private JPanel[] panels;
		private Box[] dataBoxes;
		JButton button;
		private int inst = 0;
		private String[] carTypes;
		private final String[] queries = {"Kto od ... do ... przejechał najwięcej km", 
							"Kto od ... do ... przejechał najmniej km", 
							"Jaki samochód ma największe średnie zużycie paliwa", 
							"Serwisowanie jakiej marki samochodu typu ... jest najdroższe w roku ...", 
							"Czy samochodami ciężarowymi jździły osoby bez wystarczających uprawnień", 
							"Kto prowadził najtańszy w serwisowaniu samochód od ... do ...", 
							"Kto prowadził najdroższy w eksploatacji samochód od ..  do ...", 
							"Na jakie materiały eksploatacyjne wydano najwięcej w roku ...", 
							"Na jakie materiały eksploatacyjne wydano najmniej w roku ...", 
							"Jaki rodzaj ładunku przewożony był przez największą liczbą kilometrów", 
							"Jaki rodzaj ładunku ma najmniejszy koszy przewozu na jeden kilometr", 
							"Kto ma najwięcej punktów karnych przyznanych w okresie od ... do ...", 
							"Kto ma najmniej punktów karnych przyznanych w okresie od ... do ...", 
							"Przy przewozie jakiego rodzaju ładunku w okresie od ... do ... przyznano największą liczbę pkt. karnych", 
							"Przy przewozie jakiego rodzaju ładunku w okresie od ... do ... przyznano najmniejszą liczbę pkt. karnych"};
		private final String[] years = {"2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008",
						  "2007"};
		private final String[] months = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec",
						   "sierpień", "wrzesień", "padziernik", "listopad", "grudzień"};
		private final String[] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
						 "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};

		
		public GUIQueryPanel(int width, int height){
			this.setBackground(Color.magenta);
			//super(new BorderLayout());
			layout = new BorderLayout();
	        this.setLayout(layout);
	        panels = new JPanel[3];
	        panels[0]= new JPanel();
	        panels[1]= new JPanel();
	        panels[2]= new JPanel();
	        dataBoxes = new Box[6];
	        this.add(panels[0],BorderLayout.NORTH);
	        this.add(panels[1],BorderLayout.CENTER);
	        this.add(panels[2],BorderLayout.SOUTH);
			button = new JButton("Submit");

			QueryComboBox queryComboBox = new QueryComboBox(queries);
			panels[0].add(queryComboBox);
			queryComboBox.addActionListener(this);
			this.setSize(width, height);
			//queryComboBox.setLocation(width/100, height/20);
		}

		public void actionPerformed(ActionEvent e) {
			QueryType type = QueryType.NONE;
			if(e.getSource().equals(button)){
				Box qBox = (Box)panels[0].getComponent(0);
				type = assertQueryType((String)qBox.getSelectedItem());
				JTable querryResults = this.getQuerryResults((String)qBox.getSelectedItem(),type);
				panels[1].removeAll();
				this.repaint();
				panels[1].add(new JScrollPane(querryResults));
				this.revalidate();
			} else {
				Box eventSource = (Box)e.getSource();
				if(eventSource.getBoxType() == BoxType.QUERRY){
					type = assertQueryType((String)eventSource.getSelectedItem());
					respondToQuery(type);
				}
			}
			//respond(type);
	    }
		
		private JTable getQuerryResults(String query, QueryType type){
			String yearFrom;
			int monthFrom;
			String dayFrom;
			String yearTo;
			int monthTo;
			String dayTo;
			JTable t;
			java.sql.Date from;
			java.sql.Date to;
			switch(type){
			case DATE_FROM_TO:
				yearFrom = (String)dataBoxes[0].getSelectedItem();
				monthFrom = assesMonth((String)dataBoxes[1].getSelectedItem());
				//System.out.print((String)dataBoxes[3].getSelectedItem());
				dayFrom = (String)dataBoxes[2].getSelectedItem();
				from = getDate(yearFrom, monthFrom, dayFrom);
				yearTo = (String)dataBoxes[3].getSelectedItem();
				monthTo = assesMonth((String)dataBoxes[4].getSelectedItem());
				dayTo = (String)dataBoxes[5].getSelectedItem();
				to = getDate(yearTo, monthTo, dayTo);
				t = sendFromToQuery(query, from, to);
				return t;
			case DATE_YEAR:
				yearFrom = (String)dataBoxes[0].getSelectedItem();
				monthFrom = 1;
				dayFrom = this.days[0];
				from = getDate(yearFrom, monthFrom, dayFrom);
				yearTo = (String)dataBoxes[0].getSelectedItem();
				monthTo = 12;
				dayTo = this.days[this.days.length-1];;
				to = getDate(yearTo, monthTo, dayTo);
				t = sendYearQuery(query, from, to);
				return t;
			case CAR_TYPE_AND_YEAR:
				String carType = (String)dataBoxes[0].getSelectedItem();
				yearFrom = (String)dataBoxes[1].getSelectedItem();
				monthFrom = 1;
				dayFrom = this.days[0];
				from = getDate(yearFrom, monthFrom, dayFrom);
				yearTo = (String)dataBoxes[1].getSelectedItem();
				monthTo = 12;
				dayTo = this.days[this.days.length-1];;
				to = getDate(yearTo, monthTo, dayTo);
				t = sendCarTypeQuery(query, from, to, carType);
				return t;
			case NONE:
				t = sendNoneQuery(query);
				return t;
			}
			return null;
		}
		
		private JTable sendFromToQuery(String query, java.sql.Date from, java.sql.Date to) {
			if(query == "Kto od ... do ... przejechał najwięcej km"){  
            	String[]  res = Viewer.this.Controler.getDriverWithMaxKm(from,to);
				Object[] labels = {"Id kierowcy", "Imie", "Nazwisko", "Suma przejechanych km" };
				Object[][] data = new Object[1][4];
				for(int i =0; i<4;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Kto od ... do ... przejechał najmniej km"){  
				String[]  res = Viewer.this.Controler.getDriverWithMinKm(from,to);
				Object[] labels = {"Id kierowcy", "Imie", "Nazwisko", "Suma przejechanych km" };
				Object[][] data = new Object[1][4];
				for(int i =0; i<4;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Kto prowadził najtańszy w serwisowaniu samochód od ... do ..."){  
				String[]  res = Viewer.this.Controler.getDriverWithCheapestCarForServicing(from, to);
				Object[] labels = {"Imie","Nazwisko","Koszty serwisowania" };
				Object[][] data = new Object[1][3];
				for(int i =0; i<3;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Kto prowadził najdroższy w eksploatacji samochód od ..  do ..."){  
				String[]  res = Viewer.this.Controler.getDriverWithMostExpensiveCarForServicing(from, to);
				Object[] labels = {"Imie","Nazwisko","Koszty serwisowania" };
				Object[][] data = new Object[1][3];
				for(int i =0; i<3;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Kto ma najwięcej punktów karnych przyznanych w okresie od ... do ..."){  
				String[]  res = Viewer.this.Controler.getDriverWithMostPenaltyPoints(from, to);
				Object[] labels = {"Id kierowcy","Imie","Nazwisko","Liczba pkt" };
				Object[][] data = new Object[1][4];
				for(int i =0; i<4;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Kto ma najmniej punktów karnych przyznanych w okresie od ... do ..."){  
				String[]  res = Viewer.this.Controler.getDriverWithLeastPenaltyPoints(from, to);
				Object[] labels = {"Id kierowcy","Imie","Nazwisko","Liczba pkt" };
				Object[][] data = new Object[1][4];
				for(int i =0; i<4;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Przy przewozie jakiego rodzaju ładunku w okresie od ... do ... przyznano największą liczbę pkt. karnych"){ 
				String[]  res = Viewer.this.Controler.getCargoWithMostPenaltyPoints(from, to);
				Object[] labels = {"Rodzaj ładunku","Liczba pkt" };
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Przy przewozie jakiego rodzaju ładunku w okresie od ... do ... przyznano najmniejszą liczbę pkt. karnych"){  
				String[]  res = Viewer.this.Controler.getCargoWithLeastPenaltyPoints(from, to);
				Object[] labels = {"Rodzaj ładunku","Liczba pkt" };
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}
			return null;
		}
		
		public JTable sendYearQuery(String query, Date from, Date to) {
		
			if (query == "Na jakie materiały eksploatacyjne wydano najwięcej w roku ..."){  
				String[]  res = Viewer.this.Controler.getMaterialsIssuedTheMost(from, to);
				Object[] labels = {"Rodzaj ładunku","Koszty" };
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Na jakie materiały eksploatacyjne wydano najmniej w roku ..."){  
				String[]  res = Viewer.this.Controler.getMaterialsIssuedAtLeast(from, to);
				Object[] labels = {"Rodzaj ładunku","Koszty" };
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
            }
			return null;
		}
		

		public JTable sendNoneQuery(String query) {
			if (query == "Jaki samochód ma największe średnie zużycie paliwa"){
				String[]  res = Viewer.this.Controler.getVehicleWithMaxFuelConsumption();
				Object[] labels = {"ID pojazdu","Marka","Model","Średnie zużycie paliwa"};
				Object[][] data = new Object[1][4];
				for(int i =0; i<4;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Czy samochodami ciężarowymi jździły osoby bez wystarczających uprawnień"){
				String[]  res = Viewer.this.Controler.getDriverWithWrongDrivingLicense();
				Object[] labels = {"Imie","Nazwisko"};
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Jaki rodzaj ładunku przewożony był przez największą liczbą kilometrów"){
				String[]  res = Viewer.this.Controler.getCargoCarriedByTheLargestNumberOfKm();
				Object[] labels = {"Rodzaj ładunku","Suma km" };
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}else if (query == "Jaki rodzaj ładunku ma najmniejszy koszy przewozu na jeden kilometr"){
				String[]  res = Viewer.this.Controler.getTypeOfCargoWithSmallestTransportCost();
				Object[] labels = {"Rodzaj ładunku","Koszt transportu"};
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}
			return null;
		}
		
		public JTable sendCarTypeQuery(String query, Date from, Date to, String carType) {
			if (query == "Serwisowanie jakiej marki samochodu typu ... jest najdroższe w roku ..."){  
				String[]  res = Viewer.this.Controler.getMostExpensiveMaintenance(from, to, carType);
				Object[] labels = {"Marka","Koszty serwisowania" };
				Object[][] data = new Object[1][2];
				for(int i =0; i<2;i++){
					data[0][i]=res[i];
				}
				return new JTable(data,labels);
			}
			return null;
		}

		private int assesMonth(String month){
			for(int i=0;i<12;i++){
				if(this.months[i]==month)
					return i+1;
			}
			return -1;
		}
		
		private java.sql.Date getDate(String year, int month, String day){
			SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
			java.util.Date  parsed;
			java.sql.Date sqlD;
			try {
				parsed = (java.util.Date)format.parse(year+" "+month+" "+day);
				sqlD = new java.sql.Date(parsed.getTime());
				//System.out.print(sqlD);
				return sqlD;
			} catch (ParseException e) {
				System.out.print("aaaa");
			}
			//long millis=System.currentTimeMillis(); 
			return null;
		}
		
		private void respondToQuery(QueryType type){
			panels[1].removeAll();
			this.repaint();
			switch(type){
				case DATE_FROM_TO:
					JPanel panelLeft = new JPanel();
					JPanel panelRight = new JPanel();
					this.panels[1].add(panelLeft,BorderLayout.WEST);
					this.panels[1].add(panelRight,BorderLayout.EAST);
					this.dataBoxes[0] = new DateComboBox(this.years,BoxType.YEAR);
					panelLeft.add(dataBoxes[0]);
					this.dataBoxes[1] = new DateComboBox(this.months,BoxType.MONTH);
					panelLeft.add(dataBoxes[1]);
					this.dataBoxes[2] = new DateComboBox(this.days,BoxType.DAY);
					panelLeft.add(dataBoxes[2]);
					this.dataBoxes[3] = new DateComboBox(this.years,BoxType.YEAR);
					panelRight.add(dataBoxes[3]);
					this.dataBoxes[4] = new DateComboBox(this.months,BoxType.MONTH);
					panelRight.add(dataBoxes[4]);
					this.dataBoxes[5] = new DateComboBox(this.days,BoxType.DAY);
					panelRight.add(dataBoxes[5]);
					this.revalidate();
					break;
				case DATE_YEAR:
					this.dataBoxes[0] = new DateComboBox(this.years,BoxType.YEAR);
					panels[1].add(dataBoxes[0]);
					this.revalidate();
					break;
				case CAR_TYPE_AND_YEAR: 
					if(inst == 0){
						inst++;
						carTypes= Viewer.this.Controler.getTypeOfVehicleTable();
					}
					this.dataBoxes[0] = new Box(carTypes, BoxType.CAR_TYPE);
					this.dataBoxes[1] = new DateComboBox(this.years,BoxType.YEAR);
					panels[1].add(dataBoxes[0]);
					panels[1].add(dataBoxes[1]);
					this.revalidate();
					break;
				}
			if (panels[2].getComponentCount() == 0){
				button.addActionListener(this);
				this.panels[2].add(button);
				this.revalidate();
			}
		}
		
		private QueryType assertQueryType(String query){
			QueryType type = QueryType.NONE;
			if(query == "Kto od ... do ... przejechał najwięcej km"){  
            	type = QueryType.DATE_FROM_TO;
			}else if (query == "Kto od ... do ... przejechał najmniej km"){  
            	type = QueryType.DATE_FROM_TO;
			}else if (query == "Serwisowanie jakiej marki samochodu typu ... jest najdroższe w roku ..."){  
            	type = QueryType.CAR_TYPE_AND_YEAR;
			}else if (query == "Kto prowadził najtańszy w serwisowaniu samochód od ... do ..."){  
            	type = QueryType.DATE_FROM_TO;
			}else if (query == "Kto prowadził najdroższy w eksploatacji samochód od ..  do ..."){  
            	type = QueryType.DATE_FROM_TO;
			}else if (query == "Na jakie materiały eksploatacyjne wydano najwięcej w roku ..."){  
            	type = QueryType.DATE_YEAR;
			}else if (query == "Na jakie materiały eksploatacyjne wydano najmniej w roku ..."){  
            	type = QueryType.DATE_YEAR;
			}else if (query == "Kto ma najwięcej punktów karnych przyznanych w okresie od ... do ..."){  
            	type = QueryType.DATE_FROM_TO;
            }else if (query == "Kto ma najmniej punktów karnych przyznanych w okresie od ... do ..."){  
            	type = QueryType.DATE_FROM_TO;
            }else if (query == "Przy przewozie jakiego rodzaju ładunku w okresie od ... do ... przyznano największą liczbę pkt. karnych"){ 
            	type = QueryType.DATE_FROM_TO;
            }else if (query == "Przy przewozie jakiego rodzaju ładunku w okresie od ... do ... przyznano najmniejszą liczbę pkt. karnych"){  
            	type = QueryType.DATE_FROM_TO;
            }
			return type;
		}
		
	}
	protected class Box extends JComboBox{
		private BoxType type;
		public Box(String[] items, BoxType type){
			super(items);
			this.type = type;
		}
		public BoxType getBoxType(){
			return type;
		
		}
	}
	protected class DateComboBox extends Box{
		public DateComboBox(String[] dates, BoxType type){
			super(dates, type);
			this.setSize(70,30);
		}
	}
	
	protected class QueryComboBox extends Box{
		public QueryComboBox(String[] queries){
			super(queries,BoxType.QUERRY);
			this.setSize(100,20);
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		Controler.close();
		System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
