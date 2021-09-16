package drawshop.shapes;

/**
 * Cette classe permet de calculer l'aire d'une Shape dessinée parfaitement
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.shapes.factories.DrawingType
 */
public interface PerfectShape extends Shape {

    /**
     *
     * @return l'aire de la Shape parfaite
     */
    public double getArea();
}