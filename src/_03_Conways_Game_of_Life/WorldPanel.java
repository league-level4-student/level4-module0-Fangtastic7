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

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell array[][];

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = w / cpr;
		System.out.println("size: " + cellSize);
		// 3. Initialize the cell array to the appropriate size.
		array = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < array.length; i++) {
			for (int y = 0; y < array.length; y++) {
				array[i][y] = new Cell(i * cellSize, y * cellSize, cellSize);
			}
		}
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive member to true of false
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			for (int y = 0; y < array.length; y++) {
				// int x = rand.nextInt(1);
				
					array[i][y].isAlive = rand.nextBoolean();
				
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterate through the cells and set them all to dead.
		System.out.println("Clear");
		for (int i = 0; i < array.length; i++) {
			for (int y = 0; y < array.length; y++) {
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
		// 6. Iterate through the cells and draw them all
		System.out.println("Paint");
		for (int i = 0; i < array.length; i++) {
			for (int y = 0; y < array.length; y++) {
				array[i][y].draw(g);
				System.out.println("Draw");
			}
		}

		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int i = 0; i < array.length; i++) {
			for (int y = 0; y < array.length; y++) {
				int num = getLivingNeighbors(i, y);
				System.out.println(num);
				livingNeighbors[i][y] = num;
				

			}
		}
		// 8. check if each cell should live or die
		for (int i = 0; i < array.length; i++) {
			for (int y = 0; y < array.length; y++) {
				array[i][y].liveOrDie(livingNeighbors[i][y]);
			}
		}

		repaint();
	}

	// 9. Complete the method.
	// It returns an int of 8 or less based on how many
	// living neighbors there are of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {
		int neighbors = 0;
		if (x > 0) {
			if (array[x - 1][y].isAlive) {
				neighbors++;
			}

		} else if (y > 0) {
			if (array[x][y - 1].isAlive) {
				neighbors++;
			}
			if (x > 0) {
				if (array[x - 1][y - 1].isAlive) {
					neighbors++;
				}
				if (cellsPerRow - 1 > x) {
					if (array[x + 1][y - 1].isAlive) {
						neighbors++;
					}
				}
			}

		} else if (x < cellsPerRow - 1) {
			if (array[x + 1][y].isAlive) {
				neighbors++;
			}
		} else if (y < cellsPerRow - 1) {
			if (array[x][y + 1].isAlive) {
				neighbors++;
			}
			if (x < cellsPerRow - 1) {
				if (array[x + 1][y + 1].isAlive) {
					neighbors++;
				}
				if (x > 0) {
					if (array[x - 1][y + 1].isAlive) {
						neighbors++;
					}
				}
			}
		}
		// see the amount of living neighbors

		// As shown below takes account of non-living and assumes that they are all
		// alive around
		// makes sure to check that the neighbors are alive.
		// for(int i = 0; i < array.length ; i++) {
		// for(int j=0; j < array.length; j++) {
		/*
		 * if (x == 0 && y == 0) { if (array[x + 1][y].isAlive) { neighbors = neighbors
		 * + 1; System.out.println("0,0"); } else if (array[x + 1][y + 1].isAlive) {
		 * neighbors = neighbors + 1; } else if (array[x][y + 1].isAlive) { neighbors =
		 * neighbors + 1; } } else if (x == 0 && y == cellsPerRow - 1) {
		 * System.out.println(x + ", " + y); if (array[x][y - 1].isAlive) { neighbors =
		 * neighbors + 1; } else if (array[x + 1][y - 1].isAlive) { neighbors =
		 * neighbors + 1; } else if (array[x + 1][y].isAlive) { neighbors = neighbors +
		 * 1; } } else if (y == 0 && x == cellsPerRow - 1) { System.out.println(x + ", "
		 * + y); if (array[x][y + 1].isAlive) { neighbors = neighbors + 1; } else if
		 * (array[x - 1][y + 1].isAlive) { neighbors = neighbors + 1; } else if (array[x
		 * - 1][y].isAlive) { neighbors = neighbors + 1; } } else if (x == cellsPerRow -
		 * 1 && y == cellsPerRow - 1) {
		 * 
		 * if (array[x][y - 1].isAlive) { neighbors = neighbors + 1; } else if (array[x
		 * - 1][y - 1].isAlive) { neighbors = neighbors + 1; } else if (array[x -
		 * 1][y].isAlive) { neighbors = neighbors + 1; } } else if (x == 0 && y > 0 && y
		 * < cellsPerRow - 1) { if (array[x][y - 1].isAlive) { neighbors = neighbors +
		 * 1; } else if (array[x + 1][y - 1].isAlive) { neighbors = neighbors + 1; }
		 * else if (array[x + 1][y].isAlive) { neighbors = neighbors + 1; } else if
		 * (array[x + 1][y + 1].isAlive) { neighbors = neighbors + 1; } else if
		 * (array[x][y + 1].isAlive) { neighbors = neighbors + 1; } } else if (y == 0 &&
		 * x > 0 && x < cellsPerRow - 1) { if (array[x - 1][y].isAlive) { neighbors =
		 * neighbors + 1; } else if (array[x - 1][y + 1].isAlive) { neighbors =
		 * neighbors + 1; } else if (array[x][y + 1].isAlive) { neighbors = neighbors +
		 * 1; } else if (array[x + 1][y + 1].isAlive) { neighbors = neighbors + 1; }
		 * else if (array[x + 1][y].isAlive) { neighbors = neighbors + 1; } } else if (x
		 * == cellsPerRow - 1 && y > 0 && y < cellsPerRow - 1) { if (array[x][y -
		 * 1].isAlive) { neighbors = neighbors + 1; } else if (array[x - 1][y -
		 * 1].isAlive) { neighbors = neighbors + 1; } else if (array[x - 1][y].isAlive)
		 * { neighbors = neighbors + 1; } else if (array[x - 1][y + 1].isAlive) {
		 * neighbors = neighbors + 1; } else if (array[x][y + 1].isAlive) { neighbors =
		 * neighbors + 1; } } else if (y == cellsPerRow - 1 && x > 0 && x < cellsPerRow
		 * - 1) { if (array[x - 1][y].isAlive) { neighbors = neighbors + 1; } else if
		 * (array[x - 1][y - 1].isAlive) { neighbors = neighbors + 1; } else if
		 * (array[x][y - 1].isAlive) { neighbors = neighbors + 1; } else if (array[x +
		 * 1][y - 1].isAlive) { neighbors = neighbors + 1; } else if (array[x +
		 * 1][y].isAlive) { neighbors = neighbors + 1; } } else if (x > 0 && y > 0 && x
		 * < cellsPerRow - 1 && y < cellsPerRow - 1) { if (array[x][y + 1].isAlive) {
		 * neighbors = neighbors + 1; } else if (array[x - 1][y + 1].isAlive) {
		 * neighbors = neighbors + 1; } else if (array[x - 1][y].isAlive) { neighbors =
		 * neighbors + 1; } else if (array[x - 1][y - 1].isAlive) { neighbors =
		 * neighbors + 1; } else if (array[x][y - 1].isAlive) { neighbors = neighbors +
		 * 1; } else if (array[x + 1][y - 1].isAlive) { neighbors = neighbors + 1; }
		 * else if (array[x + 1][y].isAlive) { neighbors = neighbors + 1; } else if
		 * (array[x + 1][y + 1].isAlive) { neighbors = neighbors + 1; } }
		 */
		/*
		 * for(int i = -1 ; i <= array.length ; i++) { for(int j=-1; j <= array.length;
		 * j++) { if( array[x+i][y+j].isAlive==true) { neighbors = neighbors +1; } } }
		 */

		return neighbors;

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
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		// System.out.println("x - " + e.getX());
		// System.out.println("y - " + e.getY());
		for (int i = 0; i < array.length; i++) {
			for (int y = 0; y < array.length; y++) {
				if (e.getX() < i * cellSize + cellSize && e.getX() > i * cellSize) {
					if (e.getY() < y * cellSize + cellSize && e.getY() > y * cellSize) {
						// System.out.println("Clicked");
						if (array[i][y].isAlive) {
							System.out.println("Kill??");
							array[i][y].isAlive = false;
						} else {
							array[i][y].isAlive = true;
						}
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
