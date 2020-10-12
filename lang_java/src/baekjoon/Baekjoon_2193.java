package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_2193 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] memo = new long[N][2]; // 범위 주의!
        // memo[i] = i+1자리의 이친수의 개수.
        // memo[i][j] = i+1 자리에서 j로 끝나는 이친수의 개수
        memo[0][1] = 1;
        
        // f(n) = f(n-1)에서 1을 붙이는 경우 + f(n-1)에서 0을 붙이는 경우 - 이친수가 아닌 경우
        // f(n) = f(n-1)*2 - 1로 끝나는 경우
        // f(n) = memo[i-1][0] + memo[i-1][1]임...
        for(int i = 1; i < N; i++) {
        	memo[i][0] = memo[i-1][0] + memo[i-1][1]; // 0을 붙이는 경우
        	memo[i][1] = memo[i-1][0]; // 1을 붙이는 경우
        }
        System.out.println(memo[N-1][0] + memo[N-1][1]);
        
	}
}