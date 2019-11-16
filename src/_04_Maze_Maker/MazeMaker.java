package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	private static ArrayList<Cell> unvisited = new ArrayList<Cell>();
	
	public static Maze generateMaze(int w, int h){
		width = w; //number of cells
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
	 Cell cell = new Cell(randGen.nextInt(w), randGen.nextInt(h)); 
	// Cell cell = new Cell(width, height);
	//	maze = new Maze(width, height);
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(cell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.hasBeenVisited();
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		
		unvisited = getUnvisitedNeighbors(currentCell);
		System.out.println(unvisited.size());
		//C. if has unvisited neighbors,
		if(unvisited.isEmpty()) {
			//C1. select one at random.
			 int wid = randGen.nextInt(width);
			 int hei = randGen.nextInt(height);
			Cell cell1 = new Cell(wid, hei);
			System.out.println(cell1);
			//C2. push it to the stack
			uncheckedCells.push(cell1);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, cell1);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = cell1;
			currentCell.hasBeenVisited();
			
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		else {
			//D1. if the stack is not empty
			if(!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				Cell popped = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = popped;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
				
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		System.out.println(c1.getX());
		if(c1.getX() < maze.getWidth() - 1 ) {
		if(c1.getX()+1 == c2.getX() ) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		}
		if(c1.getX() > 0) {
		if( c1.getX()-1 == c2.getX()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		}
		if(c1.getY() > 0) {
		if( c1.getY()+1 == c2.getY()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		}
		if(c1.getY() < maze.getHeight() -1 ) {
		if(c1.getY()-1 == c2.getY()){
			c1.setSouthWall(false);
			c1.setNorthWall(false);
		}
	}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		unvisited.add(c);
		return unvisited;
	}
}
