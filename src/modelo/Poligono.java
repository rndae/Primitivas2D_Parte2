package modelo;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Poligono {
	private String grosor;
    private String tipo;
    private Color color;
    ArrayList<Point> vertices;
    ArrayList<Line> aristas;
    
    public Poligono(){
    	setDefault();
    	vertices = new ArrayList<Point>();
    	aristas = new ArrayList<Line>();
    }
    
    public void setDefault(){
    	grosor ="Delgado";
        tipo="Continuo";
        color = new Color( 0,76,153); 
    }
    
    public void nuevoPunto(Point p){
    	vertices.add(p);
    }
    
    public void generarAristas(){
    	for(int i=0;i<vertices.size()-1;i++){
    		aristas.add(new Line(vertices.get(i),vertices.get(i+1)));
    	}
    	aristas.add(new Line(vertices.get(0),vertices.get(vertices.size()-1)));
    	
    }
    
    public ArrayList<Line> dibujar() {
    	return aristas;
	}
    	
    public ArrayList<Point> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Point> vertices) {
		this.vertices = vertices;
	}
    
}
