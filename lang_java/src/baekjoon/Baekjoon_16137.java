package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_16137 extends Solution {
	static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(map, N, M));
	}
	static int bfs(int[][] map, int N, int M) {
		int answer = 0;
		boolean[][][] visited = new boolean[N][N][2]; // 오작교를 놓거나 안놓고 돌아가는 경우
		Queue<int[]> queue = new LinkedList<int[]>(); // 현재 위치, 시간, 여태까지 오작교 썼는지 여부, 바로 지금 오작교 걷넜는지.. now가 바로 이전임.
		queue.add(new int[] {0, 0, 0, 0, 0});
		while(!queue.isEmpty()) {
			int[] now = queue.remove();
			for(int i = 0; i < 4; i++) {
				int[] next = {now[0]+move[i][0], now[1]+move[i][1], now[2]+1, now[3], 0};
				if(next[0] == N-1 && next[1] == N-1) {
					return next[2];
				}
				if(next[0] >= 0 && next[0] < N && next[1] >=0 && next[1] < N) {
					int ground =  map[next[0]][next[1]];
					if(ground == 1) { // 그냥 갈 수 있는 경우
						if(!visited[next[0]][next[1]][next[3]]) { // 추가로 오작교를 만들지 않은 경우에서 방문하지 않았다면 방문
							queue.add(next);
							visited[next[0]][next[1]][next[3]] = true;
						}
					} else if(ground == 0) { // 비어있는 경우 
						if(!visited[next[0]][next[1]][1] && !isCross(map, next[0], next[1], N) && now[3] == 0 && now[4] == 0) {// 언젠가는 오작교를 만들 수 있는 경우
							if(next[2] >= M && next[2] % M == 0 ) { 
								next[3] = 1;
								next[4] = 1;
								queue.add(next);
								visited[next[0]][next[1]][next[3]] = true;
							} else { // 언젠가는 만들 수 있지만 지금 당장 오작교를 만들 수 없다면 대기
								next[0] = now[0];
								next[1] = now[1];
								queue.add(next);
							}
						}
					} else { // 이미 오작교가 있는 경우
						if(!visited[next[0]][next[1]][next[3]] && now[4] == 0) { // 이미 오작교가 있음
							if(next[2] >= ground && next[2]%ground == 0) { // 다리가 열릴 시간인 경우
								next[4] = 1;
								queue.add(next);
								visited[next[0]][next[1]][next[3]] = true;
							} else { // 아직 다리가 열릴 시간이 아니면 대기한다.
								next[0] = now[0];
								next[1] = now[1];
								queue.add(next);
							}
						}
					}
				}
			}
		}
		
		return 0;
	}
	static boolean isCross(int[][] map, int i, int j, int N) {
		for(int d = 0; d < 4; d++) {
			int p1 = i + move[d][0];
			int p2 = j + move[d][1];
			int p3 = i + move[(d+1)%4][0];
			int p4 = j + move[(d+1)%4][1];
			if(p1 >=0 && p1 < N && p2 >=0 && p2 < N && p3 >=0 && p3 < N && p4 >=0 && p4 < N && map[p1][p2] != 1 && map[p3][p4] !=1) return true; // 절벽이 교차하는 경우
		}
		return false;
	}
}

