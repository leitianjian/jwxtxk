package Lecture01;

public class SwirlPicture {
    //    private String path;
    private double rotateRadian;
    private Picture picture;
    private double centerWid;
    private double centerHei;

    public SwirlPicture (String path, double rotateAngle){
//        this.path          = path;
        this.rotateRadian  = angle2radian(rotateAngle);
        this.picture       = new Picture(path);
        this.centerHei     = picture.height() / 2.0;
        this.centerWid     = picture.width() / 2.0;
    }

    public void rotate (){
        int width  = picture.width();
        int height = picture.height();

        Picture finPic = new Picture(width, height);
        System.out.println(width + " " + height);

        for (int col = 0; col < width; ++ col){
            for (int row = 0; row < height; ++ row){
                System.out.println(calAngle(rotateRadian,col, row));
                int newCol = (int) ((col - centerWid) * Math.cos(calAngle(rotateRadian, col, row)) - (row - centerHei) * Math.sin(calAngle(rotateRadian, col, row)) + centerWid);
                int newRow = (int) ((col - centerWid) * Math.sin(calAngle(rotateRadian, col, row)) + (row - centerHei) * Math.cos(calAngle(rotateRadian, col, row)) + centerHei);

                if (newCol < width && newRow < height && newCol > 0 && newRow > 0){
//                    System.out.println(newCol + " " + newRow);
                    finPic.set(newCol, newRow, picture.get(col, row));
//                    finPic.set(col, row, picture.get(col, row));
                }
            }
        }
//        picture.show();
        finPic.show();

//        return 0;
    }

    private double calAngle (double rotateRadian, int col, int row){
        return rotateRadian - Math.PI / 256 * Math.sqrt((col - centerWid) * (col - centerWid) + (row - centerHei) * (row - centerHei));
    }

    private double angle2radian (double angle){
        return - (angle / 180) * Math.PI;
    }

    public static void main(String[] args) {
        RotatePicture r2 = new RotatePicture("images.jpeg", 30);
        SwirlPicture r = new SwirlPicture("images.jpeg", 30);
        r.rotate();
        r2.rotate();
    }
}
