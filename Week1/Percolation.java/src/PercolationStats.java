
public class PercolationStats {

    private Percolation percolation;
    
    private double[] means;
    
    public PercolationStats(int N, int T){
        
        means = new double[T];
        
        for(int k = 0;k < T;k++){
            percolation = new Percolation(N);
            
            int countOpened = 0;
            do{
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                
                if (percolation.isOpen(i, j))
                    continue;
                percolation.open(i, j);
                
                countOpened++;
            }while(!percolation.percolates());
            
            means[k] = (double)countOpened/(N*N);
        }
    }
    
    public double mean()
    {
        return StdStats.mean(means);
    }
    
    public double stddev()
    {
        return StdStats.stddev(means);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
        if (N < 1 || T <= 1)
            throw new IllegalArgumentException();
        
        PercolationStats percolationStats = new PercolationStats(N, T);
        
        double confidence1 = percolationStats.mean() - 1.96*percolationStats.stddev() / Math.sqrt(T);
        double confidence2 = percolationStats.mean() + 1.96*percolationStats.stddev() / Math.sqrt(T);
        
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + confidence1 + "," + confidence2);
    }

}
