package codingInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

import common.Solution;

public class CH02 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//print(q1(createSingleLinkedList(10)));
		//System.out.println(q2(createSingleLinkedList(10), Integer.parseInt(br.readLine())));
		//print(q3(createDoubleLinkedList(Integer.parseInt(br.readLine()))));
		print(q4(createSingleLinkedList(10), 4));
	}
	static public class DoubleLinkedList {
		public Node head;
		public Node tail;
		public DoubleLinkedList(Node head, Node tail) {
			this.head = head;
			this.tail = tail;
		}
		public void add(int add) { // n을 마지막에 추가
			if(this.head == null) {
				Node n = new Node(null, null, add);
				this.head = n;
				this.tail = n;
				return;
			}
			Node start = this.head;
			while(start.next != null) {
				start = start.next;
			}
			Node n = new Node(null, start, add);
			start.next = n;
			this.tail = n;
		}
	}
	static public class Node {
		public Node next;
		public Node prev;
		public int data;
		public Node(Node next, Node prev, int data) {
			this.next = next;
			this.prev = prev;
			this.data = data;
		}
	}
	static LinkedList<Integer> createSingleLinkedList(int n) {
		LinkedList<Integer> data = new LinkedList<Integer>();
		for(int i = 0; i < n; i++) {
			data.add((int)(Math.random()*10));
		}
		print(data);
		return data;
	}
	static DoubleLinkedList createDoubleLinkedList(int n) {
		DoubleLinkedList data = new DoubleLinkedList(null, null);
		for(int i = 0; i < n; i++) {
			data.add((int)(Math.random()*10));
		}
		print(data);
		return data;
	}
	// 중복요소 제거
	static HashSet<Integer> q1(LinkedList<Integer> data) {
		HashSet<Integer> hash = new HashSet<Integer>();
		Iterator<Integer> it = data.iterator();
		while(it.hasNext()) {
			hash.add(it.next());
		}
		return hash;
	}
	
	// 뒤에서 k 번째 요소 구하기
	static int q2(LinkedList<Integer> data, int k) {
		if(k <= 0 || k > data.size()) return Integer.MIN_VALUE;
		Iterator<Integer> fast = data.iterator();
		Iterator<Integer> slow = data.iterator();
		int index = 0;
		while(fast.hasNext()) {
			if(index > k-1) slow.next(); // 마지막 반환 시 next() 호출하므로.. k-1보다 클 경우부터 한다.
			index++;
			fast.next();
		}
		return slow.next();
	}

	// 중간노드 제거
	static DoubleLinkedList q3(DoubleLinkedList data) {
		/*
		if(data.size()%2 == 0) return data;
		Iterator<Integer> fast = data.iterator();
		Iterator<Integer> slow = data.iterator();
		int moveFast = 0;
		while(fast.hasNext()) {
			fast.next();
			moveFast++;
			if(moveFast == 2) {
				moveFast = 0;
				slow.next();
			}
		}
		slow.next();
		slow.remove();
		return data;*/
		
		// 삭제할 노드에만 접근 가능
		data.head.next.next.prev = data.head;
		data.head.next = data.head.next.next;
		return data;
	}

	// 분할
	static DoubleLinkedList q4(LinkedList<Integer> data, int k) {
		DoubleLinkedList result = new DoubleLinkedList(null, null);
		DoubleLinkedList after = new DoubleLinkedList(null, null);
		Iterator<Integer> it = data.iterator();
		
		while(it.hasNext()) {
			int d = it.next();
			if(d < k) {
				result.add(d);
			} else {
				after.add(d);
			}
		}
		result.tail.next = after.head;
		after.head.prev = result.tail;
		
		return result;
	}
	
	
}
