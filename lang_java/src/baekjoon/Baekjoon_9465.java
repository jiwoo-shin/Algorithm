/*package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_9465 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] sticker;
        StringTokenizer st;
        int[][] memo;
	    StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sticker = new int[N][2];
            memo = new int[N][2]; 
            // 입력
            for(int sticker_j = 0; sticker_j < 2; sticker_j++) {
                st =  new StringTokenizer(br.readLine());
            	for(int sticker_i = 0; sticker_i < N; sticker_i++) {
            		sticker[sticker_i][sticker_j] = Integer.parseInt(st.nextToken());
            	}
            }
            if(sticker[0][0] > sticker[0][1]) {
            	memo[0][0] = sticker[0][0];
            	memo[0][1] = 0;
            } else  {
            	memo[0][0] = sticker[0][0];
            	memo[0][1] = 1;
            }

            memo[1][1] = 1-memo[0][1];
            memo[1][0] = memo[0][0] + sticker[1][memo[1][1]];
            //
            if(memo[1][0] < sticker[1][1-memo[1][1]] + sticker[0][memo[1][1]]) {  // 1번째 에서 구해진 것을 안떼고 다른 것을떼는 경우
            	memo[1][1] = 1-memo[0][1];
                memo[1][0] = sticker[1][1-memo[1][1]] + sticker[0][memo[1][1]];
            } 
            
            // f(n) = f(n-1) + 대각선으로 떼거나 이거나 f(n-2) + 둘 중 하나 떼거나 -> max(f(n-1) + n-1의 대각선 위치, f(n-2) + 0번째, f(n-2) + 1번째 )
            for(int memo_i = 2; memo_i < N; memo_i++) {
            	int max_j = 1 - memo[memo_i-1][1]; // 1이면 0, 0이면 1
            	int max = memo[memo_i-1][0] + sticker[memo_i][max_j];
            	
            	// memo[i-1][j] 가 추가로 더해질 곳은?
            	
            	//i-2, i번째에서 j가  같은 경우
               if(max < memo[memo_i-2][0] + sticker[memo_i][memo[memo_i-2][1]] + sticker[memo_i-1][1-memo[memo_i-2][1]]) {// i에서의 스티커의j 와 i-2에서의 j가 같은 경
                	max_j = memo[memo_i-2][1]; 
                	max = memo[memo_i-2][0] + sticker[memo_i][memo[memo_i-2][1]] + sticker[memo_i-1][1-memo[memo_i-2][1]];
            	}
            	
            	//i-2, i번째에서 j가  다른 경우
            	if(max < memo[memo_i-2][0] + sticker[memo_i][1-memo[memo_i-2][1]]) {
                	max_j = 1-memo[memo_i-2][1]; 
                	max = memo[memo_i-2][0] + sticker[memo_i][1-memo[memo_i-2][1]];
            	}
            	memo[memo_i][0] = max;
            	memo[memo_i][1] = max_j;
            }
            sb.append(memo[N-1][0]).append('\n');
	            
        }
        System.out.println(sb.toString());
        
	}
}*/
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_9465 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] sticker;
        StringTokenizer st;
        int[][] dp;
        
	    StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sticker = new int[N][2];
            dp = new int[N][3]; 
            // 입력
            for(int sticker_j = 0; sticker_j < 2; sticker_j++) {
                st =  new StringTokenizer(br.readLine());
            	for(int sticker_i = 0; sticker_i < N; sticker_i++) {
            		sticker[sticker_i][sticker_j] = Integer.parseInt(st.nextToken());
            	}
            }
            dp[0][0] = 0;
            dp[0][1] = sticker[0][0];
            dp[0][2] = sticker[0][1];
            
            for(int k = 1; k < N; k++) {
                // 이전 행동에 따라 다음에 가능한 행동이 정해짐. 따라서 이전 모든 행동의경우에 대해 저장 후 현재 행동을 실행해본다.
            	// 그냥 dp로 하면 어디서 뗐는지 알 수가 없음
            	// dp도 i에서 가능한 최적과 최적일때 어디를 떼었는지 저장 x
            	// 
                // 1. k에서 스티커를 떼지 않는 경우
                dp[k][0] = Integer.max(Integer.max(dp[k-1][0], dp[k-1][1]), dp[k-1][2]);
                
                // 2. k에서 스티커를떼는 경우
                // 2-1. 1번째 스티커 떼는 경우
                dp[k][1] = Integer.max(sticker[k][0] + dp[k-1][2], sticker[k][0] + dp[k-1][0]);
                
                // 2. i에서 스티커를떼는 경우
                // 2-2. 2번째 스티커 떼는 경우
                dp[k][2] = Integer.max(sticker[k][1] + dp[k-1][1], sticker[k][1] + dp[k-1][0]);
            }
        	
            sb.append(Integer.max(Integer.max(dp[N-1][0], dp[N-1][1]), dp[N-1][2])).append('\n');
	            
        }
        System.out.println(sb.toString());
        
	}
}