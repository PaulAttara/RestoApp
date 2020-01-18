package ca.mcgill.ecse223.resto.view;

import java.awt.BasicStroke;
import java.awt.Color;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.Table;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TableLayout extends JPanel {

	List<JButton> tables = new ArrayList<JButton>();
	List<JButton> seats = new ArrayList<JButton>();
	HashMap<JButton, Table> tableMap;
	HashMap<JButton, Seat> seatMap;
	
	
    
    int k;
    int width = 540;
    int height = 620;
    
    static int rows = 10;
    static int columns = 10;

    int htOfRow = height / rows;
    int wdOfRow = width / columns;
    
	public TableLayout() {
		super();
		init();
	}

	public void init() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 640, 720);
		this.setVisible(true);
		tableMap = new HashMap<JButton, Table>();
		seatMap = new HashMap<JButton, Seat>();
		refreshTable();
		
		
	}

	//this function delete all the tables and create new ones
	public void refreshTable() {
		System.out.println("refreshing");
		this.setVisible(false);
		

		RestoApp restoApp = RestoAppApplication.getRestoApp();
		//all the table are hashmapped to are button in the list called buttons
		//remove buttons from jpanel
		for(JButton button : tables) {
			this.remove(button);
		}
		//clear the list
		tables.clear();
		
		for(JButton button : seats) {
			this.remove(button);
		}
		//clear the list
		seats.clear();
		
		//add new buttons by using restoApp.getTables
		for (Table table : restoApp.getCurrentTables()) {
			if (table != null) {
				List currentSeats =  table.getCurrentSeats();
				String status = "";
				/*List currentSeats =  table.getCurrentSeats();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss") ;
				Date time = new Date();
				System.out.println(dateFormat.format(date));
				System.out.println(timeFormat.format(time));
			
				for(ca.mcgill.ecse223.resto.model.Reservation r : table.getReservations()) {
					if (sqlDate == r.getDate()) System.out.println("same date");
				}*/
				
				if(table.hasOrders() && table.getStatusFullName() != "Available") status = "In Use";
				
				
				//if(!(table.hasOrders()||table.hasReservations())) status = "Available";
				
				JButton button = new JButton(Integer.toString(table.getNumber()));
				tables.add(button);
				button.setFont(new Font("Avenir Next", Font.PLAIN, 20)); // EDIT THIS LINE AND THE NEXT TO CHANGE FONT
				button.setForeground(Color.red); // DEFAULT COLOR OF BUTTONS IS WHITE
				button.setToolTipText("<html>"
                        + status
                        +"<br>"
                        + table.getStatusFullName()
                   + "</html>");
				
				int locationx =50+wdOfRow*table.getX();
				int locationy = 75+htOfRow* table.getY();
				int widthtable = wdOfRow*table.getWidth();
				int heighttable = htOfRow*table.getLength()-50;
				
				button.setBounds(locationx, locationy, widthtable, heighttable );
				if(RestoAppController.tableList.contains(table)) button.setBackground(Color.BLUE);
				else button.setBackground(Color.white);																									// IS
				button.setOpaque(true);
				button.setContentAreaFilled(true);
				button.setBorderPainted(true);
				button.setVisible(true);
				tableMap.put(button, table);
				this.add(button);
				/*
				System.out.println("table number: " + table.getNumber());
				System.out.println("table x: " + table.getX());
				System.out.println("table y: " + table.getY());
				System.out.println("table width: " + table.getWidth());
				System.out.println("table length: " + table.getLength());
				System.out.println("table seat: " + currentSeats.size());
				*/
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
						if(button.getBackground()==Color.blue) {
							button.setBackground(Color.white);
						}
						else{
							button.setBackground(Color.blue);
						}
						/*if(table.getStatusFullName() != "Available") {
							//Display availability WHITE means available. Blue means in use
					        button.setBackground(Color.BLUE);
						}*/
						RestoAppController.setTable(tableMap.get(button));
					}
					
				});
				
				//create button for seats
				int index = 0;
				//int seats = table.getCurrentSeats();
				//System.out.println(table.getCurrentSeat(index));
				int x= locationx;
					for(int j = 0; j<(currentSeats.size()/2); j++) {
						
					JButton buttonseat = new JButton();
					buttonseat.setBounds(x+5, locationy-20, wdOfRow-10, 15);
					buttonseat.setBackground(Color.white);																									// IS
					buttonseat.setOpaque(true);
					buttonseat.setContentAreaFilled(true);
					buttonseat.setBorderPainted(true);
					buttonseat.setVisible(true);
					x+= wdOfRow;
					//System.out.println(index);
					seatMap.put(buttonseat, table.getCurrentSeat(index));
				
					buttonseat.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if(seatMap.get(buttonseat).hasOrderItems())
								for(OrderItem i : seatMap.get(buttonseat).getOrderItems())
									System.out.println(i);
							
							if(buttonseat.getBackground()==Color.blue) {
								buttonseat.setBackground(Color.white);
							}
							else{
								buttonseat.setBackground(Color.blue);
							}
							
							
							
							
							RestoAppController.setSeat(seatMap.get(buttonseat));
						}
					});
					this.add(buttonseat);
					seats.add(buttonseat);
					index ++;
					}
					x = locationx;
					for(int j = 0; j<(currentSeats.size()/2); j++) {
						
						JButton buttonseat = new JButton();
						buttonseat.setBounds(x+5, locationy+heighttable+5, wdOfRow-10, 15);
						buttonseat.setBackground(Color.white);																									// IS
						buttonseat.setOpaque(true);
						buttonseat.setContentAreaFilled(true);
						buttonseat.setBorderPainted(true);
						buttonseat.setVisible(true);
						x+= wdOfRow;
						System.out.println(index);
						seatMap.put(buttonseat, table.getCurrentSeat(index));
					
						buttonseat.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for(OrderItem i : seatMap.get(buttonseat).getOrderItems())
									System.out.println(i);
							if(buttonseat.getBackground()==Color.blue) {
								buttonseat.setBackground(Color.white);
							}
							else{
								buttonseat.setBackground(Color.blue);
							}
								RestoAppController.setSeat(seatMap.get(buttonseat));
							}
						});
						this.add(buttonseat);
						seats.add(buttonseat);
						index ++;
					}
				
				
				
			}
			
		}
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		
		//Graphics2D g2d = (Graphics2D) g.create();
		 //Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		 //g2d.setStroke(dashed);
	    super.paintComponent(g);
	        
      
        g.setColor(Color.LIGHT_GRAY);
        
        for (k = 0; k <= rows; k++)
          g.drawLine(50, (k * htOfRow)+50  , width+50, k * htOfRow +50);
       
        for (k = 0; k <= columns; k++)
          g.drawLine(k*wdOfRow +50  , 50, k*wdOfRow+50 , height+50);
      }
	
/*
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		TableLayout tableLayout = new TableLayout();
		frame.add(tableLayout);
	}*/
}
