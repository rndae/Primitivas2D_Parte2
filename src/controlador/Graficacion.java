package controlador;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import modelo.Circumference;
import modelo.Cuadrado;
import modelo.FloodFill;
import modelo.Line;
import modelo.Poligono;
import modelo.ScanLine;
import modelo.Triangulo;
import modelo.Grafico;

public class Graficacion{  
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
	@FXML private Label col1, col2, col3;
	@FXML private TextField corX, corY;
	@FXML private Button btnBorrar;
	
	//Parte tres, Transformaciones
	@FXML private ListView<String> lista;
	
	@FXML private TextField rotar;
	
	@FXML private TextField escalar;
	
	@FXML private TextField tcorX, tcorY;
	@FXML private Button btnTransformar;
	
	ObservableList<String> figsTexto;
	HashMap<String,Grafico> figuras;
		
	public Graficacion(){
		figsTexto = FXCollections.observableArrayList();
		figuras = new HashMap<String, Grafico>();
	}
	
	private void actualizar(Grafico fig, String claveAnte, int indice){
		figsTexto.set(indice, fig.toString());
		repintar(lienzo.getGraphicsContext2D());
		lista.setItems(figsTexto);
	}
	
	private void repintar(GraphicsContext gc){
		lienzo.getGraphicsContext2D().clearRect(0, 0, lienzo.getWidth(), lienzo.getHeight());		
		for(Grafico fig : figuras.values()){
			pintarFigura(fig, lienzo.getGraphicsContext2D());
		}
	}
    
	@FXML private void transformar(ActionEvent e){
		String clave = lista.getSelectionModel().getSelectedItem();
		int indice = lista.getSelectionModel().getSelectedIndex();
		if(clave != null){
				Grafico fig = figuras.get(clave);
				if(fig != null){
				if(rotar.getText().length()>0){
					fig.rotar(Integer.parseInt(rotar.getText()));
				}
				if(escalar.getText().length()>0){
					fig.escalar(Integer.parseInt(escalar.getText()));
				}
				if(tcorX.getText().length()>0 && tcorY.getText().length()>0){
					fig.trasladar(new Point(1,1), new Point(Integer.parseInt(tcorX.getText())
							,Integer.parseInt(tcorY.getText())));
				}
				//pintarFigura(fig, lienzo.getGraphicsContext2D());
				actualizar(fig, clave, indice);
				System.out.println(clave);
				System.out.println(fig.toString());
			}
		}
	}
	
	@FXML private void borrar(ActionEvent e){
		lienzo.getGraphicsContext2D().clearRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
		figsTexto = FXCollections.observableArrayList();
		figuras = new HashMap<String, Grafico>();
		lista.setItems(figsTexto);
		System.out.println("Borra. ");
	}
	
	@FXML private void dibujar(ActionEvent event){
		GraphicsContext gc = lienzo.getGraphicsContext2D();
		/*//---prueba		
			pintarLinea(new modelo.Line(new Point(0, 5),new Point(0, 200)), gc);		
			pintarCircunferencia(new Circumference(420,150, 150), gc);
			pintarPoligono(new Triangulo(new Point(50, 5),new Point(50, 200), new Point(150, 100)), gc);		
			pintarPoligono(new Cuadrado(new Point(150, 50),250), gc);
        */
		if(lp1.getText().length()>0 && lp1.getText().length()>0){
			modelo.Line lin = pintarLinea(nuevaLinea(), gc);
			figsTexto.add(lin.toString());
			figuras.put(lin.toString(), lin);
			}
		if(centro.getText().length()>0 && radio.getText().length()>0){
			Circumference cir = pintarCircunferencia(nuevaCircunferencia(), gc);
			figsTexto.add(cir.toString());
			figuras.put(cir.toString(), cir);
		}
		if(tp1.getText().length()>0 && tp2.getText().length()>0 && tp3.getText().length()>0){
			Poligono poli = pintarPoligono(nuevoTriangulo(), gc);			
			figsTexto.add(poli.toString());
			figuras.put(poli.toString(),poli);
		}
		if(cp.getText().length()>0 && largo.getText().length()>0){
			Poligono poli = pintarPoligono(nuevoCuadrado(), gc);
			figsTexto.add(poli.toString());
			figuras.put(poli.toString(), poli);
		}
		System.out.print("Dibuja. ");		
		lista.setItems(figsTexto);
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
	
	@FXML private void seleccion(MouseEvent e){
		corX.setText(""+(int)e.getX());
		corY.setText(""+(int)e.getY());
		tcorX.setText(""+(int)e.getX());
		tcorY.setText(""+(int)e.getY());
		//System.out.println(e.getX()+" "+e.getY());
	}
	
	private void pintarFigura(Grafico graf, GraphicsContext gc){
		if(graf instanceof Line){
			pintarLinea((Line)graf, gc);
		}
		else if(graf instanceof Circumference){
			pintarCircunferencia((Circumference)graf, gc);
		}
		else if(graf instanceof Poligono){
			pintarPoligono((Poligono)graf, gc);
		}
	}
	
	private Circumference pintarCircunferencia(Circumference cir, GraphicsContext gc){
		ArrayList<Point> ar = cir.bresenham();
		for(Point po: ar){
			gc.fillRect(po.getX(), po.getY(), 1, 1);
		}
		return cir;
	}
	
	private modelo.Line pintarLinea(modelo.Line lin, GraphicsContext gc){
			ArrayList<Point> ar = lin.bresenham();
			for(Point po: ar){
				gc.fillRect(po.getX(), po.getY(), 1, 1);
			}
			return lin;
	}
	
	private Poligono pintarPoligono(Poligono pol, GraphicsContext gc){
		ArrayList<Line> lins = pol.dibujar();
		for(Line lin: lins){
			ArrayList<Point> ar = lin.DDA();
			for(Point po: ar){
				gc.fillRect(po.getX(), po.getY(), 1, 1);
			}
		}
		return pol;
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
	
	private Triangulo nuevoTriangulo(){
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

	
}
