package controlador;

import java.awt.Point;

import application.Main;
import javafx.fxml.FXML;

public class LineaControl {
	@FXML
	private Point p1;
	@FXML
	private Point p2;
	@FXML
	private void pintarLinea(){
		Main.pintarLinea();
	}
}
