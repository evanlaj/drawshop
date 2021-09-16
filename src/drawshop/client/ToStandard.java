package drawshop.client;

import drawshop.shapes.drawing.Drawing;
import drawshop.shapes.drawing.serialization.DrawingSerializationHandler;

import java.io.IOException;

/**
 * Cette classe permet d'enregistrer au format .draw le dessin entré
 * en paramètre en type Perfect, qu'importe son type.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.shapes.drawing.Drawing
 */

public class ToStandard {

    public static void main (String[] args) {

        if(args.length < 2) {
            System.out.println("Usage : original_file_name new_file_name");
            System.exit(0);
        }

        try {

            // Load Drawing
            Drawing drawing = DrawingSerializationHandler.loadDrawing(args[0]);

            // Redraw Drawing
            Drawing newDrawing = (Drawing) drawing.toStandard();

            // Save Drawing
            DrawingSerializationHandler.saveDrawing(newDrawing, args[1]);

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}