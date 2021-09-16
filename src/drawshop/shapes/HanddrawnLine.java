package drawshop.shapes;

import drawshop.noise.Noise;
import drawshop.shapes.visitors.ShapeVisitor;

import java.awt.*;

/**
 * Cette classe permet de représenter une ligne dessinée à main levée
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class HanddrawnLine implements Line {
	private double x0, y0, x1, y1, x2, y2;
	private Color c;

	/**
	 * Ce constructeur permet de créer une ligne légèrement déformée
	 * avec toutes les coordonnées pré-remplies ainsi que sa couleur
	 *
	 * @param x0 coordonnée x du premier point de la ligne
	 * @param y0 coordonnée y du premier point de la ligne
	 * @param x1 coordonnée x du second point de la ligne
	 * @param y1 coordonnée y du second point de la ligne
	 * @param c couleur de la ligne
	 */
	public HanddrawnLine(double x0, double y0, double x1, double y1, Color c) {
		this.x0 = x0+Noise.getNoise(); this.y0 = y0+Noise.getNoise();
		this.x1 = x1+Noise.getNoise(); this.y1 = y1+Noise.getNoise();
		this.x2 = (x0+x1)/2 + Noise.getNoise();
		this.y2 = (y0+y1)/2 + Noise.getNoise();
		this.c = c;
	}

	public double getLength() {
		double dx=Math.abs(x1-x0);
		double dy=Math.abs(y1-y0);
		return Math.sqrt(dx*dx+dy*dy);
	}

	/**
	 *
	 * @return la coordonnée x du premier point de la ligne
	 */
	public double getX0() { return x0; }

	/**
	 *
	 * @return la coordonnée y du premier point de la ligne
	 */
	public double getY0() { return y0; }

	/**
	 *
	 * @return la coordonnée x du second point de la ligne
	 */
	public double getX1() { return x1; }

	/**
	 *
	 * @return la coordonnée y du second point de la ligne
	 */
	public double getY1() { return y1; }

	/**
	 *
	 * @return la coordonnée x du point au milieu de la ligne légèrement déviée
	 */
	public double getX2() { return x2; }

	/**
	 *
	 * @return la coordonnée y du point au milieu de la ligne légèrement déviée
	 */
	public double getY2() { return y2; }

	/**
	 *
	 * @return la couleur de la ligne
	 */
	public Color getColor() { return c; }
	
	public void acceptVisitor(ShapeVisitor sv) throws IllegalArgumentException {
		if(sv == null) throw new IllegalArgumentException("sv cannot be null");
		else sv.visit(this);
	}
	
	public void move(int dx, int dy) {
		x0 += dx;
		y0 += dy;
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
		
	}

	public void mirrorX(double x) {
		x0 = x + (x - x0);
		x1 = x + (x - x1);
		x2 = x + (x - x2);
	}

	public void mirrorY(double y) {
		y0 = y + (y - y0);
		y1 = y + (y - y1);
		y2 = y + (y - y2);
	}

	public double getCenterX() {
		return x0 + (x1 - x0)/2;
	}
	public double getCenterY() { return y0 + (y1 - y0)/2; }

	public Shape toStandard() {
		return new PerfectLine(x0, y0, x1, y1, c);
	}
}
