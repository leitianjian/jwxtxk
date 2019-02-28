package test.lab02;
public class ChainedExceptionDemo {
	public static void main(String[]args){
		//chained exception
		CalledClass.entry(args);
	}
}


//chained exception
class CalledClass{
	public static void entry(String[]args){
		try{
			method1();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void method1()throws Exception{
		try{
			method2();
		}
		catch(Exception ex){
			throw new Exception("New info from method1",ex);
		}
	}
	public static void method2() throws Exception {
	    try{
	        method3();
        }catch (Exception ex) {
            throw new Exception("New info from method2",ex);
        }
	}
	public static void method3() throws Exception{
	    throw new Exception("New info from method3");
    }
}