package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1915 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		int[][][] memo = new int[3][n][m]; // ����, ����, ���簢
		char[][] map = new char[n][m];
		int answer = 0;
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = tmp.charAt(j);
				boolean isOne = map[i][j] == '1';
				// if isOne map[i][j] = Math.min(map[i-1][j-1], Math.min(map[i-1][j], map[i][j-1])) + 1; // �̷��� �Ǵ°�.. ����, �Է�, ���簢 �� ���� ���� �κ��� ���� ���簢�� �̷�� ���� ���� ����..
				boolean isIGreaterThanZero = i > 0, isJGreaterThanZero = j > 0;
				if(isIGreaterThanZero && isOne) {
					memo[1][i][j] = memo[1][i-1][j] + 1;
				} else {
					memo[1][i][j] = map[i][j] - '0';
				}
				
				if(isJGreaterThanZero && isOne) {
					memo[0][i][j] = memo[0][i][j-1] + 1;
				} else {
				 	memo[0][i][j] = map[i][j] - '0';
				}
				
				if(isJGreaterThanZero && isIGreaterThanZero && isOne) {
					memo[2][i][j] = Math.min(Math.min(memo[0][i][j-1], memo[1][i-1][j]), memo[2][i-1][j-1]) + 1;
				} else {
				 	memo[2][i][j] = map[i][j] - '0';
				}
				answer = Math.max(answer, memo[2][i][j]);
			}
		}
		System.out.println(answer * answer);
	}
	
	
}