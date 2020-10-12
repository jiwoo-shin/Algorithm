package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_11052 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String[] strP = br.readLine().split(" ");
        int[] P = new int[N];
        
		int max = 0;
		P[0] = Integer.parseInt(strP[0]);
		
		for(int i = 1; i < N;i++) {
			P[i] = Integer.parseInt(strP[i]);
			// max = 0; max를 0으로 초기화할 필요는 없음. f(n)은 항상 f(n-1)보다크니까.. f(n-1) + f(1) , ...중에서 최대값을 찾는 것이기 때문,
			
			// n은 1과 n-1 / 2와 n-2 ... / 그냥 n 이런식으로 만들 수 있으므로
			// f(n) = max((f(1) + f(n-1)), (f(2) + f(n-2)), ''' , n개 들어있는 카드 가격 )
			for(int j = 0; j <= (i/2); j++) {
				if(max < P[j] + P[(i-1)-j]) max =  P[j] + P[(i-1)-j];
			}
			if(max > P[i])P[i] = max;
		}
		
		System.out.println(P[N-1]);
		
	}
}