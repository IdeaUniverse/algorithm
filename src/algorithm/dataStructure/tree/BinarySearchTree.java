package algorithm.dataStructure.tree;

/**
 * 二叉搜索树
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

    public BinarySearchTree(TreeNode<T> node){
        super(node);
    }

    public BinarySearchTree(){super();}

    public TreeNode<T> search(T data){
        TreeNode<T> current = root;
        while (current != null){
            if(data.compareTo(current.data) == 0)
                return current;
            else if(data.compareTo(current.data) < 0)
                current = current.leftChild;
            else current = current.rightChild;
        }
        return null;
    }

    @Override
    public TreeNode<T> insert(T data){
        TreeNode<T> node = new TreeNode<>(data);
        if(root == null){
            root = node;
            return root;
        }
        TreeNode<T> current = root;
        TreeNode<T> parent = null;
        while (current != null){
            if(data.compareTo(current.data) == 0)
                return current;
            else if(data.compareTo(current.data) < 0){
                parent = current;
                current = current.leftChild;
            }else {
                parent = current;
                current = current.rightChild;
            }
        }

        if(data.compareTo(parent.data) < 0)
            parent.leftChild = node;
        else if(data.compareTo(parent.data) > 0)
            parent.rightChild = node;

        return node;
    }
}
