import java.util.Objects;

/**
 * A simple binary tree node class with two recursively defined children and a copy() method
 * to allow for creating deep copies of class instances for immutability
 *
 * @author George Tanev
 */
public class BinaryTreeNode<T> {

    private T value;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;

    public BinaryTreeNode(T value, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public BinaryTreeNode() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public BinaryTreeNode copy() {
        final BinaryTreeNode<T> binaryTreeNode = new BinaryTreeNode<>(value);

        if (leftChild != null) binaryTreeNode.leftChild = leftChild.copy();
        if (rightChild != null) binaryTreeNode.rightChild = rightChild.copy();

        return binaryTreeNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;

        return Objects.equals(value, that.value) &&
                Objects.equals(leftChild, that.leftChild) &&
                Objects.equals(rightChild, that.rightChild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, leftChild, rightChild);
    }
}