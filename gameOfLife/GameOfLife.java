/*-------------------------------------------------------------
 * GameOfLife.java
 * Author: Verena Schickmair
 * Date: 21.04.2020
 * Task: Task 5
 *
 * This class creates a new matrix with the values height, width and
 * the probability and also draws it in the console. 
 * Also the animation is called here.
 * -----------------------------------------------------------*/
import kwm.Drawing;
import kwm.graphics.CellMatrix;;

public class GameOfLife {
	public static void main(String[] args) {

		Drawing.begin("GameOfLife", 1600, 800);
		CellMatrix matrix = new CellMatrix(20,200,50);
		matrix.startAnimation();
		Drawing.end();
	}
}
