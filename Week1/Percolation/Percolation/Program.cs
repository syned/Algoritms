using System;
using System.Collections.Generic;
using System.Linq;

namespace PercolationProgram
{
    class Program
    {
        static void Main(string[] args)
        {
            var percolation = new Percolation(10);
            Console.WriteLine(percolation);
        }
    }
}
