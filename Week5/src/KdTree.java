/**
 * Created by IntelliJ IDEA.
 * User: syned
 * Date: 16.09.12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class KdTree {

    private class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        private int level;

        public Node(Point2D p, int level)
        {
            this.p = p;
            this.level = level;
        }
    }
    
    private Node rootNode;
    private int size = 0;
    private Point2D nearest;
    private double distanceToNearest;

    private Node insert(Node node, Point2D p, int level) {
        
        if (node == null)
        {
            size++;
            return new Node(p, level);
        }

        if (p.equals(node.p))
        {
            return node;
        }

        int cmp;
        cmp = compare(p, node.p, level);

        if (cmp < 0)
            node.lb = insert(node.lb, p, level+1);
        else
            node.rt = insert(node.rt, p, level+1);
        return node;
    }

    private int compare(Point2D p1, Point2D p2, int level) {
        int cmp;
        if (level % 2 == 0) {
            cmp = Point2D.X_ORDER.compare(p1, p2);
        }
        else {
            cmp = Point2D.Y_ORDER.compare(p1, p2);
        }
        return cmp;
    }

    public KdTree() {
    }

    public boolean isEmpty() {
        return rootNode == null;
    }
    public int size() {
        return size;
    }
    public void insert(Point2D p) {
        rootNode = insert(rootNode, p, 0);
    }

    private boolean contains(Node node, Point2D p, int level) {
        if (node == null)
            return false;

        if (p.equals(node.p))
            return true;

        if (compare(p, node.p, level) < 0)
            return contains(node.lb, p, level+1);
        else
            return contains(node.rt, p, level+1);
    }

    public boolean contains(Point2D p) {
        return contains(rootNode, p, 0);
    }
    public void draw() {
    }
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();
        
        range(queue, rootNode, rect, 0);
        
        return queue;
    }

    private void range(Queue<Point2D> queue, Node node, RectHV rect, int level)
    {
        if (node == null)
            return;

        if (rect.contains(node.p))
            queue.enqueue(node.p);

        if (level % 2 == 0) {
            if (rect.xmin() <= node.p.x())
                range(queue, node.lb, rect, level+1);
            if (rect.xmax() >= node.p.x())
                range(queue, node.rt, rect, level+1);
        }
        else {
            if (rect.ymin() <= node.p.y())
                range(queue, node.lb, rect, level+1);
            if (rect.ymax() >= node.p.y())
                range(queue, node.rt, rect, level+1);
        }
    }

    public Point2D nearest(Point2D p) {
        if (rootNode == null)
            return null;
        
        distanceToNearest = Double.MAX_VALUE;
        
        find(rootNode, p, 0);

        return nearest;
    }

    private void find(Node node, Point2D p, int level) {
        if (node == null)
            return;

        double distance = p.distanceSquaredTo(node.p);
        if (distance < distanceToNearest) {
            distanceToNearest = distance;
            nearest = node.p;
        }

        double distanceToSquare;
        if (compare(p, node.p, level) < 0) {
            find(node.lb, p, level+1);

            if (level % 2 == 0) {
                distanceToSquare = p.x() - node.p.x();
            }
            else {
                distanceToSquare = p.y() - node.p.y();
            }

            if (distanceToSquare * distanceToSquare < distanceToNearest)
                find(node.rt, p, level+1);
        }
        else {
            find(node.rt, p, level+1);

            if (level % 2 == 0) {
                distanceToSquare = p.x() - node.p.x();
            }
            else {
                distanceToSquare = p.y() - node.p.y();
            }

            if (distanceToSquare * distanceToSquare < distanceToNearest)
                find(node.lb, p, level+1);
        }
    }

    public static void main(String[] args) {
        KdTree tree = new KdTree();
        
        Point2D[] points = new Point2D[10];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(1, i);
            tree.insert(points[i]);
        }               
        
        for (int i = 0; i < points.length; i++)
            if (!tree.contains(points[i]))
                System.out.println(false);
    }
}
