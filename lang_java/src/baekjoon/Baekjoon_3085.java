package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_3085 extends Solution {
	
	
	static int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 상 하 우 좌
	static int[][] checkTwoDirection = {{2, 3}, {2, 3}, {0, 1}, {0, 1}};
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		int[][][] canEat = new int[4][N][N];
		int answer = 0;

		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int direction = 0; direction < 4; direction++) {
					int newI = direction == 1 ? N-1-i : i;
					int newJ = direction == 2 ? N-1-j : j;
					int prevI = newI + move[direction][0], prevJ = newJ + move[direction][1];
					if(prevI >= 0 && prevI < N && prevJ >= 0 && prevJ < N && map[newI][newJ] == map[prevI][prevJ]) canEat[direction][newI][newJ] = canEat[direction][prevI][prevJ] + 1;
					else canEat[direction][newI][newJ] = 1;
					answer = Math.max(canEat[direction][newI][newJ], answer);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// i, j 를 교체해본다. 교체하면서 함께 바뀌는 부분은 따로 고려하지 않고 i, j 를 순회하며 확인한다.
				int right = j+1;
				int left = j-1;
				int top = i-1;
				int bottom = i+1;
				if(left >= 0 && map[i][j] != map[i][left]) { // 왼쪽에 있던 숫자를 i 위치로 옮긴다.
					if(right < N && map[i][right] == map[i][left]) answer = Math.max(answer, canEat[2][i][right] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(top >= 0 && map[top][j] == map[i][left]) answer = Math.max(answer, canEat[0][top][j] + 1);
					if(bottom < N && map[bottom][j] == map[i][left]) answer = Math.max(answer, canEat[1][bottom][j] + 1);
					if(bottom < N && top >= 0 && map[bottom][j] == map[i][left] && map[bottom][j] == map[top][j]) answer = Math.max(answer, canEat[1][bottom][j] + 1 + canEat[0][top][j]);
				}
				if(right < N && map[i][j] != map[i][right]) { 
					if(left >= 0 && map[i][left] == map[i][right]) answer = Math.max(answer, canEat[3][i][left] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(top >= 0 && map[top][j] == map[i][right]) answer = Math.max(answer, canEat[0][top][j] + 1);
					if(bottom < N && map[bottom][j] == map[i][right]) answer = Math.max(answer, canEat[1][bottom][j] + 1);
					if(bottom < N && top >= 0 && map[bottom][j] == map[i][right] && map[bottom][j] == map[top][j]) answer = Math.max(answer, canEat[1][bottom][j] + 1 + canEat[0][top][j]);
				}
				if(top >= 0 && map[i][j] != map[top][j]) {
					if(bottom < N && map[bottom][j] == map[top][j]) answer = Math.max(answer, canEat[1][bottom][j] + 1);
					if(left >= 0 && map[i][left] == map[top][j]) answer = Math.max(answer, canEat[3][i][left] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(right < N && map[i][right] == map[top][j]) answer = Math.max(answer, canEat[2][i][right] + 1);
					if(left >= 0 && right < N && map[i][right] == map[top][j] && map[i][right] == map[i][left]) answer = Math.max(answer, canEat[2][i][right] + canEat[3][i][left] + 1);
				}
				if(bottom < N && map[i][j] != map[bottom][j]) {
					if(top >= 0 && map[top][j] == map[bottom][j]) answer = Math.max(answer, canEat[0][top][j] + 1);
					if(left >= 0 && map[i][left] == map[bottom][j]) answer = Math.max(answer, canEat[3][i][left] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(right < N && map[i][right] == map[bottom][j]) answer = Math.max(answer, canEat[2][i][right] + 1);
					if(left >= 0 && right < N && map[i][right] == map[bottom][j] && map[i][right] == map[i][left]) answer = Math.max(answer, canEat[2][i][right] + canEat[3][i][left] + 1);
				}
			}
		}
		
		System.out.println(answer);
		
		/*// 코드 정리 전..
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		
		int[][] toRight = new int[N][N];
		int[][] toLeft = new int[N][N];
		int[][] toTop = new int[N][N];
		int[][] toBottom = new int[N][N];
		int answer = 0;
		
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(j == 0) {
					toRight[i][j] = 1;
				} else if(j > 0) {
					if(map[i][j-1] == map[i][j]) {
						toRight[i][j] =  toRight[i][j-1] + 1;
					}
					else toRight[i][j] = 1;
				}
				answer = Math.max(toRight[i][j], answer);
				
				int leftIndex = N-1-j;
				if(leftIndex == N-1) {
					toLeft[i][leftIndex] = 1;
				} else if(leftIndex < N-1) {
					if(map[i][leftIndex+1] == map[i][leftIndex]) {
						toLeft[i][leftIndex] =  toLeft[i][leftIndex+1] + 1;
					}
					else toLeft[i][leftIndex] = 1;
				}
				answer = Math.max(toLeft[i][j], answer);
				
				if(i == 0) {
					toBottom[i][j] = 1;
				} else if(i > 0) {
					if(map[i][j] == map[i-1][j]) {
						toBottom[i][j] =  toBottom[i-1][j] + 1;
					}
					else toBottom[i][j] = 1;
				}
				answer = Math.max(toBottom[i][j], answer);
				
				int bottomIndex = N-1-i;
				if(bottomIndex == N-1) {
					toTop[bottomIndex][j] = 1;
				} else if(bottomIndex < N-1) {
					if(map[bottomIndex+1][j] == map[bottomIndex][j]) {
						toTop[bottomIndex][j] =  toTop[bottomIndex+1][j] + 1;
					}
					else toTop[bottomIndex][j] = 1;
				}
				answer = Math.max(toTop[i][j], answer);
				
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// i, j 를 교체해본다. 교체하면서 함께 바뀌는 부분은 따로 고려하지 않고 i, j 를 순회하며 확인한다.
				int right = j+1;
				int left = j-1;
				int top = i-1;
				int bottom = i+1;
				if(left >= 0 && map[i][j] != map[i][left]) { // 왼쪽에 있던 숫자를 i 위치로 옮긴다.
					if(right < N && map[i][right] == map[i][left]) answer = Math.max(answer, toLeft[i][right] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(top >= 0 && map[top][j] == map[i][left]) answer = Math.max(answer, toBottom[top][j] + 1);
					if(bottom < N && map[bottom][j] == map[i][left]) answer = Math.max(answer, toTop[bottom][j] + 1);
					if(bottom < N && top >= 0 && map[bottom][j] == map[i][left] && map[bottom][j] == map[top][j]) answer = Math.max(answer, toTop[bottom][j] + 1 + toBottom[top][j]);
				}
				if(right < N && map[i][j] != map[i][right]) { 
					if(left >= 0 && map[i][left] == map[i][right]) answer = Math.max(answer, toRight[i][left] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(top >= 0 && map[top][j] == map[i][right]) answer = Math.max(answer, toBottom[top][j] + 1);
					if(bottom < N && map[bottom][j] == map[i][right]) answer = Math.max(answer, toTop[bottom][j] + 1);
					if(bottom < N && top >= 0 && map[bottom][j] == map[i][right] && map[bottom][j] == map[top][j]) answer = Math.max(answer, toTop[bottom][j] + 1 + toBottom[top][j]);
				}
				if(top >= 0 && map[i][j] != map[top][j]) {
					if(bottom < N && map[bottom][j] == map[top][j]) answer = Math.max(answer, toTop[bottom][j] + 1);
					if(left >= 0 && map[i][left] == map[top][j]) answer = Math.max(answer, toRight[i][left] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(right < N && map[i][right] == map[top][j]) answer = Math.max(answer, toLeft[i][right] + 1);
					if(left >= 0 && right < N && map[i][right] == map[top][j] && map[i][right] == map[i][left]) answer = Math.max(answer, toLeft[i][right] + toRight[i][left] + 1);
				}
				if(bottom < N && map[i][j] != map[bottom][j]) {
					if(top >= 0 && map[top][j] == map[bottom][j]) answer = Math.max(answer, toBottom[top][j] + 1);
					if(left >= 0 && map[i][left] == map[bottom][j]) answer = Math.max(answer, toRight[i][left] + 1); // map[i][right] == map[i][left] : 왼쪽에 있는 숫자를 오른쪽에 붙이면 늘어나는 경우
					if(right < N && map[i][right] == map[bottom][j]) answer = Math.max(answer, toLeft[i][right] + 1);
					if(left >= 0 && right < N && map[i][right] == map[bottom][j] && map[i][right] == map[i][left]) answer = Math.max(answer, toLeft[i][right] + toRight[i][left] + 1);
				}
			}
		}
		
		System.out.println(answer);*/
		
	}
	
	
}