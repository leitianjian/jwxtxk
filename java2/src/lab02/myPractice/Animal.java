package lab02.myPractice;

public abstract class Animal {
    protected int speed;
    protected String imageFileName;

    /**
     * Draw the race image of an specific animal
     * @param sx         x start point
     * @param sy         y start point
     * @param time       running time of the animal
     * @param scaledWid  the width of the picture
     * @param scaledHei  the height of the picture
     * */
    abstract public void raceDraw (double sx, double sy, double time, int scaledWid, int scaledHei);
}
