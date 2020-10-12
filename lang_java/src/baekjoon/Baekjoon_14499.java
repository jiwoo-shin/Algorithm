package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14499 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로크기
		int M = Integer.parseInt(st.nextToken()); // 가로크기
		int x = Integer.parseInt(st.nextToken()); // 주사위 x 좌표
		int y = Integer.parseInt(st.nextToken()); // 주사위 y 좌표
		int K = Integer.parseInt(st.nextToken()); // 명령 개수
		int[][] map = new int[N][M]; //지도
		int[] dice = new int[6]; // 주사위에 적힌 숫자
		int[][] move_change = {{3, 5, 2, 0}, {3, 0, 2, 5}, {1, 0, 4, 5}, {1, 5, 4, 0}}; // 이동에 따라 바뀔 index 위치들. 해당 index 내의 숫자들이 위치가 이동한다.
		int[][] move_position = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // x, y 가 얼마나 바뀌는지
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int move = Integer.parseInt(st.nextToken()) - 1;
			int tmp = dice[move_change[move][0]];
			x += move_position[move][0]; // 일단 이동 후 이동 가능한지 판단. 이동 불가능할 경우 이동 전 좌표로 바꾼다.
			y += move_position[move][1];
			if(0 <= x && x < N && 0 <= y && y < M) { // 이동 가능한 경우
				for(int j = 0; j < 3; j++) {
					dice[move_change[move][j]] = dice[move_change[move][j + 1]];
				}
				dice[move_change[move][3]] = tmp;
				answer.append(dice[0]).append('\n');
				if(map[x][y] == 0) map[x][y] = dice[5]; // 이동한 칸에 쓰여 있는 수가 0인 경우
				else { // 이동한 칸에 쓰여 있는 수가 0이 아닌 경우
					dice[5] = map[x][y];
					map[x][y] = 0;
				}
			} else { // 이동 불가능한 경우 이동하지 않는다.
				x -= move_position[move][0];
				y -= move_position[move][1];
			}
		}
		System.out.print(answer);
	}
}

/* 참고 ) 주사위 이동 별 인덱스 위치 변경
 * if(move == 1 && x < M-1) { // 동쪽
	int tmp = dice[3];
	dice[3] = dice[5];
	dice[5] = dice[2];
	dice[2] = dice[0];
	dice[0] = tmp;
	x++;
} else if(move == 2 && x > 0) { //서
	int tmp = dice[3];
	dice[3] = dice[0];
	dice[0] = dice[2];
	dice[2] = dice[5];
	dice[5] = tmp;
	x--;
} else if(move == 3 && y < N-1) { // 북
	int tmp = dice[1];
	dice[1] = dice[0];
	dice[0] = dice[4];
	dice[4] = dice[5];
	dice[5] = tmp;
	y--;
} else if(move == 4 && y > 0){ // 남
	int tmp = dice[1];
	dice[1] = dice[5];
	dice[5] = dice[4];
	dice[4] = dice[0];
	dice[0] = tmp;
}
 * */