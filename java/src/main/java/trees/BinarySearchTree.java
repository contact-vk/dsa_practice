package trees;

import java.util.ArrayDeque;
import java.util.Stack;

@SuppressWarnings("WeakerAccess")
public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(final K key, final V value) {
        root = new Node(key, value);
    }

    /**
     * This will add values to tree
     *
     * @param key   key corresponding to value
     * @param value value to be saved
     */
    public void insert(final K key, final V value) {
        final Node toBeInsertedNode = new Node(key, value);
        if (this.root == null) {
            this.root = toBeInsertedNode;
        } else {
            insertInternal(this.root, toBeInsertedNode);
        }
    }

    /**
     * Helper function to be used by {@link #insert(Comparable, Object)}
     *
     * @param root             root of tree/subtree under which key has to be inserted
     * @param toBeInsertedNode Node which has to be inserted in tree
     */
    private void insertInternal(final Node root, final Node toBeInsertedNode) {

        final int compareResult = toBeInsertedNode.key.compareTo(root.key);

        if (compareResult <= 0) {
            if (root.left != null) {
                insertInternal(root.left, toBeInsertedNode);
            } else {
                root.left = toBeInsertedNode;
                toBeInsertedNode.parent = root;
            }
        } else {
            if (root.right != null) {
                insertInternal(root.right, toBeInsertedNode);
            } else {
                root.right = toBeInsertedNode;
                toBeInsertedNode.parent = root;
            }
        }
    }

    /**
     * This will perform the in order traversal and print the tree
     */
    public void printInOrder() {
        printInOrderInternal(this.root);
    }

    /**
     * Helper function to print the tree
     *
     * @param root root of tree/subtree
     */
    private void printInOrderInternal(final Node root) {

        if (root == null) {
            return;
        }

        printInOrderInternal(root.left);
        System.out.println(root);
        printInOrderInternal(root.right);
    }

    /**
     * This will perform the pre-order traversal and print the tree
     */
    public void printPreOrder() {
        printPreOrderInternal(this.root);
    }

    /**
     * Helper function to be called from {@link #printPreOrder()}
     *
     * @param root root of tree/subtree
     */
    private void printPreOrderInternal(final Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        printPreOrderInternal(root.left);
        printPreOrderInternal(root.right);
    }

    /**
     * This will perform the post-order traversal and print the tree
     */
    public void printPostOrder() {
        printPostOrderInternal(this.root);
    }

    /**
     * Helper function to be called from {@link #printPostOrder()}
     *
     * @param root root of tree/subtree
     */
    public void printPostOrderInternal(final Node root) {
        if (root == null) {
            return;
        }
        printPreOrderInternal(root.left);
        printPreOrderInternal(root.right);
        System.out.println(root);
    }

    /**
     * This will remove first node with given key
     *
     * @param key key to be removed
     */
    public void remove(final K key) {

        Node rootNode = this.root;

        //noinspection ConditionalBreakInInfiniteLoop
        while (true) {

            // Key not found scenario
            if (rootNode == null) {
                break;
            }

            final int compareResult = key.compareTo(rootNode.key);

            // Match found
            if (compareResult == 0) {

                // Case 1 : Node is leaf node
                if (rootNode.left == null && rootNode.right == null) {

                    final Node parent = rootNode.parent;
                    rootNode.parent = null;

                    if (parent == null) {
                        this.root = null;
                    } else if (parent.left == rootNode) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }

                    // Case 2 : If Node has 1 child and that is right
                } else if (rootNode.left == null) {

                    final Node parent = rootNode.parent;
                    rootNode.parent = null;

                    if (parent == null) {
                        this.root = rootNode.right;
                    } else if (parent.left == rootNode) {
                        parent.left = rootNode.right;
                    } else {
                        parent.right = rootNode.right;
                    }

                    // Case 3 : If Node has 1 child and that is left
                } else if (rootNode.right == null) {

                    final Node parent = rootNode.parent;
                    rootNode.parent = null;

                    if (parent == null) {
                        this.root = rootNode.left;
                    } else if (parent.left == rootNode) {
                        parent.left = rootNode.left;
                    } else {
                        parent.right = rootNode.left;
                    }

                    // Case 4 : If Node has 2 child
                } else {

                    final Node successor = successorInternal(rootNode);
                    final K key1 = successor.key;
                    final V value1 = successor.value;
                    remove(key1);
                    rootNode.key = key1;
                    rootNode.value = value1;

                }

                rootNode = null;

            } else if (compareResult < 0) {
                rootNode = rootNode.left;
            } else {
                rootNode = rootNode.right;
            }
        }
    }

    /**
     * Search function which will return value for a given key
     *
     * @param key key to be searched
     * @return value corresponding to key
     */
    public Node search(final K key) {
        return searchInternal(root, key);
    }

    /**
     * Helper function to be used by {@link #search(Comparable)}
     *
     * @param root root of tree/subtree
     * @param key  key to be searched
     * @return value corresponding to key
     */
    private Node searchInternal(final Node root, final K key) {

        if (root == null) {
            return null;
        }

        final int compareResult = key.compareTo(root.key);

        if (compareResult < 0) {
            return searchInternal(root.left, key);
        } else if (compareResult == 0) {
            return root;
        } else {
            return searchInternal(root.right, key);
        }
    }

    /**
     * This will return the minimum element in tree
     *
     * @return minimum element
     */
    public V min() {
        return minimumInternal(root).value;
    }

    /**
     * Helper function to be used by {@link #min()}, {@link #successor(Comparable)}
     *
     * @param root root element of tree/subtree
     * @return minimum element if exists i.e root is not null else null
     */
    private Node minimumInternal(final Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return minimumInternal(root.left);
    }

    /**
     * This will return maximum element in tree
     *
     * @return maximum element
     */
    public V max() {
        return maximumInternal(root).value;
    }

    /**
     * Helper function to be used by {@link #max()}
     *
     * @param root root element of tree/subtree
     * @return maximum element
     */
    private Node maximumInternal(final Node root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return maximumInternal(root.right);
    }

    /**
     * This will return the previous node in in-order traversal
     *
     * @param key key for which we need predecessor
     * @return predecessor if exist else null
     */
    public Node predecessor(final K key) {

        final Node nodeForGivenKey = search(key);

        if (nodeForGivenKey == null) {
            return null;
        }

        if (nodeForGivenKey.left != null) {
            return maximumInternal(nodeForGivenKey.left);
        } else {
            Node parent = nodeForGivenKey.parent;
            Node current = nodeForGivenKey;
            while (parent != null && current == parent.left) {
                current = parent;
                parent = current.parent;
            }
            return parent;
        }
    }

    /**
     * This will return the next node in-order traversal
     *
     * @param key key for which we need successor
     * @return successor if exist else null
     */
    public Node successor(final K key) {

        final Node nodeForGivenKey = search(key);
        return successorInternal(nodeForGivenKey);
    }

    /**
     * Helper function to be called from {@link #successor(Comparable)} and {@link #remove(Comparable)}
     *
     * @param rootNode root node of tree/subtree
     * @return successor if exist else null
     */
    private Node successorInternal(final Node rootNode) {
        if (rootNode == null) {
            return null;
        }

        if (rootNode.right != null) {
            return minimumInternal(rootNode.right);
        } else {
            Node parent = rootNode.parent;
            Node current = rootNode;
            while (parent != null && current == parent.right) {
                current = parent;
                parent = current.parent;
            }
            return parent;
        }
    }

    /**
     * Rank(key) = position in the sorted BST with index one
     * i.e rank(min()) = 1 and rank(max()) = N
     * if key doesn't exist return -1
     *
     * @param key key used to find rank
     * @return rank of that key
     */
    public int rank(final K key) {

        Node rootNode = this.root;

        // If tree is empty, then rank is 0
        if (rootNode == null) {
            return 0;
        }

        final ArrayDeque<Node> queueToHoldElementsLessThanKey = new ArrayDeque<>();
        final Stack<Node> toBeProcessed = new Stack<>();
        boolean found = false;
        toBeProcessed.push(rootNode);

        while (!toBeProcessed.isEmpty()) {

            while (rootNode.left != null) {
                rootNode = rootNode.left;
                toBeProcessed.push(rootNode);
            }

            final Node current = toBeProcessed.pop();
            queueToHoldElementsLessThanKey.offerFirst(current);

            final int compareResult = key.compareTo(current.key);
            if (compareResult == 0) {
                found = true;
                break;
            }
            if (current.right != null) {
                rootNode = current.right;
                toBeProcessed.push(rootNode);
            }
        }
        return found ? queueToHoldElementsLessThanKey.size() : -1;
    }

    /**
     * For a given rank, return the Node with that rank
     * i.e select(1) = {@link #min()}, select(N) = {@link #max()}
     *
     * @param rank rank of element
     * @return key with the specified rank
     */
    public Node select(final int rank) {

        Node rootNode = this.root;

        // If tree is empty, then rank is 0
        if (rootNode == null || rank == 0) {
            return null;
        }

        final ArrayDeque<Node> queueToHoldElementsTillRankReached = new ArrayDeque<>();
        final Stack<Node> toBeProcessed = new Stack<>();
        toBeProcessed.push(rootNode);

        while (!toBeProcessed.isEmpty()) {

            while (rootNode.left != null) {
                rootNode = rootNode.left;
                toBeProcessed.push(rootNode);
            }

            final Node current = toBeProcessed.pop();
            queueToHoldElementsTillRankReached.offerFirst(current);

            if (queueToHoldElementsTillRankReached.size() == rank) {
                return current;
            }
            if (current.right != null) {
                rootNode = current.right;
                toBeProcessed.push(rootNode);
            }
        }
        return null;
    }


    public class Node {

        private K key;

        private V value;

        private Node left;

        private Node right;

        private Node parent;

        Node(final K key, final V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("[%s - %s]", key, value);
        }
    }
}
