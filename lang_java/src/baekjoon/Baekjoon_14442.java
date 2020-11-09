package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14442 extends Solution {
	static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 선택할 그룹

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String tmp;
		int[][] map = new int[N][M];
		int[][] visited = new int[N][M]; // 여기까지 오는데 벽을 몇개 부수었는지 (0: 방문 x, 1: 벽 하나도 안부숨, n: 벽을 n-1개 부숨) -> 이게 같은 위치인데 벽을 부순 개수가 다르면 다르게 봐야한다.
		for(int i = 0; i < N; i++) {
			tmp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j) - '0';
				visited[i][j] = -1; // 하나도 부수지 않은 0과 한번도 방문하지않은 것과는 달라야 하므로..0 으로 초기화하면 벽이 하나도 없을 떄 계속 돈다.
			}
		}
		// bfs 안에서 next가 비교되고 있으므로 시작 전 now값에 대해 비교 필요
		if(N ==1 && M == 1) System.out.println(1);
		else System.out.println(bfs(map, visited, N, M, K));
		
	}
	
	static int bfs(int[][] map, int[][] visited, int N, int M, int K) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {0, 0, 0, 1});
		visited[0][0] = 0;
		int[] now, next;
		while(!queue.isEmpty()) {
			now = queue.remove();
			for(int i = 0; i < 4; i++) {
				next = new int[] {now[0] + move[i][0], now[1] + move[i][1], now[2], now[3]+1};
				if(next[0] >= 0 && next[0] < N && next[1] >= 0 && next[1] < M) {
					int wall = next[2]+map[next[0]][next[1]]; // 벽을 부순 개수
					// 부순 벽의 개수가 제한보다 작고 방문한 적이 없거나 이전에 방문하였는데 부순 벽의 개수가 이번이 더 작은 경우
					if(wall <= K && (visited[next[0]][next[1]] == -1 || visited[next[0]][next[1]] > wall )) { // 왔던 곳을 bfs로 다시 온다는 것은 결국 최적은 아니라는 소리, 경로는 이전에 왔던 게 더 길지만 벽을 덜 부수고 오는경우도 따져야 한다.
						next[2] += map[next[0]][next[1]];
						// System.out.println(now[0]+","+now[1]+" -> "+next[0]+","+next[1]+"  ("+next[2]+" "+next[3]+")");
						if(next[0] == N-1 && next[1] == M-1) return next[3];
						visited[next[0]][next[1]] = wall;
						queue.add(next);
					}
				}
			}
		}
		
		return -1;
	}
}
