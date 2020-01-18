package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.View;

public class MainMenu extends JPanel {

	JButton welcomeLabel;
	JButton emptySpace;
	JButton TableSetup;
	JButton ViewMenu;
	JButton StartOrder;
	JButton Reservation;
	JButton Order;
	JButton TakeOut;
	JButton EndOrder;
	JButton viewBill;
	JButton emptySpace2;
	
	MainMenu(){
		super();
		init();
	}
	
	public void init(){
		
		this.setLayout(new GridLayout(11,1));
		this.setBounds(640, 200, 640, 10);
		this.setOpaque(false);
		
		emptySpace = createButton(" ");
		emptySpace.setVisible(false);
		welcomeLabel = createButton("Welcome");
		welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD));
		TableSetup = createButton("Table Setup");
		ViewMenu = createButton("View Menu");
		StartOrder = createButton("Start Order");
		Reservation = createButton("Reservation");
		Order = createButton("View Order");
		TakeOut = createButton("Take Out");
		EndOrder = createButton("End Order");
		viewBill = createButton("View Bills");
		emptySpace2 = createButton(" ");
		
		/*/TableSetup.setVisible(false);
		TableInfo.setVisible(false);
		Menu.setVisible(false);
		Reservation.setVisible(false);*/
		
		
		
		this.add(emptySpace);
		this.add(welcomeLabel);
		this.add(TableSetup);
		this.add(Reservation);
		this.add(ViewMenu);
		this.add(Order);
		this.add(TakeOut);
		this.add(StartOrder);
		this.add(EndOrder);
		this.add(viewBill);
		this.add(emptySpace2);
		
		this.setVisible(true);
		
		
		
		
	}
	
	public JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("Avenir Next", Font.PLAIN, 25));
		button.setForeground(Color.white);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
