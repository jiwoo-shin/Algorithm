package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_10942 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] input = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] memo = new boolean[N][N]; // memo[i][j] = i-1 ~ j-1 팰린드롬인지
		for(int i = 0; i < 3; i++) { // 1자리~3자리 초기화
			int max = N-i;
			for(int j = 0; j < max; j++) { // 시작점이 0 ~ max 까지
				memo[j][j+i] = isSame(j, j+i, input); // j, j+i 가 동일한 숫자인 경우 팰린드롬
			}
		}
		for(int i = 3; i < N; i++) { // 4자리 ~ N 자리 가 팰린드롬인지 구함
			int max = N-i;
			for(int j = 0; j < max; j++) { // 시작점이 0 ~ max 까지
				memo[j][j+i] = memo[j+1][j+i-1] && isSame(j, j+i, input); // 이전에 구한 것을 이용하여 다음을 구함 // j+1 ~ j+i-1이 팰린드롬이고 j, j+i 가 동일한 숫자이면  j ~ j+i도 팰린드롬이다.
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			answer.append(memo[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]? 1: 0).append('\n');	
		}
		System.out.print(answer);

	}
	static boolean isSame(int i, int j, int[] input) {
		return input[i] == input[j];
	}
	
}
