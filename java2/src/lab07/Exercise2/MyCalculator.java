package lab07.Exercise2;
public class MyCalculator {

	public static void main(String[] args) {
		int x = 360;
		int y = 60;
		System.out.println(x + "+" + y + "=" + getCalculator('+').CalculatorInt(x, y));
		
		System.out.println(x + "-" + y + "=" + getCalculator('-').CalculatorInt(x, y));

		System.out.println(x + "*" + y + "=" + getCalculator('*').CalculatorInt(x, y));
		
		System.out.println(x + "/" + y + "=" + getCalculator('/').CalculatorInt(x, y));
	}
	
	static Calculator getCalculator(char operator) {
		//write your code here
	    if (operator == '+'){
	        return (x, y) -> {return x + y;};
        } else if (operator == '-'){
	        return (x, y) -> {return x - y;};
        } else if (operator == '*'){
	        return (x, y) -> {return x * y;};
        } else {
	        return (x, y) -> {return x / y;};
        }
	}
}
