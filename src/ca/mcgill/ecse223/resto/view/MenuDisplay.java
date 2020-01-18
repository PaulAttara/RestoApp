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

public class MenuDisplay extends JPanel {

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
	JLabel AddMenuItem;

	JLabel appTitle;
	JLabel mainTitle;
	JLabel desTitle;
	JLabel bevTitle;
	JLabel alcTitle;
	JLabel updatedNa;
	JLabel updatedCat;
	JLabel updatedPr;
	JLabel menuItemSelected;

	int appNumber;
	int mainNumber;
	int desNumber;
	int bevNumber;
	int alcNumber;

	JTextField updatedName;
	JTextField updatedCategory;
	JTextField updatedPrice;

	JButton appButton;
	JButton mainButton;
	JButton desButton;
	JButton bevButton;
	JButton alcButton;
	JButton delete;
	JButton edit;
	JButton save;
	JButton order;
	JButton add;

	JButton Back;
	JButton BacktoCat;
	JButton BacktoList;

	MenuDisplay() {
		super(); 
		init();
	}

	public void init() {

		this.setLayout(null);


		menuZone = new JPanel(new GridLayout(5,1));
		menuZone.setBounds(0, 150, 640, 500);
		menuZone.setOpaque(false);
		ok = new JPanel(new GridLayout(5,1));
		ok.setBounds(0, 150, 640, 500);
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
		AddMenuItem = createLabel("  New Menu Item");
		menuItemSelected = createLabel("");
		updatedName = createTextField(20);
		updatedCategory = createTextField(20);
		updatedPrice = createTextField(5);

		save = createButton("Save");
		delete = createButton("Delete item");
		edit = createButton("Edit item");

		add =  createButton("Add");
		add.setBounds(480, 600, 100, 50);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add.setVisible(false);
				category.setVisible(false);
				appButton.setVisible(false);
				mainButton.setVisible(false);
				desButton.setVisible(false);
				bevButton.setVisible(false);
				alcButton.setVisible(false);
				Back.setVisible(true);
				BacktoList.setVisible(false);
				BacktoCat.setVisible(false);
				AddMenuItem.setFont(AddMenuItem.getFont().deriveFont(Font.BOLD));
				AddMenuItem.setVisible(true);
				updatedName.setBounds(330, 200, 200, 50);
				updatedName.setVisible(true);
				appRadio.setBounds(345, 270, 180, 50);
				appRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
				appRadio.setForeground(Color.white);
				appRadio.setVisible(true);
				mainRadio.setBounds(345, 310, 180, 50);
				mainRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
				mainRadio.setForeground(Color.white);
				mainRadio.setVisible(true);
				desRadio.setBounds(345, 350, 180, 50);
				desRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
				desRadio.setForeground(Color.white);
				desRadio.setVisible(true);
				alcRadio.setBounds(345, 390, 230, 50);
				alcRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
				alcRadio.setForeground(Color.white);
				alcRadio.setVisible(true);
				bevRadio.setBounds(345, 430, 180, 50);
				bevRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
				bevRadio.setForeground(Color.white);
				bevRadio.setVisible(true);
				updatedPrice.setBounds(330, 510, 200, 50);
				updatedPrice.setVisible(true);
				updatedNa.setBounds(80, 200, 200, 50);
				updatedNa.setVisible(true);
				updatedCat.setBounds(80, 350, 200, 50);
				updatedCat.setVisible(true);
				updatedPr.setBounds(80, 510, 200, 50);
				updatedPr.setVisible(true);
				save.setBounds(215, 600, 200, 50);
				save.setVisible(true);
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = updatedName.getText();
						ItemCategory[] category  = RestoAppController.getItemCategories();
						String aPrice = updatedPrice.getText();
						Double price =  Double.parseDouble(aPrice);
						if(appRadio.isSelected()) {
							try {
								System.out.println("This item has just been added: " + name + " " + "[" + price + "]");
								RestoAppController.addMenuItem(name, category[0], price);
							} catch (InvalidInputException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(mainRadio.isSelected()) {
							try {
								System.out.println("This item has just been added: " + name + " " + "[" + price + "]");
								RestoAppController.addMenuItem(name, category[1], price);
							} catch (InvalidInputException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(desRadio.isSelected()){
							try {
								System.out.println("This item has just been added: " + name + " " + "[" + price + "]");
								RestoAppController.addMenuItem(name, category[2], price);
							} catch (InvalidInputException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(alcRadio.isSelected()) {
							try {
								System.out.println("This item has just been added: " + name + " " + "[" + price + "]");
								RestoAppController.addMenuItem(name, category[3], price);
							} catch (InvalidInputException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(bevRadio.isSelected()) {
							try {
								System.out.println("This item has just been added: " + name + " " + "[" + price + "]");
								RestoAppController.addMenuItem(name, category[4], price);
							} catch (InvalidInputException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}});

			}
		});


		Back = createButton("Back");
		Back.setBounds(50, 600, 100, 50);
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appRadio.setVisible(false);
				mainRadio.setVisible(false);
				desRadio.setVisible(false);
				alcRadio.setVisible(false);
				bevRadio.setVisible(false);
				AddMenuItem.setVisible(false);
				updatedName.setVisible(false);
				updatedCategory.setVisible(false);
				updatedPrice.setVisible(false);
				updatedNa.setVisible(false);
				updatedCat.setVisible(false);
				updatedPr.setVisible(false);
				save.setVisible(false);
				add.setVisible(true);
				category.setVisible(true);
				appButton.setVisible(true);
				mainButton.setVisible(true);
				desButton.setVisible(true);
				bevButton.setVisible(true);
				alcButton.setVisible(true);
				Back.setVisible(false);
				BacktoList.setVisible(false);
				BacktoCat.setVisible(false);
				
				updatedName.setText("");
				updatedCategory.setText("");
				updatedPrice.setText("");
				
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
				edit.setVisible(false);
				delete.setVisible(false);
				menuItemSelected.setVisible(false);
				EditMenu.setVisible(false);
				updatedName.setVisible(false);
				updatedNa.setVisible(false);
				updatedPrice.setVisible(false);
				updatedPr.setVisible(false);
				updatedCat.setVisible(false);
				save.setVisible(false);
				add.setVisible(true);
				
				updatedName.setText("");
				updatedCategory.setText("");
				updatedPrice.setText("");
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
				add.setVisible(true);
				delete.setOpaque(false);
				delete.setVisible(false);
				edit.setOpaque(false);
				edit.setVisible(false);
				category.setVisible(true);
				EditMenu.setVisible(false);
				updatedName.setVisible(false);
				updatedCategory.setVisible(false);
				updatedPrice.setVisible(false);
				updatedNa.setVisible(false);
				updatedCat.setVisible(false);
				updatedPr.setVisible(false);
				save.setVisible(false);
				menuItemSelected.setVisible(false);
				
				
				updatedName.setText("");
				updatedCategory.setText("");
				updatedPrice.setText("");
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
						add.setVisible(false);
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
						add.setVisible(false);
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
						add.setVisible(false);
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
						add.setVisible(false);
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
						add.setVisible(false);
					}
				});
				menuZone.add(alcButton);
			}

		}


		this.add(Back);
		this.add(BacktoCat);
		this.add(BacktoList);
		this.add(add);
		this.add(AddMenuItem);
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
		this.add(updatedName);
		this.add(updatedCategory);
		this.add(updatedPrice);
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
								ok.remove(edit);
								ok.remove(delete);
								delete = createButton("Delete item");
								edit = createButton("Edit item");
								app.setVisible(false);
								appTitle.setVisible(false);
								BacktoCat.setVisible(true);
								add.setVisible(false);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								edit.setVisible(true);
								ok.add(edit);
								delete.setVisible(true);
								ok.add(delete);
								ok.setVisible(true);
								delete.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {	
										try {
											RestoAppController.removeMenuItem(mi);
											if(mi.getCurrentPricedMenuItem()==null)System.out.println("Removed: " + mi);
										} catch (InvalidInputException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}});
								edit.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										delete.setVisible(false);
										edit.setVisible(false);
										menuItemSelected.setVisible(false);
										EditMenu.setFont(EditMenu.getFont().deriveFont(Font.BOLD));
										EditMenu.setVisible(true);
										updatedName.setBounds(330, 200, 200, 50);
										updatedName.setVisible(true);
										updatedName.setBounds(330, 200, 200, 50);
										updatedName.setVisible(true);
										appRadio.setBounds(345, 270, 180, 50);
										appRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										appRadio.setForeground(Color.white);
										appRadio.setVisible(true);
										mainRadio.setBounds(345, 310, 180, 50);
										mainRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										mainRadio.setForeground(Color.white);
										mainRadio.setVisible(true);
										desRadio.setBounds(345, 350, 180, 50);
										desRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										desRadio.setForeground(Color.white);
										desRadio.setVisible(true);
										alcRadio.setBounds(345, 390, 230, 50);
										alcRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										alcRadio.setForeground(Color.white);
										alcRadio.setVisible(true);
										bevRadio.setBounds(345, 430, 180, 50);
										bevRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										bevRadio.setForeground(Color.white);
										bevRadio.setVisible(true);
										updatedPrice.setBounds(330, 510, 200, 50);
										updatedPrice.setVisible(true);
										updatedNa.setBounds(80, 200, 200, 50);
										updatedNa.setVisible(true);
										updatedCat.setBounds(80, 350, 200, 50);
										updatedCat.setVisible(true);
										updatedPr.setBounds(80, 510, 200, 50);
										updatedPr.setVisible(true);
										save.setBounds(215, 600, 200, 50);
										save.setVisible(true);
										save.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												String name = updatedName.getText();
												ItemCategory[] category  = RestoAppController.getItemCategories();
												String aCategory = updatedCategory.getText();
												String aPrice = updatedPrice.getText();
												Double price =  Double.parseDouble(aPrice);
												if(appRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + appLabel);
														RestoAppController.updateMenuItem(mi, name, category[0], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(mainRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + appLabel);
														RestoAppController.updateMenuItem(mi, name, category[1], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(desRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + appLabel);
														RestoAppController.updateMenuItem(mi, name, category[2], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(alcRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + appLabel);
														RestoAppController.updateMenuItem(mi, name, category[3], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(bevRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + appLabel);
														RestoAppController.updateMenuItem(mi, name, category[4], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
											}});
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
								ok.remove(edit);
								ok.remove(delete);
								delete = createButton("Delete item");
								edit = createButton("Edit item");
								main.setVisible(false);
								mainTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								edit.setVisible(true);
								ok.add(edit);
								delete.setVisible(true);
								ok.add(delete);
								ok.setVisible(true);
								delete.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											RestoAppController.removeMenuItem(mi);

											System.out.println("Removed: " + mainLabel);
											mi.delete();
										} catch (InvalidInputException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}});
								edit.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										delete.setVisible(false);
										edit.setVisible(false);	
										menuItemSelected.setVisible(false);
										EditMenu.setFont(EditMenu.getFont().deriveFont(Font.BOLD));
										EditMenu.setVisible(true);
										updatedName.setBounds(330, 200, 200, 50);
										updatedName.setVisible(true);
										appRadio.setBounds(345, 270, 180, 50);
										appRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										appRadio.setForeground(Color.white);
										appRadio.setVisible(true);
										mainRadio.setBounds(345, 310, 180, 50);
										mainRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										mainRadio.setForeground(Color.white);
										mainRadio.setVisible(true);
										desRadio.setBounds(345, 350, 180, 50);
										desRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										desRadio.setForeground(Color.white);
										desRadio.setVisible(true);
										alcRadio.setBounds(345, 390, 230, 50);
										alcRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										alcRadio.setForeground(Color.white);
										alcRadio.setVisible(true);
										bevRadio.setBounds(345, 430, 180, 50);
										bevRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										bevRadio.setForeground(Color.white);
										bevRadio.setVisible(true);
										updatedPrice.setBounds(330, 510, 200, 50);
										updatedPrice.setVisible(true);
										updatedNa.setBounds(80, 200, 200, 50);
										updatedNa.setVisible(true);
										updatedCat.setBounds(80, 350, 200, 50);
										updatedCat.setVisible(true);
										updatedPr.setBounds(80, 510, 200, 50);
										updatedPr.setVisible(true);
										save.setBounds(215, 600, 200, 50);
										save.setVisible(true);
										save.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												String name = updatedName.getText();
												ItemCategory[] category  = RestoAppController.getItemCategories();
												String aCategory = updatedCategory.getText();
												String aPrice = updatedPrice.getText();
												Double price =  Double.parseDouble(aPrice);
												if(appRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + mainLabel);
														RestoAppController.updateMenuItem(mi, name, category[0], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(mainRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + mainLabel);
														RestoAppController.updateMenuItem(mi, name, category[1], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(desRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + mainLabel);
														RestoAppController.updateMenuItem(mi, name, category[2], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(alcRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + mainLabel);
														RestoAppController.updateMenuItem(mi, name, category[3], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(bevRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + mainLabel);
														RestoAppController.updateMenuItem(mi, name, category[4], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
											}});
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
								ok.remove(edit);
								ok.remove(delete);
								delete = createButton("Delete item");
								edit = createButton("Edit item");
								des.setVisible(false);
								desTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								edit.setVisible(true);
								ok.add(edit);
								delete.setVisible(true);
								ok.add(delete);
								ok.setVisible(true);
								delete.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											RestoAppController.removeMenuItem(mi);
											System.out.println("Removed: " + desLabel);
										} catch (InvalidInputException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}});
								edit.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										delete.setVisible(false);
										edit.setVisible(false);
										menuItemSelected.setVisible(false);
										EditMenu.setFont(EditMenu.getFont().deriveFont(Font.BOLD));
										EditMenu.setVisible(true);
										updatedName.setBounds(330, 200, 200, 50);
										updatedName.setVisible(true);
										appRadio.setBounds(345, 270, 180, 50);
										appRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										appRadio.setForeground(Color.white);
										appRadio.setVisible(true);
										mainRadio.setBounds(345, 310, 180, 50);
										mainRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										mainRadio.setForeground(Color.white);
										mainRadio.setVisible(true);
										desRadio.setBounds(345, 350, 180, 50);
										desRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										desRadio.setForeground(Color.white);
										desRadio.setVisible(true);
										alcRadio.setBounds(345, 390, 230, 50);
										alcRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										alcRadio.setForeground(Color.white);
										alcRadio.setVisible(true);
										bevRadio.setBounds(345, 430, 180, 50);
										bevRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										bevRadio.setForeground(Color.white);
										bevRadio.setVisible(true);
										updatedPrice.setBounds(330, 510, 200, 50);
										updatedPrice.setVisible(true);
										updatedNa.setBounds(80, 200, 200, 50);
										updatedNa.setVisible(true);
										updatedCat.setBounds(80, 350, 200, 50);
										updatedCat.setVisible(true);
										updatedPr.setBounds(80, 510, 200, 50);
										updatedPr.setVisible(true);
										save.setBounds(215, 600, 200, 50);
										save.setVisible(true);
										save.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												String name = updatedName.getText();
												ItemCategory[] category  = RestoAppController.getItemCategories();
												String aCategory = updatedCategory.getText();
												String aPrice = updatedPrice.getText();
												Double price =  Double.parseDouble(aPrice);
												if(appRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + desLabel);
														RestoAppController.updateMenuItem(mi, name, category[0], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(mainRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + desLabel);
														RestoAppController.updateMenuItem(mi, name, category[1], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(desRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + desLabel);
														RestoAppController.updateMenuItem(mi, name, category[2], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(alcRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + desLabel);
														RestoAppController.updateMenuItem(mi, name, category[3], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(bevRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + desLabel);
														RestoAppController.updateMenuItem(mi, name, category[4], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
											}});
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
								ok.remove(edit);
								ok.remove(delete);
								delete = createButton("Delete item");
								edit = createButton("Edit item");
								bev.setVisible(false);
								bevTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								edit.setVisible(true);
								ok.add(edit);
								delete.setVisible(true);
								ok.add(delete);
								ok.setVisible(true);	
								delete.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											RestoAppController.removeMenuItem(mi);
											System.out.println("Removed: " + nonAlcLabel);
										} catch (InvalidInputException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}});
								edit.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										delete.setVisible(false);
										edit.setVisible(false);
										menuItemSelected.setVisible(false);
										EditMenu.setFont(EditMenu.getFont().deriveFont(Font.BOLD));
										EditMenu.setVisible(true);
										updatedName.setBounds(330, 200, 200, 50);
										updatedName.setVisible(true);
										appRadio.setBounds(345, 270, 180, 50);
										appRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										appRadio.setForeground(Color.white);
										appRadio.setVisible(true);
										mainRadio.setBounds(345, 310, 180, 50);
										mainRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										mainRadio.setForeground(Color.white);
										mainRadio.setVisible(true);
										desRadio.setBounds(345, 350, 180, 50);
										desRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										desRadio.setForeground(Color.white);
										desRadio.setVisible(true);
										alcRadio.setBounds(345, 390, 230, 50);
										alcRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										alcRadio.setForeground(Color.white);
										alcRadio.setVisible(true);
										bevRadio.setBounds(345, 430, 180, 50);
										bevRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										bevRadio.setForeground(Color.white);
										bevRadio.setVisible(true);
										updatedPrice.setBounds(330, 510, 200, 50);
										updatedPrice.setVisible(true);
										updatedNa.setBounds(80, 200, 200, 50);
										updatedNa.setVisible(true);
										updatedCat.setBounds(80, 350, 200, 50);
										updatedCat.setVisible(true);
										updatedPr.setBounds(80, 510, 200, 50);
										updatedPr.setVisible(true);
										save.setBounds(215, 600, 200, 50);
										save.setVisible(true);
										save.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												String name = updatedName.getText();
												ItemCategory[] category  = RestoAppController.getItemCategories();
												String aCategory = updatedCategory.getText();
												String aPrice = updatedPrice.getText();
												Double price =  Double.parseDouble(aPrice);
												if(appRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + nonAlcLabel);
														RestoAppController.updateMenuItem(mi, name, category[0], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(mainRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + nonAlcLabel);
														RestoAppController.updateMenuItem(mi, name, category[1], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(desRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + nonAlcLabel);
														RestoAppController.updateMenuItem(mi, name, category[2], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(alcRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + nonAlcLabel);
														RestoAppController.updateMenuItem(mi, name, category[3], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(bevRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + nonAlcLabel);
														RestoAppController.updateMenuItem(mi, name, category[4], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
											}});
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
								ok.remove(edit);
								ok.remove(delete);
								delete = createButton("Delete item");
								edit = createButton("Edit item");
								alc.setVisible(false);
								alcTitle.setVisible(false);
								BacktoCat.setVisible(true);
								menuItemSelected.setText(mi.getName());
								menuItemSelected.setFont(menuItemSelected.getFont().deriveFont(Font.BOLD));
								menuItemSelected.setVisible(true);
								edit.setVisible(true);
								ok.add(edit);
								delete.setVisible(true);
								ok.add(delete);
								ok.setVisible(true);
								
								delete.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										try {
											RestoAppController.removeMenuItem(mi);
											System.out.println("Removed: " + alcLabel);
										} catch (InvalidInputException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}});
								edit.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										delete.setVisible(false);
										edit.setVisible(false);	
									
										menuItemSelected.setVisible(false);
										EditMenu.setFont(EditMenu.getFont().deriveFont(Font.BOLD));
										EditMenu.setVisible(true);
										updatedName.setBounds(330, 200, 200, 50);
										updatedName.setVisible(true);
										appRadio.setBounds(345, 270, 180, 50);
										appRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										appRadio.setForeground(Color.white);
										appRadio.setVisible(true);
										mainRadio.setBounds(345, 310, 180, 50);
										mainRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										mainRadio.setForeground(Color.white);
										mainRadio.setVisible(true);
										desRadio.setBounds(345, 350, 180, 50);
										desRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										desRadio.setForeground(Color.white);
										desRadio.setVisible(true);
										alcRadio.setBounds(345, 390, 230, 50);
										alcRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										alcRadio.setForeground(Color.white);
										alcRadio.setVisible(true);
										bevRadio.setBounds(345, 430, 180, 50);
										bevRadio.setFont(new Font("Avenir Next", Font.PLAIN, 21));
										bevRadio.setForeground(Color.white);
										bevRadio.setVisible(true);
										updatedPrice.setBounds(330, 510, 200, 50);
										updatedPrice.setVisible(true);
										updatedNa.setBounds(80, 200, 200, 50);
										updatedNa.setVisible(true);
										updatedCat.setBounds(80, 350, 200, 50);
										updatedCat.setVisible(true);
										updatedPr.setBounds(80, 510, 200, 50);
										updatedPr.setVisible(true);
										save.setBounds(215, 600, 200, 50);
										save.setVisible(true);
										save.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												String name = updatedName.getText();
												ItemCategory[] category  = RestoAppController.getItemCategories();
												String aCategory = updatedCategory.getText();
												String aPrice = updatedPrice.getText();
												Double price =  Double.parseDouble(aPrice);
												if(appRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + alcLabel);
														RestoAppController.updateMenuItem(mi, name, category[0], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(mainRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + alcLabel);
														RestoAppController.updateMenuItem(mi, name, category[1], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(desRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + alcLabel);
														RestoAppController.updateMenuItem(mi, name, category[2], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(alcRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + alcLabel);
														RestoAppController.updateMenuItem(mi, name, category[3], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												if(bevRadio.isSelected()) {
													try {
														System.out.println("This item has just been updated: " + alcLabel);
														RestoAppController.updateMenuItem(mi, name, category[4], price);
													} catch (InvalidInputException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
											}});
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
	public static void main(String[] args) {
		MenuDisplay menuDisplay = new MenuDisplay();
		JFrame jFrame = new JFrame();
		jFrame.setSize(1280, 720);
		jFrame.add(menuDisplay);
		jFrame.setVisible(true);
	}
}
