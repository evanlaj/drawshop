package drawshop.editor;

import drawshop.shapes.drawing.Drawing;
import drawshop.shapes.drawing.serialization.DrawingSerializationHandler;
import drawshop.shapes.EShape;
import drawshop.shapes.Shape;
import drawshop.shapes.factories.DrawingType;
import drawshop.shapes.factories.HanddrawnFactory;
import drawshop.shapes.factories.PerfectFactory;
import drawshop.shapes.factories.ShapeFactory;
import drawshop.shapes.visitors.ShapeDrawingVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;

/**
 * Cette classe permet d'instancier un JPanel constituant la partie principale
 * de l'éditeur de dessin. Ce JPanel affiche le dessin en cours d'édition et
 * permet d'ajouter des formes à ce dessin. En plus de ça, plusieurs variables
 * représentant l'état de l'éditeur sont enregistrées dans cette classe.
 *
 * Ces variables incluent :
 * <ul>
 *     <li>Le dessin actuellement édité</li>
 *     <li>Le nom du fichier dans lequel le dessin doit être enregistré</li>
 *     <li>La mode d'édition actuel (autrement dit la forme à dessiner lors d'une
 *     interaction avec l'éditeur</li>
 *     <li>La dernière forme dessinée</li>
 *     <li>La couleur actuelle (autrement dit la couleur à utiliser lors de la
 *     génération d'une forme)</li>
 * </ul>
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.shapes.drawing.Drawing
 */
public class DrawingEditor extends JPanel {

    private Drawing drawing;
    private String fileName;

    private EShape mode;
    private Shape lastShape;

    private Color color;

    private int[] selectionCoords; // x0, y0, x1, y1

    /**
     * Ce constructeur sert à initialiser un DrawingEditor pour un nouveau
     * dessin qui n'a pas de fichier associé.
     *
     * @param drawing le dessin à éditer.
     *
     * @see drawshop.editor.EditorFrame#newDrawing(int, int, DrawingType)
     */
    public DrawingEditor(Drawing drawing) {
        this(drawing, null);
    }

    /**
     * Ce constructeur sert à initialiser un DrawingEditor pour un dessin qui
     * a déjà un fichier associé, par exemple lors du chargement d'un
     * dessin enregistré via l'éditeur.
     *
     * <p>
     * Ce constructeur initialise le mode d'édition sur Rectangle et définie la
     * taille du panel pour être la taille du dessin entré en paramètre.
     * </p>
     *
     * @param drawing le dessin à éditer.
     * @param fileName le nom du fichier dans lequel sauvegarder le dessin.
     *
     * @see #saveDrawingAs(String fileName)
     */
    public DrawingEditor(Drawing drawing, String fileName) {

        this.drawing = drawing;
        this.fileName = fileName;
        this.mode = EShape.RECTANGLE;
        selectionCoords = new int[4];

        setPreferredSize(new Dimension(drawing.getWidth(), drawing.getHeight()));
        setMinimumSize(new Dimension(drawing.getWidth(), drawing.getHeight()));

        initSelection();

        setBackground(new Color(255,255,255));
    }

    /* Ajoute la gestion d'évenement de la souris.
     * Sert uniquement de récuperer la zone de séléction de l'utilisateur
     * afin de générer des formes de la bonne taille.
     */
    private void initSelection() {

        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                selectionCoords[0] = e.getX();
                selectionCoords[1] = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                selectionCoords[2] = e.getX();
                selectionCoords[3] = e.getY();

                drawShape();
            }
        });

    }

    /**
     * Méthode héritée de JPanel.
     * S'assure de dessiner les formes du dessin en cours d'édition sur le
     * panel de l'éditeur.
     *
     * @param g
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;
        drawing.acceptVisitor(new ShapeDrawingVisitor(graphics));
        graphics.dispose();
    }

    /**
     *  Cette méthode permet d'appeler la méthode de sauvegarde de dessin 
     *  présente dans la classe DrawingSerializationHandler avec les bon paramètres.
     *  Elle échouera si le dessin est null ou si le nom du fichier est 
     *  null ou vide.
     *
     *  <p>En plus de cela, le nom du fichier sera sauvegardé afin de pouvoir sauvegarder
     *  le dessin sans avoir à redemander à l'utilisateur le nom du fichier.</p>
     *
     * @param fileName le chemin du fichier dans lequel enregistrer le dessin en cours d'édition.
     *                 
     * @see DrawingSerializationHandler#saveDrawing(Drawing drawing, String fileName)
     */
    public void saveDrawingAs(String fileName) {
        if (this.drawing == null) return;
        if (fileName == null || fileName.equals("")) return;

        try {
            DrawingSerializationHandler.saveDrawing(this.drawing, fileName);
            this.fileName = fileName;
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     *
     * @return le chemin du fichier dans lequel enregistrer le dessin en cours d'édition
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     *
     * @return le dessin en cours d'édition
     */
    public Drawing getDrawing() { return drawing; }

    /**
     *  Cette méthode charge un nouveau dessin grâce à la méthode de déserialisation
     *  présente dans la classe DrawingSerializationHandler. Le dessin ainsi chargé
     *  remplace l'ancien dessin de l'éditeur et l'éditeur prend la taille du
     *  nouveau dessin.
     *
     * @param fileName le nom du fichier depuis lequel charger un nouveau dessin.
     */
    public void loadDrawing(String fileName) {

        try {
            this.drawing = DrawingSerializationHandler.loadDrawing(fileName);

            List<Shape> shapes = this.drawing.getShapes();

            for(Shape s : shapes)
                EditorFrame.getCurrentEditor().getShapeList().addShape(s);

            setPreferredSize(new Dimension(drawing.getWidth(), drawing.getHeight()));
            setMinimumSize(new Dimension(drawing.getWidth(), drawing.getHeight()));
            revalidate();
            repaint();

            this.fileName = fileName;

        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
    }


    /**
     * Remplace le mode d'édition actuel du dessin avec le nouveau mode choisi.
     * Actuellement les modes d'éditions consistent uniquement en des formes à
     * ajouter au dessin.
     *
     * @param mode le nouveau mode d'édition du dessin
     */
    public void setMode(EShape mode){
        this.mode = mode;
    }

    /**
     * Ajoute une forme au dessin en prenant en compte le type de dessin (Handdrawn ou Perfect),
     * ainsi que la forme à ajouter, puis met à jour la fenêtre afin d'afficher la nouvelle forme.
     *
     * <p>En plus d'ajouter la forme à la liste des formes du dessin, elle est
     * aussi ajouté à la liste présente sur la droite de la fenêtre, et dans laquelle
     * différente actions peuvent être effectuée sur les formes.</p>
     *
     * @see drawshop.editor.ShapeListPanel
     */
    public void drawShape() {

        ShapeFactory factory;

        switch (drawing.getType()) {

            case HANDRAWN:
                factory = HanddrawnFactory.getFactory();
                break;
            case PERFECT:
                factory = PerfectFactory.getFactory();
                break;
            default:
                factory = null;
                return;
        }

        switch (mode) {

            case RECTANGLE:
                sortCoords();
                lastShape = factory.createRectangle(selectionCoords[0], selectionCoords[1], selectionCoords[2], selectionCoords[3], color);
                drawing.addShape(lastShape);

                EditorFrame.getCurrentEditor().getShapeList().addShape(lastShape);

                break;
            case CIRCLE:
                sortCoords();
                int[] center = new int[2];
                center[0] = (selectionCoords[2] - selectionCoords[0])/2 + selectionCoords[0];
                center[1] = (selectionCoords[3] - selectionCoords[1])/2 + selectionCoords[1];
                int radius = (selectionCoords[3] - selectionCoords[1])/2;
                lastShape = factory.createCircle(center[0], center[1], radius, color);
                drawing.addShape(lastShape);

                EditorFrame.getCurrentEditor().getShapeList().addShape(lastShape);

                break;
            case LINE:
                lastShape = factory.createLine(selectionCoords[0], selectionCoords[1], selectionCoords[2], selectionCoords[3], color);
                drawing.addShape(lastShape);

                EditorFrame.getCurrentEditor().getShapeList().addShape(lastShape);

                break;
            default:
                return;
        }

        revalidate();
        repaint();

    }

    /**
     * Retire la forme entrée en paramètre au dessin et rafraichit la fenêtre
     * pour afficher le changement.
     *
     * @param s la forme à retirer du dessin
     */
    public void removeShape(Shape s) {
        drawing.removeShape(s);
        revalidate();
        repaint();
    }

    /**
     * Ajoute la forme entrée en paramètre au dessin et rafraichit la fenêtre
     * pour afficher le changement.
     *
     * @param s la forme à ajouter au dessin
     */
    public void addShape(Shape s) {
        drawing.addShape(s);
        revalidate();
        repaint();
    }

    /* S'assure que les coordonnées récupérées lors des interactions de
     * l'utilisateur sont dans l'ordre { x0, y0, x1, y1 } avec x0 >= x1,
     * y0 > y1.
     */
    private void sortCoords() {
        int x0 = selectionCoords[0];
        int y0 = selectionCoords[1];
        int x1 = selectionCoords[2];
        int y1 = selectionCoords[3];

        selectionCoords[0] = Math.min(x0, x1);
        selectionCoords[1] = Math.min(y0, y1);

        selectionCoords[2] = Math.max(x0, x1);
        selectionCoords[3] = Math.max(y0, y1);
    }

    /**
     * Remplace la couleur actuelle avec la couleur
     * entrée en paramètre.
     *
     * @param color la nouvelle couleur
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
