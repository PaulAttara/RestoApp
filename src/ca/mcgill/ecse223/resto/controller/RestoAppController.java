package ca.mcgill.ecse223.resto.controller;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.SpringLayout.Constraints;

import org.omg.CORBA.SystemException;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.Table.Status;

public class RestoAppController {

	
	public static List<Table> tableList = new ArrayList<Table>();
	public static List<Seat> seatList = new ArrayList<Seat>();
	public static OrderItem selectedoi = null;
	public static Reservation res = null;

	public static void setTable(Table table) {
		boolean isDuplicate = false;
		for (Table currentTable : tableList) {
			if (currentTable == table) {
				isDuplicate = true;
			}
		}
		if (!isDuplicate) {
			tableList.add(table);
		} else {
			tableList.remove(table);
		}
	}

	public static void removeFromList(Table table) {
		for (Table currentTable : tableList) {
			if (currentTable == table) {
				tableList.remove(currentTable);
			}
		}
	}

	public static void setSeat(Seat seat) {
		if(seatList.contains(seat)) seatList.remove(seat);
		else seatList.add(seat);
	}
	public static List<Table> getTables() {
		return RestoAppApplication.getRestoApp().getCurrentTables();
	}
	
	public static void addMenuItem(String name, ItemCategory category, double price) throws InvalidInputException {
		if (price < 0){
			throw new InvalidInputException("The price must be positive. ");
        }
		if (price == 0){
			throw new InvalidInputException("The price can't be 0$. ");
        }
		if (name == null) {
			throw new InvalidInputException("Must specify the name of the item to add. ");
        }
		if (name.isEmpty()) {
			throw new InvalidInputException("Need to enter a valid name in order to add a menu item. ");
        }
		if (category == null){
			throw new InvalidInputException("Must specify the category of the menu item to add. ");
        }
		RestoApp r = RestoAppApplication.getRestoApp();
		Menu menu = r.getMenu();
		MenuItem menuItem = new MenuItem(name, menu);
		menuItem.setItemCategory(category);
		PricedMenuItem pmi = menuItem.addPricedMenuItem(price, r);
		menuItem.setCurrentPricedMenuItem(pmi);
		try{
            RestoAppApplication.save();
        }
        catch(RuntimeException e){
            throw new InvalidInputException(e.getMessage());
        }
	}
	public static void removeMenuItem(MenuItem menuItem) throws InvalidInputException{
		
		if (menuItem == null) {
			throw new InvalidInputException("Must choose a valid menu item to remove. ");
        }
		if (!menuItem.hasCurrentPricedMenuItem()) {
			throw new InvalidInputException("Menu item to remove is not a current priced menu item. ");
        }
		
			System.out.println(menuItem.setCurrentPricedMenuItem(null));
			try {
				RestoAppApplication.save();
			} catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
			
	}
	public static void updateMenuItem(MenuItem menuItem, String name, ItemCategory category, double price) throws InvalidInputException{
		if (menuItem == null) {
			throw new InvalidInputException("Must choose a valid menu item to update. ");
        }
		if (!menuItem.hasCurrentPricedMenuItem()) {
			throw new InvalidInputException("Menu item to update is not a current priced menu item. ");
        }
		if (name == null){
			throw new InvalidInputException("Must specify the name of the menu item to update. ");
        }
		if (name.isEmpty()) {
			throw new InvalidInputException("Need to enter a valid name in order to update a menu item. ");
        }
		if (category == null){
			throw new InvalidInputException("Must specify the category of the menu item to update. ");
        }
		if (price < 0){
			throw new InvalidInputException("The price must be positive. ");
        }
		if (price == 0){
			throw new InvalidInputException("The price can't be 0$. ");
        }
		if(menuItem.setName(name) == false) {
			throw new InvalidInputException("Can't update name. ");
		}
		menuItem.setName(name);
		menuItem.setItemCategory(category);
		if(price!=menuItem.getCurrentPricedMenuItem().getPrice()) {
		RestoApp r = RestoAppApplication.getRestoApp();
		PricedMenuItem pmi = menuItem.addPricedMenuItem(price, r);
		menuItem.setCurrentPricedMenuItem(pmi);
		}
		try{
            RestoAppApplication.save();
        }
        catch(RuntimeException e){
            throw new InvalidInputException(e.getMessage());
        }
	}

	public static void createTable(int number, int x, int y, int width, int length, int numberOfSeats)
			throws InvalidInputException {
		if (x < 0 || y < 0 || width <= 0 || length <= 0 || numberOfSeats <= 0) {
			throw new InvalidInputException("Invalid input, negative numbers not accepted");
		}
		RestoApp r = RestoAppApplication.getRestoApp();
		r.reinitialize();
		List<Table> currentTables = r.getCurrentTables();

		for (Table currentTable : currentTables) {
			Boolean overlap = currentTable.doesOverlap(x, y, width, length);
			if (overlap == true) {
				throw new InvalidInputException(
						"Error: the table you are trying to create overlaps with a previously existing one");
			}
			if (currentTable.getNumber() == number) {
				System.out
						.println("currentTable number is: " + currentTable.getNumber() + "and input number: " + number);
				throw new InvalidInputException("Table number already exists");
			}
		}

		Table table;
		String error = "";
		// try {
		System.out.print(number);
		System.out.print(x);
		System.out.print(y);
		System.out.print(width);
		System.out.print(length);
		System.out.println("\n");
		table = new Table(number, x, y, width, length, r);

		r.addCurrentTable(table);
		if (number == 201115)
		{
			r.setTakeOutTable(table);
			System.out.println("check: table with special ID 201115 has been set as takeout table");
		}
		for (int i = 1; i <= numberOfSeats; i++) {
			Seat seat = table.addSeat();
			table.addCurrentSeat(seat);
		}
		try {
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}
	// good code

	public static void createTakeOut(String aName, int aPhoneNumber) throws InvalidInputException
	{
		RestoApp resto = RestoAppApplication.getRestoApp();
		Table gottenTakeOutTable = resto.getTakeOutTable();
		//an order was previously created and is now passed
		gottenTakeOutTable.startOrder();
		Order aOrder = gottenTakeOutTable.getOrder(0);
//		aOrder.addTable(gottenTakeOutTable);
//		gottenTakeOutTable.addOrder(aOrder);
		resto.addTakeOut(aPhoneNumber, aName, aOrder);
	}
	public static void removeTable() throws InvalidInputException {
		String error = "";
		if(tableList.size() > 1) error = "You have selected too many tables";
		if(tableList.isEmpty()) error = "Must enter a table "; 
		Table selectedTable = tableList.get(0);
		

		boolean reserved = selectedTable.hasReservations();
		if (reserved) {
			error = error + "Cannot remove table because it is reserved. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		RestoApp r = RestoAppApplication.getRestoApp();

		List<Order> currentOrders = r.getCurrentOrders();
		///added spencer here 
		Table takeOutTester = r.getTakeOutTable();
		if (selectedTable == takeOutTester)
		{
		error = "Cannot remove TakeOutTable";
		throw new InvalidInputException(error);
		}
		//end 
		for (Order order : currentOrders) {
			List<Table> tables = order.getTables();
			boolean inUse = tables.contains(selectedTable);
			if (inUse) {
				throw new InvalidInputException("Cannot remove table because it is in use.");
			}
		}

		r.removeCurrentTable(selectedTable);
		try {
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}


	public static void updateTable(int aNumber, int seats) throws InvalidInputException {
		String error = "";
		if(tableList.size() > 1) error = "You have selected too many tables";
		if(tableList.isEmpty()) error = "Must enter a table "; 
		Table selectedTable = tableList.get(0);
		if (aNumber < 0) {
			error = error + "The table number must be positive. ";
		}
		if (seats < 0) {
			error = error + "The number of seats must be positive. ";
		}
		boolean reserved = selectedTable.hasReservations();
		if (reserved) {
			error = error + "Cannot remove table because it is reserved. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		RestoApp r = RestoAppApplication.getRestoApp();

		List<Order> currentOrders = r.getCurrentOrders();

		for (Order order : currentOrders) {

			List<Table> tables = order.getTables();

			boolean inUse = tables.contains(selectedTable);
			if (inUse) {
				throw new InvalidInputException("Cannot update table because it is in use.");
			}

		}

		try {
			selectedTable.setNumber(aNumber);
		} catch (RuntimeException e) {
			throw new InvalidInputException("Table number cannot be a duplicate");
		}

		int n = selectedTable.numberOfCurrentSeats();

		for (int i = 1; i <= seats - n; i++) {
			Seat seat = selectedTable.addSeat();
			selectedTable.addCurrentSeat(seat);
		}

		for (int i = 1; i <= n - seats; i++) {
			Seat seat = selectedTable.getCurrentSeat(0);
			selectedTable.removeCurrentSeat(seat);
		}

		try {
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	public static void updateDimension(int width, int length) throws InvalidInputException {
		String error = "";
		
		if(tableList.size() > 1) error = "You have selected too many tables";
		if(tableList.isEmpty()) error = "Must enter a table "; 
		Table selectedTable = tableList.get(0);
		
		
		if (width < 0 && length < 0) {
			error = "The dimensions you have entered are negative";
		} else if (width < 0) {
			error = "The width is negative";
		} else if (length < 0) {
			error = "The lnegth is negative";
		}

		if (error.length() > 0)
			throw new InvalidInputException(error);

		int x = selectedTable.getX();
		int y = selectedTable.getY();

		RestoApp restoapp2 = RestoAppApplication.getRestoApp();

		List<Table> currentTables = restoapp2.getCurrentTables();

		for (Table currentTable : currentTables) {
			boolean isError = false;
			if(currentTable != selectedTable)
			isError = currentTable.doesOverlap(x, y, width, length);

			if (isError == true)
				throw new InvalidInputException("Error: This table overlaps with another!");
		}

		selectedTable.setWidth(width);
		selectedTable.setLength(length);

		try {
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void moveTable(int x, int y) throws InvalidInputException {
		String error = "";

		if(tableList.size() > 1) error = "You have selected too many tables";
		if(tableList.isEmpty()) error = "Must enter a table"; 
		Table selectedTable = tableList.get(0);
		
		if (x < 0 && y < 0) {
			error = "The coordinate you have entered are negative";
		} else if (x < 0) {
			error = "The x coordinate is negative";
		} else if (y < 0) {
			error = "The y coordinate is negative";
		}

		if (error.length() > 0)
			throw new InvalidInputException(error);

		int width = selectedTable.getWidth();
		int length = selectedTable.getLength();

		RestoApp restoapp2 = RestoAppApplication.getRestoApp();

		List<Table> currentTables = restoapp2.getCurrentTables();

		for (Table currentTable : currentTables) {
			boolean isError = currentTable.doesOverlap(x, y, width, length);

			if (isError == true)
				throw new InvalidInputException("Error: This table overlaps with another!");
		}

		selectedTable.setX(x);
		selectedTable.setY(y);

		try {
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static ItemCategory[] getItemCategories() {
		ItemCategory[] i = ItemCategory.values();
		return i;
	}

	public static List<MenuItem> getMenuItems(ItemCategory itemCategory) throws InvalidInputException {
		if (itemCategory == null)
			throw new InvalidInputException("Error: This category does not exist!");
		List<MenuItem> result = new ArrayList<MenuItem>();

		RestoApp restoApp = RestoAppApplication.getRestoApp();
		Menu menu2 = restoApp.getMenu();
		for(MenuItem item : menu2.getMenuItems()) {
			if(item.getCurrentPricedMenuItem()!=null)
			System.out.println(item.getName() + item.getCurrentPricedMenuItem().getPrice() );
	
			
			boolean current = item.hasCurrentPricedMenuItem();
			ItemCategory category = item.getItemCategory();
			if (current && category.equals(itemCategory)) {
				result.add(item);
				System.out.println(item.getName());
			}
		}
		return result;
	}

	public static void reserve(String date, String time, int numberInParty, String contactName,
			String contactEmailAddress, String contactPhoneNumber) throws InvalidInputException {

		String datetext = date;
		String year = datetext.substring(0, 4);
		String month = datetext.substring(5,7);
		String day = datetext.substring(8,10);
		
		String timetext = time;
		String hour = timetext.substring(0, 2);
		String minute= timetext.substring(3, 5);
		
		java.sql.Date inputDate = new java.sql.Date(Integer.parseInt(year)-1900, Integer.parseInt(month)-1, Integer.parseInt(day));
		java.sql.Time inputTime = new java.sql.Time(Integer.parseInt(hour), Integer.parseInt(minute), 0);
		
		int seatCapacity = 0;
		String error = "";
		if (date == "") {
			error = "Invalid date";
		}
		if (time == "") {
			error += "Invalid time";
		}
		if (numberInParty <= 0) {
			error += "Invalid number in party";
		}
		if (contactName == null || contactEmailAddress == null || contactPhoneNumber == null) {
			error += "Invalid contact information";
		}
		if (tableList == null) {
			error += "Invalid table list";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		RestoApp resto = RestoAppApplication.getRestoApp();
		List<Reservation> reservations = new ArrayList<Reservation>();
		List<Table> currentTables = resto.getCurrentTables();

		for (Table table : tableList) {
			Boolean current = currentTables.contains(table);
			if (current == false) {
				throw new InvalidInputException("Invalid table");
			}
			seatCapacity += table.numberOfCurrentSeats();
			reservations = table.getReservations();
			for (Reservation reservation : reservations) {
				System.out.println("shabi");
				Boolean overlaps = reservation.doesOverlap(inputDate, inputTime);
				System.out.println("shabi2");
				if (overlaps == true) {
					System.out.println("shabi3");
					throw new InvalidInputException("Date/Time overlaps");
				}
			}
		}
		System.out.println("shabi4");
		if (seatCapacity < numberInParty) {
			throw new InvalidInputException("Seat Capacity insufficient");
		}
		Table[] tableArray = tableList.toArray(new Table[tableList.size()]);
		System.out.println("shabi5");
		Reservation res = new Reservation(inputDate, inputTime, numberInParty, contactName, contactEmailAddress,
				contactPhoneNumber, resto, tableArray);
		try {
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static List<Reservation> getReservation() {
		RestoApp r = RestoAppApplication.getRestoApp();		
		return r.getReservations();
	}
	
	public static void deleteReservation() throws InvalidInputException{
		if(res == null) throw new InvalidInputException("No reservation selected");
		if(!getReservation().contains(res)) throw new InvalidInputException("Not active reservation");
		

		res.delete();
		
		try {
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	
	
	
	public static void orderMenuItem(MenuItem menuItem, int quantity) throws InvalidInputException {
		String error = "";
		if (menuItem == null || seatList == null) {
			error = "Seats or menu item don't exist";
		} else if (seatList == null || quantity < 0) {
			error = "Seats is empty or quantity is not positive";
		} 
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		RestoApp r = RestoAppApplication.getRestoApp();		
		boolean current = menuItem.hasCurrentPricedMenuItem();
		
		if (current == false) {
			throw new InvalidInputException("Invalid menu item");
		}	
		
		List<Table> currentTables = r.getCurrentTables();
		Order lastOrder = null;	
		
		for(Seat seat : seatList) {		
			Table table = seat.getTable();
			current = currentTables.contains(table);
			if (current == false) {
				throw new InvalidInputException ("Invalid table");
			}	
			
			List<Seat> currentSeats = table.getCurrentSeats();
			current = currentSeats.contains(seat);
			
			if (current == false) {
				throw new InvalidInputException ("Invalid seat");
			}
			if (lastOrder == null) {
				if(table.numberOfOrders() > 0) {
					lastOrder = table.getOrder(table.numberOfOrders()-1);
				}
				else {
					throw new InvalidInputException ("Table doesn't have an order");
				}				
			}
			else {
				Order comparedOrder = null;
				if (table.numberOfOrders() > 0) {
					comparedOrder = table.getOrder(table.numberOfOrders()-1);
				}
				else {
					throw new InvalidInputException("Table doesn't have an order");
				}
				
				if (!comparedOrder.equals(lastOrder)) {
					throw new InvalidInputException("Table doesn't have an order");
					
				}
			}		
		}
		
		if(lastOrder == null) {
			throw new InvalidInputException ("Last Order doesn't exist");
		}
		
		PricedMenuItem pmi = menuItem.getCurrentPricedMenuItem();
		boolean itemCreated = false;
		OrderItem newItem = null;
		
		for (Seat seat : seatList) {
			Table table = seat.getTable();
			
			if (itemCreated) {
				table.addToOrderItem(newItem, seat);
			}
			else {
				OrderItem lastItem = null;
				
				if (lastOrder.numberOfOrderItems() > 0) {
					lastItem = lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1);
				}
				
				table.orderItem(quantity, lastOrder, seat, pmi);
				
				if (lastOrder.numberOfOrderItems() > 0 && !lastOrder.getOrderItem(lastOrder.numberOfOrderItems()-1).equals(lastItem)) {
					itemCreated = true;
					newItem = lastOrder.getOrderItem(lastOrder.numberOfOrderItems()-1);
				}
	
			}
		}
		
		if (itemCreated == false) {
			throw new InvalidInputException ("No order item created");
		}
		
		try{
            RestoAppApplication.save();
        }
        catch(RuntimeException e){
            throw new InvalidInputException(e.getMessage());
        }	
	}
	
	/**
	public static void orderMenuItem(MenuItem menuItem, int quantity) throws InvalidInputException{
		Order order = selectedTable.getOrder(0);
		if (quantity <= 0 || menuItem == null || seatList == null) {
			throw new InvalidInputException("Invalid Inputs");
		}
		
		List<Table> tables = order.getTables();
		boolean itemCreated = false;
		OrderItem orderItem = null;
		
		if (!menuItem.hasCurrentPricedMenuItem()) {
			throw new InvalidInputException("Menuitem doesn't have price");
		}
		PricedMenuItem pmi = menuItem.getCurrentPricedMenuItem();
		
				
		for (Seat seat : seatList) {
			Table table = seat.getTable();
			if (!tables.contains(table) || !table.getCurrentSeats().contains(seat) || table.getStatusFullName() == "Available") {
				throw new InvalidInputException("Order and seats don't match");
			}
			
			if (itemCreated) {
				if (orderItem != null) {
					table.addToOrderItem(orderItem, seat);
				}
				
			}
			else {
				table.orderItem(quantity, order, seat, pmi);
				itemCreated = true;
				orderItem = order.getOrderItem(order.numberOfOrderItems()-1);
			}
		}
		
		try {
			RestoAppApplication.save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}*/
	/*
    public static void orderMenuItem(MenuItem menuItem, int quantity) throws InvalidInputException {

        if (!(quantity > 0)) {
            throw new InvalidInputException("Please enter a positive quantity.\n");
        }
        if (menuItem == null) {
            throw new InvalidInputException("Please enter a valid MenuItem.\n");
        }
        if (seatList == null) {
            throw new InvalidInputException("Please enter valid seats.\n");
        }
        if (seatList.isEmpty()) {
            throw new InvalidInputException("No seats in list.\n");
        }

        RestoApp r = RestoAppApplication.getRestoApp();

        boolean current = menuItem.hasCurrentPricedMenuItem();
        if (!current) {
            throw new InvalidInputException("The ordered MenuItem does not exist.\n");
        }

        List<Table> currentTables = r.getCurrentTables();
        Order lastOrder = null;

        for (Seat seat : seatList) {
            Table table = seat.getTable();

            current = currentTables.contains(table);
            if (!current) {
                throw new InvalidInputException("The table associated with the seat is not currently active.\n");
            }

            List<Seat> currentSeats = table.getCurrentSeats();

            current = currentSeats.contains(seat);
            if (!current) {
                throw new InvalidInputException("The seat is not currently active.\n");
            }

            if (lastOrder == null) {
                if (table.numberOfOrders() > 0) {
                    lastOrder = table.getOrder(table.numberOfOrders() - 1);
                } else {
                    throw new InvalidInputException("No order associated with table.\n");
                }
            } else {
                Order comparedOrder = null;

                if (table.numberOfOrders() > 0) {
                    comparedOrder = table.getOrder(table.numberOfOrders() - 1);
                } else {
                    throw new InvalidInputException("No order associated with table.\n");
                }

                if (!comparedOrder.equals(lastOrder)) {
                    throw new InvalidInputException("Last order does not match compared last order.\n");
                }
            }
        }

        if (lastOrder == null) {
            throw new InvalidInputException("No Order found.\n");
        }

        PricedMenuItem pmi = menuItem.getCurrentPricedMenuItem();
        boolean itemCreated = false;
        OrderItem newItem = null;

        for (Seat seat : seatList) {
            Table table = seat.getTable();

            if (itemCreated) {
            	
                table.addToOrderItem(newItem, seat);
            } else {
            	System.out.println("ouba");
                OrderItem lastItem = null;

                if (lastOrder.numberOfOrderItems() > 0) {
                    lastItem = lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1);
                    System.out.println("ouaa");
                }

                table.orderItem(quantity, lastOrder, seat, pmi);

                if ((lastOrder.numberOfOrderItems() > 0)
                        && (!lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1).equals(lastItem))) {
                    itemCreated = true;
                    newItem = lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1);
                }
            }
        }

        if (!itemCreated) {
            throw new InvalidInputException("OrderItem not sucessfully created.\n");
        }

        try{
            RestoAppApplication.save();
        }
        catch(RuntimeException e){
            throw new InvalidInputException(e.getMessage());
        }	

    }*/

	
	//Cancel order Item
	public static void cancelOrderItem() throws InvalidInputException {
		

		if (selectedoi == null) throw new InvalidInputException("OrderItem does not exist");

		List<Seat> seats = selectedoi.getSeats();
		Order order = selectedoi.getOrder();
		
		List<Table> tables = new ArrayList<Table>();
		
		for(Seat seat : seats) {
			Table table = seat.getTable();
			
			Order lastOrder = null;
			
			if(table.numberOfOrders() > 0) {
				lastOrder = table.getOrder(table.numberOfOrders() - 1);
			}else {
				throw new InvalidInputException("Table doesn't have an order");
			}
			
			if(lastOrder.equals(order) && !(tables.contains(table))) {
				tables.add(table);
			}
		}
		
		for(Table table : tables) {
			table.cancelOrderItem(selectedoi);
			}
		selectedoi.delete();
		

		try{
            RestoAppApplication.save();
        }
        catch(RuntimeException e){
            throw new InvalidInputException(e.getMessage());
        }
	}	

	
	
	//Cancel order
	public static void cancelOrder() throws InvalidInputException {
		
		if(tableList.size() > 1) throw new InvalidInputException("You have selected too many tables");
		if(tableList.isEmpty() || tableList == null) throw new InvalidInputException("Must enter a table"); 
		Table selectedTable = tableList.get(0);
		
		RestoApp resto = RestoAppApplication.getRestoApp();
		List<Table> currentTables = resto.getCurrentTables();
		
		boolean current = currentTables.contains(selectedTable);
		
		if (current == false) throw new InvalidInputException("Table is not in current tables");
		
		selectedTable.cancelOrder();
		// what i added to make it work
		//the order is removed, but once you start a new one at same table, the old ones appear
		for(OrderItem oi : getOrderItems())
		{
			oi.delete();
		}
		//end of what i added
		try{
            RestoAppApplication.save();
        }
        catch(RuntimeException e){
            throw new InvalidInputException(e.getMessage());
        }
	}
	//start order
	public static void startOrder () throws InvalidInputException{
		if (tableList == null) {
			throw new InvalidInputException("Invalid Input");
		}
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		List<Table> currentTables = restoApp.getCurrentTables();
		for (Table table : tableList) {
			if (!currentTables.contains(table)) {
				throw new InvalidInputException("Table not in CurrentTables list");
			}
		}
		boolean orderCreated = false;
		Order newOrder = null;
		for (Table table : tableList) {
			if (orderCreated) {
				table.addToOrder(newOrder);
			}
			else {
				Order lastOrder = null;
				if (table.numberOfOrders() > 0) {
					lastOrder = table.getOrder(table.numberOfOrders()-1);
				}
				table.startOrder();
				if (table.numberOfOrders() > 0 && !table.getOrder(table.numberOfOrders()-1).equals(lastOrder)) {
					orderCreated = true;
					newOrder = table.getOrder(table.numberOfOrders()-1);
				}
			}
		}
		if(orderCreated == false) {
			throw new InvalidInputException("No table selected or table already has an order");
		}
		restoApp.addCurrentOrder(newOrder);
		try {
			RestoAppApplication.save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

  //end order
	public static void endOrder() throws InvalidInputException{
		
		if(tableList.size() > 1) throw new InvalidInputException("You have selected too many tables");
		if(tableList.isEmpty()) throw new InvalidInputException("Must enter a table"); 
		Table selectedTable = tableList.get(0);
		
		
		if (selectedTable == null) 
			throw new InvalidInputException("Invalid Table");
		if(selectedTable.getOrders().size() == 0) throw new InvalidInputException("No Order Started for Selected Table");
		Order order = selectedTable.getOrder(0);
		
		
		if (order == null) {
			throw new InvalidInputException("Invalid Order");
		}
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		if (!restoApp.getCurrentOrders().contains(order)) {
			throw new InvalidInputException("Order not active");
		}
		List<Table> tablesList = order.getTables();
		Table[] tables = tablesList.toArray(new Table[tablesList.size()]);

		try {
			for (Table table : tables) {
				//System.out.println("Table" + table.getNumber());
				if (table.numberOfOrders() > 0 && table.getOrder(table.numberOfOrders()-1).equals(order)) {
					table.endOrder(order);
				}
			}
			if (allTablesAvailableOrDifferentCurrentOrder(tablesList, order)) {
				restoApp.removeCurrentOrder(order);
				//System.out.println("removed");
				RestoAppApplication.save();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
try {
			
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		
		
	}
	public static boolean allTablesAvailableOrDifferentCurrentOrder(List<Table> tables, Order order) {
		boolean canRemove = true;
		for (Table table : tables) {
			if (table.getStatusFullName() != "Available" || table.getOrder(table.numberOfOrders()-1).equals(order)) {
				
				canRemove = false;
				break;
				
			}
		}
		return canRemove;
	}
 //get order items
	public static List<OrderItem> getOrderItems() throws InvalidInputException{
		String error = "";
		if(tableList.size() > 1) throw new InvalidInputException("You have selected too many tables");
		if(tableList.isEmpty()) throw new InvalidInputException("Must enter a table"); 
	
		Table selectedTable = tableList.get(0);
		
		RestoApp resto = RestoAppApplication.getRestoApp();
		List<Table> currentTables = resto.getCurrentTables();
		Boolean current = currentTables.contains(selectedTable);
		
		if(current == false) {
			
			throw new InvalidInputException("Table not in the current tables");
		}
		
		String status = selectedTable.getStatusFullName();
		
		if(status == "Available") {
			throw new InvalidInputException("No Order Started");
		}
		
		Order lastOrder = null;

		List<Seat> currentSeats = selectedTable.getCurrentSeats();
		List<OrderItem> result = new ArrayList<OrderItem>();
		
		if(selectedTable.numberOfOrders() > 0) {
			lastOrder = selectedTable.getOrder(selectedTable.numberOfOrders() - 1);
		}
		else {
			throw new InvalidInputException("Table does not contain any orders");
		}
		
		
		for(Seat seat: currentSeats) {
			List<OrderItem> orderItems = seat.getOrderItems();
			for(OrderItem orderItem: orderItems) {
				Order order = orderItem.getOrder();
				if(lastOrder.equals(order) && !result.contains(orderItem)) {
					result.add(orderItem);
					lastOrder = order;
				}
			}
		}
		
		return result;
		
	}
//issue bill
	public static void issueBill() throws InvalidInputException {
		if (seatList == null || seatList.isEmpty())
			throw new InvalidInputException("Nothing is selected");

		RestoApp restoApp = RestoAppApplication.getRestoApp();
		List<Table> currentTables = restoApp.getCurrentTables();
		Order lastOrder = null;

		for (Seat aseat : seatList) {
			Table table = aseat.getTable();
			boolean current = currentTables.contains(table);
			if (!current)
				throw new InvalidInputException("Table not in use");
			List<Seat> currentSeats = table.getCurrentSeats();
			current =  currentSeats.contains(aseat);
			if (!current)
				throw new InvalidInputException("Seat not in use");

			if (lastOrder == null) {
				if (table.numberOfOrders() > 0) {
					lastOrder = table.getOrder(table.numberOfOrders() - 1);
				} else {
					throw new InvalidInputException("No Order");
				}

			} else {
				Order comparedOrder = null;
				if (table.numberOfOrders() > 0) {
					comparedOrder = table.getOrder(table.numberOfOrders() - 1);
				} else {
					throw new InvalidInputException("No Order");
				}
				if (!comparedOrder.equals(lastOrder)) {
					throw new InvalidInputException("No Order");
				}
			}
		}

		if (lastOrder == null)
			throw new InvalidInputException("Still no order");
		Boolean billCreated = false;
		Bill newBill = null;

		for (Seat seat : seatList) {
			Table table = seat.getTable();
			if (billCreated) {
				table.addToBill(newBill, seat);
			} else {
				Bill lastBill = null;
				if (lastOrder.numberOfBills() > 0) {
					lastBill = lastOrder.getBill(lastOrder.numberOfBills() - 1);
				}
				table.billForSeat(lastOrder, seat);
				if (lastOrder.numberOfBills() > 0
						&& !lastOrder.getBill(lastOrder.numberOfBills() - 1).equals(lastBill)) {
					billCreated = true;
					newBill = lastOrder.getBill(lastOrder.numberOfBills() - 1);
				}
			}
		}
		if (!billCreated) {
			throw new InvalidInputException("The bill was not created");
		}	
		
		
		
		try {
			
			RestoAppApplication.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static List<String> viewBill() throws InvalidInputException {
		List<Bill> bills = new ArrayList<Bill>();
		List<String> list = new ArrayList<String>();
		RestoApp r = RestoAppApplication.getRestoApp();
		for(Order order : r.getOrders()) {
			for (Bill bill : order.getBills()) {
				bills.add(bill);
			}	
		}
		for(Bill b : bills) {
			Double price = 0.0;
			System.out.println(b.getIssuedForSeats());
			for(Seat s : b.getIssuedForSeats()) {
			
				for( OrderItem oi : s.getOrderItems()) {
					price += oi.getPricedMenuItem().getPrice()/oi.getQuantity();
				}
			}
			list.add("Bill: " + (new DecimalFormat("##.##").format(price)) + " $");
		}
		return list;
	}
}