package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_2156 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int number; // i 번째 잔에 들어있는 포도주의양
        int prev_number; // 이전 잔(i-1번째)에 들어있는 포도주의 양
        
        int[][] dp = new int[n][3]; // dp[n][i] = n+1번째 잔을 연속 i잔 마셔서 마시는 경우 최대로 마실 수 있는 잔
        // 현재 할 수 있는 행동 : 안마시기, 1잔마시기, 2잔마시기
        // dp[i][0] = max(dp[i-1][0],[1],[2]) (마시지 않는다.)
        // dp[i][1] = dp[i-1][0] + number // 이전잔을 안마시고 현재 잔을 마신다 (연속 1잔)
        // dp[i][2] = dp[i-2][0] + number + prev_number // 이전잔,현재잔 모두 마신다 (연속 2잔)
        
        
        // 2차원 배열로 이렇게 안해도 dp[i] = i 번째 에서의최적이면
        // dp[i] = Max(i 안마심(i-1최적), 한잔마셔서 i (i-2최적 + i 포도주 양), 2잔마셔서 i(i-3최적 + i-1 포도주양+i-2포도주 양))
        //해도 되는게 최적이 쓰이기 때문에 현재 최적 = 이전 최적 + 현재 가능한 행동
        // 행동 가능을 판별하는 조건이 특별히 없이 최적을구하고 구하면 된다. (그냥 포도주를 마시거나 말거나의상태임)
        // 만약 행동  가능 여부가 특정 조건으로 판별이 필ㅇ요핟다면 추가로 저장 필요핟.

        number = Integer.parseInt(br.readLine());
    	dp[0][1] = number;
    	prev_number = number;
    	if(n >= 2) {
    		number = Integer.parseInt(br.readLine());
        	dp[1][0] = dp[0][1]; // 1번째 잔 마시고 2번째 잔 안마시는 경우
        	dp[1][1] = number; // 1번째잔 안마시고 2번째 잔 마시는 경우
        	dp[1][2] = number + prev_number; // 1번째잔 마시고 2번째 잔 마시는 경우
        	prev_number = number;
    	}
        for(int i = 2; i < n; i++) {
        	number = Integer.parseInt(br.readLine());
        	dp[i][0] = Integer.max(Integer.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
        	dp[i][1] = dp[i-1][0] + number;
        	dp[i][2] = dp[i-2][0] + number + prev_number;
        	prev_number = number;
        }
        
        System.out.println(Integer.max(Integer.max(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));
	}
}