package java2_lab05;

import java.util.HashSet;
import java.util.Iterator;

public class q1 {

    static class Person2 {
        private String name;
        private int age;
        private long id;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Person2(String name, int age, long id) {
            super();
            this.name = name;
            this.age = age;
            this.id = id;
        }
        public Person2(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }
        public Person2(String name) {
            super();
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + ", id=" + id + "]";
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Person2 other = (Person2) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;

            if (age != other.age) {
                return false;
            }
            return true;
        }
    }


    public static void main(String[] args) {
        HashSet<Person2> set = new HashSet();
        set.add(new Person2("Li", 22, 1000000008));
        set.add(new Person2("Deng", 20, 1000000007));
        set.add(new Person2("Zhao", 23, 1000000006));
        set.add(new Person2("Zhao", 32, 1000000004));
        System.out.println("=========Before Remove=========");
        Iterator iterator1 = set.iterator();
        while(iterator1.hasNext()){
            Object object = iterator1.next();
            System.out.println(object);
        }

        if (set.contains(new Person2("Zhao", 32, 1000000004))){
            System.out.println("The specified element is exist.");
            set.remove(new Person2("Zhao", 32, 1000000004));
        }
        System.out.println("=========After Remove==========");
        Iterator iterator2 = set.iterator();
        while(iterator2.hasNext()){
            Object object = iterator2.next();
            System.out.println(object);
        }
    }
}
