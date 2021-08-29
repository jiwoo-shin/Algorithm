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
		System.out.println(q2(createSingleLinkedList(10), Integer.parseInt(br.readLine())));
		createDoubleLinkedList(10);
	}
	static LinkedList<Integer> createSingleLinkedList(int n) {
		LinkedList<Integer> data = new LinkedList<Integer>();
		for(int i = 0; i < n; i++) {
			data.add((int)(Math.random()*10));
		}
		return data;
	}
	static DoubleLinkedList createDoubleLinkedList(int n) {
		DoubleLinkedList data = new DoubleLinkedList(null, null);
		for(int i = 0; i < n; i++) {
			data.add((int)(Math.random()*10));
		}
		return data;
	}
	// 중복요소 제거
	static HashSet<Integer> q1(LinkedList<Integer> data) {
		print(data);
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
		print(data);
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
	
	
	
	static class DoubleLinkedList {
		Node head;
		Node tail;
		DoubleLinkedList(Node head, Node tail) {
			this.head = head;
			this.tail = tail;
		}
		void add(int add) { // n을 마지막에 추가
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
	static class Node {
		Node next;
		Node prev;
		int data;
		Node(Node next, Node prev, int data) {
			this.next = next;
			this.prev = prev;
			this.data = data;
		}
	}
	
}
