/**
 * Point.java
 * Mar 8, 2015
 * Igal Israilevich
 */


import java.util.Comparator;

/**
 * @author Igal Israilevich
 *
 */
public class Point implements Comparable<Point> {
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
	/**
	 * compare points by slope to this point
	 */
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

		@Override
		public int compare(final Point o1, final Point o2) {
			// TODO Auto-generated method stub
			double slope1 = slopeTo(o1);
			double slope2 = slopeTo(o2);
			if (slope1 < slope2) {
				return -1;
			} else if (slope1 > slope2) {
				return 1;
			} else {
				// the same slope 
				//1.sort by y
				if(o1.y < o2.y){
					return -1;
				}
				else if(o1.y > o2.y){
					return 1;
				}
				else{
				//2.sort by x
					if(o1.x < o2.x){
						return -1;
					}
					else if(o1.x > o2.x){
						return 1;
					}
					else{
						return 0;
					}
				}
				
			}
		}

	}; //

	/**
	 * construct the point (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * draw this point
	 */
	public void draw() {
		StdDraw.setPenRadius(0.01);
		StdDraw.point(x, y);
	}

	/**
	 * draw the line segment from this point to that point
	 * 
	 * @param that
	 */
	public void drawTo(Point that) {
		StdDraw.setPenRadius(0.005);
		StdDraw.line(x, y, that.x, that.y);
	}

	/**
	 * string representation
	 */
	public String toString() {
		return "(" + x + ", " + y+")";
	}

	/**
	 * is this point lexicographically smaller than that point?
	 */
	public int compareTo(Point that) {
		if (y < that.y) {
			return -1;
		} else if (y > that.y) {
			return 1;
		} else {
			if (x < that.x) {
				return -1;
			} else if (x > that.x) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	/**
	 * the slope between this point and that point
	 * 
	 * @param that
	 * @return
	 */
	public double slopeTo(Point that) {
		int delY = that.y - y;
		int delX = that.x - x;
		if (delY == 0 && delX == 0) {
			return Double.MIN_VALUE;
		}
		if (delY == 0) {
			return 0.0;
		}
		if (delX == 0) {
			return Double.MAX_VALUE;
		}
		return delY / (double) delX;

	}



}