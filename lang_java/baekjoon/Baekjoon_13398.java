package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_13398 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] memo = new int[n][2]; 
        int[] numbers = new int[n];

        st =  new StringTokenizer(br.readLine());
        int answer = memo[0][0] = memo[0][1] = numbers[0] = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i < n; i++) {
        	numbers[i] = Integer.parseInt(st.nextToken()); //입력
        	
        	// memo[n][0] : n-1 번째 숫자까지 숫자를 하나도 제거하지 않은 경우 (무조건 제거하지 않은 경우만 저장) -> 최적이 아니라 제거 안한 값들을 저장.최적을 찾기위한 값 (만약 제거 불가능 하다면 이게 최적이겠ㅈㅣ)
        	// memo[n][1] : n-1 번째 숫자까지 숫자를 제거하거나 하지않은 경우 (제거한 경우이거나 제거하지않은경우일수 있음.) ->제거하거나 제거하지않거나 모두 고려한 최적값
        	// 제거 조건이 있으므로 2차원 배열에 따로 만든다.
            // memo[n][0] = max(memo[n-1][0]+num[n], num[n]); // n을 수열에 포함시키거나 n 하나만하거나
            // memo[n][1] = max(memo[n-1][0], memo[n-1][1] + numbers[n], numbers[n]); // n을 제거하거나 하지 않거나 포함 시키거나 n하나만하거나
        	// 1. n을 제거한다.(n-1 무조건!제거안한 경우) 2. n을제거하지 않는다 (n-1 제거or제거안한경우 인 경우의 최적 + n). 
        	// 3. n 혼자쓴다. ->이경우는 memo[n][0]에저장됨
        	
        	memo[i][0] = Integer.max(memo[i-1][0] + numbers[i], numbers[i]);
        	memo[i][1] = Integer.max(memo[i-1][1] + numbers[i], memo[i-1][0]);
        	
        	// for문 안에서 memo[i-1]만 쓰고 최대값만 구하면 되는거니까 memo 배열 필요 없이 값 몇개만 있어도 괜찮을듯.
        	answer = Integer.max(memo[i][1], answer);
        	answer = Integer.max(memo[i][1], answer);
        }
        
        // memo[n-1]이 답은 아님.memo[n-1]은 n-1 번째숫자를 사용한 경우 가능한 최적해.
        System.out.println(answer);
	}

}