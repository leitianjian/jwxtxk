package Lecture01;

import java.awt.*;

public class SwirlPicture {
    private double rotateRadian;
    private Picture picture;
    private int width;
    private int height;
    private double centerWid;
    private double centerHei;

    public SwirlPicture (String path, double rotateAngle){
        this.rotateRadian  = angle2radian(rotateAngle);
        this.picture       = new Picture(path);
        this.width         = picture.width();
        this.height        = picture.height();
        this.centerHei     = height / 2.0;
        this.centerWid     = width / 2.0;
    }

    /**
     * The original algorithm which may cause a lot of black dots
     * */
    public void rotate (){
        Picture finPic = new Picture(width, height);

        for (int col = 0; col < width; ++ col){
            for (int row = 0; row < height; ++ row){
                int newCol = (int) ((col - centerWid) * Math.cos(calAngle(rotateRadian, col, row)) -
                                    (row - centerHei) * Math.sin(calAngle(rotateRadian, col, row)) + centerWid);
                int newRow = (int) ((col - centerWid) * Math.sin(calAngle(rotateRadian, col, row)) +
                                    (row - centerHei) * Math.cos(calAngle(rotateRadian, col, row)) + centerHei);

                if (newCol < width && newRow < height && newCol > 0 && newRow > 0){
                    finPic.set(newCol, newRow, picture.get(col, row));
                }
            }
        }
        finPic.show();
    }
    private double calAngle (double rotateRadian, int col, int row){
        return rotateRadian - Math.PI / 256 * Math.sqrt((col - centerWid) * (col - centerWid) +
                                                        (row - centerHei) * (row - centerHei));
    }

    public void rotateInverse (){
        Picture finPic = new Picture(width, height);

        for (int newCol = 0; newCol < width; ++ newCol){
            for (int newRow = 0; newRow < height; ++ newRow){
                int col = (int) ((newCol - centerWid) * Math.cos(calAngleInverse(rotateRadian, newCol, newRow)) -
                                 (newRow - centerHei) * Math.sin(calAngleInverse(rotateRadian, newCol, newRow)) + centerWid);
                int row = (int) ((newCol - centerWid) * Math.sin(calAngleInverse(rotateRadian, newCol, newRow)) +
                                 (newRow - centerHei) * Math.cos(calAngleInverse(rotateRadian, newCol, newRow)) + centerHei);
                if (col < width && row < height && row > 0 && col > 0){
                    finPic.set(newCol, newRow, picture.get(col, row));
                } else {
                    finPic.set(newCol, newRow, Color.BLACK);
                }
            }
        }
        finPic.show();
    }
    private double calAngleInverse (double rotateRadian, int col, int row){
        return -rotateRadian + Math.PI / 256 * Math.sqrt((col - centerWid) *(col - centerWid) +
                                                         (row - centerHei) * (row - centerHei));
    }

    private double angle2radian (double angle){
        return - (angle / 180) * Math.PI;
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            SwirlPicture r = new SwirlPicture(args[0], Integer.parseInt(args[1]));
            r.rotate();
            r.rotateInverse();
        } else {
            System.out.println("Usage: java -classpath . SwirlPicture [fileName] [rotationDegree]");
        }
    }
}
