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
		System.out.println(q1(graph,  n0, n2));
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
}
