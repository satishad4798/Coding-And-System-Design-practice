package coding.tree;


public class Main {


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.left.right = new TreeNode(7);

        /*
            1
           / \
          2   3
         / \
        4   5
           /
          6
           \
            7

        */
        //1. invert tree
        System.out.println("original order");
        printInOrder(root);
        invertTree(root);
        System.out.println("\ninverted order");
        printInOrder(root);

        //2. sum of all nodes
        int sum = sumOfAllNodes(root);
        System.out.println("\nSum of all notes : " + sum);


        //3.depth of the tree
        int depth = findDepth(root);
        System.out.println("Depth of the tree is " + depth);


    }


    private static int findDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(findDepth(root.left), findDepth(root.right));


    }

    private static void invertTree(TreeNode root) {

        if (root == null)
            return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);


    }

    private static void printInOrder(TreeNode root) {

        if (root == null)
            return;

        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);

    }

    static int sumOfAllNodes(TreeNode node) {

        if (node == null)
            return 0;

        return node.val + sumOfAllNodes(node.left) + sumOfAllNodes(node.right);

    }
}

