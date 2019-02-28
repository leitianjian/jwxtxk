package java2_lab04;

import java.util.Arrays;

public class Person3 implements Comparable<Person3>{
    public Person3(String name, int age, char sex) {
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
    public int compareTo(Person3 o) {
        if(compare(this.sex, o.sex) == 0){
            return -this.name.compareTo(o.name);
        }else
            return compare(this.sex, o.sex);
    }
    public int compare(char a, char b){
        return a - b;
    }

    public static void main(String[] args) {
        Person3[] persons = new Person3[5];
        persons[0] = new Person3("Wang", 18, 'M');
        persons[1] = new Person3("Qing", 17, 'F');
        persons[2] = new Person3("Zhao", 19, 'F');
        persons[3] = new Person3("Liu", 18, 'M');
        persons[4] = new Person3("Ma", 17, 'F');

        Arrays.sort(persons);

        for(Person3 p: persons){
            System.out.println(p);
        }
    }
}
