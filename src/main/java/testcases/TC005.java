package testcases;

public class TC005 {

	public static int mat1(){
		try {
			return 0;
			
		}
		finally {
			System.out.println("final block");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stuby
			System.out.println(TC005.mat1());
		
		

	}

}
