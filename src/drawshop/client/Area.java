package drawshop.client;

import drawshop.shapes.drawing.Drawing;
import drawshop.shapes.drawing.serialization.DrawingSerializationHandler;
import drawshop.shapes.PerfectShape;
import drawshop.shapes.Shape;
import drawshop.shapes.factories.DrawingType;

import java.io.IOException;

/**
 * Cette classe permet de calculer l'aire totale des formes d'un dessin.
 * Cependant, elle ne gère pas la superposition des formes.
 * Elle ne peut être appelée qu'avec un invité de commande.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 *
 * @see drawshop.shapes.drawing.Drawing
 */

public class Area {

    public static void main(String[] args) {

        if(args.length < 1) {
            System.out.println("usage : java Area drawing_file");
            System.exit(1);
        }

        try {
            Drawing drawing = DrawingSerializationHandler.loadDrawing(args[0]);

            if(!drawing.getType().equals(DrawingType.PERFECT)) {
                System.out.println("please use a perfect drawing.");
                System.exit(1);
            }

            double area = 0;

            for(Shape shape : drawing.getShapes()) {
                area += ((PerfectShape) shape).getArea();
            }

            System.out.println("Total area : " + area);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
