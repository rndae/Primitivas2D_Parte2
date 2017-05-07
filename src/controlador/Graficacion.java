package controlador;

import java.awt.Point;
import java.util.ArrayList;

import application.*;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import modelo.*;

public class Graficacion {
	/*@FXML private Point lp1, lp2;
	//
	@FXML private Point centro;
	@FXML private int radio;
	
	@FXML private Point tp1, tp2, tp3;
	
	@FXML private Point cp;
	@FXML private int largo;
	*/
	@FXML private AnchorPane pantalla;
	
	@FXML private Canvas lienzo;
	
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
	
	@FXML private void dibujar(ActionEvent event){
		GraphicsContext gc = lienzo.getGraphicsContext2D();
		
		//if(lp1!=null && lp2!=null)
			pintarLinea(new modelo.Line(new Point(0, 5),new Point(0, 200)), gc);
			pintarPoligono(new Triangulo(new Point(50, 5),new Point(50, 200), new Point(150, 100)), gc);

		System.out.print("Dibuja");
	}
	
	@FXML private void rellenar(ActionEvent event){		
		System.out.print("Rellena");
	}
	
	private void pintarLinea(modelo.Line lin, GraphicsContext gc){
			ArrayList<Point> ar = lin.bresenham();
			for(Point po: ar){
				gc.fillRect(po.getX(), po.getY(), 1, 1);
			}		
	}
	
	private void pintarPoligono(Poligono pol, GraphicsContext gc){
		ArrayList<Line> ar = pol.dibujar();
		for(Line lin: ar){
			pintarLinea(lin, gc);
		}		
}
	
	/*private void doPixel(Graphics g, int x, int y){
    	g.fillRect(x, y, 1, 1);
    	
	}
	ArrayList<Point> ar =a.bresenham();
		for(Point po: ar){
			 (po.getX(),po.getY())
		}
	*/
}
