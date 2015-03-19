import java.util.Arrays;

/**
 * Brute.java
 * Mar 8, 2015
 * Igal Israilevich
 */

/**
 * @author Igal Israilevich
 *
 */
public class Brute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stopwatch stopwatch = new Stopwatch();
		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		// read in the input
		String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();
		Point[] arr = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);

			p.draw();
			arr[i] = p;
		}

		for (int i = 0; i < N; i++) {

			for (int j = i + 1; j < N; j++) {
				double slope1 = arr[i].slopeTo(arr[j]);
				for (int x = j + 1; x < N; x++) {					
					double slope2 = arr[i].slopeTo(arr[x]);
					if(slope1 != slope2) continue;
					for (int y = x + 1; y < N; y++) {
						double slope3 = arr[i].slopeTo(arr[y]);						
						if(slope1 == slope2 && slope1 == slope3){
							Point[] line = {arr[i],arr[j],arr[x],arr[y]};
							
							Arrays.sort(line);
							System.out.print(line[0] + "->" + line[1] + "->" + line[2] + "->" + line[3]);
							System.out.println();
							line[0].drawTo(line[3]);
						}

					}
				}
			}

		}
		
        // display to screen all at once
        StdDraw.show(0);
        double seconds = stopwatch.elapsedTime();
        System.out.println("takes time " + seconds + " seconds");


	}
}
