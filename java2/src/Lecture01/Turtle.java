package Lecture01;

// this class source code is directly copy from the Professor MingXin He's ppt.
public class Turtle {
    private double x, y;
    private double angle;

    public Turtle (double x0, double y0, double angle){
        this.x     = x0;
        this.y     = y0;
        this.angle = angle;
    }

    public void turnLeft (double delta){
        angle += delta;
    }

    public void goForward (double d){
        double oldX = x;
        double oldY = y;
        x += d * Math.cos(Math.toRadians(angle));
        y += d * Math.sin(Math.toRadians(angle));
        StdDraw.line(oldX, oldY, x, y);
    }

    public static void main(String[] args) {
        Turtle turtle = new Turtle(0.5, 0.5, 0);
        turtle.goForward(0.5); turtle.turnLeft(120);
        turtle.goForward(0.5); turtle.turnLeft(120);
        turtle.goForward(0.5); turtle.turnLeft(120);
    }
}
