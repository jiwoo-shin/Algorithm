package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11053 extends Solution {
	
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

        int[] memo = new int[N]; 
        int answer = 1;
        memo[0] = 1;
        // 1. memo[i] : i+1번째 숫자에서 가능한 부분수열의 최대 길이 인 경우
        // memo[i] = memo[j]+1 (단, A[i] < A[j]를 만족하는 것 중 memo[j]가 최대인 j)
        for(int i = 1; i < N; i++) {
            memo[i] = 1;
        	for(int j = 0; j < i; j++) {
        		if(A[j] < A[i] && memo[i] < memo[j]+1) memo[i] = memo[j]+1;
        	}
        	if(memo[i] > answer) answer = memo[i];
        }
        System.out.println(answer);

        /*// 2. memo[i] : 길이 i인 수열에서 가능한 최적 (가장 작은) 숫자의 값
        // memo[i] = A[j] (memo[i-1] < A[j]인 경우)
        // memo[i-k] = A[j] (memo[i-1] > A[j]인 경우, A[i-k] < A[j] < A[i-k+1]를 만족하는 값)
        memo[0] = A[0];
        int j = 0;
        for(int i = 1; i < N; i++) { // 모든 A[i]에 대해 어느 memo에 넣을 지 정한다.
        	if(memo[j] < A[i]) {
        		memo[++j] = A[i]; // memo[j] 보다 A[i] 가 크므로 이어진 수열에 A[i]를 붙일 수 있다.
        	} else { // memo[j]에 이어서 A[i]를 못 붙이는 경우
        		int tmp_j = 0;
        		while(memo[tmp_j] < A[i] && tmp_j < i) { // memo[tmp_j] >= A[i]인 경우 memo[tmp_j]는 A[i]로 바뀐다.
        			tmp_j++;
        		}
        		memo[tmp_j] = A[i];
        	}
        }
        
        System.out.println(j+1); */
        
        
	}
	
}