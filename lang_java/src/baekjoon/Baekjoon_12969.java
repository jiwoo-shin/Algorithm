package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12969 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

		System.out.println(dp(N, K));
	}
	static String dp(int N, int K) {
		String[][][][][] memo = new String[31][][][][];
		int[][] add_num = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		String[] add_string = {"A", "B", "C"};
		
		
		memo[1] = new String[1][2][2][2];
		memo[1][0][1][0][0] = "A";
		memo[1][0][0][1][0] = "B";
		memo[1][0][0][0][1] = "C";
		
		
		for(int i = 1; i <= N-1; i++) { // i로 i+1을 구하므로 1부터 30-1까지 가능하다
			int next_i = i+1;
			int max_k = (i*i-i)/2;
			int max_next_k = (next_i*next_i-next_i)/2;
			memo[next_i] = new String[max_next_k+1][next_i+1][next_i+1][next_i+1]; // 0개도 가능하므로 length+1(i+1) 가지 가능하다.
			boolean[][][][] visited = new boolean[max_next_k+1][next_i+1][next_i+1][next_i+1];
			for(int j = 0; j <= max_k; j++) {
				for(int a = 0; a <= i; a++) { 
					for(int b = 0; b <= i-a; b++) {
						int c = i-a-b; // c는 a, b, i의 개수로 정해지므로 0~ c <= i-a-b; 까지 for문 돌지 않아도 된다.
						if(memo[i][j][a][b][c]!=null) {
							int[] add_j = {0, a, a+b};
							for(int add = 0; add < 3; add++) { // A, B, C를 더함
								int next_j = j+add_j[add];
								int next_a = a+add_num[add][0];
								int next_b = b+add_num[add][1];
								int next_c = c+add_num[add][2];
								if(next_a <= next_i && next_b <= next_i && next_c <= next_i && next_j <= max_next_k && !visited[next_j][next_a][next_b][next_c]) {
									visited[next_j][next_a][next_b][next_c] = true;
									memo[next_i][next_j][next_a][next_b][next_c] = memo[i][j][a][b][c]+add_string[add];
									if(i == N-1 && next_j == K) { 
										return memo[next_i][next_j][next_a][next_b][next_c];
									}
								}
							}
						}
					}
				}
			}
		}
		return "-1";
	}
}

