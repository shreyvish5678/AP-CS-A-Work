import java.util.Scanner;
import java.io.PrintWriter;

public class USMap {
	public static void main(String[] args) {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
		StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.GRAY);
        Scanner infile = FileUtils.openToRead("cities.txt");
        double xVal, yVal;
		while (infile.hasNext()) {
			yVal = infile.nextDouble();
			xVal = infile.nextDouble();
			infile.nextLine();
			StdDraw.point(xVal, yVal);
		}
	}
}
