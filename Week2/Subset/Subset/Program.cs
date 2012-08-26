using System;
using System.Linq;
using System.Text;

namespace Subset
{
    class Program
    {
        static void Main(string[] args)
        {
            var queue = new RandomizedQueue<string>();
            queue.Enqueue("Item1");
            queue.Enqueue("Item2");
            queue.Enqueue("Item3");
            queue.Enqueue("Item4");
            queue.Enqueue("Item5");
            queue.Enqueue("Item6");
            queue.Enqueue("Item7");

            foreach (var item in queue)
            {
                Console.WriteLine(item);
            }

            for (int i = 0; i < 7; i++)
            {
                Console.WriteLine(queue.Dequeue());
            }
        }
    }
}
