package drawshop.shapes;

import drawshop.shapes.visitors.ShapeVisitor;

import java.io.Serializable;

/**
 * Cette classe représente une forme, qu'importe soit elle.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.shapes.visitors.ShapeVisitor
 */

public interface Shape extends Serializable {

	/**
	 * Cette méthode permet d'effectuer l'action du ShapeVisitor
	 * entré en paramètres sur la Shape
	 *
	 * @param sv ShapeVisitor qui détermine l'action à effectuer
	 */
	public void acceptVisitor(ShapeVisitor sv);

	/**
	 * Cette méthode permet de "bouger" la Shape en modifiant ses coordonnées
	 *
	 * @param dx le décalage sur l'axe X
	 * @param dy le décalage sur l'axe Y
	 */
	public void move(int dx, int dy);

	/**
	 * Cette méthode permet d'inverser la Shape sur l'axe X
	 *
	 * @param x position de l'axe
	 */
	public void mirrorX(double x);

	/**
	 * Cette méthode permet d'inverser la Shape sur l'axe Y
	 *
	 * @param y position de l'axe
	 */
	public void mirrorY(double y);

	/**
	 *
	 * @return le milieu de la Shape sur l'axe X
	 */
	public double getCenterX();

	/**
	 *
	 * @return le milieu de la Shape sur l'axe Y
	 */
	public double getCenterY();

	/**
	 *
	 * @return la Shape dessinée de façon Parfaite
	 * @see drawshop.shapes.factories.DrawingType
	 */
	public Shape toStandard();
}