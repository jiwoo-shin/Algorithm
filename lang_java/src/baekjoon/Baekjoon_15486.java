package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15486 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+2]; // N일에 상담을 했을 때 얻을 수 있는 
		int[][] counsel = new int[N+1][2]; // 
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			counsel[i][0] = Integer.parseInt(st.nextToken()); // 시간
			counsel[i][1] = Integer.parseInt(st.nextToken()); // 이익
		}
		print(dp);
		for(int i = 1; i <= N; i++) {
			System.out.println(i);
			// i일에 상담을 하는 경우
			int next = i+counsel[i][0]; // i일에 상담을 한 경우 상담을 할 수 있는 다음 날짜
			System.out.println(next+" "+(dp[i] + counsel[i][1]));
			if(next <= N+1) {
				dp[next] = Math.max(dp[next], dp[i] + counsel[i][1]); //dp[i] : i일 까지 상담했을 떄 얻을 수 있는 최대 이익
			}
			// i일에 상담을 하지 않는 경우
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			print(dp);
		}
		System.out.println(dp[N+1]);
	}
}

