package trees;

import org.junit.Assert;
import org.junit.Test;

public class LowestCommonAncestorTest {

    @Test
    public void testCommonAncestor() {

        final LowestCommonAncestor<Integer> tree = new LowestCommonAncestor<>();
        final LowestCommonAncestor.Node<Integer> root = tree.addElement(1, null, LowestCommonAncestor.Position.None);

        final LowestCommonAncestor.Node<Integer> leftElement1 = tree.addElement(2, root, LowestCommonAncestor.Position.Left);
        final LowestCommonAncestor.Node<Integer> rightElement1 = tree.addElement(3, root, LowestCommonAncestor.Position.Right);

        final LowestCommonAncestor.Node<Integer> leftElement2 = tree.addElement(4, leftElement1, LowestCommonAncestor.Position.Left);
        final LowestCommonAncestor.Node<Integer> leftElement3 = tree.addElement(5, leftElement1, LowestCommonAncestor.Position.Right);

        final LowestCommonAncestor.Node<Integer> leftElement4 = tree.addElement(8, leftElement3, LowestCommonAncestor.Position.Left);

        final LowestCommonAncestor.Node<Integer> rightElement2 = tree.addElement(6, rightElement1, LowestCommonAncestor.Position.Left);

        Assert.assertEquals(tree.findCommonAncestor(leftElement2, leftElement3).getData().intValue(), 2);

        Assert.assertEquals(tree.findCommonAncestor(leftElement2, rightElement2).getData().intValue(), 1);

        Assert.assertEquals(tree.findCommonAncestor(leftElement2, leftElement4).getData().intValue(), 2);

        Assert.assertEquals(tree.findCommonAncestor(leftElement1, leftElement4).getData().intValue(), 1);
    }
}
