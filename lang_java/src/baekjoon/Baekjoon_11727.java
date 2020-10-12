package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_11727 extends Solution {
	
	static int[] memo;
	
	// top-down
	static int dp(int n) {
		int index = n-1;
		if(memo[index] == 0) {
			if(index == 0) memo[index] = 1;
			else if(index == 1)memo[index] = 3;
			else {
				memo[index] = (dp(n-1) + dp(n-2)*2)%10007; 
				// (memo[index-1] + memo[index-2]*2)%10007; 아님
				// (dp(index-1) + dp(index-2)*2)%10007 아님.. dp(n) 을 호출하면 dp 내에서 memo에 맞는 index로 바꿔 사용하기 때문에
			}
		}
		return memo[index];
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N]; // memo[x] = x-1 을 만드는 경우의 수
		
		
		// top-down
		 // System.out.println(dp(N));
	
		memo[0] = 1; // 1 만드는 경우의 수
		if(N > 1) memo[1] = 3;
		
		// bottom-up
		// f(n) = f(n-1) 로 만든 후 2x1타일 세로로 길게 사용하는 경우 + f(n-2) 로 만든 후 2x2  타일 쓰는 경우   + f(n-2) 로 만든 후  2x1타일 가로로 길게 사용하는경우
		for(int i = 2; i < N; i++) {
			memo[i] = (memo[i-1] + memo[i-2]*2)%10007;
			// memo[i-1] + memo[i-2] + 1이 아님. 
			//  memo[i-2]*2 : f(n-2) 로 만든 후 2x2  타일 쓰는 경우 / f(n-2) 로 만든 후  2x1타일 가로로 길게 사용하는경우
			// 2x2 타일 쓰는 경우가 f(n-2) 가지 있고 2x1 길게 사용하는 경우가 f(n-2) 가지가 있음.
		}
		System.out.println(memo[N-1]);
		
	}

}
