package modelo;

import java.awt.Color;
/**
 *
 * @author Cristhian
 */
public class Primitive2D {
    private String grosor;
    private String tipo;
    private Color color;
    
    public Primitive2D(){
        setDefault();
    }
    
    public Primitive2D(String g, String t, Color c){
        setGrosor(g);
        setTipo(t);
        setColor(c);
    }
    
    public void setDefault(){
        setGrosor("Delgado");
        setTipo("Continuo");
        setColor(0,76,153); 
    }

    /**
     * @return the grosor
     */
    public String getGrosor() {
        return grosor;
    }

    /**
     * @param grosor the grosor to set
     */
    public void setGrosor(String grosor) {
        this.grosor = grosor;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int r,int g,int b) {
        this.color = new Color(r,g,b);
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
}