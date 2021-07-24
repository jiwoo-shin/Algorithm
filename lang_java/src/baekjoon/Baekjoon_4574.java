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
		int[][] move = {{1, 0}, {0, 1}}; // �Ʒ�, ������ // ����, �¿� ���� �ʿ䰡 ����. �� ��ġ���� ���� ������ ���� �ϸ� �ߺ�Ȯ�ε�. ���� / �¿� �� �ϳ����� �ϸ� �ȴ�.
		if(i == 9) { // ��!!!!!
			return true;
		}
		if(board[i][j] == 0) {
			int[] validNumber = getValidNumber(i, j, board);
			int[][] validNumberDomino = new int[2][10]; // ���� �� �� �ʿ�� ����..��� ������ �ϹǷ� ��/�� ���� �ϳ��� �ϸ� �ȴ�.
			if(validNumber[0] == 0) return false; // // ä������ �ʾҴµ� ������ ���ڰ� ������ �մ����� ���ư����Ѵ�.
			for(int validNumberIndex = 1; validNumberIndex <= validNumber[0]; validNumberIndex++) { // i ���� ������ ��� ���ڸ� i �� �־��.
				int number1 = validNumber[validNumberIndex];
				for(int validDominoDirection = 0; validDominoDirection < 2; validDominoDirection++) { // i �����̰� ���� �ǿ� ���� ���ڸ� ���Ѵ�.
					int dominoI = i+move[validDominoDirection][0], dominoJ = j+move[validDominoDirection][1]; 
					if(dominoI >= 0 && dominoI < 9 && dominoJ >= 0 && dominoJ < 9 && board[dominoI][dominoJ] == 0 ) { // �������� ���� �� �ִ� ��ġ�� ���
							validNumberDomino[validDominoDirection] = getValidNumber(dominoI, dominoJ, board); // �����ǿ� ������ ���ڸ� ���Ѵ�.
							for(int validDominoIndex = 1; validDominoIndex <= validNumberDomino[validDominoDirection][0]; validDominoIndex++) { // �����ǿ� ������ ��� ���ڸ� �غ���.
								int number2 = validNumberDomino[validDominoDirection][validDominoIndex];
								if(!dominoUse[number1-1][number2-1] && number2 != number1) { // �ش� ���̳�(i,j ���� ���� + �������� ���ڷ� �̷���� ���̳�)�� �ѹ��� ����� ���� ���� ���, �� 2���� ���� ���ڰ� �ٸ� ���
									board[i][j] = number1;
									board[dominoI][dominoJ] = number2;
									dominoUse[number1-1][number2-1] = true;
									dominoUse[number2-1][number1-1] = true;
									//int[][] tmp; // play ���� null�� ��ȯ�Ǵ� ��찡 �����Ƿ�
									boolean isEnd  = false;
									if(j < 8) isEnd  = play(i, j+1, board, dominoUse);
									else isEnd = play(i+1, 0, board, dominoUse);
									
									if(isEnd) return true; // end
									
									board[i][j] = 0; // ���� for���� �̻��� ���ڰ� ä������ ���� �����ϱ� ����
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
		
		return false;//������� �°Ÿ� �߰��� for ��ġ�鼭 ����ε� board�� ��ȯ���� ���Ѱǵ�.. �׷��� ���� �ƴ� �����. 
	}
	static void fillBoard(String position, int number, int[][] board) {
		int i = position.charAt(0) - 'A';
		int j = position.charAt(1) - '1';
		board[i][j] = number;
	}
	static int[] getValidNumber(int i, int j, int[][] board) { // i, j�� ������ ���� �迭�� ���Ѵ�.
		
		int[] validNumber = new int[10];
		boolean[] number = new boolean[10]; // 1-9 .. �̹� �ѹ� ���� ���ڶ� ����� �� ���� ��� true
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
		int[][][] validNumber = new int[9][9][10]; // ��ġ�� ������ ����. 0�̸� �����Ѵ�. (0-9)
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
				if(board[i][j] == 0) { // ���忡�� �� �κи� Ȯ���Ѵ�.
					validNumber[i][j] = getValidNumber(i, j, board); // i, j ���� ������ ���ڸ� �̾Ƴ���.
					
					if(validNumber[i][j][0] == 1) { // �Ѱ��� ��츸 ������ ���
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
					if(board[i][j] == 0 && validNumber[i][j][0] == 1) { // ���忡�� �� �κи� Ȯ���Ѵ�. �Ѱ��� ��츸 ������ ���
						check = true;
						board[i][j] = validNumber[i][j][1];
						validNumber = updateValidNumber(i, j, board, validNumber);
					}
				}
			}
		} while(check);
		// board, validNumber Ȯ��.
		
		print(board);
		print(validNumber);
	}
	static int[][] play(int i, int j, int[][] board, int[][][] validNumberOrigin, int count) {
		int[] validNumber = validNumberOrigin[i][j];
		if(board[i][j] == 0 && validNumber[0] == 0) return null; // ä������ �ʾҴµ� �����Ѱ� ������ �մ����� ���ư����Ѵ�.
		if(count == 81) return board; // ��� ���ڸ� ä������ ���
		if(board[i][j] == 0) { // ���� 
			
		}
		for(int validNumberIndex = 1; validNumberIndex <= validNumber[0]; validNumberIndex++) {
			board[i][j] = validNumber[validNumberIndex];
			if(i < 8) play(i+1, j, board, validNumberOrigin, count+1);
			else play(0, j+1, board, validNumberOrigin, count+1);
			
			board[i][j] = 0;
		}
		
		return board;
	}
	
	static int[][][] updateValidNumber(int i, int j, int[][] board, int[][][] validNumber) { // i, j�� ����Ǿ i, j �ָ��� ���� ���ڵ��� �����Ѵ�.
		int deleteNumber = board[i][j];
		for(int x = 0; x < 9; x++) {
			boolean isDelete = false;
			for(int l = 1; l < 10 && validNumber[i][x][l] > 0; l++) {
				if(isDelete) {
					validNumber[i][x][l-1] = validNumber[i][x][l]; // �����Ǿ 0�̵� ������ �����ϱ� ����..
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
					validNumber[y][j][l-1] = validNumber[y][j][l]; // �����Ǿ 0�̵� ������ �����ϱ� ����..
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
						validNumber[x][y][l-1] = validNumber[x][y][l]; // �����Ǿ 0�̵� ������ �����ϱ� ����..
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
	static int[] getValidNumber(int i, int j, int[][] board) { // i, j�� ������ ���� �迭�� ���Ѵ�.
		
		int[] validNumber = new int[10];
		boolean[] number = new boolean[10]; // 1-9 .. �̹� �ѹ� ���� ���ڶ� ����� �� ���� ��� true
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