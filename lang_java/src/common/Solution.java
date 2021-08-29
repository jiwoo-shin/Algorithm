package common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Solution {
	public abstract void solution() throws IOException;

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