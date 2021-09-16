package drawshop.shapes.factories;

import drawshop.shapes.Circle;
import drawshop.shapes.Line;
import drawshop.shapes.Rectangle;

import java.awt.*;

/**
 * Cette interface est un modèle de Factory permettant d'instancier des formes.
 *
 * Les formes actuellement supportées sont :
 * <ul>
 *     <li>Le rectangle</li>
 *     <li>Le cercle</li>
 *     <li>La ligne</li>
 * </ul>
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public interface ShapeFactory {

	/**
	 * Instancie un cercle avec les attributs entrés en paramètre.
	 *
	 * @param cx la coordonnée x du centre du cercle
	 * @param cy la coordonnée y du centre du cercle
	 * @param rad le radius du cercle
	 * @param c la couleur du perimètre du cercle
	 * @return un cercle instancié avec les variables entrés en paramètre
	 */
	public Circle createCircle(double cx, double cy, double rad, Color c);

	/**
	 * Instancie un cercle avec les attributs entrés en paramètre.
	 *
	 * @param x0 la coordonnée x du coin supérieur gauche du rectangle
	 * @param y0 la coordonnée y du coin supérieur gauche du rectangle
	 * @param x1 la coordonnée x du coin inférieur droit du rectangle
	 * @param y1 la coordonnée y du coin inférieur droit du rectangle
	 * @param c la couleur du perimètre du rectangle
	 * @return un rectangle instancié avec les attributs entrés en paramètre
	 */
	public Rectangle createRectangle(double x0, double y0, double x1, double y1, Color c);

	/**
	 * Instancie une ligne avec les attributs entrés en paramètre.
	 *
	 * @param x0 la coordonnée x du premier point de la ligne
	 * @param y0 la coordonnée y du premier point de la ligne
	 * @param x1 la coordonnée x du deuxième point de la ligne
	 * @param y1 la coordonnée y du deuxième point de la ligne
	 * @param c la couleur de la ligne
	 * @return une ligne instanciée avec les attributs entrés en paramètre
	 */
	public Line createLine(double x0, double y0, double x1, double y1, Color c);
}
