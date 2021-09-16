package drawshop.shapes.factories;

import drawshop.shapes.HanddrawnCircle;
import drawshop.shapes.HanddrawnLine;
import drawshop.shapes.HanddrawnRectangle;

import java.awt.*;

/**
 * Cette classe est une Factory permettant d'instancier des formes ayant un aspect
 * fait à la main.
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
public class HanddrawnFactory implements ShapeFactory {

	private static final HanddrawnFactory factory = new HanddrawnFactory();
	
	public static HanddrawnFactory getFactory() { return factory; }
	
	private HanddrawnFactory() {}
	
	@Override
	public HanddrawnCircle createCircle(double cx, double cy, double rad, Color c) {
		return new HanddrawnCircle(cx, cy, rad, c);
	}

	@Override
	public HanddrawnRectangle createRectangle(double x0, double y0, double x1, double y1, Color c) {
		return new HanddrawnRectangle(x0, y0, x1, y1, c);
	}

	@Override
	public HanddrawnLine createLine(double x0, double y0, double x1, double y1, Color c) {
		return new HanddrawnLine(x0, y0, x1, y1, c);
	}

}
