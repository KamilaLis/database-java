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
import java.util.Vector;

import javax.swing.*;

public class Viewer{
	
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
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(new Dimension(screensize.width/2,screensize.height/2));
		frame.setLocation(screensize.width/4, screensize.height/4);
		JTabbedPane tp=new JTabbedPane();
		tp.setBounds(50,50,200,200);  
		tp.add("zapytania",new GUIQueryPanel(screensize.width/2, screensize.height/2));  
		tp.add("dodawanie",new GUIAddPanel(screensize.width/2, screensize.height/2));  
		tp.add("usuwanie",new JPanel());  
		tp.add("wypisywanie",new JPanel());  
		//GUIQueryPanel gui = new GUIQueryPanel(screensize.width/2, screensize.height/2);
		//frame.getContentPane().add(gui, BorderLayout.CENTER);
		frame.getContentPane().add(tp);
		frame.setVisible(true);
	}
	private class GUIAddPanel extends JPanel implements ActionListener{
		private BorderLayout layout;
		private JPanel[] panels;
		JButton button;
		private final String[] properties = { "Cargo", "Kierowca", "Mandat", 
				"Historia", "Marka", "Model", "Parts of car", "Serwisowanie",
				"Przyczepa", "Typ cargo", "Typ materiału eksploatacyjnego",
				"Typ prawa jazdy", "Typ pojazdu"
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
				this.revalidate();
			} else {
				JComboBox eventSource = (JComboBox)e.getSource();
				String property = (String)eventSource.getSelectedItem();
				showInputBoxes(property);
			}
		}
		
		private void showInputBoxes(String property){
			if(property == "Cargo"){
				JLabel idR = new JLabel("ID rodzaju towaru:");
				JLabel waga = new JLabel("Waga towaru:");
				JLabel idH = new JLabel("ID historii:");
				JTextField idRin=new JTextField(70);
				JTextField wagain=new JTextField(70);
				JTextField idHin=new JTextField(70);
				panels[1].add(idR);
				panels[1].add(idRin);
				panels[1].add(waga);
				panels[1].add(wagain);
				panels[1].add(idH);
				panels[1].add(idHin);
				this.revalidate();
			}else if(property == "Kierowca"){
				
			}
			if (panels[2].getComponentCount() == 0){
				button.addActionListener(this);
				this.panels[2].add(button);
				this.revalidate();
			}
		}
		
	}
	
	private class GUIQueryPanel extends JPanel implements ActionListener{
		
		private BorderLayout layout;
		private JPanel[] panels;
		private Box[] dataBoxes;
		JButton button;
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
					this.dataBoxes[0] = new Box(Viewer.this.Controler.getTypeOfVehicleTable(), BoxType.CAR_TYPE);
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

}
