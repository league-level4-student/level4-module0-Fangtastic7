
public class WarmUp {
public static void main(String[] args) {
	final float smallnumber = 0.000f;
	if((0.1 + 0.2) == 0.3) {
		System.out.println("A");
	}
	System.out.println(0.1 + 0.2);
	if((0.2 + 0.2) == 0.4) {
		System.out.println("B");
	}
	System.out.println(0.2 + 0.2);
	if((0.3 + 0.2) == 0.5) {
		System.out.println("C");
	}
	System.out.println(0.3 + 0.2);
	if((0.4 + 0.2) == 0.6) {
		System.out.println("D");
	}
	System.out.println(0.4 + 0.2);
	if(0.3-(0.1 + 0.2) < smallnumber) {
		System.out.println("Z");
	}
}
}
