package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_2741 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		StringBuffer buffer = new StringBuffer();
		for(int i = 1; i < N+1; i++) {
			buffer.append(i).append('\n');
		}
		
		System.out.print(buffer);
	}
}
