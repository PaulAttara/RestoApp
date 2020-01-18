package ca.mcgill.ecse223.resto.application;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.resto.view.RestoAppPage;

public class RestoAppApplication {
	
	private static RestoApp restoapp;
	private static String filename = "menu.resto";
	

	
		
		public static void main(String[] args) {
			// start UI
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new RestoAppPage().setVisible(true);
	                try {
	                ca.mcgill.ecse223.resto.controller.RestoAppController.createTable(201115,9,9,1,1,2);
	                }catch (InvalidInputException e) {
	                System.out.println("error occured in making unique takeouttable");
	                }
	            }
	        });
	}
	

	public static RestoApp getRestoApp() {
		if (restoapp == null) {
			// load model
			restoapp = load();
		}
 		return restoapp;
	}
	
	public static void save() {
		restoapp.reinitialize();
		PersistenceObjectStream.serialize(restoapp);
	}
	
	public static RestoApp load() {
		PersistenceObjectStream.setFilename(filename);
		restoapp = (RestoApp) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if (restoapp == null) {
			restoapp = new RestoApp();
		}
		else {
			restoapp.reinitialize();
		}
		return restoapp;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}