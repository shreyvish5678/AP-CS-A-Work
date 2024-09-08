import java.util.Scanner;
import java.io.PrintWriter;

/**
 * 	Cities in the US using StdDraw
 * 	
 * 	@author Shrey Vishen
 * 	@since September 8, 2024
 */

public class USMap {
	/**
	 * 	Creates the window, and graphs the cities on the window
	 * 	
	 */
	public static void main(String[] args) {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
		StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.GRAY);
        Scanner infile = FileUtils.openToRead("cities.txt");
        
        double xVal, yVal;
        String rest, city;
        int total = 0;
		while (infile.hasNext()) {
			yVal = infile.nextDouble();
			xVal = infile.nextDouble();
			rest = infile.nextLine();
			StdDraw.point(xVal, yVal);
			total += 1;
		}
		double[][] location = new double[total][2];
		String[] cities = new String[total];
		int index = 0;
		infile = FileUtils.openToRead("cities.txt");
		while (infile.hasNext()) {
			yVal = infile.nextDouble();
			xVal = infile.nextDouble();
			rest = infile.nextLine();
			city = rest.split(" , ")[0].strip();
			cities[index] = city;
			location[index][0] = xVal;
			location[index][1] = yVal;
			index += 1;	
		}
		infile = FileUtils.openToRead("bigCities.txt");
		int pop = 0;
		index = 0;
		while (infile.hasNext()) {
			index += 1;
			infile.nextInt();
			rest = infile.nextLine();
			city = rest.split(" , ")[0].strip();
			pop = Integer.parseInt(rest.split(" , ")[1].strip().split(" ")[1].strip());
			if (index <= 10) {
				StdDraw.setPenColor(StdDraw.RED);
			}
			else {
				StdDraw.setPenColor(StdDraw.BLUE);
			}
			StdDraw.setPenRadius(0.6 * (Math.sqrt(pop)/18500));
			for (int i = 0; i < cities.length; i++) {
				if (city.equals(cities[i])) {
					StdDraw.point(location[i][0], location[i][1]);
				}
			}
		}
	}
}
