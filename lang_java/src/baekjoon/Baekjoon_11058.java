package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11058 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		if(N < 6) System.out.println(N);
		else {
			long[] memo = new long[N];
			for(int i = 0; i < 6; i++) {
				memo[i] = (i+1);
			}
			for(int i = 6; i < N; i++) {
				long tmp = memo[i-1] + 1; // i-1 에서 A 를 입력한 경우
				for(int j = i-3; j >= 2; j--) {
					tmp = Math.max(tmp, memo[j]*(i-j-1)); // j 를 복사하여 사용 (i 에서 복사하여 사용 가능한 모든 경우에 대해 구해본다.) // 최대값이 되기 위해 복사하는 값이  i-1 에서와 i 에서 다를 수 있으므로 buffer를 따로 저장하지는 않는다.
				}
				memo[i] = tmp;
			}
			System.out.println(memo[N-1]);
		}
	}
	
}