package lab02.myPractice;

import lab02.Picture;

public class Turtle extends Animal{

    public Turtle (int speed, String fileName){
        super.speed = speed;
        super.imageFileName = fileName;
    }

    @Override
    public void raceDraw(double sx, double sy, double time, int scaledWid, int scaledHei) {
        double x = sx + time * speed;
        StdDraw.picture(x, sy, imageFileName, scaledWid, scaledHei);
    }
}
