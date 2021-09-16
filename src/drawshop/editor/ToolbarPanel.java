package drawshop.editor;

import drawshop.shapes.EShape;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe permet d'instancier un JPanel constituant une partie
 * de l'éditeur de dessin. Ce JPanel permet de choisir la couleur des formes
 * à générer ainsi que le mode d'édition du dessin.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.shapes.EShape
 */
public class ToolbarPanel extends JPanel {

    private Color color;
    private JButton[] modeButtons;

    public ToolbarPanel() {

        this.color = new Color(0,0,0);

        this.setLayout(new BorderLayout());
        initColorPanel();
        initModePanel();
    }

    /* Initialise le bouton permettant de choisir la couleur utilisée par l'éditeur
     * lors de la création de nouvelle formes dans le dessin en cours d'édition.
     * Lorsque le bouton est cliqué, une fenêtre de selection de couleur s'affiche,
     * et la couleur n'est modifié que si une nouvelle couleur est choisie.
     */
    private void initColorPanel() {
        JPanel colorSelectorPanel = new JPanel();
        colorSelectorPanel.setBackground(new Color(255,255,255));
        colorSelectorPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180,180,180)), "Color", TitledBorder.CENTER, TitledBorder.TOP));

        JButton selectColorButton = new JButton();
        selectColorButton.setBackground(color);
        selectColorButton.setPreferredSize(new Dimension(40,40));

        selectColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(selectColorButton,"Choose",color);
                if(newColor != null) color = newColor;
                selectColorButton.setBackground(color);
                EditorFrame.getCurrentEditor().getDrawingEditor().setColor(color);
            }
        });

        colorSelectorPanel.add(selectColorButton);

        add(colorSelectorPanel, BorderLayout.NORTH);
    }

    /* Initialise la liste de boutons permettant de choisir la forme à ajouter
     * au dessin. Cette liste de forme est récuperée dans l'Enum EShape.
     */
    private void initModePanel() {
        JPanel shapeSelectorPanel = new JPanel();
        shapeSelectorPanel.setBackground(new Color(255,255,255));
        shapeSelectorPanel.setLayout(new BoxLayout(shapeSelectorPanel, BoxLayout.Y_AXIS));

        shapeSelectorPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180,180,180)), "Shapes", TitledBorder.CENTER, TitledBorder.TOP));

        EShape[] modes = EShape.values();
        modeButtons = new JButton[modes.length];

        shapeSelectorPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        for(int i = 0; i < modes.length; i++) {

            modeButtons[i] = new JButton();
            modeButtons[i].setPreferredSize(new Dimension(40,40));
            modeButtons[i].setBackground(new Color(235,235,235));

            modeButtons[i].setIcon(new ImageIcon(getClass().getResource("/resources/img/" + modes[i].name() + ".png")));

            EShape mode = modes[i];
            modeButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    EditorFrame.getCurrentEditor().getDrawingEditor().setMode(mode);
                }
            });

            shapeSelectorPanel.add(modeButtons[i]);
            shapeSelectorPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        add(shapeSelectorPanel, BorderLayout.CENTER);
    }

    /**
     * Retourne la couleur séléctionnée grâce au bouton disponible sur le JPanel
     * Cette couleur est la couleur utilisée par l'éditeur lors de la création
     * de nouvelle formes dans le dessin en cours d'édition.
     *
     * @return la couleur actuellement utilisée par l'éditeur.
     */
    public Color getCurrentColor() { return this.color; }

}
