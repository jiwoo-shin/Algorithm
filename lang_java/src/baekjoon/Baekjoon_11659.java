package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11659 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] memo = new int[N+1]; // N 번째 숫자 까지의 합
		
		memo[0] = 0; 
		for(int i = 1; i <= N; i++) {
			memo[i] = memo[i-1] + Integer.parseInt(st.nextToken()); // 1번째~i번째 숫자의 합
		}
		
		int i, j;
		for(int l = 0; l < M; l++) {
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			
			int sum =  memo[j] - memo[i-1];
			sb.append(sum).append('\n');
		}
		
		System.out.println(sb.toString());
		

	}
}