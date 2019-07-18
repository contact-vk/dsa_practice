package trees;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer, String> tree;

    @Before
    public void setup() {
        tree = new BinarySearchTree<>();
        tree.insert(15, "root");
        tree.insert(6, "first");
        tree.insert(6, "eighth");
        tree.insert(6, "ninth");
        tree.insert(7, "second");
        tree.insert(4, "third");
        tree.insert(5, "fourth");
        tree.insert(23, "fifth");
        tree.insert(71, "sixth");
        tree.insert(50, "seventh");
    }

    @Test
    public void testSearch() {
        Assert.assertNotNull(tree.search(15));
        Assert.assertNotNull(tree.search(7));
        Assert.assertNull(tree.search(21));
    }

    @Test
    public void testMin() {
        Assert.assertEquals(tree.min(),  "third");
    }

    @Test
    public void testMax() {
        Assert.assertEquals(tree.max(),  "sixth");
    }

    @Test
    public void testSuccessor() {
        Assert.assertEquals(tree.successor(23).getValue(), "seventh");
        Assert.assertEquals(tree.successor(7).getValue(), "root");
        Assert.assertNull(tree.successor(71));
    }

    @Test
    public void testPredecessor() {
        Assert.assertEquals(tree.predecessor(6).getValue(), "fourth");
        Assert.assertEquals(tree.predecessor(50).getValue(), "fifth");
        Assert.assertNull(tree.predecessor(4));
    }

    @Test
    public void testRemove() {
        tree.printInOrder();
        System.out.println("------");

        tree.remove(5);
        tree.printInOrder();
        System.out.println("------");

        tree.remove(23);
        tree.printInOrder();
        System.out.println("------");

        tree.remove(6);
        tree.printInOrder();
        System.out.println("------");
    }

    @Test
    public void testRank() {
        Assert.assertEquals(tree.rank(50), 9);
        Assert.assertEquals(tree.rank(99), -1);
        Assert.assertEquals(tree.rank(4), 1);
        Assert.assertEquals(tree.rank(71), 10);
    }

    @Test
    public void testSelect() {
        Assert.assertEquals(tree.select(9).getValue(), "seventh");
        Assert.assertNull(tree.select(0));
        Assert.assertEquals(tree.select(1).getValue(), "third");
        Assert.assertEquals(tree.select(10).getValue(), "sixth");
    }
}
