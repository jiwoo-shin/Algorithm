package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_6087 extends Solution {
	
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		char[][] map = new char[H][W];
		boolean[][] visited = new boolean[H][W];
		Queue<int[]> queue = new LinkedList<int[]>();
		for(int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < W; j++) {
				if(map[i][j] == 'C' && queue.isEmpty()) {
					queue.add(new int[] {i, j, 0});
					visited[i][j] = true;
				} else if(map[i][j] == '*') visited[i][j] = true;
			}
		}
		System.out.println(bfs(queue, visited, map, H, W));
	}
	static int bfs(Queue<int[]> queue, boolean[][] visited, char[][] map, int H, int W) {
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i = 0; i < 4; i++) {
				int[] next = new int[] { now[0]+move[i][0], now[1]+move[i][1], now[2]+1 }; // 좌표, 꺾은 횟수
				
				// 한 방향으로 불가능할때 까지 쭉 방문.. 
				while(0 <= next[0] && next[0] < H && 0 <= next[1] && next[1] < W && map[next[0]][next[1]] != '*') {
					if(!visited[next[0]][next[1]]) { // 방문했던 경우는 재방문하지는 않음. 꺾이지 않는것 우선으로 방문하기 때문에 이미 방문했던 곳은 꺾임이 적은 횟수ㄹ로 방문을 하였음
						if(map[next[0]][next[1]] == 'C') return next[2]-1;
						queue.add(next);
						visited[next[0]][next[1]] = true;
					}
					// 업데이트
					next = new int[] { next[0]+move[i][0], next[1]+move[i][1], next[2] };
				}
			}
		}
		return -1;
	}

}
