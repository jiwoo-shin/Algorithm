package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_13707 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] memo = new int[N][K]; // memo[i][j] = 숫자 i+1을 K+1개의 숫자로 만드는 경우의 수
        // memo[i][j] += memo[i-p][j-1] (0 <= p <= i), 0< j < K
        // memo[i-1][j] += memo[i-1-p][j-1] (0 <= p <=i-1) (p= 0도 가능한게 i를 j-1개로 만들고 뒤에 0 더하면 되니까)
        // memo[i][j] = memo[i-1][j] + memo[i][j-1]
        
        memo[0][0] = 1; 
        for(int j = 1; j < K; j++) {
        	memo[0][j] = j+1; // 1 을 j+1개로 만드는 (순서가 다르면 다른 것이므로 1이 아닌 j 가지 존재 (0과 1 순서 바꿔가면서 만든다.))
        }
        for(int i = 1; i < N; i++) {
        	memo[i][0] = 1;
        	for(int j = 1; j < K; j++) {
            	memo[i][j] = (memo[i-1][j] + memo[i][j-1])%1000000000;
            	//System.out.println(i+", "+j+" // "+memo[i][j]);
        	}
        }   
        
        System.out.println(memo[N-1][K-1]);
	}
}