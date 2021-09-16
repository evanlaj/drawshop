package drawshop.shapes.factories;

import drawshop.shapes.PerfectCircle;
import drawshop.shapes.PerfectLine;
import drawshop.shapes.PerfectRectangle;

import java.awt.*;

/**
 * Cette classe est une Factory permettant d'instancier des formes ayant un aspect
 * parfait.
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
public class PerfectFactory implements ShapeFactory {

	private static final PerfectFactory factory = new PerfectFactory();
	
	public static PerfectFactory getFactory() { return factory; }
	
	private PerfectFactory() {}
	
	@Override
	public PerfectCircle createCircle(double cx, double cy, double rad, Color c) {
		return new PerfectCircle(cx, cy, rad, c);
	}

	@Override
	public PerfectRectangle createRectangle(double x0, double y0, double x1, double y1, Color c) {
		return new PerfectRectangle(x0, y0, x1, y1, c);
	}

	@Override
	public PerfectLine createLine(double x0, double y0, double x1, double y1, Color c) {
		return new PerfectLine(x0, y0, x1, y1, c);
	}

}
