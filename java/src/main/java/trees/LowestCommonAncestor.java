package trees;

import java.util.ArrayList;
import java.util.List;

class LowestCommonAncestor<T> {

    private Node<T> root;

    LowestCommonAncestor() {
        this.root = null;
    }

    Node<T> addElement(final T data, final Node<T> parent, final Position position) {
        final Node<T> currentNode = new Node<>(data);
        if (this.root == null) {
            this.root = currentNode;
        } else {
            if (position == Position.Left) {
                parent.left = currentNode;
            } else if (position == Position.Right) {
                parent.right = currentNode;
            } else {
                throw new RuntimeException("Position can be Left or Right only");
            }
        }
        return currentNode;
    }

    Node<T> findCommonAncestor(final Node<T> first, final Node<T> second) {

        final int heightOfTree = height();
        final int maxNoOfElements = (int) (Math.pow(2, heightOfTree) - 1);
        final List<Node<T>> levelOrderList = new ArrayList<>(maxNoOfElements);

        for (int i = 0; i < heightOfTree; i++) {
            toArray(this.root, levelOrderList, i, 1);
        }

        int firstIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < levelOrderList.size(); i++) {
            if (levelOrderList.get(i) == first) {
                firstIndex = i;
            }
            if (levelOrderList.get(i) == second) {
                secondIndex = i;
            }
        }

        final List<Integer> listOfAncestorsForFirst = new ArrayList<>();

        while (true) {

            final int ancestorPosition = (firstIndex + 1) % 2 == 0 ? (firstIndex + 1) / 2 : (firstIndex + 1 - 1) / 2;
            listOfAncestorsForFirst.add(ancestorPosition);

            if (ancestorPosition == 1) {
                break;
            }
            firstIndex = ancestorPosition - 1;
        }

        while (true) {

            final int ancestorForSecond = (secondIndex + 1) % 2 == 0 ? (secondIndex + 1) / 2 : (secondIndex + 1 - 1) / 2;

            if (listOfAncestorsForFirst.contains(ancestorForSecond)) {
                return levelOrderList.get(ancestorForSecond - 1);
            }

            secondIndex = ancestorForSecond - 1;
        }
    }

    private int height() {
        return heightInternal(this.root);
    }

    private int heightInternal(final Node<T> rootNode) {
        if (rootNode == null) {
            return 0;
        }

        final int leftMaxHeight = heightInternal(rootNode.left);
        final int rightMaxHeight = heightInternal(rootNode.right);
        return Math.max(leftMaxHeight, rightMaxHeight) + 1;
    }

    private void toArray(final Node<T> rootNode, final List<Node<T>> array, final int level, final int position) {
        if (level == 0) {
            array.add(position - 1, rootNode);
            return;
        }
        if (rootNode == null) {
            return;
        }
        toArray(rootNode.left, array, level - 1, 2 * position);
        toArray(rootNode.right, array, level - 1, (2 * position) + 1);
    }

    static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        Node(final T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        T getData() {
            return data;
        }
    }

    enum Position {
        Left,
        Right,
        None
    }
}
