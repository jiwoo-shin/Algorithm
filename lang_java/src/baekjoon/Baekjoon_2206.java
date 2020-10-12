package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2206 extends Solution {

	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		
		int[][][] number = new int[N][M][2]; // 이동 
		int[][] wall = new int[N][M]; // 벽.
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j < M; j++) {
				wall[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		//초기화
		ArrayList<int[]> queue = new ArrayList<int[]>();
		number[N-1][M-1][0] = -1; // 도착점 -1로 초기화. 도착하지 않는 경우 -1임 > 이거 먼저 함. 1 1일 경우 1이 답이므로
		number[N-1][M-1][1] = -1; // 도착점 -1로 초기화. 도착하지 않는 경우 -1임 > 이거 먼저 함. 1 1일 경우 1이 답이므로
		number[0][0][0] = 1; // 시작점 1 // 벽을 안 부수고 온 경우 걸린 거리
		number[0][0][1] = 1; // 시작점 1 // 벽을 부수고 온 경우 걸린 거리
		// 점 (i, j)를 벽을 부수고 올 수도있고 벽을부수고 오지않을수도 있으므로 2가지 경우에 대해 확인이 필요.
		// 벽을 부수고 온 경우 도착 불가능하지만 벽을안부수고 왔을 떄 도착 가능할수도 있으므로
		
		queue.add(new int[] {0, 0, 0});
		int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int now_break = 0, now_i, now_j;
		int[] index;
		
		while(!queue.isEmpty()) {
			index = queue.remove(0);
			now_i = index[0];
			now_j = index[1];
			now_break = index[2];
			
            if(now_i == N-1 && now_j == M-1)break;
			
			for(int i = 0; i < 4; i++) { //상하좌우
				int next_i = now_i + move[i][0];
				int next_j = now_j + move[i][1];
				
				
				// 방문 가능한 범위이고 (next_i >=0 && next_i < N && next_j >=0 && next_j < M)
				if(next_i >=0 && next_i < N && next_j >=0 && next_j < M ) { 
					
					// next_break : 다음 벽 부술건지 여부.
					int next_break = now_break + wall[next_i][next_j]; //next_break가 2인 경우는 불가능한 경우 .. 이전에 벽 부수고 또 부수는 거니까..
					
					
					// 방문하지 않았을 때 (number[next_i][next_j] < 1) -> ==0이 아님.마지막이 -1로 초기화되어있으므로
					// 다음 방문이 필요한 위치가방문하지 않은 위치인 경우
					if(next_break < 2 && number[next_i][next_j][next_break] < 1) {
						queue.add(new int[] {next_i, next_j, next_break});
						number[next_i][next_j][next_break] = number[now_i][now_j][now_break] + 1;
					}
				}
			}
			
		}
		
		System.out.println(number[N-1][M-1][now_break]);
	
	}
	
}
