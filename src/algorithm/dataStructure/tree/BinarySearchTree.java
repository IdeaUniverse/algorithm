package algorithm.dataStructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 二叉搜索树
 */
public class BinarySearchTree<T extends Comparable<T>> {

    public TreeNode<T> root;

    public BinarySearchTree(TreeNode<T> node){
        root = node;
    }

    public BinarySearchTree(){}

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

    /**
     * 层序遍历
     */
    public List<TreeNode<T>> bfs(){
        if(root == null) return Collections.emptyList();
        List<TreeNode<T>> list = new ArrayList<>();
        Queue<TreeNode<T>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        while (queue.size() > 0){
            TreeNode<T> current = queue.poll();
            list.add(current);
            if(current.leftChild != null)
                queue.add(current.leftChild);
            if(current.rightChild != null)
                queue.add(current.rightChild);
        }
        return list;
    }
}
