package drawshop.shapes.visitors;

import drawshop.shapes.*;

/**
 * Cette interface est un modèle de Visitor permettant de réaliser des actions sur
 * des formes.
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
public interface ShapeVisitor {

	public void visit(HanddrawnCircle circle);
	public void visit(HanddrawnLine line);
	public void visit(HanddrawnRectangle rectangle);
	
	public void visit(PerfectCircle circle);
	public void visit(PerfectLine line);
	public void visit(PerfectRectangle rectangle);
	
}
