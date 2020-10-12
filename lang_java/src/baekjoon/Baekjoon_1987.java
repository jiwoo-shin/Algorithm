package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1987 extends Solution {
	static char[][] board;
	static int R, C;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C]; // 보드
		for(int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		boolean[] visited = new boolean[26];
		visited[board[0][0]-'A'] = true;
		System.out.println(getWay(visited, new int[] {0, 0}, 0));
	}
	
	static int getWay(boolean[] visited, int[] position, int depth) { // visited : 방문한 알파벳 표시, position: 현재 위치, depth: 현재까지 방문한 길이
		int answer = 0;
		int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 상하좌우로 이동
		for(int i = 0; i < 4; i++) {
			int now_i = move[i][0] + position[0];
			int now_j = move[i][1] + position[1];
			// 가능한 위치인 경우
			if(now_i < R && now_j < C && now_i >= 0 && now_j >= 0)  { 
				if(!visited[board[now_i][now_j]-'A']) { // 방문가능한 경우
					visited[board[now_i][now_j]-'A'] = true; // 현재 알파벳을 방문
					answer = Math.max(answer, getWay(visited, new int[] {now_i,  now_j}, depth+1)); // answer를 업데이트
					visited[board[now_i][now_j]-'A'] = false; // 동일한 depth 에서 다른 위치 방문할 것이므로 false 값으로 돌려둔다.
				}
			}
		}
		return answer+1; // 그냥 answer return이 아님. 현재 위치도 더해줘야함
	}
}