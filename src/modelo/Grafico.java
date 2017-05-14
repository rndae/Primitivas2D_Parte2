package modelo;

import java.awt.Point;

public interface Grafico{

	void escalar(Point p, int sx, int sy);
	void rotar(Point p, int deg);
	void trasladar(Point a,Point b);
	
	
	
}
