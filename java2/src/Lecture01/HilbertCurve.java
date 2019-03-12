package Lecture01;

public class HilbertCurve {
    private String hilbertStr;
    private int order;
    private double length;

    public HilbertCurve (int order) {
        this.hilbertStr = "a";
        this.order      = order;
        this.length     =  2.0 / (order * order * order); // control the length of one step
    }

    /**
     * Algorithm: rewrite system, which means that the steps
     *     where the turtle goes will can be write into some recursive formula
     *     ref: https://codegolf.stackexchange.com/questions/100335/draw-the-hilbert-curve*/
    public void draw () {
        for (int i = 0; i < order; ++ i){
            StringBuilder temp = new StringBuilder();
            for (char c : hilbertStr.toCharArray()){
                if (c == 'a'){
                    temp.append("-bf+afa+fb-");
                } else if (c == 'b'){
                    temp.append("+af-bfb-fa+");
                } else {
                    temp.append(c);
                }
            }
            hilbertStr = temp.toString();
        }

        Turtle turtle = new Turtle(0.01, 0.01, 0);
        for (char c : hilbertStr.toCharArray()){
            switch (c) {
                case '-' :
                    turtle.turnLeft(90);
                    break;
                case '+' :
                    turtle.turnLeft(270);
                    break;
                case 'f' :
                    turtle.goForward(length);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            HilbertCurve h = new HilbertCurve(Integer.parseInt(args[0]));
            h.draw();
        } else {
            System.out.println("Usage: java -classpath . HilbertCurve [orderNum].");
        }
    }
}
