package modelo;



import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class FloodFill {
	public FloodFill(){
		
	}
	
	public void floodFill4(Color color,Canvas lienzo,int x, int y) throws Exception{
		//Image imagen = input.snapshot(null, null);
		
		/*ImageInputStream imagenInput = ImageIO.createImageInputStream(input);
		BufferedImage imagen = ImageIO.read(imagenInput);
		*/
		llenar4(color,lienzo,x, y);
	}
	
	private void llenar4(Color color, /*BufferedImage*/Canvas lienzo, int x, int y) {
		if(!pixelPintado(lienzo, x, y)){
			lienzo.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
			llenar4(color,lienzo,x,y-1);
			llenar4(color,lienzo,x+1,y);
			llenar4(color,lienzo,x,y+1);
			llenar4(color,lienzo,x-1,y);
		}
	}

		
	private boolean pixelPintado(/*BufferedImage*/Canvas lienzo, int x, int y){
		String white="#FFFFFF";
		int blanco = Integer.parseInt(white);
		PixelReader pr = lienzo.snapshot(new SnapshotParameters(), null ) .getPixelReader(); 
		return pr.getColor(x, y).toString().equals("white");
	}
	
	public void inundacion(Canvas lienzo){
		
	}
}
