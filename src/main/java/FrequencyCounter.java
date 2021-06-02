import BinarySearch.BinarySearchST;
import BinarySearchTree.BST;
import edu.princeton.cs.algs4.StdOut;
import interfaces.ST;

import java.io.*;
import java.util.Scanner;

/**
 * Program FrequencyCounter.java is a symbol-table client that finds the number of occurrences of each string (having at least as many characters as a given threshold length) in a sequence of strings from standard input, then iterates through the keys to find the one that occurs the most frequently.
 * Source: https://algs4.cs.princeton.edu/31elementary/
 */
public class FrequencyCounter {
    public static void main(String[] args)  {

        int minLen = 1; // Integer.parseInt(args[0]);

        try {
            /**
             * Binary search
             */
            ST<String, Integer> st = new BinarySearchST<>();

            /**
             * Binary search tree
             */
            // ST<String, Integer> st = new BST();
            File file = new File("C:\\Code\\symbol-tables\\dickens.txt");
            Scanner sc = new Scanner(new FileReader(file));

            String word;
            while (sc.hasNextLine()) {
                Scanner sc2 = new Scanner(sc.nextLine());
                while (sc2.hasNext()) {
                    word = sc2.next();
                    // System.out.println(word);
                    if (word.length() < minLen) continue; // ignore short strings
                    if (!st.contains(word)) st.put(word, 1);
                    else
                        st.put(word, st.get(word) + 1);
                }
            }

            String max = "";
            st.put(max, 0);
            Iterable<String> keys = st.keys();
            for (String w : keys) {
                try {
                    if (w != null && st.get(w) > st.get(max))
                        max = w;
                } catch(Exception e) {
                    System.out.println(e.getStackTrace());
                }
            }

            StdOut.println("Total word count at least " + minLen + " characters long: " + st.total());
            StdOut.println("Distinct word count at least " + minLen + " characters long: " + st.size());
            StdOut.println("Highest frequency: " + max + " " + st.get(max));
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
