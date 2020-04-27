package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_15558 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		boolean[][] visited = new boolean[2][N];
		StringBuilder sb;
		for(int i = 0; i < 2; i++) {
			sb = new StringBuilder(br.readLine());
			for(int j = 0; j < N; j++) {
				if(sb.charAt(j) == '0') visited[i][j] = true;
			}
		} 
		System.out.println(bfs(k, N, visited));
		
	}
	static int bfs(int k, int N, boolean [][] visited) { // 결국 N 끝까지 가야하므로.. dfs가 더 빠를듯.. 어쨋든 노드의 끝을 탐색 가능한지 봐야하기 때문에 갈수 있는 최대한의 깊이를 먼저 탐색해보는게 더 빠를듯. 
		Queue<int[]> queue = new LinkedList<int[]>();
		// 초기값 (왼쪽 첫번째)
		queue.add(new int[] {0, 0, 0});
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int[][] next = {{now[0], now[1]+1, now[2]+1}, {now[0], now[1]-1, now[2]+1}, {-now[0] + 1, now[1] + k, now[2]+1}}; // 앞, 뒤, 반대줄
			for(int i = 0; i < 3; i++) {
				int[] tmp = next[i];
				if(tmp[1] >= N) return 1; // 성공한 경우 // == 아님
				if(tmp[2] <= tmp[1] && tmp[1] < N && !visited[tmp[0]][tmp[1]]) {// tmp가 성공하지 않은 경우이고 방문 가능한 경우
					visited[tmp[0]][tmp[1]] = true;
					queue.add(tmp);
				}
			}
			
		}
		
		return 0; 
	}
	
}
