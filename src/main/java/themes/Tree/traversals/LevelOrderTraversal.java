package main.java.themes.Tree.traversals;


//src: https://leetcode.com/problems/binary-tree-level-order-traversal/
//Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
//Input: root = [3,9,20,null,null,15,7]
//Output: [[3],[9,20],[15,7]]
//Input: root = [1]
//Output: [[1]]
//Input: root = []
//Output: []
//The number of nodes in the tree is in the range [0, 2000].
//-1000 <= Node.val <= 1000
//

public class LevelOrderTraversal {
    /*
    class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        var levels = new ArrayList<List<Integer>>();

        if(root != null) {
            q.offer(root);
        }

        while(!q.isEmpty()) {
            var level = new ArrayList<Integer>();
            int size = q.size();

            for(int i = 0; i < size; i++) {
                var n = q.poll();
                level.add(n.val);

                if(n.left != null) {
                    q.offer(n.left);
                }

                if(n.right != null) {
                    q.offer(n.right);
                }
            }

            levels.add(level);
        }

        return levels;
    }
}
     */
}
