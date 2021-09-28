package common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import codingInterview.CH02.DoubleLinkedList;
import codingInterview.CH02.Node;
import codingInterview.CH02.SingleLinkedList;


public abstract class Solution {
	public abstract void solution() throws IOException;

	public static void print(Node data) {
		if(data != null) System.out.println(data.data);
		else System.out.println("null");
	}
	public static void print(SingleLinkedList array) {
		if(array == null) {
			System.out.println("null");
			return;
		}
		Node head = array.head;
		while(head != null) {
			System.out.print(head.data+" ");
			head = head.next;
		}
		System.out.println();
	}
	public static void print(DoubleLinkedList array) {
		Node head = array.head;
		while(head != null) {
			System.out.print(head.data+" ");
			head = head.next;
		}
		System.out.println();
	}
	public static void print(HashSet<Integer> array) {
		for(int i : array) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	public static void print(LinkedList<Integer> array) {
		Iterator<Integer> it = array.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		System.out.println();
	}
	public static void print(int[][][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j][0] != 0) {
					System.out.print("["+i+"]["+j+"]: ");
					for(int k = 0; k < array[i][j].length; k++) {
						System.out.print(array[i][j][k]+", ");
					}
					System.out.println();
				}
			}
			System.out.println();
		}
	}
	public static void print(String[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.println(array[i][j]);
			}
			System.out.println();
		}
	}
	public static void print(boolean[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]?1+" ":0+" ");
		}
		System.out.println();
	}
	public static void print(boolean[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]?1+" ":0+" ");
			}
			System.out.println();
		}
	}
	public static void print(char[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}
	public static void print(int[][] array) {
		System.out.println();
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] < 10) System.out.print(" "+array[i][j]+" ");
				else  System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void print(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
}