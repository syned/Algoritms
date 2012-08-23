using System;
using System.Collections;
using System.Collections.Generic;

namespace Subset
{
    public class Deque<T> : IEnumerable<T>, IEnumerator<T>
    {
        public Deque()
        {
            
        }

        public bool IsEmpty()
        {
            throw new NotImplementedException();
        }

        public int Size()
        {
            throw new NotImplementedException();
        }

        public void AddFirst(T item)
        {
            
        }

        public void AddLast(T item)
        {
            
        }

        public T RemoveFirst()
        {
            throw new NotImplementedException();
        }

        public T RemoveLast()
        {
            throw new NotImplementedException();
        }

        #region IEnumerable

        public IEnumerator<T> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion

        #region IEnumerator
        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public bool MoveNext()
        {
            throw new NotImplementedException();
        }

        public void Reset()
        {
            throw new NotImplementedException();
        }

        public T Current
        {
            get { throw new NotImplementedException(); }
        }

        object IEnumerator.Current
        {
            get { return Current; }
        }

        #endregion
    }

    public class RandomizedQueue<T> : IEnumerable<T>
    {

        public RandomizedQueue()
        {
            
        }

        public bool IsEmpty()
        {
            throw new NotImplementedException();
        }

        public int Size()
        {
            throw new NotImplementedException();
        }

        public void Enqueue(T item)
        {
            
        }

        public T Dequeue()
        {
            throw new NotImplementedException();
        }

        public T Sample()
        {
            throw new NotImplementedException();
        }

        #region IEnumerable
        public IEnumerator<T> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion
    }
}