import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;
    private int capacity;
    
    public RandomizedQueue() {
        capacity = 2;
        resizeQueue();
    }
    
    private void resizeQueue() {
        Item[] tmp = (Item[]) new Object[capacity];
        
        for (int i = 0; i < size; i++) {
            tmp[i] = queue[i];
        }
        
        queue = tmp;
    }
    
    private void checkIfQueueIsEmpty() {
        if (size() == 0)
            throw new NoSuchElementException();
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        
        if (size == capacity) {
            capacity *= 2;
            resizeQueue();
        }
        
        queue[size] = item;
        size++;
    }
    
    public Item dequeue() {
        
        checkIfQueueIsEmpty();
        
        int randomIndex = StdRandom.uniform(size);
        Item randomItem = queue[randomIndex];
        size--;
        queue[randomIndex] = queue[size];
        queue[size] = null;
        
        if (size > 4 && size < capacity / 4) {
            capacity /= 2;
            resizeQueue();
        }
        
        return randomItem;
    }
    
    public Item sample() {
        
        checkIfQueueIsEmpty();
        
        int randomIndex = StdRandom.uniform(size);
        Item randomItem = queue[randomIndex];
        return randomItem;
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {        
        
        private Item[] iteratorQueue;
        private int current;
        
        public RandomizedQueueIterator() {
            current = 0;
            
            iteratorQueue = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iteratorQueue[i] = queue[i];
            }
            
            StdRandom.shuffle(iteratorQueue);
        }
        
        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (current >= size() || isEmpty())
                throw new NoSuchElementException();
            return iteratorQueue[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
}
