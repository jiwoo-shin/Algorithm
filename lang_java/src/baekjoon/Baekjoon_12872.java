package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12872 extends Solution {
	
	static int N, M, P;
	static long[][] memo;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		memo = new long[P+1][N+1]; // [P][N] : N개의 곡으로 길이 P 플레이리스트를 만들 때 만들 수 있는 경우의 수
		memo[0][0] = 1; // memo[1][1] = N임
		for(int i = 1; i < P+1; i++) {
			for(int j = 0; j < N+1; j++) {
				// j <= N 이면 사용하지 않았던 노래 사용 가능. 
				if(j > 0) memo[i][j] =  (memo[i][j] + memo[i-1][j-1]*(N-j+1))%1000000007; 
				// memo[i-1][j-1] 에 사용하지 않았던 새로운 노래를 i 번째 노래로 사용하는 경우 : i 번째에 오는 노래는 memo[i-1][j-1]에서 사용하지 않은 N-(j-1)가지가 가능하다. 
				
				// 사용하였던 노래 사용
				if(j > M) memo[i][j] = (memo[i][j] + memo[i-1][j]*(j-M))%1000000007; 
				// memo[i-1][j-1] 에 사용하였던 노래를 i 번째 노래로 사용하는 경우 : i 번째에 오는 노래는 memo[i-1][j] 의 플레이리스트에서 우측에서 M 개를 제외한 i-1-M 가지 가능이 아니고 플레이리스트를 만들 때 사용한 j가지의 노래 중 우측 M 개 제외하고 사용 가능하므로 j-M 개 가지 가능
			}
		}
		System.out.print(memo[P][N]+" ");
		
	}
}

