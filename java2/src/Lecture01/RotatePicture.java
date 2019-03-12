package Lecture01;

import java.awt.*;

public class RotatePicture {
    private double rotateRadian;
    private Picture picture;
    private int width;
    private int height;
    private double centerWid;
    private double centerHei;

    public RotatePicture (String path, double rotateAngle){
        this.rotateRadian  = angle2radian(rotateAngle);
        this.picture       = new Picture(path);
        this.width         = picture.width();
        this.height        = picture.height();
        this.centerWid     = width / 2.0;
        this.centerHei     = height / 2.0;
    }

    /**
     * The original version of rotation algorithm, which can cause a lot of black dot in the new picture
     * */
    public void rotate (){
        Picture finPic = new Picture(width, height);

        for (int col = 0; col < width; ++ col){
            for (int row = 0; row < height; ++ row){
                int newCol = (int) ((col - centerWid) * Math.cos(rotateRadian) -
                                    (row - centerHei) * Math.sin(rotateRadian) +
                                     centerWid);
                int newRow = (int) ((col - centerWid) * Math.sin(rotateRadian) +
                                    (row - centerHei) * Math.cos(rotateRadian) +
                                     centerHei);

                if (newCol < width && newRow < height && newCol > 0 && newRow > 0){
                    finPic.set(newCol, newRow, picture.get(col, row));
                }
            }
        }
        picture.show();
        finPic.show();
    }

    /**
     * The inverse version of the rotate algorithm, see the readme.md doc for more information
     * */
    public void rotateInverse (){
        Picture finPic = new Picture(width, height);

        for (int newCol = 0; newCol < width; ++ newCol){
            for (int newRow = 0; newRow < height; ++ newRow){
                int col = (int) ((newCol - centerWid) * Math.cos(-rotateRadian) -
                                 (newRow - centerHei) * Math.sin(-rotateRadian) +
                                  centerWid);
                int row = (int) ((newCol - centerWid) * Math.sin(-rotateRadian) +
                                 (newRow - centerHei) * Math.cos(-rotateRadian) +
                                  centerHei);

                if (col < width && row < height && col > 0 && row > 0){
                    finPic.set(newCol, newRow, picture.get(col, row));
                } else {
                    finPic.set(newCol, newRow, Color.BLACK);
                }
            }
        }
        finPic.show();
    }

    private double angle2radian (double angle){
        return - (angle / 180) * Math.PI;
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            RotatePicture r = new RotatePicture(args[0], Integer.parseInt(args[1]));
            r.rotate();
            r.rotateInverse();
        } else {
            System.out.println("Usage: java -classpath . RotatePicture [fileName] [rotationDegree]");
        }
    }
}
