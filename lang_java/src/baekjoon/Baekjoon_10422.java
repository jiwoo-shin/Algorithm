package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_10422 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); 
		long[] memo = new long[5001];
		memo[0] = 1;
		memo[2] = 1;
		StringBuilder answer = new StringBuilder();
		for(int i = 4; i <= 5000; i += 2) {
			for(int j = 0; j <= i-2; j +=2) {
				memo[i] = (memo[i] + memo[i-j-2]*memo[j])%1000000007;
				// n개의 올바른 괄호문자열 : (n-2개)0개/(n-4개)2개/(n-6개)4개 /... /(0개)n-2개
				// n개의 올바른 괄호문자열 : (n-2개의 올바른 괄호문자열)0개의 올바른 괄호문자열/(n-4개의 올바른 괄호문자열)2개의 올바른 괄호문자열/(n-6개의 올바른 괄호문자열)4개의 올바른 괄호문자열 /... /(0개의 올바른 괄호문자열)n-2개의 올바른 괄호문자열
			}
		}
		for(int i = 0; i < T; i++) {
			answer.append(memo[Integer.parseInt(br.readLine())]).append('\n');
		}
		System.out.println(answer);
	}
}