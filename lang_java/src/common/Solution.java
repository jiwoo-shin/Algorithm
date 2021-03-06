package common;

import java.io.IOException;

public abstract class Solution {
	public abstract void solution() throws IOException;
	
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
				System.out.print(array[i][j]+" ");
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