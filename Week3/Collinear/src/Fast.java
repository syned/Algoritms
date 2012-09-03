import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: syned
 * Date: 01.09.12
 * Time: 9:22
 * To change this template use File | Settings | File Templates.
 */
public class Fast {
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



        for (int i = 0; i < N; i++) {
            Point p = points[i];

            int j = i+1;

            Arrays.sort(points, j, N, p.SLOPE_ORDER);
            
            while (j < N) {
                double slope = p.slopeTo(points[j]);
                int count = 1;
                double nextSlope;
                for (; j+count < N; count++) {
                    nextSlope = p.slopeTo(points[j+count]);
                    if (slope != nextSlope)
                        break;
                }

                if (count >= 3) {
                    boolean alreadyCollinear = false;
                    for (int k = 0; k < i; k++) {
                        double backSlope = p.slopeTo(points[k]);
                        if (backSlope == slope)
                            alreadyCollinear = true;
                    }

                    if (!alreadyCollinear) {
                        Point[] collinearPoints = new Point[count+1];
                        collinearPoints[0] = p;
                        for (int k = j, m = 1; k < j + count; k++, m++) {
                            collinearPoints[m] = points[k];
                        }

                        Arrays.sort(collinearPoints);
                        String s = collinearPoints[0].toString();
                        for (int k = 1; k < collinearPoints.length; k++) {
                            s += " -> " + collinearPoints[k].toString();
                        }

                        StdOut.println(s);
                        collinearPoints[0].drawTo(collinearPoints[collinearPoints.length-1]);
                    }
                }

                j += count;
            }
        }

//        display to screen all at once
        StdDraw.show(0);
    }
}
