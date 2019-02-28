package java2_lab04;

import java.util.*;


public class A1Q3 {

    static class Student implements Comparable<Student> {
        private String name;
        private int score;

        public Student(String name, int a) {
            this.name = name;
            this.score = a;
        }

        public Student(String name) {
            this.name = name;
            this.score = new Random().nextInt(101);
        }

        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            if (this.score == o.score) {
                return this.name.compareTo(o.name);
            } else {
                return -(this.score - o.score);
            }
        }

        @Override
        public String toString() {
            return name + ": " + score;
        }
    }


    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        String[] namelist = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"
        , "m", "n", "o", "p", "q", "r"};
        for (String str : namelist){
            list.add(new Student(str));
        }
        // I generate score randomly.

        // make two students have the same score.
        list.add(new Student("s", 76));
        list.add(new Student("t", 76));
        Collections.sort(list);

        System.out.println(list);

        List<Student> listTop3 = new ArrayList<>();
        listTop3.add(list.get(0));
        listTop3.add(list.get(1));
        listTop3.add(list.get(2));

        // for loop iterate
        for (int i = 0; i < listTop3.size(); i ++){
            System.out.println(listTop3.get(i).toString());

        }
        System.out.println();
        //for each
        for (Student student : listTop3){
            System.out.println(student.toString());
        }
        System.out.println();

        // iterator
        Iterator it = listTop3.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println();
    }
}
