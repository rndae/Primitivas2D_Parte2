package modelo;

public class ConversorColor {

	public int[] CMYtoRGB(int c, int m, int y) {
		int[] rgb = new int[3];
		rgb[0] = 1 - c;
		rgb[1] = 1 - m;
		rgb[2] = 1 - y;
		return rgb;
	}

	public double[] HSLtoRGB(int h, double s, double l) {
		double[] rgb = new double[3];		
		if (validoHSL(h, s, l)) {
			double c = (1 - Math.abs(2 * l - 1)) * s;
			int hp = h / 60;
			double x = c * (1 - Math.abs((hp % 2) - 1));
			if(0 <= hp && hp < 1){
				rgb[0] = c;
				rgb[1] = x;
				rgb[2] = 0;
			}
			if(1 <= hp && hp < 2){
				rgb[0] = x;
				rgb[1] = c;
				rgb[2] = 0;
			}
			if(2 <= hp && hp < 3){
				rgb[0] = 0;
				rgb[1] = c;
				rgb[2] = x;
			}
			if(3 <= hp && hp < 4){
				rgb[0] = 0;
				rgb[1] = x;
				rgb[2] = c;
			}
			if(4 <= hp && hp < 5){
				rgb[0] = x;
				rgb[1] = 0;
				rgb[2] = c;
			}
			if(5 <= hp && hp < 6){
				rgb[0] = c;
				rgb[1] = 0;
				rgb[2] = x;
			}
			double m = l - (c / 2);
			rgb[0] = Math.round(((rgb[0]+m)*255));
			rgb[1] = Math.round((int)((rgb[1]+m)*255));
			rgb[2] = Math.round(((rgb[2]+m)*255));
			
			
			System.out.println(c);
			System.out.println(hp);
			System.out.println(x);
			System.out.println(m);
		}
		
		return rgb;
	}

	private boolean validoHSL(int h, double s, double l) {
		boolean valido = false;
		if (0 <= h && h < 360) {
			if (0 <= s && s <= 1.0) {
				if (0 <= l && l <= 1.0) {
					valido = true;
				}
			}
		}
		return valido;
	}

}
