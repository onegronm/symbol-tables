# symbol-tables
WIP: A frequency counter to compare the performance of symbol table implementations. Specifically, we compare the performance of binary search with an ordered array, binary search tree, red-black BST, and hashing.

Dataset: dickens.txt (nearly the complete works of Charles Dickens)

Source: https://introcs.cs.princeton.edu/java/data/

- Total word count at least 1 character long: 5158645
- Distinct word count at least 1 characters long: 184706
- Highest word count: 'the' 244961

| Implementation  | Runtime | Search | Insert | Delete | Notes |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| Binary search in an ordered array  | 2 m 18 s | O(lg(n)) | O(n) | O(n) | Need to shift all greater items over |
| Binary search tree | 17 s | O(n) | O(n) | O(n) | O(~2 lg(h)) search/insert, O(sqrt(h)) delete on average case where h is the expected height of the tree. Worst-case height is N but exponentially small chance when keys are inserted in random order  |
| Red-Black BST | 15 s | O(lg(n)) | O(lg(n)) | O(lg(n)) | Guaranteed logarithmic performance for all operations. The LLRB remains perfectly balanced whether the input is supplied in ascending, descending, or random order |