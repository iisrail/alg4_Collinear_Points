import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fast.java
 * Mar 10, 2015
 * Igal Israilevich
 */

/**
 * 1.firstly we should to sort all points lexicographically from small y to big
 * y in order we'll be able to identify when order of the same Collinear Points
 * or their subsegments returns
 * 
 * 2. We'll sort points in loop every cycle according to slop with a new base
 * point
 * 
 * 3. In the case that we'll get a line (3 points or more with same slop) we'll
 * check whether the group have a point that less then base point in that case
 * it is only permutation of previous found line and therefore not will be
 * displayed
 * 
 * @author Igal Israilevich
 *
 */
public class Fast {
	public static void main(String[] args) {
		Stopwatch stopwatch = new Stopwatch();
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] arrPoints = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            
            p.draw();
            arrPoints[i]=p;
        }


		
		Arrays.sort(arrPoints);
		int len = arrPoints.length;
		Point basePoint;
		// Iteration can be done without 2 last points
		for (int i = 0; i < len - 2; i++) {
			basePoint = arrPoints[i];
			// copy all other points to a new array
			Point[] tmpArray = new Point[len - 1];
			int x = 0;
			for (int k = 0; k < len; k++) {
				if (i == k)
					continue;
				tmpArray[x++] = arrPoints[k];
			}
			// copy done

			// sort by slop
			Arrays.sort(tmpArray, basePoint.SLOPE_ORDER);
			// find groups of Collinear Points
			List<List<Point>> listGroups = getCollinearPoints(tmpArray, basePoint);
			printListGroups(listGroups);

		}
        // display to screen all at once
        StdDraw.show(0);
        double seconds = stopwatch.elapsedTime();
        System.out.println("takes time " + seconds + " seconds");

	}

	// find groups of Collinear Points sorted by slop to base point
	private static List<List<Point>> getCollinearPoints(Point[] points, Point basePoint) {
		int counter = 1;
		List<Point> group = null;
		Point prevPoint = points[0];
		double prevSlop = basePoint.slopeTo(prevPoint);
		List<List<Point>> listGroups = new ArrayList<>();

		for (int i = 1; i < points.length; i++) {
			Point currPoint = points[i];
			
			double slop = basePoint.slopeTo(currPoint);
			if (slop == prevSlop) {
				counter++;

				if (counter == 2) {
					// we found new group
					group = new ArrayList<>();
					group.add(basePoint);
					group.add(prevPoint);

				} else if (counter == 3) {
					listGroups.add(group);
				}

				group.add(currPoint);

			} else {
				prevSlop = slop;
				counter = 1;
				prevPoint = currPoint;
			}
		}

		// TODO eliminate redundant groups

		return listGroups;
	}



	private static void printListGroups(List<List<Point>> list) {
		for (List<Point> group : list) {
			//eliminate redundant groups
			if(!isRedundant(group)){
				printGroup(group);
				Point fPoint = group.get(0);
				Point lPoint = group.get(group.size()-1);
				fPoint.drawTo(lPoint);
			}
		}
	}
	
	private static boolean isRedundant(List<Point> group){
		Point basePoint = group.get(0);
		Point secPoint = group.get(1);
		if(basePoint.compareTo(secPoint)==1){
			return true;
		}
		else{
			return false;
		}
	}

	private static void printGroup(List<Point> group) {
		int size = group.size();
		for (int i = 0; i < size; i++) {
			
			Point point = group.get(i);
			if (i == group.size() - 1) {
				System.out.print(point);
			} else {
				System.out.print(point + " -> ");
			}
		}
		System.out.println();
	}

}
