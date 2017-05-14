package modelo;

import java.awt.Point;

public class Triangulo extends Poligono{
	private Point p1, p2, p3;
	
	public Triangulo(Point p1,Point p2,Point p3){
		super();
		this.p1 = p1; this.p2 = p2; this.p3 = p3;
		nuevoPunto(p1);
		nuevoPunto(p2);
		nuevoPunto(p3);
		generarAristas();
	}
	public String toString(){
    	return "Triangulo ("+p1.x+","+p1.y+")-("+p2.x+","+p2.y+")-("+p3.x+","+p3.y+")";
    }
}
