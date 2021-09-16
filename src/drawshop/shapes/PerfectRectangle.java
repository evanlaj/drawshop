package drawshop.shapes;

import drawshop.shapes.visitors.ShapeVisitor;

import java.awt.*;

/**
 * Cette classe permet de représenter un rectangle parfait.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */

public class PerfectRectangle implements Rectangle, PerfectShape {
	private double x0, y0, x1, y1;
	private Color c;

	/**
	 * Ce constructeur permet de créer un rectangle avec toutes les coordonnées pré-remplies
	 * ainsi que sa couleur
	 *
	 * @param x0 la coordonnée x du coin supérieur gauche du rectangle
	 * @param y0 la coordonnée y du coin supérieur gauche du rectangle
	 * @param x1 la coordonnée x du coin inférieur droit du rectangle
	 * @param y1 la coordonnée y du coin inférieur droit du rectangle
	 * @param c la couleur du perimètre du rectangle
	 */
	public PerfectRectangle(double x0, double y0, double x1, double y1, Color c) {
		this.x0 = x0; this.y0 = y0;
		this.x1 = x1; this.y1 = y1;
		this.c = c;
	}

	public double getWidth() { return Math.abs(x1-x0); }

	public double getHeight() { return Math.abs(y1-y0); }

	/**
	 *
	 * @return la coordonnée x du coin supérieur gauche du rectangle
	 */
	public double getX0() { return x0; }

	/**
	 *
	 * @return la coordonnée y du coin supérieur gauche du rectangle
	 */
	public double getY0() { return y0; }

	/**
	 *
	 * @return la coordonnée x du coin inférieur droit du rectangle
	 */
	public double getX1() { return x1; }

	/**
	 *
	 * @return la coordonnée y du coin inférieur droit du rectangle
	 */
	public double getY1() { return y1; }

	/**
	 *
	 * @return la couleur du rectangle
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
		double newX1 = x + (x - x0);

		x0 = x + (x - x1);
		x1 = newX1;
	}

	public void mirrorY(double y) {
		double newY1 = y + (y - y0);

		y0 = y + (y - y1);
		y1 = newY1;
	}

	public double getCenterX() { return x0 + (x1 - x0)/2; }
	public double getCenterY() { return y0 + (y1 - y0)/2; }

	public double getArea() {
		return (x1-x0)*(y1-y0);
	}

	public Shape toStandard() { return this; }
}