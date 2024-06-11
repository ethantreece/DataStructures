package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.tree.AbstractTree.PositionCollection;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    
    @Override
    public Iterable<Position<E>> inOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }

    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
        if (p != null) {
            inOrderHelper(left(p), traversal);
            traversal.add(p);
            inOrderHelper(right(p), traversal);
        }
    }
    
    @Override
    public int numChildren(Position<E> p) {
        int children = 0;
        if (left(p) != null) {
            children++;
        }
        if (right(p) != null) {
            children++;
        }
        return children;
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
        if (p == root()) {
            return null;
        }
        validate(p);
        if (left(parent(p)).getElement() == p.getElement()) {
            return right(parent(p));
        } else {
            return left(parent(p));
        }
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}