using System;

namespace PercolationProgram
{
    public class PercolationStats
    {
        private readonly Percolation _percolation;

        private double _mean;

        public PercolationStats(int N, int T)
        {
            var rnd = new Random();            

            for (int k = 0; k < T; k++)
            {        
                Console.WriteLine("Iteration {0}", k);
                _percolation = new Percolation(N);
                var countOpened = 0;

                do
                {
                    var i = rnd.Next(1, N + 1);
                    var j = rnd.Next(1, N + 1);

                    if (_percolation.IsOpen(i, j))
                        continue;
                    _percolation.Open(i, j);

                    countOpened++;

                } while (!_percolation.Percolates());

                Console.WriteLine("Count opened {0}", countOpened);

                _mean = (_mean + (double) countOpened/(N*N)) / 2;

                Console.WriteLine("mean {0}", _mean);
            }            
        }

        public double Mean()
        {
            return _mean;
        }



        static void Main(string[] args)
        {
            var N = Int32.Parse(args[0]);
            var T = Int32.Parse(args[1]);

            var percolationStats = new PercolationStats(N, T);

            Console.WriteLine("mean {0}", percolationStats.Mean());
        }
    }
}