package drawshop.shapes;

import drawshop.shapes.visitors.ShapeVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un groupement de plusieurs Shape
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */

public class ShapeGroup implements Shape {

	private List<Shape> shapes;

	/**
	 * Ce constructeur permet de créer un ShapeGroup avec une
	 * liste de shapes à lui intégrer
	 *
	 * @param shapes la liste des Shape à intégrer au nouveau ShapeGroup
	 */
	public ShapeGroup(List<Shape> shapes) {
		this.shapes = new ArrayList<Shape>(shapes);
	}

	/**
	 * Ce constructeur permet de créer un ShapeGroup vide
	 */
	public ShapeGroup() {
		this.shapes = new ArrayList<Shape>();
	}

	/**
	 * Permet d'ajouter une Shape à la liste de Shape du ShapeGroup
	 * @param s la Shape à ajouter
	 */
	public void addShape(Shape s) {
		this.shapes.add(s);
	}

	/**
	 * Permet de retirer une Shape à la liste de Shape du ShapeGroup
	 * @param s la Shape à retirer
	 */
	public void removeShape(Shape s) {
		this.shapes.remove(s);
	}

	/**
	 *
	 * @return la liste entière de Shape du ShapeGroup
	 */
	public List<Shape> getShapes() { return shapes; }

	public void acceptVisitor(ShapeVisitor sv) {
		for(Shape shape: shapes) shape.acceptVisitor(sv);
	}

	public void move(int dx, int dy) {
		for(Shape shape : shapes) shape.move(dx, dy);
	}

	public void mirrorX(double x) {
		for(Shape shape : shapes) shape.mirrorX(x);
	}

	public void mirrorY(double y) {
		for(Shape shape : shapes) shape.mirrorY(y);
	}

	public double getCenterX() {
		double centerSum = 0;
		for(Shape shape : shapes) centerSum += shape.getCenterX();

		return centerSum/shapes.size();

	}

	public double getCenterY() {
		double centerSum = 0;
		for(Shape shape : shapes) centerSum += shape.getCenterY();

		return centerSum/shapes.size();
	}

	public Shape toStandard() {

		List<Shape> newShapes = new ArrayList<Shape>();

		for(Shape shape : shapes) {
			newShapes.add(shape.toStandard());
		}

		return new ShapeGroup(newShapes);
	}
}