package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

import java.io.InputStreamReader;

public class Baekjoon_14502 extends Solution {

	static ArrayList<int[]> virus;
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		virus = new ArrayList<int[]>();
		boolean[][] visited = new boolean[N][M]; // 바이러스가 방문 가능하면 false, 방문 불가능할경우 true
		int wall_size = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					visited[i][j] = true;
					wall_size++;
				} else if(map[i][j] == 2) {
					virus.add(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}

		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					// 1. dfs 사용
					//answer = Math.max(answer, dfs(0, visited, i, j, N, M)); // i, j 를 시작점
					//visited[i][j] = false;
					
					// dfs2 사용
					visited[i][j] = true;
					answer = Math.max(answer, dfs2(0, visited, i, j, N, M)); // i, j 를 시작점
					visited[i][j] = false; // 다음번 반복문에 영향을 주지 않기 위해
				}
				
			}
		}
		
		System.out.println(answer-(wall_size+3));
		
	}
	static int dfs2(int depth, boolean[][] visited, int i, int j, int N, int M) { // 어디에 벽을 세울건지 3개 선택
		int answer = 0;
		// 벽 세개를 세워서 더이상 탐색할 필요가 없는 경우. 
		if(depth == 2) return getSafe(visited, N, M); //getSafe 내에서 visited가 바뀌므로 깊은복사를한다.흠. 이걸 dfs로 탐색하고 
		for(int t = i; t < N; t++) { // 같은 원소, 순서가 달라도 같은 경우이므로 항상 오름차순에 대해서만 탐색한다.
			for(int k = t == i?j+1: 0; k < M; k++) {
				 if(!visited[t][k]) { // 벽이나 바이러스가 아닌 경우
					visited[t][k] = true; // 벽을 새로 놓는다.
					answer = Math.max(answer, dfs(depth+1,  visited, t, k, N, M));
					visited[t][k] = false; // 새로 놓은 벽을 치움.
				 }
			}
		}
		return answer;
	}
	static int dfs(int depth, boolean[][] visited, int i, int j, int N, int M) {  // 어디에 벽을 세울건지 3개 선택. dfs 말고 삼중 for문 돌려도 될듯 3개만 구하는것이니.
		int answer = 0;
		visited[i][j] = true; // 벽을 새로 놓는다.
		if(depth == 2) return getSafe(visited, N, M); // 벽 세개를 세워서 더이상 탐색할 필요가 없는 경우.
		for(int t = i; t < N; t++) { // 같은 원소, 순서가 달라도 같은 경우이므로 항상 오름차순에 대해서만 탐색한다.
			for(int k = t == i?j+1: 0; k < M; k++) {
				 if(!visited[t][k]) { // 벽이나 바이러스가 아닌 경우
					answer = Math.max(answer, dfs(depth+1,  visited, t, k, N, M));
					visited[t][k] = false; // 새로 놓은 벽을 치움. visited가 얕은복사 되므로 dfs 내에서 [t][k] 가 벽으로 바뀐채로 for문이 돌면 안되므로
				 }
			}
		}
		return answer;
	}
	static int getSafe(boolean[][] visited, int N, int M) { // 탐색을 dfs로 해도 될듯. 시작 점을 virus에서 가져오면 되니
		ArrayList<int[]> virus_copy = (ArrayList<int[]>) virus.clone();
		boolean[][] is_spread = new boolean[N][M];
		int number_virus = 0;
		int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		while(!virus_copy.isEmpty()) {
			int[] virus_now = virus_copy.remove(0);
			number_virus++;
			for(int i = 0; i < 4; i++) {
				int[] next_virus = {virus_now[0] + move[i][0], virus_now[1] + move[i][1]};
				if(isOk(next_virus[0], next_virus[1], N, M) && !visited[next_virus[0]][next_virus[1]] && !is_spread[next_virus[0]][next_virus[1]] ) {
					virus_copy.add(next_virus);
					is_spread[next_virus[0]][next_virus[1]] = true;
				}
			}
		}
		return N*M - number_virus;
	}
	static boolean isOk(int i, int j, int N, int M) {
		return i >=0 && i < N && j >= 0 &&j < M;
	}
}
