import java.util.stream.Stream;

/**
 * A small example of the roll algorithm applied to a random binary tree with ten nodes
 *
 * @author George Tanev (12/2018)
 */
public class Example {

    private static BinaryTreePrinter printer = new BinaryTreePrinter(System.out);

    public static void main(String[] args) {
        final BinaryTreeNode<Character> root = new BinaryTreeNode<>('E');
        final BinaryTreeNode<Character> left = new BinaryTreeNode<>('D');
        final BinaryTreeNode<Character> right = new BinaryTreeNode<>('H');

        left.setLeftChild(new BinaryTreeNode<>('B', new BinaryTreeNode<>('A'), new BinaryTreeNode<>('C')));
        right.setLeftChild(new BinaryTreeNode<>('F', null, new BinaryTreeNode<>('G')));
        right.setRightChild(new BinaryTreeNode<>('J', new BinaryTreeNode<>('I'), null));

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

        ┌────── J
        │       └────── I
┌────── H
│       │       ┌────── G
│       └────── F
E
└────── D
        │       ┌────── C
        └────── B
                └────── A

PreOrder:   E D B A C H F G J I
InOrder:    A B C D E F G H I J
PostOrder:  A C B D G F I J H E

                ┌────── E
                │       │       ┌────── H
                │       │       │       │       ┌────── J
                │       │       │       └────── I
                │       └────── F
                │               └────── G
        ┌────── D
┌────── B
│       └────── C
A

PreOrder:   A B C D E F G H I J
InOrder:    A C B D G F I J H E
PostOrder:  C G J I H F E D B A

┌────── I
J
│       ┌────── G
│       │       └────── F
└────── H
        │               ┌────── C
        │               │       │       ┌────── A
        │               │       └────── B
        │       ┌────── D
        └────── E

PreOrder:   J H E D C B A G F I
InOrder:    E D B A C H F G J I
PostOrder:  A B C D E F G H I J

*/
