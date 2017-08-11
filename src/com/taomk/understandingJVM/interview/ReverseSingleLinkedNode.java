package com.taomk.understandingJVM.interview;

import java.util.Stack;

/**
 * 搜狐面试时提出的一个问题：倒序排列给出的单链表
 * 
 * @author taomk 2017年3月29日 下午3:39:55
 *
 */
public class ReverseSingleLinkedNode {

	public static void main(String[] args) {
		
		// 初始状态
		SingleLinkedNode singleLinked = new SingleLinkedNode(0);
		for (int i = 1; i <= 5; i++) {
			singleLinked.add(i);
		}
		
		System.out.print("init\t: ");
		SingleLinkedNode.show(singleLinked);
		
		// insert
		singleLinked.insert(6, 6);
		
		System.out.print("\ninsert\t: ");
		SingleLinkedNode.show(singleLinked);
		
		
		// delete
		singleLinked.delete(6);
		System.out.print("\ndelete\t: ");
		SingleLinkedNode.show(singleLinked);
		
		// reverse
		
		System.out.print("\nreverse\t: ");
//		Node reversedNode = Node.reverseByStack(singleLinked.getHead());
		Node reversedNode = Node.reverseByExchange(singleLinked.getHead());
		Node.show(reversedNode);
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
	 * 将指定值构造成一个节点，并添加在链表尾部
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
	
	/**
	 * 将指定节点添加在链表尾部
	 * @param data
	 */
	public void add(Node newNode){
		if(head == null){
			head = newNode;
		}else{
			tail.setNext(newNode);
		}
		tail = newNode;
	}
	
	
	/**
	 * 将代表指定值的节点插入到指定的index位置
	 * @param index
	 * @param data
	 */
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
	
	/**
	 * 展示链表数据
	 * 
	 * @param s
	 */
	public static void show(SingleLinkedNode s){
		Node current = s.head;
		while(current != null){
			System.out.print(current.getData());
			if(current.getNext()!=null){
				System.out.print(" -> ");
			}
			current = current.getNext();
		}
	}
		
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
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
	
	/**
	 * 使用栈来完成链表反转
	 * 
	 * @param head
	 * @return
	 */
	public static Node reverseByStack(Node head) {  
	    Stack<Node> stack = new Stack<Node>();  
	      
	    // put all the nodes into the stack  
	    while (head != null) {  
	        stack.add(head);  
	        head = head.getNext();  
	    }  
	      
	    //reverse the linked list  
	    Node current = stack.pop();  
	    head = current;  
	    while (stack.empty() != true) {  
	        Node next = stack.pop();  
	        //set the pointer to null, so the last node will not point to the first node.  
	        next.setNext(null);  
	        current.setNext(next);  
	        current = next;  
	    }  
	      
	    return head;      
	}  
	
	/**
	 * <pre>
	 * 利用两个指针，分别指向前一个节点和当前节点
	 * 每次做完当前节点和下一个节点的反转后，把两个节点往下移，直到到达最后节点。
	 * 
	 * 初始链表：A(head)->B->C->D
	 * 新的链表：null(previous)
	 * 
	 * 第一次遍历：
	 * 初始链表：B(head)->C->D
	 * 新的链表：A(previous)
	 * 
	 * 第二次遍历：
	 * 初始链表：C(head)->D
	 * 新的链表：B(previous)->A
	 * 
	 * 第三次遍历：
	 * 初始链表：D(head)
	 * 新的链表：C(previous)->B->A
	 * 
	 * 第四次遍历：
	 * 初始链表：null(head)
	 * 新的链表：D(previous)->C->B->A
	 * 
	 * </pre>
	 * 
	 * @param head 初始链表头
	 * @return 反转后的新链表头节点
	 */
	public static Node reverseByExchange(Node head) {  
	    Node previous = null;  
	  
	    while (head != null) {  
	        Node nextNode = head.getNext();  
	        head.setNext(previous);  
	        previous = head;  
	        head = nextNode;  
	    }  
	          
	    return previous;      
	}  

	
	public static void show(Node head){
		Node current = head;
		while(current != null){
			System.out.print(current.getData());
			if(current.getNext()!=null){
				System.out.print(" -> ");
			}
			current = current.getNext();
		}
	}
}