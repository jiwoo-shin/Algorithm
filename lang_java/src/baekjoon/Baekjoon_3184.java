package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_3184 extends Solution {
	int[][] move_position = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
	boolean[][] visited;
	char[][] map;
	int R, C;

	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		int ship_num = 0, wolf_num = 0;
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '#') {
					visited[i][j] = true;
				} else if(map[i][j] == 'o') {
					 ship_num++;
				} else if(map[i][j] == 'v') {
					 wolf_num++;
				}
			}
		}

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				int group_ship = 0, group_wolf = 0;
				if(!visited[i][j]) {
					Queue<int[]> positions = new LinkedList<int[]>();
					positions.add(new int[] {i, j});
					visited[i][j] = true;
					while(!positions.isEmpty()) {
						int[] position =  positions.poll();
						//visited[position[0]][position[1]] = true; // 여기서만하면 경로가 다른 경우 두번 방문함. next로 방문여부 미리 체크한다.
						if(map[position[0]][position[1]] == 'o') {
							group_ship++;
						}
						else if(map[position[0]][position[1]] == 'v') group_wolf++;
						for(int move = 0; move < 4; move++) {
							int[] next = {position[0] + move_position[move][0], position[1] + move_position[move][1]};
							if(next[0] >= 0 && next[0] < R && next[1] >= 0 && next[1] < C && !visited[next[0]][next[1]]) {
								visited[next[0]][next[1]] = true;
								positions.add(next);
							}
						}
					}
					if(group_ship > group_wolf) {
						wolf_num -= group_wolf;
					} else {
						ship_num -= group_ship;
					}
				}
			}
		}
		System.out.println(ship_num+" "+wolf_num);
	}
	/*
	int[] search(int[] position, boolean[][] visited, int R, int C) {
		for(int i = 0; i < 4; i++) {
			int[] next = {position[0] + move[i][0], position[1] + move[i][1]};
			if(next[0] >= 0 && next[0] < R && next[1] >= 0 && next[1] < C && !visited[next[0]][next[1]]) {
				visited[next[0]][next[1]] = true;
				search(next, visited, R, C);
			}
		}
	}*/
}
