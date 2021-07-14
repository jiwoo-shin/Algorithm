package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_10986 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] memo = new long[N]; //  i+1 번째 숫자 까지 합
		int[] mod = new int[M]; //  memo[i] 까지 나머지가 j 인 것이 몇개 있는지
		
		st = new StringTokenizer(br.readLine());

		memo[0] = Integer.parseInt(st.nextToken());
		int modM = (int)(memo[0] % M);
		mod[modM]++;
		long answer = modM == 0 ? 1 : 0; // answer 은 long.. 최대 10^6(10^6+1)/2 가지가 될 수 있으므로
		
		
		for(int i = 1; i < N; i++) { // i번째 까지에 대해서만 고려한다.
			memo[i] = Integer.parseInt(st.nextToken()) + memo[i-1]; // i번째 까지의 합을 구한다.
			modM = (int)(memo[i] % M); // i번째 까지 합의 나머지를 구한다.
			answer += mod[modM];  // i 번째 까지의 합의 나머지가 k이면 그 k를 제외하면 나누어 떨어지므로. i 번째 이전의 연속된 숫자의 합 중 나머지가 k인 것은 제외하면 M으로 나누어 떨어진다.
			if(modM == 0) {
				answer += 1; // 자기 자신 하나만 있는 경우
			}
			mod[modM]++;
		}
		System.out.println(answer);
		

	}
}