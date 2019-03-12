package lab02.myPractice;

public class Rabbit extends Animal{

    public Rabbit (int speed, String fileName){
        super.speed = speed;
        super.imageFileName = fileName;
    }

    @Override
    public void raceDraw(double sx, double sy, double time, int scaledWid, int scaledHei) {

        int count = (int) time / 4;
        double reminder = time % 4;
        double x = sx + speed * (2 * count + ((reminder < 2) ? reminder : 2));

        StdDraw.picture(x, sy, imageFileName, scaledWid, scaledHei);
    }
}
