using System;
using System.Collections;
using System.Collections.Generic;

namespace Subset
{
    public class Deque<T> : IEnumerable<T> where T : class
    {
        private QueueItem _firstItem;
        private QueueItem _lastItem;

        private int _size;

        public Deque()
        {
            
        }

        public bool IsEmpty()
        {
            return _size == 0;
        }

        public int Size()
        {
            return _size;
        }

        public void AddFirst(T item)
        {
            CheckIfItemIsNull(item);

            var tmpItem = _firstItem;

            _firstItem = new QueueItem
                             {
                                 Value = item,
                                 Next = tmpItem
                             };

            if (IsEmpty())
                _lastItem = _firstItem;

            _size++;
        }

        private static void CheckIfItemIsNull(T item)
        {
            if (item == null)
                throw new NullReferenceException();
        }

        public void AddLast(T item)
        {
            CheckIfItemIsNull(item);

            var tmpItem = _lastItem;

            _lastItem = new QueueItem
                            {
                                Value = item,                                
                            };

            
            
            if (!IsEmpty())
            {
                tmpItem.Next = _lastItem;
            }
            else
                _firstItem = _lastItem;

            _size++;
        }

        public T RemoveFirst()
        {
            if (IsEmpty())
                throw new NoSuchElementException();

            var tmpItem = _firstItem;

            _firstItem = _firstItem.Next;

            _size--;

            return tmpItem.Value;
        }

        public T RemoveLast()
        {
            if (IsEmpty())
                throw new NoSuchElementException();

            var tmpItem = _lastItem;

            _lastItem = _lastItem.Prev;

            _size--;

            return tmpItem.Value;
        }

        private class QueueItem
        {
            public T Value { get; set; }            
            public QueueItem Next { get; set; }            
        }

        #region IEnumerable

        public IEnumerator<T> GetEnumerator()
        {
            QueueItem iterator = _firstItem;
            while (iterator != null)
            {
                yield return iterator.Value;
                iterator = iterator.Next;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion
    }
}