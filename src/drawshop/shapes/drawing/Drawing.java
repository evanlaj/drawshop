package drawshop.shapes.drawing;

import drawshop.shapes.Shape;
import drawshop.shapes.ShapeGroup;
import drawshop.shapes.factories.DrawingType;
import drawshop.shapes.visitors.ShapeDrawingVisitor;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de représenter un dessin composé de plusieurs formes
 * d'une taille unique et d'un type unique.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.editor.NewDrawingFrame
 * @see drawshop.shapes.factories.DrawingType
 */
public class Drawing extends ShapeGroup {

    private final DrawingType type;
    private final int width;
    private final int height;

    /**
     * Ce constructeur sert à initialiser un nouveau dessin avec une
     * liste de Shape vide.
     *
     * @param width largeur du dessin
     * @param height hauteur du dessin
     * @param type type du dessin
     *
     * @see drawshop.shapes.factories.DrawingType
     */
    public Drawing(int width, int height, DrawingType type) {
        this(width, height, type, new ArrayList<Shape>());
    }

    /**
     * Ce constructeur sert à initialiser un nouveau dessin avec une
     * liste de Shape contenant des formes déjà créées.
     *
     * @param width largeur du dessin
     * @param height hauteur du dessin
     * @param type type du dessin
     * @param shapes liste des formes du dessin
     *
     * @see drawshop.shapes.factories.DrawingType
     */
    public Drawing(int width, int height, DrawingType type, List<Shape> shapes) {
        super(shapes);

        this.width = width;
        this.height = height;
        this.type = type;
    }

    /**
     *
     * @return la largeur du dessin
     */
    public int getWidth() { return this.width;  }

    /**
     *
     * @return la hauteur du dessin
     */
    public int getHeight() { return this.height; }

    /**
     *
     * @return le type du dessin
     * @see drawshop.shapes.factories.DrawingType
     */
    public DrawingType getType() { return this.type;   }

    /**
     *
     * @return le milieu de l'axe X (la largeur) du dessin
     */
    public double getCenterX() {
        return width/2.0;
    }

    /**
     *
     * @return le milieu de l'axe Y (la hauteur) du dessin
     */
    public double getCenterY() {
        return height/2.0;
    }

    /**
     *
     * @return le dessin actuel mais de type Perfect
     * avec toutes les formes redessinées parfaitement.
     */
    public Shape toStandard() {

        List<Shape> newShapes = new ArrayList<Shape>();

        for(Shape shape : this.getShapes()) {
            newShapes.add(shape.toStandard());
        }

        return new Drawing(width, height, DrawingType.PERFECT, newShapes);
    }
}
