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

public class TakeOutPage extends JPanel{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	JPanel inputsZone;

	JLabel titleText;
	JLabel nameText;
	JLabel phoneNumberText;
	
	
	JTextField name;
	JTextField phoneNumber;
	

	JButton createTakeOut;
	JButton Back;
	
	  static Time inputTime;
	
	TakeOutPage(){
		super();
		init();
	}
	
	public void init(){
		
		
		
		this.setLayout(null);
		this.setBounds(640, 0, 640, 720);
		this.setOpaque(true);
		
		
		
		inputsZone = new JPanel();
		inputsZone.setLayout(new GridLayout(6,2));
		inputsZone.setOpaque(false);
		inputsZone.setBounds(150, 200, 340, 370);
		
		Back = createButton("Back");
		Back.setBounds(50, 600, 100, 30);
		createTakeOut= createButton("Create");
		createTakeOut.setBounds(150, 590, 340, 50);
		
		
		titleText = createLabel("Take Out");
		titleText.setBounds(50,50, 540,100);
		titleText.setFont(titleText.getFont().deriveFont(Font.BOLD));
		nameText = createLabel("Name");
		phoneNumberText = createLabel("Phone Number");
		
		
		name = createTextField(5);
		name.addFocusListener(new FocusAdapter() {
			  public void focusGained(FocusEvent fEvt) {
			    name.selectAll();
			  }
			});
		
		phoneNumber = createTextField(5);
		phoneNumber.addFocusListener(new FocusAdapter() {
			  public void focusGained(FocusEvent fEvt) {
				  phoneNumber.selectAll();
			  }
			});
//		numberPeople = createTextField(5);
//		contactName = createTextField(5);
//		contactEmail = createTextField(5);
//		contactPhone = createTextField(5);
//		date.setText("yyyy-MM-dd");
//		time.setText("hh:mm:ss");
		
		
		  
		   
		createTakeOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// to be addded for iteration 5	
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//java.sql.Date inputDate = null;
				//String myDate = date.getText();
				
				//java.sql.Name inputName =null;
				//inputName = inputName.valueOf(name.getText());
				//inputPhoneNumber = inputPhoneNumber.valueOf(phoneNumber.getText());
				//System.out.println("input date is " + name.getText() + " and input time is " + time.getText());
				
					try {
						//inputName = inputName.valueOf(name.getText());
						//System.out.println("input date is : " + inputDate + " and input time is : " + inputTime);
						//RestoAppController.createTakeOut(inputDate, inputTime.valueOf(time.getText()), Integer.parseInt(numberPeople.getText()),
						//		contactName.getText(),contactEmail.getText(),contactPhone.getText());
						RestoAppController.createTakeOut(name.getText(),Integer.parseInt(phoneNumber.getText()));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);	
					}
			
			}
			});
		
		inputsZone.add(nameText);
		inputsZone.add(name);
		inputsZone.add(phoneNumberText);
		inputsZone.add(phoneNumber);
		
		
		
		this.add(Back);
		this.add(titleText);
		this.add(inputsZone);
		this.add(createTakeOut);
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