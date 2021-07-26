package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1937 extends Solution {
	
	static int[][] memo;
	static int[][] map;
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int n;
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[n][n];
		memo = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				answer = Math.max(go(i, j), answer);
			}
		}
		System.out.println(answer);
	}

	static int go(int i, int j) {
		//boolean isGo = false;
		if(memo[i][j] == 0) { // 4방향 중 어느곳도 갈 수 없다면 0이 아니라 1임. 자기 자신도 포함해야하므로
			memo[i][j] = 1;
			for(int direction = 0; direction < 4; direction++) {
				int nextI = i + move[direction][0], nextJ = j + move[direction][1];
				if(nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n && map[nextI][nextJ] > map[i][j]) {
	                memo[i][j] = Math.max(go(nextI, nextJ) + 1, memo[i][j]);
					// isGo = true;
				}
			}
			// if(!isGo) memo[i][j] = 1; // i, j 위치에서 더이상 이동할 수 없는 경우
		}
		return memo[i][j];
	}
	
	
}