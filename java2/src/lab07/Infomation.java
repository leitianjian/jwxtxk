package lab07;

public class Infomation {

	public static void main(String[] args) {
		Info info = getInfo();
		info.print();
	}
	
	public static Info getInfo() {
		Info info;
		info = new Info() {
			
			@Override
			public void print() {
				System.out.println("Welcome!");				
			}
		};
		//info = ()->{System.out.println("Welcome!");};
		
		return info;
	}
}
