# symbol-tables
WIP: A frequency counter to compare the performance of symbol table implementations. Specifically, we compare the performance of binary search with an ordered array, binary search tree, 2-3 tree search, red-black BST, and hashing.

Dataset: dickens.txt (nearly the complete works of Charles Dickens)

Source: https://introcs.cs.princeton.edu/java/data/

- Total word count at least 1 character long: 5158645
- Distinct word count at least 1 characters long: 184706
- Highest word count: 'the' 244961

| Implementation  | Runtime | Search | Insert | Delete | Notes |
| ------------- | ------------- |
| Binary search in an ordered array  | 2 m 30 s 904 ms  | O(lg(n)) | O(n) | O(n) | Need to shift all greater items over |


