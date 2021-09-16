package drawshop.editor;

import drawshop.shapes.factories.DrawingType;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe permet d'instancier une JFrame permettant de décrire un les
 * caractéristiques d'un dessin afin de l'initialiser puis de l'ajouter à
 * l'éditeur de dessin.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */
public class NewDrawingFrame extends JFrame {
    static final int WIDTH  = 260;

    private JRadioButton[] typeButtons;

    private JTextField width;
    private JTextField height;

    private JButton submitButton;

    /**
     * Instancie une NewDrawingFrame et l'affiche au centre de l'écran.
     * Cette JFrame est composée d'une liste des différents types de dessin
     * disponibles, de zone de texte permettant de définir la taille et la
     * largeur du dessin et un bouton submit permettant d'instancier un nouveau
     * dessin et de l'ajouter à l'éditeur.
     *
     * @see drawshop.shapes.factories.DrawingType
     */
    public NewDrawingFrame() {
        setTitle("Drawshop - New File");

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setLayout(new BorderLayout());

        JPanel centralPanel = new JPanel();

        centralPanel.add(initPanelType());
        centralPanel.add(initPanelSize());
        centralPanel.add(initPanelSubmit());

        this.add(centralPanel, BorderLayout.CENTER);

        this.setSize(WIDTH, 184+26*typeButtons.length);
        this.setVisible(true);

    }

    //voir NewDrawingFrame()
    private JPanel initPanelType() {

        JPanel panelType = new JPanel(new GridLayout(0,1));
        panelType.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180,180,180), 1),"Drawing type", TitledBorder.CENTER, TitledBorder.TOP));

        DrawingType[] types = DrawingType.values();

        ButtonGroup bg = new ButtonGroup();
        typeButtons = new JRadioButton[types.length];

        for(int i = 0; i < types.length; i++) {
            typeButtons[i] = new JRadioButton(types[i].getName());
            if(i == 0) typeButtons[i].setSelected(true);
            bg.add(typeButtons[i]);
            JPanel p = new JPanel();
            p.add(typeButtons[i]);
            p.setPreferredSize(new Dimension(190, 26));
            panelType.add(p);
        }

        return panelType;
    }

    //voir NewDrawingFrame()
    private JPanel initPanelSize() {

        JPanel panelSize = new JPanel(new GridLayout(0,1));
        panelSize.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180,180,180), 1),"Drawing dimensions", TitledBorder.CENTER, TitledBorder.TOP));
        panelSize.setPreferredSize(new Dimension(200, 75));

        width = new JTextField("300");
        height = new JTextField("300");
        width.setPreferredSize(new Dimension(60,18));
        height.setPreferredSize(new Dimension(60,18));

        JPanel panelWidth = new JPanel();
        panelWidth.add(new JLabel("Width :"));
        panelWidth.add(width);

        JPanel panelHeight = new JPanel();
        panelHeight.add(new JLabel("Height :"));
        panelHeight.add(height);

        panelSize.add(panelWidth);
        panelSize.add(panelHeight);

        return panelSize;
    }

    //voir NewDrawingFrame()
    private JPanel initPanelSubmit() {

        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("OK");
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditorFrame editor = EditorFrame.getCurrentEditor();

                DrawingType type = DrawingType.PERFECT;

                for(int i = 0; i < typeButtons.length; i++) {
                    if(typeButtons[i].isSelected())
                        type = DrawingType.values()[i];
                }
                editor.newDrawing(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()), type);
                dispose();
            }
        });
        buttonPanel.add(submitButton);

        return buttonPanel;
    }
}
