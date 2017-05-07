package modelo;

import java.awt.Point;

public class Cuadrado extends Poligono{
	public Cuadrado(Point p,int lado){
		super();
		nuevoPunto(p);
		nuevoPunto(new Point(p.x+lado,p.y));
		nuevoPunto(new Point(p.x+lado,p.y+lado));
		nuevoPunto(new Point(p.x,p.y+lado));
		generarAristas();
	}
	
		
	
}
