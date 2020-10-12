package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2293 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		int[][] memo = new int[k][n]; // memo[i] : 동전으로 i원을 만드는 경우의 수 (memo[i][j] : 동전으로 i+1원을 만드는 경우의 수(단, coins[0]~coins[j] 까지의 동전만 사용함.))
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
			if(coins[i]-1 < k) memo[coins[i]-1][i] = 1;
		}
		// System.out.println(dp1(memo, coins, k, n));
		System.out.println(dp2(coins, k, n));
	}
	
	static int dp1(int[][] memo, int[] coins, int k, int n) {
		for(int i = 1; i < k; i++) { // i 원에 대한 경우의 수를 계산 (2원부터 계산한다.)
			for(int j = 0; j < n; j++) { // 어디까지 동전을 사용하였는지
				if(i-(coins[j]) >= 0) {
					for(int l = 0; l <=j; l++) {
						memo[i][j] += memo[i-(coins[j])][l]; 
					}
				}
			}
		}
		int answer = 0;
		for(int i = 0; i <n; i++) {
			answer += memo[k-1][i];
		}
		return answer;
	}
	static int dp2(int[] coins, int k, int n) { // 1번째 동전만 사용, 1번+2번째 동전 사용, ... 이런식으로 memo를 채워나간다. (k원을 위한 경우의 수를 구할 때... 경우의 수에 사용된 동전들을 입력 순서대로 정렬하여 추가되는 동전을 뒤에 붙이는 방식이므로 순서 다른애들이 중복으로 계산되지 않음.)
		int[] memo = new int[k];
		for(int i = 0; i < n; i++) { // 어디까지 동전을 사용할지 
			if(coins[i]-1 < k) memo[coins[i]-1] += 1;
			for(int j = 0; j < k; j++) { // k원에 대해 구함
				if(j-coins[i] >= 0)memo[j] += memo[j-coins[i]];
			}
			
		}
		return memo[k-1];
	}
}