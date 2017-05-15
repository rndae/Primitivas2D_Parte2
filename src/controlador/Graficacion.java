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
import modelo.ConversorColor;
import modelo.Cuadrado;
import modelo.FloodFill;
import modelo.Line;
import modelo.Poligono;
import modelo.ScanLine;
import modelo.Triangulo;
import modelo.Grafico;

public class Graficacion{  
	@FXML private AnchorPane controles;
	@FXML private AnchorPane inicio;
	
	@FXML private AnchorPane pantalla;
	
	@FXML private Button btnColor;
	
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
	
	private ObservableList<String> figsTexto;
	private HashMap<String,Grafico> figuras;
	private int lins, circs, cuads, tris;
		
	public Graficacion(){
		figsTexto = FXCollections.observableArrayList();
		figuras = new HashMap<String, Grafico>();
		lins = 0; circs = 0; cuads = 0; tris = 0;
		}
	
	private void actualizar(Grafico fig, String claveAnte, int indice){
		//figsTexto.set(indice, fig.toString());
		repintar(lienzo.getGraphicsContext2D());
		//lista.setItems(figsTexto);
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
					fig.escalar(Double.parseDouble(escalar.getText()));
				}
				if(tcorX.getText().length()>0 && tcorY.getText().length()>0){
					fig.trasladar(new Point(Integer.parseInt(tcorX.getText())
							,Integer.parseInt(tcorY.getText())));
				}
				//pintarFigura(fig, lienzo.getGraphicsContext2D());
				actualizar(fig, clave, indice);
				System.out.println(clave);
				System.out.println(fig.toString());
			}

		}
	}
	
	@FXML private void seleccionarColor(){
		inicio.setVisible(true);
	}
	
	@FXML private void btnRGB(){
		inicio.setVisible(false);
		col1.setText("R:");
		col2.setText("G:");
		col3.setText("B:");
		System.out.println("a RGB");
	}
	@FXML private void btnHSL(){
		inicio.setVisible(false);
		col1.setText("H:");
		col2.setText("S:");
		col3.setText("L:");
		System.out.println("a HSL");
	}
	@FXML private void btnCMY(){
		inicio.setVisible(false);
		col1.setText("C:");
		col2.setText("M:");
		col3.setText("Y:");
		System.out.println("a CMY");
	}
	
	@FXML private void borrar(ActionEvent e){
		lienzo.getGraphicsContext2D().clearRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
		figsTexto = FXCollections.observableArrayList();
		figuras = new HashMap<String, Grafico>();
		lista.setItems(figsTexto);
		lins = 0; circs = 0; cuads = 0; tris = 0;
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
		String clave = "";
		if(lp1.getText().length()>0 && lp2.getText().length()>0){
			modelo.Line lin = pintarLinea(nuevaLinea(), gc);
			clave = lin.toString()+lins;
			figsTexto.add(clave);
			figuras.put(clave, lin);
			lins++;
			}
		if(centro.getText().length()>0 && radio.getText().length()>0){
			Circumference cir = pintarCircunferencia(nuevaCircunferencia(), gc);	
			clave = cir.toString()+circs;
			figsTexto.add(clave);
			figuras.put(clave, cir);
			circs++;
		}
		if(tp1.getText().length()>0 && tp2.getText().length()>0 && tp3.getText().length()>0){
			Poligono poli = pintarPoligono(nuevoTriangulo(), gc);	
			clave = poli.toString()+tris;
			figsTexto.add(clave);
			figuras.put(clave,poli);
			tris++;
		}
		if(cp.getText().length()>0 && largo.getText().length()>0){
			Poligono poli = pintarPoligono(nuevoCuadrado(), gc);
			clave = poli.toString()+cuads;
			figsTexto.add(clave);
			figuras.put(clave, poli);
			cuads++;
		}
		lp1.setText("");lp2.setText("");
		radio.setText("");centro.setText("");
		tp1.setText("");tp2.setText("");tp3.setText("");
		largo.setText("");cp.setText("");
		
		System.out.print("Dibuja. ");		
		lista.setItems(figsTexto);
	}
	
	@FXML private void rellenar(ActionEvent event) throws Exception{
		double r = Double.parseDouble(colorR.getText());
		double g = Double.parseDouble(colorG.getText());
		double b = Double.parseDouble(colorB.getText());
		int[] cols = new int[3];
		cols[0] = (int)r; cols[1] = (int)g; cols[2]= (int)b;
		 if(col1.getText().charAt(0) =='H'){
			cols = ConversorColor.HSLtoRGB((int)r, (int)g, (int)b);
		 }else if(col1.getText().charAt(0) =='C'){
				cols = ConversorColor.CMYtoRGB(r, g, b);
	     }
		r = cols[0] ; g = cols[1]; b = cols[2];
		
		FloodFill floodfill = new FloodFill();
		WritableImage imagen = lienzo.snapshot(null, null);
			GraphicsContext gc = lienzo.getGraphicsContext2D();
		Color rellenado = Color.rgb((int)r,(int)g,(int)b); 
		System.out.println(r);
		System.out.println(g);
		System.out.println(b);
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
			ArrayList<Point> ar = lin.bresenham();
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
