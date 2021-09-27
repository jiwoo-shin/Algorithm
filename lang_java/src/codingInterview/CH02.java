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
		// print(q4(createSingleLinkedList(10), 4));
		// print(q5(new LinkedList<Integer>(), 0, createSingleLinkedList(3).iterator(), createSingleLinkedList(3).iterator()));
		//print(q51(new LinkedList<Integer>(), createSingleLinkedList(3).iterator(), createSingleLinkedList(3).iterator()));
		//LinkedList<Integer> tmp = createSingleLinkedList(new int[] {1,2,3,32,1});
		//System.out.println(q6(tmp, tmp.iterator()));
		//SingleLinkedList common = createSingleCustomLinkedList(new int[] {1,2,3});
		//SingleLinkedList d1 = createSingleCustomLinkedList(new int[] {1,2});
		//SingleLinkedList d2 = createSingleCustomLinkedList(new int[] {1});
		//d1.add(common);
		// d2.add(common);
		//print(q7(d1, d2));
		SingleLinkedList tmp = createSingleCustomLinkedList(new int[] {1,2,3,4,5});
		Node cir = tmp.head.next.next;
		tmp.add(cir);
		print(q8(tmp));
	}
	static public class SingleLinkedList {
		public Node head;
		public SingleLinkedList(Node head) {
			this.head = head;
		}
		public void add(SingleLinkedList add) {
			if(this.head == null) {
				this.head = add.head;
				return;
			}
			Node start = this.head;
			while(start.next != null) {
				start = start.next;
			}
			start.next = add.head;
		}
		public void add(int add) { // n을 마지막에 추가
			if(this.head == null) {
				Node n = new Node(null, add);
				this.head = n;
				return;
			}
			Node start = this.head;
			while(start.next != null) {
				start = start.next;
			}
			Node n = new Node(null, add);
			start.next = n;
		}
		public void add(Node add) { // n을 마지막에 추가
			if(this.head == null) {
				this.head = add;
				return;
			}
			Node start = this.head;
			while(start.next != null) {
				start = start.next;
			}
			Node n = add;
			start.next = n;
		}
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
		public Node(Node next, int data) {
			this.next = next;
			this.prev = null;
			this.data = data;
		}
		public Node(Node next, Node prev, int data) {
			this.next = next;
			this.prev = prev;
			this.data = data;
		}
	}
	static SingleLinkedList createSingleCustomLinkedList(int[] n) {
		SingleLinkedList data = new SingleLinkedList(null);
		for(int i = 0; i < n.length; i++) {
			data.add(n[i]);
		}
		print(data);
		return data;
	}
	static LinkedList<Integer> createSingleLinkedList(int n) {
		LinkedList<Integer> data = new LinkedList<Integer>();
		for(int i = 0; i < n; i++) {
			data.add((int)(Math.random()*10));
		}
		print(data);
		return data;
	}
	static LinkedList<Integer> createSingleLinkedList(int[] n) {
		LinkedList<Integer> data = new LinkedList<Integer>();
		for(int i = 0; i < n.length; i++) {
			data.add(n[i]);
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
	
	// 연결리스트 합 - 역순
	static LinkedList<Integer> q5(LinkedList<Integer> sum, int index, Iterator<Integer> number1, Iterator<Integer> number2) {
		if(!number1.hasNext() && !number2.hasNext()) return sum;
		int num1 = number1.hasNext()? number1.next() : 0;
		int num2 = number2.hasNext()? number2.next() : 0;
		int add = num1 + num2 + ((sum.size() > index) ? sum.remove(index) : 0);
		sum.add(add % 10);
		if(add / 10 > 0)sum.add(add / 10);
		q5(sum, index+1, number1, number2);
		return sum;
	}
	
	// 연결리스트 합 - 정상순서
	static LinkedList<Integer> q51(LinkedList<Integer> sum, Iterator<Integer> number1, Iterator<Integer> number2) {
		if(!number1.hasNext() && !number2.hasNext()) return sum;
		int num1 = number1.hasNext()? number1.next() : 0;
		int num2 = number2.hasNext()? number2.next() : 0;
		int add = num1 + num2;
		if(add >= 10) {
			sum = round(sum, sum.iterator(),  sum.size()-1, add/10);
		}
		sum.add(add % 10);
		q51(sum, number1, number2);
		return sum;
	}
	static LinkedList<Integer> round(LinkedList<Integer> number, Iterator<Integer> it, int index, int addNumber) {
		if(!it.hasNext()) {
			number.add(addNumber);
			return number;
		}
		int test =  (number.size() > index) ? number.remove(index): 0;
		int sum = test + addNumber;
		if(sum >= 10) {
			round(number, it, index-1, sum/10);
			sum %= 10;
		} 
		number.add(sum);
		return number;
	}

	// 회문인지 검사
	static boolean q6(LinkedList<Integer> data, Iterator<Integer> it) {
		boolean answer = true;
		int rightNumber = it.next();
		if(it.hasNext()) {
			answer = q6(data, it);
		}
		int leftNumber = data.remove();
		return answer && (leftNumber == rightNumber);
	}

	// 교집합
	static SingleLinkedList q7(SingleLinkedList data1, SingleLinkedList data2) {
		print(data1);
		print(data2);
		boolean hasIntersection = false;
		int[] size = new int[2];
		SingleLinkedList[] datas = new SingleLinkedList[] {data1, data2};
		Node[] node = new Node[2];
		for(int i = 0; i < 2; i++) {
			node[i] = datas[i].head;
			while(node[i].next != null) {
				size[i]++;
				node[i] = node[i].next;
			}
		}
		hasIntersection = node[0] == node[1];
		if(hasIntersection) {
			node[0] = datas[0].head;
			node[1] = datas[1].head;
			int bigIndex = 0;
			if(size[1] > size[0]) bigIndex = 1;
			int smallIndex = 1-bigIndex;
			for(int i = 0; i < size[bigIndex] - size[smallIndex]; i++) {
				node[bigIndex] = node[bigIndex].next;
			}
			while(node[0] != null) {
				if(node[0] == node[1]) return new SingleLinkedList(node[0]);
				node[0] = node[0].next;
				node[1] = node[1].next;
			}
		} 
		return null;
	}
	
	// 루프 발견
	static Node q8(SingleLinkedList data) {
		Node slow = data.head;
		Node fast = data.head;
		Node circle = null;
		while(fast != null && circle == null) {
			fast = fast.next;
			if(fast != null) fast = fast.next;
			slow = slow.next;
			if(fast == slow) circle = fast;
		}
		if(fast == null) return null; // 루프 없는 경우
		
		fast = data.head;
		while(fast != circle) {
			circle = circle.next;
			fast = fast.next;
		}
		return fast;
	}
}
