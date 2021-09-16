package drawshop.shapes;

import drawshop.noise.Noise;
import drawshop.shapes.visitors.ShapeVisitor;

import java.awt.*;

/**
 * Cette classe permet de représenter un rectangle fait à la main.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class HanddrawnRectangle implements Rectangle {
	private double[] x;
	private double[] y;
	private double[] midx;
	private double[] midy;
	private Color c;

	/**
	 * Instancie un rectangle fait à la main.
	 *
	 * @param x0 la coordonnée x du coin supérieur gauche du rectangle
	 * @param y0 la coordonnée y du coin supérieur gauche du rectangle
	 * @param x1 la coordonnée x du coin inférieur droit du rectangle
	 * @param y1 la coordonnée y du coin inférieur droit du rectangle
	 * @param c la couleur du perimètre du rectangle
	 */
	public HanddrawnRectangle(double x0, double y0, double x1, double y1, Color c) {
		
		this.x = new double[4];
		this.y = new double[4];
		this.midx = new double[4];
		this.midy = new double[4];
		
		this.x[0] = x0+Noise.getNoise(); 
		this.y[0] = y0+Noise.getNoise();
		
		this.x[1] = x0+Noise.getNoise(); 
		this.y[1] = y1+Noise.getNoise();
		
		this.x[2] = x1+Noise.getNoise();
		this.y[2] = y1+Noise.getNoise();
		
		this.x[3] = x1+Noise.getNoise(); 
		this.y[3] = y0+Noise.getNoise();
		
		this.midx[0] = (x[0]+x[1])/2 + Noise.getNoise();
		this.midy[0] = (y[0]+y[1])/2 + Noise.getNoise();
		
		this.midx[1] = (x[1]+x[2])/2 + Noise.getNoise();
		this.midy[1] = (y[1]+y[2])/2 + Noise.getNoise();
		
		this.midx[2] = (x[2]+x[3])/2 + Noise.getNoise();
		this.midy[2] = (y[2]+y[3])/2 + Noise.getNoise();
		
		this.midx[3] = (x[3]+x[0])/2 + Noise.getNoise();
		this.midy[3] = (y[3]+y[0])/2 + Noise.getNoise();
		
		this.c = c;

	}
	
	// Méthodes propres à Rectangle :
	public double getWidth() { return Math.abs(midx[2]-midx[0]); }
	public double getHeight() { return Math.abs(midy[2]-midy[0]); }

	/**
	 * Retourne la coordonnée x du coin dont l'indice est entré en paramètre.
	 *
	 * les indices des coins sont distribués dans le sens horaire avec le coin supérieur
	 * gauche ayant l'indice 0.
	 *
	 * @param pos l'indice du coin dont on veut la position
	 * @return la coordonnée x du coin dont l'indice est entré en paramètre.
	 */
	public double getX(int pos) {
		if(pos < 0 || pos >= x.length) return -1;
		return x[pos];
	}

	/**
	 * Retourne la coordonnée y du coin dont l'indice est entré en paramètre.
	 *
	 * les indices des coins sont distribués dans le sens horaire avec le coin supérieur
	 * gauche ayant l'indice 0.
	 *
	 * @param pos l'indice du coin dont on veut la position
	 * @return la coordonnée y du coin dont l'indice est entré en paramètre.
	 */
	public double getY(int pos) {
		if(pos < 0 || pos >= y.length) return -1;
		return y[pos];
	}

	/**
	 * Retourne la coordonnée x du milieu de la ligne dont l'indice est entré en paramètre.
	 *
	 * les indices des lignes sont distribués dans le sens horaire avec la ligne supérieure
	 * ayant l'indice 0.
	 *
	 * @param pos l'indice de la ligne dont on veut la position du milieu
	 * @return la coordonnée x du milieu de la ligne dont l'indice est entré en paramètre.
	 */
	public double getMidX(int pos) {
		if(pos < 0 || pos >= midx.length) return -1;
		return midx[pos];		
	}

	/**
	 * Retourne la coordonnée y du milieu de la ligne dont l'indice est entré en paramètre.
	 *
	 * les indices des lignes sont distribués dans le sens horaire avec la ligne supérieure
	 * ayant l'indice 0.
	 *
	 * @param pos l'indice de la ligne dont on veut la position du milieu
	 * @return la coordonnée y du milieu de la ligne dont l'indice est entré en paramètre.
	 */
	public double getMidY(int pos) {
		if(pos < 0 || pos >= midy.length) return -1;
		return midy[pos];	
	}

	/**
	 *
	 * @return la couleur du rectangle.
	 */
	public Color getColor() { return c; }
	
	public void acceptVisitor(ShapeVisitor sv) throws IllegalArgumentException {
		if(sv == null) throw new IllegalArgumentException("sv cannot be null");
		else sv.visit(this);
	}
	
	public void move(int dx, int dy) {
		
		for(int xp = 0; xp < x.length; xp++) x[xp] += dx;
		for(int yp = 0; yp < x.length; yp++) y[yp] += dy;
		
		for(int xp = 0; xp < midx.length; xp++) midx[xp] += dx;
		for(int yp = 0; yp < midy.length; yp++) midy[yp] += dy;

	}

	public void mirrorX(double x) {

		double[] newX = new  double[4];
		double[] newMidX = new  double[4];

		for(int i = 0; i < 4; i++)
			newX[i] = x + (x - this.x[i]);

		for(int i = 0; i < 4; i++)
			newMidX[i] = x + (x - this.midx[i]);

		double tempX = newX[0];
		newX[0] = newX[1];
		newX[1] = tempX;

		tempX = newX[2];
		newX[2] = newX[3];
		newX[3] = tempX;

		tempX = newMidX[1];
		newMidX[1] = newMidX[3];
		newMidX[3] = tempX;

		this.x = newX;
		this.midx = newMidX;
	}

	public void mirrorY(double y) {
		double[] newY = new  double[4];
		double[] newMidY = new  double[4];

		for(int i = 0; i < 4; i++)
			newY[i] = y + (y - this.y[i]);

		for(int i = 0; i < 4; i++)
			newMidY[i] = y + (y - this.midy[i]);

		double tempY = newY[0];
		newY[0] = newY[3];
		newY[3] = tempY;

		tempY = newY[2];
		newY[2] = newY[1];
		newY[1] = tempY;

		tempY = newMidY[0];
		newMidY[0] = newMidY[2];
		newMidY[2] = tempY;

		this.y = newY;
		this.midy = newMidY;
	}

	public double getCenterX() {
		return x[0] + (x[3] - x[0])/2;
	}
	public double getCenterY() {
		return y[0] + (y[2] - y[0])/2;
	}

	public Shape toStandard() {



		return new PerfectRectangle(x[0], y[0], x[2], y[2], c);
	}

}
