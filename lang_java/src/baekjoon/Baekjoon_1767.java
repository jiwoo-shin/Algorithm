package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import common.Solution;

public class Baekjoon_1767 extends Solution {
	
	static int N, M, K;
	static long[][][] memo;
    static long[][][] d = new long[101][101][101];
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		memo = new long[N+1][M+1][K+1]; // 
		// 세로 한줄만 있는 경우 초기화
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= M; j++) memo[i][j][0] = 1; // 초기값 유의
			// 일부러 i, j 0 부터 넣음. 1 씩 작게 하지 x -> 1씩 작게하면 인덱스 값이 1일 때 사실은 2칸이라 2개 추가가 가능한데.. 조건을 그렇게 추가하면 오히려 복잡
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) { // 세로 한줄만 있는 경우 초기화 완료하였음
				for(int k = 1; k <= K; k++) {
					
					// 1. j 번째 세로줄에 아무것도 추가하지 않는 경우
					memo[i][j][k] += memo[i][j-1][k];
					
					// 2. j 번째 세로줄에 1개 추가하는 경우
					//memo[i][j][k] += memo[i][j-1][k-1];  이렇게 하면 추가한 것에 있는 줄이 2번 공격받는 경우가 생길 수 있음 -> 공격을 받거나/ 최대 한번만 받거나로 나눠야함
					// 2-1. j 번째 세로줄에 1개 추가하는데 안맞는 경우
					if(k >= 1) memo[i][j][k] += memo[i-1][j-1][k-1]*(i);
					if(k > 1) {
						// 2-2. j 번째 세로줄에 1개 추가하는데 맞는 경우
						if(j >= 2) memo[i][j][k] += memo[i-1][j-2][k-2]*(i)*(j-1);
						// 3. j 번째 세로줄에 2개 추가하는 경우
						if(i >= 2)memo[i][j][k] += memo[i-2][j-1][k-2]*(i*(i-1))/2;
					}
					
					memo[i][j][k] %= 1000001;
				}
			}
		}
		System.out.println(memo[N][M][K]);
		
		
	}
}

