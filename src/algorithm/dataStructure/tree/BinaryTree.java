package algorithm.dataStructure.tree;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

public class BinaryTree <T extends Comparable<T>> {

    public TreeNode<T> root;

    public BinaryTree(TreeNode<T> root) {
        this.root = root;
    }

    public BinaryTree() {}

    /**
     * 按完全二叉树方式填充
     */
    public TreeNode<T> insert(T data){
        TreeNode<T> node = new TreeNode<>(data);
        if(root == null){
            root = node;
            return root;
        }
        Queue<TreeNode<T>> queue = new ArrayDeque<>(); // 想象为左边是队头比较好理解，更贴合二叉树
        queue.add(root);
        TreeNode<T> current = root;
        while (current.leftChild != null && current.rightChild != null){ // 直到队首结点有可插入子结点的位置，即找到了要插入的位置，跳出循环
            queue.add(current.leftChild);
            queue.add(current.rightChild);
            queue.poll();   // current结点没有可插入子结点的位置，没有利用价值，出队
            current = queue.peek();
        }
        if(current.leftChild == null){
            current.leftChild = node;
        }else {
            current.rightChild = node;
        }
        return node;
    }

    /**
     * 层序遍历
     */
    public List<TreeNode<T>> bfs(){
        if(root == null) return Collections.emptyList();
        List<TreeNode<T>> list = new ArrayList<>();
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
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
