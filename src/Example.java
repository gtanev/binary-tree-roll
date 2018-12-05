import java.util.stream.Stream;


public class Example {

    private static BinaryTreePrinter printer = new BinaryTreePrinter(System.out);

    public static void main(String[] args) {
        final BinaryTreeNode root = new BinaryTreeNode<>(1);
        final BinaryTreeNode left = new BinaryTreeNode<>(2);
        final BinaryTreeNode right = new BinaryTreeNode<>(3);

        left.setLeftChild(new BinaryTreeNode<>(4, null, new BinaryTreeNode<>(7)));
        right.setLeftChild(new BinaryTreeNode<>(5));
        right.setRightChild(new BinaryTreeNode<>(6));

        root.setLeftChild(left);
        root.setRightChild(right);

        final BinaryTreeNode cwRollRoot = BinaryTreeRollUtils.rollClockwise(root);
        final BinaryTreeNode ccwRollRoot = BinaryTreeRollUtils.rollCounterClockwise(root);

        Stream.of(root, cwRollRoot, ccwRollRoot).forEach(Example::printInfo);

        printer.getStream().close();
    }

    private static void printInfo(BinaryTreeNode tree) {
        printer.printTree(tree);
        printer.printPreOrder(tree);
        printer.printInOrder(tree);
        printer.printPostOrder(tree);
        printer.getStream().println();
    }
}


/*
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
*/