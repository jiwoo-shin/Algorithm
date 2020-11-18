package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_10026 extends Solution {
	static int answer1, answer2;
	static boolean[][][] visited;
	static char[][] map;
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int N;

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[2][N][N];
		String tmp;
		for(int i = 0; i < N; i++) {
			tmp = br.readLine();
			for(int j = 0; j < N; j++)
				map[i][j] = tmp.charAt(j);
		}
		//bfs(N);
		search(N);
	}
	
	// 다른 배열을 만나는게 항상 다 다른구역인지 모름. 그니까 구역 1을 위에서, 왼쪽에서, 아래에서 보는걸 다 다른것으로 보기 떄문에 덱보다는 i, j 순차로 dfs든 bfs든 돌리는게 적합
	static void bfs1(int N) {
		Deque<int[]> deque = new ArrayDeque<int[]>();
		for(int i = 0; i < 2; i++) {
			deque.add(new int[] {0, 0, 1});
			visited[0][0][i] = true;
			int answer = 0;
			while(!deque.isEmpty()) {
				int[] now = deque.remove();
				System.out.println(now[0]+" "+now[1]+" "+map[now[0]][now[1]]);
				for(int j = 0; j < 4; j++) {
					int[] next = new int[] {now[0]+move[j][0], now[1]+move[j][1], now[2]};
					if(isOk(next[0], next[1]) && !visited[next[0]][next[1]][i]) {
						visited[next[0]][next[1]][i] = true;
						if(isSameArea(map[now[0]][now[1]],map[next[0]][next[1]], i)) {
							deque.addFirst(next);
							// answer--;
						} else {
							deque.add(next);
							answer++;
						}
					}
				}
			}
			System.out.println(answer);
		}
	}
	static void search(int N) {
		StringBuilder answer = new StringBuilder();
		for(int k = 0; k < 2; k++) {
			int tmp = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[k][i][j]) {
						dfs(i, j, k);
						tmp++;
					}
				}
			}
			answer.append(tmp).append(' ');
		}
		System.out.print(answer);
	}
	static boolean isOk(int i, int j) {
		return i >= 0 && i < N && j >=0 && j < N;
	}
	static boolean isSameArea(char color, char color1, int k) {
		if(k == 0) return color == color1;
		if(k == 1) return color == color1 || (Math.abs(color1-color) == 'R'-'G');
		return false;
	}
	////
	static void dfs(int i, int j, int k) {
		for(int t = 0; t < 4; t++) {
			int next_i = i+move[t][0], next_j = j+move[t][1];
			if(isOk(next_i, next_j) && isSameArea(map[i][j], map[next_i][next_j], k) && !visited[k][next_i][next_j]) {
				visited[k][next_i][next_j] = true;
				dfs(next_i, next_j, k);
			}
		}
	}
}
