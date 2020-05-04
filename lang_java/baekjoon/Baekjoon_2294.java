package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2294 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(dp2(coins, k, n));
	}
	static int dp2(int[] coins, int k, int n) { // 1번째 동전만 사용, 1번+2번째 동전 사용, ... 이런식으로 memo를 채워나간다. (k원을 위한 경우의 수를 구할 때... 경우의 수에 사용된 동전들을 입력 순서대로 정렬하여 추가되는 동전을 뒤에 붙이는 방식이므로 순서 다른애들이 중복으로 계산되지 않음.)
		int[] memo = new int[k];
		for(int i = 0; i < n; i++) { // 어디까지 동전을 사용할지 
			if(coins[i]-1 < k) memo[coins[i]-1] = 1;
			for(int j = 0; j < k; j++) { // k원에 대해 구함
				if(j-coins[i] >= 0 && memo[j-coins[i]] > 0) {
					if(memo[j] == 0) memo[j] = memo[j-coins[i]] + 1; // memo[j]가 구하지 않은 경우라면
					else memo[j] = Math.min(memo[j-coins[i]] + 1, memo[j]);
				}
			}
			
		}
		if(memo[k-1] == 0) return -1; // 불가능한 경우
		return memo[k-1];
	}
}