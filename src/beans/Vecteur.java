/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author TOSHIBA
 */
public class Vecteur implements Serializable{
    private double x;
    private double y;
    private double norme;

    public Vecteur(double x, double y, double norme) {
        this.x = x;
        this.y = y;
        this.norme = norme;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getNorme() {
        return norme;
    }

    public void setNorme(double norme) {
        this.norme = norme;
    }
    
    
    
}
