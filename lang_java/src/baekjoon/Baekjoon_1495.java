package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1495 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 개수 
		int S = Integer.parseInt(st.nextToken()); // 시작볼륨
		int M = Integer.parseInt(st.nextToken()); // 올릴 수 있는 볼륨 최대
		int[] V = new int[N]; // 볼륨 리스트
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dp(S, M, N, V));
		
	}
	static int dp(int S, int M, int N, int[] V) {
		boolean[][] memo = new boolean[N+1][M+1]; // memo[i][j] : i 번째를 사용하였을 때 무게 j 가 가능한지 여부
		memo[0][S] = true;
		for(int i = 1; i < N; i++) { // i 번째
			for(int j = M; j >= 0; j--) { // 볼륨 j
				if( j-V[i-1] >= 0 && memo[i-1][j-V[i-1]] || j+V[i-1] <= M && memo[i-1][j+V[i-1]] ) { 
					// i-1, j-V[i] 에서 볼륨을 올리는게 가능한 경우이거나/ i-1, j+V[i] 에서 볼륨을 내리는게 가능
					memo[i][j] = true;
				}
			}
		}
		for(int j = M; j >= 0; j--) { // 볼륨 j
			if(j-V[N-1] >= 0 && memo[N-1][j-V[N-1]] || j+V[N-1] <= M && memo[N-1][j+V[N-1]]) {
				return j;
			}
		}
		
		return -1;
	}
}