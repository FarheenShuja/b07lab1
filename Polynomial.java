package Labs;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Polynomial {
	
	double coeffs[];
	int exp[];
	public Polynomial() {
		coeffs = new double[0];
		exp = new int[0];
	}
	
	public Polynomial(double given[], int given_exp[]) {
		coeffs = new double[given.length];
		coeffs = given.clone();
		exp = new int[given_exp.length];
		exp = given_exp.clone();
	}
	
	public Polynomial (File content) throws FileNotFoundException {
		Scanner scan = new Scanner(content);
		String poly = scan.nextLine();
		String[] terms = poly.split("(?=-)|(?=\\+)");
		
		int length = terms.length;
		coeffs = new double[length];
		exp = new int[length];
		
		for(int i = 0; i < length; i++) {
			if (terms[i].contains("x")) {
				String[] idv = terms[i].split("x");
				coeffs[i] = Double.parseDouble(idv[0]);
				exp[i] = Integer.parseInt(idv[1]);
			}
			else {
				coeffs[i] = Double.parseDouble(terms[i]);
				exp[i] = 0;
			}
		}
	}
	
	public void saveToFile(String writeFile) {
		String poly = "";
		for(int i = 0; i < exp.length; i++) {
			if (exp[i] != 0) {
				if (coeffs[i]<0)
				{
					poly = poly + coeffs[i] + "x" + exp[i];
				}
				else
				{
					poly = poly + "+" + coeffs[i] + "x" + exp[i];
				}
			}
			else {
				poly = poly + coeffs[i];
			}
		}
		try{
			FileWriter wr = new FileWriter("writeFile");
			wr.write(poly);
			wr.close();
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public double[] getCoeffs()
	{
		return coeffs;
	}
	
	public int[] getExp()
	{
		return exp;
	}

	public int combined(int exp1[], int exp2[])
	{
		int total = exp1.length + exp2.length;
		int same = 0;
		for (int i = 0; i < exp1.length; i++) {
			for (int j = 0; j < exp2.length; j++) {
				if (exp1[i] == exp2[j]) {
					same = same + 1;
				}
			}
			
		}
		return total-same;
	}
	
	public Polynomial add(Polynomial a)
	{
		double[] coeffs2 = new double[a.getCoeffs().length];
		coeffs2 = a.getCoeffs().clone();
		int[] exp2 = new int[a.getExp().length];
		exp2 = a.getExp().clone();
		
		int sum = combined(exp, exp2);
		double[] sumcoeffs = new double[sum];
		int[] sumexp = new int[sum];
		
		boolean match = false;
		for(int i = 0; i < exp.length; i++) {
			match = false;
			for (int j = 0; j < exp2.length; j++) {
				if (exp[i] == exp2[j]) {
					sumcoeffs[i] = coeffs[i] + coeffs2[j];
					sumexp[i] = exp[i];
					match = true;
				}
			}
			if (match == false) {
				sumcoeffs[i] = coeffs[i];
				sumexp[i] = exp[i];
			}
		}
		
		match = false;
		int end = exp.length;
		for(int n = 0; n < exp2.length; n++) {
			match = false;
			for(int k = 0; k < exp.length; k++) {
				if (exp2[n] == sumexp[k] && sumcoeffs[k] != 0) {
					match = true;
				}
			}
			if (match == false) {
				sumcoeffs[end] = coeffs2[n];
				sumexp[end] = exp2[n];
				end = end + 1;
			}

		}
		return new Polynomial(sumcoeffs,sumexp);
	}

	public double evaluate(double x)
	{
		double sum=0;
		for (int i = 0; i < coeffs.length; i++) {
			sum = sum + coeffs[i]*Math.pow(x, exp[i]);
		}
		return sum;
	}
	
	public boolean hasRoot(double root)
	{
		if (evaluate(root)==0)
			return true;
		else
			return false;
	}
	
	public int check(int[] arr, int num) {
		int index = -1;
		for(int i = 0; i < arr.length; i++) {
			if(num == arr[i]) {
				index = i;
			}
		}
		return index;
	}
	
	public Polynomial multiply(Polynomial a) {
		double[] coeffs2 = new double[a.getCoeffs().length];
		coeffs2 = a.getCoeffs().clone();
		int[] exp2 = new int[a.getExp().length];
		exp2 = a.getExp().clone();
		
		int sum = exp.length + exp2.length;
		double[] sumcoeffs = new double[sum];
		int[] sumexp = new int[sum];
		
		int sum_p = 0;
		for(int i = 0; i < exp.length; i++) {
			for(int j = 0; j < exp2.length; j++) {
				int newexp = exp[i]+exp2[j];
				int index = check(sumexp, newexp);
				if(index == -1) {
					sumexp[sum_p] = newexp;
					sumcoeffs[sum_p] = coeffs[i] * coeffs2[j];
					++sum_p;
				}
				else {
					sumcoeffs[index] = sumcoeffs[index] + (coeffs[i] * coeffs2[j]);
				}
			}
		}
		
		int i = 0;
		while(sumexp[i] != 0) {
			++i;
		}
		
		return new Polynomial(sumcoeffs,sumexp);
	}
	
}
