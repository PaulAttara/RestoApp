package ca.mcgill.ecse223.resto.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDeviceTransmitter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

public class StartOrderDisplay extends JPanel {

	/*
	List<JButton> jButtonApp = new ArrayList<JButton>();
	List<JButton> jButtonMain = new ArrayList<JButton>();
	List<JButton> jButtonDes = new ArrayList<JButton>();
	List<JButton> jButtonBev = new ArrayList<JButton>();
	List<JButton> jButtonAlc = new ArrayList<JButton>();
	 */

	JRadioButton appRadio = new JRadioButton("Appetizers");
	JRadioButton mainRadio = new JRadioButton("Main Course");
	JRadioButton desRadio = new JRadioButton("Desserts");
	JRadioButton alcRadio = new JRadioButton("Alcoholic Beverages");
	JRadioButton bevRadio = new JRadioButton("Beverages");

	JPanel menuZone;
	JPanel ok;
	JPanel app;
	JPanel main;
	JPanel des;
	JPanel bev;
	JPanel alc;

	JLabel category;

	JLabel EditMenu;

	JLabel appTitle;
	JLabel mainTitle;
	JLabel desTitle;
	JLabel bevTitle;
	JLabel alcTitle;
	JLabel updatedNa;
	JLabel updatedCat;
	JLabel updatedPr;
	JLabel menuItemSelected;
	
	//JLabel quantityLabel;

	int appNumber;
	int mainNumber;
	int desNumber;
	int bevNumber;
	int alcNumber;


	//JTextField quantityText;

	JButton appButton;
	JButton mainButton;
	JButton desButton;
	JButton bevButton;
	JButton alcButton;
	JButton save;
	JButton orderItem;

	JButton Back;
	JButton BacktoCat;
	JButton BacktoList;

	StartOrderDisplay() {
		super(); 
		init();
	}

	public void init() {

		this.setLayout(null);


		menuZone = new JPanel(new GridLayout(5,1));
		menuZone.setBounds(0, 150, 640, 500);
		menuZone.setOpaque(false);
		ok = new JPanel(new GridLayout(5,1));
		ok.setBounds(200, 150, 240, 500);
		ok.setOpaque(false);

		app = createGridPanel();
		main = createGridPanel();
		des = createGridPanel();
		bev = createGridPanel();
		alc = createGridPanel();

		category = createLabel("Food Category");
		category.setFont(category.getFont().deriveFont(Font.BOLD));
		category.setVisible(true);
		appTitle = createLabel("Appetizers");
		appTitle.setFont(appTitle.getFont().deriveFont(Font.BOLD));
		mainTitle = createLabel("Main Course");
		mainTitle.setFont(mainTitle.getFont().deriveFont(Font.BOLD));
		desTitle = createLabel("Desserts");
		desTitle.setFont(desTitle.getFont().deriveFont(Font.BOLD));
		bevTitle = createLabel("Beverages");
		bevTitle.setFont(bevTitle.getFont().deriveFont(Font.BOLD));
		alcTitle = createLabel("Alcoholic Beverages");
		alcTitle.setFont(alcTitle.getFont().deriveFont(Font.BOLD));
		updatedNa = createLabel("Name");
		updatedCat = createLabel("Category");
		updatedPr = createLabel("Price");
		EditMenu = createLabel("  Edit Menu Item");
		menuItemSelected = createLabel("");
		
		/*quantityText = createTextField(2);
		quantityText.setBounds(270, 400, 50, 50);
		quantityLabel = createLabel("quantity");
		quantityLabel.setBounds(0, 300, 640, 100);*/

		save = createButton("Save");
		orderItem = createButton("Add order item");


		Back = createButton("Back");
		Back.setBounds(50, 600, 100, 50);
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appRadio.setVisible(false);
				mainRadio.setVisible(false);
				desRadio.setVisible(false);
				alcRadio.setVisible(false);
				bevRadio.setVisible(false);
				updatedNa.setVisible(false);
				updatedCat.setVisible(false);
				updatedPr.setVisible(false);
				save.setVisible(false);
				category.setVisible(true);
				appButton.setVisible(true);
				mainButton.setVisible(true);
				desButton.setVisible(true);
				bevButton.setVisible(true);
				alcButton.setVisible(true);
				Back.setVisible(false);
				BacktoList.setVisible(false);
				BacktoCat.setVisible(false);
			//	quantityText.setVisible(false);
			//	quantityLabel.setVisible(false);
			}
		});

		BacktoCat = createButton("Back");
		BacktoCat.setBounds(50, 600, 100, 50);
		BacktoCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appRadio.setVisible(false);
				mainRadio.setVisible(false);
				desRadio.setVisible(false);
				alcRadio.setVisible(false);
				bevRadio.setVisible(false);
				menuZone.setVisible(true);
				app.setVisible(false);
				main.setVisible(false);
				des.setVisible(false);
				bev.setVisible(false);
				alc.setVisible(false);
				Back.setVisible(true);
				category.setVisible(true);
				appTitle.setVisible(false);
				mainTitle.setVisible(false);
				desTitle.setVisible(false);
				bevTitle.setVisible(false);
				alcTitle.setVisible(false);
				BacktoCat.setVisible(false);
				BacktoList.setVisible(false);
				orderItem.setVisible(false);
				menuItemSelected.setVisible(false);
				EditMenu.setVisible(false);;
				updatedNa.setVisible(false);
				updatedPr.setVisible(false);
				updatedCat.setVisible(false);
				save.setVisible(false);
			//	quantityText.setVisible(false);
			//	quantityLabel.setVisible(false);
			}
		});
		BacktoList = createButton("Back");
		BacktoList.setBounds(50, 600, 100, 50);
		BacktoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appRadio.setVisible(false);
				mainRadio.setVisible(false);
				desRadio.setVisible(false);
				alcRadio.setVisible(false);
				bevRadio.setVisible(false);
				appTitle.setVisible(false);
				menuZone.setVisible(true);
				BacktoList.setVisible(false);
				BacktoCat.setVisible(true);
				orderItem.setOpaque(false);
				orderItem.setVisible(false);
				category.setVisible(true);
				EditMenu.setVisible(false);
				updatedNa.setVisible(false);
				updatedCat.setVisible(false);
				updatedPr.setVisible(false);
				save.setVisible(false);
				menuItemSelected.setVisible(false);
			//	quantityText.setVisible(false);
			//	quantityLabel.setVisible(false);
			}
		});

		for(ItemCategory itemCategory:RestoAppController.getItemCategories())
		{
			String a = "";
			if (itemCategory.name() == "Appetizer") {
				a = "Appetizers";
				appButton = createButton(a);
				appButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						app.removeAll();
						refresh();
						menuZone.setVisible(false);
						category.setVisible(false);
						Back.setVisible(false);
						app.setVisible(true);
						appTitle.setVisible(true);
						BacktoCat.setVisible(true);
					}
				});
				menuZone.add(appButton);

			}
			if (itemCategory.name() == "Main") {
				a = "Main Course";
				mainButton = createButton(a);
				mainButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						main.removeAll();
						refresh();
						menuZone.setVisible(false);
						category.setVisible(false);
						Back.setVisible(false);
						main.setVisible(true);
						mainTitle.setVisible(true);
						BacktoCat.setVisible(true);
					}
				});
				menuZone.add(mainButton);
			}
			if (itemCategory.name() == "Dessert") {
				a = "Desserts";
				desButton = createButton(a);
				desButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						des.removeAll();
						refresh();
						menuZone.setVisible(false);
						category.setVisible(false);
						Back.setVisible(false);
						des.setVisible(true);
						desTitle.setVisible(true);
						BacktoCat.setVisible(true);
					}
				});
				menuZone.add(desButton);

			}
			if (itemCategory.name() == "NonAlcoholicBeverage") {
				a = "Beverages";
				bevButton = createButton(a);
				bevButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bev.removeAll();
						refresh();
						menuZone.setVisible(false);
						category.setVisible(false);
						Back.setVisible(false);
						bev.setVisible(true);
						bevTitle.setVisible(true);
						BacktoCat.setVisible(true);
					}
				});
				menuZone.add(bevButton);
			}
			if (itemCategory.name() == "AlcoholicBeverage") {
				a = "Alcoholic Beverages";
				alcButton = createButton(a);
				alcButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						alc.removeAll();
						refresh();
						menuZone.setVisible(false);
						category.setVisible(false);
						Back.setVisible(false);
						alc.setVisible(true);
						alcTitle.setVisible(true);
						BacktoCat.setVisible(true);
					}
				});
				menuZone.add(alcButton);
			}

		}


		this.add(Back);
		this.add(BacktoCat);
		this.add(BacktoList);
		this.add(ok);
		this.add(menuZone);
		this.add(app);
		this.add(main);
		this.add(des);
		this.add(bev);
		this.add(alc);
		this.add(category);
		this.add(appTitle);
		this.add(mainTitle);
		this.add(desTitle);
		this.add(bevTitle);
		this.add(alcTitle);
		this.add(updatedNa);
		this.add(updatedCat);
		this.add(updatedPr);
		this.add(EditMenu);
		this.add(save);
		this.add(menuItemSelected);
		this.add(appRadio);
		this.add(mainRadio);
		this.add(desRadio);
		this.add(alcRadio);
		this.add(bevRadio);
		this.setVisible(false);
	}
	public JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Avenir Next", Font.PLAIN, 25));
		label.setForeground(Color.white);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 50, 640, 100);
		// label.setBounds(boundaries[0],boundaries[1],boundaries[2],boundaries[3]);
		label.setOpaque(false);
		label.setVisible(false);
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

	public JPanel createGridPanel() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBounds(0, 150, 640, 500);
		panel.setOpaque(false);
		panel.setVisible(false);
		return panel;
	}

	public JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("Avenir Next", Font.PLAIN, 25));
		button.setForeground(Color.white);
		// button.setBounds(boundaries[0],boundaries[1],boundaries[2],boundaries[3]);
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

	public void refresh() {
		for(ItemCategory itemCategory:RestoAppController.getItemCategories()) {
			List<MenuItem> menuItem = null;
			try {
				menuItem = RestoAppController.getMenuItems(itemCategory);
			} catch (InvalidInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (MenuItem mi : menuItem) {
				switch (mi.getItemCategory().name()) {
				case "Appetizer":
					if(mi.hasCurrentPricedMenuItem() == true) {
						Double priceApp = mi.getCurrentPricedMenuItem().getPrice();
						String appPrice = priceApp.toString();
						String appLabel = mi.getName() + " " + "(" + appPrice + ")";
						JButton appSelected = createButton(appLabel);
						appSelected.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
						//		quantityText.setVisible(true);
						//		quantityLabel.setVisible(true);
								
								app.setVisible(false);
								appTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								orderItem.setVisible(true);
							//	ok.add(quantityLabel);
							//	ok.add(quantityText);
								ok.removeAll();
								orderItem = createButton("Add order item");
								ok.add(orderItem);
								ok.setVisible(true);
							
								orderItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {	
										//POUR DALLIN
								//		String quantity = quantityText.getText();
										try {
											//System.out.println(mi);
											//System.out.println(Integer.parseInt(quantity));
											//System.out.println(RestoAppController.seatList);
											RestoAppController.orderMenuItem(mi, RestoAppController.seatList.size());
											//System.out.println(RestoAppController.seatList);
											//System.out.println(RestoAppController.seatList.get(0).getOrderItem(0));
										} catch (Exception e1) {
											JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
									}});
							}
						});
						app.add(appSelected);
						break;
					}
				case "Main":
					if(mi.hasCurrentPricedMenuItem() == true) {
						Double priceMain = mi.getCurrentPricedMenuItem().getPrice();
						String mainPrice = priceMain.toString();
						String mainLabel = mi.getName() + " " + "(" + mainPrice + ")";
						JButton mainSelected = createButton(mainLabel);
						mainSelected.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
						//		quantityText.setVisible(true);
						//		quantityLabel.setVisible(true);
								main.setVisible(false);
								mainTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								orderItem.setVisible(true);
								orderItem = createButton("Add order item");
							//	ok.add(quantityLabel);
							//	ok.add(quantityText);
								ok.removeAll();
								ok.add(orderItem);
								ok.setVisible(true);
								orderItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {	
										//POUR DALLIN
							//			String quantity = quantityText.getText();
										try {
											RestoAppController.orderMenuItem(mi, RestoAppController.seatList.size());
										} catch (Exception e1) {
											JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
									}});	
							}
						});
						main.add(mainSelected);
						break;
					}
				case "Dessert":
					if(mi.hasCurrentPricedMenuItem() == true) {
						Double priceDes = mi.getCurrentPricedMenuItem().getPrice();
						String desPrice = priceDes.toString();
						String desLabel = mi.getName() + " " + "(" + desPrice + ")";
						JButton dessertSelected = createButton(desLabel);
						dessertSelected.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
						//		quantityText.setVisible(true);
							//	quantityLabel.setVisible(true);
								des.setVisible(false);
								desTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								orderItem.setVisible(true);
								orderItem = createButton("Add order item");
							//	ok.add(quantityLabel);
							//	ok.add(quantityText);
								ok.removeAll();
								ok.add(orderItem);
								ok.setVisible(true);
								orderItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {	
										//POUR DALLIN
							//			String quantity = quantityText.getText();
										try {
											RestoAppController.orderMenuItem(mi, RestoAppController.seatList.size());
										} catch (Exception e1) {
											JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
									}});
							}
						});
						des.add(dessertSelected);
						break;
					}
				case "NonAlcoholicBeverage":
					if(mi.hasCurrentPricedMenuItem() == true) {
						Double priceNonAlc = mi.getCurrentPricedMenuItem().getPrice();
						String nonAlcPrice = priceNonAlc.toString();
						String nonAlcLabel = mi.getName() + " " + "(" + nonAlcPrice + ")";
						JButton bevSelected = createButton(nonAlcLabel);
						bevSelected.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
						//		quantityText.setVisible(true);
						//		quantityLabel.setVisible(true);
								bev.setVisible(false);
								bevTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								orderItem.setVisible(true);
								orderItem = createButton("Add order item");
						//		ok.add(quantityLabel);
						//		ok.add(quantityText);
								ok.removeAll();
								ok.add(orderItem);
								ok.setVisible(true);
								orderItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {	
										//POUR DALLIN
						//				String quantity = quantityText.getText();
										try {
											RestoAppController.orderMenuItem(mi, RestoAppController.seatList.size());
										} catch (Exception e1) {
											JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
									}});
							}
						});
						bev.add(bevSelected);
						break;
					}
				case "AlcoholicBeverage":
					if(mi.hasCurrentPricedMenuItem() == true) {
						Double priceAlc = mi.getCurrentPricedMenuItem().getPrice();
						String alcPrice = priceAlc.toString();
						String alcLabel = mi.getName() + " " + "(" + alcPrice + ")";
						JButton alcSelected = createButton(alcLabel);
						alcSelected.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
					//			quantityText.setVisible(true);
					//			quantityLabel.setVisible(true);
								alc.setVisible(false);
								alcTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								orderItem.setVisible(true);
								orderItem = createButton("Add order item");
						//		ok.add(quantityLabel);
						//		ok.add(quantityText);
								ok.removeAll();
								ok.add(orderItem);
								ok.setVisible(true);
								orderItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {	
										//POUR DALLIN
						//				String quantity = quantityText.getText();
										try {
											System.out.println("o");
											
											RestoAppController.orderMenuItem(mi, RestoAppController.seatList.size());
											System.out.println(RestoAppController.seatList.get(0).getOrderItem(0));
										} catch (Exception e1) {
											JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
					
										
									}});
							}
						});
						alc.add(alcSelected);
						break;
					}
				}
			}
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