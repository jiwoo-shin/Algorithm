package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_4991 extends Solution {
	
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		while (h+w > 0) {
			char [][] map = new char[h][w];
			boolean[][] visited = new boolean[h][w];
			int[][] node = new int[11][2];
			int dirty = 0;
			for(int i = 0; i < h; i++) {
				StringBuilder sb = new StringBuilder(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = sb.charAt(j);
					if(map[i][j] == 'x') visited[i][j] = true;
					else if(map[i][j] == 'o') {
						map[i][j] = (char) 0;
						node[0] = new int[] {i, j};
					} else if(map[i][j] == '*') {
						dirty++;
						map[i][j] = (char) dirty;
						node[dirty] = new int[] {i, j};
					}
				}
			}
			int[][] lengths = new int[dirty+1][dirty+1]; // [i][j]  : 노드 i 에서 j 까지 이동하는데 걸리는 최소 길이
			boolean isOk = true;
			for(int i = 0; i < dirty+1; i++) {
				lengths[i] = bfs(node[i], dirty+1, h, w, map); // 모든 시작 + 더러운 곳에 대해 bfs 구하고
				if(lengths[i] == null) { // 방문하지 못하는 노드가 있는 경우 .. (A <-> B 가능, B <-> C 불가, A <-> C 가능인 경우는 없음..A <-> B 가능, A <-> C 가능 이면  B <-> C 도 가능임 (B->A->C))
					answer.append(-1).append('\n');
					isOk = false;
					break;
				}
			}
			if(isOk) answer.append(dfs(1, dirty+1, new boolean[dirty+1], new int[dirty+1], lengths)).append('\n');
			// 순열을 사용하여 배치
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		System.out.println(answer);
	}
	static int getLen(int[][] lengths, int[] array, int size) {
		int answer = 0;
		for(int i = 1; i < size; i++) {
			answer += lengths[array[i-1]][array[i]];
		}
		
		return answer == 0? -1 : answer;
	}
	static int dfs(int depth, int number, boolean[] visited, int[] array, int[][] lengths) {
		if(depth == number) {
			return getLen(lengths, array, number);
		} 
		int answer = Integer.MAX_VALUE;
		for(int i = 1; i < number; i++) { // 시작은 항상 0 고정이므로
			if(!visited[i]) {
				visited[i] = true;
				array[depth] = i;
				answer = Math.min(answer, dfs(depth+1, number, visited, array, lengths));
				visited[i] = false;
			}
		}
		return answer;
		
	}
	static int[] bfs(int[] start, int number, int height, int width, char[][] map) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(start);
		boolean[][] visited = new boolean[height][width];
		visited[start[0]][start[1]] = true;
		int depth = 0;
		int[] lengths = new int[number]; // start에서 number 번째 노드까지 검색 시 걸리는 시간
		while(!queue.isEmpty() && number > 0) { // 모든 출발지점과 더러운 지점에 대해서만 확인하면 됨
			depth++;
			int size = queue.size();
			for(int t = 0; t < size && number > 0; t++) {
				int[] now = queue.poll();
				//it.remove();
				for(int i = 0; i < 4; i++) {
					int[] next = new int[] {now[0]+move[i][0], now[1]+move[i][1]};
					if(0 <= next[0] && next[0] < height && 0 <= next[1] && next[1] < width && !visited[next[0]][next[1]]) {
						visited[next[0]][next[1]] = true;
						if(map[next[0]][next[1]] == 'x') continue; //벽은 지나갈 수 없음
						if(0 <= map[next[0]][next[1]] && map[next[0]][next[1]] < 10) { //  출발 지점이거나 더러운 경우
							number--;
							lengths[map[next[0]][next[1]]] = depth;
						} 
						queue.add(next);
					}
				}
			}
		}
		if(number > 1) return null; // ㅏ현재 노드에서 방문하지 못하는 노드가 있는 경우 (현재 노드 제외)
		
		return lengths;
	}
}
