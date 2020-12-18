package testcases;

public class TC004 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]= {3,4,5,3,9,0,5,0};
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				if(a[i]==a[j]) {
					System.out.println(a[j]);
				}

			}
		}
	}

}
