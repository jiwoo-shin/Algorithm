package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2169 extends Solution {
	
	
	static int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 상 하 우 좌
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] memo = new int[N][M];
		// int[][][] memo = new int[N][M][3]; // 아래로, 왼쪽으로, 오른쪽으로
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i == 0) {
					if(j == 0) memo[i][j] = map[i][j];
					else memo[i][j] = map[i][j] + memo[i][j-1]; // 최상단 발판들은 항상 시작점에서 좌측으로 밖에.. 오른쪽으로 오려면 위로 올라오는 수밖에 없는에 위로는 못오니까..
				}
			}
		}
		
		//  왼쪽, 오른쪽, 아래방향에서 -> i, j 로 갈 때 얼마나 많은 점수를 챙길 수 있는지
		for(int i = 1; i < N; i++) {
			// toLeft, toRight 별개이다. 함께 쓰면 동일 위치를 여러번 방문하는 것이 허용된다.
			// 왼쪽으로
			int[] toLeft = new int[M];
			toLeft[M-1] = memo[i-1][M-1] + map[i][M-1]; // memo[i-1][M-1] + map[i][M-1] : 아래로
			for(int j = M-2; j >= 0; j--) {
				toLeft[j] = Math.max(toLeft[j+1], memo[i-1][j]) + map[i][j]; // toLeft[j+1] : 왼쪽으로, memo[i-1][j]: 아래로
			}
			
			// 오른쪽으로
			int[] toRight = new int[M];
			toRight[0] = memo[i-1][0] + map[i][0];//memo[i][0]; // Integer.MIN_VALUE.. 이러면 다음에 오른쪽으로 이동할 때 의도하지 않은 숫자를 사용하게 된다.
			for(int j = 1; j < M; j++) {
				// toRight[j+1] : 오른쪽으로, memo[i-1][j]: 아래로
				toRight[j] =  Math.max(toRight[j-1], memo[i-1][j]) + map[i][j]; // toRight[j-1] + map[i][j];가 아닌게 toLeft 보다 memo가 커도 toRIght 보다는 작을 수 있으므로 여기서도 max, min 확인 필요
			}
			for(int j = 0; j < M; j++) memo[i][j] = Math.max(toRight[j], toLeft[j]); 
		}
		System.out.println(memo[N-1][M-1]);
	}
	
	
}