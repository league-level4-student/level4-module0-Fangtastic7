package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell array[][];
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize = w / cpr;
		//3. Initialize the cell array to the appropriate size.
		array = new Cell[w][h];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for(int i = 0 ; i < array.length; i++) {
			for(int y =0;  y <array.length; y++) {
				array[i][y] = new Cell(w, h, cellSize);
			}
		}
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		Random rand = new Random();
		for(int i = 0 ; i < array.length; i++) {
			for(int y =0;  y <array.length; y++) {
			int x =	rand.nextInt(1);
			if(x == 1) {
				array[i][y].isAlive = true;	
			}
			else {
				array[i][y].isAlive = true;
			}
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for(int i = 0 ; i < array.length; i++) {
			for(int y =0;  y <array.length; y++) {
				array[i][y].isAlive = false;
			}
			}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for(int i = 0 ; i < array.length; i++) {
			for(int y =0;  y <array.length; y++) {
				array[i][y].draw(g);
			}
		}
		
		
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for(int i = 0 ; i < array.length; i++) {
			for(int y =0;  y <array.length; y++) {
				
				livingNeighbors[i][y] = getLivingNeighbors(i, y);
				
			}
			}
		//8. check if each cell should live or die
		for(int i = 0 ; i < array.length; i++) {
			for(int y =0;  y <array.length; y++) {
				System.out.println("Cell: " + i + " " + y + " - " + array[i][y].isAlive);
			}
			}
		
		
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		
		if(x>0 && y>0 && x < cellsPerRow && y < cellsPerRow) {
			return 8;
		}
		else if(x==0 || y>0 && y < cellsPerRow || x==cellsPerRow && y > 0 && y < cellsPerRow || y==0 && x>0 && x < cellsPerRow || y==cellsPerRow && x > 0 && x < cellsPerRow) {
			return 5;
		}
		else if(x==0 && y==0 || x==cellsPerRow && y==cellsPerRow || x==0 && y==cellsPerRow || y==0 && x ==cellsPerRow) {
			return 3;
		}
		return -1;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		System.out.println(e.getX());
		System.out.println(e.getY());
		for(int i =0; i < array.length; i++) {
			for(int y= 0; y < array.length; y++) {
				if(array[i][y].getX()==e.getX() && array[i][y].getY()==e.getY()) {
					if(array[i][y].isAlive==true) {
						array[i][y].isAlive=false;
					}
					else {
						array[i][y].isAlive=true;
					}
				}
			}
		}
		
		
		
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
