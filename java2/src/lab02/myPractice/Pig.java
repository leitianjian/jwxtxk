package lab02.myPractice;

public class Pig extends Animal{

    public Pig(int speed, String fileName){
        super.speed = speed;
        super.imageFileName = fileName;
    }

    @Override
    public void raceDraw(double sx, double sy, double time, int scaledWid, int scaledHei) {
        int count = (int) time / 4;
        double reminder = time % 4;
        double x = sx + speed * (3 * count + ((reminder < 3) ? reminder : 3));
        StdDraw.picture(x, sy, imageFileName, scaledWid, scaledHei);
    }
}
