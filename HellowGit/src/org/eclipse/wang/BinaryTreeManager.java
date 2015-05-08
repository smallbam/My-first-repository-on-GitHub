package org.eclipse.wang;

public class BinaryTreeManager
{
	/**
	 * 观察二叉树和广义表，得出一些结论：
	 * 
	 * 每当遇到字母，表明要创建节点 <br>
	 * 每当遇到“（”，表明要创建左孩子节点<br>
	 * 每当遇到“，”，表明要创建右孩子节点<br>
	 * 每当遇到“）”，表明要返回上一层节点<br>
	 * 广义表中“（”的数量正好是二叉树的层数
	 * 
	 * @param expression
	 * @return
	 */
	public BinaryTree createBinaryTree(String expression)
	{
		char[] chars = expression.toCharArray();
		return null;
	}

}
