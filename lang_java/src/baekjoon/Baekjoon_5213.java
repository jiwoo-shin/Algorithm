package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_5213 extends Solution {
	
	static int N;
	static int[][][] tile;
	static boolean[][] visited;
	static int[] move_i_list = {-1, 1, 0, -1, 1, 0}; // 왼쪽에서 위(j-> 홀: 0, 짝: -1) , 왼쪽에서 아래(j-> 홀: 0, 짝: -1), 왼쪽에서 왼쪽 (j -> -1))
	// 오른쪽에서 위(j-> 홀: 1, 짝:0), 오른쪽에서 아래(j-> 홀: 1, 짝:0), 오른쪽에서 오른쪽(j -> 1)
	

	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		tile = new int[N][N][2];
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			int row = N - i%2;
			for(int j = 0; j < row; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				tile[i][j][0] = Integer.parseInt(st.nextToken());
				tile[i][j][1] = Integer.parseInt(st.nextToken());
			}
		}
		int[] route = new int[N*N-N/2+1]; // 경로를 저장 i 번에 방문 시 어느 노드를 통해 와야 최단으로 도착하는지
		Queue<int[]> queue = new LinkedList<int[]>(); // 현재 탐색중인 타일을 저장
		queue.offer(new int[] {0, 0}); // 초기 위치
		visited[0][0] = true;
		int max = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i = 0; i < 6; i++) {
				int mod = now[0] % 2;
				int move_i = move_i_list[i];
				int move_j = i==2 || i == 5? 1-2*(1-i/3) : i/3 - 1 + mod; // i/2-(1-mod);
				int[] next = new int[] {now[0]+move_i, now[1]+move_j}; 
				if(isOk(now, next, i/3, 1-i/3)) {
                    max = Math.max(max, getNumber(next));
					visited[next[0]][next[1]] = true; // bfs 이므로 이전에 방문하면 다시 방문할 필요가 없음 dfs와 다르게 visited 중간에 true로 바꾸지 않아도 된다.
					// System.out.println(i+" : "+now[0]+","+now[1]+"("+i/3+") -> "+next[0]+","+next[1]+"("+(1-i/3)+")"+"   ");
					
                    route[getNumber(next)] = getNumber(now);
					queue.offer(next);
				}
			}
		}
		int length = 1;
		int now_route = max;
		answer.append(now_route).append(' ');
		while(now_route > 1) {
			length++;
			now_route = route[now_route];
			answer.insert(0, now_route+" ");
		}
		answer.insert(0, length+"\n");
		System.out.println(answer);
	}
	static boolean isOk(int[] now, int[] next, int tile_pos_now, int tile_pos_next) {
		// 빈 타일이면 0이라 무조건 다름. 1부터 입력되기 때문에
		return (next[0] >= 0 && next[0] < N && next[1] >= 0 && next[1] < N) && (tile[now[0]][now[1]][tile_pos_now] == tile[next[0]][next[1]][tile_pos_next]) && !visited[next[0]][next[1]];
	}
	static int getNumber(int[] pos) {
		int number = pos[0]*N + pos[1] - pos[0]/2 + 1;
		return number;
	}
}
