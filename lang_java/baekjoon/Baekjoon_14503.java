package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14503 extends Solution {
	
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북, 동, 남, 서를 바라보고 현재 방향으로 직진할 때 이동하는 좌표
	static int[][] map; // 2 : 벽, 1 : 아직 청소되지 않은 경우, 0: 청소된 경우
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로크기
		int M = Integer.parseInt(st.nextToken()); // 가로크기
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 좌표 (세로)
		int c = Integer.parseInt(st.nextToken()); // 좌효 (가로)
		int d = Integer.parseInt(st.nextToken()); // 방향
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken()) + 1;
		}
		
		int answer = 0;
		int[] position = new int[] {r, c, d};
		while(position[0] >= 0) { // 더이상 이동이 불가능할 때 까지 반복한다.
			// 현재 위치를 청소
			answer += map[position[0]][position[1]];
			map[position[0]][position[1]] = 0; 
			// 이동
			position = move(position[0], position[1], position[2], map, N, M);
		}
		System.out.println(answer);
	}
	static int[] move(int r, int c, int d, int[][] map, int N, int M) { // 다음 위치를 구함
		for(int i = 0; i < 4; i++) { // 네 방향에 대해 구해봄
			// 왼쪽 방향으로 회전 후 이동
			d = (d+3)%4; //(move_d+4-1)%4;
			int x = r+move[d][0];
			int y = c+move[d][1];
			if(0 <= x && x < N && 0 <= y && y < M && map[x][y] == 1) return new int[] {x, y, d}; // 왼쪽 방향으로 이동 가능하고 아직 청소하지 않은 공간이 존재할 경우
		}
		// 네 방향 모두 청소가 이미 되어있거나 벽인 경우
		// 후진
		int x = r-move[d][0];
		int y = c-move[d][1];
		if(0 <= x && x < N && 0 <= y && y < M && map[x][y] != 2) return new int[] {x, y, d};// 후진 가능한 경우 (여기서 청소된 경우, 벽인 경우 분리 필요) 청소되었더라도 벽이 아니면 이동 가능하다.
		else return new int[] {-1, -1, -1}; // 후진 불가한 경우
	}
}
