import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: syned
 * Date: 09.09.12
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class Solver {

    private boolean isSolvable;    
    private PriorityBoard lastNode;

    private Comparator<PriorityBoard> comparator;

    private class PriorityBoard {
        private Board board;
        private int manhattan;
        private int moves;
        private PriorityBoard previous;

        public PriorityBoard(Board board, int moves, PriorityBoard previous) {
            this.board = board;
            this.moves = moves;
            this.manhattan = board.manhattan();
            this.previous = previous;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append("manhattan="+manhattan);
            s.append("\n");
            s.append(board.toString());
            return s.toString();
        }
    }

    public Solver(Board initial) {
        comparator = new Comparator<PriorityBoard>() {
            @Override
            public int compare(PriorityBoard o1, PriorityBoard o2) {
                int priority1 = o1.moves + o1.manhattan;
                int priority2 = o2.moves + o2.manhattan;
                if (priority1 < priority2)
                    return -1;
                if (priority1 > priority2)
                    return 1;
                return 0;
            }
        };

        MinPQ<PriorityBoard> priorityQueue = new MinPQ<PriorityBoard>(4, comparator);
        MinPQ<PriorityBoard> twinQueue = new MinPQ<PriorityBoard>(4, comparator);

        lastNode = new PriorityBoard(initial, 0, null);
        PriorityBoard twinLastNode = new PriorityBoard(initial.twin(), 0, null);

        while (!lastNode.board.isGoal() && !twinLastNode.board.isGoal()) {
            for (Board b : lastNode.board.neighbors()) {
                if (lastNode.previous == null || !b.equals(lastNode.previous.board)) {
                    priorityQueue.insert(new PriorityBoard(b, lastNode.moves+1, lastNode));
                }
            }

            for (Board b : twinLastNode.board.neighbors()) {
                if (twinLastNode.previous == null || !b.equals(twinLastNode.previous.board)) {
                    twinQueue.insert(new PriorityBoard(b, twinLastNode.moves+1, lastNode));
                }
            }
            
            lastNode = priorityQueue.delMin();
            twinLastNode = twinQueue.delMin();
        }

        if (lastNode.board.isGoal()) {
            isSolvable = true;
        }

        if (twinLastNode.board.isGoal()) {
            isSolvable = false;
            lastNode = null;
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (lastNode == null)
            return -1;
        return lastNode.moves;
    }

    public Iterable<Board> solution() {
        if (lastNode == null)
            return null;
        Stack<Board> stack = new Stack<Board>();
        PriorityBoard last = lastNode;
        while (last != null) {
            stack.push(last.board);
            last = last.previous;
        }
        return stack;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
