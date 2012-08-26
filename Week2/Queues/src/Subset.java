import java.util.Iterator;


public class Subset {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        
        RandomizedQueue<String> rndQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rndQueue.enqueue(s);
        }
        
        int i = 0;
        for (String item : rndQueue) {
            if (i == count)
                return;
            
            StdOut.println(item);
            i++;
        }
    }

}
