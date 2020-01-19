package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2225 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] memo = new int[N+1][K]; // memo[i][k] : 숫자 i을 길이 k+1의 항인 덧셈으로 표현하는 경우의 수
        
         
        for(int i = 0; i <= N; i++) {
        	memo[i][0] = 1; // 항 하나로 표현 가능한 경우
        }
        
    	// memo[i][k] = memo[i][k] + memo[i-1][k-1] + memo[i-1][k-1] + ... + memo[0][k-1] // memo[i][k]는 0을 더하는 경우
        for(int k = 1; k < K; k++) {
        	for(int i = 0; i < N+1; i++) {
        		for(int j = 0; j <= i; j++) {
        			memo[i][k] = (memo[i][k] + memo[j][k-1])%1000000000;
        		}
        	}
        }
        // 아래의 점화식으로도 구할 수 있다.
        // memo[i][k] = memo[i-1][k] + memo[i][k-1] 
        // 1. i-1을 k의 길이로 만들어서 제일 앞의 숫자에 1씩 더한다. // 2. i를 만든 후 맨 앞에 0을 추가로 붙여준다.
        // 1과 2과 겹칠일 x > 1ㅇ은 맨 앞에 +1이라 맨 앞 숫자가 0일 경우는 없으므로
        // i-1 을 k의 길이로 만든 후 각 항에 대해 각각 +1을 해주는 경우는고려할 필요가 없다. 결국 다 들어가 있는 최적의 경우이기 때문
        
        System.out.println(memo[N][K-1]);
	}
	
}