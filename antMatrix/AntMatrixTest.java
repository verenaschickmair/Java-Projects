/*-------------------------------------------------------------
 * AntMatrixTest.java
 * Author: Verena Schickmair
 * Date: 14.04.2020
 * Task: Task 4
 *
 * This class creates a matrix with the height/width entered in the
 * new object matrix. Also the animation is called here.
 * -----------------------------------------------------------*/
import kwm.Drawing;
import kwm.ant.AntMatrix;

public class AntMatrixTest {
	public static void main(String[] args) {

		Drawing.begin("AntMatrix", 3000, 3000);
		AntMatrix matrix = new AntMatrix(10,15);
		matrix.startAnimation();
		Drawing.end();
	}
}
