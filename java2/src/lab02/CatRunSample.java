package lab02;

class CFG {
    public final static int DEFAULT_CANVAS_WIDTH = 400;

    public final static int DEFAULT_CANVAS_HEIGHT = 400;
    public final static int DEFAULT_SCALE_MIN = -1000;
    public final static int DEFAULT_SCALE_MAX = 1000;
}

public class CatRunSample {

	public static void main(String[] args) {
		StdDraw.setCanvasSize(CFG.DEFAULT_CANVAS_WIDTH, CFG.DEFAULT_CANVAS_HEIGHT);
		StdDraw.setScale(CFG.DEFAULT_SCALE_MIN, CFG.DEFAULT_SCALE_MAX);

		String imageFileName = "cat.png";
		Picture picture = new Picture(imageFileName);
		// int scaledHeight = picture.height();
		// int scaledWidth = picture.width();

		int scaledHeight = CFG.DEFAULT_SCALE_MAX;
		int scaledWidth = picture.width() * CFG.DEFAULT_SCALE_MAX / picture.height();

		int speed = 80;
		StdDraw.enableDoubleBuffering();
		double y = 0;
		double x = CFG.DEFAULT_SCALE_MIN + 0.5 * scaledHeight;
		for (; x < CFG.DEFAULT_SCALE_MAX - 0.5 * scaledHeight; ) {
			x += speed;
			StdDraw.clear();
			StdDraw.picture(x, y, imageFileName, scaledWidth, scaledHeight);
			StdDraw.show();
			StdDraw.pause(100);

		}
	}

}
