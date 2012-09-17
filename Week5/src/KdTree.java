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

        public Node(Point2D p)
        {
            this.p = p;
        }
    }
    
    private Node rootNode;
    private int size = 0;
    private int level = -1;

    private Node insert(Node node, Point2D p) {
        if (node == null)
            return new Node(p);

        level++;

        int cmp;
        cmp = compare(p, node.p, level);
        if (cmp < 0)
            node.lb = insert(node.lb, p);
        else if (cmp > 0)
            node.rt = insert(node.rt, p);
        else
            node.p = p;
        return node;
    }

    private int compare(Point2D p1, Point2D p2, int level) {
        int cmp;
        if (level % 2 == 0)
            cmp = Point2D.X_ORDER.compare(p1, p2);
        else
            cmp = Point2D.Y_ORDER.compare(p1, p2);
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
        rootNode = insert(rootNode, p);
        level = -1;
    }

    public boolean contains(Point2D p) {
        Node node = rootNode;
        int level = 0;
        while (node != null) {
            int cmp = compare(p, node.p, level);
            if (cmp > 0)
                node = node.lb;
            else if (cmp < 0)
                node = node.rt;
            else
                return true;
        }
        return false;
    }
    public void draw() {
    }
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();
        return queue;
    }
    public Point2D nearest(Point2D p) {
        if (rootNode == null)
            return null;
        Node current = rootNode;
        Point2D nearest = current.p;

        while (current != null) {

        }

        return nearest;
    }
}
