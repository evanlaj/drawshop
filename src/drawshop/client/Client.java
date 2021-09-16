package drawshop.client;

import drawshop.editor.NewDrawingFrame;

/**
 * Cette classe est uniquement constituée de la méthode main permettant de
 * lancer l'application Drawshop. Au lancement de l'application, une nouvelle
 * fenêtre permettant d'éditer un nouveau dessin est affichée.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see  drawshop.editor.NewDrawingFrame
 */

public class Client {

    public static void main (String[] args) {
        new NewDrawingFrame();
    }
}
