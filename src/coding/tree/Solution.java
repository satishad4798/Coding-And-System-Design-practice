//package coding.tree;
//
//import java.util.ArrayDeque;
//import java.util.Queue;
//
/// **
// * Definition for a binary tree node.
// * public class TreeNode {
// * int val;
// * TreeNode left;
// * TreeNode right;
// * TreeNode(int x) { val = x; }
// * }
// */
//class ParentTreeNode {
//
//    TreeNode leftParent;
//    TreeNode rightParent;
//
//    public static void main(String[] args) {
//        ParentTreeNode parentTreeNode = new ParentTreeNode();
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(1);
//        root.left.left = new TreeNode(6);
//        root.left.right = new TreeNode(2);
//        root.right.left = new TreeNode(0);
//        root.right.right = new TreeNode(8);
//        root.left.right.left = new TreeNode(7);
//        root.left.right.right = new TreeNode(4);
//
//        System.out.println(parentTreeNode.lowestCommonAncestor(root, root.left, root.right).val); // should return 3
//    }
//
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        Queue<TreeNode> q2 = new ArrayDeque<>();
//
//        searchChild(root, p, q);
//        return null;
//    }
//
//    TreeNode searchChild(TreeNode node, TreeNode p, TreeNode q) {
//
//        //base case
//        if (node == null) {
//            return null;
//        }
//
//        if (node == p || node == q) {
//            return node;
//        }
//        //search in left and right
//        TreeNode left = searchChild(node.left, p, q);
//        TreeNode right = searchChild(node.right, p, q);
//
//
//        if (left != null && right != null) {
//            return node;
//        }
//        return left != null ? left : right;
//
//    }
//}