package drawshop.editor;

import drawshop.shapes.drawing.Drawing;
import drawshop.shapes.factories.DrawingType;
import drawshop.shapes.visitors.ShapeDrawingVisitor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe permet d'instancier une JFrame constituée de toutes les parties
 * de l'éditeur de dessin. Ces différentes parties peuvent communiquer grâce
 * aux différentes méthodes présentes dans la classe.
 *
 * <p>Cette classe suit le patron Singleton et ne peux donc pas être instanciée
 * depuis l'extérieur. pour récupérer l'instance actuelle de la classe, il faut
 * utiliser la méthode getCurrentEditor().</p>
 *
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see #getCurrentEditor()
 */
public final class EditorFrame extends JFrame {

    private static final int WIDTH  = 860;
    private static final int HEIGHT = 520;

    private static EditorFrame currentEditor;

    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenu exitMenu;

    private JPanel viewer;
    private DrawingEditor drawing;
    private ToolbarPanel toolbar;
    private ShapeListPanel shapeListPanel;

    /**
     * Puisque cette classe est finale il n'est pas nécessaire de se soucier
     * d'une arborescence de classes filles. Cette méthode permet donc à la fois
     * de récuperer l'instance actuelle de l'éditeur et d'instancier l'éditeur
     * si aucune instance n'a déjà été générée.
     *
     * <p>Lorsque la class est instanciée, la JFrame ainsi que tous les composants
     * de l'éditeur sont instanciés. La taille de la fenêtre est définie dans les
     * constantes WIDTH et HEIGHT de la classe ({@value WIDTH}, {@value HEIGHT} et
     * celle-ci est affichée au centre de l'écran.</p>
     *
     * @return l'instance actuelle de l'éditeur, générée si besoin durant l'exécution.
     */
    public static EditorFrame getCurrentEditor() {
        if(currentEditor != null) return currentEditor;

        currentEditor = new EditorFrame();

        return currentEditor;
    }

    // voir getCurrentEditor pour les détails sur l'instanciation de la classe.
    private EditorFrame() {

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/resources/img/icon.png")).getImage());

        initMenuBar();

        toolbar = new ToolbarPanel();
        add(toolbar,BorderLayout.WEST);

        shapeListPanel = new ShapeListPanel();
        add(shapeListPanel,BorderLayout.EAST);
        

        viewer = new JPanel();

        viewer.setBackground(new Color(235,235,235));
        viewer.setLayout(new GridBagLayout());

        add(viewer, BorderLayout.CENTER);

        setVisible(true);
    }

    /* Cette méthode initialise la barre de menu présente toute en haut de la
     * fenêtre de l'éditeur.
     */
    private void initMenuBar() {

        //File Menu

        fileMenu = new JMenu("File");

        //voir drawshop.editor.NewDrawingFrame
        JMenuItem newFile = new JMenuItem("New file");
        newFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { new NewDrawingFrame(); }
        });
        fileMenu.add(newFile);

        //voir openDrawing()
        JMenuItem openFile = new JMenuItem("Open file");
        openFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { openDrawing(); }
        });
        fileMenu.add(openFile);

        fileMenu.addSeparator();

        //voir saveDrawing() et DrawingEditor.saveDrawingAs()
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if (drawing.getFileName() != null)
                    drawing.saveDrawingAs(drawing.getFileName());
                else saveDrawing();
            }
        });
        fileMenu.add(save);

        //voir saveDrawing()
        JMenuItem saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  saveDrawing(); }
        });
        fileMenu.add(saveAs);

        fileMenu.addSeparator();

        //voir exportDrawing()
        JMenuItem export = new JMenuItem("Export");
        export.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  exportDrawing(); }
        });
        fileMenu.add(export);

        //Exit Menu

        exitMenu = new JMenu("Exit");
        JMenuItem exit = new JMenuItem("Exit");

        //quitte l'application
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitMenu.add(exit);


        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255,255,255));
        menuBar.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        menuBar.add(fileMenu);
        menuBar.add(exitMenu);

        this.add(menuBar, BorderLayout.NORTH);
    }

    /* Ouvre un explorateur de fichier afin de choisir un fichier dans lequel
     * sauvegarder le dessin actuellement édité. Le nom du fichier est ensuite
     * passé dans la méthode saveDrawing() de DrawingEditor.
     */
    private void saveDrawing() {

        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(".draw files (*.draw)", "draw");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);

        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        if (chooser.showOpenDialog(currentEditor) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();

            String fileName = f.getAbsolutePath();

            if(!f.exists() && !fileName.endsWith(".draw")) fileName += ".draw";
            drawing.saveDrawingAs(fileName);

            setTitle("Drawshop - " + drawing.getFileName());
        }
    }

    /* Ouvre un explorateur de fichier afin de choisir un dessin à charger.
     * Le nom du fichier est ensuite passé dans la méthode loadDrawing()
     * de DrawingEditor.
     */
    private void openDrawing() {

        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(".draw files (*.draw)", "draw");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);

        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        if (chooser.showOpenDialog(currentEditor) == JFileChooser.APPROVE_OPTION) {

            File f = chooser.getSelectedFile();

            shapeListPanel.removeAll();
            drawing.loadDrawing(f.getAbsolutePath());
            setTitle("Drawshop - " + drawing.getFileName());
        }
    }

    /**
     * Instancie un nouveau dessin avec les valeur entrée en paramètre, puis
     * affiche ce dessin sur l'éditeur.
     *
     * @param width la largeur du nouveau dessin
     * @param height la hauteur du nouveau dessin
     * @param type le type de forme à ajouter au dessin.
     */
    public void newDrawing(int width, int height, DrawingType type) {
        if(drawing != null) viewer.remove(drawing);

        Drawing d = new Drawing(width, height, type);
        drawing = new DrawingEditor(d);

        shapeListPanel.removeAll();

        drawing.setColor(toolbar.getCurrentColor());
        viewer.add(drawing, new GridBagConstraints());
        revalidate();
        repaint();

        setTitle("Drawshop - Unsaved file");
    }

    /* Ouvre un explorateur de fichier afin de choisir un fichier dans lequel
     * exporter le dessin actuellement édité.
     *
     * Le dessin sera exporter au format .png
     */
    private void exportDrawing() {

        Drawing d = drawing.getDrawing();

        BufferedImage bImg = new BufferedImage(d.getWidth(), d.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,d.getWidth(),d.getHeight());
        d.acceptVisitor(new ShapeDrawingVisitor(g));

        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(".png files (*.png)", "png");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);

        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        File f = null;
        String fileName;

        if (chooser.showOpenDialog(currentEditor) == JFileChooser.APPROVE_OPTION) f = chooser.getSelectedFile();
        if(f == null) return;

        fileName = f.getAbsolutePath();

        if(!f.exists() && !fileName.endsWith(".png")) fileName += ".png";

        try { ImageIO.write(bImg, "png", new File(fileName));
        } catch (IOException e) { e.printStackTrace(); }
    }

    DrawingEditor getDrawingEditor() { return this.drawing; }
    ShapeListPanel getShapeList() { return this.shapeListPanel; }
}
