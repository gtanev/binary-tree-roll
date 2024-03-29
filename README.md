# Binary Tree Roll Algorithm

This is a simple Java implementation of my linear time [binary tree roll algorithm](https://ieeexplore.ieee.org/document/8011115/). The source code contains a POJO 
class for creating binary tree nodes, a utility class implementing the clockwise (CW) and counterclockwise (CCW) 
variants of the algorithm, a helper class for printing to an output stream, and a basic example demonstrating the 
execution of the algorithm with the resulting binary trees and their depth-first traversals.


## Introduction

Binary tree roll is an operation by which a binary tree is restructured in such a way that its topological representation
appears to be rolled at a 90 degree angle, either in a clockwise or a counter-clockwise direction. The two directions 
produce two inverse variants of the roll operation, a clockwise (CW) and a counterclockwise (CCW) roll. A major 
result of this operation is that two of the depth-first traversals of the rolled binary tree are identical to 
another two traversals of the original binary tree. In fact, we can define the CW and CCW roll functions in terms of the 
preorder, inorder, and postorder traversal functions of the original (T1) and the rolled (T2) tree, as follows:

- **CW(T1)** = T2 ⇔ (inorder(T1) = preorder(T2) ∧ postorder(T1) = inorder(T2))
- **CCW(T1)** = T2 ⇔ (preorder(T1) = inorder(T2) ∧ inorder(T1) = postorder(T2))

![Roll Example](https://farm5.staticflickr.com/4877/45429412704_38d51ffd76_o.png)


## Pseudocode and short analysis

```
def rollCCW(root, parent = null) 
    if root != null
        if root.right != null
            rollCCW(root.right, parent)
            root.right.left = root
            root.right = null
        else if parent != null
            parent.right = root
            parent.left = null        
        if root.left != null
            rollCCW(root.left, root)        
```

This algorithm "rewires" the nodes of any given binary tree in such a way that it performs the complete roll operation by 
visiting each node exactly once during a recursive inorder traversal, producing the expected tree in accordance
with the formal definition of the roll operation. 

Applying the roll transformation successively in a single direction on any binary tree (mutating it in place) results 
in a sequential pass through a unique subset of all possible binary tree topologies of a given size, until 
the topology of the initial tree is eventually reached. Note that the number of distinct binary trees obtained
through this cyclic transformation can be either equal to or a multiple of the number of possible topologies, 
as there are trees that are topologically identical to each other but have their nodes at different positions.

The running time of this algorithm is always linear or `Θ(𝑛)` analogous to the linear traversal it performs, and its space 
complexity is proportional to the height of the given binary tree as the call stack grows up to `ℎ + 1` frames, 
i.e., `Θ(𝑛)` in the worst case (for `ℎ = 𝑛 - 1`) and `Θ(log2𝑛)` in the best case (for `ℎ = ⌊log2𝑛⌋`).


## Examples

_Note_: Trees are drawn from left to right (root node is on the far left).

- A random binary tree with seven nodes rolled clockwise and counterclockwise: 


```
        ┌────── 6
┌────── 3
│       └────── 5
1
└────── 2
        │       ┌────── 7
        └────── 4

PreOrder:   1 2 4 7 3 5 6 
InOrder:    4 7 2 1 5 3 6 
PostOrder:  7 4 2 5 6 3 1 

        ┌────── 1
        │       │       ┌────── 3
        │       │       │       └────── 6
        │       └────── 5
┌────── 2
4
└────── 7

PreOrder:   4 7 2 1 5 3 6 
InOrder:    7 4 2 5 6 3 1 
PostOrder:  7 6 3 5 1 2 4 

6
│       ┌────── 5
└────── 3
        │               ┌────── 7
        │               │       └────── 4
        │       ┌────── 2
        └────── 1

PreOrder:   6 3 1 2 7 4 5 
InOrder:    1 2 4 7 3 5 6 
PostOrder:  4 7 2 1 5 3 6
```



- A complete cycle of CCW roll transformations leading back to the original tree: 

  
```
1
│       ┌────── 4
└────── 2
        └────── 3

PreOrder:   1 2 3 4 
InOrder:    3 2 4 1 
PostOrder:  3 4 2 1 

┌────── 4
│       │       ┌────── 3
│       └────── 2
1

PreOrder:   1 4 2 3 
InOrder:    1 2 3 4 
PostOrder:  3 2 4 1 

┌────── 3
│       └────── 2
4
└────── 1

PreOrder:   4 1 3 2 
InOrder:    1 4 2 3 
PostOrder:  1 2 3 4 

┌────── 2
3
│       ┌────── 1
└────── 4

PreOrder:   3 4 1 2 
InOrder:    4 1 3 2 
PostOrder:  1 4 2 3 

2
│       ┌────── 1
│       │       └────── 4
└────── 3

PreOrder:   2 3 1 4 
InOrder:    3 4 1 2 
PostOrder:  4 1 3 2 

        ┌────── 4
┌────── 1
│       └────── 3
2

PreOrder:   2 1 3 4 
InOrder:    2 3 1 4 
PostOrder:  3 4 1 2 

4
│       ┌────── 3
└────── 1
        └────── 2

PreOrder:   4 1 2 3 
InOrder:    2 1 3 4 
PostOrder:  2 3 1 4 

┌────── 3
│       │       ┌────── 2
│       └────── 1
4

PreOrder:   4 3 1 2 
InOrder:    4 1 2 3 
PostOrder:  2 1 3 4 

┌────── 2
│       └────── 1
3
└────── 4

PreOrder:   3 4 2 1 
InOrder:    4 3 1 2 
PostOrder:  4 1 2 3 

┌────── 1
2
│       ┌────── 4
└────── 3

PreOrder:   2 3 4 1 
InOrder:    3 4 2 1 
PostOrder:  4 3 1 2 

1
│       ┌────── 4
│       │       └────── 3
└────── 2

PreOrder:   1 2 4 3 
InOrder:    2 3 4 1 
PostOrder:  3 4 2 1 

        ┌────── 3
┌────── 4
│       └────── 2
1

PreOrder:   1 4 2 3 
InOrder:    1 2 4 3 
PostOrder:  2 3 4 1 

3
│       ┌────── 2
└────── 4
        └────── 1

PreOrder:   3 4 1 2 
InOrder:    1 4 2 3 
PostOrder:  1 2 4 3 

┌────── 2
│       │       ┌────── 1
│       └────── 4
3

PreOrder:   3 2 4 1 
InOrder:    3 4 1 2 
PostOrder:  1 4 2 3 

┌────── 1
│       └────── 4
2
└────── 3

PreOrder:   2 3 1 4 
InOrder:    3 2 4 1 
PostOrder:  3 4 1 2 

┌────── 4
1
│       ┌────── 3
└────── 2

PreOrder:   1 2 3 4 
InOrder:    2 3 1 4 
PostOrder:  3 2 4 1 

4
│       ┌────── 3
│       │       └────── 2
└────── 1

PreOrder:   4 1 3 2 
InOrder:    1 2 3 4 
PostOrder:  2 3 1 4 

        ┌────── 2
┌────── 3
│       └────── 1
4

PreOrder:   4 3 1 2 
InOrder:    4 1 3 2 
PostOrder:  1 2 3 4 

2
│       ┌────── 1
└────── 3
        └────── 4

PreOrder:   2 3 4 1 
InOrder:    4 3 1 2 
PostOrder:  4 1 3 2 

┌────── 1
│       │       ┌────── 4
│       └────── 3
2

PreOrder:   2 1 3 4 
InOrder:    2 3 4 1 
PostOrder:  4 3 1 2 

┌────── 4
│       └────── 3
1
└────── 2

PreOrder:   1 2 4 3 
InOrder:    2 1 3 4 
PostOrder:  2 3 4 1 

┌────── 3
4
│       ┌────── 2
└────── 1

PreOrder:   4 1 2 3 
InOrder:    1 2 4 3 
PostOrder:  2 1 3 4 

3
│       ┌────── 2
│       │       └────── 1
└────── 4

PreOrder:   3 4 2 1 
InOrder:    4 1 2 3 
PostOrder:  1 2 4 3 

        ┌────── 1
┌────── 2
│       └────── 4
3

PreOrder:   3 2 4 1 
InOrder:    3 4 2 1 
PostOrder:  4 1 2 3 

1
│       ┌────── 4
└────── 2
        └────── 3

PreOrder:   1 2 3 4 
InOrder:    3 2 4 1 
PostOrder:  3 4 2 1

```
It can be observer that the number of distinct topologies in the obtained set is 6, while the number of distinct binary trees is 24.

---
For a more detailed analysis and in-depth explanation of the algorithm, please refer to this paper: https://ieeexplore.ieee.org/document/8011115/
