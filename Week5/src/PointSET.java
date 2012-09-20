/**
 * Created by IntelliJ IDEA.
 * User: syned
 * Date: 16.09.12
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class PointSET {

    private SET<Point2D> bst;

    public PointSET() {
        bst = new SET<Point2D>();
    }
    public boolean isEmpty() {
        return bst.isEmpty();
    }
    public int size() {
        return bst.size();
    }
    public void insert(Point2D p) {
        if (!contains(p))
            bst.add(p);
    }
    public boolean contains(Point2D p) {
        return bst.contains(p);
    }
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p : bst) {
            p.draw();
        }
    }
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D p : bst)
            if (rect.contains(p))
                queue.enqueue(p);
        return queue;
    }
    public Point2D nearest(Point2D p) {
        Point2D nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point2D point : bst) {
            double currentDistance = point.distanceSquaredTo(p);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                nearest = point;
            }
        }

        return nearest;
    }
}
