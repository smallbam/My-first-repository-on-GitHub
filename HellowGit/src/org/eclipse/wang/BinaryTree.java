package org.eclipse.wang;

/**
 * http://cslibrary.stanford.edu/110/BinaryTrees.html#java
 * 
 * @author super
 *
 */
public class BinaryTree
{
	private Node root;

	private class Node
	{
		private Node left;

		private Node right;

		private int data;

		private Node(int newData)
		{
			this.left = null;
			this.right = null;
			this.data = newData;
		}
	}

	public void insert(int data)
	{
		root = insert(root, data);
	}

	/**
	 * Recursive insert
	 */
	private Node insert(Node node, int data)
	{
		if (node == null)
		{
			node = new Node(data);
		}
		else
		{
			if (data <= node.data)
			{
				node.left = insert(node.left, data);
			}
			else
			{
				node.right = insert(node.right, data);
			}
		}
		return node;
	}

	public boolean lookup(int data)
	{
		return lookup(root, data);
	}

	/**
	 * Recursive lookup
	 */
	private boolean lookup(Node node, int data)
	{
		if (node == null)
		{
			return false;
		}
		if (data == node.data)
		{
			return true;
		}
		else if (data < node.data)
		{
			return lookup(node.left, data);
		}
		else
		{
			return lookup(node.right, data);
		}
	}

	/**
	 * Returns the max root-to-leaf depth of the tree. Uses a recursive helper
	 * that recurs down to find the max depth.
	 */
	public int size()
	{
		return size(root);
	}

	private int size(Node node)
	{
		if (node == null)
		{
			return 0;
		}
		else
		{
			return size(node.left) + 1 + size(node.right);
		}
	}

	/**
	 * Returns the max root-to-leaf depth of the tree. Uses a recursive helper
	 * that recurs down to find the max depth.
	 */
	public int maxDepth()
	{
		return maxDepth(root);
	}

	private int maxDepth(Node node)
	{
		if (node == null)
		{
			return 0;
		}
		else
		{
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);
			return Math.max(lDepth, rDepth) + 1;
		}
	}

	/**
	 * Returns the min value in a non-empty binary search tree. Uses a helper
	 * method that iterates to the left to find the min value.
	 */
	public int minValue()
	{
		return (minValue(root));
	}

	/**
	 * Finds the min value in a non-empty binary search tree.
	 */
	private int minValue(Node node)
	{
		Node current = node;
		while (current.left != null)
		{
			current = current.left;
		}

		return (current.data);
	}

	/**
	 * Prints the node values in the "inorder" order. Uses a recursive helper to
	 * do the traversal.
	 */
	public void printTreeInorder()
	{
		printTreeInorder(root);
		System.out.println();
	}

	private void printTreeInorder(Node node)
	{
		if (node == null)
			return;

		// left, node itself, right
		printTreeInorder(node.left);
		System.out.print(node.data + "  ");
		printTreeInorder(node.right);
	}

	public void printTreePreorder()
	{
		printTreePreorder(root);
		System.out.println();
	}

	private void printTreePreorder(Node node)
	{
		if (node == null)
		{
			return;
		}
		System.out.print(node.data + "  ");
		printTreePreorder(node.left);
		printTreePreorder(node.right);
	}

	public void printTreePostorder()
	{
		printTreePostorder(root);
		System.out.println();
	}

	private void printTreePostorder(Node node)
	{
		if (node == null)
		{
			return;
		}
		else
		{
			printTreePostorder(node.left);
			printTreePostorder(node.right);
			System.out.print(node.data + "  ");
		}
	}

	public boolean sameTree(BinaryTree other)
	{
		return (sameTree(root, other.root));
	}

	private boolean sameTree(Node a, Node b)
	{
		if (a == null && b == null)
		{
			return true;
		}
		else if (a != null && b != null)
		{
			return a.data == b.data && sameTree(a.left, b.left) && sameTree(a.right, b.right);
		}
		else
		{
			return false;
		}
	}
}
