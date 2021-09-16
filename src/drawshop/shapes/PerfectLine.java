package drawshop.shapes;


import drawshop.shapes.visitors.ShapeVisitor;

import java.awt.*;

/**
 * Cette classe permet de représenter une ligne parfaite.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class PerfectLine implements Line, PerfectShape {
	private double x0, y0, x1, y1;
	private Color c;

	/**
	 * Ce constructeur permet de créer une ligne avec toutes les coordonnées pré-remplies
	 * ainsi que sa couleur
	 *
	 * @param x0 coordonnée x du premier point de la ligne
	 * @param y0 coordonnée y du premier point de la ligne
	 * @param x1 coordonnée x du second point de la ligne
	 * @param y1 coordonnée y du second point de la ligne
	 * @param c couleur de la ligne
	 */
	public PerfectLine(double x0, double y0, double x1, double y1, Color c) {
		this.x0 = x0; this.y0 = y0;
		this.x1 = x1; this.y1 = y1;
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
	}

	public void mirrorX(double x) {
		x0 = x + (x - x0);
		x1 = x + (x - x1);
	}

	public void mirrorY(double y) {
		y0 = y + (y - y0);
		y1 = y + (y - y1);
	}

	public double getCenterX() { return x0 + (x1 - x0)/2; }
	public double getCenterY() { return y0 + (y1 - y0)/2; }

	public double getArea() { return 0; }
	public Shape toStandard() { return this; }

}

