import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: syned
 * Date: 09.09.12
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private short[][] blocks;
    private int N;
    private int hamming;
    private int manhattan;
    private boolean isGoal = true;
    private Queue<Board> queue;

    private int emptyCellRow = 0;
    private int emptyCellCol = 0;
    
    private int rightPlace(int i, int j) {
        return i*N + j + 1;
    }
    
    private int getRow(int value) {
        return (value - 1) / N;
    }
    
    private int getCol(int value) {
        return (value - 1) % N;
    }

    private int getDistanceToGoalPosition(int i, int j) {
        int row = getRow(this.blocks[i][j]);
        int col = getCol(this.blocks[i][j]);
        
        return Math.abs(row - i) + Math.abs(col - j);
    }

    private int[][] swap(int cellRow, int cellCol, int cellRow2, int cellCol2) {
        int[][] swapped = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
               swapped[i][j] = this.blocks[i][j];
        int temp = swapped[cellRow][cellCol];
        swapped[cellRow][cellCol] = swapped[cellRow2][cellCol2];
        swapped[cellRow2][cellCol2] = temp;

        return swapped;
    }

    public Board(int[][] blocks) {
        N = blocks.length;
        
        this.blocks = new short[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = (short) blocks[i][j];
                if (this.blocks[i][j] != rightPlace(i, j) && this.blocks[i][j] != 0) {
                    hamming++;
                    manhattan += getDistanceToGoalPosition(i, j);
                    isGoal = false;
                }
                
                if (this.blocks[i][j] == 0) {
                    emptyCellCol = j;
                    emptyCellRow = i;
                }
            }
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }
    
    public boolean isGoal() {
        return isGoal;
    }

    public Board twin() {
        short leftCol = 0;
        short leftRow = 0;
        if (blocks[leftRow][leftCol] == 0 || blocks[leftRow][leftCol+1] == 0) {
            leftRow = 1;
        }

        return new Board(swap(leftRow, leftCol, leftRow, leftCol+1));
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;

        if (this.N != that.N)
            return false;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (this.blocks[i][j] != that.blocks[i][j])
                    return false;

        return true;
    }

    public Iterable<Board> neighbors() {
        queue = new Queue<Board>();

        if (emptyCellRow != 0) {
            queue.enqueue(new Board(swap(emptyCellRow, emptyCellCol, emptyCellRow - 1, emptyCellCol)));
        }

        if (emptyCellRow != N-1) {
            queue.enqueue(new Board(swap(emptyCellRow, emptyCellCol, emptyCellRow+1, emptyCellCol)));
        }

        if (emptyCellCol != 0) {
            queue.enqueue(new Board(swap(emptyCellRow, emptyCellCol, emptyCellRow, emptyCellCol-1)));
        }

        if (emptyCellCol != N-1) {
            queue.enqueue(new Board(swap(emptyCellRow, emptyCellCol, emptyCellRow, emptyCellCol+1)));
        }

        return queue;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
