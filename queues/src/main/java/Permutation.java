import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int k;

        while(!StdIn.isEmpty()) {
            String line = StdIn.readString();
            rq.enqueue(line);
        }

        try {
            k = Integer.parseInt(args[0]);

            while(!StdIn.isEmpty()) {
                String line = StdIn.readString();
                rq.enqueue(line);
            }
        }
        catch (NumberFormatException ex) {
            System.err.println("First argument must be an int");
            return;
        }

        while (k > 0) {
            StdOut.println(rq.dequeue());
            k--;
        }
    }
}
