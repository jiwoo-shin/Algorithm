package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11055 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; //A[i] :i 번째 숫자
        StringTokenizer st;

        st =  new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] memo = new int[N]; // memo[i] = i-1 번째 배열의 숫자를 사용하였을 때 가능한 가장 큰 증가 수열
        memo[0] = A[0];
        int answer = A[0];
        for(int i = 1; i < N; i++) {
        	memo[i] = A[i]; // 초기값 필요하다. A[i]가 이전에 나온 숫자들 보다 작은 경우 memo[i]는 0이 아니라 A[i]이다 (102 101 102 같은 경우)
        	for(int j = i-1; j > -1; j--) {
        		// A[i] > A[j] 를 만족하는 가장 큰 j만 답이 아님.
        		// 1 100 2 102 인 경우 102 > 2 로 i = 3인 경우 가장 큰 j는 2이지만 1까지 다 봐야함.
        		if(A[i] > A[j] && memo[i] < memo[j] + A[i]) { // 1. A[i] > A[j] 이고 (증가수열이고) 2. 이전에 구한 답보다 최적의 해 인 경우
        			 memo[i] = memo[j] + A[i];
        		}
        	}
        	answer = Math.max(answer, memo[i]);
        }
        System.out.println(answer);
        
	}
	
}