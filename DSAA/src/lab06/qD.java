package lab06;

import java.util.*;

public class qD {
    static class Node{
        private int previousNodeIndex;
        int level;
        ArrayList<Integer> connectedNodeIndex;

        public Node (){
            connectedNodeIndex = new ArrayList<>();
            level = 0;
        }

        public void setPrevious(int index) {
            this.previousNodeIndex = index;
        }
    }

    /*My ring queue:
    *   1. enqueue: rear ++
    *   2. dequeue: front ++
    *   3. isEmpty*/
    static class MyQueue{
        private int[] array;
        private int front;
        private int rear;
        private int len;

        public MyQueue(){
            this.front = 0;
            this.rear = 0;
            this.array = new int[100000];
            this.len = 100000;
        }
        public boolean isEmpty (){
            return rear == front;
        }
        public boolean isFull (){
            return rear == len;
        }

        public int dequeue (){
            front ++;
            return array[front - 1];
        }

        public void enqueue (int value){

            array[rear] = value;
            rear ++;
        }
    }

    static int findMaxLength(Node[] array, int startIndex){
        int i = startIndex;
        MyQueue myQueue = new MyQueue();
        myQueue.enqueue(i);
        array[i].level = 0;
        array[i].setPrevious(-1);

        while (!myQueue.isEmpty()){
            i = myQueue.dequeue();
            int depth = array[i].level + 1;
            ArrayList<Integer> temp = array[i].connectedNodeIndex;
            int previous = array[i].previousNodeIndex;

            if(temp.size() > 1 || array[i].level == 0){
               for(int x : temp){
                   if (x != previous) {
                       myQueue.enqueue(x);
                       array[x].level = depth;
                       array[x].setPrevious(i);
                   }
               }
            }
        }

        return i;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();

        for(int i = 0; i< numCases; i ++){
            int numNode = sc.nextInt();
            int numEdge = numNode - 1;
            Node[] array = new Node[numNode];
            for (int i1 = 0; i1 < numNode; i1 ++){
                array[i1] = new Node();
            }

            for (int j = 0; j < numEdge; j ++){
                int node1 = sc.nextInt();
                int node2 = sc.nextInt();
                node1--; node2--;
                array[node1].connectedNodeIndex.add(node2);
                array[node2].connectedNodeIndex.add(node1);
            }

            int deepest1 = findMaxLength(array, 0);
//            System.out.println(deepest1);
            int wide = array[findMaxLength(array, deepest1)].level;
            System.out.println(wide);
        }
    }
}
