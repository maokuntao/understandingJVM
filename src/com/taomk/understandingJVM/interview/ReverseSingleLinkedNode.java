package com.taomk.understandingJVM.interview;

/**
 * 搜狐面试时提出的一个问题：倒序排列给出的单链表
 * 
 * @author taomk 2017年3月29日 下午3:39:55
 *
 */
public class ReverseSingleLinkedNode {

	public static void main(String[] args) {
		
		SingleLinkedNode head = new SingleLinkedNode(0);
		for (int i = 1; i <= 5; i++) {
			head.add(i);
		}
		
		head.show(head);

	}

}

/**
 * 单链表实现
 * 
 * @author taomk 2017年3月29日 下午6:10:54
 *
 */
class SingleLinkedNode{
	
	/**
	 * 头节点、尾节点
	 */
	private Node head , tail;
	
	SingleLinkedNode(Object data){
		
		tail = head = new Node(data);
	}
	
	
	/**
	 * 将指定节点添加在链表尾部
	 * @param data
	 */
	public void add(Object data){
		Node newNode = new Node(data);
		if(head == null){
			head = newNode;
		}else{
			tail.setNext(newNode);
		}
		tail = newNode;
	}
	
	public void insert(int index, Object data){
		
		// 遍历查找出位置为index-1的那个节点，即为将要插入节点的前一个节点
		Node aheadNode = head;
		int aheadIndex = 0;
		while(aheadNode!=null && aheadIndex<index-2){
			aheadNode = aheadNode.getNext();
			aheadIndex++;
		}
		
		// 将要插入节点的后一个节点
		Node behindNode = aheadNode.getNext();
		Node currentNode = new Node(data);
		aheadNode.setNext(currentNode);
		currentNode.setNext(behindNode);
	}
	
	/**
	 * 删除指定位置的节点
	 * 
	 * @param index
	 */
	public void delete(int index){
		
		// 找到位置为index-1的那一个元素，即待删除节点的前一个节点
		Node aheadNode = head;
		int aheadIndex = 0;
		while(aheadNode!=null && aheadIndex<index-2){
			aheadNode = aheadNode.getNext();
			aheadIndex++;
		}
		
		aheadNode.setNext(aheadNode.getNext().getNext());
		
	}
	
	public void show(SingleLinkedNode s){
		Node current = s.head;
		while(current!=null){
			System.out.print(current.getData() + " -> ");
			current = current.getNext();
		}
	}
}


/**
 * 节点定义
 * 
 * @author taomk 2017年3月29日 下午4:56:08
 *
 */
class Node {

	/**
	 * 节点数据
	 */
	private Object data;
	
	
	/**
	 * 下一个节点
	 */
	private Node next;

	Node(Object value) {
		this.data = value;
		this.next = null;
	}

	public Object getData() {
		return data.toString();
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}