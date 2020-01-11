package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_15990 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
	    StringBuilder sb = new StringBuilder();
        
        long[][] P = new long[100000][3]; // P[i] 는 숫자 i+1을 만들 수 있는 방법의수
        P[0][0] = 1; // 1
        P[1][1] = 1; // 2
        P[2][0] = 1; // 2+1
        P[2][1] = 1; // 1+2
        P[2][2] = 1; // 3
        
        // 4를 만들기 위해선 1을 만들고 + 3 / 2를 만들고 +2 /3을 만들고 +1 - (연속해서 사용되는 경우 -> 2를 만들고 +2, 3을 만들고 +1 중에 1로 끝나는..)
        // f(n) = f(n-1) + f(n-2) + f(n-3) - (f(n-1) 중 1로 끝나는 + f(n-2) 중 2로 끝나는 + f(n-3) 중 3으로 끝나는)
        for(int i = 3; i < 100000; i++) {
        	P[i][0] = (P[i-1][1] + P[i-1][2])%1000000009; // f(n-1) 중 1로 끝나는 것 제외
        	P[i][1] = (P[i-2][0] + P[i-2][2])%1000000009; // f(n-2) 중 2로 끝나는 것 제외
        	P[i][2] = (P[i-3][0] + P[i-3][1])%1000000009; // f(n-3) 중 3로 끝나는 것 제외
        }
        
        for(int i = 0; i < T; i++) {
        	int n = Integer.parseInt(br.readLine());
        	// 여기서 또 나머지로 나눠줘야함. -> P[n-1][0] + P[n-1][1] + P[n-1][2]이 1000000009보다 커질 수 있으므로
        	sb.append((P[n-1][0] + P[n-1][1] + P[n-1][2])%1000000009).append('\n');
        }
		System.out.println(sb.toString());
		
	}
}