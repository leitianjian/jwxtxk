package java2_lab04;

import java.util.Arrays;

public class Person1 implements Comparable<Person1>{
	public Person1(String name, int age, char sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	private String name;
	private int age;
	private char sex;
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}	
	public void setAge(int age) {
		this.age = age;
	}	
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}

    @Override
    public int compareTo(Person1 o) {
        return compare(this.age, o.age);
    }
    public int compare(int a, int b){
	    return (a > b) ? 1 : ((a == b) ? 0 : -1);
    }

    public static void main(String[] args) {
        Person1[] persons = new Person1[5];
        persons[0] = new Person1("Wang", 18, 'M');
        persons[1] = new Person1("Qing", 17, 'F');
        persons[2] = new Person1("Zhao", 19, 'F');
        persons[3] = new Person1("Liu", 18, 'M');
        persons[4] = new Person1("Ma", 17, 'F');

        Arrays.sort(persons);

        for(Person1 p : persons){
            System.out.println(p);
        }
    }
}

