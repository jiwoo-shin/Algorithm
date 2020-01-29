package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11722 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; //A[i] :i 번째 숫자
       
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
        
        // 2.  memo[i] = 감소수열일 때 i 번째 원소에서 쓰일 수 있는 가장 최적의 값
        int[] memo = new int[N + 1];
        int length = 1;
        memo[0] = Integer.MAX_VALUE;
        memo[length] = A[0];
        for(int i = 1; i < N; i++) {
        	int j = length;
        	while(memo[j] <= A[i]) { // memo[j] <= A[i]인 경우 계속 돈다.
        		j--;
        	}
			memo[++j] = A[i]; // i, j에 대해 memo[j] > A[i]를 만족하는 최소 j인 경우 memo[j+1]은 A[i]이다. -> memo[j+1] <= A[i]를 만족하는 가장 작은 j 
			length = Integer.max(j, length); // length 와 j 중 큰 것이 길이...
        }
        System.out.println(length);
        
        /*
        // 1. memo[i] = 배열의 i+1 번째 원소를 사용하였을 때 가능한 가장 긴(최적인) 감소수열인 경우
        int[] memo = new int[N];
        memo[0] = 1;
        int answer = 1;
        for(int i = 1; i < N; i++) {
        	memo[i] = 1; // 초기값은 1 (연결이 불가능한 경우)
        	for(int j = 0; j < i; j++) { // i 보다 작은 모든 j에 대해 확인해야함
        		if(A[i] < A[j]) memo[i] = Math.max(memo[i], memo[j] + 1);
        	}
        	answer = Math.max(answer, memo[i]); // memo[N-1]이 정답이 아님
        }*/
	}
	
}