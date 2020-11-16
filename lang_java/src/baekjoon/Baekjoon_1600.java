package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1600 extends Solution {
	static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int[][] horse_move = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken());
		
		int[][] visited = new int[H][W]; // 같은 위치더라도 말의 움직임으로 이동한 횟수가 다르면 다름. 돌아가더라도 말의 이동으로 적게 이동해야 도착할 수 도 있으므로 boolean이면 말의 움직임은 항상 먼저 쓰고 그 다음은 항상 상화좌우만 이동함 
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				visited[i][j] = -Integer.parseInt(st.nextToken())-1; // 방문 x, 0회로 방문한 경우 분리가 필요해서 -2: 장애물, -1: 방문 x, 0: 말의 이동으로 0번 이동.. . 아니면 boolean[H][W][K] visited해도 될 듯, map 1이 아닌 경우와 함께..
 			}
		}
		if(W == 1 && H == 1) System.out.println(0);
		else System.out.println(bfs(visited, H, W, K));
	}
	
	static int bfs(int[][] visited, int H, int W, int K) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {0, 0, 0, 0});
		visited[0][0] = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.remove();
			for(int i = 0; i < 4; i++) {
				int[] next = new int[] {now[0]+move[i][0], now[1]+move[i][1], now[2]+1, now[3]};
				if(next[0] >= 0 && next[0] < H && next[1] >= 0 && next[1] < W && (visited[next[0]][next[1]] != -2 && (visited[next[0]][next[1]] > next[3] || visited[next[0]][next[1]] == -1))) { // next[3]이 더 작다는 것은 이전에 방문했던 것 보다 말의 이동을 덜한 것 말의 이동을 아끼는 중
					if(next[0] == H-1 && next[1] == W-1) return next[2];
					visited[next[0]][next[1]] = next[3];
					queue.add(next);
				}
			}
			for(int i = 0; i < 8; i++) {
				int[] next = new int[] {now[0]+horse_move[i][0], now[1]+horse_move[i][1], now[2]+1, now[3]+1};
				if(next[0] >= 0 && next[0] < H && next[1] >= 0 && next[1] < W && (visited[next[0]][next[1]] != -2  && (visited[next[0]][next[1]] > next[3] || visited[next[0]][next[1]] == -1)) && next[3] <= K) {
					if(next[0] == H-1 && next[1] == W-1) return next[2];
					visited[next[0]][next[1]] = next[3];
					queue.add(next);
				}
			}
		}
		return -1;
	}
}
