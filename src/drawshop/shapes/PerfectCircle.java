package drawshop.shapes;

import drawshop.shapes.visitors.ShapeVisitor;

import java.awt.*;

/**
 * Cette classe permet de représenter un cercle parfait.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class PerfectCircle implements Circle, PerfectShape {
	private double cx, cy, rad;
	private Color c;

	/**
	 * Instancie un cercle avec les attributs entrés en paramètre.
	 *
	 * @param cx la coordonnée x du centre du cercle
	 * @param cy la coordonnée y du centre du cercle
	 * @param rad le radius du cercle
	 * @param c la couleur du perimètre du cercle
	 */
	public PerfectCircle(double cx, double cy, double rad, Color c) {
		this.cx = cx; this.cy = cy; this.rad = rad;
		this.c = c;
	}
	
	public double getRadius() { return rad; }

	/**
	 *
	 * @return la couleur du cercle.
	 */
	public Color getColor() { return c; }
	
	public void acceptVisitor(ShapeVisitor sv) throws IllegalArgumentException {
		if(sv == null) throw new IllegalArgumentException("sv cannot be null");
		else sv.visit(this);
	}
	
	public void move(int dx, int dy) {
		cx += dx;
		cy += dy;
	}

	public void mirrorX(double x) {
		cx = x + (x - cx);
	}
	public void mirrorY(double y) { cy = y + (y - cy); }

	public double getCenterX() { return cx; }
	public double getCenterY() { return cy; }

	public double getArea() {
		return Math.PI*rad*rad;
	}

	public Shape toStandard() { return this; }

}


