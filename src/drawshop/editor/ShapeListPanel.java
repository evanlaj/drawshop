package drawshop.editor;

import drawshop.shapes.Shape;
import drawshop.shapes.ShapeGroup;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.HashMap;

/**
 * Cette classe permet d'instancier un JPanel constituant une partie
 * de l'éditeur de dessin. Ce JPanel affiche la liste des formes
 * constituant le dessin et permet de réaliser divers actions sur
 * celles-ci.
 *
 * Ces actions incluent :
 * <ul>
 *     <li>Fusionner plusieurs formes en un seul ShapeGroup</li>
 *     <li>Degrouper le contenu d'un ShapeGroup</li>
 *     <li>Réaliser le miroir d'une ou plusieurs formes</li>
 *     <li>Supprimer une forme</li>
 *     <li>Déplacer une forme</li>
 * </ul>
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class ShapeListPanel extends JPanel {

    private DefaultListModel<String> shapeList;
    private JList<String> list;

    private HashMap<String, Shape> shapeMap;
    private int nbShapes;

    private JButton mergeShapeButton;
    private JButton unmergeShapeButton;
    private JButton mirrorXShapeButton;
    private JButton mirrorYShapeButton;
    private JButton removeShapeButton;

    private JTextField dx;
    private JTextField dy;
    private JButton submitMoveButton;

    /**
     * Ce constructeur initialise une ShapeListPanel dans lequel on retrouve
     * une liste des différentes formes présentes dans le dessin en cours
     * d'édition, ainsi que des boutons permettant de réaliser différentes
     * actions sur ces formes.
     */
    public ShapeListPanel() {

        this.shapeMap = new HashMap<String, Shape>();
        this.nbShapes = 0;

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(255,255,255));

        initListPanel();
        initActionPanel();
        initMovePanel();
        initActionListeners();
    }

    private void initListPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(new Color(255,255,255));
        listPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180,180,180)), "Shape list", TitledBorder.CENTER, TitledBorder.TOP));

        this.shapeList = new DefaultListModel<String>();
        this.list = new JList<String>(shapeList);
        this.list.setBackground(new Color(235,235,235));

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(100, 140));
        listScroller.setBorder(BorderFactory.createLineBorder(new Color(180,180,180), 1));

        listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        listPanel.add(listScroller);

        add(listPanel, BorderLayout.NORTH);
    }

    private void initActionPanel() {

        JPanel actionPanel = new JPanel();

        actionPanel.setBackground(new Color(255,255,255));
        actionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180,180,180)), "Actions", TitledBorder.CENTER, TitledBorder.TOP));
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        mergeShapeButton = new JButton("Merge shapes");
        mergeShapeButton.setBackground(new Color(235,235,235));
        mergeShapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        unmergeShapeButton = new JButton("Unmerge shapes");
        unmergeShapeButton.setBackground(new Color(235,235,235));
        unmergeShapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mirrorXShapeButton = new JButton("Mirror vertically");
        mirrorXShapeButton.setBackground(new Color(235,235,235));
        mirrorXShapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mirrorYShapeButton = new JButton("Mirror horizontally");
        mirrorYShapeButton.setBackground(new Color(235,235,235));
        mirrorYShapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeShapeButton = new JButton("Remove shape");
        removeShapeButton.setBackground(new Color(235,235,235));
        removeShapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        actionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        actionPanel.add(mirrorXShapeButton);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        actionPanel.add(mirrorYShapeButton);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        actionPanel.add(removeShapeButton);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        actionPanel.add(mergeShapeButton);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        actionPanel.add(unmergeShapeButton);

        add(actionPanel, BorderLayout.CENTER);

    }

    private void initMovePanel() {
        JPanel movePanel = new JPanel(new BorderLayout());
        JPanel coordinatesPanel = new JPanel();
        JPanel buttonMovePanel = new JPanel();

        coordinatesPanel.setBackground(new Color(255,255,255));
        coordinatesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180,180,180)), "Move shape", TitledBorder.CENTER, TitledBorder.TOP));

        buttonMovePanel.setBackground(new Color(255,255,255));

        dx = new JTextField();
        dx.setPreferredSize(new Dimension(40,18));
        dy = new JTextField();
        dy.setPreferredSize(new Dimension(40,18));
        submitMoveButton = new JButton("Move");

        coordinatesPanel.add(new JLabel("dx : "));
        coordinatesPanel.add(dx);
        coordinatesPanel.add(new JLabel("dy : "));
        coordinatesPanel.add(dy);
        buttonMovePanel.add(submitMoveButton);
        movePanel.add(coordinatesPanel, BorderLayout.CENTER);
        movePanel.add(buttonMovePanel, BorderLayout.SOUTH);

        add(movePanel, BorderLayout.SOUTH);
    }

    private void initActionListeners() {
        mergeShapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<String> names = list.getSelectedValuesList();
                if(names.size() < 2) return;

                DrawingEditor editor = EditorFrame.getCurrentEditor().getDrawingEditor();

                ShapeGroup sg = new ShapeGroup();
                for (String name : names) {
                    sg.addShape(shapeMap.get(name));
                    editor.removeShape(shapeMap.get(name));
                    removeShape(name);
                }
                editor.addShape(sg);
                addShape(sg);
            }
        });

        unmergeShapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<String> names = list.getSelectedValuesList();
                if(names.size() < 1) return;

                DrawingEditor editor = EditorFrame.getCurrentEditor().getDrawingEditor();

                for (String name : names) {
                    if(shapeMap.get(name) instanceof ShapeGroup) {
                        for(Shape shape : ((ShapeGroup) shapeMap.get(name)).getShapes()) {
                            editor.addShape(shape);
                            addShape(shape);
                        }
                        editor.removeShape(shapeMap.get(name));
                        removeShape(name);
                    }
                }
            }
        });

        removeShapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<String> names = list.getSelectedValuesList();
                if(names.size() < 1) return;

                DrawingEditor editor = EditorFrame.getCurrentEditor().getDrawingEditor();

                for (String name : names) {
                    editor.removeShape(shapeMap.get(name));
                    removeShape(name);
                }
            }
        });

        mirrorXShapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<String> names = list.getSelectedValuesList();
                if(names.size() < 1) return;

                DrawingEditor editor = EditorFrame.getCurrentEditor().getDrawingEditor();

                for (String name : names) shapeMap.get(name).mirrorX(shapeMap.get(name).getCenterX());

                editor.revalidate();
                editor.repaint();

            }
        });

        mirrorYShapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<String> names = list.getSelectedValuesList();
                if(names.size() < 1) return;

                DrawingEditor editor = EditorFrame.getCurrentEditor().getDrawingEditor();

                for (String name : names) shapeMap.get(name).mirrorY(shapeMap.get(name).getCenterY());

                editor.revalidate();
                editor.repaint();

            }
        });

        submitMoveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<String> names = list.getSelectedValuesList();
                if(names.size() < 1) return;

                DrawingEditor editor = EditorFrame.getCurrentEditor().getDrawingEditor();

                int x = Integer.parseInt(dx.getText());
                int y = Integer.parseInt(dy.getText());

                for (String name : names) shapeMap.get(name).move(x, y);
                editor.revalidate();
                editor.repaint();
            }
        });
    }

    /**
     * Ajoute une forme à la liste de forme affichée dans le JPanel.
     *
     * @param s la forme à ajouter à la liste
     */
    public void addShape(Shape s) {

        String shapeName = "Shape " + (++nbShapes);

        shapeMap.put(shapeName, s);
        shapeList.add(0, shapeName);

        this.revalidate();
        this.repaint();
    }

    /**
     * Renvoie la forme associée au nom entré en paramètre. Le nom de la forme
     * peut être récupéré dans la liste affichée dans le JPanel.
     *
     * @param shapeName le nom de la forme à récupérer, présent dans la liste affichée.
     * @return la forme associée au nom entré en paramètre.
     */
    public Shape getShape(String shapeName) {
        return shapeMap.get(shapeName);
    }

    /**
     * Supprime de la liste la forme associée au nom entré en paramètre. Le nom
     * de la forme peut être récupéré dans la liste affichée dans le JPanel.
     *
     * @param shapeName le nom de la forme à supprimer, présent dans la liste affichée.
     */
    public void removeShape(String shapeName) {

        shapeList.removeElement(shapeName);
        shapeMap.remove(shapeName);

        this.revalidate();
        this.repaint();

    }

    /**
     * Supprime tous les élements de la liste affichée dans le JPanel.
     */
    public void removeAll() {

        this.shapeMap = new HashMap<String, Shape>();
        this.nbShapes = 0;
        this.shapeList.removeAllElements();

        this.revalidate();
        this.repaint();
    }
}
