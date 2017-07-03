import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        final int n = args.length - 1;

        Deque<String> deque = new Deque<>();

        try {
            int k = Integer.parseInt(args[0]);
            StdRandom.shuffle(args);

            while (k > 0) {
                deque.addFirst(args[k + 1]);
                k--;
            }
        }
        catch (NumberFormatException ex) {
            System.err.println("First argument must be an int");
            return;
        }
        catch (ArrayIndexOutOfBoundsException outOfBound) {
            System.err.println("Wrong number of arguments");
        }

        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
    }
}
