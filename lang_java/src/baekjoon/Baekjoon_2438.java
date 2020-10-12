package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_2438 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		printStar(N);
		
	}
	static void printStar(int N) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= i; j++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}