package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GridPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;
	
	//1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] array2d;
 	private Color color;
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		array2d = new Pixel[rows][cols];
		
		//3. Iterate through the array and initialize each element to a new pixel.
		//Random random = new Random();
		for(int i=0; i < rows; i++) {
			for(int j=0; j < cols; j++) {
				array2d[i][j] = new Pixel(i, j);
			}
		}
		
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {
		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		Pixel x = array2d[mouseY / pixelHeight][mouseX /pixelWidth];
		x.color = color;
	//	System.out.println(mouseX);
	//	System.out.println(mouseY);
		//System.out.println(array2d[1][3].x);
		//for(int i=0; i < array2d.length; i++) {
		//	for(int j=0; j <array2d.length; j++) {
				//System.out.println(array2d[i][j].x);
			//	System.out.println(i);
				//System.out.println(j);
		//if(mouseX == pixelWidth*i + j ) {
		//	for( int x = pixelWidth *i; x < pixelWidth * (i - 1); x++) {
		//		for( int y= pixelHeight * j; y <pixelHeight * (j-1) ; y++) {
		//	if(mouseX <pixelWidth * i && mouseX > pixelWidth * (i+1) && mouseY <pixelHeight * j && mouseY > pixelHeight * (j+1)) {
		//	System.out.println("Color changed");
		// array2d[i][j].color = color;
		 System.out.println(array2d.length);
		 System.out.println(rows);
	//	}
			//	}}
		}
			//}
		//}
		
//	}
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Iterate through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		//   Then, use drawRect to add a grid pattern to your display.
		for(int i=0; i < rows; i++) {
			for(int j=0; j < cols; j++) {
				
				g.setColor(array2d[i][j].color);
				g.fillRect(j * pixelWidth, i * pixelHeight, pixelWidth, pixelHeight);
				g.setColor(Color.black);
				g.drawRect(j * pixelWidth, i * pixelHeight, pixelWidth, pixelHeight);
				
			}
		}
	}
}
