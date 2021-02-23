/*-------------------------------------------------------------
 * AntMatrix.java
 * Author: Verena Schickmair
 * Date: 14.04.2020
 * Task: Task 4
 *
 * This class is creating a matrix with a fixed height and width.
 * It also draws an ant (represented by a triangle). The boxes of 
 * the matrix are either white or gray, initially they are all white.
 * The ant starts at a random position in the matrix and points in
 * a random direction. If the box the ant is standing on is white,
 * the ant turns 90 degree to the right, moves forward and colors 
 * the previous box grey. If it's gray, it turns 90 degrees to the left, 
 * moves forward and colors the previous box white. If the ant leaves 
 * the matrix on one side, it appears on the opposite side.
 * 
 * - initMatrix(): Prints the first matrix - all boxes are initially false/white
 * - drawBox(int width, int height): Draws a single box with the associated color
 * - initAnt(): Sets the initial values of the ant at the beginning
 * - newAntPos(): Sets the (new) position of the ant in the matrix
 * - drawAnt(int x, int y): Tests in which direction the triangle should point
 * 	and also draws the triangle
 * - nextSecond(): Is called as an event every second (timer)
 * - turnRight(): The ant turns 90 degrees to the right
 * - turnLeft(): The ant turns 90 degrees to the left
 * - moveForward(): The ant moves in the direction it is pointing
 * - updateMatrix(): Updates the old matrix with new white/gray fields
 * - draw(): Updates the old matrix and replaces the ant
 * - startAnimation(): Starts the animation with the timer
 * - actionPerformed(): Every time the fixed time of the timer is reached, 
	these events will be performed
 * -----------------------------------------------------------*/
package kwm.ant;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import kwm.Drawing;

public class AntMatrix implements ActionListener{

	//Color of the Matrix (false = white, true = gray)
	public boolean[][] matrix;

	//Position and direction of the ant
	int PositionX;
	int PositionY;
	int dir;

	//Direction values of the ant
	final int ANTUP = 0;
	final int ANTRIGHT = 1;
	final int ANTDOWN = 2;
	final int ANTLEFT = 3;

	//Size of the Matrix and Pixels
	public int width;
	public int height;
	public int size;

	//Event settings
	public Timer timer;
	public final static int DELAY = 1000;	

	//Constructor
	public AntMatrix(int width, int height) {
		this.matrix = new boolean[width][height];
		this.width = width;
		this.height = height;
		this.size = 50; //Size of the boxes
		initMatrix();
		initAnt();
	}

	//initMatrix()
	//Prints the first matrix - all boxes are initially false 
	//(so their color is white)
	public void initMatrix() {
		int width = matrix.length;
		for(int i=0; i<width; i++) {
			int height = matrix[i].length;
			for(int j=0; j<height; j++) {
				matrix[i][j] = false;
				drawBox(i,j);
			}	
		}
		Drawing.paint();
	}

	//drawBox(width,height)
	//Draws a single box with the associated color
	public void drawBox (int width, int height) {
		int x = width*size;
		int y = height*size;

		if (matrix[width][height] != true) {
			Drawing.graphics.setColor(Color.white); //white box
			Drawing.graphics.fillRect(x,y,size,size);
		}
		else {
			Drawing.graphics.setColor(Color.gray); //gray box
			Drawing.graphics.fillRect(x,y,size,size);
		}
		Drawing.graphics.setColor(Color.black); //black border
		Drawing.graphics.drawRect(x,y,size,size);
	}

	//initAnt()
	//Sets the initial values of the ant at the beginning
	public void initAnt() {
		//Random position of Ant
		double antposX = (Math.random() * width);
		double antposY = (Math.random() * height);

		//Random direction of Ant
		this.dir = (int)(Math.random() * 4);

		//Setting the position of the ant
		this.PositionX = (int)antposX;
		this.PositionY = (int)antposY;

		//Color -> white
		matrix[PositionX][PositionY] = false;
		newAntPos();
	}

	//newAntPos()
	//Sets the (new) position of the ant in the matrix
	public void newAntPos() {
		int x = PositionX*size;
		int y = PositionY*size;
		drawAnt(x,y);
	}

	//drawAnt(x,y)
	//Tests in which direction the triangle should point
	//- also draws the triangle
	public void drawAnt(int x, int y) {		
		Drawing.graphics.setColor(Color.green);

		if(dir == 0) { //ANTUP
			int[] xCoord = {x,x+size/2,x+size};
			int[] yCoord = {y+size, y, y+size};
			Drawing.graphics.fillPolygon(xCoord, yCoord, 3);
		}
		else if(dir == 1) { //ANTRIGHT
			int[] xCoord = {x,x,x+size};
			int[] yCoord = {y, y+size, y+size/2};
			Drawing.graphics.fillPolygon(xCoord, yCoord, 3);
		}
		else if(dir == 2) { //ANTDOWN
			int[] xCoord = {x,x+size/2,x+size};
			int[] yCoord = {y, y+size, y};
			Drawing.graphics.fillPolygon(xCoord, yCoord, 3);
		}
		else if(dir == 3) { //ANTLEFT
			int[] xCoord = {x,x+size,x+size};
			int[] yCoord = {y+size/2, y, y+size};
			Drawing.graphics.fillPolygon(xCoord, yCoord, 3);
		}
		Drawing.paint();
	}

	//nextSecond()
	//Is called as an event every second (timer)
	public void nextSecond() {
		boolean state = matrix[PositionX][PositionY];

		//Tests if the state of the current box, on which 
		//the ant is located, is true or false
		if(state != true) {
			turnRight();
			matrix[PositionX][PositionY] = true;
			moveForward();
		}
		else{
			turnLeft();
			matrix[PositionX][PositionY] = false;
			moveForward();
		}
	}

	//turnRight()
	//The ant turns 90 degrees to the right
	public void turnRight() {
		this.dir++;
		if(this.dir > ANTLEFT)
			this.dir = ANTUP;
	}

	//turnLeft()
	//The ant turns 90 degrees to the left
	public void turnLeft() {
		this.dir--;
		if(dir < ANTUP)
			this.dir = ANTLEFT;
	}

	//moveForward()
	//The ant moves in the direction it is pointing
	public void moveForward() {
		switch(this.dir) {
		case ANTUP:
			PositionY--;;
			break;
		case ANTRIGHT:
			PositionX++;
			break;
		case ANTDOWN:
			PositionY++;
			break;
		case ANTLEFT:
			PositionX--;
			break;
		}

		//If ant goes out on the right side 
		//-> comes back in on the left side
		if(PositionX > width-1) {
			PositionX = 0;
		}
		//If ant goes out on the left side 
		//-> comes back in on the right side
		else if(PositionX < 0) {
			PositionX = width-1;
		}

		//If ant goes out on the bottom side 
		//-> comes back in on the top side
		if(PositionY > height-1) {
			PositionY = 0;
		}
		//If ant goes out on the top side 
		//-> comes back in on the bottom side
		else if(PositionY < 0) {
			PositionY = height-1;
		}
	}

	//updateMatrix()
	//Updates the old matrix with new white/gray fields
	public void updateMatrix() {
		int width = matrix.length;
		for(int i=0; i<width; i++) {
			int height = matrix[i].length;
			for(int j=0; j<height; j++) {
				drawBox(i,j);
			}	
		}
		Drawing.paint();
	}

	//draw()
	//Updates the old matrix and replaces the ant
	public void draw() {
		updateMatrix();
		newAntPos();
	}

	//startAnimation()
	//Starts the animation with the timer
	public void startAnimation(){ 
		this.timer = new Timer(DELAY, this);
		this.timer.start();
		try{
			synchronized(this.timer){this.timer.wait();}
		}
		catch(InterruptedException e){}
	}

	//actionPerformed()
	//Every time the fixed time of the timer is reached, 
	//the following events will be performed
	public void actionPerformed(ActionEvent e){
		this.nextSecond();
		this.draw();
	}
}