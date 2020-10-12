package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12865 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 물건의 개수 
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		int[][] things = new int[N][2];
		int[][] memo1 = new int[N][K+1]; // memo[i][j] : i 번째 물건을 넣을 때 무게 j 제한에 대해 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken()); // 무게
			things[i][1] = Integer.parseInt(st.nextToken()); // 가치
			if(things[i][0] <= K)memo1[i][things[i][0]] = things[i][1];
		}
		
		//System.out.println(dp1(things, memo1, N, K));
		System.out.println(dp(things, N, K));
	}
	static int dp(int[][] things, int N, int K) {
		int[] memo = new int[K+1]; // memo[i]: 무게 i인 경우 최대 가치
		// i 번째 물건을 사용하였을 때 무게 K~0 까지 구함
		for(int i = 0; i < N; i++) { // 0~i 번째 물건 사용한 경우 (물건은 하나가 있으면 한번밖에  사용할 수 없으므로)
			for(int j = K; j-things[i][0] >= 0; j--) { // 무게가  K가 되는 경우 (무게를 K~0 까지 만들어 보고 여기서 가능한 최고의 가치를 가져간다.)( 가져갈 수 있는 무게의 제한이 클수록 이득도ㅗ 커지므로 큰값부터 구해도 됨. 
				// j 를 + 1부터 K 까지 오름차순으로 게산할 경우 다음 K 에서 이전에 구한 값이 쓰일 수 있으므로 i 번째 물건 중복 사용되는 문제가 발생한다 ( // 1 2 / 1 1) )
				
				memo[j] = Math.max(memo[j], memo[j-things[i][0]] + things[i][1]); //i 를 쓰거나, i 사용하지 않거나 (memo[i-1]보다는 항상 큼.)
			}
		}

		print(memo);
		return memo[K];
	}
	static int dp1(int[][] things, int[][] memo, int N, int K) {
		for(int i = 0; i < N; i++) { // i 번째 물건에 대해 구함
			for(int j = 0; j <= K; j++) { // 각 무게 j 에 대해서 구함
				// i 번째 물건을 사용 가능한 경우 (j-things[i][0] >= 0)
				if(i == 0 && j > things[i][0]) memo[i][j] = memo[i][j-1];
				if(i > 0) {
					if(j-things[i][0] >= 0)memo[i][j] = Math.max(memo[i][j], memo[i-1][j-things[i][0]] + things[i][1]);
					memo[i][j] = Math.max(memo[i][j], memo[i-1][j]);
				}
				
			}
		}
	//	print(memo);
		return memo[N-1][K];
	}
}