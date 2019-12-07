//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

	public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
		int side = 0;
		int up = 0;
		int down = 0;
		int normal = 0;
    	int[] location1 = new int[2];
    	int[] location2 = new int[2];
    	int[] location3 = new int[2];
    	int[] location4 = new int[2];
        for(int i =0; i < field.length-1; i++) {
        	for(int j = 0; j < field.length-1; j++) {
        		
        		if(i  < field.length-2) {
        			
        		if((field[i][j] == 'w') && (field[i+1][j] == 'o') && field[i+2][j] == 'c') {
        			System.out.println(i + ", " + j + "(condition: flipped side");
        			side = side +1;
        			location1[0] = i;
        			location1[1] = j;
        		}
        		}
        		else if(i > 1) {
        			if(field[i][j] == 'c' && field[i-1][j] == 'o' && field[i-2][j] == 'w') {
        				System.out.println(i + ", " + j + "(condition: normal");
        				normal = normal +1;
        				location2[0] = i;
            			location2[1] = j;
        			}
        		}
        		
        		else if( j> 1 ) {
        			 if(field[i][j] == 'c' && field[i][j-1] == 'o' && field[i][j-2] == 'w') {
        				 System.out.println(i + ", " + j + "(condition: bottom to top");
        				up = up +1;
        				location3[0] = i;
            			location3[1] = j;
            		}
        		}
        		
        		else if( j < field.length-2) {
        			if(field[i][j] == 'c' && field[i][j+1] == 'o' && field[i][j+2] == 'w') {
        				 System.out.println(i + ", " + j + "(condition: top to bottom");
       				down = down +1;
       				location4[0] = i;
        			location4[1] = j;
           		}
        		}	 
        	}
        }
        if(side == 1) {
        	return location1;
        }
        else if(normal == 1) {
        	return location2;
        }
        else if(up == 1) {
        	return location3; 
        }
        else if(down ==1) {
        	return location4;
        }
    
return null;
}
	}
