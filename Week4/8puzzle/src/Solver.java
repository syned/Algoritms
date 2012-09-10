import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: syned
 * Date: 09.09.12
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class Solver {

    private boolean isSolvable;

    public Solver(Board initial) {
       Board current initial
    }

    public boolean isSolvable() {
        return false;
    }

    public int moves() {
        return 0;
    }

    public Iterable<Board> solution() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
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
