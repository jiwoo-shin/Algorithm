package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_4574 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int testCase = 1;
		while(N != 0) {
			sb.append("Puzzle ").append(testCase).append("\n");
			int[][] board = new int[9][9];
			boolean[][] dominoUse = new boolean[9][9];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int[] num = new int[2];
				for(int j = 0; j < 2; j++) {
					num[j] = Integer.parseInt(st.nextToken());
					fillBoard(st.nextToken(), num[j], board);
				}
				dominoUse[num[0]-1][num[1]-1] = true;
				dominoUse[num[1]-1][num[0]-1] = true;
			}
			st = new StringTokenizer(br.readLine());
			
			for(int num = 1; num < 10; num++) {
				fillBoard(st.nextToken(), num, board);
			}
			play(0, 0, board, dominoUse);
			for(int i = 0; i < 9; i++) sb.append(Arrays.toString(board[i]).replaceAll(", ", "").replace("[","").replace("]","")).append("\n");
			N = Integer.parseInt(br.readLine());
			testCase++;
		}
		System.out.print(sb.toString());
	}
	
	static boolean play(int i, int j, int[][] board, boolean[][] dominoUse) {
		int[][] move = {{1, 0}, {0, 1}}; // 아래, 오른쪽 // 상하, 좌우 다할 필요가 없음. 각 위치에서 돌기 때문에 상하 하면 중복확인됨. 상하 / 좌우 중 하나씩만 하면 된다.
		if(i == 9) { // 끝!!!!!
			return true;
		}
		if(board[i][j] == 0) {
			int[] validNumber = getValidNumber(i, j, board);
			int[][] validNumberDomino = new int[2][10]; // 상하 다 할 필요는 없음..모든 점에서 하므로 하/상 둘중 하나만 하면 된다.
			if(validNumber[0] == 0) return false; // // 채워지지 않았는데 가능한 숫자가 없으면 앞단으로 돌아가야한다.
			for(int validNumberIndex = 1; validNumberIndex <= validNumber[0]; validNumberIndex++) { // i 번에 가능한 모든 숫자를 i 에 넣어본다.
				int number1 = validNumber[validNumberIndex];
				for(int validDominoDirection = 0; validDominoDirection < 2; validDominoDirection++) { // i 고정이고 다음 판에 쓰인 숫자를 구한다.
					int dominoI = i+move[validDominoDirection][0], dominoJ = j+move[validDominoDirection][1]; 
					if(dominoI >= 0 && dominoI < 9 && dominoJ >= 0 && dominoJ < 9 && board[dominoI][dominoJ] == 0 ) { // 다음판이 놓일 수 있는 위치인 경우
							validNumberDomino[validDominoDirection] = getValidNumber(dominoI, dominoJ, board); // 다음판에 가능한 숫자를 구한다.
							for(int validDominoIndex = 1; validDominoIndex <= validNumberDomino[validDominoDirection][0]; validDominoIndex++) { // 다음판에 가능한 모든 숫자를 해본다.
								int number2 = validNumberDomino[validDominoDirection][validDominoIndex];
								if(!dominoUse[number1-1][number2-1] && number2 != number1) { // 해당 도미노(i,j 판의 숫자 + 다음판의 숫자로 이루어진 도미노)를 한번도 사용한 적이 없는 경우, 판 2개에 쓰인 숫자가 다른 경우
									board[i][j] = number1;
									board[dominoI][dominoJ] = number2;
									dominoUse[number1-1][number2-1] = true;
									dominoUse[number2-1][number1-1] = true;
									//int[][] tmp; // play 에서 null이 반환되는 경우가 있으므로
									boolean isEnd  = false;
									if(j < 8) isEnd  = play(i, j+1, board, dominoUse);
									else isEnd = play(i+1, 0, board, dominoUse);
									
									if(isEnd) return true; // end
									
									board[i][j] = 0; // 다음 for문에 이상한 숫자가 채워지는 것을 방지하기 위해
									board[dominoI][dominoJ] = 0;
									dominoUse[number1-1][number2-1] = false;
									dominoUse[number2-1][number1-1] = false;
								}
							}
						
					}
				}
			}
		} else { // 
			boolean isEnd  = false;
			if(j < 8)isEnd = play(i, j+1, board, dominoUse);
			else isEnd = play(i+1, 0, board, dominoUse);
			if(isEnd) return true;
		}
		
		return false;//여기까지 온거면 중간에 for 거치면서 제대로된 board를 반환받지 못한건데.. 그러면 답이 아닌 경우임. 
	}
	static void fillBoard(String position, int number, int[][] board) {
		int i = position.charAt(0) - 'A';
		int j = position.charAt(1) - '1';
		board[i][j] = number;
	}
	static int[] getValidNumber(int i, int j, int[][] board) { // i, j의 가능한 숫자 배열을 구한다.
		
		int[] validNumber = new int[10];
		boolean[] number = new boolean[10]; // 1-9 .. 이미 한번 사용된 숫자라 사용할 수 없는 경우 true
		for(int x = 0; x < 9; x++) {
			number[board[i][x]] = true;
		}
		for(int y = 0; y < 9; y++) {
			number[board[y][j]] = true;
		}
		
		int startY = i/3*3;
		int startX = j/3*3;
		for(int y = startY; y < startY + 3; y++) {
			for(int x = startX; x < startX + 3; x++) {
				number[board[y][x]] = true;
			}
		}
		
		validNumber = changeNumber(number);
		return validNumber;
	}
	static int[] changeNumber(boolean[] number) {
		int[] validNumber = new int[10];
		int index = 1;
		for(int l = 1; l < 10; l++) {
			if(!number[l]) validNumber[index++] = l;
		}
		validNumber[0] = index-1;
		return validNumber;
	}
	
}
/*package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_4574 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[9][9];
		// boolean[][] visited = new boolean[9][9];
		int[][][] validNumber = new int[9][9][10]; // 위치별 가능한 숫자. 0이면 종료한다. (0-9)
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 2; j++) {
				int num = Integer.parseInt(st.nextToken());
				board = fillBoard(st.nextToken(), num, board);
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int num = 1; num < 10; num++) {
			board = fillBoard(st.nextToken(), num, board);
		}
		print(board);
		for(int i = 0; i< 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == 0) { // 보드에서 빈 부분만 확인한다.
					validNumber[i][j] = getValidNumber(i, j, board); // i, j 에서 가능한 숫자를 뽑아낸다.
					
					if(validNumber[i][j][0] == 1) { // 한가지 경우만 가능한 경우
						board[i][j] = validNumber[i][j][1];
						validNumber = updateValidNumber(i, j, board, validNumber);
					}
				}
			}
		}
		print(validNumber);
		boolean check = false;
		do {
			check = false;
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					if(board[i][j] == 0 && validNumber[i][j][0] == 1) { // 보드에서 빈 부분만 확인한다. 한가지 경우만 가능한 경우
						check = true;
						board[i][j] = validNumber[i][j][1];
						validNumber = updateValidNumber(i, j, board, validNumber);
					}
				}
			}
		} while(check);
		// board, validNumber 확정.
		
		print(board);
		print(validNumber);
	}
	static int[][] play(int i, int j, int[][] board, int[][][] validNumberOrigin, int count) {
		int[] validNumber = validNumberOrigin[i][j];
		if(board[i][j] == 0 && validNumber[0] == 0) return null; // 채워지지 않았는데 가능한게 없으면 앞단으로 돌아가야한다.
		if(count == 81) return board; // 모든 숫자를 채워넣은 경우
		if(board[i][j] == 0) { // 다음 
			
		}
		for(int validNumberIndex = 1; validNumberIndex <= validNumber[0]; validNumberIndex++) {
			board[i][j] = validNumber[validNumberIndex];
			if(i < 8) play(i+1, j, board, validNumberOrigin, count+1);
			else play(0, j+1, board, validNumberOrigin, count+1);
			
			board[i][j] = 0;
		}
		
		return board;
	}
	
	static int[][][] updateValidNumber(int i, int j, int[][] board, int[][][] validNumber) { // i, j가 변경되어서 i, j 주면의 가능 숫자들을 변경한다.
		int deleteNumber = board[i][j];
		for(int x = 0; x < 9; x++) {
			boolean isDelete = false;
			for(int l = 1; l < 10 && validNumber[i][x][l] > 0; l++) {
				if(isDelete) {
					validNumber[i][x][l-1] = validNumber[i][x][l]; // 삭제되어서 0이된 공간을 제거하기 위해..
					validNumber[i][x][l] = 0;
				}
				if(validNumber[i][x][l] == deleteNumber) {
					validNumber[i][x][l] = 0;
					isDelete = true;
				}
			}
			if(isDelete) validNumber[i][x][0]--;
		}
		for(int y = 0; y < 9; y++) {
			boolean isDelete = false;
			for(int l = 1; l < 10 && validNumber[y][j][l] > 0; l++) {
				if(isDelete) {
					validNumber[y][j][l-1] = validNumber[y][j][l]; // 삭제되어서 0이된 공간을 제거하기 위해..
					validNumber[y][j][l] = 0;
				}
				if(validNumber[y][j][l] == deleteNumber) {
					validNumber[y][j][l] = 0;
					isDelete = true;
				}
			}
			if(isDelete) validNumber[y][j][0]--;
		}
		
		int startY = i/3*3;
		int startX = j/3*3;
		for(int y = startY; y < startY + 3; y++) {
			for(int x = startX; x < startX + 3; x++) {
				boolean isDelete = false;
				for(int l = 1; l < 10 && validNumber[x][y][l] > 0; l++) {
					if(isDelete) {
						validNumber[x][y][l-1] = validNumber[x][y][l]; // 삭제되어서 0이된 공간을 제거하기 위해..
						validNumber[x][y][l] = 0;
					}
					if(validNumber[x][y][l] == deleteNumber) {
						validNumber[x][y][l] = 0;
						isDelete = true;
					}
				}
				if(isDelete) validNumber[x][y][0]--;
			}
		}
		return validNumber;
	}
	static int[][] fillBoard(String position, int number, int[][] board) {
		int i = position.charAt(0) - 'A';
		int j = position.charAt(1) - '1';
		board[i][j] = number;
		return board;
	}
	static int[] getValidNumber(int i, int j, int[][] board) { // i, j의 가능한 숫자 배열을 구한다.
		
		int[] validNumber = new int[10];
		boolean[] number = new boolean[10]; // 1-9 .. 이미 한번 사용된 숫자라 사용할 수 없는 경우 true
		for(int x = 0; x < 9; x++) {
			number[board[i][x]] = true;
		}
		for(int y = 0; y < 9; y++) {
			number[board[y][j]] = true;
		}
		
		int startY = i/3*3;
		int startX = j/3*3;
		for(int y = startY; y < startY + 3; y++) {
			for(int x = startX; x < startX + 3; x++) {
				number[board[y][x]] = true;
			}
		}
		
		validNumber = changeNumber(number);
		return validNumber;
	}
	static int[] changeNumber(boolean[] number) {
		int[] validNumber = new int[10];
		int index = 1;
		for(int l = 1; l < 10; l++) {
			if(!number[l]) validNumber[index++] = l;
		}
		validNumber[0] = index-1;
		return validNumber;
	}
	
}*/