package algorithm.dataStructure.tree;

public class TreeNode<T extends Comparable<T>> {
    public T data;
    public TreeNode<T> leftChild;
    public TreeNode<T> rightChild;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
//                ", leftChild=" + leftChild +
//                ", rightChild=" + rightChild +
                '}';
    }
}
