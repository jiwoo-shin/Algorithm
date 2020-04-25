package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_3055 extends Solution {

	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); 
		int C = Integer.parseInt(st.nextToken()); 

		boolean[][] visited = new boolean[R][C];
		int[] end = new int[2];
		ArrayList<int[]> queue = new ArrayList<int[]>();
		ArrayList<int[]> water = new ArrayList<int[]>();
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j < C; j++) {
				if(tmp.charAt(j) == 'S') {
					visited[i][j] = true;
					queue.add(new int[] {i, j});
				} else if(tmp.charAt(j) == 'D') {
					visited[i][j] = true;
					end = new int[] {i, j};
				} else if(tmp.charAt(j) == '*') {
					visited[i][j] = true;
					water.add(new int[] {i, j});
				} else if(tmp.charAt(j) == 'X') visited[i][j] = true;
			}
		}
		
		int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int answer = 0;
		int[] now = new int[2];
		boolean isPrint =  false;

		
		while(!queue.isEmpty()) {
			// for문이 끝나면 다음 depth가 됨
			int water_size = water.size(); // for 안에 바로 넣으면 안에서 add와 remove 때문에 size가 계속 바귀니까.
			for(int i = 0; i < water_size; i++) {
				int[] now_water = water.remove(0);
				for(int j = 0; j < 4; j++) {
					int next_i = now_water[0] + move[j][0];
					int next_j = now_water[1] + move[j][1];
					if(next_i >= 0 && next_i < R && next_j >=0 && next_j < C && !visited[next_i][next_j]) {
						visited[next_i][next_j] = true;
						water.add(new int[]{next_i, next_j});
					}
				}
			}
			int queue_size = queue.size();
			// System.out.println(queue_size+" test");
			for(int i = 0; i < queue_size; i++) {
				now = queue.remove(0);
				for(int j = 0; j < 4; j++) {
					int next_i = now[0] + move[j][0];
					int next_j = now[1] + move[j][1];
					if(next_i == end[0] && next_j == end[1]) { // 저 위에 water 범람을 막기 위해 D도 visited = true로 설정되어있기 때문에 요거 먼저 확인함.
						System.out.println(++answer);
						// 큐를비워줌. 비워지지 않을 경우 또 돌게 되는데 목적지에 도착하는경우는 visited여부에 관계 없이 end 값만 맞으면 되므로 한번 더 출력될수도 있음.
						while(!queue.isEmpty()) { // queue_size - i같은걸로 안하는게 아래에서 큐가 추가될 수 있으므로
							queue.remove(0);
						}
						queue_size = 0;
						isPrint = true;
						break;
					}
					if(next_i >= 0 && next_i < R && next_j >=0 && next_j < C && !visited[next_i][next_j]) {
						visited[next_i][next_j] = true;
						queue.add(new int[]{next_i, next_j});
					}
				}
			}
			answer++;
		}
		if(!isPrint) { // 도착 못하더라도 answer가 0인 것은 아님. 어느정도 탐색을 하다 도착 못하는거기때문에
			System.out.println("KAKTUS");
		} 
	
	}
	
}
