package drawshop.noise;

import java.util.Random;

/**
 * Cette classe permet de retourner des valeurs aléatoires.
 *
 * @author Evan Lajusticia
 * @author Pauline Rugliano
 *
 * @version 1.0
 */

public class Noise {

	private static Random r=new Random();

	/**
	 *
	 * @return une valeur aléatoire comprise entre -5 et 5.
	 */
	public static double getNoise() {
		return 5-10*r.nextDouble();
	}
}

