package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12869 extends Solution {
	
	//boolean[][][] visited = new boolean[61][61][61]; // 필요없음. memo가 상위호환임
	static int[][][] memo;
	static int[][] attack_list = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] SCV = new int[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			SCV[i] = Integer.parseInt(st.nextToken());
		}
		memo = new int[SCV[0]+1][SCV[1]+1][SCV[2]+1];
		for(int i = 0; i < SCV[0]+1; i++) {
			for(int j = 0; j < SCV[1]+1; j++) {
				for(int k = 0; k < SCV[2]+1; k++) {
					memo[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		memo[0][0][0] = 0; // 초기값 설정. 내가 지금 알 수 있는 최적의 값
		System.out.println(dp(SCV[0], SCV[1], SCV[2]));
	}
	static int dp(int scv0, int scv1, int scv2) {
		 if(memo[scv0][scv1][scv2] != Integer.MAX_VALUE) return memo[scv0][scv1][scv2]; 
		 
		// memo[scv0][scv1][scv2] = length;  // 이러면 dfs이기 때문에 먼저온다고 최적이 아닐 수 있음. dfs라서 깊게깊게 가기 때문에 123이 첫번째 가지의 5번째 depth 이고 2번째 가지의 3 depth일 때 5란 값을 가지므로 최적이 아닐 수 있음
		for(int i = 0; i < 6; i++) {
			// memo에 저장된 것이 최적이라 가정하고 저장된 최적으로 현재 최적의 값을 구함. 이전에 구한 최적의 값들을 활용하여 현재 값을 구함
			// 반환값에 +1을 하므로 0 에서 몇번째로 오는지
			memo[scv0][scv1][scv2] = Math.min(memo[scv0][scv1][scv2], dp(Math.max(0, scv0-attack_list[i][0]), Math.max(0, scv1-attack_list[i][1]), Math.max(0, scv2-attack_list[i][2]))+1);
		}
		return memo[scv0][scv1][scv2];
	}
}

