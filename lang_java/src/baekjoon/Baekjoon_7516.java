package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_7516 extends Solution {

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		int[][] visited = new int[M][N]; 

		ArrayList<int[]> queue = new ArrayList<int[]>(); // 방문할 정점들을 저장
		int number = 0; // 방문한 정점의 개수
		int num_unable = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				String tmp = st.nextToken();
				visited[i][j] = tmp.charAt(0) - '0'; //0 인 경우는 방문할 필요가 없음
				if(visited[i][j] == 1) { //1 인 경우 방문할 정점에 추가
					queue.add(new int[]{i, j});
				} else if(visited[i][j] < 0) { // '-1' - '0'은 -1이 아님..
					num_unable++;
				}
			}
		}
		
		// 더 이상 큐에 방문할 정점이 없을 때 까지 
		// 방문한 개수가 N*M - (-1의 개수)보다 작은 경우 -1 반환
		// 모든 정점을 방문한 경우 answer 반환

		int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		int index = 0;
		int now_i = queue.get(0)[0], next_i;
		int now_j = queue.get(0)[1], next_j;
		
		while(index < queue.size()) { // 방문할 정점이 없을때 까지 반복 (큐는 크기가 계속 변경하므로 queue.size를 계속 구해야함)
			// 현재 방문중인 정점의 위치를가져옴
			now_i = queue.get(index)[0];
			now_j = queue.get(index)[1];
			number++; // 방문한 정점의 개수 +1
			
			for(int i = 0; i < 4; i++) {
				next_i = now_i + move[i][0];
				next_j = now_j + move[i][1];
				if(next_i >= 0 && next_i < M && next_j >= 0 && next_j < N) {
					if(visited[next_i][next_j] == 0) { //-1이거나 1이면 안됨 > 1이면 이미 방문, -1이면 방문 불가
						visited[next_i][next_j] = visited[now_i][now_j] + 1;
						queue.add(new int[]{next_i, next_j}); // 방문 
					}
				}
			}
			
			index++;
		}

		if(number + num_unable < N*M) { // 방문한 정점의 수가 방문해야할 정점의 수보다 작은 경우 모두 방문한 것이 아님
			System.out.println(-1);
		} else {
			System.out.println(visited[now_i][now_j] - 1); // visited에 1부터 시작하였으므로 1을 빼준다.
		}
	}
}
