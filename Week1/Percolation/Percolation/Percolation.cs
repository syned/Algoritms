using System;
using System.Text;

namespace PercolationProgram
{
    public class Percolation
    {
        private readonly bool[] _opens;

        private readonly QuickFind _quickFind;

        private readonly int _n;

        public Percolation(int n)
        {
            _n = n;
            var doubleSize = _n*_n + 2;
            _opens = new bool[doubleSize];
            _opens[0] = _opens[doubleSize - 1] = true;
            _quickFind = new QuickFind(doubleSize);
        }

        public bool Connected(int p, int q)
        {
            return _quickFind.Connected(p, q);
        }

        public void Open(int i, int j)
        {
            var cell = GetCell(i, j);

            ConnectWithCellAbove(cell);

            ConnectWithCellBelow(cell);

            ConnectWithCellOnLeft(cell);

            ConnectWithCellOnRight(cell);

            _opens[cell] = true;
        }

        private void ConnectWithCellOnRight(int cell)
        {
            if (cell % _n == 0)
                return;
            var cellRight = cell + 1;
            if (_opens[cellRight])
                _quickFind.Union(cell, cellRight);
        }

        private void ConnectWithCellOnLeft(int cell)
        {            
            var cellLeft = cell - 1;
            if (cellLeft % _n == 0)
                return;
            if (_opens[cellLeft])
                _quickFind.Union(cell, cellLeft);
        }

        private void ConnectWithCellBelow(int cell)
        {
            var below = cell + _n;
            below = below <= _n*_n ? below : _n*_n + 1;

            if (_opens[below])
                _quickFind.Union(cell, below);
        }

        private void ConnectWithCellAbove(int cell)
        {
            var above = cell - _n;
            var aboveCell = above > 0 ? above : 0;

            if (_opens[aboveCell])
                _quickFind.Union(cell, aboveCell);
        }

        private int GetCell(int i, int j)
        {
            return (i - 1)*_n + j;
        }

        public bool IsOpen(int i, int j)
        {
            var cell = GetCell(i, j);
            return _opens[cell];
        }
   
        public bool IsFull(int i, int j)
        {
            throw new NotImplementedException();
        }

        public bool Percolates()
        {
            throw new NotImplementedException();
        }

        public override string ToString()
        {
            return base.ToString();
//            var sb = new StringBuilder();
//            for (int i = 0; i < _n; i++)
//            {
//                for (int j = 0; j < _n; j++)
//                {
//                    sb.Append(String.Format("{0,5} ", _cells[i*_n + j]));
//                }
//                sb.AppendLine();
//            }
//            return sb.ToString();
        }
    }
}