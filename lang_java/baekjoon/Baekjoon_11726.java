package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_11726 extends Solution {
	static int[] memo;
	
	// top-down
	static int dp(int n) {
		if(memo[n-1] == 0) {
			if(n == 1)memo[0] = 1;
			else if (n == 2)memo[1] = 2;
			else  memo[n-1] = (dp(n-1) + dp(n-2))%10007;
		}
		return memo[n-1];
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N]; // memo[x] = x-1 을 만드는 경우의 수
		
		
		// top-down
		// System.out.println(dp(N));
	
		memo[0] = 1; // 1 만드는 경우의 수
		if(N > 1) memo[1] = 2;
		
		// bottom-up
		// f(n) = f(n-1) 만들고 세로로 블럭 넣는 경우  + f(n-1) 만들고 가로로 블럭 넣는 경우 가 가능하므로
		// f(n) = f(n-1) + f(n-2)
		for(int i = 2; i < N; i++) {
			memo[i] = (memo[i-1] + memo[i-2])%10007;
			// (A+B)%C = (A%C + B%C)%C 
			// 이므로 ((i-1 만드는 경우의 수) + (i-2 만드는 경우의 수))%10007 
			// = ((i-1 만드는 경우의 수)%10007 + (i-2 만드는 경우의 수)%10007 )%10007 이므로 
			// memo[] 는 (i-1 만드는 경우의 수)%10007 를 저장하여도 무관.
		}
		System.out.println(memo[N-1]);
		
	}

}
