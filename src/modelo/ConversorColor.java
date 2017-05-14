package modelo;

public class ConversorColor {

	public int[] CMYtoRGB(int c, int m, int y){
		int[] rgb = new int[3];
		rgb[0]= 1 - c;
		rgb[1]= 1 - m;
		rgb[2]= 1 - y;
		return rgb;
	}
	
	public int[] HSLtoRGB(int h, int s, int l){
		int[] rgb = new int[3];
		return rgb;
	}
		
}
