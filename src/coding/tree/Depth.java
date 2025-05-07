package coding.tree;


public class Depth {


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(10);
        System.out.println(sum(root));

    }

    static int sum(TreeNode node) {

        if (node == null)
            return 0;

        return node.val + sum(node.left) + sum(node.right);

    }

}

