package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_9328 extends Solution {
	
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		for(int test_case = 0; test_case < T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			char [][] map = new char[h+2][w+2];
			boolean[][] visited = new boolean[h+2][w+2];
			for(int i = 1; i <= h; i++) {
				StringBuilder sb = new StringBuilder(br.readLine());
				for(int j = 1; j <= w; j++) {
					map[i][j] = sb.charAt(j-1);
					if(map[i][j] == '*') visited[i][j] = true;
				}
			}
			for(int i = 0; i < w+2; i++) {
				map[0][i] = '.';
				map[h+1][i] = '.';
			}
			for(int i = 0; i < h+2; i++) {
				map[i][0] = '.';
				map[i][w+1] = '.';
			}
			boolean[] has_key = new boolean[26];
			StringBuilder sb = new StringBuilder(br.readLine());
			for(int i = 0; i < sb.length() && sb.charAt(i)!='0'; i++) {
				has_key[sb.charAt(i) - 'a'] = true;
			}
			answer.append(bfsDQ(w, h, visited, map, has_key)).append('\n');
		}
		System.out.println(answer);
	}
	static int bfsDQ(int width, int height, boolean[][] visited, char[][] map, boolean[] has_key) {
		int answer = 0;
		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.add(new int[] {0, 0});
		visited[0][0] = true;
		int locked = 0;
		while(!dq.isEmpty()){
			int[] now = dq.poll();
			if('A' <= map[now[0]][now[1]] && map[now[0]][now[1]] <= 'Z' && !has_key[map[now[0]][now[1]] - 'A']) {
				locked++;
				if(locked == dq.size() + 1) break; // 큐에 저장된 모든 노드들이 열지 못하는 문인 경우
				dq.addLast(now);
				continue; 
			}
			locked = 0;
			for(int i = 0; i < 4; i++) {
				int[] next = new int[] { now[0]+move[i][0], now[1]+move[i][1] };
				if(0 <= next[0] && next[0] <= height+1 && 0 <= next[1] && next[1] <= width+1 && !visited[next[0]][next[1]]) {					
					char tmp = map[next[0]][next[1]];
					visited[next[0]][next[1]] = true; // 더이상 어떤 노드에서 탐색할 이유가 없음. 방문한 경우 큐에 저장됨. 문을 못여는 경우 큐에 다시 저장할 것임
					if(tmp == '.') {
						dq.addFirst(next);
					} else if(tmp == '$') { // 문서를 훔친경우
						answer++;
						dq.addFirst(next);
					} else if(tmp < 'a') { // 대문자인 경우 (잠긴 벽인 경우)
						if(has_key[tmp - 'A']) { // 열쇠가 있는 경우 열고 이동
							dq.addFirst(next);
						} else { // 열쇠가 없는 경우 보류
							dq.addLast(next);
						}
					} else { // 소문자인 경우 (열쇠인 경우)
						has_key[tmp - 'a'] = true;
						dq.addFirst(next);
					}
				}
			}
		} 

		return answer;
	}
}
