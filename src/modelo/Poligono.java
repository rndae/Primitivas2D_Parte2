package modelo;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Poligono implements Grafico{
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
    
    public String toString(){
    	return "Poligono";
    }
    
    public void nuevoPunto(Point p){
    	vertices.add(p);
    }
    
    public void generarAristas(){
    	//ArrayList<Line> aristas = new ArrayList<Line>(); 
    	for(int i=0;i<vertices.size()-1;i++){
    		aristas.add(new Line(vertices.get(i),vertices.get(i+1)));
    	}
    	aristas.add(new Line(vertices.get(0),vertices.get(vertices.size()-1)));
    	
    }
    
    public ArrayList<Line> dibujar() {
    	//generarAristas();
    	return aristas;
	}
    	
    public ArrayList<Point> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Point> vertices) {
		this.vertices = vertices;
	}

	@Override
	public void escalar(Point p, int sx, int sy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotar(Point p, int deg) {
		for(Point a:vertices){
			
		}
		
	}

	@Override
	public void trasladar(Point a,Point b) {
		int dx = a.x-b.x;
		int dy = a.y-b.y;
		for(Point p:vertices){
			p.x = p.x+dx;
			p.y = p.y+dy;
		}
		generarAristas();
	}

	
    
}
