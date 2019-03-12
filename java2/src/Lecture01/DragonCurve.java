package Lecture01;

public class DragonCurve {
    private Turtle turtle;
    private final double rootHalf = 1 / Math.sqrt(2);
    private int order;
    private double length;
    private int flag = 1; // control the inverse printout

    public DragonCurve (int order){
        // length and order should in pair
        this.order  = order;
        this.length = 0.3;
        // initialize the turtle at point (0.5, 0.5) and direction is determine by order
        this.turtle = new Turtle(0.5, 0.5, order * 90);
    }

    /**
     * Draw the dragon curve in a given order
     * */
    public void dragon (){
        this.flag = 1;
        dragonCurveRecursion(order, length, 1);
    }

    /**
     * Draw the dragon curve recursively
     * @param order  the order of the dragon curve
     * @param length the total length of the dragon curve
     * @param sign   the recursive flag
     * */
    private void dragonCurveRecursion (int order, double length, int sign) {
        if (order == 0){
            turtle.goForward(length);
        } else { // while in the same order the turn of main turn is inverse
            dragonCurveRecursion(order - 1, length * rootHalf, flag );
            turtle.turnLeft(sign * (-90));
            dragonCurveRecursion(order - 1, length * rootHalf, -flag);
        }
    }

    public void nogard () {
        this.flag = -1;
        dragonCurveRecursion(order, length, flag);
    }

    public static void main(String[] args) {
        if (args.length == 1){
            int order = Integer.parseInt(args[0]);
            DragonCurve d = new DragonCurve(order);
            d.dragon();

//            DragonCurve d1 = new DragonCurve(order);
//            d1.nogard();
        } else {
            System.out.println("Usage : java -classpath . DragonCurve [order]");
        }
    }
}
