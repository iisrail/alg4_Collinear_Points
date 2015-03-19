/**
 * TestBoth.java
 * Mar 13, 2015
 * Igal Israilevich
 */

/**
 * @author Igal Israilevich
 *
 */
public class TestBoth {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stopwatch stopwatch = new Stopwatch();
		Brute.main(args);
        double seconds = stopwatch.elapsedTime();
        System.out.println("Brute takes time " + seconds + " seconds");
        stopwatch = new Stopwatch();
		Fast.main(args);
		seconds = stopwatch.elapsedTime();
		System.out.println("Fast takes time " + seconds + " seconds");

	}

}
