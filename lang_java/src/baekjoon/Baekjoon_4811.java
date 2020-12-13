package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_4811 extends Solution {
	
	@Override
	public void solution() throws IOException {

		long[][] dp = new long[31][32]; // H, WÀÇ °³¼ö
		for(int i = 0; i < 31; i++) {
			for(int j = 0; j < 31; j++) {
				if(j == 0 && i!= 0) dp[i][j] = dp[i-1][j+1];
				else if(i == 0) dp[i][j] = 1;
				else dp[i][j] = dp[i-1][j+1] + dp[i][j-1];
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		while(number != 0) {
			answer.append(dp[number][0]).append('\n');
			number = Integer.parseInt(br.readLine());
		}
		System.out.println(answer.toString());
	}
}

