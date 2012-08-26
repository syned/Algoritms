using System;
using System.Collections;
using System.Collections.Generic;

namespace Subset
{
    public class RandomizedQueue<T> : IEnumerable<T>
    {
        private T[] _queue;
        private int _size;
        private int _capacity;

        public RandomizedQueue()
        {
            _capacity = 4;
            _queue = new T[_capacity];
        }

        public bool IsEmpty()
        {
            return Size() == 0;
        }

        public int Size()
        {
            return _size;
        }

        public void Enqueue(T item)
        {
            if (_size == _capacity)
            {
                _capacity *= 2;
                var tmp = new T[_capacity];
                for (int i = 0; i < _size; i++)
                {
                    tmp[i] = _queue[i];
                }

                _queue = tmp;
            }

            _queue[_size] = item;
            _size++;
        }

        public T Dequeue()
        {
            var randomIndex = new Random().Next(_size);
            var randomItem = _queue[randomIndex];
            _size--;
            _queue[randomIndex] = _queue[_size];
            _queue[_size] = default(T);

            if (_size <= _capacity / 4)
            {
                _capacity /= 2;
                var tmp = new T[_capacity];
                for (int i = 0; i < _size; i++)
                {
                    tmp[i] = _queue[i];
                }

                _queue = tmp;
            }

            return randomItem;
        }

        public T Sample()
        {
            var randomIndex = new Random().Next(_size);
            var randomItem = _queue[randomIndex];
            return randomItem;
        }

        #region IEnumerable
        public IEnumerator<T> GetEnumerator()
        {
            for (int i = _size - 1; i >= 0; i--)
            {
                yield return _queue[i];
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion
    }
}