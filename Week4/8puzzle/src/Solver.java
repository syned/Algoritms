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
    private Queue<Board> solution;
    private MinPQ<PriorityBoard> priorityQueue;
    private int moves = 0;

    private Comparator<PriorityBoard> comparator;

    private class PriorityBoard {
        private Board board;
        private int priority;

        public PriorityBoard(Board board, int moves) {
            this.board = board;
            this.priority = moves + board.manhattan();
        }
    }

    public Solver(Board initial) {
        comparator = new Comparator<PriorityBoard>() {
            @Override
            public int compare(PriorityBoard o1, PriorityBoard o2) {
                if (o1.priority < o2.priority)
                    return -1;
                if (o1.priority > o2.priority)
                    return 1;
                return 0;
            }
        };

        priorityQueue = new MinPQ<PriorityBoard>(4, comparator);
        solution = new Queue<Board>();

        Board current = initial;
        Board previous = null;
        moves = 0;
        solution.enqueue(current);
        while (!current.isGoal()) {
            for (Board b : current.neighbors()) {
                if (!b.equals(previous))
                    priorityQueue.insert(new PriorityBoard(b, moves));
            }
            moves++;
            previous = current;
            if (priorityQueue.isEmpty())
                break;
            current = priorityQueue.delMin().board;
            solution.enqueue(current);
            if (current.equals(initial)) {
                solution = null;
                break;
            }
        }

        isSolvable = current.isGoal();
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
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
