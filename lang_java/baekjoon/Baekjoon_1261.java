package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_1261 extends Solution {

	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		
		int[][] number = new int[M][N]; // 벽을 부수는 개수 (유의 : -1로 초기화해주지 않았기 떄문에 0을 미방문 정점으로 보고 마지막 출력 시 -1을 해준다.)
		int[][] wall = new int[M][N]; // 벽.
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j < N; j++) {
				wall[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		ArrayList<int[]> queue = new ArrayList<int[]>();
		queue.add(new int[] {0, 0});
		number[0][0] = 1;
		int[] index;
		int[] move_i = {-1, 0, 0, 1};
		int[] move_j = {0, 1, -1, 0};
		
		while(!queue.isEmpty()) {
			// index 방문
			index = queue.get(0);
			queue.remove(0);
			int i = index[0]; 
			int j = index[1];
			int num = number[i][j];
			
			// 벽을 부수는 최소 개수이므로 
			// 추가로 벽을 부술때 큐의 뒷부분에 추가해주고
			// 추가로 벽을 부수지 않아도 될 떄 앞에 추가해준다.
			for(int k = 0; k < 4; k++) {
				// 다음 방문하는 정점
				int next_i = i + move_i[k];
				int next_j = j + move_j[k];
				if(next_i >= 0 && next_i < M && next_j >=0 && next_j < N) { // 방문 가능한 정점 범위일 경우
					if(number[next_i][next_j] == 0) { // 방문하지 않은 경우
						number[next_i][next_j] = num + wall[next_i][next_j];
						if(wall[next_i][next_j] == 0) { //추가로 벽을 부수지 않아도 되는경우
							queue.add(0, new int[] {next_i, next_j}); 
						} else {// 추가로 벽을 부수는 경우
							queue.add(new int[] {next_i, next_j}); 
						}
					}
				}
			}
		}
		
		System.out.println(number[M-1][N-1]-1); // 1부터 시작하였으므로
		
	}
	
}
