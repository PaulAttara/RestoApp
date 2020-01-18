
package ca.mcgill.ecse223.resto.view;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Table;

public class Reservation extends JPanel{

	JPanel inputsZone;
	JPanel list;

	JLabel titleText;
	JLabel dateText;
	JLabel timeText;
	JLabel numberPeopleText;
	JLabel contactNameText;
	JLabel contactEmailText;
	JLabel contactPhoneText;
	
	JTextField title;
	JTextField date;
	JTextField time;
	JTextField numberPeople;
	JTextField contactName;
	JTextField contactEmail;
	JTextField contactPhone;
	
	JButton reserve;
	JButton view;
	JButton Back;
	JButton smallBack;
	JButton delete;
	    
	List<JButton> buttons;
	HashMap<JButton, ca.mcgill.ecse223.resto.model.Reservation> resMap;
	
	  Time inputTime;
	  Date inputDate;
	
	Reservation(){
		super();
		init();
	}
	
	public void init(){
		
		
		
		this.setLayout(null);
		this.setBounds(640, 0, 640, 720);
		this.setOpaque(true);
		
		list = new JPanel();
		list.setLayout(new GridLayout(0,1));
		list.setOpaque(false);
		list.setBounds(150, 200, 340, 370);
		list.setVisible(false);
		
		inputsZone = new JPanel();
		inputsZone.setLayout(new GridLayout(6,2));
		inputsZone.setOpaque(false);
		inputsZone.setBounds(150, 200, 340, 370);
		
		Back = createButton("back");
		Back.setBounds(50, 600, 100, 30);
		reserve = createButton("Reserve");
		reserve.setBounds(150, 600, 340, 50);
		smallBack = createButton("back");
		smallBack.setBounds(50, 600, 100, 30);
		view = createButton("view");
		view.setBounds(490, 600, 100, 30);
		delete = createButton("Delete");
		delete.setBounds(150, 600, 340, 50);
		delete.setVisible(false);
		
		
		titleText = createLabel("Reservation");
		titleText.setBounds(50,50, 540,100);
		dateText = createLabel("Date");
		timeText = createLabel("Time");
		numberPeopleText = createLabel("Participants");
		contactNameText = createLabel("Contact Name"); 
		contactEmailText = createLabel("Email");
		contactPhoneText = createLabel("Phone Number");
		
		
		date = createTextField(5);
		
		
		time = createTextField(5);
		
		numberPeople = createTextField(5);
		contactName = createTextField(5);
		contactEmail = createTextField(5);
		contactPhone = createTextField(5);
		date.setText("yyyy-MM-dd");
		time.setText("hh:mm");
		
		
		 smallBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					list.setVisible(false);
					smallBack.setVisible(false);
					delete.setVisible(false);
					view.setVisible(true);
					inputsZone.setVisible(true);
					Back.setVisible(true);
					reserve.setVisible(true);
					titleText.setVisible(true);
					
				}
				});
		   view.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					list.setVisible(true);
					smallBack.setVisible(true);
					delete.setVisible(true);
					view.setVisible(false);
					inputsZone.setVisible(false);
					Back.setVisible(false);
					reserve.setVisible(false);
					titleText.setVisible(false);
					refresh();
				}
				});
		
		   
		   buttons = new ArrayList<JButton>();
			resMap = new HashMap<JButton, ca.mcgill.ecse223.resto.model.Reservation>();
			refresh();
		
		inputsZone.add(dateText);
		inputsZone.add(date);
		inputsZone.add(timeText);
		inputsZone.add(time);
		inputsZone.add(numberPeopleText);
		inputsZone.add(numberPeople);
		inputsZone.add(contactNameText);
		inputsZone.add(contactName);
		inputsZone.add(contactEmailText);
		inputsZone.add(contactEmail);
		inputsZone.add(contactPhoneText);
		inputsZone.add(contactPhone);
		
		
		this.add(Back);
		this.add(titleText);
		this.add(inputsZone);
		this.add(reserve);
		this.add(smallBack);
		this.add(view);
		this.add(list);
		this.add(delete);
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
	
	public void refresh() {
		for(JButton b : buttons) {
			list.remove(b);
		}
		//clear the list
		buttons.clear();
		
		for(ca.mcgill.ecse223.resto.model.Reservation r : RestoAppController.getReservation()) {
			JButton button = createButton(r.getDate().toString() + " " + r.getTime().toString()+ " " + r.getContactName());
			buttons.add(button);
			resMap.put(button, r);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RestoAppController.res = resMap.get(button);
				}
				});
			list.add(button);
		}
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


