package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2210 extends Solution {
	static int[][] move = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] number = new int[5][5];
		boolean[] visited = new boolean[999999];
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				number[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				answer += dfs(0, 0, visited, new int[] {i, j}, number);
			}
		}
		System.out.println(answer);
	}
	static int dfs(int depth, int make, boolean[] visited, int[] now, int[][] number) {
		if(depth == 6) {
			if(!visited[make]) {
				visited[make] = true;
				return 1;
			} else return 0;
		} else {
			int answer = 0;
			make = make*10 + number[now[0]][now[1]];
			
			for(int i = 0; i < 4; i++) {
				int[] next = new int[] {now[0]+move[i][0], now[1]+move[i][1]};
				if(check(next[0], next[1])) {
					answer += dfs(depth+1, make, visited, next, number);
				}
			}
			
			return answer;
		}
	}
	static boolean check(int i, int j) {
		return i>=0 && j >=0 && i < 5 && j < 5;
	}
}
