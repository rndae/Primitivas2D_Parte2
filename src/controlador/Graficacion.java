package controlador;

import java.awt.Point;

import application.*;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Graficacion {
	/*@FXML private Point lp1, lp2;
	
	@FXML private Point centro;
	@FXML private int radio;
	
	@FXML private Point tp1, tp2, tp3;
	
	@FXML private Point cp;
	@FXML private int largo;
	*/
    @FXML private TextField lp1, lp2;
	
	@FXML private TextField centro;
	@FXML private TextField radio;
	
	@FXML private TextField tp1, tp2, tp3;
	
	@FXML private TextField cp;
	@FXML private TextField largo;
	
	@FXML private Button botonDibujar;
	
	@FXML private Button botonRellenar;
	
	public Graficacion(){
		
	}
	
	@FXML private void Dibujar(){
		if(lp1!=null && lp2!=null)
			Main.pintarLinea();
	}
}
