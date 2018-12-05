import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * A utility class implementing the clockwise (CW) and counterclockwise (CCW) variants
 * of the binary tree roll algorithm in a thread-safe and immutable fashion
 *
 * @author George Tanev
 */
public final class BinaryTreeRollUtils {

    // a thread-safe reference to the root of the rolled tree to be returned by the algorithm
    private static final ThreadLocal<BinaryTreeNode> rolledRoot = ThreadLocal.withInitial(() -> null);

    public static BinaryTreeNode rollClockwise(BinaryTreeNode root) {
        return roll(root, BinaryTreeRollUtils::rollCW);
    }

    public static BinaryTreeNode rollCounterClockwise(BinaryTreeNode root) {
        return roll(root, BinaryTreeRollUtils::rollCCW);
    }

    private static BinaryTreeNode roll(BinaryTreeNode root, BiConsumer<BinaryTreeNode, BinaryTreeNode> rollVariant) {
        Objects.requireNonNull(root, "'root' cannot be null");

        rolledRoot.set(null);

        // call the appropriate algorithm with a deep clone of the input tree to avoid mutating it
        rollVariant.accept(root.copy(), null);

        return rolledRoot.get();
    }

    private static void rollCW(BinaryTreeNode root, BinaryTreeNode parent) {
        if (root == null) return;

        if (root.getLeftChild() != null) {
            rollCW(root.getLeftChild(), parent);
            root.getLeftChild().setRightChild(root);
            root.setLeftChild(null);
        } else {
            if (parent == null) {
                if (rolledRoot.get() == null)
                    rolledRoot.set(root);
            } else {
                parent.setLeftChild(root);
                parent.setRightChild(null);
            }
        }

        rollCW(root.getRightChild(), root);
    }

    private static void rollCCW(BinaryTreeNode root, BinaryTreeNode parent) {
        if (root == null) return;

        if (root.getRightChild() != null) {
            rollCCW(root.getRightChild(), parent);
            root.getRightChild().setLeftChild(root);
            root.setRightChild(null);
        } else {
            if (parent == null) {
                if (rolledRoot.get() == null) {
                    rolledRoot.set(root);
                }
            } else {
                parent.setRightChild(root);
                parent.setLeftChild(null);
            }
        }

        rollCCW(root.getLeftChild(), root);
    }

    private BinaryTreeRollUtils() {
    }
}