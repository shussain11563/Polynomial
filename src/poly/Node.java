public class Node {
	// draws a diamond centered at (x,y) with specified height that gets scaled
	// by some random factor
	private static void diamond(double x, double y, double height) {
		double[] xCoordinates = { x, x - height / 4, x, x + height / 4 };
		double[] yCoordinates = { y - height / 2, y, y + height / 2, y };
		Transform2D.scale(xCoordinates, yCoordinates, 2 * Math.random());
		StdDraw.filledPolygon(xCoordinates, yCoordinates);
	}
 
	// surrounds the specified point (x,y) with diamonds of a certain height
	// and specified density
	private static void starryNight(int n, double x, double y, double height, double density) {
		if (n == 0) return;
		else {
			StdDraw.setPenColor(Color.yellow);
			diamond(x, y, height);
			starryNight(n - 1, x, y + density, height / 2, density / 2);
			starryNight(n - 1, x, y - density, height / 2, density / 2);
			starryNight(n - 1, x + density, y, height / 2, density / 2);
			starryNight(n - 1, x - density, y, height / 2, density / 2);
		}
	}
 
	// sets the scale, background, and takes in the depth of the recursion
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		StdDraw.setScale(-5.0, +5.0);
		StdDraw.clear(Color.black);
		starryNight(n, 0, 0, 1, 1);
	}
 }