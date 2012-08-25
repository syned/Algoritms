
public class Percolation {
    
    private boolean[] opens;
    
    private WeightedQuickUnionUF quickFind;
    private WeightedQuickUnionUF quickFindFull;
    private int N;
    
    private int rootBelow;
    private int rootAbove = 0;
    
    public Percolation(int N) {
        this.N = N;
        rootBelow = N*N + 1;
        int doubleSize = rootBelow + 1;
        opens = new boolean[doubleSize];
        opens[0] = true;
        opens[doubleSize - 1] = true;
        quickFind = new WeightedQuickUnionUF(doubleSize);
        quickFindFull = new WeightedQuickUnionUF(doubleSize - 1);
    }
    
    private int getCell(int i, int j) {
        if (i > N || j > N || i < 1 || j < 1)
            throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
        return (i-1)*N + j;
    }
    
    private void connectWithCellOnRight(int cell) {
        if (cell % N == 0)
            return;
        int cellRight = cell + 1;
        if (opens[cellRight]) {
            quickFind.union(cell, cellRight);
            quickFindFull.union(cell, cellRight);
        }
    }
    
    private void connectWithCellOnLeft(int cell) {
        int cellLeft = cell - 1;
        if (cellLeft % N == 0)
            return;
        if (opens[cellLeft]) {
            quickFind.union(cell, cellLeft);
            quickFindFull.union(cell, cellLeft);
        }
    }
    
    private void connectWithCellBelow(int cell) {
        int below = cell + N;
        
        if (below >= rootBelow)
            below = rootBelow;
        
        if (opens[below]) {
            quickFind.union(cell, below);
            if (below != rootBelow)
                quickFindFull.union(cell, below);
        }
    }
    
    private void connectWithCellAbove(int cell) {
        int above = cell - N;
        
        if (above < rootAbove)
            above = rootAbove;
        
        if (opens[above]) {
            quickFind.union(cell, above);
            quickFindFull.union(cell, above);
        }
    }
    
    public void open(int i, int j) {
        
        int cell = getCell(i, j);
        
        connectWithCellAbove(cell);
        
        connectWithCellBelow(cell);
        
        connectWithCellOnLeft(cell);
        
        connectWithCellOnRight(cell);
        
        opens[cell] = true;
    }
    
    public boolean isOpen(int i, int j) {
        int cell = getCell(i, j);
        return opens[cell];
    }
    
    public boolean isFull(int i, int j) {
        int cell = getCell(i, j);
        return opens[cell] && quickFindFull.connected(cell, rootAbove);
    }
    
    public boolean percolates()
    {
        return quickFind.connected(rootAbove, rootBelow);
    }
    
}
