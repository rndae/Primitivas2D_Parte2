package modelo;



import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class FloodFill {
	public FloodFill(){
		
	}
	
	//Rellenado sin imagen
	public int fill(int x, int y, Color color, GraphicsContext gc, Canvas lienzo){
        Queue<Point> puntos = new LinkedList<Point>();
        puntos.offer(new Point(x, y));
        PixelReader pr;
        Color relleno = Color.INDIGO;
        int veces = 0;
        while (puntos.size() > 0){
            Point p = puntos.poll();
            //veces++;
            x = p.x;
            y = p.y;
            pr = lienzo.snapshot(null,null).getPixelReader();
            Color act = pr.getColor(x, y);            
            if(color.equals(act)){
            	//gc.fillRect(x, y, 1, 1);
            	veces++;
                gc.getPixelWriter().setColor(x,y, relleno);
                if(color.equals(pr.getColor(x, y-1)))
                	puntos.offer(new Point(x , y - 1));
                if(color.equals(pr.getColor(x+1, y)))
                	puntos.offer(new Point(x + 1, y));
                if(color.equals(pr.getColor(x, y + 1)))
                	puntos.offer(new Point(x, y +1));
                if(color.equals(pr.getColor(x -1, y)))
                	puntos.offer(new Point(x - 1, y));            	
            }
        }
        return veces;
    }
	
	//Rellenado a imagen
	public int fill(int x, int y, Color color, GraphicsContext gc, Canvas lienzo,
				WritableImage imagen, Color relleno){
        Queue<Point> puntos = new LinkedList<Point>();
        puntos.offer(new Point(x, y));
        PixelReader pr = imagen.getPixelReader();
        Color ant = pr.getColor(x, y);
        if(!ant.equals(relleno))
        	color = ant;
        if(relleno == null) relleno = Color.INDIGO;
        int veces = 0;
        while (puntos.size() > 0){
            Point p = puntos.poll();
            x = p.x;
            y = p.y;            
            if(color.equals(pr.getColor(x, y))){
            	//gc.fillRect(x, y, 1, 1);
            	veces++;
                imagen.getPixelWriter().setColor(x,y, relleno);
                if(color.equals(pr.getColor(x, y-1)))
                	puntos.offer(new Point(x , y - 1));
                if(color.equals(pr.getColor(x+1, y)))
                	puntos.offer(new Point(x + 1, y));
                if(color.equals(pr.getColor(x, y + 1)))
                	puntos.offer(new Point(x, y +1));
                if(color.equals(pr.getColor(x -1, y)))
                	puntos.offer(new Point(x - 1, y));            	
            }
        }
        return veces;
    }
	
}
