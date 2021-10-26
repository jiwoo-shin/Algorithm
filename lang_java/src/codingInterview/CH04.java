package codingInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import common.Solution;

public class CH04 extends Solution {

	@Override
	public void solution() throws IOException {
		Node n0 = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		n0.add(new Node[] {n1, n4});
		n4.add(new Node[] {n1});
		n1.add(new Node[] { n2});
		Graph graph = new Graph(5, new Node[] {n0, n1, n2, n3, n4});
		// System.out.println(q1(graph,  n0, n2));
		//System.out.println(q2(new int[] {1,2,3,4,5,6,7,8}));

		TreeNode t0 = new TreeNode(0, 0);
		TreeNode t1 = new TreeNode(1, 1);
		TreeNode t2 = new TreeNode(1, 2);
		TreeNode t3 = new TreeNode(2, 3);
		TreeNode t4 = new TreeNode(2, 4);
		TreeNode t5 = new TreeNode(2, 5);
		TreeNode t6 = new TreeNode(3, 6);
		t0.addLeft(t1);
		t0.addRight(t2);
		t1.addLeft(t3);
		t1.addRight(t4);
		t2.addLeft(t5);
		t3.addLeft(t6);
		//q3(t0);
		q3_1(t0);
	}
	class Node {
		int number;
		ArrayList<Node> list;
		Node(int number) {
			this.list = new ArrayList<Node>();
			this.number = number;
		}
		void add(Node[] list) {
			for(int i = 0; i < list.length; i++) this.list.add(list[i]);
		}
		public String toString() {
			String tmp = "["+this.number+"] ";
			for(int i = 0; i < list.size(); i++) tmp += list.get(i)+" ";
			return tmp;
		}
	}
	class Graph {
		Node[] nodeList;
		int length;
		public Graph(int length, Node[] list) {
			this.nodeList = new Node[length];
			for(int i = 0; i < length; i++) {
				this.nodeList[i] = list[i];
			}
			this.length = length;
		}
	}
	boolean q1(Graph graph, Node start, Node end) {
		Queue<Node> queue = new LinkedList<Node>();
		boolean[] visited = new boolean[graph.length];
		queue.add(start);
		visited[start.number] = true;
		while(!queue.isEmpty()) {
			Node present = queue.remove();
			for(int i = 0; i < present.list.size(); i++) {
				Node next = present.list.get(i);
				if(!visited[next.number]) {
					if(next.number == end.number) return true;
					visited[next.number] = true;
					queue.add(next);
				}
			}
		}
		
		return false;
	}
	TreeNode q2(int[] list) {
		int maxLength = (int) (Math.log(list.length)/Math.log(2)); // 몇 층까지 있는지 (0층부터 length 층 까지 있음)
		int lastIndex = list.length - (int)Math.pow(2, maxLength); // 마지막 층은 몇번째 인덱스까지 있는지 (0~lastIndex까지)
		return createTree(0, 0, maxLength, lastIndex, 0, list);
	}
	LinkedList q3(TreeNode root) {
		LinkedList<LinkedList> list = new LinkedList<LinkedList>();
		Queue<TreeNode> tmp = new LinkedList<TreeNode>();
		tmp.add(root);
		while(!tmp.isEmpty()) {
			TreeNode presentNode = tmp.poll();
			if(list.size() <= presentNode.step) list.add(new LinkedList<>());
			list.get(presentNode.step).add(presentNode);
			if(presentNode.leftChild != null) {
				tmp.add(presentNode.leftChild);
			}
			if(presentNode.rightChild != null) {
				tmp.add(presentNode.rightChild);
			}
		}
		for(int i = 0; i < list.size(); i++) {
			LinkedList tmp1 = list.get(i);
			System.out.print("["+i+"] ");
			for(int j = 0; j < tmp1.size(); j++) {
				System.out.print(((TreeNode)tmp1.get(j)).number+" ");
			}
			System.out.println();
		}
		return list;
	}
	LinkedList q3_1(TreeNode root) {
		LinkedList<LinkedList> list = new LinkedList<LinkedList>();
		recursiveAdd(0, root, list);
		for(int i = 0; i < list.size(); i++) {
			LinkedList tmp1 = list.get(i);
			System.out.print("["+i+"] ");
			for(int j = 0; j < tmp1.size(); j++) {
				System.out.print(((TreeNode)tmp1.get(j)).number+" ");
			}
			System.out.println();
		}
		return list;
	}
	void recursiveAdd(int step, TreeNode node, LinkedList<LinkedList> list) {
		if(list.size() <= step) list.add(new LinkedList<TreeNode>());
		LinkedList<TreeNode> tmp = list.get(step);
		tmp.add(node);
		if(node.leftChild != null) recursiveAdd(step+1, node.leftChild, list);
		if(node.rightChild != null) recursiveAdd(step+1, node.rightChild, list);
	}
	class TreeNode {
		int step; // 몇 층인지..
		
		int number;
		TreeNode leftChild;
		TreeNode rightChild;
		TreeNode() {
			
		}
		TreeNode(int step, int number) {
			this.step = step;
			this.number = number;
		}
		public void addLeft(TreeNode left) {
			this.leftChild = left;
		}
		public void addRight(TreeNode right) {
			this.rightChild = right;
		}
		public String toString() {
			/*
			if(leftChild != null) tmp += leftChild.toString()+" ";
			tmp += this.number+" ";
			if(rightChild != null) tmp += rightChild.toString()+" ";
			*/
			String tmp = "";
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			System.out.print(this.number+" ("+this.step+")  ");
			queue.add(this.leftChild);
			queue.add(this.rightChild);
			int presentStep = 0;
			while(!queue.isEmpty()) {
				TreeNode tmpNode = queue.remove();
				if(tmpNode != null) {
					String print = tmpNode.number+" ("+tmpNode.step+")  ";
					if(tmpNode.step != presentStep) {
						 print = "\n" + print;
						presentStep = tmpNode.step;
					}
					System.out.print(print);
					if(tmpNode.leftChild != null)queue.add(tmpNode.leftChild);
					if(tmpNode.rightChild != null)queue.add(tmpNode.rightChild);
				}
			}
			return tmp;
			
		}
	}
	TreeNode createTree(int nowLength, int index, int maxLength, int lastIndex, int number, int[] numberList) {
		if(nowLength < maxLength || (nowLength == maxLength && index <= lastIndex)) {
			TreeNode node = new TreeNode();
			int leftIndex =  2 * index; // 해당 층에서의 index .. 
			int leftNumber = (int)Math.pow(2, nowLength+1)-1 + leftIndex;
			// 왼쪽
			node.leftChild = createTree(nowLength + 1, leftIndex, maxLength, lastIndex, leftNumber, numberList);
			// 본인
			node.number = numberList[number];
			node.step = nowLength;
			// 오른쪽
			node.rightChild = createTree(nowLength + 1, leftIndex + 1, maxLength, lastIndex, leftNumber+1, numberList);
			return node;
		}
		return null;
	}
}
