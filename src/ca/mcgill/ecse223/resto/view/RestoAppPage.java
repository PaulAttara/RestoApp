package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.view.*;

import java.awt.Font;

public class RestoAppPage extends JFrame {

	Reservation reservation;
	
	public RestoAppPage() {

		super();
		init();
		// InitialDialog initialDialog = new InitialDialog();
	}
	
	static List<OrderItem> result;
	static OrderItem selectOrderItem;


	public void init() {

		getContentPane().setLayout(null);
		this.setSize(1280, 720);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);

		MainMenu mainMenu = new MainMenu();
		mainMenu.setBounds(640, 0, 640, 720);
		getContentPane().add(mainMenu);

		StartOrderDisplay startOrderDisplay = new StartOrderDisplay();
		
		BillPanel billPanel = new BillPanel();
		
		TableLayout tableLayout = new TableLayout();
		tableLayout.setSize(640, 720);
		TableOptions tableOptions = new TableOptions();
		validate();
		MenuDisplay menuDisplay = new MenuDisplay();
		menuDisplay.setBounds(640, 0, 640, 720);

		tableOptions.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(true);
				tableOptions.setVisible(false);
				System.out.println("from main page");
				
				//Add
				tableOptions.addLength.setText("");
				tableOptions.addNumber.setText("");
				tableOptions.addSeat.setText("");
				tableOptions.addWidth.setText("");
				tableOptions.addX.setText("");
				tableOptions.addY.setText("");
				
				//Move
				tableOptions.moveX.setText("");
				tableOptions.moveY.setText("");
				
				//Update
				tableOptions.updateLength.setText("");
				tableOptions.updateNumber.setText("");
				tableOptions.updateWidth.setText("");
				tableOptions.updateSeat.setText("");
			}
		});

		tableOptions.add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestoApp restoApp = RestoAppApplication.getRestoApp();
				
				try {
					if(Integer.parseInt(tableOptions.addSeat.getText())%2 == 1) 
					RestoAppController.createTable(Integer.parseInt(tableOptions.addNumber.getText()),
							Integer.parseInt(tableOptions.addX.getText()),
							Integer.parseInt(tableOptions.addY.getText()),
							Integer.parseInt(tableOptions.addWidth.getText()),
							Integer.parseInt(tableOptions.addLength.getText()),
							Integer.parseInt(tableOptions.addSeat.getText())+1);
					else RestoAppController.createTable(Integer.parseInt(tableOptions.addNumber.getText()),
							Integer.parseInt(tableOptions.addX.getText()),
							Integer.parseInt(tableOptions.addY.getText()),
							Integer.parseInt(tableOptions.addWidth.getText()),
							Integer.parseInt(tableOptions.addLength.getText()),
							Integer.parseInt(tableOptions.addSeat.getText()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
				} 

				
				for (Table table : restoApp.getTables()) {
					if (table != null) {
						/*
						System.out.println("- table number: " + table.getNumber());
						System.out.println("- table x: " + table.getX());
						System.out.println("- table y: " + table.getY());
						System.out.println("- table width: " + table.getWidth());
						System.out.println("- table length: " + table.getLength());
						System.out.println("- table seat: " + table.numberOfSeats());
					*/
					}
				}

				tableLayout.refreshTable();
				
			}
		});
		tableOptions.remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RestoAppController.removeTable();
					
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
				}
				clean();
				tableLayout.refreshTable();

			}
		});

		tableOptions.update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Here:" + tableOptions.updateNumber.getText() + ":end.");
				if(!tableOptions.updateSeat.getText().equals("") || !tableOptions.updateNumber.getText().equals("")) {
					System.out.println("stop");
				try {
					if(Integer.valueOf(tableOptions.updateSeat.getText()) % 2 == 1) {
					RestoAppController.updateTable(Integer.valueOf(tableOptions.updateNumber.getText()),
							Integer.valueOf(tableOptions.updateSeat.getText())+1);
					} else RestoAppController.updateTable(Integer.valueOf(tableOptions.updateNumber.getText()),
							Integer.valueOf(tableOptions.updateSeat.getText()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
					clean();
				}
				}
				if(!tableOptions.updateWidth.getText().equals("") || !tableOptions.updateLength.getText().equals("")) {
				try {
					 
					RestoAppController.updateDimension(Integer.valueOf(tableOptions.updateWidth.getText()),
							Integer.valueOf(tableOptions.updateLength.getText()));
					 
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
					clean();
				}
				
				}
				
				clean();
				tableLayout.refreshTable();

			}
		});

		tableOptions.move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RestoAppController.moveTable(Integer.valueOf(tableOptions.moveX.getText()),
							Integer.valueOf(tableOptions.moveY.getText()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
					clean();
				}
				clean();
				tableLayout.refreshTable();

			}
		});

		menuDisplay.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableLayout.refreshTable();
				mainMenu.setVisible(true);
				menuDisplay.setVisible(false);
			}
		});

		getContentPane().add(tableLayout);
		getContentPane().add(menuDisplay);
		getContentPane().add(tableOptions);
		getContentPane().add(billPanel);

		
		reservation = new Reservation();
		reservation.setBounds(640, 0, 640, 720);
		getContentPane().add(reservation);

		reservation.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableLayout.refreshTable();
				mainMenu.setVisible(true);
				reservation.setVisible(false);
				
				reservation.date.setText("yyyy-MM-dd");
				reservation.time.setText("hh:mm");
				reservation.contactEmail.setText("");
				reservation.contactPhone.setText("");
				reservation.contactName.setText("");
				reservation.numberPeople.setText("");
				
			}
		});

		mainMenu.ViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(false);
				menuDisplay.setVisible(true);
				menuDisplay.Back.setVisible(true);
			}
		});
		mainMenu.TableSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(false);
				tableOptions.setVisible(true);
			}
		});

		mainMenu.Reservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(false);
				reservation.setVisible(true);
				

			}
		});
		
		reservation.reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				//System.out.println("input date is " + reservation.date.getText() + " and input time is " + reservation.time.getText());
				
					try {
						
						//System.out.println("input date is : " + reservation.inputDate + " and input time is : " + reservation.inputTime);
						RestoAppController.reserve(reservation.date.getText(),reservation.time.getText(), Integer.parseInt(reservation.numberPeople.getText()),
								reservation.contactName.getText(),reservation.contactEmail.getText(),reservation.contactPhone.getText());
					} catch (Exception e1) {
						
						
						JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);	
					}
					
			
			}
			});
		
		reservation.delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					RestoAppController.deleteReservation();
				} catch (Exception e1) {
					
					
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);	
				}
			}
			});
		
		
		Order order = new Order();
		order.setBounds(640, 0, 640, 720);
		getContentPane().add(order);

		mainMenu.Order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				
					//System.out.println("allahu" + RestoAppController.seatList.get(0).getOrderItems());
					//System.out.println(result);
					
					result = RestoAppController.getOrderItems();
				//	System.out.println(result.get(0).getPricedMenuItem().getMenuItem().getName());
					
					order.refresh(result);
					
					/*
					for(OrderItem oItem : result)
					{
						JButton button = createButton(oItem.getPricedMenuItem().getMenuItem().getName());
						order.panel.add(button);
						order.panel.setVisible(true);
					}*/
				
					//System.out.println("sfasdaa");
					mainMenu.setVisible(false);
					order.setVisible(true);	
					
				} catch (Exception e1) {
					
				
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
					
				}
				clean();
				tableLayout.refreshTable();
				///tableLayout.refreshTable();
				//if(RestoAppController.selectedTable.getOrders().size()>0) {
					
				
				
				


			}
		});
		
		order.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
				tableLayout.refreshTable();
				
				mainMenu.setVisible(true);
				order.setVisible(false);
			}
		});
		//cancel order
		order.Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					RestoAppController.cancelOrder();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
				}
				try {
					result = RestoAppController.getOrderItems();
				} catch (InvalidInputException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
				}
				order.refresh(result);
				System.out.println("CancelItem");	
				clean();
				tableLayout.refreshTable();
			}
		});
		//cancel order item
		order.CancelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
				tableLayout.refreshTable();
				
				try {
					RestoAppController.cancelOrderItem();
					order.erase();
				} catch (InvalidInputException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
				}
			System.out.println("CancelOrderItem");
			
			
			clean();
			tableLayout.refreshTable();
			}
			});
		
		//issue bill
		order.IssueBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RestoAppController.issueBill();
					System.out.println("IssueBill");
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
				}
				
				clean();
				tableLayout.refreshTable();
			
			}
		});
		////////NEW STUFF ALEX
		order.add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.setVisible(false);
				startOrderDisplay.setVisible(true);
				
			}
			});
		
		
		//Start Order
		//moved this line
		//StartOrderDisplay startOrderDisplay = new StartOrderDisplay();
		startOrderDisplay.setBounds(640, 0, 640, 720);
		startOrderDisplay.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
				tableLayout.refreshTable();
				mainMenu.setVisible(true);
				startOrderDisplay.setVisible(false);
			}
		});
		getContentPane().add(startOrderDisplay);
		
		mainMenu.StartOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RestoAppController.startOrder();
					mainMenu.setVisible(false);
					startOrderDisplay.setVisible(true);
				} catch (Exception e1) {
					clean();
					tableLayout.refreshTable();
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
					
				}
				

			}
		});
		
		//End order
		
		
		mainMenu.EndOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RestoAppController.endOrder();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
					
				}
				clean();
				tableLayout.refreshTable();

			}
		});
		//////TAKE OUT
		TakeOutPage takeOutPage = new TakeOutPage();
		takeOutPage.setBounds(640, 0, 640, 720);
		getContentPane().add(takeOutPage);
		
		mainMenu.TakeOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(false);
				takeOutPage.setVisible(true);
				//tableLayout.refreshTable();

			}
		});
		takeOutPage.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableLayout.refreshTable();
				mainMenu.setVisible(true);
				takeOutPage.setVisible(false);
				
				takeOutPage.name.setText("");
				takeOutPage.phoneNumber.setText("");
			}
		});
		
		mainMenu.viewBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableLayout.refreshTable();
				mainMenu.setVisible(false);
				billPanel.panel.removeAll();
				try {
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "message", JOptionPane.ERROR_MESSAGE);
				}
				try {
					for(String s : RestoAppController.viewBill()) {
						
						JLabel label = createLabel(s);
						billPanel.panel.add(label);
					}
				} catch (InvalidInputException e1) {
					
				}
				
				billPanel.setVisible(true);
			}
			});
		
		
		
		billPanel.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableLayout.refreshTable();
				mainMenu.setVisible(true);
				billPanel.setVisible(false);
			}
			});
		
		this.setVisible(true);

		
	}
	
	
	
	
	
	public static List<OrderItem> getResult(){
		return result;
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
	public static void clean() {
		RestoAppController.tableList.clear();
		RestoAppController.seatList.clear();
		//RestoAppController.selectedoi = null;
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
	
	
}
