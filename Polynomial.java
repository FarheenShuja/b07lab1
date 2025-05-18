public class Polynomial {
	
	double coeffs[];
	public Polynomial() {
		coeffs = new double[0];
	}
	
	public Polynomial(double given[]) {
		coeffs = new double[given.length];
		coeffs = given.clone();
	}
	
	public double[] getCoeffs()
	{
		return coeffs;
	}
	
	public Polynomial add(Polynomial a) {

		
		double[] coeffs2 = new double[a.getCoeffs().length];
		coeffs2 = a.getCoeffs().clone();

		int smallpoly = coeffs2.length;
		int onebigger = 0;
		if (coeffs.length < coeffs2.length) {
			smallpoly = coeffs.length;
		}
		else {
			onebigger = 1;
		}
		
		if (onebigger == 0) {
			for (int i = 0; i < smallpoly; i++) {
				coeffs2[i] = coeffs[i] + coeffs2[i];
			}
			return new Polynomial(coeffs2);	
		}
		else {
			for (int i = 0; i < smallpoly; i++) {
				coeffs[i] = coeffs[i] + coeffs2[i];
			}
			return new Polynomial(coeffs);	
		}

	}
	
	public double evaluate(double x)
	{
		double sum=0;
		for (int i = 0; i < coeffs.length; i++) {
			sum = sum + coeffs[i]*Math.pow(x, i);
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
	
}
