package testcases;

public class Tc001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double d=2343.5476;
		double roundedDouble = Math.round(d * 100.0) / 100.0;
		System.out.println("Rounded double: "+roundedDouble);
	}

}
