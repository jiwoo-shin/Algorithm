package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_5557 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 숫자의 개수 
		int[] numbers = new int[N]; // 볼륨 리스트
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dp(N, numbers));
	}
	static long dp(int N, int[] numbers) {
		long answer = 0;
		long[][] memo = new long[N-1][21]; // memo[i][j] : i 번째 까지 사용하였을 때 숫자 j를 만드는 경우의 수
		memo[0][numbers[0]] = 1;
		for(int i = 1; i < N-1; i++) {
			for(int j = 0; j < 21; j++) {
				int plus = j + numbers[i]; // 더한 경우
				int minus = j - numbers[i]; // 뺀 경우
				if(plus < 21) {
					memo[i][plus] += memo[i-1][j]; // ++ 아님
				}
				if(minus >= 0 ) {
					memo[i][minus] += memo[i-1][j]; // ++ 아님
				} 
			}
		}
		answer = memo[N-2][numbers[N-1]]; // numbers[N-1]이 되는 경우의 수
		return answer;
	}
}