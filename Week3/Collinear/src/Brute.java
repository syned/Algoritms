import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: syned
 * Date: 01.09.12
 * Time: 8:48
 * To change this template use File | Settings | File Templates.
 */
public class Brute {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }

        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++)
                    for (int m = k+1; m < N; m++) {
                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[i].slopeTo(points[k]);
                        double slope3 = points[i].slopeTo(points[m]);

                        if (slope1 == slope2 && slope1 == slope3) {
                            Point[] collinearPoints = new Point[4];
                            collinearPoints[0] = points[i];
                            collinearPoints[1] = points[j];
                            collinearPoints[2] = points[k];
                            collinearPoints[3] = points[m];

                            Arrays.sort(collinearPoints);

                            StdOut.println(collinearPoints[0] + " -> " + collinearPoints[1] + " -> " + collinearPoints[2] + " -> " + collinearPoints[3]);
                            collinearPoints[0].drawTo(collinearPoints[3]);
                        }
                    }
        
        // display to screen all at once
        StdDraw.show(0);
    }
}
