package drawshop.shapes.visitors;

import drawshop.shapes.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

/**
 * Cette classe est un Visitor permettant de dessiner une forme sur un Graphics2D
 *
 * Les formes actuellement supportées sont :
 * <ul>
 *     <li>Le rectangle parfait</li>
 *     <li>Le cercle parfait</li>
 *     <li>La ligne parfaite</li>
 *     <li>Le rectangle fait à la main</li>
 *     <li>Le cercle fait à la main</li>
 *     <li>La ligne faite à la main</li>
 * </ul>
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class ShapeDrawingVisitor implements ShapeVisitor {

	private Graphics2D canvas;

	/**
	 * Instancie un ShapeDrawingVisitor avec le canvas entré en paramètre.
	 *
	 * @param canvas le Graphics2D sur lequel dessiner les formes.
	 */
	public ShapeDrawingVisitor(Graphics2D canvas) {
		this.canvas = canvas;
	}

	/**
	 *  Dessine sur le canvas un cercle fait à la main.
	 * @param circle le cercle à dessiner.
	 */
	public void visit(HanddrawnCircle circle) {
		canvas.setColor(circle.getColor());
		canvas.draw(
				new Ellipse2D.Double(
						circle.getCenterX()-circle.getRadius(),
						circle.getCenterY()-circle.getRadius(),
						circle.getWidth()*2, circle.getHeight()*2));
	}

	/**
	 *  Dessine sur le canvas une ligne faite à la main.
	 * @param line la ligne à dessiner.
	 */
	public void visit(HanddrawnLine line) {
		canvas.setColor(line.getColor());
		canvas.draw(new QuadCurve2D.Double(line.getX0(), line.getY0(), line.getX2(), line.getY2(), line.getX1(), line.getY1()));
	}

	/**
	 *  Dessine sur le canvas un rectangle fait à la main.
	 * @param rectangle le rectangle à dessiner.
	 */
	public void visit(HanddrawnRectangle rectangle) {
		canvas.setColor(rectangle.getColor());
		
		for(int i = 0; i < 4; i++)
			canvas.draw(
					new QuadCurve2D.Double(
							rectangle.getX(i), 
							rectangle.getY(i), 
							rectangle.getMidX(i), 
							rectangle.getMidY(i), 
							rectangle.getX((i+1)%4), 
							rectangle.getY((i+1)%4)));
	}

	/**
	 *  Dessine sur le canvas un cercle parfait.
	 * @param circle le cercle à dessiner.
	 */
	public void visit(PerfectCircle circle) {
		canvas.setColor(circle.getColor());
		canvas.draw(
				new Ellipse2D.Double(
						circle.getCenterX()-circle.getRadius(),
						circle.getCenterY()-circle.getRadius(),
						circle.getRadius()*2, circle.getRadius()*2));
	}

	/**
	 *  Dessine sur le canvas une ligne parfaite.
	 * @param line la ligne à dessiner.
	 */
	public void visit(PerfectLine line) {
		canvas.setColor(line.getColor());
		canvas.draw(new Line2D.Double(line.getX0(), line.getY0(), line.getX1(), line.getY1()));
	}

	/**
	 *  Dessine sur le canvas un rectangle parfait.
	 * @param rectangle le rectangle à dessiner.
	 */
	public void visit(PerfectRectangle rectangle) {
		canvas.setColor(rectangle.getColor());
		canvas.draw(
				new Rectangle2D.Double(
						rectangle.getX0(), 
						rectangle.getY0(), 
						rectangle.getX1()-rectangle.getX0(), 
						rectangle.getY1()-rectangle.getY0()));
	}

}
