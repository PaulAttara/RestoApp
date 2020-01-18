package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.model.Bill;

public class BillPanel extends JPanel {

	JPanel panel;
	JLabel title;
	JButton Back;
	

	public BillPanel() {
		super();
		init();
	}

	public void init() {

		this.setLayout(null);
		this.setBounds(640, 0, 640, 720);
		this.setOpaque(false);

		panel = new JPanel();

		panel.setLayout(new GridLayout(0, 1));
		panel.setBounds(100, 150, 440, 400);
		panel.setOpaque(false);
		
		title = createLabel("Bills");
		title.setBounds(0,50,640,150);
		
		Back = createButton("back");
		Back.setBounds(50, 600, 100, 50);
		Back.setVisible(true);
		
		this.add(panel);
		this.add(Back);
		this.add(title);
		this.setVisible(false);
		
		
	}
	public JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Avenir Next", Font.PLAIN, 25));
		label.setForeground(Color.white);
		label.setHorizontalAlignment(JLabel.CENTER);
		// label.setBounds(boundaries[0],boundaries[1],boundaries[2],boundaries[3]);
		label.setOpaque(false);
		label.setVisible(true);
		return label;
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
