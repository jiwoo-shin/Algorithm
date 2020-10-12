package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_1699 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] memo = new int[N+1]; // memo[i] : 숫자 i를 제곱수의 합으로 표현할 때, 제곱수 항의 최소 개수
        
        memo[1] = 1; // memo[0] = 0이여야함
        
        // i-1을 만들고 1을 더하는 경우 + i-2를 만들고 2를 더하는 경우 +.... +
        // i-1 만드는 사이 사이 1을 넣는 경우는고려하지 않아도 됨. 어차피 그 안에서 순서 바뀌는모든 경우의 수가 memo[i-1]에 포함되어있기 때문 . 
        // 마지막에 더할지 앞에더할지도 따로 따지지 않아도 됨. 어차피 i-1 + 0,i-2 + 1, .... 하면서 마지막에 오는 모든 경우를따지기 때문에. 1을 앞에넣고 i-1중 하나 가 마지막으로 가는게 저 많은 +들 중 i-1 합 중 하나의 마지막인 경우도 있음.
    	// memo[i] = min(memo[i-1] + memo[0], memo[i-2] + memo[1], ... )
        for(int i = 2; i < N+1; i++) {
        	memo[i] = i; // 가능한 최대 값. 모두 1로 표현하는 경우
        	for(int j = 1; j*j <= i; j++) { // i 보다 작은 제곱수에 대해 구해봄
        		// 제곱수를 만들고 더하는 경우
            	memo[i] = Integer.min(memo[i-j*j] + 1, memo[i]); // memo[0] = 0이므로 i가 제곱수이면 memo[0] + 1 로 1이 된다.
        	}
        }
        System.out.println(memo[N]);
	}
	
}