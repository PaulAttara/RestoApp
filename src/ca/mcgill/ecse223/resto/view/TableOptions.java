package ca.mcgill.ecse223.resto.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;

public class TableOptions extends JPanel {

	JPanel MainMenu;
	JButton Back;

	JLabel MenuTitle;
	JButton AddTable;
	JButton RemoveTable;
	JButton UpdateTable;
	JButton MoveTable;
	JButton BacktoMain;

	static int row;
	static int column;
	
	// for add table
	JPanel addTable;
	JLabel addMain;
	JLabel addNumberText;
	JLabel addXText;
	JLabel addYText;
	JLabel addWidthText;
	JLabel addLengthText;
	JLabel addSeatText;

	JTextField addNumber;
	JTextField addSeat;
	JTextField addX;
	JTextField addY;
	JTextField addWidth;
	JTextField addLength;
	

	JButton add;
	// end of add table

	// for remove table
	JPanel removeTable;
	JPanel removeZone;
	JPanel removeBackZone;
	JLabel removeMain;
	JButton remove;
	JButton removeBack;
	// end of remove table

	// for update table
	JPanel updateTable;
	JPanel updateZone;
	JPanel updateBackZone;
	JLabel updateMain;
	JLabel updateNumberText;
	JLabel updateSeatText;
	JLabel updateWidthText;
	JLabel updateLengthText;
	JTextField updateWidth;
	JTextField updateLength;
	JTextField updateNumber;
	JTextField updateSeat;

	JButton update;
	JButton updateBack;
	// end of update table

	// for move table
	JPanel moveTable;
	JPanel moveZone;
	JPanel moveBackZone;
	JLabel moveMain;
	JLabel moveXText;
	JLabel moveYText;
	JTextField moveX;
	JTextField moveY;
	JButton move;
	JButton moveBack;
	// end of move table

	public TableOptions() {
		super();
		init();
	}

	public void init() {

		this.setLayout(null);
		this.setBounds(640,0,640,720);
		this.setOpaque(true);
		
		MainMenu = new JPanel();
		MainMenu.setLayout(new GridLayout(4, 1));
		MainMenu.setOpaque(false);
		MainMenu.setBounds(150, 150, 340, 500);


		MenuTitle = createLabel("Table Setup");
		MenuTitle.setFont(new Font("Avenir Next", Font.BOLD, 30));
		MenuTitle.setBounds(0,50, 640,100);
		Back = createButton("Back");
		Back.setBounds(50, 600, 100, 50);
		Back.setVisible(true);

		BacktoMain = createButton("Back");
		BacktoMain.setBounds(50, 600, 100, 50);
		BacktoMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuTitle.setVisible(true);
				MainMenu.setVisible(true);
				Back.setVisible(true);
				BacktoMain.setVisible(false);
				addTable.setVisible(false);
				addMain.setVisible(false);
				removeTable.setVisible(false);
				removeMain.setVisible(false);
				updateTable.setVisible(false);
				updateMain.setVisible(false);
				moveTable.setVisible(false);
				moveMain.setVisible(false);
				
				//Add
				addLength.setText("");
				addNumber.setText("");
				addSeat.setText("");
				addWidth.setText("");
				addX.setText("");
				addY.setText("");
				
				//Move
				moveX.setText("");
				moveY.setText("");
				
				//Update
				updateLength.setText("");
				updateNumber.setText("");
				updateWidth.setText("");
				updateSeat.setText("");
			}
		});
		BacktoMain.setVisible(false);

		// add button
		AddTable = createButton("Add Table");
		AddTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(false);
				MenuTitle.setVisible(false);
				BacktoMain.setVisible(true);
				addTable.setVisible(true);
				addMain.setVisible(true);
				Back.setVisible(false);
			}
		});
		RemoveTable = createButton("Remove Table");
		RemoveTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(false);
				MenuTitle.setVisible(false);
				BacktoMain.setVisible(true);
				removeTable.setVisible(true);
				removeMain.setVisible(true);
				Back.setVisible(false);
			}
		});
		UpdateTable = createButton("Update Table");
		UpdateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(false);
				MenuTitle.setVisible(false);
				BacktoMain.setVisible(true);
				updateTable.setVisible(true);
				updateMain.setVisible(true);
				Back.setVisible(false);
			}
		});
		MoveTable = createButton("Move Table");
		MoveTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(false);
				MenuTitle.setVisible(false);
				BacktoMain.setVisible(true);
				moveTable.setVisible(true);
				moveMain.setVisible(true);
				Back.setVisible(false);
			}
		});
		//
		MainMenu.add(AddTable);
		MainMenu.add(RemoveTable);
		MainMenu.add(UpdateTable);
		MainMenu.add(MoveTable);

		MainMenu.setVisible(true);

		// Add Table GUI
		// biggest container
		addTable = new JPanel(new GridLayout(0, 1));
		addTable.setBounds(150, 150, 340, 500);
		addTable.setOpaque(false);

		
		// create labels
		addMain = createLabel("Add Table");
		addMain.setFont(addMain.getFont().deriveFont(Font.BOLD));
		addMain.setBounds(0,50,640,100);
		addMain.setVisible(false);
		
		addNumberText = createLabel("Table Number ");
		addXText = createLabel("X - Coordinate ");
		addYText = createLabel("Y - Coordinate ");
		addWidthText = createLabel("Width ");
		addLengthText = createLabel("Length ");
		addSeatText = createLabel("Number of seats ");
		// create textfield
		addNumber = createTextField(5);
		addSeat = createTextField(5);
		addX = createTextField(5);
		addY = createTextField(5);
		addWidth = createTextField(5);
		addLength = createTextField(5);
		
		// create button
		add = createButton("Add");
	
		addTable.add(addNumberText);
		addTable.add(addNumber);
		addTable.add(addXText);
		addTable.add(addX);
		addTable.add(addYText);
		addTable.add(addY);
		addTable.add(addWidthText);
		addTable.add(addWidth);
		addTable.add(addLengthText);
		addTable.add(addLength);
		addTable.add(addSeatText);
		addTable.add(addSeat);
	
		addTable.add(add);
		addTable.setVisible(false);
		// END OF ADD TABLE GUI

		// remove Table GUI
		// biggest container
		removeTable = new JPanel(new GridLayout(0,1));
		removeTable.setBounds(200, 150, 240, 500);
		removeTable.setOpaque(false);
	
		removeMain = createLabel("Remove Table");
		removeMain.setFont(removeMain.getFont().deriveFont(Font.BOLD));
		removeMain.setBounds(0,50,640,100);
		removeMain.setVisible(false);
		// create button
		remove = createButton("Remove");
		
		removeTable.add(remove);
		
		// set visible
		removeTable.setVisible(false);
		// END OF REMOVE TABLE GUI

		// Update Table GUI
		// biggest container
		updateTable = new JPanel(new GridLayout(0,1));
		updateTable.setBounds(150, 150, 340, 500);
		updateTable.setOpaque(false);
		
		// create labels
		updateMain = createLabel("Update Table");
		updateMain.setFont(updateMain.getFont().deriveFont(Font.BOLD));
		updateMain.setBounds(0,50,640,100);
		updateMain.setVisible(false);
		
		updateNumberText = createLabel("New Table Number ");
		updateSeatText = createLabel("New Number of Seats ");
		updateWidthText = createLabel("New Table Width ");
		updateLengthText = createLabel("New Table Length ");
		// create textfield
		updateNumber = createTextField(5);
		updateSeat = createTextField(5);
		updateWidth  = createTextField(5);
		updateLength  = createTextField(5);
		
		// create button
		update = createButton("Update");
	
		// arrange all

		updateTable.add(updateNumberText);
		updateTable.add(updateNumber);
		updateTable.add(updateSeatText);
		updateTable.add(updateSeat);
		updateTable.add(updateWidthText);
		updateTable.add(updateWidth);
		updateTable.add(updateLengthText);
		updateTable.add(updateLength);
		updateTable.add(update);
		// set visible
		updateTable.setVisible(false);
		// END OF UPDATE TABLE GUI

		// Move Table GUI
		// biggest container
		moveTable = new JPanel(new GridLayout(0, 1));
		moveTable.setBounds(150, 150, 340, 500);
		moveTable.setOpaque(false);
		
		// create labels
		moveMain = createLabel("Move Table");
		moveMain.setFont(moveMain.getFont().deriveFont(Font.BOLD));
		moveMain.setBounds(0,50,640,100);
		moveMain.setVisible(false);
		
		moveXText = createLabel("New Table X ");
		moveYText = createLabel("New Table Y ");
		// create textfield
		moveX = createTextField(5);
		moveY = createTextField(5);
		// create button
		move = createButton("Move");
		
		// button add action listener
		

		// arrange center
		moveTable.add(new JLabel(""));
		moveTable.add(moveXText);
		moveTable.add(new JLabel(""));
		moveTable.add(moveX);
		moveTable.add(new JLabel(""));
		moveTable.add(moveYText);
		moveTable.add(new JLabel(""));
		moveTable.add(moveY);
		moveTable.add(new JLabel(""));
		moveTable.add(move);
		// set visible
		moveTable.setVisible(false);
		// END OF MOVE TABLE GUI

		this.add(addTable);
		this.add(addMain);
		this.add(removeTable);
		this.add(removeMain);
		this.add(updateTable);
		this.add(updateMain);
		this.add(moveTable);
		this.add(moveMain);
		this.add(MenuTitle);
		this.add(MainMenu);
		this.add(Back);
		this.add(BacktoMain);
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

	public JTextField createTextField(int inputfield) {
		JTextField textfield = new JTextField(inputfield);
		textfield.setPreferredSize(new Dimension(200, 24));

		textfield.setForeground(Color.WHITE);
		textfield.setFont(new Font("avenir Next", Font.PLAIN, 20));
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
		RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(50, 50, 540, 620, 50, 50);
		GradientPaint cyanToblue = new GradientPaint(600, 145, vert, 300, 760, bleu);
		graphics2.setPaint(cyanToblue);
		graphics2.fill(roundedRectangle);
		graphics2.draw(roundedRectangle);
	}

}
