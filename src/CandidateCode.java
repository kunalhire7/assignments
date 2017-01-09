import java.util.ArrayList;
import java.util.List;

public class CandidateCode {

    public static void main(String[] args) {
        String empty = "empty";
//        String[] input1 = {"empty", "D1", "C1", "C2"};
        String[] input1 = {empty, "D1", "D2", "D3", "D4", "D5", "D6", empty, "C1", empty, "C2", "C3",
                "D7", "D8", empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, "C4",
                empty, "C5", "C6", empty, empty, empty, empty};
        int input2 = input1.length;
        System.out.println(maxMediators(input1, input2));
    }

    public static int maxMediators(String[] input1, int input2) {
        Node root = createBinaryTree(input1);
        return diameter(root) - 2;
    }

    private static Node createBinaryTree(String[] input1) {
        List<Node> nodes = new ArrayList<Node>();
        Node root = new Node(input1[1]);
        nodes.add(root);
        for (int i = 1; i <= (input1.length - 1) / 2; i++) {
            if (!input1[i].equals("empty")) {
                Node n = getNodeByName(input1[i], nodes);
                String leftChildName = getLeftChild(input1, i);
                String rightChildName = getRightChild(input1, i);
                Node leftNode = null;
                Node rightNode = null;
                if(leftChildName != null){
                    leftNode = new Node(leftChildName);
                }
                if(rightChildName != null){
                    rightNode = new Node(rightChildName);
                }
                n.setLeft(leftNode);
                n.setRight(rightNode);
                nodes.add(leftNode);
                nodes.add(rightNode);
            }

        }
        return root;
    }

    private static Node getNodeByName(String name, List<Node> nodes) {
        for (Node n : nodes) {
            if (n != null && n.getName().equals(name)) {
                return n;
            }
        }
        return null;
    }

    private static String getLeftChild(String[] input1, int currentIndex) {
        int possibleLocation = 2 * currentIndex;
        String name = null;
        if (possibleLocation <= input1.length) {
            name = input1[possibleLocation];
        }
        return name.equals("empty") ? null : name;
    }

    private static String getRightChild(String[] input1, int currentIndex) {
        int possibleLocation = 2 * currentIndex + 1;
        String name = null;
        if (possibleLocation <= input1.length) {
            name = input1[possibleLocation];
        }
        return name.equals("empty") ? null : name;
    }

    static class Node {
        String name;
        Node left;
        Node right;

        Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private static int diameter(Node root) {
        if (root == null)
            return 0;

        int lheight = height(root.left);
        int rheight = height(root.right);

        int ldiameter = diameter(root.left);
        int rdiameter = diameter(root.right);

        return Math.max(lheight + rheight + 1,
                Math.max(ldiameter, rdiameter));

    }

    private static int height(Node node) {
        if (node == null)
            return 0;

        return (1 + Math.max(height(node.left), height(node.right)));
    }

}
