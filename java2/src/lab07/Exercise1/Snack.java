package lab07.Exercise1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Snack {
	public Snack(double price) {
		super();
		this.price = price;
	}

	@MyAnnotation(precision=1)
	private double price;

	@MyAnnotation(format = "yyyy-MM-dd")
	private Date productionDate;
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	@Override
	public String toString() {
		return "Snack [price=" + price + ", productionDate=" + productionDate + "]";
	}

    public static void main(String[] args) {
        Class c = Snack.class;
        double price = 777.3333;
//        Field[] fields = c.getDeclaredFields();
//		for (Field field : fields) {
//			Annotation[] annotations = field.getAnnotations();
//			System.out.print(field.getName() + "---->");
//			for (Annotation annotation : annotations) {
//				System.out.println(annotation.toString());
//			}
//		}
        try {
            MyAnnotation myAnnotation = c.getDeclaredField("productionDate").getAnnotation(MyAnnotation.class);
            Method setDate = c.getMethod("setProductionDate", Date.class);
            Method getDate = c.getMethod("getProductionDate");
            Constructor<Snack> constructor = c.getConstructor(double.class);
            Snack snack = (Snack)constructor.newInstance(price);
            String format = myAnnotation.toString().split("=")[1];
            format = format.split(",")[0];
            System.out.println(format);
            DateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse("2018-10-24 16:10:00");
            setDate.invoke(snack, date);
            System.out.println("The date is " + getDate.invoke(snack));
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException | ParseException | NoSuchFieldException e){
            e.printStackTrace();
        }
    }
}
