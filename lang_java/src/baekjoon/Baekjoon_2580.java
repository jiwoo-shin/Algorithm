package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2580 extends Solution {

	static int[][] sudokus = new int[9][9]; // 스도쿠들
	static int empty_length = 0; // 빈 스도쿠
	static ArrayList<int[]> empty_index; // 빈 스도쿠 위치 저장
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		empty_index = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				sudokus[i][j] = Integer.parseInt(st.nextToken());
				if(sudokus[i][j] == 0) {
					empty_index.add(new int[] {i, j});
				}
			}
		}
		empty_length = empty_index.size();
		
		sudoku(0);
	}
	
	// 가능한지 검사
	static boolean check(int x, int y, int number) {
		int arround_x = 3*(x/3), arround_y = 3*(y/3);
		// 아니면 for 안돌도록 배열을 만들어서 할수도 있을 듯 (가로[9][9], 세로[9][9], 주위 9개[9][9] 각 배열 만들고 각 배열[i][number]이 true 인지 검사) // 각 가로, 세로, 주위 9개에 숫자를 붙여서 0~8 개의 배열로 만든다.
		for(int i = 0; i < 9; i++) {
			if(sudokus[x][i] == number) return false; // 가로 방향 같은 경우
			if(sudokus[i][y] == number) return false; // 세로방향 같은 경우
			if(sudokus[arround_x + i/3][arround_y + i%3] == number) return false; // 주위 9개에 같은 숫자가 있는 경우
		}
		return true;
	}

	static boolean sudoku(int depth) {
		if(depth == empty_length) {
			StringBuilder answer = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					answer.append(sudokus[i][j]).append(' ');
				}
				answer.append('\n');
			}
			System.out.print(answer.toString());
			return true;
		} else {
			int[] position = empty_index.get(depth);
			int x = position[0], y = position[1];
			for(int i = 1; i <= 9; i++) {
				int number = i;
				if(check(x, y, number)) { // number이 가능한 경우
					sudokus[x][y] = number; // sudoku에 숫자를 넣는다.
					if(sudoku(depth+1)) return true; // 다음 스도쿠에 들어갈 숫자를 구해본다. // (다음 스도쿠 긑까지 구해보고 가능하면 true/ 불가능 시 false return됨)
				} 
				sudokus[x][y] = 0; // number로 구해지지 않는 경우 다시 빈값을 넣어야함. check..
			}
		}
		return false; // 중간에 return 안만나고 여기까지 오는거는 못구했다는것.
	}
}