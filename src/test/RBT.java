public class RBT<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left, right;
        boolean color;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }

    private Node root;

    // Insertion
    public void insert(K key, V value) {
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node insert(Node node, K key, V value) {
        if (node == null) return new Node(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insert(node.left, key, value);
        else if (cmp > 0) node.right = insert(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }

    // Helper methods for balancing
    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    // Other methods (e.g., search, delete) can be added as needed

    public boolean search(K key) {
        Node current = root;  // Start from the root

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                System.out.println(String.format("Key=%s, Values=%s, Color=%s", current.key, current.value, current.color));
                return true;  // Value found
            } else if (cmp < 0) {
                current = current.left;  // Go left
            } else {
                current = current.right;  // Go right
            }
        }

        return false;  // Value not found
    }

    public static void main(String[] args) {
        RBT<Integer, String> tree = new RBT<>();
        tree.insert(10, "A");
        tree.insert(5, "B");
        tree.insert(15, "C");
        tree.insert(2, "C");
        tree.insert(3, "C");
        tree.insert(9, "C");
        tree.insert(20, "C");

        System.out.println(tree.root.key); // Should print 10
        System.out.println(tree.root.value); // Should print 10
        System.out.println(tree.root.color); // Should print 10

        System.out.println(tree.root.left); // Should print 10
        System.out.println(tree.root.right); // Should print 10

        System.out.println(tree.search(15));
        System.out.println(tree.search(10));
        System.out.println(tree.search(5));
        System.out.println(tree.search(20));
    }
}