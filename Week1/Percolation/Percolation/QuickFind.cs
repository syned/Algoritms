using System;

namespace PercolationProgram
{
    public class QuickFind
    {
        private int count;
        private int[] id;
        private int[] sz;

        public QuickFind(int n)
        {
            count = n;
            id = new int[n];
            for (int i = 0; i < n; i++)
            {
                id[i] = i;
            }

            sz = new int[n];
            for (int i = 0; i < n; i++)
            {
                sz[i] = 1;
            }
        }

        public void Union(int p, int q)
        {
            var pId = Find(p);
            var qId = Find(q);

            if (pId == qId)
                return;

            if (sz[pId] < sz[qId])
            {
                id[pId] = id[qId];
                sz[qId] += sz[pId];
            }
            else
            {
                id[qId] = id[pId];
                sz[pId] += sz[qId];
            }
        }

        private int Find(int p)
        {
            while (p != id[p]) p = id[p];
            return p;
        }

        public override string ToString()
        {
            return String.Format("[{0}]", String.Join(",", id));
        }

        public bool Connected(int p, int q)
        {
            return Find(p) == Find(q);
        }
    }
}