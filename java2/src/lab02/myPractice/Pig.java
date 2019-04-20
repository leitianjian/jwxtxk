package lab02.myPractice;

import Lecture01.Picture;

public class Pig extends Animal{
//    private Picture picture;

    public Pig(int speed, String fileName){
        super.speed = speed;
        super.imageFileName = fileName;
//        this.picture = new Picture(imageFileName);
    }

    @Override
    public void raceDraw(double sx, double sy, double time, int scaledWid, int scaledHei) {
        int count = (int) time / 4;
        double reminder = time % 4;
        double x = sx + speed * (3 * count + ((reminder < 3) ? reminder : 3));
        StdDraw.picture(x, sy, imageFileName, scaledWid, scaledHei);
    }

//    public int getPictureWidth() {
//        return picture.width();
//    }
//
//    public int getPictureHeight() {
//        return picture.height();
//    }

}
