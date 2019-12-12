package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_4963 extends Solution {

	static boolean visited[][]; // 정점 방문 여부
	static int w, h; 
	static int[] move_x = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] move_y = {1, 1, 0, -1, -1, -1, 0, 1};
	
	static void search(int i, int j) {
		visited[i][j] = true; //방문

		for(int k = 0; k < 8; k++) {
			int next_x = i + move_x[k];
			int next_y = j + move_y[k];
			if(next_x >= 0 && next_x < h && next_y >= 0 && next_y < w) {
				if(!visited[next_x][next_y])search(next_x, next_y);
			}
		}
	}
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken()); 
		h = Integer.parseInt(st.nextToken()); 
		int tmp;
		int number;
		while(w != 0 || h != 0) {
			number = 0;
			visited = new boolean[h][w];
			
			for(int i = 0;  i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					tmp = Integer.parseInt(st.nextToken()); // String tmp = st.nextToken(); -> 공백 기준으로 st를 하나씩 가져옴
					if(tmp == 0)visited[i][j] = true; //0 인 경우는 방문할 필요가 없음
				}
			}
			
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(!visited[i][j]) { // 방문하지 않은 정점인 경우
						search(i, j); // 방문
						number++; // 방문 후 다음 시작점에서 시작하는 경우는 다른 단지임
					}
				}
			}
			System.out.println(number);
	    	st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); 
			h = Integer.parseInt(st.nextToken()); 
		}
		
	}
}
