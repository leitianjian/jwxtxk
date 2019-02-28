package Lecture01;

import java.awt.*;

public class GlassPicture {
    private Picture picture;
    private int width;
    private int height;

    public GlassPicture (String path){
        this.picture = new Picture(path);
        this.width   = picture.width();
        this.height  = picture.height();
    }

    public void glassPicture (){
        Picture finPicture = new Picture(width, height);
        for (int col = 0; col < width; ++ col){
            for (int row = 0; row < height; ++ row){
                finPicture.set(col, row, getNeighborColor(col, row));
            }
        }
        finPicture.show();
    }

    /**
     * Returning the random neighborhood pixel which the coordinate diff less than 5
     *
     * @param col the column index
     * @param row the row index
     * @return the color of random neighbors' color
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     * */
    private Color getNeighborColor (int col, int row){
        // Calculate the margin of the neighborhood
        int leftMargin   = (col >= 5)         ? 5 : col;
        int rightMargin  = (width - col) > 5  ? 5 : width - col - 1;
        int headMargin   = (row >= 5)         ? 5 : row;
        int bottomMargin = (height - row) > 5 ? 5 : height - row - 1;

        // Calculate the dimension and the total pixel number
        int neighborWid  = leftMargin + rightMargin + 1;
        int neighborHei  = headMargin + bottomMargin + 1;
        int pixelCount   = neighborHei * neighborWid;

        // Get the random index to be the index of the pixel we choose
        int randNum      = (int) (Math.random() * pixelCount);

        return picture.get(randNum % neighborWid + col - leftMargin, randNum / neighborWid + row - headMargin);
    }

    public static void main(String[] args) {
        GlassPicture g = new GlassPicture("images.jpeg");
        g.glassPicture();
    }
}
