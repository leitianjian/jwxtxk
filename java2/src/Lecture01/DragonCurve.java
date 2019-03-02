package Lecture01;

public class DragonCurve {
    private Turtle turtle;
    private final double rootHalf = 1 / Math.sqrt(2);
    private int order;
    private double length;

    public DragonCurve (int order, double length){
        // length and order should in pair
        this.order  = order;
        this.length = length;
        // initialize the turtle at point (0.5, 0.5) and direction is determine by order
        this.turtle = new Turtle(0.5, 0.5, order * 90);
    }

    /**
     * Draw the dragon curve in a given order
     * */
    public void dragonCurve (){
        dragonCurveRecursion(order, length, 1);
    }

    private void dragonCurveRecursion (int order, double length, int sign) {
        if (order == 0){
            turtle.goForward(length);
        } else { // while in the same order the turn of main turn is inverse
            dragonCurveRecursion(order - 1, length * rootHalf, 1);
            turtle.turnLeft(sign * (-90));
            dragonCurveRecursion(order - 1, length * rootHalf, -1);
        }
    }

    public static void main(String[] args) {
        DragonCurve d = new DragonCurve(15, 0.3);
        d.dragonCurve();
    }
}
