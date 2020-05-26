package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_3190 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 보드 크기
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		boolean[][] apples = new boolean[N][N];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apples[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
		}
		int L = Integer.parseInt(br.readLine()); // 뱀의 이동 횟수
		int[][] move_list = new int[L][2];
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			move_list[i][0] = Integer.parseInt(st.nextToken());
			move_list[i][1] = st.nextToken().toCharArray()[0];
		}
		int[] move = {0, 1}; // 이동에 따라 인덱스 값이 어떻게 변하는지
		int time = 0;
		int move_index = 0;
		int[] head = {0, 0};
		boolean[][] isSnake = new boolean[N][N]; // 해당 위치가 뱀인지 저장함
		// tail과 history 없이 머리가 해당 위치에 몇조에 위치했는지 저장하여서 몸 길이와 시간의 차 비교해서 구해도 될듯.
		int[][][] history = new int[N][N][2]; // 해당 위치에서 어디로 이동했는지 저장
		int[] tail = {0, 0};
		while(true) {
			time++;
			// 다음 머리의 위치를 구함
			int next_head_i = head[0]+move[0];
			int next_head_j = head[1]+move[1];
			
			if(next_head_i >= N || next_head_j >= N || next_head_j < 0 || next_head_i < 0 || isSnake[next_head_i][next_head_j]) break; // 벽에 닿거나 자기 몸에 닿은 경우
			
			// 머리이동
			history[head[0]][head[1]] = new int[]{next_head_i, next_head_j}; // 현재 머리에서 어디로 갔는지 저장
			head = new int[] {next_head_i, next_head_j};
			isSnake[next_head_i][next_head_j] = true;
			
			if(apples[head[0]][head[1]]) { // 사과가 있는 경우
				apples[head[0]][head[1]] = false;
			} else { // 사과가 없는 경우
				// 꼬리 위치를 비워준다.
				isSnake[tail[0]][tail[1]] = false;
				tail = new int[] {history[tail[0]][tail[1]][0], history[tail[0]][tail[1]][1]};
			}
			if(move_index < L && move_list[move_index][0] == time) { // 뱀이 회전한 경우
				if(move_list[move_index][1] == 'L') move = new int[]{-move[1], move[0]}; // 왼쪽으로 회전 
				else move = new int[]{move[1], -move[0]}; // 오른쪽으로 회전
				move_index++;
			}
		}
		System.out.println(time);
	}
}
