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

public class Baekjoon_3197 extends Solution {
	
	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[R][C];
		int[][] swan_array = new int[2][2];
		Queue<int[]> water = new LinkedList<int[]>();
		int swan_index = 0;
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < C; j++) {
				char tmpChar = tmp.charAt(j);
				if(tmpChar == 'X') map[i][j] = true;
				else if(tmpChar == 'L') {
					swan_array[swan_index++] = new int[] {i, j};
					water.add(new int[] {i, j});
				} else {
					water.add(new int[] {i, j});
				}
			}
		}
		System.out.println(meet(R, C, swan_array[0], swan_array[1], water, map));
	}
	static int meet(int R, int C, int[] start_swan, int[] end_swan, Queue<int[]> water, boolean[][] map) {
		int answer = 0;
		
		Deque<int[]> swan = new ArrayDeque<int[]>();
		swan.add(start_swan);
		boolean[][] swan_visited = new boolean[R][C];
		swan_visited[start_swan[0]][start_swan[1]] = true;
		while(answer <= R*C) {
			// 백조 이동// queue로 remove 하면서 while 비교로 하고 다음날에 방문해야 하는 위치(X인 경우)는 next_swan 이라는 큐를 새로 만들어도 될듯..
			for(int i = 0; i < swan.size(); i++) { 
				int[] now_swan = swan.remove(); 
				int add_swan = 0;
				for(int direction = 0; direction < 4; direction++) {
					int[] next_swan = new int[] {now_swan[0]+move[direction][0], now_swan[1]+move[direction][1]};
					if(next_swan[0] >= 0 && next_swan[0] < R && next_swan[1] >= 0 && next_swan[1] < C) {
						if(!map[next_swan[0]][next_swan[1]]) { // 물인 경우
							if(!swan_visited[next_swan[0]][next_swan[1]] ) { // 백조가 방문한적이 없거나 물인 경우
								if(next_swan[0] == end_swan[0] && next_swan[1] == end_swan[1]) return answer;
								else {
									swan.addFirst(next_swan);//그냥 add는 i 가 방문하지 않아도 증가하므로 백조가 모든 경우를 방문하지 못함
									swan_visited[next_swan[0]][next_swan[1]] = true;
								}
							}
							add_swan++; // 백조기준 주변이 물인경우
						}
					} else {
						add_swan++; // 백조 기준 주변이 벽인 경우 
					}
					
				}
				if(add_swan < 4) { 
					swan.add(now_swan); // 백조 주변으로 모두 확인한 것이 아니면 물이 녹은 후에도 사용해야 하므로 제거는 하지 않음
				} else { // 백조 기준 상하좌우 탐색할 필요 없는 경우 -> 백조가 제거되므로 i도 빼줘야 size와 i 가 의도한 대로 작용함 (swan.size()는 현재 백조를 제거한 사이즈를 반환하기 때문에 i에서도 제거 필요)
					i--;
				}
			}
			
			// 물이 녹음
			int water_size = water.size();
			for(int i = 0; i < water_size; i++) { // water에 현재 저장되어 있는 만큼만 해야함
				int[] now_water = water.remove();
				for(int direction = 0; direction < 4; direction++) {
					int[] next_water = new int[] {now_water[0]+move[direction][0], now_water[1]+move[direction][1]};
					if(next_water[0] >= 0 && next_water[0] < R && next_water[1] >= 0 && next_water[1] < C && map[next_water[0]][next_water[1]]) {
						map[next_water[0]][next_water[1]] = false;
						water.add(next_water);
					}
				}
			}
			
			answer++;
		}
		return -1;
	}
}
