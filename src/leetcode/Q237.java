package leetcode;

import algorithm.dataStructure.tree.BinaryTree;
import algorithm.dataStructure.tree.TreeNode;
import common.Test;
import common.Utils;
import java.util.*;

/**
 * 二叉树的所有路径
 * https://leetcode-cn.com/problems/binary-tree-paths/
 */
public class Q237 implements Test {

    public List<String> binaryTreePaths(TreeNode<Integer> root) {
        if(root == null) return Collections.emptyList();
        List<String> list = new ArrayList<>();
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        // map 记录每个结点的路径
        Map<TreeNode<Integer>, String> map = new HashMap<>();
        while (!stack.isEmpty()){
            TreeNode<Integer> current = stack.pop();
            String path = map.getOrDefault(current, "");
            path += current.data;
            map.put(current, path);
            if(current.isLeafNode()){
                list.add(map.get(current));
                continue;
            }
            if(current.rightChild != null){
                stack.push(current.rightChild);
                map.put(current.rightChild, path + "->");
            }
            if(current.leftChild != null){
                stack.push(current.leftChild);
                map.put(current.leftChild, path + "->");
            }
        }
        return list;
    }

    /**
     * leetcode官方
     */
    public void constructPaths(TreeNode<Integer> root, String path, List<String> paths) {
        if (root != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(root.data);
            if (root.leftChild == null && root.rightChild == null) {  // 当前节点是叶子节点
                paths.add(pathSB.toString());  // 把路径加入到答案中
            } else {
                pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                constructPaths(root.leftChild, pathSB.toString(), paths);
                constructPaths(root.rightChild, pathSB.toString(), paths);
            }
        }
    }

    @Override
    public boolean test() {
        int[] arr = Utils.generateIntArray(10, 20);
        BinaryTree<Integer> bt = Utils.generateCompleteBinaryTree(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        Utils.print("dfs遍历原树", bt.dfs());
        List<String> list = binaryTreePaths(bt.root);
        Utils.print("我的答案", list);

        List<String> paths = new ArrayList<>();
        constructPaths(bt.root, "", paths);
        Utils.print("官方答案", paths);
        boolean result1 = list.size() == paths.size();
        for (int i = 0; i < list.size(); i++) {
            if(!list.get(i).equals(paths.get(i)))
                return false;
        }
        return result1;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q237(), 100);
    }
}
