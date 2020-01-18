package ca.mcgill.ecse223.resto.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
//import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class Order extends JPanel {
	
	JPanel orderPage;
	JPanel panel;
	HashMap<JButton, OrderItem> itemMap;
	JScrollPane pane = new JScrollPane(orderPage);
	//orderPage.add(pane);
	JLabel viewOrder;
	JButton Back;
	JButton Cancel;
	JButton CancelItem;
	JButton IssueBill;
	JButton add;
	List<JButton> OrderItems = new ArrayList<JButton>();
	
	public static JButton theButton;
	
	Order(){
		super();
		init();
	}
	
	
	

	public Order(Date date, Time time, RestoApp resto, List<Table> tables) {
		// TODO Auto-generated constructor stub
	}




	public void init(){
		itemMap =  new HashMap<JButton, OrderItem>();
		
		this.setLayout(null);
		this.setBounds(640, 0, 640, 720);
		this.setOpaque(true);
		
		panel = new JPanel();
		
		panel.setLayout(new GridLayout(0,1));
		panel.setBounds(0, 150, 640, 400);
		   panel.setOpaque(false);
		
		
		/*orderPage = new JPanel();
		orderPage.setLayout(new GridLayout(6,2));
		orderPage.setOpaque(false);
		orderPage.setBounds(150, 200, 340, 370);*/
		
		Back = createButton("Back");
		Back.setBounds(50, 600, 100, 30);
		
		Cancel = createButton("Cancel Order");
		Cancel.setBounds(this.getWidth()/2-100, 550, 200, 50);
		
		CancelItem = createButton("Cancel Order Item");
		CancelItem.setBounds(this.getWidth()/2-150, 585, 300, 50);
		
		IssueBill = createButton("Issue Bill");
		IssueBill.setBounds(this.getWidth()/2-100, 620, 200, 50);
		
		viewOrder = createLabel("View Order");
		//viewOrder.setBounds(50,50, 540,100);
		viewOrder.setFont(new Font("Avenir Next", Font.BOLD, 30));
		viewOrder.setBounds(0,50, 640,100);
		
		add = createButton("+");
		add.setFont(new Font("Avenir Next", Font.BOLD, 50));
		add.setBounds(490, 600, 100, 30);
		
		


		this.add(add);
		this.add(viewOrder);
		this.add(Back);
		this.add(Cancel);
		this.add(CancelItem);
		this.add(IssueBill);
		this.add(panel);
		
		this.setVisible(false);	
	}

public JLabel createLabel(String name) {
	JLabel label = new JLabel(name);
	label.setFont(new Font("Avenir Next", Font.PLAIN, 25));
	label.setForeground(Color.white);
	label.setHorizontalAlignment(JLabel.CENTER);
	label.setSize(500, 200);
	// label.setBounds(boundaries[0],boundaries[1],boundaries[2],boundaries[3]);
	label.setOpaque(false);
	label.setVisible(true);
	return label;
}

public JTextField createTextField(int inputfield) {
	JTextField textfield = new JTextField(inputfield);
	// textfield.setBounds(boundaries[0],boundaries[1],boundaries[2],boundaries[3]);
	textfield.setForeground(Color.WHITE);
	textfield.setFont(new Font("avenir Next", Font.PLAIN, 25));
	textfield.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	textfield.setBackground(null);
	textfield.setOpaque(false);
	textfield.setHorizontalAlignment(JTextField.CENTER);
	Color c = new Color(0f, 0f, 0f, 0f);
	textfield.getCaret().setBlinkRate(0);
	textfield.setCaretColor(c);
	return textfield;
}

public JButton createButton(String name) {
	JButton button = new JButton(name);
	button.setFont(new Font("Avenir Next", Font.PLAIN, 25));
	button.setForeground(Color.white);
	// button.setBounds(boundaries[0],boundaries[1],boundaries[2],boundaries[3]);
	button.setOpaque(false);
	button.setContentAreaFilled(false);
	button.setBorderPainted(false);
	button.setVisible(true);
	button.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			button.setForeground(Color.LIGHT_GRAY);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			button.setForeground(Color.white);
		}
	});
	return button;
}

public void refresh (List<OrderItem> oi) {
	
	
	
	panel.setVisible(false);
	for(JButton button : OrderItems) {
		panel.remove(button);
	}
	OrderItems.clear();
	panel.removeAll();
	//System.out.println(panel.is);
	itemMap.clear();
	for(OrderItem o : oi) {
		//System.out.println("fsfdasf");
		JButton button;
		if(o.getQuantity()!=1) 
		button = createButton(o.getPricedMenuItem().getMenuItem().getName() + "(" +o.getQuantity() + ")");
		else button = createButton(o.getPricedMenuItem().getMenuItem().getName());
		OrderItems.add(button);
		itemMap.put(button, o);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestoAppController.selectedoi = itemMap.get(button);
				if(theButton == button) theButton = null;
				else theButton = button;
				//System.out.println(RestoAppController.selectedoi.getPricedMenuItem().getMenuItem().getName());
			}
		});
		//System.out.println("rweedadfr");
		
		
		panel.add(button);
		panel.setVisible(true);
		this.add(panel);
		System.out.println("rweer");
	}
	
}

public void erase() {
	panel.setVisible(false);
	panel.remove(theButton);
	panel.setVisible(true);
}

public void paintComponent(Graphics g) { // DONT TOUCH THIS CLASS PLEASE, ITS THE CLASS THAT MAKES THE BIG MENU

	// RECTANGLE
	super.paintComponent(g);
	Graphics2D graphics2 = (Graphics2D) g;
	Color vert = new Color(69, 214, 165);
	Color bleu = new Color(55, 164, 234);
	RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float( 50, 50,
			540, 620, 50, 50);
	GradientPaint cyanToblue = new GradientPaint(600, 145, vert, 300, 760, bleu);
	graphics2.setPaint(cyanToblue);
	graphics2.fill(roundedRectangle);
	graphics2.draw(roundedRectangle);
}

}