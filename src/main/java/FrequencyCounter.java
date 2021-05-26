import BinarySearch.ArrayST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import interfaces.ST;

/**
 * Program FrequencyCounter.java is a symbol-table client that finds the number of occurrences of each string (having at least as many characters as a given threshold length) in a sequence of strings from standard input, then iterates through the keys to find the one that occurs the most frequently.
 * Source: https://algs4.cs.princeton.edu/31elementary/
 */
public class FrequencyCounter {
    public static void main(String[] args){
        ST<String, Integer> st = new ArrayST<>();

        st.put("test", 1);

        int minlen = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) continue; // ignore short strings
            if (!st.contains(word)) st.put(word, 1);
            else                    st.put(word, st.get(word) + 1);
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        StdOut.println(max + " " + st.get(max));
    }
}
