package Lecture01;

//import java.awt.*;
//import java.io.File;

public class RotatePicture {
//    private String path;
    private double rotateRadian;
    private Picture picture;

    public RotatePicture (String path, double rotateAngle){
//        this.path          = path;
        this.rotateRadian  = angle2radian(rotateAngle);
        this.picture       = new Picture(path);
    }

    public void rotate (){
        int width  = picture.width();
        int height = picture.height();
        double centerWid = width / 2.0;
        double centerHei = height / 2.0;
        Picture finPic = new Picture(width, height);
        System.out.println(width + " " + height);

        for (int col = 0; col < width; ++ col){
            for (int row = 0; row < height; ++ row){
                int newCol = (int) ((col - centerWid) * Math.cos(rotateRadian) - (row - centerHei) * Math.sin(rotateRadian) + centerWid);
                int newRow = (int) ((col - centerWid) * Math.sin(rotateRadian) + (row - centerHei) * Math.cos(rotateRadian) + centerHei);

                if (newCol < width && newRow < height && newCol > 0 && newRow > 0){
//                    System.out.println(newCol + " " + newRow);
                    finPic.set(newCol, newRow, picture.get(col, row));
//                    finPic.set(col, row, picture.get(col, row));
                }
            }
        }
        picture.show();
        finPic.show();

//        return 0;
    }

    private double angle2radian (double angle){
        return - (angle / 180) * Math.PI;
    }

    public static void main(String[] args) {
        RotatePicture r = new RotatePicture("test2.png", 30);
        r.rotate();
    }
}
