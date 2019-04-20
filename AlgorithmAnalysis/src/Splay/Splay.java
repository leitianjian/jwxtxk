package Splay;


class Node {
    int value;
    int leftWeight;
    int rightWeight;
    Node left;
    Node right;

    public Node (int value, int leftWeight, int rightWeight, Node left, Node right){
        this.value = value;
        this.leftWeight = leftWeight;
        this.rightWeight = rightWeight;
        this.left = left;
        this.right = right;
    }
}

class SplayTree {
    Node root;

}

public class Splay {

}
