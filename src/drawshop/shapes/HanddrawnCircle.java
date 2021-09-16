package drawshop.shapes;

import drawshop.noise.Noise;
import drawshop.shapes.visitors.ShapeVisitor;

import java.awt.*;

/**
 * Cette classe permet de représenter un cercle fait à la main.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class HanddrawnCircle implements Circle {
	private double cx, cy, height, width, rad;
	private Color c;

	/**
	 * Instancie un cercle avec les attributs entrés en paramètre.
	 *
	 * @param cx la coordonnée x du centre du cercle
	 * @param cy la coordonnée y du centre du cercle
	 * @param rad le radius du cercle
	 * @param c la couleur du perimètre du cercle
	 */
	public HanddrawnCircle(double cx, double cy, double rad, Color c) {
		this.cx = cx+Noise.getNoise();
		this.cy = cy+Noise.getNoise(); 
		this.height = rad+Noise.getNoise();
		this.width = rad+Noise.getNoise();
		this.rad = rad;
		this.c = c;
	}

	/**
	 * Puisque le cercle n'est pas parfait, la hauteur peut être différente
	 * de la largeur.
	 *
	 * @return la hauteur du cercle
	 */
	public double getHeight() { return height; }

	/**
	 * Puisque le cercle n'est pas parfait, la hauteur peut être différente
	 * de la largeur.
	 *
	 * @return la largeur du cercle
	 */
	public double getWidth () { return width;  }

	/**
	 *
	 * @return le radius initial du cercle, avant les effets fait à la main.
	 */
	public double getRadius() { return rad;    }

	/**
	 *
	 * @return la couleur du perimètre du cercle.
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

	public Shape toStandard() {
		return new PerfectCircle(cx, cy, rad, c);
	}
}
