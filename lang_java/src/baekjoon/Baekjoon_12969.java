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
		String answer = dp(new char[N], 0, 0, 0, 0, 0, new boolean[K+1][N+1][N+1][N+1], N, K);
		if(answer == null) answer = "-1";
		System.out.println(answer);
		
	}
	// bfs식으로 탐색하기 때문에 memo
	static String dp(int N, int K) {

		String[][][][] memo = new String[436][31][31][31]; // a,b,c의 개수가 i를 구하므로 i까지 배열넣을필요는 없음..
		boolean[][][][] visited = new boolean[436][31][31][31];
		int[][] add_num = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		String[] add_string = {"A", "B", "C"};
		
		
		memo[0][1][0][0] = "A";
		memo[0][0][1][0] = "B";
		memo[0][0][0][1] = "C";
		
		
		for(int i = 1; i <= N-1; i++) { // i로 i+1을 구하므로 1부터 30-1까지 가능하다
			int next_i = i+1;
			int max_k = (i*i-i)/2;
			int max_next_k = (next_i*next_i-next_i)/2;
			for(int j = 0; j <= max_k; j++) {
				for(int a = 0; a <= i; a++) { 
					for(int b = 0; b <= i-a; b++) {
						int c = i-a-b; // c는 a, b, i의 개수로 정해지므로 0~ c <= i-a-b; 까지 for문 돌지 않아도 된다.
						if(memo[j][a][b][c]!=null) {
							int[] add_j = {0, a, a+b};
							for(int add = 0; add < 3; add++) { // A, B, C를 더함
								int next_j = j+add_j[add];
								int next_a = a+add_num[add][0];
								int next_b = b+add_num[add][1];
								int next_c = c+add_num[add][2];
								if(next_a <= next_i && next_b <= next_i && next_c <= next_i && next_j <= max_next_k && !visited[next_j][next_a][next_b][next_c]) {
									visited[next_j][next_a][next_b][next_c] = true;
									memo[next_j][next_a][next_b][next_c] = memo[j][a][b][c]+add_string[add];
									if(i == N-1 && next_j == K) { 
										return memo[next_j][next_a][next_b][next_c];
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
	// dfs식으로 풀어서 현재의 answer만 저장하면됨
	static String dp(char[] answer, int n, int k, int a, int b, int c, boolean[][][][] visited, int N, int K) { // n : 현재 문자열의 길이, k : 현재 만족하는 개수, a: 현재 A 개수, b: 현재 B 개수, c : 현재 C 개수
		
		if(n==N) { // 현재 구한 문자열의 길이가 정답의 조건에 부합하는 경우
			if(k == K) return String.copyValueOf(answer); // 정답을 구한거임
			else return null; // 정답을 못구함.. 또록..문자 길이는 맞는데 K 가 다름..
		}
		if(k > K) return null; // 정답일 가능성이 없음.. k는 같거나 증가하므로
		
		if(!visited[k][a][b][c]) {
			visited[k][a][b][c] = true;
		} else return null;
		
		answer[n] = 'A';
		if(dp(answer, n+1, k, a+1, b, c, visited, N, K)!=null) return String.copyValueOf(answer);
		answer[n] = 'B';
		if(dp(answer, n+1, k+a, a, b+1, c, visited, N, K)!=null) return String.copyValueOf(answer);
		answer[n] = 'C';
		if(dp(answer, n+1, k+a+b, a, b, c+1, visited, N, K)!=null) return String.copyValueOf(answer);
		
		
		return null;
		
	}
}

