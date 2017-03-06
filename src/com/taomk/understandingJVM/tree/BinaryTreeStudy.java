package com.taomk.understandingJVM.tree;

/**
 * 二叉树study
 * 
 * <pre>
 *           A 
 *     B          C 
 *  D     E            F
 * </pre>
 * @author taomk 2017年3月6日 下午2:59:02
 *
 */
public class BinaryTreeStudy {

	/**
	 * 根节点
	 */
	private TreeNode rootNode;

	BinaryTreeStudy() {
		rootNode = new TreeNode(1, "rootNode(A)");
	}

	public TreeNode getRootNode() {
		return rootNode;
	}
	
	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public static void main(String[] args) {

		BinaryTreeStudy binaryTree = new BinaryTreeStudy();  
        binaryTree.createBinaryTreeNode(binaryTree.getRootNode()); 
        
        System.out.println("The size of the tree is " + binaryTree.size());  
        System.out.println("The height of the tree is " + binaryTree.height());  
          
        System.out.println("*******(前序遍历)[ABDECF]遍历*****************");  
        binaryTree.preOrder(binaryTree.getRootNode());  
          
        System.out.println("*******(中序遍历)[DBEACF]遍历*****************");  
        binaryTree.inOrder(binaryTree.getRootNode());  
         
        System.out.println("*******(后序遍历)[DEBFCA]遍历*****************");  
        binaryTree.postOrder(binaryTree.getRootNode());  
          
//        System.out.println("***非递归实现****(前序遍历)[ABDECF]遍历*****************");  
//        binaryTree.nonRecPreOrder(binaryTree.getRootNode());  
//          
//        System.out.println("***非递归实现****(中序遍历)[DBEACF]遍历*****************");  
//        binaryTree.nonRecInOrder(binaryTree.getRootNode());  
//          
//        System.out.println("***非递归实现****(后序遍历)[DEBFCA]遍历*****************");  
//        binaryTree.noRecPostOrder(binaryTree.getRootNode());  
 
	}

	/**
	 * 检查二叉树是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return rootNode == null;
	}

	/**
	 * 返回树的高度
	 * 
	 * @return
	 */
	public int height() {
		return height(rootNode);
	}

	/**
	 * 返回树的节点个数
	 * 
	 * @return
	 */
	public int size() {
		return size(rootNode);
	}

	/**
	 * 返回元素的父节点
	 * 
	 * @param element
	 * @return
	 */
	TreeNode parent(TreeNode element) {
		if (rootNode == null || element == rootNode) {
			return null;
		} else {
			return parent(rootNode, element);
		}
	}

	/**
	 * 查找左子树
	 * 
	 * @param element
	 * @return
	 */
	public TreeNode getLeftChildNode(TreeNode element) {
		return (element != null) ? element.getLeftTreeNode() : null;
	}

	/**
	 * 查找右子树
	 * 
	 * @param element
	 * @return
	 */
	public TreeNode getRightChildNode(TreeNode element) {
		return (element != null) ? element.getRightTreeNode() : null;
	}

	/**
	 * 返回根节点
	 * 
	 * @return
	 */
	public TreeNode getRoot() {
		return rootNode;
	}

	/**
	 * 释放节点
	 * 
	 * @param subTree
	 */
	public void destory(TreeNode subTree) {
		if (subTree != null) {
			destory(subTree.getLeftTreeNode());
			destory(subTree.getRightTreeNode());
			subTree = null;
		}
	}

	/**
	 * 遍历
	 * 
	 * @param subTree
	 */
	public void traverse(TreeNode subTree) {
		System.out.println("key:" + subTree.getKey() + "-data:" + subTree.getData());
		traverse(subTree.getLeftTreeNode());
		traverse(subTree.getRightTreeNode());
	}

	/**
	 * 前序遍历
	 * 
	 * @param subTree
	 */
	public void preOrder(TreeNode subTree) {
		if (subTree != null) {
			visted(subTree);
			preOrder(subTree.getLeftTreeNode());
			preOrder(subTree.getRightTreeNode());
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param subTree
	 */
	public void inOrder(TreeNode subTree) {
		if (subTree != null) {
			inOrder(subTree.getLeftTreeNode());
			visted(subTree);
			inOrder(subTree.getRightTreeNode());
		}
	}

	/**
	 * 后续遍历
	 * 
	 * @param subTree
	 */
	public void postOrder(TreeNode subTree) {
		if (subTree != null) {
			postOrder(subTree.getLeftTreeNode());
			postOrder(subTree.getRightTreeNode());
			visted(subTree);
		}
	}

	private void visted(TreeNode subTree){  
        subTree.setVisited(true);  
        System.out.println("key:"+subTree.getKey()+"-data:"+subTree.getData());;  
    } 
	
	private TreeNode parent(TreeNode subTree, TreeNode element) {
		if (subTree == null) {
			return null;
		} else if (subTree.getLeftTreeNode() == element || subTree.getRightTreeNode() == element) {
			// 返回父结点地址
			return subTree;
		}

		TreeNode p;
		// 先在左子树中找，如果左子树中没有找到，才到右子树去找
		if ((p = parent(subTree.getLeftTreeNode(), element)) != null) {
			// 递归在左子树中搜索
			return p;
		} else {
			// 递归在右子树中搜索
			return parent(subTree.getRightTreeNode(), element);
		}
	}

	/**
	 * 递归遍历计算出二叉树节点个数
	 * 
	 * @param subNode
	 * @return
	 */
	private int size(TreeNode subNode) {
		if (subNode == null) {
			return 0;
		} else {
			return size(subNode.getLeftTreeNode()) + size(subNode.getRightTreeNode()) + 1;
		}
	}

	/**
	 * 递归遍历计算出二叉树的高度
	 * 
	 * @param subNode
	 * @return
	 */
	private int height(TreeNode subNode) {

		if (subNode == null) {
			return 0;
		} else {
			int leftNodeHeight = height(subNode.getLeftTreeNode());
			int rightNodeHeight = height(subNode.getRightTreeNode());
			return (leftNodeHeight > rightNodeHeight) ? (leftNodeHeight + 1) : (rightNodeHeight + 1);
		}
	}

	/**
	 * 创建一棵如下结构的二叉树。
	 *
	 * <pre>
	 *           A 
	 *     B          C 
	 *  D     E            F
	 * </pre>
	 * 
	 * 
	 * @param root
	 *            根节点
	 */
	public void createBinaryTreeNode(TreeNode root) {

		TreeNode nodeB = new TreeNode(2, "B");
		TreeNode nodeC = new TreeNode(3, "C");
		TreeNode nodeD = new TreeNode(4, "D");
		TreeNode nodeE = new TreeNode(5, "E");
		TreeNode nodeF = new TreeNode(6, "F");

		root.setLeftTreeNode(nodeB);
		root.setRightTreeNode(nodeC);

		root.getLeftTreeNode().setLeftTreeNode(nodeD);
		root.getLeftTreeNode().setRightTreeNode(nodeE);
		root.getRightTreeNode().setRightTreeNode(nodeF);
	}

}

/**
 * 定义一棵二叉树
 * 
 * @author taomk 2017年3月6日 下午3:05:17
 *
 */
class TreeNode {
	private int key = 0;
	private String data;
	private boolean isVisited = false;
	private TreeNode leftTreeNode;
	private TreeNode rightTreeNode;

	public TreeNode() {
		// Empty
	}

	public TreeNode(int key, String data) {
		this.key = key;
		this.data = data;
		leftTreeNode = null;
		rightTreeNode = null;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public TreeNode getLeftTreeNode() {
		return leftTreeNode;
	}

	public void setLeftTreeNode(TreeNode leftTreeNode) {
		this.leftTreeNode = leftTreeNode;
	}

	public TreeNode getRightTreeNode() {
		return rightTreeNode;
	}

	public void setRightTreeNode(TreeNode rightTreeNode) {
		this.rightTreeNode = rightTreeNode;
	}

}
