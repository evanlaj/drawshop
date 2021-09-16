package drawshop.client;

import drawshop.shapes.drawing.Drawing;
import drawshop.shapes.drawing.serialization.DrawingSerializationHandler;

import java.io.IOException;

/**
 * Cette classe permet d'enregistrer au format .draw l'image miroir
 * horizontale du dessin entré en paramètres.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */

public class Mirror {

    public static void main (String[] args) {

        if(args.length < 2) {
            System.out.println("usage : java Mirror [-V / -H (default)] original_file new_file ");
            System.exit(1);
        }

        String originalFileName = "";
        String newFileName = "";
        boolean verticalMirror = false;

        if(args.length == 2) {
            originalFileName = args[0];
            newFileName = args[1];
        }

        if(args.length > 2) {
            originalFileName = args[1];
            newFileName = args[2];

            verticalMirror = args[0].equals("-V");
        }

        try {
            Drawing drawing = DrawingSerializationHandler.loadDrawing(originalFileName);

            if(verticalMirror) drawing.mirrorX(drawing.getCenterX());
            else drawing.mirrorY(drawing.getCenterY());

            DrawingSerializationHandler.saveDrawing(drawing, newFileName);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
