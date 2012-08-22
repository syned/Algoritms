
public class Percolation {
    
    private boolean[] opens;
    
    private WeightedQuickUnionUF quickFind;
    private int N;
    
    private int rootBelow;
    private int rootAbove = 0;
    
    public Percolation(int N){
        this.N = N;
        rootBelow = N*N + 1;
        int doubleSize = rootBelow + 1;
        opens = new boolean[doubleSize];
        opens[0] = opens[doubleSize - 1] = true;
        quickFind = new WeightedQuickUnionUF(doubleSize);
    }
    
    private int getCell(int i, int j){
        if (i > N || j > N || i < 1 || j < 1 )
            throw new java.lang.IndexOutOfBoundsException();
        return (i-1)*N + j;
    }
    
    private void connectWithCellOnRight(int cell){
        if (cell % N == 0)
            return;
        int cellRight = cell + 1;
        if (opens[cellRight])
            quickFind.union(cell, cellRight);
    }
    
    private void connectWithCellOnLeft(int cell){
        int cellLeft = cell - 1;
        if (cellLeft % N == 0)
            return;
        if(opens[cellLeft])
            quickFind.union(cell, cellLeft);
    }
    
    private void connectWithCellBelow(int cell){
        int below = cell + N;
        below = below < rootBelow ? below : rootBelow;
        if (opens[below])
            quickFind.union(cell, below);
    }
    
    private void connectWithCellAbove(int cell){
        int above = cell - N;
        int aboveCell = above > rootAbove ? above : rootAbove;
        if (opens[aboveCell])
            quickFind.union(cell, aboveCell);
    }
    
    public void open(int i, int j){
        
        int cell = getCell(i, j);
        
        connectWithCellAbove(cell);
        
        connectWithCellBelow(cell);
        
        connectWithCellOnLeft(cell);
        
        connectWithCellOnRight(cell);
        
        opens[cell] = true;
    }
    
    public boolean isOpen(int i, int j){
        int cell = getCell(i, j);
        return opens[cell];
    }
    
    public boolean isFull(int i, int j){
        int cell = getCell(i, j);
        return opens[cell] && quickFind.connected(cell, rootAbove);
    }
    
    public boolean percolates()
    {
        return quickFind.connected(rootAbove, rootBelow);
    }
    
}
