package lab02.myPractice;

import java.util.ArrayList;

class CFG {
        public final static int DEFAULT_CANVAS_WIDTH = 400;

        public final static int DEFAULT_CANVAS_HEIGHT = 400;

        public final static int DEFAULT_SCALE_MIN = -1000;

        public final static int DEFAULT_SCALE_MAX = 1000;
}


public class AnimalRace {
    // pause: 25, speed divide by 4
    public static void main(String[] args) {
        StdDraw.setCanvasSize(CFG.DEFAULT_CANVAS_WIDTH, CFG.DEFAULT_CANVAS_HEIGHT);
        StdDraw.setScale(CFG.DEFAULT_SCALE_MIN, CFG.DEFAULT_SCALE_MAX);

        double time = 0;
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Rabbit(80, "rabbit.png"));
        animals.add(new Turtle (10, "turtle.png"));
        animals.add(new Pig (40, "pig.png"));

        StdDraw.enableDoubleBuffering();
        double x = 50;

        while (true){
            StdDraw.clear();
            for (int i = 0; i < animals.size(); ++ i){
                animals.get(i).raceDraw(-900, 1000 - 500 * (i + 1), time, 500, 600);
            }
            StdDraw.show();
            StdDraw.pause(25);
            time += 0.25;
        }
    }
}
