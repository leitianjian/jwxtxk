package test.lab02;

public class AboutFinally {
	public static void main(String[] arg) {
		System.out.print(Method());
	}

	private static boolean Method() {
		try
		{
			return true;
		}
		finally 
		{
			return false;
		}		
	}
}
