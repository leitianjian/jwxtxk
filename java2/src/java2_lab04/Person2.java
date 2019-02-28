package java2_lab04;

import java.util.Arrays;

public class Person2 implements Comparable<Person2>{
    public Person2(String name, int age, char sex) {
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
    public int compareTo(Person2 o) {
        return this.name.compareTo(o.name);
    }

    public static void main(String[] args) {
        Person2[] persons = new Person2[5];
        persons[0] = new Person2("Wang", 18, 'M');
        persons[1] = new Person2("Qing", 17, 'F');
        persons[2] = new Person2("Zhao", 19, 'F');
        persons[3] = new Person2("Liu", 18, 'M');
        persons[4] = new Person2("Ma", 17, 'F');

        Arrays.sort(persons);

        for(Person2 p: persons){
            System.out.println(p);
        }
    }
}
