import java.io.PrintStream;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * A helper class for drawing binary trees and printing their node values in any depth-first order
 * to the console or to any PrintStream output channel passed as an optional constructor parameter
 *
 * @author George Tanev
 */
public class BinaryTreePrinter {
    private final PrintStream printStream;

    public BinaryTreePrinter(PrintStream printStream) {
        this.printStream = Optional.ofNullable(printStream).orElse(System.out);
    }

    public BinaryTreePrinter() {
        this(null);
    }

    public PrintStream getStream() {
        return printStream;
    }

    public void printPreOrder(BinaryTreeNode binaryTreeNode) {
        printTraversal(this::preOrder, binaryTreeNode, "PreOrder");
    }

    public void printInOrder(BinaryTreeNode binaryTreeNode) {
        printTraversal(this::inOrder, binaryTreeNode, "InOrder");
    }

    public void printPostOrder(BinaryTreeNode binaryTreeNode) {
        printTraversal(this::postOrder, binaryTreeNode, "PostOrder");
    }

    private void printTraversal(BiConsumer<BinaryTreeNode, StringBuilder> traversalMethod,
                                BinaryTreeNode binaryTreeNode, String traversalName) {

        StringBuilder output = new StringBuilder();
        output.append(String.format("%1$-12s", traversalName + ":"));

        traversalMethod.accept(binaryTreeNode, output);

        printStream.println(output);
    }

    private void preOrder(BinaryTreeNode node, StringBuilder output) {
        printNodeValue(node, output).append(' ');

        if (node.getLeftChild() != null)
            preOrder(node.getLeftChild(), output);

        if (node.getRightChild() != null)
            preOrder(node.getRightChild(), output);
    }

    private void inOrder(BinaryTreeNode node, StringBuilder output) {
        if (node.getLeftChild() != null)
            inOrder(node.getLeftChild(), output);

        printNodeValue(node, output).append(' ');

        if (node.getRightChild() != null)
            inOrder(node.getRightChild(), output);
    }

    private void postOrder(BinaryTreeNode node, StringBuilder output) {
        if (node.getLeftChild() != null)
            postOrder(node.getLeftChild(), output);

        if (node.getRightChild() != null)
            postOrder(node.getRightChild(), output);

        printNodeValue(node, output).append(' ');
    }

    public void printTree(BinaryTreeNode binaryTreeNode) {
        StringBuilder output = new StringBuilder();

        if (binaryTreeNode.getRightChild() != null)
            printSubTree(binaryTreeNode.getRightChild(), output, true, "");

        printNodeValue(binaryTreeNode, output).append('\n');

        if (binaryTreeNode.getLeftChild() != null)
            printSubTree(binaryTreeNode.getLeftChild(), output, false, "");

        printStream.println(output);
    }

    private void printSubTree(BinaryTreeNode node, StringBuilder output, boolean isRightChild, String prefix) {
        if (node.getRightChild() != null)
            printSubTree(node.getRightChild(), output, true, prefix.concat(isRightChild ? "        " : "│       "));

        output.append(prefix).append(isRightChild ? "┌──" : "└──").append("──── ");
        printNodeValue(node, output).append('\n');

        if (node.getLeftChild() != null)
            printSubTree(node.getLeftChild(), output, false, prefix.concat(isRightChild ? "│       " : "        "));
    }

    private StringBuilder printNodeValue(BinaryTreeNode node, StringBuilder output) {
        return output.append(String.valueOf(node.getValue()));
    }
}