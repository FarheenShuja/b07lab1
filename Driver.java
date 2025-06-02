package Labs;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException {
	
		File content = new File("readFile.txt");
		Polynomial p5 = new Polynomial(content);
		p5.saveToFile("writeFile.txt");
		
		double [] a1 = {2,1};
		int [] a2 = {1,2};
		Polynomial p1 = new Polynomial(a1,a2);
		
		double [] b1 = {5,3,4};
		int [] b2 = {2,1,0};
		Polynomial p2 = new Polynomial(b1,b2);
		
		double [] c1 = {1};
		int [] c2 = {1};
		Polynomial p3 = new Polynomial(c1,c2);
		
		double [] d1 = {1};
		int [] d2 = {1};
		Polynomial p4 = new Polynomial(d1,d2);
		
		Polynomial s = p1.multiply(p2);
		Polynomial t = p3.multiply(p4);
		
		System.out.println("s(0.2) = " + s.evaluate(0.2));
		System.out.println("s(2) = " + t.evaluate(2));
		
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
	}
}
