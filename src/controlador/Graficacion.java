package controlador;


import java.awt.Point;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import modelo.Circumference;
import modelo.Cuadrado;
import modelo.FloodFill;
import modelo.Line;
import modelo.Poligono;
import modelo.Triangulo;

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
	@FXML private TextField colorR, colorG, colorB ;
	@FXML private TextField corX, corY;
	
	public Graficacion(){
		
	}
	
	@FXML private void dibujar(ActionEvent event){
		GraphicsContext gc = lienzo.getGraphicsContext2D();
		/*//---prueba
		if(lp1!=null && lp2!=null)
			pintarLinea(new modelo.Line(new Point(0, 5),new Point(0, 200)), gc);
		if(centro!=null && radio!=null)
			pintarCircunferencia(new Circumference(420,150, 150), gc);
		if(tp1!=null && tp2!=null && tp3!=null)
			pintarPoligono(new Triangulo(new Point(50, 5),new Point(50, 200), new Point(150, 100)), gc);
		if(cp!=null && largo!=null)
			pintarPoligono(new Cuadrado(new Point(150, 50),250), gc);
        */
		if(lp1.getText().length()>0 && lp1.getText().length()>0)
			pintarLinea(nuevaLinea(), gc);
		if(centro.getText().length()>0 && radio.getText().length()>0)
			pintarCircunferencia(nuevaCircunferencia(), gc);
		if(tp1.getText().length()>0 && tp2.getText().length()>0 && tp3.getText().length()>0)
			pintarPoligono(nuevoTriangulo(), gc);
		if(cp.getText().length()>0 && largo.getText().length()>0)
			pintarPoligono(nuevoCuadrado(), gc);
		
		System.out.print("Dibuja. ");
	}
	
	@FXML private void rellenar(ActionEvent event) throws Exception{		
		FloodFill floodfill = new FloodFill();
		WritableImage imagen = lienzo.snapshot(null, null);
		GraphicsContext gc = lienzo.getGraphicsContext2D();
		Color rellenado = Color.rgb(Integer.parseInt(colorR.getText()), 
				Integer.parseInt(colorG.getText()),Integer.parseInt(colorB.getText()));  
		int veces = floodfill.fill(Integer.parseInt(corX.getText()), Integer.parseInt(corY.getText()),
				    Color.WHITE, gc, lienzo, imagen, rellenado);
		gc.drawImage(imagen, 0, 0);
		System.out.print("Rellena "+veces + " pixeles. ");
	}
	
	private void pintarCircunferencia(Circumference cir, GraphicsContext gc){
		ArrayList<Point> ar = cir.bresenham();
		for(Point po: ar){
			gc.fillRect(po.getX(), po.getY(), 1, 1);
		}
	}
	
	private void pintarLinea(modelo.Line lin, GraphicsContext gc){
			ArrayList<Point> ar = lin.bresenham();
			for(Point po: ar){
				gc.fillRect(po.getX(), po.getY(), 1, 1);
			}		
	}
	
	private void pintarPoligono(Poligono pol, GraphicsContext gc){
		ArrayList<Line> lins = pol.dibujar();
		for(Line lin: lins){
			ArrayList<Point> ar = lin.bresenham(); //lin.DDA() ---revisar--
			for(Point po: ar){
				gc.fillRect(po.getX(), po.getY(), 1, 1);
			}
		}		
	}
	
	private Line nuevaLinea(){
		String[] a = lp1.getText().split(" |,");
		Point p1 = new Point(Integer.parseInt(a[0]), Integer.parseInt(a[1]));	
		a = lp2.getText().split(" |,");
		Point p2 = new Point(Integer.parseInt(a[0]), Integer.parseInt(a[1]));				
		return new Line(p1, p2);
	}
	
	private Circumference nuevaCircunferencia(){
		String[] a = centro.getText().split(" |,");	
		int ra = Integer.parseInt(radio.getText());		
		return new Circumference(Integer.parseInt(a[0]),Integer.parseInt(a[1]), ra);
	}
	
	private  Triangulo nuevoTriangulo(){
		String[] a = tp1.getText().split(" |,");
		Point p1 = new Point(Integer.parseInt(a[0]), Integer.parseInt(a[1]));	
		a = tp2.getText().split(" |,");
		Point p2 = new Point(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
		a = tp3.getText().split(" |,");
		Point p3 = new Point(Integer.parseInt(a[0]), Integer.parseInt(a[1]));	
		return new Triangulo(p1, p2,p3);
	}
	
	private Cuadrado nuevoCuadrado(){
		String[] a = cp.getText().split(" |,");	
		int lon = Integer.parseInt(largo.getText());		
		return new Cuadrado(new Point(Integer.parseInt(a[0]),Integer.parseInt(a[1])), lon);
	}
	
	
	
	//gc.setFill(Color.BLACK);  color de trazo 
}
