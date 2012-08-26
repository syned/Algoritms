import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    public Deque() {
        firstItem = null;
        lastItem = null;
        size = 0;
    }
    
    private class QueueItem {
        private Item item;
        private QueueItem next;
        private QueueItem prev;
    }
    
    private QueueItem firstItem;
    private QueueItem lastItem;
    private int size;
    
    private void checkIfItemIsNull(Item item) {
        if (item == null)
            throw new NullPointerException();
    }
    
    private void checkIfQueueIsEmpty() {
        if (isEmpty())
            throw new NoSuchElementException();
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {
        checkIfItemIsNull(item);
        
        QueueItem tmp = firstItem;
        
        firstItem = new QueueItem();
        firstItem.item = item;
        firstItem.next = tmp;
        
        if (isEmpty())
            lastItem = firstItem;
        else
            tmp.prev = firstItem;
        
        size++;
    }
    
    public void addLast(Item item) {
        checkIfItemIsNull(item);
        
        QueueItem tmp = lastItem;
        
        lastItem = new QueueItem();
        lastItem.item = item;
        lastItem.prev = tmp;
        
        if (isEmpty())
            firstItem = lastItem;
        else
            tmp.next = lastItem;
        
        size++;
    }
    
    public Item removeFirst() {
        checkIfQueueIsEmpty();
        
        QueueItem tmp = firstItem;
        firstItem = firstItem.next;        
        size--;
        if (size > 0)
            firstItem.prev = null;
        else
            lastItem = null;
        return tmp.item;
    }
    
    public Item removeLast() {
        checkIfQueueIsEmpty();
        
        QueueItem tmp = lastItem;
        lastItem = lastItem.prev;        
        size--;
        if (size > 0)
            lastItem.next = null;
        else
            firstItem = null;
        return tmp.item;        
    }
    
    private class DequeIterator implements Iterator<Item> {

        QueueItem current;        
        
        public DequeIterator() {
            current = firstItem;
        }
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            
            if (!hasNext())
                throw new NoSuchElementException();
            
            QueueItem tmp = current;
            current = current.next;
            
            return tmp.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }    
}
