package drawshop.shapes.drawing.serialization;

import drawshop.shapes.drawing.Drawing;

import java.io.*;

/**
 * Cette classe permet d'enregistrer et de charger des dessins
 * depuis des fichiers au format .draw.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.shapes.drawing.Drawing
 */
public final class DrawingSerializationHandler {

    private DrawingSerializationHandler() {}

    /**
     * Cette méthode permet de charger un dessin.
     *
     * @param fileName le nom du fichier .draw qui va être chargé
     * @return le dessin qui vient d'être chargé
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Drawing loadDrawing(String fileName) throws IOException, ClassNotFoundException {

        ObjectInputStream ois = null;

        FileInputStream file = new FileInputStream(fileName);
        ois = new ObjectInputStream(file);
        Drawing d = (Drawing) ois.readObject();
        ois.close();
        return d;
    }

    /**
     * Cette méthode permet de sauvegarder un dessin.
     *
     * @param drawing le dessin à sauvegarder
     * @param fileName le nom que l'on veut donner au fichier une fois sauvegardé
     * @throws IOException
     */
    public static void saveDrawing(Drawing drawing, String fileName) throws IOException {

        ObjectOutputStream oos = null;

        FileOutputStream file = new FileOutputStream(fileName);
        oos = new ObjectOutputStream(file);
        oos.writeObject(drawing);
        oos.flush();
        oos.close();
    }

}
