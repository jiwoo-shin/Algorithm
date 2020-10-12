package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_16194 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
		StringTokenizer st = new StringTokenizer(br.readLine());
        int[] P = new int[N]; // P[n] : 카드 n장으을 사는데 드는 최소 가격
        int min = Integer.MAX_VALUE;
		P[0] = Integer.parseInt(st.nextToken());
		int limit = 0;
		
		for(int i = 1; i < N; i++) {
			min = Integer.parseInt(st.nextToken());
			limit = i/2;
			for(int j = 0; j <= limit; j++) {
				// n은 1장& n-1장 , 2장&n-2장...이런식으로 만들 수 있으므로
				// f(n) = min(f(1)+f(n-1), f(2)+f(n-2), ... , f(n));
				if(min > P[j] + P[i-1-j]) min = P[j] + P[i-1-j];
			}
			P[i] = min;
		}
		
		System.out.println(P[N-1]);
		
	}
}