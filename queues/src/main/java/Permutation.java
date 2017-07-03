import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        final int n = args.length - 1;

        Deque<String> deque = new Deque<>();

        try {
            int k = Integer.parseInt(args[0]);

            int[] randomIndexes = StdRandom.permutation(n, k);
            for (int idx : randomIndexes) {
                System.err.println(idx);
                deque.addFirst(args[idx + 1]);
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
