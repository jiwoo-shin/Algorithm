package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1890 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] input = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dp(N, input));

	}
	
	static long dp(int N, int[][] input) { // int 아님.. 범위 잘 확인해야함..
		long[][] memo = new long[N][N]; // memo[i][j] : (0, 0) ~ (i-1, j-1) 까지 가능한 경로
		memo[0][0] = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int move = input[i][j];
				if(move > 0) {
					if(i+move < N) memo[i+move][j] += memo[i][j];
					if(j+move < N) memo[i][j+move] += memo[i][j];
				}
			}
		}
		return memo[N-1][N-1];
	}
	
}
