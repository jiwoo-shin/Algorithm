package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11066 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder answer = new StringBuilder();
		for(int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[][] memo = new int[k][k]; // memo[i][j] : i~j 까지 합치는데 드는 최소
			int[] sum = new int[k]; // sum[i] : i 까지의 합

			int before = 0;
			for(int i = 0; i < k; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				memo[i][i] = 0; // i~i 를 합치는비용은 0원
				if(i > 0) {
					sum[i] = sum[i-1] + tmp;
					memo[i-1][i] = before + tmp;
					
				} else sum[i] = tmp;
				before = tmp;
			}
			answer.append(dp(k, memo, sum)).append('\n');
		}
		System.out.println(answer);
	}
	
	
	static int dp(int k, int[][] memo, int[] sums) {
		for(int i = 2; i < k; i++) { // i+1개 합치는것.
			for(int j = 0; j < k-i; j++) { //j 부터 i+1개 합침 (j 부터 j+i 까지)
				memo[j][j+i] = Integer.MAX_VALUE; // memo[j][j+i]는 과거에 사용한 비용 + 합치는데 사용할 비용
				for(int l = 0; l < i; l++) {
					memo[j][j+i] = Math.min(memo[j][j+i], memo[j][j+l] +  memo[j+l+1][j+i]); // j, j+l 를 합치는데 필요한 비용 +  j+l+1,j+i 를 합치는데 필요한 비용
				}
				int sum = sums[i]; // j~ j+i 사이의 합 (i~j+i를 합치므로 i~j+i 합만큼.합치는데 비용이 필요함 비용이 필요)
				if(j > 0) sum = sums[j+i] - sums[j-1]; 
				memo[j][j+i] += sum; // j, j+l과 j+l+1,j+i를 합하는데 과거에 사용한 비용(sum 더하기 전 memo[j][j+i]) + i~i+j 까지 새로 합하는데 필요한 비용(sum)
			}
		}
		return memo[0][k-1];
	}
}