package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Baekjoon_15988 extends Solution {

	// dp (bottom-up)
	static long bottomUp(long[] memo, int n) {
		memo[0] = 1;
		if(n > 1) memo[1] = 2;
		if(n > 2) memo[2] = 4;
		// f(n) = f(n-1) 로 만든 후 1을 더하는 경우 + f(n-2)로 만든 후 2를 더하는 경우 + f(n-3)으로 더한 후 3을 더하는 경우
		for(int i = 3; i < n; i++) {
			memo[i] = (memo[i-1] + memo[i-2] + memo[i-3])%1000000009;
		}
		return memo[n-1];
	}
	
	// dp (top-down)
	static long topDown(long[] memo, int n) {
		int index = n-1;
		if(memo[index] == 0) {
			if(index == 0) memo[index] = 1;
			else if(index == 1) memo[index] = 2;
			else if(index == 2) memo[index] = 4;
			else {
				// 1000000009 으로 나눈 나머지 이므로 (topDown(memo, n-1) + topDown(memo, n-2) + topDown(memo, n-3)) < 1000000009*3 이라 int 자료형 넘침
				memo[index] = (topDown(memo, n-1) + topDown(memo, n-2) + topDown(memo, n-3))%1000000009;
			}
		}
		
		return memo[index];
	}
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
	    StringBuilder sb = new StringBuilder();
	    
	    long[] memo = new long[1000000];
	    memo[0] = 1;
		memo[1] = 2;
		memo[2] = 4;
		
		// f(n) = f(n-1) 로 만든 후 1을 더하는 경우 + f(n-2)로 만든 후 2를 더하는 경우 + f(n-3)으로 더한 후 3을 더하는 경우
		for(int i = 3; i < 1000000; i++) {
			// 1000000009 으로 나눈 나머지 이므로 (topDown(memo, n-1) + topDown(memo, n-2) + topDown(memo, n-3)) < 1000000009*3 이라 int 자료형 넘침
			memo[i] = (memo[i-1] + memo[i-2] + memo[i-3])%1000000009;
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			sb.append(memo[n-1]).append('\n');
			
			// 굳이 여기서 호출해셔 구한 memo를 또 구할필요는 없음.
			// 여기서 이렇게 호출하면 memo 구해진게 또 구해짐.
            // 아니면 memo를 완전히 밖으로 뺴던가..
			// sb.append(bottomUp(new long[n], n)).append('\n');
			//sb.append(topDown(new long[n], n)).append('\n');
		}
		System.out.println(sb.toString());
		
	}
}
