package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11048 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] input = new int[N][M];
		int[][] memo = new int[N+1][M+1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < N+1; i++) {
			memo[i][M] = -1;
		}
		for(int j = 0; j < M; j++) {
			memo[N][j] = -1;
		}
		System.out.println(dp_recursion(N, M, N, M, input, memo));
		

		/*
			int[][] memo = new int[N][M]; // memo[i][j] : 좌표 (i+1,j+1) 에서 얻을 수 있는 사탕의 최대값
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					if(j > 0 && i == 0) memo[i][j] = memo[i][j-1] + Integer.parseInt(st.nextToken());  // 제일 윗줄은 (1,1)에서 쭉 오는 방법밖에 없음
					else if(i > 0 && j == 0) memo[i][j] = memo[i-1][j] + Integer.parseInt(st.nextToken());// 제일 왼쪽줄은 (1,1)에서 쭉 오는 방법밖에 없음
					else  memo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println(dp(N, M, memo));
		 */

	}
	
	static int dp_recursion(int N, int M, int i, int j, int[][] input, int[][] memo) {// i, j 에서의 최대 위치를 반환
		if(memo[i][j] != -1) return memo[i][j]; // 이미 구해진 값인 경우 사용한다. (이게 없으면 중복으로 사용되는 위치에 대해서 계속 계산해야하므로 시간이 오래걸림.)
		
		// 바로 return이 아니고 memo 배열에 저장한다.
		if(i == 1 && j == 1) memo[i][j] = input[i-1][j-1]; // memo는 input 보다 가로세로 1씩 더 길다.
		else if(i == 0 || j == 0) memo[i][j] = 0;
		else memo[i][j] = Math.max(dp_recursion(N, M, i-1, j, input, memo), dp_recursion(N, M, i, j-1, input, memo)) + input[i-1][j-1]; // memo는 i, j 가 -1인 경우도 포함하여 0으로 시작하기 때문에 실ㅈ제 위치는 input 보다 1씩 크다.
		
		return memo[i][j]; // input[][] 아님
	}
	
	static int dp(int N, int M, int[][] memo) {
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < M; j++) {
				memo[i][j] = Math.max(Math.max(memo[i-1][j], memo[i][j-1]), memo[i-1][j-1]) + memo[i][j]; // 왼쪽, 위 중 큰 위치에서 출발한다. (대각선은 어차피 왼쪽, 위에 비해서는 무조건 작으므로 비교 x)
			}
		}
		return memo[N-1][M-1];
	}
	
}
