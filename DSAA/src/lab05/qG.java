package lab05;

import java.util.Scanner;
import java.util.Stack;

public class qG {
    static void getTable (String str, int[] table){
        int i = -1;
        int j = 0;
        table[0] = -1;
        while (j < str.length() - 1){
            if (i == -1 || str.charAt(i) == str.charAt(j)) {
                i++;
                j++;
                table[j] = i;
            } else {
                i = table[i];
            }
        }
        while (i != -1 && str.charAt(j) != str.charAt(i)){
            i = table[i];
        }
        if (i == -1){
            ++ j;
            table[j] = 0;
        } else {
            if (str.charAt(j) == str.charAt(i)){
                ++ i;
                ++ j;
                table[j] = i;
            }
        }
    }
    static class MyStack {
        char[] array;
        int stackTop = -1;

        public MyStack(int len){
            array = new char[len];
        }

        public char peek (){
            if (stackTop == -1){
                return '\0';
            } else {
                return array[stackTop];
            }
        }

        public char pop(){
            if (stackTop == -1){
                return 0;
            } else {
                stackTop --;
                return array[stackTop + 1];
            }
        }

        public void push (Character c){
            stackTop ++;
            array[stackTop] = c;
        }

        public boolean isempty (){
            switch (stackTop){
                case -1 :
                    return true;
                 default:
                     return false;
            }
        }

        public String toString(){
            return new String(array, 0, stackTop + 1);
        }
    }


    static class Word {
        String word;
        int[] kmpTable;
        int j; // word ptr

        public Word(String word, int j){
            this.word = word;

            this.j = j;
            this.kmpTable = new int[word.length() + 1];
            getTable(this.word, this.kmpTable);
        }
    }

    static class WordPosition {
        int[] array;

        public WordPosition (int len){
            this.array = new int[len];
        }
    }

    static void giveij (Word[] words, WordPosition wordPosition){
        for(int i = 0; i < words.length; i ++){
            words[i].j = wordPosition.array[i];
        }
    }


    static int matcher (char c, Word[] words, Stack<WordPosition> stack){
        int min_len = 100000;
        WordPosition p = new WordPosition(words.length);
        for (int i = 0; i < words.length; i ++){

            Word word = words[i];
            if (word.j > word.word.length()){

            }
            while(word.j == word.word.length() ||(word.j != -1 && c != word.word.charAt(word.j))){
                word.j = word.kmpTable[word.j];
            }
            word.j ++;
            p.array[i] = word.j;
            int need = word.word.length() - word.j;
            if (need < min_len){
                min_len = need;
            }
        }
        stack.push(p);
        return min_len;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLines = sc.nextInt();
        sc.nextLine();
        Word[] words = new Word[numLines];
        for (int i = 0; i < numLines; i ++){
            words[i] = new Word(sc.nextLine(), 0);
//            System.out.print(words[i]);
        }

        String operation = sc.nextLine();
        int opera_len = operation.length();
        MyStack command = new MyStack(opera_len);

        Stack<Integer> result   = new Stack<>();
        Stack<WordPosition> position = new Stack<>();

        int min_len = 100000;
        for (Word word : words){
            int wordLen = word.word.length();
            if (wordLen < min_len){
                min_len = wordLen;
            }
        }

        System.out.println(min_len);
        result.push(min_len);
        position.push(new WordPosition(numLines));

        for (int i = 0; i < opera_len; i ++){
            if (operation.charAt(i) != '-'){
                char c = operation.charAt(i);
                command.push(c);
                int result1 = matcher(c, words, position);
                result.push(result1);
                System.out.println(result1);
            } else {
                if (command.isempty()){
                    giveij(words, position.peek());
                    System.out.println(result.peek());
                } else {
                    command.pop();
                    position.pop();
                    giveij(words, position.peek());
                    result.pop();
                    System.out.println(result.peek());
                }
            }
        }
    }
}
//    static class A {
//        int[] i;
//        int[] j;
//        int itop = 0;
//        int jtop = 0;
//
//        public A(int len){
//            i = new int[len];
//            j = new int[len];
//        }
//
//        public void addElement (int a1, int a2){
//            i[itop] = a1;
//            itop ++;
//            j[jtop] = a2;
//            jtop ++;
//        }
//
//    }
//    static void formArray (Word[] words, int[] i, int[] j){
//        for(int k = 0; k < words.length; k ++){
//            i[k] = words[k].i;
//            j[k] = words[k].j;
//        }
//    }

//    static int KMPmatcher (String command, Word word){
//        int i = command.length();
//        int j = word.j;
//        String wordStr = word.word;
//        int table[] = word.kmpTable;
//        int len_command = command.length();
//        int word_len = wordStr.length();
//
//
//
//        while (i != len_command){
//            if (j == word_len){
//                j = table[j];
//            }
//            if (j == -1 || command.charAt(i) == wordStr.charAt(j)){
//                i ++;
//                j ++;
//                if (j == word_len && i != len_command){
//                    j = table[j];
//                }
//            } else {
//                j = table[j];
//            }
//        }
//        if (j == 0){
//            word.j = 0;
//            return word_len;
//        } else {
//            word.j = j;
//            return word_len - j; //
//        }
//
//    }
//    static void giveij (Word words[], A a){
//        for (int k = 0; k < words.length; k ++) {
//            words[k].i = a.i[k];
//            words[k].j = a.j[k];
//        }
//    }
//        int min = min_len;
//            String comStr = command.toString();
//            A a2 = new A(word_len);
//            for (Word word : words){
//                int need = KMPmatcher(comStr, word);
//                a2.addElement(word.i, word.j);
//                if (need < min){
//                    min = need;
//                }
//            }
//            System.out.println(min);
//            result.push(min);
//            result_ij.push(a2);
//        while(commandptr < opera_len){
//            if(operation.charAt(commandptr) != '-'){
//                commandptr ++;
//            } else {
//                operation.deleteCharAt(commandptr);
//                commandptr --;
//                opera_len --;
//                if (commandptr != 0){
//
//                }
//
//            }
//        }

//        for (int i = 0; i < opera_len; i ++){
//            if (operation.charAt(i) != '-'){
//                commandptr ++;
//            } else {
//                operation.deleteCharAt(i);
//                i --;
//
//                opera_len --;
//                if (commandptr != -1){
//                    operation.deleteCharAt(i);
//                    opera_len --;
//                    result_ij.pop();
//                    giveij(words, result_ij.peek());
//                    result.pop();
//                    System.out.println(result.peek());
//                   commandptr --;
//                } else {
//                    giveij(words, result_ij.peek());
//                    System.out.println(result.peek());
//                    //i--;
//                }
//                continue;
//            }
//            int min = min_len;
//            String command;
//            if (commandptr == -1){
//                command = "";
//            } else {
//                command = operation.substring(0, commandptr + 1);
//                System.out.println(command);
//            }
//            for (int j = 0; j < words.length; j ++){
//                int need = KMPmatcher(command, words[j]);
//                if (need < min){
//                    min = need;
//                }
//            }
//            System.out.println(min);
//            result.push(min);
//            a = new A(words);
//            result_ij.push(a);
//        }
//        Stack<A> result_ij = new Stack<>();


//        Character[] command = new Character[operation.length() + 1];
//        char array[] = command.toCharArray();
//        command[0] = 0;
//        A a = new A(word_len);