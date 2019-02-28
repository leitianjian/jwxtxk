package lab07;
import lab07.Exercise1.MyAnnotation;
import lab07.Exercise1.Snack;
// 反射的作用，作用于集合，当集合是泛型时，要用反射来返回类型，进行操作
// 反射的作用，批改作业脚本，知道class名称，用invoke执行main方法，

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

public class Lab7 {

	public static void main(String[] args) throws Exception {		
		Class clazz = Snack.class;
		//Class clazz = Class.forName("per.lab7.Snack");
		
        //Snack snack = new Snack(0.9);
        //Class clazz = snack.getClass();
		//������ĳ�Ա�����ϵ�ע��
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			System.out.print(field.getName() + "---->");
			for (Annotation annotation : annotations) {
				System.out.println(annotation.toString());			
			}
		}
		//��ȡָ�������ϵ�ָ��ע��
		MyAnnotation myAnnotation = clazz.getDeclaredField("price").getAnnotation(MyAnnotation.class);
		
		int precision = myAnnotation.precision();		
		double temp = 778.654;		
		BigDecimal b = new BigDecimal(temp); 
		double price = b.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(price);
		
		//��ȡ��������ͨ����ȡ�Ĺ���������Snack����
		Constructor<Snack> aConstructor = clazz.getConstructor(double.class);// 需要传入参数列表
		Snack sanck = (Snack)aConstructor.newInstance(price);
		//��ȡָ������
		Method getPrice = clazz.getMethod("getPrice");
		//����ָ������.invoke
		System.out.println("The price is " + getPrice.invoke(sanck));
		

		//Lamda���ʽ
		TreeMap map = new TreeMap<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		//ʹ��Lambda���ʽ��ʾ�ӿ���ʿʵ����Ķ���
//		TreeMap map = new TreeMap<>(				
//			(Person o1, Person o2) ->{return o1.getName().compareTo(o2.getName());}			// 泛型要声明类型
//		);
		map.put(new Person("Ma"), "This");
		map.put(new Person("Liu"), "is");
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object + "------->"+ object.hashCode());
		}
		
	}

}

