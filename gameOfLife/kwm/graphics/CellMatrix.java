/*-------------------------------------------------------------
 * CellMatrix.java
 * Author: Verena Schickmair
 * Date: 21.04.2020
 * Task: Task 5
 * 
 * This class generates a Matrix with width*height cells, which 
 * have a random state (they are either alive (true) or dead (false)).
 * The state is dependent on the probability entered by the user.
 * By adding a animation, the matrix (generation) changes every second. 
 * Every cell is changing according to certain rules.
 * 
 * Methods:
 * initialize(): Generates the first generation, Return: void
 * 
 * alive(int i, int j): Generates a random number and sets the 
 * cell alive (true) or dead (false) - Return: void
 * 
 * draw(): Draws the matrix (size width*height) - Return: void
 * 
 * drawCell(int i, int j): Draws every cell (size: 15 pixels) according
 * to their state. If the state is true, the cell is green and else
 * it is white. - Return: void
 * 
 * startAnimation(): Sets the timer and starts the animation - Return: void
 * 
 * actionPerformed(ActionEvent e): Starts the events generateCellGeneration()
 * and draw() depending on the timer - Return: void
 * 
 * generateCellGeneration(): Generates a new generation - Return: void
 * 
 * copyGameMatrix(int width, int height): Creates a copy of the latest 
 * gameMatrix-Matrix - Return: void
 * 
 * generateNew(int width, int height): Fills the copy with new states 
 * by analyzing the neighbors of the old matrix and applying the rules 
 * Return: void
 * 
 * replaceOld(int width, int height): The new matrix replaces the old 
 * matrix by overwriting - Return: void
 * 
 * applyRules(int aliveNeighbors, int i, int j): Applying the rules on 
 * every cell in the matrix - Return: void
 * 
 * countNeighbors(int i, int j, int width, int height): Counts the numbers
 * of neighbors of every cell in the matrix - Return: int value
 * -----------------------------------------------------------*/

package kwm.graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import kwm.Drawing;

public class CellMatrix implements ActionListener{

	private boolean[][] gameMatrix;
	private boolean[][] futureMatrix;
	public final int SIZE = 15;
	public double p;

	public Timer timer;
	public static int delay = 100;

	//Constructor
	public CellMatrix(int p, int width, int height) {
		this.gameMatrix = new boolean[width][height];
		this.p = p;
		initialize();
		draw();
	}

	//initialize()
	//Generates the first generation
	//Return: void
	public void initialize() {
		int width = gameMatrix.length;
		int height = gameMatrix[0].length;

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				alive(i, j);
			}
		}
	}

	//alive(int i, int j)
	//Generates a random number and sets the 
	//cell alive (true) or dead (false)
	//Return: void
	public void alive(int i, int j) {
		double r = Math.random();
		double k = (p/100);

		if (r < k) {
			gameMatrix[i][j] = true;
		}
		else {
			gameMatrix[i][j] = false;
		}
	}

	//draw()
	//Draws the matrix (size width*height)
	//Return: void
	public void draw(){
		int width = gameMatrix.length;
		int height = gameMatrix[0].length;

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				drawCell(i,j);
			}
		}
		Drawing.paint();
	}

	//drawCell(int i, int j)
	//Draws every cell (size: 15 pixels) according to their state.
	//If the state is true, the cell is green and else it is white.
	//Return: void
	public void drawCell (int i, int j) {
		int x = i*SIZE;
		int y = j*SIZE;

		if (gameMatrix[i][j] != true) {
			Drawing.graphics.setColor(Color.white); //white box
			Drawing.graphics.fillRect(x,y,SIZE,SIZE);
		}
		else {
			Drawing.graphics.setColor(Color.green); //green box
			Drawing.graphics.fillRect(x,y,SIZE,SIZE);
		}
		Drawing.graphics.setColor(Color.black); //black border
		Drawing.graphics.drawRect(x,y,SIZE,SIZE);
		Drawing.paint();
	}

	//startAnimation()
	//Sets the timer and starts the animation
	//Return: void
	public void startAnimation(){
		this.timer = new Timer(CellMatrix.delay, this);
		this.timer.start();

		try{
			synchronized(this.timer){this.timer.wait();}
		}
		catch(InterruptedException e){}
		Drawing.paint();
	}

	//actionPerformed(ActionEvent e)
	//Starts the events generateCellGeneration() and draw() depending on the timer
	//Return: void
	public void actionPerformed(ActionEvent e){
		this.generateCellGeneration();
		this.draw();
	}

	//generateCellGeneration()
	//Generates a new generation
	//Return: void
	public void generateCellGeneration(){
		int width = this.gameMatrix.length;
		int height = this.gameMatrix[0].length;
		copyGameMatrix(width, height);
		generateNew(width, height);
		replaceOld(width, height);
	}

	//copyGameMatrix(int width, int height)
	//Creates a copy of the latest gameMatrix-Matrix
	//Return: void
	public void copyGameMatrix(int width, int height) {
		futureMatrix = new boolean[width][height];

		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				this.futureMatrix[i][j] = this.gameMatrix[i][j];
			}
		}
	}

	//generateNew(int width, int height)
	//Fills the copy with new states by analyzing the neighbors 
	//of the old matrix and applying the rules 
	//Return: void
	public void generateNew(int width, int height) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int aliveNeighbors = countNeighbors(i,j,width,height);
				applyRules(aliveNeighbors,i,j);
			}
		}	
	}

	//replaceOld(int width, int height)
	//The new matrix replaces the old matrix by overwriting
	//Return: void
	public void replaceOld(int width, int height) {
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				this.gameMatrix[i][j] = this.futureMatrix[i][j];
			}
		}
	}

	//applyRules(int aliveNeighbors, int i, int j)
	//Applying the rules on every cell in the matrix
	//Return: void
	public void applyRules(int aliveNeighbors, int i, int j) {
		//Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
		if(aliveNeighbors == 3 && !gameMatrix[i][j]) {
			futureMatrix[i][j] = true;
		}

		//Any live cell with two or three live neighbours lives on to the next generation
		else if(aliveNeighbors == 2 && gameMatrix[i][j]) {
			futureMatrix[i][j] = true;
		}
		else if(aliveNeighbors == 3 && gameMatrix[i][j]) {
			futureMatrix[i][j] = true;
		}

		//Any live cell with fewer than two live neighbors dies, as if by underpopulation
		else if(aliveNeighbors < 2 && gameMatrix[i][j]) {
			futureMatrix[i][j] = false;
		}

		//Any live cell with more than three live neighbors dies, as if by overpopulation
		else if(aliveNeighbors > 3 && gameMatrix[i][j]) {
			futureMatrix[i][j] = false;
		}
	}

	//countNeighbors(int i, int j, int width, int height)
	//Counts the numbers of neighbors of every cell in the matrix
	//Return: int value
	public int countNeighbors(int i, int j, int width, int height) 
	{
		//Counter
		int aliveNeighbors = 0; 

		//The position of the neighbors clockwise (staring right-middle)
		int[] ioff = {1,1,0,-1,-1,-1,0,1};
		int[] joff = {0,1,1,1,0,-1,-1,-1};

		//8 runs for every neighbor
		for (int x = 0; x < 8; x++) {
			int neighborI = i+ioff[x];
			int neighborJ = j+joff[x];

			//Ignoring fields out of the border + checking if neighbor is alive
			if(neighborI >= 0 && neighborJ >= 0 && neighborI < width && neighborJ < height) {
				if(gameMatrix[neighborI][neighborJ])
					aliveNeighbors++;
			}
		}
		return aliveNeighbors;
	}
}